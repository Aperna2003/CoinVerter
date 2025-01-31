<!DOCTYPE html>
<html lang="it" dir="ltr">
<head>
	<script type="text/javascript" src="JS/scripts.js"></script>
	<link rel="shortcut icon" type="image/gif" href="img/logo.png">
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale = 1, width = device-width">
	<link id="mystylesheet" rel="stylesheet" type="text/css" href="CSS/light.css">

	<title>CoinVerter</title>
</head>

<body onresize="switchSuite()" onload="switchSuite()">

	<jsp:include page='header.jsp'>
		<jsp:param name="PageTitle" value="index"/>
	</jsp:include>

	<main class="bgPage">
		<div class="bg">
			<div class="flexContainer">
				<a href="Shop?filter=valuta&action=categoria">
					<div class="flexbox">
						<img class="flexImg" alt="" src="img/icon/iconCurrency.png">
						<h5>VALUTE</h5>
					</div>
				</a> <a href="Shop?filter=crypto&action=categoria">
					<div class="flexbox">
						<img class="flexImg" alt="" src="img/icon/iconCrypto.png">
						<h5>CRYPTOVALUTE</h5>
					</div>
				</a> <a href="Shop?filter=ricarica&action=categoria">
					<div class="flexbox">
						<img class="flexImg" alt="" src="img/icon/iconGiftCard.png">
						<h5>RICARICHE</h5>
					</div>
				</a> <a href="Shop?filter=moneta&action=categoria">
					<div class="flexbox">
						<img class="flexImg" alt="" src="img/icon/iconVbucks.png">
						<h5>MONETE DI GIOCO</h5>
					</div>
				</a>
			</div>
		</div>
	</main>

	<div class="toTop">
		<a href="#" class="topBtn">
			<img src="img/icon/up.png" alt="">
		</a>
	</div>

	<jsp:include page="footer.jsp" />

</body>
</html>