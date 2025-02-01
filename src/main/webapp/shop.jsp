<%--suppress ALL --%>
<!DOCTYPE html>
<html lang="it" dir="ltr">
<%@ page import="java.util.ArrayList,prodotti.ProductBean,coin.Carrello,java.text.DecimalFormat"%>

<%
	HttpSession sessione = request.getSession();
	ArrayList<ProductBean> prodotti = (ArrayList<ProductBean>)sessione.getAttribute("products");
	DecimalFormat df = new DecimalFormat("#.##");

	if(prodotti==null || prodotti.isEmpty()){
		response.sendRedirect(request.getContextPath()+"/Shop");
		return;
	}
	Carrello carrello =(Carrello) sessione.getAttribute("cart");
	int v = -1;
%>

<head>
	<link rel="shortcut icon" type="image/gif" href="img/logo.png">
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale = 1, width = device-width">
	<link id="mystylesheet" rel="stylesheet" type="text/css" href="CSS/light.css">

	<%if (prodotti.size() <= 4) {%>
	<link id="mystylesheet" rel="stylesheet" type="text/css" href="CSS/shopLow.css">
	<%} else {%>
	<link id="mystylesheet" rel="stylesheet" type="text/css" href="CSS/shopHigh.css">
	<%} %>

	<title>CoinVerter</title>
</head>

<body id="" onresize="switchSuite()" onload="switchSuite()">

	<jsp:include page='header.jsp'>
		<jsp:param name="PageTitle" value="shop" />
	</jsp:include>

	<main class="bgPage">
		<section>
			<div class="menuBar">
				<header>
					<h4>CATEGORIE</h4>
					<img id="closeBtn" class="icon" src="img/icon/iconX.png" alt="">
				</header>

				<div class="category">
					<div class="categoryItem">
						<a class="selectionMenu"
							href="Shop?filter=valuta&action=categoria"> <img
							class="categoryIcon" src="img/icon/iconCurrency.png" alt="">Valute
						</a>
					</div>
					<div class="categoryItem">
						<a class="selectionMenu"
							href="Shop?filter=crypto&action=categoria"> <img
							class="categoryIcon" src="img/icon/iconCrypto.png" alt="">Cryptovalute
						</a>
					</div>
					<div class="categoryItem">
						<a class="subMenuBtn"> <img class="categoryIcon"
							src="img/icon/iconCard.png" alt="">Carte <img
							class="dropdown" src="img/icon/right.png" alt="">
						</a>
						<div class="subMenu" show>
							<a class="subItem" href="Shop?filter=ricarica&action=categoria">Ricariche</a>
							<a class="subItem" href="Shop?filter=moneta&action=categoria">Gift
								Card</a>
						</div>
					</div>
					<div class="megaPezza"></div>
				</div>
			</div>
		</section>


		<div class="bgShop">
			<section class="shop">

				<div class="headerShopContainer">
					<div class="menuBtn">
						<img src="img/icon/iconMenu.png" alt="">
					</div>
					<h2>
						I nostri <span>prodotti</span>
					</h2>
				</div>

				<div class="shopRow">
				<% if(prodotti.isEmpty()){%>
					<h1>ARRAYLIST PRODOTTI VUOTO</h1>
				<%}
				for(ProductBean p : prodotti){ %>
					<div class="shopItem">
						<div class="itemBox">
							<div class="optionContainer">
								<div class="options">
									<a onclick="openPopup('<%=p.getCode()%>')" class="option">
										<!-- <a onclick="modificaCarrello('<%=p.getCode()%>','add','quantity')" class="option"> -->
										Aggiungi al carrello
									</a>
								</div>
							</div>
							<div class="imgShopContainer">
								<img id="imgvolo"
									src="<%=request.getServletContext().getContextPath()%>/<%=p.getFoto()%>"
									alt="">
							</div>
							<div class="detailBox">
								<h5><%= p.getName() %></h5>
								<h6>
									<%if(p.getType().equals("moneta")){%> <%=df.format(p.getValue())%>
									<%}else {%>&#128; <%=df.format(p.getPrice())%><%} %>
								</h6>
							</div>
						</div>
					</div>
				<%}%>

				</div>
				<div class="popup-overlay" id="popupOverlay">
					<div class="popup" id="popup">
						<a class="close" id="closePopup" onclick="closePopupFunc()">�</a>
						<div class="popup-content">
							<p>inserisci quantit�:</p>
							<div class="wau">
								<input type="number" placeholder="quantit�" id="inputQty"
									min="1">
							</div>
							<a class="addcart" onclick="modificaCarrello('add')">Aggiungi
								al carrello</a>
						</div>
					</div>
				</div>
			</section>
		</div>

	</main>

	<script>
    const subMenu = document.querySelector('.sub-menu ul').hide();
    subMenu.addEventListener(".sub-menu a").click(function () {
      subMenu(this).parent(".sub-menu").children("ul").slideToggle("100");
      subMenu(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
    });
  </script>

	<jsp:include page='footer.jsp'></jsp:include>

	<!--Jquery-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
    $(document).ready(function () {
      // expand/collapse menuBar
      $('.menuBtn').click(function () {
        $('.menuBar').addClass('active');
        $('.menuBtn').css("visibility", "hidden");
      });

      //close
      $('#closeBtn').click(function () {
        $('.menuBar').removeClass('active');
        $('.menuBtn').css("visibility", "visible");
      })

      //subMenu
      $('.subMenuBtn').click(function () {
        $(this).next('.subMenu').slideToggle();
        $(this).find('.dropdown').toggleClass('rotate');
      });
    });
  </script>

</body>

</html>