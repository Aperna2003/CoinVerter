package prodotti;


import java.io.IOException;
import java.sql.SQLException;

//import prodotti.ProductBean;
import utenti.User;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/Gestione")
@MultipartConfig
public class ManageProductServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u =(User) request.getSession().getAttribute("user");
		if(u == null || !u.isAdmin()) {
			response.sendRedirect("index.jsp");
			return;
		}
		String attivita = request.getParameter("activity");
		ProductBean prodotto = new ProductBean();
		ProductDaoDataSource source = new ProductDaoDataSource();
		
		
		
		
		if(attivita == null) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				prodotto = source.doRetrieveByKey(id);
				request.getSession().setAttribute("mod", prodotto);
				response.sendRedirect("admin/ProdottoForm.jsp");
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		if(attivita != null) {
			
			switch(attivita) {
				case "modify": 
					System.out.println("dentro modify");
					try {
						int id = Integer.parseInt(request.getParameter("id"));
						float prezzo = -1;
						Double valore = -1.00;
						
						if(!request.getParameter("valore").equals(""))
							valore = Double.parseDouble(request.getParameter("valore"));
						
						if(!request.getParameter("prezzo").equals(""))
							prezzo = Float.parseFloat(request.getParameter("prezzo"));
						//int quantita = Integer.parseInt("quantity");
						//boolean disponibile = Boolean.parseBoolean("available");
						String tipo = request.getParameter("tipo");
						//String nome = null;
						String nome = request.getParameter("nome");
						Part filePart = request.getPart("foto");
						

						prodotto = source.doRetrieveByKey(id);
						if(nome != null && !nome.equals("")) prodotto.setName(nome);
						
						if(tipo != null) prodotto.setType(tipo);
					
						if(valore != -1.00) prodotto.setValue(valore);
						
						if(prezzo != -1) prodotto.setPrice(prezzo);
						
						//prodotto.setAvailable(disponibile);
						

						
						 
						if(filePart != null) {
					    	 String fileName = filePart.getSubmittedFileName();
					    	 if(fileName != null && !fileName.isEmpty()){
					    		 String user = System.getProperty("user.home"); 
					    		 
					    		 
					    		 
					    		 
					    		 
					    		 //String relativePath = user + "\\git\\repository\\codice\\CoinVerter\\WebContent\\img\\products\\" + fileName;
					    		 //String relativePath = "\\WebContent\\img\\products\\" + fileName;
					    		 
					    		 String projectPath = getServletContext().getRealPath("/");
					    	     String relativePath = projectPath + "img/products/" + fileName;
					    	     System.out.println(relativePath);
					    		 
					    		 
					    		 filePart.write(relativePath);
					        //prodotto.setFoto(relativePath);
					        prodotto.setFoto("img/products/" + fileName);
					     }						
					     }
						//if((prodotto.getType() == "ricarica") && quantita > 0) prodotto.setQuantity(quantita);
						//else prodotto.setQuantity(-1)
						source.doUpdate(prodotto);
						System.out.println("fatto doupdate nuovi valori: "+ prodotto.toString());
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "add":
					float prezzo = -1;
					
					Double valore = -1.00;
	
					valore = Double.parseDouble(request.getParameter("valore"));
					prezzo = Float.parseFloat(request.getParameter("prezzo"));

					String tipo = request.getParameter("tipo");
					//String nome = null;
					String nome = request.getParameter("nome");
					Part filePart = request.getPart("foto");
					try {	
						
						if(nome != null && !nome.equals("")) prodotto.setName(nome);
						
						if(tipo != null) prodotto.setType(tipo);
						
						if(valore != -1.00) prodotto.setValue(valore);
					
						if(prezzo != -1) prodotto.setPrice(prezzo);
						
						 if(filePart != null) {
					    	 String fileName = filePart.getSubmittedFileName();
					    	 
					    	 if(fileName != null && !fileName.isEmpty()){
					    		// String user = System.getProperty("user.home");

					    		 String projectPath = getServletContext().getRealPath("/");
					    	     String relativePath = projectPath + "img/products/" + fileName;
					    	     System.out.println(relativePath);
					    		 
					    		 filePart.write(relativePath);
					    		 prodotto.setFoto("img/products/" + fileName);
					    	 }						
					     }
						
						prodotto.setQuantity(-1);
						source.doSave(prodotto);
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
				case "remove":
					try {
						int id = Integer.parseInt(request.getParameter("id"));
						if(id > 0)source.doRemove(id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
			
				case "delete":
					try {
						int id = Integer.parseInt(request.getParameter("id"));
						if(id > 0)source.doDelete(id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
			
			}//switch
			response.sendRedirect("ProdottiAD");
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	
	
	
}	