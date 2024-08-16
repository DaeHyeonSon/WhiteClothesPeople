<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Payment</title>
</head>
<body>
<p>${welcomeMessage}</p>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
    async function requestPay() {
        try {
            // Fetch user details
            const userData = await axios.get('http://localhost/payment/details');
            const user = userData.data;

            // Log the user data
            console.log(user);

            // Generate a unique merchant_uid
            const now = new Date();
            const dateString = now.toISOString().replace(/[-:T]/g, '').split('.')[0]; // YYYYMMDDHHMMSS
            const merchant_uid = `${user.name}${dateString}`;

            // Initialize the payment gateway
            IMP.init(`${impCode}`);
            IMP.request_pay(
                {
                    pg: "nice_v2",        
                    pay_method: "card",   
                    merchant_uid: merchant_uid, 
                    name: user.planName,     
                    amount: user.plan,           
                    buyer_email: user.email,
                    buyer_name: user.name,
                    buyer_tel: user.phone,
                    buyer_addr: user.address,
                    buyer_postcode: user.postcode
                },
                function (res) {
                    console.log(res);
                	 if (!res.error_code && !res.error_msg) {
                        console.log("Payment Success");
                        console.log(res.imp_uid);

                        // Send success data to server
                        axios.post('/payment/success', {
                            params: {
                                imp_uid: res.imp_uid,
                                merchant_uid: res.merchant_uid,
                            }
                        })
                            .then(function (response) {
                                document.open();
                                document.write(response.data);
                                document.close();
                            })
                            .catch(function (error) {
                                console.log("Error during payment success callback:", error);
                            });
                    } else {
                        // Send fail data to server and redirect to fail page
                        axios.get('/payment/fail', {
                            params: {
                                imp_uid: res.imp_uid,
                                merchant_uid: res.merchant_uid
                            }
                        })
                            .then(function (response) {
                                document.open();
                                document.write(response.data);
                                document.close();
                            })
                            .catch(function (error) {
                                console.log("Error during payment fail callback:", error);
                            });
                    }
                }
            );
        } catch (error) {
            console.log("Error Fetching User Data: ", error);
        }
    }
    async function refundPay(){
        axios.get("http://localhost/payment/refund");
    }
</script>
<button onclick="requestPay()">결제하기</button>
<button onclick="refundPay()">환불하기</button>
<!-- 결제하기 버튼 생성 -->
</body>
</html>