<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
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
        }
        .profile-info .details p {
            margin: 5px 0;
            color: #777;
        }
        .bio {
            margin-bottom: 20px;
        }
        .bio p {
            font-size: 14px;
            color: #555;
        }
        .bio .hashtags {
            color: #007bff;
        }
        .settings {
            margin-top: 20px;
            padding: 10px;
            border-top: 1px solid #ddd;
        }
        .settings a {
            display: block;
            margin: 10px 0;
            color: #007bff;
            text-decoration: none;
        }
        .settings a:hover {
            text-decoration: underline;
        }
        .right-sidebar {
            flex: 1;
            padding: 20px;
            border-left: 1px solid #ddd;
            background: #fafafa;
        }
        .suggestions {
            margin-bottom: 20px;
        }
        .suggestions h2 {
            font-size: 18px;
            margin-bottom: 10px;
            color: #333;
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
                    <h1>홍길동</h1>
                    <p>@honggildong</p>
                    <p>서울, 대한민국</p>
                </div>
            </div>
            <!-- Bio Section -->
            <div class="bio">
                <p>안녕하세요! 이곳은 제 인스타그램 스타일 메인 페이지입니다. 나만의 멋진 사진과 이야기를 공유하세요!</p>
                <p class="hashtags">#여행 #음악 #맛집</p>
            </div>
            <!-- Settings Section -->
            <div class="settings">
                <a href="#">프로필 설정</a>
                <a href="#">우소료 확인 😉</a>
                <a href="#">우소리스트</a>
                <a href="#">매칭 확인</a>
            </div>
        </div>
        <div class="right-sidebar">
            <!-- Suggested Users Section -->
            <div class="suggestions">
                <h2>알 수 있는 사람들</h2>
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
