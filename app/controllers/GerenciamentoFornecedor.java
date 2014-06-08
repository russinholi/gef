package controllers;

import java.util.List;

import model.Fornecedor;
import model.Fornecedor;
import model.Usuario;

import com.avaje.ebean.Ebean;

import play.data.Form;
import play.mvc.Controller;

import play.mvc.Result;
import views.html.fornecedor.*;
import views.html.usuario.formularioUsuario;

public class GerenciamentoFornecedor extends Controller {

	public static Result index() {
		List<Fornecedor> listaFornecedores = Ebean.createQuery(Fornecedor.class).findList();
		return ok(index.render(listaFornecedores));
	}

	public static Result formularioNovoFornecedor() {
		Form<Fornecedor> form = Form.form(Fornecedor.class);
		return ok(formularioFornecedor.render(form));

	}

	public static Result persistirFornecedor() {
		Form<Fornecedor> form = Form.form(Fornecedor.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(formularioFornecedor.render(form));
		}
		Fornecedor novoFornecedor = form.get();
		novoFornecedor.setAtivo(true);
		novoFornecedor.save();
		return redirect(routes.GerenciamentoFornecedor.index());
	}

}
