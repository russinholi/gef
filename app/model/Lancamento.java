package model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Lancamento extends Model {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	private ETipoOperacao tipo;

	@Required
	private Double valor;

	@Enumerated(EnumType.STRING)
	private EFormaPagamento formaPagamento;

	public Integer getId() {
		return id;
	}

	public ETipoOperacao getTipo() {
		return tipo;
	}

	public void setTipo(ETipoOperacao tipo) {
		this.tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public EFormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(EFormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

}
