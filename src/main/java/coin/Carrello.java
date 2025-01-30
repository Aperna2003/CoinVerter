package coin;

import prodotti.ProductBean;
import java.util.ArrayList;
import java.util.Iterator;



public class Carrello {

	private ArrayList<ProductBean> products;
	private int count;
	
	public Carrello() {
		products = new ArrayList<ProductBean>();
		count = 0;
	}
	
	public void addProduct(ProductBean product, int q) {
	    if(product != null && q > 0) {
			if (products.isEmpty()) {
		        products.add(product);
		        product.setQuantity(q);
		        count += q;
		    } else {
		        boolean productExists = false;
		        for (Iterator<ProductBean> it = products.iterator(); it.hasNext(); ) {
		            ProductBean prod = it.next();
		            if (prod.getCode() == product.getCode()) {
		                prod.setQuantity(prod.getQuantity() + q);
		                count += q;
		                productExists = true;
		                break;
		            }
		        }
		        if (!productExists) {
		            products.add(product);
		            product.setQuantity(q);
		            count += q;
		        }
		    }
	
	    }
	}    

	public void deleteProduct(ProductBean product,int quantity) {
		if(quantity>0) {
			for(ProductBean prod : products) {
				if(prod.getCode() == product.getCode()) {
					products.remove(prod);
					count = count - quantity;
					break;
				}
			}
	 	}
	}	
	
	public int getCount(){
		return count;
	}
	
	public ArrayList<ProductBean> getProducts() {
		return products;
	}

	public void clearCart() {
		products.clear();
		count = 0;
	}

}


