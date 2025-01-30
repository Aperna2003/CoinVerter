package coin;

import java.io.IOException;
import java.sql.SQLException;

import prodotti.ProductBean;
import prodotti.ProductDaoDataSource;
import utenti.User;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDaoDataSource source = new ProductDaoDataSource();
		HttpSession sessione = request.getSession();
		User utente =(User) sessione.getAttribute("user");
		if(utente == null){
		response.sendRedirect("login.jsp");
		return;
		}
		Carrello c = (Carrello)sessione.getAttribute("cart");
		ArrayList<ProductBean> prodotti = c.getProducts();
		HashMap<String,Double> portafoglio = utente.getPortafoglio();
		
		for(ProductBean p: prodotti) {
			if(p.getType().equals("valuta")|| p.getType().equals("crypto")) {
				if(!portafoglio.containsKey(p.getName()))portafoglio.put(p.getName(),(p.getValue()*p.getQuantity()));
				else {
					double val = portafoglio.get(p.getName());
					val += (p.getValue()*p.getQuantity());
					portafoglio.put(p.getName(),val);	
				}
			}
		}
		
		try {
			source.doBuy(prodotti, utente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c.clearCart();
		utente.setPortafoglio(portafoglio);
		sessione.setAttribute("cart",c);
		sessione.setAttribute("user",utente);
		
		response.sendRedirect("index.jsp");
		
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
