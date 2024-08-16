<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>리뷰목록</title>
</head>
<body>
<h2>모든 리뷰 보기</h2>
<c:if test="${not empty reviews}">
    <table border="1">
        <thead>
        <tr>
            <th>리뷰 대상자</th>
            <th>선택 항목</th>
            <th>주관식 의견</th>
            <th>작성일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="review" items="${reviews}">
            <tr>
                <td>${review.reviewee.name}</td>
                <td>${review.selectedOption}</td>
                <td>${review.comment}</td>
                <td>${review.reviewDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${empty reviews}">
    <p>리뷰가 없습니다.</p>
</c:if>
</body>
</html>
