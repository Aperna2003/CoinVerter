package utenti;

import java.io.IOException;
import java.sql.SQLException;

import prodotti.ProductBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Manage")
public class ManageAccountServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("REFRESH");
		User u =(User) request.getAttribute("user");
		/*if(u == null || !u.isAdmin()) {
			response.sendRedirect("index.jsp");
			return;
		}*/
		String attività = request.getParameter("activity");
		User user = new User();
		String email = request.getParameter("email");
		System.out.println(email);
		UsersDaoDataSource source = new UsersDaoDataSource();
		try {
		user = source.doRetrieveByEmail(email);
		
		if(attività != null) {
			switch(attività) {
				case "modify": 
				
						
						if(!user.isAdmin()) user.setAdmin(true);
							else user.setAdmin(false);
						
						source.doUpdate(user);
						
						
						
					
					break;
				
				case "remove":
					try {
						if(email != null)source.doDelete(email);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
			}//switch
		}
		//response.sendRedirect(getServletContext().getContextPath()+ "/admin/gindex.jsp");
		response.sendRedirect("GestioneACC");
		return;
		
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}	
}