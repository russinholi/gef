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

}
