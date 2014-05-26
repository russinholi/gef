package controllers;

import static play.data.Form.*;


import java.util.ArrayList;

import java.util.List;

import com.avaje.ebean.Ebean;

import model.Cliente;
import model.Fornecedor;
import model.Usuario;
import play.data.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.cliente.*;
import views.html.usuario.formularioUsuario;

public class GerenciamentoCliente extends Controller {

	public static Result index() {
		List<Cliente> listaCliente = Ebean.createQuery(Cliente.class).findList();
		return ok(index.render(listaCliente));
	}
	
	public static Result formularioNovoCliente() {
		Form<Cliente> form = Form.form(Cliente.class);
		return ok(formularioCliente.render(form));
	}
	
	public static Result persistirCliente() {
		Form<Cliente> form = Form.form(Cliente.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(formularioCliente.render(form));
		}
		Cliente novoCliente = form.get();
		novoCliente.save();
		return redirect(routes.GerenciamentoCliente.index());
	}

}
