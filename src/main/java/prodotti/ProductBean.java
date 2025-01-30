package prodotti;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	String[] ProductType = {"valuta","crypto","moneta","ricarica"};
	
	int code;
	String name;
	double price;
	double value;
	Integer quantity;
	String tipo;
	String foto;
	boolean available;
	

	
	public ProductBean() {
		code = -1;
		name = "";
		price = -1;
		value = 1;
		quantity = 1;
		tipo = "";
		foto = null;
		available = true;
	}
	
	public ProductBean(int c, String n, double p, double v, String t) {
		code = c;
		name = n;
		price = p;
		value = v;
		quantity = 1;
		this.setType(t);
		foto = null;
		available = true;
	}
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getType() {
		return tipo;
	}
	



	public void setType(String tipo) {
		for(String t : ProductType)if(tipo.equals(t)) {this.tipo = tipo; break;}	
	}



	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "ProductBean [ProductType=" + Arrays.toString(ProductType) + ", code=" + code + ", name=" + name
				+ ", price=" + price + ", value=" + value + ", quantity=" + quantity + ", tipo=" + tipo + ", foto="
				+ foto + ", available=" + available + "]";
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(ProductType);
		result = prime * result + Objects.hash(available, code, foto, name, price, quantity, tipo, value);
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductBean other = (ProductBean) obj;
		return Arrays.equals(ProductType, other.ProductType) && available == other.available && code == other.code
				&& Objects.equals(foto, other.foto) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(quantity, other.quantity) && Objects.equals(tipo, other.tipo)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	
	
	
}
