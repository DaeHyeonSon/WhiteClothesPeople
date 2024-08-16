<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page - Guarantor</title>
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
            color: #007bff;
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
            color: #007bff;
            text-decoration: none;
            font-family: 'GmarketSansMedium', Arial, sans-serif;
            cursor: pointer;
        }
        .settings a:hover {
            text-decoration: underline;
        }
        .right-sidebar {
            flex: 1;
            padding: 20px;
            border-left: 1px solid #ddd;
            background: #fafafa;
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

        .toggle-content {
            display: none; /* Hidden by default */
            margin-top: 10px;
            background: #F8F9FA;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .status-section {
            margin-top: 10px;
        }
        .status-section .status {
            margin-bottom: 15px;
        }
        .status-section h3 {
            font-size: 16px;
            color: #333;
            font-family: 'GmarketSansMedium', Arial, sans-serif;
            margin-bottom: 10px;
        }
        .status-section .entry {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
            padding: 10px;
            background: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .status-section .entry img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            object-fit: cover;
            margin-right: 10px;
        }
        .status-section .entry .details {
            font-family: 'GmarketSansMedium', Arial, sans-serif;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="main-content">
            <!-- User Profile Section -->
            <div class="profile-info">
                <img src="uploads/animal.jpg" alt="Profile Picture">
                <div class="details">
                    <h1>${guarantorName}님 환영합니다!</h1>
                    <p>${guarantorEmail}</p>
                    <p>${guarantorLocation}</p>
                </div>
            </div>
            <!-- Bio Section -->
            <div class="bio">
                <p>소개시켜드립니다 :)</p>
                <p class="hashtags">#INTP #배구 #27</p>
            </div>
            <!-- Settings Section -->
            <div class="settings">
            	<a href="#">프로필 수정</a>
                <a href="#" id="toggleLink1">우소료 확인 😉</a>
                <div id="toggleContent1" class="toggle-content">
                    <p>2024/08 성사 시킨 매칭 수: 5</p>
                    <p>총 수익금: 6,000원</p>
                </div>

                <a href="#" id="toggleLink2">우소리스트</a>
                <div id="toggleContent2" class="toggle-content status-section">
                    <div class="status">
                        <h3>진행 중</h3>
                        <div class="entry">
                            <img src="https://via.placeholder.com/50" alt="User Picture">
                            <div class="details">
                                <p>홍민철</p>
                                <p>매치 일자: 2024-08-15</p>
                            </div>
                        </div>
                        <div class="entry">
                            <img src="https://via.placeholder.com/50" alt="User Picture">
                            <div class="details">
                                <p>김민서</p>
                                <p>매치 일자: 2024-08-13</p>
                            </div>
                        </div>
                    </div>
                    <div class="status">
                        <h3>승인됨</h3>
                        <div class="entry">
                            <img src="https://via.placeholder.com/50" alt="User Picture">
                            <div class="details">
                                <p>이서영</p>
                                <p>매치 일자: 2024-08-01</p>
                            </div>
                        </div>
                    </div>
                    <div class="status">
                        <h3>거절됨</h3>
                        <div class="entry">
                            <img src="https://via.placeholder.com/50" alt="User Picture">
                            <div class="details">
                                <p>김민정</p>
                                <p>매치 일자: 2024-08-03</p>
                            </div>
                        </div>
                    </div>
                </div>

                <a href="#">매칭 확인</a>
            </div>
        </div>
        <div class="right-sidebar">
            <!-- Suggested Users Section -->
            <div class="suggestions">
                <h2>알 수도 있는 주선자들</h2>
                <div class="user">
                    <img src="https://via.placeholder.com/50" alt="User Picture">
                    <div class="name">손대현</div>
                </div>
                <div class="user">
                    <img src="https://via.placeholder.com/50" alt="User Picture">
                    <div class="name">이승언</div>
                </div>
                <div class="user">
                    <img src="https://via.placeholder.com/50" alt="User Picture">
                    <div class="name">신혜원</div>
                </div>
            </div>
        </div>
    </div>

    <script>
        function toggleContent(id) {
            var content = document.getElementById(id);
            if (content.style.display === "none" || content.style.display === "") {
                content.style.display = "block";
            } else {
                content.style.display = "none";
            }
        }

        document.getElementById("toggleLink1").addEventListener("click", function(event) {
            event.preventDefault(); // Prevent the default anchor click behavior
            toggleContent("toggleContent1");
        });

        document.getElementById("toggleLink2").addEventListener("click", function(event) {
            event.preventDefault(); // Prevent the default anchor click behavior
            toggleContent("toggleContent2");
        });
    </script>
</body>
</html>
