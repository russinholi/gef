package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;

@Entity
@DiscriminatorValue("C")
public class Cliente extends Pessoa {

	private static final long serialVersionUID = 1L;

	private String cpf;
	
	private String rg;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public static Query<Cliente> query() {
		return Ebean.createQuery(Cliente.class);
	}
}
