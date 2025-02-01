package admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import prodotti.ProductBean;
import prodotti.ProductDaoDataSource;

/**
 * Servlet implementation class ModifyProductServlet
 */
@WebServlet("/ModificaProdotto")
public class ModifyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione = request.getSession();
		
		ProductBean prod = new ProductBean();
		ProductDaoDataSource source = new ProductDaoDataSource();
		int id =Integer.parseInt(request.getParameter("id"));
		
		try {
			prod = source.doRetrieveByKey(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sessione.setAttribute("mprod", prod);
		response.sendRedirect("admin/ModificaForm.jsp");
		/*
		//funziona ma junit da problemi
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/ModificaForm.jsp");
		dispatcher.forward(request, response);
		*/
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
	
