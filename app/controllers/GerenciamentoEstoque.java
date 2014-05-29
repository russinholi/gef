package controllers;

import java.util.List;

import model.Produto;

import com.avaje.ebean.Ebean;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.produto.formularioProduto;
import views.html.produto.index;

public class GerenciamentoEstoque extends Controller {

	public static Result index() {
		List<Produto> listaProduto = Ebean.createQuery(Produto.class).findList();
		return ok(index.render(listaProduto));
	}
	
	public static Result formularioNovoProduto() {
		Form<Produto> form = Form.form(Produto.class);
		return ok(formularioProduto.render(form));
	}
	
	public static Result persistirProduto() {
		Form<Produto> form = Form.form(Produto.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(formularioProduto.render(form));
		}
		Produto novoProduto = form.get();
		novoProduto.save();
		return redirect(routes.GerenciamentoEstoque.index());
	}

}
