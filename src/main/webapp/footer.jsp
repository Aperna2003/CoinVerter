<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale = 1, width = device-width">
<link id="mystylesheet" rel="stylesheet" type="text/css" href="<%=request.getServletContext().getContextPath()%>/CSS/light.css">
<title>Footer</title>
</head>
<body>
	<footer>
		<div class="footerContainer">
			<div class="footerFlexbox">
				<img id="footerLogo" src="<%=request.getServletContext().getContextPath()%>/img/logoName.png" alt="">
			</div>
			<div class="footerFlexbox">
				<div class="menu">
					<ul>
						<li><a href="#">About Us</a></li>
						<li><a href="#">Contact</a></li>
					</ul>
				</div>
			</div>
			<div class="footerFlexbox">
				<div class="social">
					<a href="" target="_blank"><img
						src="<%=request.getServletContext().getContextPath()%>/img/social/insta.png" alt=""></a> <a href="" target="_blank"><img
                        src="<%=request.getServletContext().getContextPath()%>/img/social/x.png" alt=""></a> <a href="" target="_blank"><img
                        src="<%=request.getServletContext().getContextPath()%>/img/social/youtube.png" alt=""></a> <a href="" target="_blank"><img
						src="<%=request.getServletContext().getContextPath()%>/img/social/linkedin.png" alt="">
					</a>
				</div>
			</div>
		</div>
	</footer>

</body>
</html>