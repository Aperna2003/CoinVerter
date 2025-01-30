<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/gif" href="img/logo.png">
<meta charset="utf-8">
<meta name="viewport" content="initial-scale = 1, width = device-width">
<link id="mystylesheet" rel="stylesheet" type="text/css"
	href="CSS/light.css">

<title>CoinVerter Login</title>
</head>
<body class="bgPage">
	<div class="login">
		<div class="loginForm">
			<h2>Accedi al tuo account personale</h2>
			<form action="Login" method="POST">
				<input type="text" name="email" placeholder="email"	required="required">
				<input type="password" name="pwd" placeholder="Password" required="required">
				<div class="centerBtn">
					<button type="button"><a href="index.jsp">Annulla</a></button>
					<button type="submit">Accedi</button>
				</div>
			</form>
			<p id="reg">
				Non sei registrato? <a href="register.jsp">Clicca qui!</a>
			</p>
		</div>
	</div>
</body>
</html>