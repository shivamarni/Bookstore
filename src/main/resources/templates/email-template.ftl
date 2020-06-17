<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>order deatils</title>
</head>
<body>
	<div style="width:100%;height:100vh;background-color: #e6dfdf">
			<div style="width: 70%;height: 100vh;background-color: #f2f0f0;margin-left: 153px;">
				<div style="width: 85%;height: 70px;margin-left: 45px;">
					Hi <span style="font-weight: 600;margin-right: 231px;">${username}</span><br><br>
					<span>Your order has been successfully placed.</span>
					<br><br><br>
					<#list books as book>
					<div style="display: flex;">
						<div style="width:25%;flex: 0 0 65%;">
							<img src=${book.bookImage} style="width:100px;height:100px">
						</div>
						<div style="flex: 1;">
						Book Name: ${book.bookName}<br>
						Author Name: ${book.bookAuthor}<br>
						Quantity : ${book.quantity.cartQuantity}<br>
						Price : ${book.bookPrice}
						</div>
					</div>
					</#list>
					<div style="margin-left: 379px;">
					Delivary Charges: ${delivaryCharges}<br>
					Cart Price : ${cartPrice}<br>
					------------------------------------<br>
					Total : ${totalPrice}
					</div>
				</div>
		</div>	
</body>
</html>