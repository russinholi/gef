package controllers;

import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.Ebean;

import model.Usuario;
import play.data.Form;
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
	
	public static Result novoUsuario() {
		Form<Usuario> form = Form.form(Usuario.class).bindFromRequest();
		  if (form.hasErrors()) {
		    return badRequest(formularioUsuario.render(form));
		  }
		  Usuario novoUsuario = form.get();
		  novoUsuario.save();
		  return redirect(routes.GerenciamentoUsuario.index());
	}
	
}
