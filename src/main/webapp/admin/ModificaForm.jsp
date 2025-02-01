<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="prodotti.ProductBean,java.text.DecimalFormat"%>

<%	HttpSession sessione = request.getSession();	
	ProductBean p = (ProductBean)sessione.getAttribute("mprod");
	DecimalFormat df = new DecimalFormat("#.##");
%>


<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/gif" href="<%=request.getServletContext().getContextPath()%>/img/logo.png">
<meta charset="utf-8">
<meta name="viewport" content="initial-scale = 1, width = device-width">
<link id="mystylesheet" rel="stylesheet" type="text/css" href="<%=request.getServletContext().getContextPath()%>/CSS/light.css">
<title>CoinVerter</title>

</head>
<body class="bgPage">
	<div class="login">
		<div class="loginForm">
			<h2>Modifica Prodotto</h2>
			<form action="../Gestione?activity=modify&id=<%=p.getCode()%>" method="POST" enctype="multipart/form-data">
				<input type="text" name="nome" placeholder="<%=p.getName()%>" >
				<input type="number" name="valore" placeholder="<%=df.format(p.getValue())%>"  step="1" min="0">
				<input type="number" name="prezzo" placeholder="<%=df.format(p.getPrice())%>"  step="0.01" min="0">
				<select name="tipo">
					<option value="" disabled selected hidden><%=p.getType()%></option>
					<option value="valuta">Valuta</option>
					<option value="crypto">Cryptovaluta</option>
					<option value="ricarica">Ricarica carta</option>
					<option value="moneta">Valuta di gioco</option>
				</select>
				<!-- <input type="file" name="foto" accept=".png, .jpg, .jpeg" > -->
				
				<div class="fileDrop">
  					<span class="fileMsg"><%=p.getFoto()%></span>
  					<input class="fileInput" type="file" name="foto" accept=".png, .jpg, .jpeg">
				</div>
				
				<div class="centerBtn">
					<button type="button"><a href="../ProdottiAD">Annulla</a></button>
					<button type="submit">Conferma</button>
				</div>
			</form>
			
			<script>
  				function updateSelectColor(selectElement) {
    				if (selectElement.value) {
      					selectElement.classList.add("valid");
    				} else {
      					selectElement.classList.remove("valid"); 
    				}
  				}

  				document.querySelectorAll("select").forEach(select => {
    				select.addEventListener("change", function () {
      					updateSelectColor(this);
   					 });
  				});
  				
  				
    			var fileInput = document.querySelector('.fileInput');
    			var droparea = document.querySelector('.fileDrop');
    			var fileMsg = document.querySelector('.fileMsg');
    			
    			document.addEventListener('DOMContentLoaded', function () {
    				var filePath = fileMsg.textContent.trim();
    				var fileName = filePath.split('/').pop();
    				fileMsg.textContent = fileName;
    			});

    			fileInput.addEventListener('dragenter', function() {
      				droparea.classList.add('isActive');
    			});

    			fileInput.addEventListener('focus', function() {
      				droparea.classList.add('isActive');
    			});

    			fileInput.addEventListener('click', function() {
      				droparea.classList.add('isActive');
    			});

 
    			fileInput.addEventListener('dragleave', function() {
     				droparea.classList.remove('isActive');
    			});

    			fileInput.addEventListener('blur', function() {
      				droparea.classList.remove('isActive');
    			});

    			fileInput.addEventListener('drop', function() {
      				droparea.classList.remove('isActive');
    			});

    			fileInput.addEventListener('change', function() {
      				var filesCount = this.files.length;
      				var textContainer = droparea.querySelector('span');
      
      				if (filesCount === 1) {
    	 				fileMsg.classList.add('changeColor');
        				var fileName = this.value.split('\\').pop();
       					textContainer.textContent = fileName;
      				}
    			});
  			</script>
		</div>
	</div>
</body>
</html>
