
<%
/*
	controllo accesso solo admin...
*/
%>
<head>
<link rel="shortcut icon" type="image/gif"
	href="${pageContext.request.contextPath}/img/logo.png">
<meta charset="utf-8">
<meta name="viewport" content="initial-scale = 1, width = device-width">
<link id="mystylesheet" rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/light.css">
<script src="${pageContext.request.contextPath}/JS/scripts.js"></script>
<title>CoinVerter</title>
</head>

<body
	onload="consoleText(['Hi, Admin.', 'Choose what you want to do'], 'text', ['tomato', 'rebeccapurple'])"
	onresize="brutta()">
	<header id="header-block">
		<div class="managementHeader">
			<div class="sidepanelBtn" onclick="bailandoo()">
				<img id="btnIcon"
					src="${pageContext.request.contextPath}/img/icon/iconMenu.png"
					alt="">
			</div>
			<div id="animationHeader" class='console-container'>
				<span id='text'></span>
				<div class='console-underscore' id='console'>&#95;</div>
			</div>
		</div>
	</header>
	<section>
		<div id="sidepanel" class="menuBar">
			<div class="extendBtn" onclick="scomparisci()">
				<a class="extend"> <img
					src="${pageContext.request.contextPath}/img/icon/right.png"
					alt="">
				</a>
			</div>
			<div id="sideMenu" class="category">
				<div id="sideItem" class="categoryItem">
					<a
						href="${pageContext.request.contextPath}/Ordini"
						class="sideSelection"> <img id="sideIcon" class="categoryIcon"
						src="${pageContext.request.contextPath}/img/icon/iconSettings.png"
						alt=""><span class="spec">HOME</span>
					</a>
				</div>
				<div id="sideItem2" class="categoryItem">
					<a href="${pageContext.request.contextPath}/ProdottiAD"
						class="sideSelection"> <img id="sideIcon2" class="categoryIcon"
						src="${pageContext.request.contextPath}/img/icon/iconProduct.png"
						alt=""><span class="spec">PRODUCTS</span>
					</a>
				</div>
				<div id="sideItem3" class="categoryItem">
					<a href="${pageContext.request.contextPath}/GestioneACC"
						class="sideSelection"> <img id="sideIcon3" class="categoryIcon"
						src="${pageContext.request.contextPath}/img/icon/iconUserSettings.png"
						alt=""><span class="spec">ACCOUNTS</span>
					</a>
				</div>
				<div id="sideItem4" class="categoryItem">
					<a href="${pageContext.request.contextPath}/index.jsp"
						class="sideSelection"> <img id="sideIcon4" class="categoryIcon"
						src="${pageContext.request.contextPath}/img/icon/iconBack.png"
						alt=""><span class="spec">BACK TO SITE</span>
					</a>
				</div>
			</div>
		</div>
	</section>
</body>
