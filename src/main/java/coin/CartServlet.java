package coin;

import prodotti.ProductBean;
import prodotti.ProductDaoDataSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione = request.getSession();
		int prodottoid = Integer.parseInt(request.getParameter("id"));
		
		String azione = request.getParameter("action");
		Carrello c = (Carrello) sessione.getAttribute("cart");
		ProductDaoDataSource source = new ProductDaoDataSource();
		int quantita = Integer.parseInt(request.getParameter("quantity"));
		
		ProductBean prodotto = null;
		
		try {
			prodotto = source.doRetrieveByKey(prodottoid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			if(azione != null && azione.equals("add") && prodotto != null) {
				
				c.addProduct(prodotto,quantita);
				
			}
			else if(azione != null && azione.equals("remove")) {
				c.deleteProduct(prodotto,quantita);
				response.sendRedirect("cart.jsp");
//				funziona ma i test danno fastidio
//				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
//				dispatcher.forward(request, response);
			}
			sessione.setAttribute("cart", c);
			 PrintWriter out = response.getWriter();
		        response.setContentType("text/plain");
		        response.setCharacterEncoding("UTF-8");
		        out.print(c.getCount());
		        out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
