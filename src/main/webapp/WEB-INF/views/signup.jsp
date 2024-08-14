<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f4f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .signup-container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        .signup-container h2 {
            margin-bottom: 30px;
            color: #000;
            font-size: 24px;
            text-align: center;
        }
        .signup-container label {
            font-size: 14px;
            color: #333;
            margin-bottom: 5px;
            display: block;
        }
        .signup-container input[type="text"],
        .signup-container input[type="password"],
        .signup-container input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 14px;
        }
        .signup-container .radio-group {
            margin-bottom: 15px;
        }
        .signup-container .radio-group label {
            margin-right: 15px; /* 라디오 버튼 간 간격 설정 */
            display: inline-block; /* 한 줄에 배치 */
        }
        .signup-container .checkbox-group {
            margin-bottom: 20px;
            font-size: 12px;
        }
        .signup-container .checkbox-group input {
            margin-right: 5px;
        }
        .signup-container button {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 2px solid #007bff;
            background-color: white;
            color: #007bff;
            font-size: 16px;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        .signup-container button:hover {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
    <div class="signup-container">
        <h2>회원가입</h2>
        <form action="${pageContext.request.contextPath}/signup" method="post">
        	
        	<div class="radio-group">
                <label>* 사용자 유형</label>
                <label><input type="radio" name="userType" value="guarantor" required> 주선자</label>
                <label><input type="radio" name="userType" value="user" required> 일반 사용자</label>
            </div>
        	
            <label for="email">* 이메일</label>
            <input type="email" id="email" name="email" placeholder="이메일" required>

            <label for="username">* 이름</label>
            <input type="text" id="username" name="username" placeholder="이름" required>

            <label for="password">* 비밀번호</label>
            <input type="password" id="password" name="password" placeholder="비밀번호" required>

            <label for="confirm-password">* 비밀번호 확인</label>
            <input type="password" id="confirm-password" name="confirm-password" placeholder="비밀번호 확인" required>

            <label for="phone-number">* 전화번호</label>
            <input type="text" id="phone-number" name="phone-number" placeholder="전화번호('-'없이 입력해주세요.)" required>

            <div class="radio-group">
                <label>* 성별</label>
                <label><input type="radio" name="gender" value="Male" required> 남성</label>
                <label><input type="radio" name="gender" value="Female" required> 여성</label>
            </div>

            <div class="checkbox-group">
                <label><input type="checkbox" name="agree" required> 이용약관과 개인정보 수집 및 정보이용에 동의합니다.</label>
            </div>

            <button type="submit">가입하기</button>
        </form>
    </div>
</body>
</html>
