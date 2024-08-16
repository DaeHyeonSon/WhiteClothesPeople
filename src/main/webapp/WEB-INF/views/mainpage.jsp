<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.whitepeoples.wooso.model.dto.SessionDTO"%>
<%
// 세션에서 SessionDTO를 가져옴
SessionDTO sessionDTO = (SessionDTO) session.getAttribute("SessionDTO");
Integer userId = sessionDTO.getUserId();
if (sessionDTO != null) {
	// SessionDTO의 내용을 표준 출력(서버 콘솔)에 출력
	System.out.println("MainPage Username: " + sessionDTO.getUserProfileDTO().getUsername());
	System.out.println("MainPage Email: " + sessionDTO.getUserProfileDTO().getEmail());
	System.out.println("MainPage Description: " + sessionDTO.getUserProfileDTO().getDescription());
	System.out.println("MainPage MBTI: " + sessionDTO.getUserProfileDTO().getMbti());
	System.out.println("MainPage Hobby: " + sessionDTO.getUserProfileDTO().getHobbies());
	System.out.println("MainPage Age: " + sessionDTO.getUserProfileDTO().getAge());
	System.out.println("MainPage Income: " + sessionDTO.getUserProfileDTO().getIncome());
	System.out.println("MainPage Image URL: " + sessionDTO.getUserProfileDTO().getUserImgUrl());
} else {
	System.out.println("SessionDTO is null");
}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Main Page</title>
<style>
        @font-face {
            font-family: 'SBAggroB';
            src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/SBAggroB.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }

        @font-face {
            font-family: 'GmarketSansMedium';
            src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }

        body {
            font-family: 'GmarketSansMedium', Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #F0F2F5;
            margin: 0;
        }

        .container {
            display: flex;
            width: 80%;
            max-width: 1200px;
            background: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }

        .main-content {
            flex: 3;
            padding: 20px;
        }

        .profile-info {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }

        .profile-info img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            object-fit: cover;
            margin-right: 15px;
        }

        .profile-info .details {
            display: flex;
            flex-direction: column;
        }

        .profile-info .details h1 {
            margin: 0;
            font-size: 24px;
            color: #333;
            font-family: 'SBAggroB', GmarketSansMedium, Arial, sans-serif;
        }

        .profile-info .details p {
            margin: 5px 0;
            color: #777;
            font-family: 'GmarketSansMedium', Arial, sans-serif;
        }

        .bio {
            margin-bottom: 20px;
        }

        .bio p {
            font-size: 14px;
            color: #555;
            font-family: 'GmarketSansMedium', Arial, sans-serif;
        }

        .bio .hashtags {
            color: #007BFF;
            font-family: 'GmarketSansMedium', Arial, sans-serif;
        }

        .settings {
            margin-top: 20px;
            padding: 10px;
            border-top: 1px solid #ddd;
            font-family: 'GmarketSansMedium', Arial, sans-serif;
        }

        .settings a {
            display: block;
            margin: 10px 0;
            color: #007BFF;
            text-decoration: none;
        }

        .settings a:hover {
            text-decoration: underline;
        }

        .right-sidebar {
            flex: 1;
            padding: 20px;
            border-left: 1px solid #ddd;
            background: #FAFAFA;
            font-family: 'GmarketSansMedium', Arial, sans-serif;
        }

        .suggestions {
            margin-bottom: 20px;
        }

        .suggestions h2 {
            font-size: 18px;
            margin-bottom: 10px;
            color: #333;
            font-family: 'GmarketSansMedium', Arial, sans-serif;
        }

        .suggestions .user {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }

        .suggestions .user img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            object-fit: cover;
            margin-right: 10px;
        }

        .suggestions .user .name {
            font-size: 16px;
            color: #333;
            font-family: 'GmarketSansMedium', Arial, sans-serif;
        }

        .subscription-info {
            display: none;
            margin-top: 10px;
            background: #F8F9FA;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        
        .friend-list {
            display: none;
            margin-top: 10px;
            background: #F8F9FA;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .subscription-info h3 {
            margin: 0 0 10px;
            color: #333;
            font-family: 'SBAggroB', GmarketSansMedium, Arial, sans-serif;
        }

        .subscription-info p {
            margin: 5px 0;
            color: #333;
        }

        .subscription-button {
            color: #000000; /* 글자 색상 */
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            margin: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s;
            font-family: 'GmarketSansMedium', Arial, sans-serif;
            text-align: center;
        }

        .subscription-button:hover {
            transform: scale(1.05); /* 호버 시 버튼 확대 효과 */
        }

        .subscription-button:focus {
            outline: none; /* 포커스 시 기본 테두리 제거 */
            box-shadow: 0 0 0 2px rgba(0, 123, 255, 0.5); /* 포커스 시 테두리 효과 */
        }

        .subscription-button.premium {
            background-color: #FFD700; /* 골드색 */
        }

        .subscription-button.premium:hover {
            background-color: #ffc107; /* 골드색 호버 */
        }

        .subscription-button.basic {
            background-color: #c3c7c7; /* 실버색 */
        }

        .subscription-button.basic:hover {
            background-color: #a9a9a9; /* 실버색 호버 */
        }
        
        .transparent-button {
    background-color: transparent; /* 배경 투명 */
    border: none; /* 테두리 제거 */
    box-shadow: none; /* 그림자 제거 */
    color: inherit; /* 텍스트 색상 상속 */
    padding: 0; /* 여백 제거 */
    font-size: inherit; /* 글자 크기 상속 */
    cursor: default; /* 기본 커서 (클릭 가능한 손가락 커서 대신) */
    pointer-events: none; /* 클릭 및 포커스 이벤트 제거 */
}

.transparent-button:hover,
.transparent-button:focus {
    background-color: transparent; /* 호버 시에도 배경 투명 유지 */
    border: none; /* 테두리 제거 */
    box-shadow: none; /* 그림자 제거 */
    color: inherit; /* 텍스트 색상 유지 */
}

.friend-list {
        display: none;
        margin-bottom: 20px;
    }

    .friend-list .user {
        display: flex;
        align-items: center;
        margin-bottom: 15px;
    }

    .friend-list .user img {
        width: 50px;
        height: 50px;
        border-radius: 50%;
        object-fit: cover;
        margin-right: 10px;
    }

    .friend-list .user .name {
        font-size: 16px;
        color: #333;
        font-family: 'GmarketSansMedium', Arial, sans-serif;
    }


    </style>
</head>
<body>
	<div class="container">
		<div class="main-content">
			<!-- User Profile Section -->
			<div class="profile-info">
				<!-- <img src="https://via.placeholder.com/100" alt="Profile Picture"> -->
				<img src="${pageContext.request.contextPath}${userImgUrl}"
					alt="Profile Picture" id="profile-picture">
				<div class="details">
					<h1>${username}님 환영합니다!</h1>
					<p>${email}</p>
				</div>
			</div>
			<!-- Bio Section -->
			<div class="bio">
				<p>${description}</p>
				<p class="hashtags">#${mbti} #${hobbies} #${age}</p>
			</div>
			<!-- Settings Section -->
			<div class="settings">
				<a href="${pageContext.request.contextPath}/profileEdit">프로필 설정</a>
				<a href="#" id="toggleFriendList">우소좀여(매칭)</a>
                <!-- 친구 리스트 영역 -->
                <div class="friend-list" id="friendList">
                    <div class="user">
                        <img src="uploads/p1.jfif" alt="User Picture">
                        <div class="name">주선자1</div>
                    </div>
                    <div class="user">
                        <img src="uploads/p2.jfif" alt="User Picture">
                        <div class="name">주선자2</div>
                    </div>
                    <div class="user">
                        <img src="uploads/p3.jfif" alt="User Picture">
                        <div class="name">주선자3</div>
                    </div>
                </div>
				<a
					href="#" id="toggleSubscription" onclick="isSubValidate()">구독
					확인</a>
				<div class="subscription-info" id="subscriptionInfo">
					<div id="subscription-options">
						<h3>아직 구독제가 선택되지 않았어요 :(</h3>
						<button class="subscription-button basic" onclick="subscribe('BASIC')">BASIC</button>
						<button class="subscription-button premium" onclick="subscribe('PREMIUM')">PREMIUM</button>
					</div>

					<!-- 현재 구독 상태 영역 -->
					<div id="current-subscription">
						<h3>현재 구독 상태</h3>
						<button class="subscription-button transparent-button" id="subscription-name" ></button><br>
						<button class="subscription-button transparent-button" id="subscription-renewal"></button><br>
						<button class="subscription-button" onclick="unsubscribe()">구독 철회</button>
					</div>
				</div>
				<a href="#">매칭 확인</a> <a
					href="${pageContext.request.contextPath}/reviews">리뷰 관리</a>
			</div>
		</div>
		<div class="right-sidebar">
			<!-- Suggested Users Section -->
			<div class="suggestions">
				<h2>알 수도 있는 주선자들</h2>
				<div class="user">
					<img src="https://via.placeholder.com/50" alt="User Picture">
					<div class="name">신혜원</div>
				</div>
				<div class="user">
					<img src="https://via.placeholder.com/50" alt="User Picture">
					<div class="name">이승언</div>
				</div>
				<div class="user">
					<img src="https://via.placeholder.com/50" alt="User Picture">
					<div class="name">손대현</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	document.getElementById("toggleSubscription").addEventListener(
		    "click",
		    function() {
		        var subscriptionInfo = document.getElementById("subscriptionInfo");
		        if (subscriptionInfo.style.display === "none" || subscriptionInfo.style.display === "") {
		            subscriptionInfo.style.display = "block";
		        } else {
		            subscriptionInfo.style.display = "none";
		        }
		    }
		);

	</script>
	<script>
        document.getElementById("toggleFriendList").addEventListener("click", function() {
            var friendList = document.getElementById("friendList");
            if (friendList.style.display === "none" || friendList.style.display === "") {
                friendList.style.display = "block";
            } else {
                friendList.style.display = "none";
            }
        });
    </script>
	<script src="https://cdn.iamport.kr/v1/iamport.js" defer></script>
	<script src="https://unpkg.com/axios@1.7.2/dist/axios.min.js" defer></script>
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
	            document.getElementById('subscription-name').innerText = "구독제: " + subscriptionName;
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
	        	amount = 9900
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

	                        alert('구독 성공!');
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
	                    alert('구독이 철회되었습니다 :( ');
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