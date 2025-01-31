<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/gif" href="<%=request.getServletContext().getContextPath()%>/img/logo.png">
<meta charset="utf-8">
<meta name="viewport" content="initial-scale = 1, width = device-width">
<link id="mystylesheet" rel="stylesheet" type="text/css" href="<%=request.getServletContext().getContextPath()%>/CSS/light.css">
<title>CoinVerter Login</title>
<%//ProductBean prod = new ProductBean();%>
</head>
<body class="bgPage">
	<div class="login">
		<div class="loginForm">
			<h2>Aggiungi Prodotto</h2>
			<form action="../Gestione?activity=add"  method="POST" enctype="multipart/form-data">
				<input type="text" name="nome" placeholder="Nome" required="required">
				<input type="number" name="valore" placeholder="Valore"  step="1" min="0">
				<input type="number" name="prezzo" placeholder="Prezzo" required="required" step="0.01" min="0">
				<select name="tipo" required="required">
					<option value="" disabled selected hidden>Categoria</option>
					<option value="valuta">Valuta</option>
					<option value="crypto">Cryptovaluta</option>
					<option value="ricarica">Ricarica carta</option>
					<option value="moneta">Valuta di gioco</option>
				</select>
				<!-- <input type="file" name="foto" accept=".png, .jpg, .jpeg" required="required"> -->
				
				<div class="fileDrop">
  					<span class="fileMsg">Scegli file</span>
  					<input class="fileInput" type="file" name="foto" accept=".png, .jpg, .jpeg" required="required">
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
