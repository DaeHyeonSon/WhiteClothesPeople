package com.whitepeoples.wooso

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class WoosoFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // FCM 메시지 수신 처리
        remoteMessage.notification?.let {
            Log.d("notification-wooso", "${it.body}")
            sendNotification(it.body)
        }
    }

    private fun sendNotification(messageBody: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        // FLAG_IMMUTABLE 플래그 추가
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        } else {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        // 알림 채널 ID 설정
        val channelId = getString(R.string.default_notification_channel_id)

        // 알림 생성
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("FCM Message")
            .setContentText(messageBody ?: "No message body")
            .setSmallIcon(R.mipmap.ic_launcher) // 알림 아이콘 설정
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // 우선순위 높이기

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Android O 이상에서는 Notification Channel이 필요합니다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // NotificationChannel 생성
            val channelName = "Default Channel"
            val channelDescription = "This is the default notification channel for Wooso app."
            val importance = NotificationManager.IMPORTANCE_HIGH // 중요도 높이기

            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }

            // NotificationManager를 통해 채널 등록
            notificationManager.createNotificationChannel(channel)
        }

        // 알림 표시
        notificationManager.notify(0, notificationBuilder.build())
    }
}
