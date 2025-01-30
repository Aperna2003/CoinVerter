package coin;

import java.sql.Date;

public class Ordine {
	
	int ID_ordine;
	Date data_acquisto;
	String email;
	int q_acquisto;
	String nome_prodotto;
	String tipo_prodotto;
	double prezzo;
	
	
	
	public int getID_ordine() {
		return ID_ordine;
	}
	public void setID_ordine(int iD_ordine) {
		ID_ordine = iD_ordine;
	}
	public Date getData_acquisto() {
		return data_acquisto;
	}
	public void setData_acquisto(Date data_acquisto) {
		this.data_acquisto = data_acquisto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getQ_acquisto() {
		return q_acquisto;
	}
	public void setQ_acquisto(int q_acquisto) {
		this.q_acquisto = q_acquisto;
	}
	public String getNome_prodotto() {
		return nome_prodotto;
	}
	public void setNome_prodotto(String nome_prodotto) {
		this.nome_prodotto = nome_prodotto;
	}
	public String getTipo_prodotto() {
		return tipo_prodotto;
	}
	public void setTipo_prodotto(String tipo_prodotto) {
		this.tipo_prodotto = tipo_prodotto;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	
	
	
}
