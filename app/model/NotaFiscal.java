package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

@Entity
public class NotaFiscal extends Model {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Integer numero;

	@OneToOne
	@JoinColumn(name = "PESSOA_ID", nullable = false)
	private Pessoa pessoa;

	@Enumerated(EnumType.STRING)
	private ETipoOperacao tipo;
	
	private Date data;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "notaFiscal")
	private List<ItemNotaFiscal> itens = new ArrayList<ItemNotaFiscal>();

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
	
	public List<ItemNotaFiscal> getItens() {
		return itens;
	}

	public void addItem(ItemNotaFiscal item) {
		if (!itens.contains(item)) {
			itens.add(item);
			item.setNotaFiscal(this);
		}
	}

	public Double getValorTotal() {
		double valorTotal = 0;
		for (ItemNotaFiscal item : itens) {
			valorTotal += item.getValor();
		}
		return valorTotal;
	}
}
 