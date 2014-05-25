package model;

import play.data.validation.Constraints.Required;

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

	
}
