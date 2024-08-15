<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Subscription</title>
    <style>
        #subscription-options, #current-subscription {
            display: none;
            margin-top: 20px;
        }
        .subscription-info {
            font-size: 16px;
        }
        .subscription-button {
            padding: 10px 20px;
            margin-right: 10px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<button onclick="isSubValidate()">구독하기</button>

<!-- 구독 옵션 선택 영역 -->
<div id="subscription-options">
    <h3>구독 옵션을 선택하세요:</h3>
    <button class="subscription-button" onclick="subscribe('PREMIUM')">PREMIUM</button>
    <button class="subscription-button" onclick="subscribe('BASIC')">BASIC</button>
</div>

<!-- 현재 구독 상태 영역 -->
<div id="current-subscription">
    <h3>현재 구독 상태</h3>
    <p class="subscription-info" id="subscription-name"></p>
    <p class="subscription-info" id="subscription-renewal"></p>
    <button class="subscription-info" onclick="unsubscribe()">구독 철회</button>
    
</div>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
async function isSubValidate() {
    try {
        // userId를 바로 할당
        const response = await axios.get("http://localhost/subscription/details");
        const userId = response.data.userId; // 여기서 userId는 response.data 내에 있다고 가정

        console.log('User ID:', userId);

        if (!userId) {
            alert('User ID is not available.');
            return;
        }

        // 구독 상태 확인 요청
        const subscriptionResponse = await axios.post("http://localhost/subscription/check-subscription", null, {
            params: { userId: userId }
        });

        const isSubscribed = subscriptionResponse.data;
        console.log('Is Subscribed:', isSubscribed.isSubscribed);

        if (isSubscribed.isSubscribed==true) {
        	// 구독 옵션은 숨기기
            document.getElementById('subscription-options').style.display = 'none';
            // 구독 중일 경우 구독제 이름과 리뉴얼 날짜를 가져오기
            const statusResponse = await axios.get("http://localhost/subscription/currentStatus", {
                params: { userId: userId }
            });

            console.log('Status Response:', statusResponse.data);

            const { subscriptionName, renewalDate } = statusResponse.data;
            
            document.getElementById('current-subscription').style.display = 'block';
            document.getElementById('subscription-name').innerHTML = "구독제: " + subscriptionName;
            document.getElementById('subscription-renewal').innerText = "갱신일: " + renewalDate;

        } else {
        	// 현재 구독 상태 정보는 숨기기
            document.getElementById('current-subscription').style.display = 'none';
            // 구독하지 않은 경우 구독 옵션 표시
            document.getElementById('subscription-options').style.display = 'block';
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Failed to process request.');
    }
}

async function subscribe(plan) {
    try {
        // Fetch user details
        const userResponse = await axios.get('http://localhost/subscription/details');
        const userData = await axios.get('http://localhost/payment/details');
        
        const user = userResponse.data;
        const userId = user.userId;
        
        const userDetail = userData.data;

        console.log('User ID:', userId);
        console.log('UserData:', userDetail);

        if (!userId) {
            alert('User ID is not available.');
            return;
        }

        // Generate a unique merchant_uid
        const now = new Date();
        const dateString = now.toISOString().replace(/[-:T]/g, '').split('.')[0]; // YYYYMMDDHHMMSS
        const merchant_uid = `${userId}${dateString}`;
        let amount = null;
        if(plan=="BASIC"){
        	amount = 100
        }else{
        	amount = 14900
        }

        IMP.init(`${impCode}`); 
        IMP.request_pay(
            {
                pg: "nice_v2",        
                pay_method: "card",   
                merchant_uid: merchant_uid, 
                name: plan, // Subscription plan name
                amount: amount, // Example amount; replace with the actual plan amount
                buyer_email: userDetail.email,
                buyer_name: userDetail.name,
                buyer_tel: userDetail.phone,
                buyer_addr: userDetail.address,
                buyer_postcode: userDetail.postcode
            },
            async function (res) {
                if (!res.error_code && !res.error_msg) {
                    console.log("Payment Success");
                    console.log(res.imp_uid);
                    console.log(res.merchant_uid);

                    // Payment was successful, now process the subscription
                    try {
                        const subscribeResponse = await axios.post('http://localhost/subscription/subscribe', null, {
                            params: {
                                userId: userId,
                                plan: plan,
                                merchant_uid:res.merchant_uid
                            }
                        });

                        alert('Subscription successful!');
                        // Optionally, reload or update the subscription status
                        isSubValidate();
                    } catch (error) {
                        console.error('Error during subscription:', error);
                        alert('Failed to subscribe.');
                    }
                } else {
                    // Handle payment failure
                    console.error('Payment Error:', res.error_msg);
                    alert('Payment failed.');
                }
            }
        );
    } catch (error) {
        console.error('Error fetching user data:', error);
        alert('Failed to initiate subscription.');
    }
}

async function unsubscribe() {
    try {
        // 사용자 정보 가져오기
        const response = await axios.get("http://localhost/subscription/details");
        const userId = response.data.userId; // 여기서 userId는 response.data 내에 있다고 가정
        const merchantId = response.data.merchantId; // merchantId도 response.data 내에 있다고 가정

        console.log('User ID:', userId);
        console.log('Merchant ID:', merchantId);

        if (!userId || !merchantId) {
            alert('User ID or Merchant ID is not available.');
            return;
        }
        
        // 먼저 able to cancle인지 확인해야겠다.
        
        // 환불 요청
        const refundResponse = await axios.post("http://localhost/payment/refund", null, {
        	params: {
                userId: userId,
                merchant_uid:merchantId
            }
        });
        
        console.log(refundResponse);

        // 환불 결과 확인
        if (refundResponse.status === 200) {
            // 환불이 성공하면 구독 취소 요청
            try {
                const unsubscribeResponse = await axios.post("http://localhost/subscription/unsubscribe", null, {
                    params: { userId: userId }
                });

                if (unsubscribeResponse.status === 200) {
                    alert('Subscription cancelled and refund processed successfully.');
                } else {
                    alert('Failed to cancel subscription.');
                }
            } catch (unsubscribeError) {
                console.error('Error unsubscribing:', unsubscribeError);
                alert('Failed to cancel subscription.');
            }
        } else {
            alert('Refund failed.');
        }
    } catch (error) {
        console.error('Error processing refund:', error);
        alert('Failed to process refund.');
    }
}

</script>
</body>
</html>
