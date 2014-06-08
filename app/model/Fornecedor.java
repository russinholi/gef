package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;

import play.data.validation.Constraints.Required;

@Entity
@DiscriminatorValue("F")
public class Fornecedor extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Required
	private String cnpj;

	private String ie;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public static Query<Fornecedor> query() {
		return Ebean.createQuery(Fornecedor.class);
	}

	
}
