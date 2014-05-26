package model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.avaje.ebean.Ebean;

import play.db.ebean.Model;

@Entity
public class Usuario extends Model {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Integer id;
	
	private String login;
	
	private String senha;
	
	private String nome;
	
	private String cpf;
	
	private String rg;
	
	private Double percentualComissao;
	
	private Boolean ativo;

	public Integer getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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

	public Double getPercentualComissao() {
		return percentualComissao;
	}

	public void setPercentualComissao(Double percentualComissao) {
		this.percentualComissao = percentualComissao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
		
}
