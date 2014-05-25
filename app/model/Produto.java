package model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Produto extends Model {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Integer id;

	private Long codigoBarras;

	@Required
	private String nome;

	private String descricao;

	private String apresentacao;

	@Required
	private Double preco;

	@Enumerated(EnumType.STRING)
	private ETipoMedicamento tipoMedicamento;

	@Enumerated(EnumType.STRING)
	private ETipoControle tipoControle;

	private Integer quantidade;

	public Long getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(Long codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getApresentacao() {
		return apresentacao;
	}

	public void setApresentacao(String apresentacao) {
		this.apresentacao = apresentacao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public ETipoMedicamento getTipoMedicamento() {
		return tipoMedicamento;
	}

	public void setTipoMedicamento(ETipoMedicamento tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
	}

	public ETipoControle getTipoControle() {
		return tipoControle;
	}

	public void setTipoControle(ETipoControle tipoControle) {
		this.tipoControle = tipoControle;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getId() {
		return id;
	}	
}
