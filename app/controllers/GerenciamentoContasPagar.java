package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class GerenciamentoContasPagar extends Controller {

	public static Result index() {
		return ok(index.render("Teste"));
	}

}
