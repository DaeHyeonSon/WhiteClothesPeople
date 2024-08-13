package com.whitepeoples.wooso

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.whitepeoples.wooso.ui.theme.WoosoTheme
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    private lateinit var webView: WebView
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private var filePathCallback: ValueCallback<Array<Uri>>? = null
    private lateinit var webAppInterface: WebAppInterface

    private lateinit var fileChooserLauncher: ActivityResultLauncher<Intent>

    // IP 변수 정의
    private var ip = "192.168.127.119"

    // 알림 권한 요청 런처
    private val requestNotificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // 알림 권한이 허용됨
                Log.d("MainActivity", "Notification permission granted")
            } else {
                // 알림 권한이 거부됨
                Log.d("MainActivity", "Notification permission denied")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 알림 권한 요청 (Android 13 이상)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        // WebView 초기화
        webView = WebView(this).apply {
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // WebView가 로드된 후에 FCM 토큰을 JavaScript로 전달
                    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            Log.w("WOOSO", "Fetching FCM registration token failed", task.exception)
                            return@addOnCompleteListener
                        }
                        val token = task.result
                        Log.d("FCM", "FCM Token: $token")
                        sendTokenToWebView(token)
                    }
                }
            }
            webChromeClient = object : WebChromeClient() {
                override fun onShowFileChooser(
                    webView: WebView?,
                    filePathCallback: ValueCallback<Array<Uri>>?,
                    fileChooserParams: FileChooserParams?
                ): Boolean {
                    this@MainActivity.filePathCallback = filePathCallback
                    val intent = fileChooserParams?.createIntent()
                    if (intent != null) {
                        fileChooserLauncher.launch(intent)
                    }
                    return true
                }
            }
            settings.javaScriptEnabled = true
            settings.allowFileAccess = true
            settings.domStorageEnabled = true
        }

        webAppInterface = WebAppInterface(this)
        webView.addJavascriptInterface(webAppInterface, "Android")

        // IP를 포함한 URL 사용
        webView.loadUrl("http://$ip:8080/index.html")

        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                webAppInterface.getAllContacts()
            } else {
                Log.w("WOOSO", "Permission denied for reading contacts")
                runOnUiThread {
                    Toast.makeText(this, "Permission denied. Unable to access contacts.", Toast.LENGTH_LONG).show()
                }
            }
        }

        fileChooserLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val resultData = result.data
            val resultUris = if (result.resultCode == RESULT_OK && resultData != null) {
                resultData.data?.let { arrayOf(it) }
            } else {
                null
            }
            filePathCallback?.onReceiveValue(resultUris?.filterNotNull()?.toTypedArray())
            filePathCallback = null
        }

        setContent {
            WoosoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WebViewContainer(innerPadding = innerPadding, webView)
                }
            }
        }
    }

    private fun sendTokenToWebView(token: String) {
        webView.evaluateJavascript("javascript:receiveToken('$token')", null)
    }

    inner class WebAppInterface(private val context: Context) {
        @JavascriptInterface
        fun pickContacts() {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
            } else {
                getAllContacts()
            }
        }

        @SuppressLint("Range")
        fun getAllContacts() {
            val contacts = mutableListOf<JSONObject>()
            val cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                ),
                null,
                null,
                null
            )
            cursor?.use {
                var count = 0
                while (it.moveToNext() && count < 300) {
                    val name = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))?.trim() ?: ""
                    val phoneNumber = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val cleanPhoneNumber = phoneNumber.replace("[^\\d]".toRegex(), "")

                    if (name.isNotBlank() && cleanPhoneNumber.isNotBlank()) {
                        val contactJson = JSONObject().apply {
                            put("name", name)
                            put("phoneNumber", cleanPhoneNumber)
                        }
                        contacts.add(contactJson)
                        count++
                    }
                }
            }
            Log.w("phone-number", "result: $contacts")
            sendContactsToJavaScript(contacts)
        }

        fun sendContactsToJavaScript(contacts: List<JSONObject>) {
            val jsonArray = JSONArray(contacts)
            val jsonContacts = jsonArray.toString()
            val encodedJson = jsonContacts.replace("\\", "\\\\")
                .replace("'", "\\'")
                .replace("\"", "\\\"")

            runOnUiThread {
                webView.evaluateJavascript("javascript:receiveContacts('$jsonContacts')", null)
            }
        }
    }

    companion object {
        private const val FILE_CHOOSER_REQUEST_CODE = 1
    }
}

@Composable
fun WebViewContainer(innerPadding: PaddingValues, webView: WebView) {
    AndroidView(
        factory = {
            webView
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    )
}
