<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
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
            background-color: #f5f5f5;
        }
        .login-container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 350px;
        }
        .logo img {
            width: 50%;
            margin-top : 100px;
        }
        .login-container h2 {
            font-size: 24px;
            margin-bottom: 80px;
            color: #333;
            font-family: 'SBAggroB', GmarketSansMedium;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 93%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-family: 'GmarketSansMedium';
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
            font-family: 'GmarketSansMedium';
        }
        .login-container button:hover {
            background-color: #0056b3;
        }
        .login-container .checkbox-container {
            text-align: left;
            margin-top: 18px;
            margin-bottom: 18px;
            font-size : 12px;
            font-family: GmarketSansMedium;
        }
        .login-container .checkbox-container input {
            margin-right: 5px;
        }
        .login-container .links {
            margin-top: 20px;
            font-size: 14px;
            color: #007bff;
        }
        .login-container .links a {
            margin: 0 10px;
            color: #007bff;
            text-decoration: none;
        }
        .login-container .links a:hover {
            text-decoration: underline;
        }
        .label select {
        font-family: 'GmarketSansMedium';
    	}
        .divider {
            margin: 20px 0;
            border-bottom: 1px solid #ccc;
            line-height: 0.1em;
        }
        .divider span {
            background: white;
            padding: 0 10px;
            color: #999;
        }
        .oauth-buttons {
            margin-top: 20px;
        }
        .oauth-buttons button {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: none;
            font-size: 16px;
            cursor: pointer;
            margin-bottom: 10px;
            font-family: 'GmarketSansMedium';
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
        <div class="logo">
               <img src="${pageContext.request.contextPath}/images/image-icon3.png" alt="Wooso Logo">
            </a>
        </div>
        <h2>>WOOSO </h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <input type="text" id="email" name="email" placeholder="이메일" required>
            <input type="password" id="password" name="password" placeholder="비밀번호" required>
                        <!-- UserType 선택 -->
        <div class "select-user-type">
            <label for="userType">Select User Type:</label>
            <select id="userType" name="userType" required>
                <option value="USER">User</option>
                <option value="GUARANTOR">Guarantor</option>
            </select>
        </div>
            
            
            <div class="checkbox-container">
                <input type="checkbox" id="remember-me" name="remember-me">
                <label for="remember-me">로그인 상태유지</label>
            </div>
            <button type="submit">로그인</button>
        </form>
        <div class="links">
            <a href="${pageContext.request.contextPath}/signup">회원가입</a>
            <a href="${pageContext.request.contextPath}/forgot-password">비밀번호 찾기</a>
        </div>
        <div class="divider"><span>또는</span></div>
        <div class="oauth-buttons">
            <button class="oauth-google">Google로 시작하기</button>
            <button class="oauth-naver">Naver로 시작하기</button>
        </div>
    </div>
</body>
</html>
