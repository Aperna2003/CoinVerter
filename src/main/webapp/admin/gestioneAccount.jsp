<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList,utenti.User"%>


<%
	HttpSession sessione = request.getSession();
	ArrayList<User> users = (ArrayList<User>)sessione.getAttribute("acc");
	
	if(session.getAttribute("REFRESH")!=null){
	    response.sendRedirect("../your_servlet");
	}
	else{    
	session.setAttribute("REFRESH","TRUE");
	}
	
	if(users==null || users.isEmpty()){
		response.sendRedirect(request.getContextPath()+"/GestioneACC");
		return;
	}
	
%>


<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/gif" href="img/logo.png">
<meta charset="utf-8">
<meta name="viewport" content="initial-scale = 1, width = device-width">
<%if(users.size() <= 3){%>
	<link id="mystylesheet" rel="stylesheet" type="text/css" href="<%=request.getServletContext().getContextPath()%>/CSS/adminFix.css">
<%}else if(users.size() <= 9){%>
	<link id="mystylesheet" rel="stylesheet" type="text/css" href="<%=request.getServletContext().getContextPath()%>/CSS/adminLow.css">
<%}%>
<script src="JS/scripts.js"></script>

<title>CoinVerter</title>
</head>


<body
	onload="consoleText(['account.', 'Choose what you want to do'], 'text', ['tomato', 'rebeccapurple'])"
	onresize="brutta()">
	<jsp:include page='adminHeader.jsp'></jsp:include>

	<main class="bgPage">
		<div id="bgLow" class="bg">
			<section class="tableFix">			
				<table>
  					<thead>
    					<tr>
							<th scope="col">NOME</th>
							<th scope="col">COGNOME</th>
							<th scope="col">EMAIL</th>
							<th scope="col">STATUS ADMIN</th>
							<th scope="col"></th>
    					</tr>
  					</thead>
  					<tbody>
  					<%for(User u: users){ %>
    					<tr>
							<td data-label="NOME"><%=u.getNome()%></td>
							<td data-label="COGNOME"><%=u.getCognome()%></td>
							<td data-label="EMAIL"><%=u.getEmail()%></td>
							<td data-label="STATUS ADMIN"><%=u.isAdmin()%></td>
							<td>
								<button class="adminBtn modAdminBtn">
									<a href="../Manage?activity=modify&email=<%=u.getEmail()%>">
										<img src="<%=request.getServletContext().getContextPath()%>/img/icon/iconModify.png" alt="" class="remove-item">
									</a>
								</button>
								<button class="adminBtn modAdminBtn">
									<a href="../Manage?activity=remove&email=<%=u.getEmail()%>">
										<img src="<%=request.getServletContext().getContextPath()%>/img/icon/iconTrash.png"	alt="" class="remove-item">
									</a>
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