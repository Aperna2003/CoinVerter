<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList,prodotti.ProductBean,java.text.DecimalFormat"%>


<%
	HttpSession sessione = request.getSession();
	ArrayList<ProductBean> prodotti = (ArrayList<ProductBean>)sessione.getAttribute("prd");
	

	if(prodotti==null || prodotti.isEmpty()){
		response.sendRedirect(request.getContextPath()+"/ProdottiAD");
		return;
	}

	DecimalFormat df = new DecimalFormat("#.##");

%>


<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/gif" href="img/logo.png">
<meta charset="utf-8">
<meta name="viewport" content="initial-scale = 1, width = device-width">
<%if(prodotti.size() <= 2){%>
	<link id="mystylesheet" rel="stylesheet" type="text/css" href="CSS/adminFix.css">
<%}else if(prodotti.size() <= 9){%>
	<link id="mystylesheet" rel="stylesheet" type="text/css" href="CSS/adminLow.css">
<%}%>

<script src="JS/scripts.js"></script>

<title>CoinVerter Sezione Admin</title>
</head>


<body
	onload="consoleText(['Hi, Admin.', 'Choose what you want to do'], 'text', ['tomato', 'rebeccapurple'])"
	onresize="brutta()">
	<jsp:include page='adminHeader.jsp'></jsp:include>

	<main class="bgPage">
		<div id="bgLow" class="bg">
			<section class="tableFix">
				<div class="prodBtn">
					<button class="adminBtn"><a id="addNewProduct" href="admin/ProdottoForm.jsp">AGGIUNGI PRODOTTO</a></button>
				</div>					
				<table>
  					<thead>
    					<tr>
							<th scope="col"></th>
							<th scope="col">ID</th>
							<th scope="col">NOME</th>
							<!--<th scope="col">QUANTITA'</th>-->
							<th scope="col">VALORE</th>
							<th scope="col">PREZZO</th>
							<th scope="col">TIPO</th>
    					</tr>
  					</thead>
  					<tbody>
  					<%for(ProductBean p : prodotti){%>
    					<tr>
							<td><img id="pImg" src="<%=p.getFoto()%>"	alt=""></td>
							<td data-label="ID"><%=p.getCode()%></td>
							<td data-label="NOME"><%=p.getName()%></td>
							<!--<td data-label="QUANTITA'"><%=p.getQuantity()%></td>-->
							<td data-label="VALORE"><%=df.format(p.getValue())%></td>
							<td data-label="PREZZO"><%=df.format(p.getPrice())%></td>
							<td data-label="TIPO"><%=p.getType()%></td>
							<td>
								<button class="adminBtn modAdminBtn">
									<a href="ModificaProdotto?id=<%= p.getCode()%>"><img src="img/icon/iconModify.png" alt="" class="remove-item"></a>
								</button>
								<button class="adminBtn modAdminBtn">
									<a href="Gestione?id=<%=p.getCode()%>&activity=delete"><img src="img/icon/iconTrash.png" alt="" class="remove-item"></a>
								</button>
							</td>
    					</tr>
    				<%} %>
  					</tbody>
				</table>
			</section>
		</div>
	</main>
</body>
</html>