package controllers;

import java.util.List;


import model.ETipoProduto;
import model.Produto;

import com.avaje.ebean.Ebean;

import play.data.Form;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.produto.formularioProduto;
import views.html.produto.formularioMedicamento;
import views.html.produto.index;

public class GerenciamentoEstoque extends Controller {

	public static Result index() {
		List<Produto> listaProduto = Produto.query().where().
										eq("tipoProduto", ETipoProduto.PRODUTO).findList();
		return ok(index.render(listaProduto, ETipoProduto.PRODUTO));
	}

	public static Result indexMedicamento() {
		List<Produto> listaProduto = Produto.query().where().
										eq("tipoProduto", ETipoProduto.MEDICAMENTO).findList();
		return ok(index.render(listaProduto, ETipoProduto.MEDICAMENTO));
	}
	
	public static Result formularioNovoProduto() {
		Form<Produto> form = Form.form(Produto.class);
		return ok(formularioProduto.render(form));
	}

	public static Result formularioMedicamento() {
		Form<Produto> form = Form.form(Produto.class);
		return ok(formularioMedicamento.render(form));
	}
	
	public static Result persistirProduto() {
		Form<Produto> form = Form.form(Produto.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(formularioProduto.render(form));
		}
		Produto novoProduto = form.get();
		if (novoProduto.getTipoMedicamento() != null){
			novoProduto.setTipoProduto(ETipoProduto.MEDICAMENTO);
		} else {
			novoProduto.setTipoProduto(ETipoProduto.PRODUTO);
		}
		novoProduto.setQuantidade(0);
		novoProduto.save();
		if (novoProduto.getTipoProduto().equals(ETipoProduto.MEDICAMENTO)){
			return redirect(routes.GerenciamentoEstoque.indexMedicamento());
		}
		return redirect(routes.GerenciamentoEstoque.index());
	}

	public static Produto baixarEstoque(Long codigoBarras, Integer quantidade) {
		Produto produto = Produto.query().where().eq("codigoBarras", codigoBarras).findUnique();
		produto.setQuantidade(produto.getQuantidade() - quantidade);
		produto.update();
		return produto;
	}

	public static Produto incluirNoEstoque(Long codigoBarras, Integer quantidade) {
		Produto produto = Produto.query().where().eq("codigoBarras", codigoBarras).findUnique();
		produto.setQuantidade(produto.getQuantidade() + quantidade);
		produto.update();
		return produto;
	}
}
