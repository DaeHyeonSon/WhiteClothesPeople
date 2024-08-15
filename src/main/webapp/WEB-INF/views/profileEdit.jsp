<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>프로필 설정</title>
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
            font-family: GmarketSansMedium;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f0f2f5;
        }
        .profile-settings-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 500px;
        }
        .profile-settings-container h2 {
            text-align: center;
            font-family: 'SBAggroB', Arial, sans-serif;
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }
        .profile-image-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-bottom: 20px;
            position: relative;
        }
        .profile-image-container img {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            object-fit: cover;
            border: 2px solid #ddd; 
            cursor: pointer; 
        }
        .profile-image-container input[type="file"] {
            display: none; 
            font-family: 'GmarketSansMedium', Arial, sans-serif;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
        .form-group p,
        .form-group input[type="text"],
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        .form-group p {
            display: block;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: transparent; /* í¬ëªíê² ì¤ì  */
            text-align: left; /* ì¼ìª½ ì ë ¬ */
        }
        .form-group textarea {
            height: 100px;
            resize: vertical;
        }
        .submit-button {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: none;
            background-color: #007bff;
            color: white;
            font-size: 16px;
            cursor: pointer;
            margin-top: 20px;
        }
        .submit-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="profile-settings-container">
        <h2>프로필 설정 </h2>
        <form action="${pageContext.request.contextPath}/updateProfile" method="post" enctype="multipart/form-data">
        
        <!-- Profile Image Section -->
        <div class="profile-image-container">
            <img src="https://via.placeholder.com/120" alt="Profile Picture" id="profile-picture">
            <label for "profileImage">프로필 이미지</label>
            <input type="file" id="profile-image" name="profile-image" accept="image/*">
        </div>
        <!-- Name Section -->
        <div class="form-group">
            <label for="name">이름</label>
            <input type="text" id="username" name="username" required>
        </div>
        <!-- Age Section -->
        <div class="form-group">
            <label for="age">나이</label>
            <input type="text" id="age" name="age" placeholder="나이">
        </div>
        <!-- MBTI Section -->
        <div class="form-group">
            <label for="mbti">MBTI</label>
            <select id="mbti" name="mbti">
                <option value="ISTJ">ISTJ</option>
                <option value="ISFJ">ISFJ</option>
                <option value="INFJ">INFJ</option>
                <option value="INTJ">INTJ</option>
                <option value="ISTP">ISTP</option>
                <option value="ISFP">ISFP</option>
                <option value="INFP">INFP</option>
                <option value="INTP">INTP</option>
                <option value="ESTP">ESTP</option>
                <option value="ESFP">ESFP</option>
                <option value="ENFP">ENFP</option>
                <option value="ENTP">ENTP</option>
                <option value="ESTJ">ESTJ</option>
                <option value="ESFJ">ESFJ</option>
                <option value="ENFJ">ENFJ</option>
                <option value="ENTJ">ENTJ</option>
            </select>
        </div>
        <!-- Hobbies Section -->
        <div class="form-group">
            <label for="hobbies">취미</label>
            <input type="text" id="hobbies" name="hobbies" placeholder="취미">
        </div>
        <!-- Address Section -->
        <div class="form-group">
            <label for="address">주소</label>
            <input type="text" id="address" name="address" placeholder="주소">
        </div>
        <!-- Income Section -->
        <div class="form-group">
            <label for="income">수입(연봉)</label>
            <select id="income" name="income">
                <option value="2000-3000">2,000 만원 ~ 3,000 만원</option>
                <option value="3000-4000">3,000 만원 ~ 4,000 만원</option>
                <option value="4000-5000">4,000 만원 ~ 5,000 만원</option>
                <option value="5000-6000">5,000 만원 ~ 6,000 만원</option>
                <option value="6000-7000">6,000 만원 ~ 7,000 만원</option>
                <option value="7000-8000">7,000 만원 ~ 8,000 만원</option>
                <option value="8000-9000">8,000 만원 ~ 9,000 만원</option>
                <option value="9000-10000">9,000 만원 ~ 1억 원</option>
                <option value="10000-">1억 원 이상 ~ </option>                
            </select>
        </div>
        <!-- Description Section -->
        <div class="form-group">
            <label for="description">자신을 보여줄 수 있는 한문장</label>
            <textarea id="description" name="description" placeholder="자기소개"></textarea>
        </div>
        <!-- Submit Button -->
        <button type="submit" class="submit-button">프로필 업데이트</button>
    </div>
    <script>
        document.getElementById('profile-picture').addEventListener('click', function() {
            document.getElementById('profile-image').click();
        });
    </script>
</body>
</html>
