
<!DOCTYPE html>
<html lang="it" dir="ltr">
<%@ page import="java.util.ArrayList,coin.Ordine,utenti.User,java.text.DecimalFormat"%>

<%
	User admin = (User)request.getSession().getAttribute("user");
	if(!admin.isAdmin()){
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return;
	}
	ArrayList<Ordine> ordini =(ArrayList<Ordine>) request.getSession().getAttribute("ordini");
	if(ordini == null){
		response.sendRedirect(request.getContextPath() + "/Ordini");
		return;
	}
	
	DecimalFormat df = new DecimalFormat("#.##");
%>

<head>
<link rel="shortcut icon" type="image/gif" href="../img/logo.png">
<meta charset="utf-8">
<meta name="viewport" content="initial-scale = 1, width = device-width">
<%if(ordini.size() <= 2){%>
	<link id="mystylesheet" rel="stylesheet" type="text/css" href="CSS/adminFix.css">
<%}else if(ordini.size() <= 9){%>
	<link id="mystylesheet" rel="stylesheet" type="text/css" href="CSS/adminLow.css">
<%}%>

<script src="../JS/scripts.js"></script>

<title>CoinVerter</title>
</head>

<body
	onload="consoleText(['Ciao, <%=admin.getNome()%>', 'Gestione ordini'], 'text', ['gold', 'grey'])"
	onresize="brutta()">
	<jsp:include page='adminHeader.jsp'></jsp:include>
	<main class="bgPage">
		<div id="bgLow" class="bg">
			<section class="tableFix">
				<div class="prodBtn">
					<form class="filterForm" method="POST" action="Ordini">
						<button class="adminBtn"><a href="Ordini">RIMUOVI FILTRO</a></button>
						<input type="text" name="user" id="orderFilter" placeholder="filter..."/>
						<input type="date" name="start"	value="<%=java.time.LocalDate.now()%>"/>
						<input type="date" name="end" value="<%=java.time.LocalDate.now()%>"/>
						<button id="btnFilter" class="adminBtn" type=submit>FILTRA</button>
					</form>
				</div>
				<table>
  					<thead>
    					<tr>
							<th colspan="2" scope="col">E-MAIL</th>
							<th scope="col">ID ORDINE</th>
							<th scope="col">DATA</th>
							<th scope="col">QUANTITA'</th>
							<th scope="col">PRODOTTO</th>
							<th scope="col">TIPO</th>
							<th scope="col">COSTO</th>
    					</tr>
  					</thead>
  					<tbody>
  					<%for(Ordine o: ordini){ %>
    					<tr>
							<td colspan="2" data-label="E-MAIL"><%=o.getEmail()%></td>
							<td data-label="ID ORDINE"><%=o.getID_ordine()%></td>
							<td data-label="DATA"><%=o.getData_acquisto()%></td>
							<td data-label="QUANTITA'"><%=o.getQ_acquisto()%></td>
							<td data-label="PRODOTTO"><%=o.getNome_prodotto()%></td>
							<td data-label="TIPO"><%=o.getTipo_prodotto()%></td>
							<td data-label="COSTO"><%=df.format(o.getPrezzo())%></td>
    					</tr>
    				<%} %>
  					</tbody>
				</table>
			</section>
		</div>
	</main>
</body>
</html>