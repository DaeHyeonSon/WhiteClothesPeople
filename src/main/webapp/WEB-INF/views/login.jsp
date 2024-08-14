<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f5f5f5;
        }
        .login-container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 300px;
        }
        .login-container h2 {
            margin-bottom: 20px;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        .login-container button {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: none;
            background-color: #007bff;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        .login-container button:hover {
            background-color: #0056b3;
        }
        .login-container .checkbox-container {
            text-align: left;
            margin-bottom: 20px;
        }
        .login-container .checkbox-container input {
            margin-right: 5px;
        }
        .login-container .links {
            margin-top: 20px;
        }
        .login-container .links a {
            margin: 0 10px;
            color: #007bff;
            text-decoration: none;
        }
        .login-container .links a:hover {
            text-decoration: underline;
        }
        .login-container .divider {
            margin: 20px 0;
            border-bottom: 1px solid #ccc;
            line-height: 0.1em;
        }
        .login-container .divider span {
            background: white;
            padding: 0 10px;
            color: #999;
        }
        .login-container .oauth-buttons {
            margin-top: 20px;
        }
        .login-container .oauth-buttons button {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: none;
            font-size: 16px;
            cursor: pointer;
            margin-bottom: 10px;
        }
        .login-container .oauth-google {
            background-color: #db4437;
            color: white;
        }
        .login-container .oauth-google:hover {
            background-color: #c23321;
        }
        .login-container .oauth-naver {
            background-color: #1ec800;
            color: white;
        }
        .login-container .oauth-naver:hover {
            background-color: #1ab600;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <input type="text" id="email" name="email" placeholder="이메일" required>
            <input type="password" id="password" name="password" placeholder="비밀번호" required>
            <div class="checkbox-container">
                <input type="checkbox" id="remember-me" name="remember-me">
                <label for="remember-me">로그인 상태유지</label>
            </div>
            <button type="submit">로그인</button>
        </form>
        <div class="links">
            <a href="${pageContext.request.contextPath}/signup">회원가입</a>
            <a href="${pageContext.request.contextPath}/forgot-password">아이디 · 비밀번호 찾기</a>
        </div>
        <div class="divider"><span>또는</span></div>
        <div class="oauth-buttons">
            <button class="oauth-google">Google로 시작하기</button>
            <button class="oauth-naver">Naver로 시작하기</button>
        </div>
    </div>
</body>
</html>