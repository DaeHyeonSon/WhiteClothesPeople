<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
            font-family: 'GmarketSansMedium', Arial, sans-serif; /* GmarketSansMedium 폰트 적용 */
            display: flex;
            justify-content: center;
            align-items: flex-start;
            height: 100vh;
            margin: 0;
            background-color: #f0f2f5;
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
            font-family: 'SBAggroB', GmarketSansMedium, Arial, sans-serif; /* SBAggroB 폰트 적용 */
        }
        .profile-info .details p {
            margin: 5px 0;
            color: #777;
            font-family: 'GmarketSansMedium', Arial, sans-serif; /* GmarketSansMedium 폰트 적용 */
        }
        .bio {
            margin-bottom: 20px;
        }
        .bio p {
            font-size: 14px;
            color: #555;
            font-family: 'GmarketSansMedium', Arial, sans-serif; /* GmarketSansMedium 폰트 적용 */
        }
        .bio .hashtags {
            color: #007bff;
            font-family: 'GmarketSansMedium', Arial, sans-serif; /* GmarketSansMedium 폰트 적용 */
        }
        .settings {
            margin-top: 20px;
            padding: 10px;
            border-top: 1px solid #ddd;
            font-family: 'GmarketSansMedium', Arial, sans-serif; /* GmarketSansMedium 폰트 적용 */
        }
        .settings a {
            display: block;
            margin: 10px 0;
            color: #007bff;
            text-decoration: none;
            font-family: 'GmarketSansMedium', Arial, sans-serif; /* GmarketSansMedium 폰트 적용 */
        }
        .settings a:hover {
            text-decoration: underline;
        }
        .right-sidebar {
            flex: 1;
            padding: 20px;
            border-left: 1px solid #ddd;
            background: #fafafa;
            font-family: 'GmarketSansMedium', Arial, sans-serif; /* GmarketSansMedium 폰트 적용 */
        }
        .suggestions {
            margin-bottom: 20px;
        }
        .suggestions h2 {
            font-size: 18px;
            margin-bottom: 10px;
            color: #333;
            font-family: 'GmarketSansMedium', Arial, sans-serif; /* GmarketSansMedium 폰트 적용 */
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
            font-family: 'GmarketSansMedium', Arial, sans-serif; /* GmarketSansMedium 폰트 적용 */
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="main-content">
            <!-- User Profile Section -->
            <div class="profile-info">
                <img src="https://via.placeholder.com/100" alt="Profile Picture">
                <div class="details">
                    <h1>${username}님 환영합니다!</h1>
                    <p>${email}</p>
                </div>
            </div>
            <!-- Bio Section -->
            <div class="bio">
                <p>${description}</p>
                <p class="hashtags">${mbti} ${hobby} ${age}</p>
            </div>
            <!-- Settings Section -->
            <div class="settings">
                <a href="#">프로필 설정</a>
                <a href="#">우소좀여</a>
                <a href="#">구독 확인</a>
                <a href="#">매칭 확인</a>
            </div>
        </div>
        <div class="right-sidebar">
            <!-- Suggested Users Section -->
            <div class="suggestions">
                <h2>알 수 있는 주선자들</h2>
                <div class="user">
                    <img src="https://via.placeholder.com/50" alt="User Picture">
                    <div class="name">김철수</div>
                </div>
                <div class="user">
                    <img src="https://via.placeholder.com/50" alt="User Picture">
                    <div class="name">이영희</div>
                </div>
                <div class="user">
                    <img src="https://via.placeholder.com/50" alt="User Picture">
                    <div class="name">박지민</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
