<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>프로필 설정</title>
    <style>
        body {
            font-family: Arial, sans-serif;
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
            border: 2px solid #ddd; /* 이미지 테두리 */
            cursor: pointer; /* 클릭할 수 있음을 표시 */
        }
        .profile-image-container input[type="file"] {
            display: none; /* 파일 선택 창 숨기기 */
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
            background-color: transparent; /* 투명하게 설정 */
            text-align: left; /* 왼쪽 정렬 */
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
        <h2>프로필 설정</h2>
        <!-- Profile Image Section -->
        <div class="profile-image-container">
            <img src="https://via.placeholder.com/120" alt="Profile Picture" id="profile-picture">
            <input type="file" id="profile-image" name="profile-image" accept="image/*">
        </div>
        <!-- Name Section -->
        <div class="form-group">
            <label for="name">이름</label>
            <p id="name">홍길동</p>
        </div>
        <!-- Age Section -->
        <div class="form-group">
            <label for="age">나이</label>
            <input type="text" id="age" name="age" placeholder="나이를 입력하세요">
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
            <input type="text" id="hobbies" name="hobbies" placeholder="취미를 입력하세요">
        </div>
        <!-- Address Section -->
        <div class="form-group">
            <label for="address">주소</label>
            <input type="text" id="address" name="address" placeholder="주소를 입력하세요">
        </div>
        <!-- Income Section -->
        <div class="form-group">
            <label for="income">수입</label>
            <select id="income" name="income">
                <option value="2000-2500">2,000만 원 ~ 2,500만 원</option>
                <option value="2500-3000">2,500만 원 ~ 3,000만 원</option>
                <option value="3000-3500">3,000만 원 ~ 3,500만 원</option>
                <option value="3500-4000">3,500만 원 ~ 4,000만 원</option>
                <option value="4000-4500">4,000만 원 ~ 4,500만 원</option>
                <option value="4500-5000">4,500만 원 ~ 5,000만 원</option>
            </select>
        </div>
        <!-- Description Section -->
        <div class="form-group">
            <label for="description">설명</label>
            <textarea id="description" name="description" placeholder="자기소개를 입력하세요"></textarea>
        </div>
        <!-- Submit Button -->
        <button type="submit" class="submit-button">수정하기</button>
    </div>
    <script>
        document.getElementById('profile-picture').addEventListener('click', function() {
            document.getElementById('profile-image').click();
        });
    </script>
</body>
</html>
