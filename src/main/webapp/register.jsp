<%--suppress LossyEncoding --%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/gif" href="img/logo.png">
<meta charset="utf-8">
<meta name="viewport" content="initial-scale = 1, width = device-width">
<link id="mystylesheet" rel="stylesheet" type="text/css" href="CSS/light.css">

<title>CoinVerter Register</title>
</head>
<body class="bgPage">
	<div class="login">
		<div class="loginForm">
			<h2>Crea un nuovo account</h2>
			<form action="Register" method="post">
				<input type="text" name="name" placeholder="Nome" required="required"> 
				<input type="text" name="surname" placeholder="Cognome" required="required"> 
				<input type="text" name="email" placeholder="Email" required="required">
				<input type="password" name="pwd" placeholder="Password" required="required">
				<div class="centerBtn">
					<button type="button"><a href="index.jsp">Annulla</a></button>
					<button type="submit">Registrati</button>
				</div>
			</form>
			<p id="reg"> Hai gi� un account? <a href="login.jsp">Accedi qui!</a></p>
		</div>
	</div>
</body>
</html>
