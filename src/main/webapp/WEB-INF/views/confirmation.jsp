<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Payment Failed</title>
</head>
<body>
<h1>Payment Failed</h1>
<p>Sorry, the payment could not be processed.</p>

<!-- Display details about the failed payment -->
<p><strong>Payment ID:</strong> ${impUid}</p>
<p><strong>Order Number:</strong> ${merchantUid}</p>

<!-- Optionally, add a link to return to the main page or retry -->
<a href="/payment">Return to Payment Page</a>
</body>
</html>