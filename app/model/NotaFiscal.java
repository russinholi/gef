package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

@Entity
public class NotaFiscal extends Model {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Integer numero;

	@OneToOne
	private Pessoa pessoa;

	@Enumerated(EnumType.STRING)
	private ETipoOperacao tipo;
	
	private Date data;

	public Integer getNumero() {
		return numero;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public ETipoOperacao getTipo() {
		return tipo;
	}

	public void setTipo(ETipoOperacao tipo) {
		this.tipo = tipo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	

}
