package controllers;

import static play.data.Form.*;
import java.util.ArrayList;

import java.util.List;

import com.avaje.ebean.Ebean;

import model.Usuario;
import play.data.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.usuario.*;

public class GerenciamentoUsuario extends Controller {

	public static Result index() {
		List<Usuario> listaUsuarios = Ebean.createQuery(Usuario.class).findList();
		return ok(index.render(listaUsuarios));
	}

	public static Result formularioNovoUsuario() {
		Form<Usuario> form = Form.form(Usuario.class);
		return ok(formularioUsuario.render(form));

	}

	public static Result persistirUsuario() {
		Form<Usuario> form = Form.form(Usuario.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(formularioUsuario.render(form));
		}
		Usuario novoUsuario = form.get();
		novoUsuario.save();
		return redirect(routes.GerenciamentoUsuario.index());
	}

	public static Result login() {		 
		return ok(login.render(form(Usuario.class)));
	}


	public static Result efetuarLogin() {
		Form<Usuario> form = Form.form(Usuario.class).bindFromRequest();
		System.out.println("1");
		if (form.hasErrors()) {
			return badRequest(login.render(form));
		} else {
			System.out.println("2");
		Usuario usuario = form.get();
		Usuario usuarioLogado = Ebean.find(Usuario.class).where().eq("login", usuario.getLogin()).eq("senha",usuario.getSenha()).findUnique();
		session().clear();
		session("login",usuarioLogado.getLogin());
		session("nomeUsuario", usuarioLogado.getNome());
		return redirect(routes.Application.index());
		}
	}
}
