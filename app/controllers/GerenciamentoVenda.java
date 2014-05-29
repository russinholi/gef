package controllers;

import java.util.ArrayList;

import java.util.List;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.venda.*;

public class GerenciamentoVenda extends Controller {

	public static Result index() {
		return ok(index.render("Teste"));
	}

	public static Result formularioNovaVenda() {
		Form<FormularioVenda> formulario = Form.form(FormularioVenda.class);
		FormularioVenda dto = new FormularioVenda();
		return ok(formularioVenda.render(formulario, dto));
	}

	public static Result persistirNotaFiscal() {
		return ok(index.render("x"));
	}
	
	public static class FormularioVenda {
		public String cpf;
		public Long produto;
		public String descricao;
		public Double precoUnitario;
		public Integer quantidade ;
		public Double desconto;
		public Double precoTotal;
		
		public List<FormularioVenda> itens = new ArrayList<FormularioVenda>();
	}
}
