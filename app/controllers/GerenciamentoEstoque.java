package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class GerenciamentoEstoque extends Controller {

	public static Result index() {
		return ok(index.render("Teste"));
	}

}
