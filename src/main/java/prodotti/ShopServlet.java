package prodotti;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Shop")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sessione = request.getSession();
		String filtro = request.getParameter("filter");
		if(filtro== null)filtro = "";
		String azione = request.getParameter("action");
		
		if(azione== null)azione = "ricerca";
		ArrayList<ProductBean> prodotti = new ArrayList<ProductBean>();
		ProductDaoDataSource source = new ProductDaoDataSource();

		
		if (azione.equals("ricerca")) {

			try {
				if (filtro.length() != 0) {
					prodotti = source.doRetrieveByName(filtro);
				} else {
					prodotti = source.doRetrieveAvailable();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (azione.equals("categoria")) {
			try {

				if (filtro != null && filtro.length() != 0) prodotti = source.doRetrieveByCategory(filtro);
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		sessione.setAttribute("products", prodotti);
		response.sendRedirect("shop.jsp");
		/*
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/shop.jsp");
		dispatcher.forward(request, response);

		 */
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}