package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	if (session().get("login") == null) {
    		return redirect(routes.GerenciamentoUsuario.login());
    	}

        return ok(index.render("Your new application is ready."));
    }

	public static Result javascriptRoutes() {       
	    response().setContentType("text/javascript");
	    return ok(Routes.javascriptRouter("jsRoutes",
               controllers.routes.javascript.GerenciamentoVenda.buscarProduto(),
               controllers.routes.javascript.GerenciamentoVenda.buscarCliente(),
               controllers.routes.javascript.GerenciamentoVenda.removerItem(),
               controllers.routes.javascript.GerenciamentoVenda.adicionarItem()
	            ));
	}
	
	public static void refreshSessaoUsuario() {
		String login = session().get("login");
		String nomeUsuario = session().get("nomeUsuario");
		session().clear();
    	if (login != null) {
    		session("login",login);
    		session("nomeUsuario", nomeUsuario);
    	}
	}

}
