<!DOCTYPE html>
<html>
<head>
	<link rel="shortcut icon" type="image/gif" href="<%=request.getServletContext().getContextPath()%>/img/logo.png">
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale = 1, width = device-width">
	<link id="mystylesheet" rel="stylesheet" type="text/css" href="<%=request.getServletContext().getContextPath()%>/CSS/light.css">

	<title>CoinVerter Login</title>
<script>
    // Funzione per formattare il numero della carta
    function formatCardNumber(input) {
      let value = input.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
      let formattedValue = value.match(/.{1,4}/g)?.join(' ') || value;
      input.value = formattedValue;
    }
  </script>
</head>
<body class="bgPage">
	<div class="login">
		<div class="loginForm">
			<h2>Utilizza metodo di pagamento</h2>
			<form action="Checkout" method="POST">
				<input type="text" name="name" placeholder="Nome sulla carta" required="required"> 
				<input type="text" name="CardNumber" maxlength="19" placeholder="Numero Carta" required="required" pattern="\d{4}(\s\d{4}){2,4}" oninput="formatCardNumber(this)">
				<input type="text" id="ExDate" name="ExDate" maxlength="5" placeholder="MM/YY" required="required" pattern="(0[1-9]|1[0-2])\/\d{2}">
				<input type="text" name="CVC" maxlength="3" placeholder="CVC" required="required">
				<div class="centerBtn">
					<button type="button"><a href="cart.jsp">Annulla</a></button>
					<button type="submit">Procedi al Pagamento</button>
				</div>
			</form>
			<script>
        		const exDateInput = document.getElementById('ExDate');
        		
        		exDateInput.addEventListener('input', function (e) {
            		let value = e.target.value;
            		value = value.replace(/[^\d]/g, '');
            			if(value.length > 2){
                			value = value.slice(0, 2) + '/' + value.slice(2);
            			}
            		e.target.value = value.slice(0, 5);
        		});
    		</script>
		</div>
	</div>
</body>
</html>