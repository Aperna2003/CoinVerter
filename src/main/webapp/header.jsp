<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="utenti.User,coin.Carrello,prodotti.ProductBean,java.util.Map,java.util.HashMap,java.text.DecimalFormat"%>

<%
	String name = request.getParameter("PageTitle");
	String ref = "";
	String link = "";
	User user = (User)session.getAttribute("user");
	//User user = new User();
	//user.setAdmin(true);
	if(session.getAttribute("update")!= null){
		session.removeAttribute("update");
		
	}
	
	Carrello cart = (Carrello)request.getSession().getAttribute("cart");
	if(cart == null){
		cart = new Carrello();
		request.getSession().setAttribute("cart",cart); 		
	}
	
	
	switch(name){
	case "shop":
	case "cart":
		ref = "index.jsp";
		link = "HOME";		
		break;
	case "index": 
		ref = "Shop";
		link = "SHOP";
		break;
	}
	
	DecimalFormat df = new DecimalFormat("#.##");
	%>

<!DOCTYPE html>
<html>
<head>
<link id="mystylesheet" rel="stylesheet" type="text/css"
	href="CSS/userHeader.css">
<%if(user!= null && user.isAdmin()){%>
<link id="mystylesheet" rel="stylesheet" type="text/css"
	href="CSS/adminHeader.css">
<%}%>
<script type="text/javascript" src="JS/scripts.js"></script>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
<body>

	<header id="header-block">
		<section>
			<div id="container">
				<div id="logoBox">
					<img id="logo" src='img/logoName.png' alt="">
				</div>
				<div id="collection">
					<div id="ref">
						<a href=<%=ref%>> <%=link%></a>
					</div>
				</div>
				<div class="searchContainer">
					<img id="searchIcon" src="img/icon/iconSearch.png" alt=""
						onclick="Ricerca('big')">
					<button id="searchBtn">
						<img id="searchIconBtn" onclick="openSearchbar(this.id)"
							src="img/icon/iconSearch.png" alt="" isOpenSearchbar>
					</button>
					<input type="text" id="input" name="searchBox"
						placeholder="Search...">

				</div>

				<div id="toggle">
					<%if(user == null){ %>
					<div id="loginButton" class="cartContainer"
						onclick="checkLogin(user)">
						<div class="position">
							<a href="login.jsp"><img class="icon"
								src="img/icon/iconUser.png" alt=""></a> <span class="caption">LOGIN</span>
						</div>
					</div>


					<%}else if(user != null && !user.isAdmin()){ %>
					<div id="loginButton" class="cartContainer">
						<div class="position">
							<a href="Logout"><img class="icon"
								src="img/icon/iconUserLog.png" alt=""></a> <span
								class="caption">LOGOUT</span>
						</div>
					</div>

					<%}else if (user != null && user.isAdmin()){ %>
					<div id="loginButton" class="cartContainer">
						<div class="position">
							<a href="Logout"><img class="icon"
								src="img/icon/iconUserLog.png" alt=""></a> <span
								class="caption">LOGOUT</span>
						</div>
					</div>

					<div class="cartContainer">
						<div class="position">
							<a href="<%=request.getServletContext().getContextPath()%>/Ordini"><img
								class="icon" src="img/icon/iconSettings.png" alt=""></a> <span
								class="caption">GESTIONE</span>
						</div>
					</div>
					<%} %>

					<div class="cartContainer">
						<div class="position">
							<div class="badge yellow" id="contatoreCarrello"><%=cart.getCount()%></div>
							<a href="cart.jsp"><img class="icon"
								src="img/icon/iconCart.png" alt=""></a> <span class="caption">CART</span>
						</div>
					</div>
					<%if(user != null){ %>
					<div class="cartContainer" onClick="openSidebar(this.id)"
						id="isOpenSidebar">
						<div class="position">
							<img class="icon" src="img/icon/iconWallet.png" alt=""> <span
								class="caption">WALLET</span>
						</div>
					</div>
					<%} %>

				</div>
			</div>
		</section>
	</header>

	<div id="sorciobarra" class="searchbar" hidden>
		<div id="sorcioover" class="searchbarOverlay"
			onclick="openSearchbar(this.id)"></div>
		<div class="searchbarContent">
			<div class="searchbarBody">
				<input type="text" id="inputbar" name="searchBox"
					placeholder="Search...">
				<button onclick="openSearchbar(this.id)" class="sBtn" id="close">
					<img class="icon" src="img/icon/iconX.png" alt="">
				</button>
			</div>
			<button onclick="Ricerca('small')" class="sBtn">
				<img class="icon" src="img/icon/iconSearch.png" alt="">
			</button>
		</div>
	</div>

	<div id="sideb" class="sidebar" hidden>
		<div id="sideover" onclick="openSidebar(this.id)" class="sidebarOverlay"></div>
		<div class="sidebarContent">
			<div class="sidebarHeader">
				<h4>WALLET</h4>
				<img onclick="openSidebar(this.id)" id="sidex" class="icon"	src="img/icon/iconX.png" alt="">
			</div>
			<div class="sidebarBody">
				<div class="wallet">
					<%if(user!= null){
						HashMap<String,Double> valute = user.getPortafoglio();
						for(Map.Entry<String, Double> v: valute.entrySet()) {
					%>
						<div class="keyValue">
							<div class="keyValueRow">
								<div class="key"><%=v.getKey()%>:</div>
								<div class="value">	<%=df.format(v.getValue())%></div>
							</div>
						</div>
					<%}} %>
				</div>
			</div>
		</div>
	</div>


</body>
</html>