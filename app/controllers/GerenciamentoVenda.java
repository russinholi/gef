package controllers;

import java.io.IOException;
import java.util.ArrayList;


import java.util.List;

import model.ETipoOperacao;
import model.NotaFiscal;
import model.Produto;
import play.Routes;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.venda.*;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GerenciamentoVenda extends Controller {

	public static Result index() {
		List<NotaFiscal> notasVenda = Ebean.find(NotaFiscal.class).where().eq("tipo", ETipoOperacao.VENDA).findList();
		return ok(index.render(notasVenda));
	}

	public static Result formularioNovaVenda() {
		Form<FormularioVenda> formulario = Form.form(FormularioVenda.class);
		return ok(formularioVenda.render(formulario, new FormularioVenda()));
	}

	public static Result buscarProduto() {
		Form<FormularioVenda> formulario = Form.form(FormularioVenda.class).bindFromRequest();
		FormularioVenda dto = formulario.get();
		String retorno = ""; 
		if (dto.produto != null) {
			Produto produto = Ebean.createQuery(Produto.class).where().eq("codigoBarras", dto.produto).findUnique();
			if (produto != null) {
				retorno = produto.getNome();
			}
		}
		
		return ok(retorno);
	}
	
	public static Result persistirNotaFiscal() {
		return ok(index.render(null));
	}
	
	public static Result removerItem(Long produto) {
		Form<FormularioVenda> formulario = Form.form(FormularioVenda.class).bindFromRequest();
		if (formulario.hasErrors()) {
			return badRequest(formularioVenda.render(formulario, new FormularioVenda()));
		}

		FormularioVenda item = formulario.get();
		
		FormularioVenda dto = criarOuObterDTOVendaSessao();
		dto.itens = removerItemDoDTO(produto, dto.itens);
		gravarDTOVendaSessao(dto);
		return ok(itens.render(dto.itens));
	}

	private static List<FormularioVenda> removerItemDoDTO(Long produto, List<FormularioVenda> itens) {
		List<FormularioVenda> listaItens = new ArrayList<FormularioVenda> (itens);
		for (FormularioVenda item : itens) {
			if (produto.equals(item.produto)){
				listaItens.remove(item);
			}
		}
		return listaItens;
	}

	public static Result adicionarItem() {
		Form<FormularioVenda> formulario = Form.form(FormularioVenda.class).bindFromRequest();
		if (formulario.hasErrors()) {
			return badRequest(formularioVenda.render(formulario, new FormularioVenda()));
		}

		FormularioVenda item = formulario.get();
		FormularioVenda dto = criarOuObterDTOVendaSessao();
		dto.cpf = item.cpf;
		dto.itens.add(item);
		gravarDTOVendaSessao(dto);
		formulario = Form.form(FormularioVenda.class);
		return ok(formularioVenda.render(formulario.fill(dto), dto));
	}

	private static void gravarDTOVendaSessao(FormularioVenda dto) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			session("venda", mapper.writeValueAsString(dto));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	private static FormularioVenda criarOuObterDTOVendaSessao() {
		FormularioVenda dto = new FormularioVenda();
		try {
			ObjectMapper mapper = new ObjectMapper();
			String stringJson = session("venda");
			if (stringJson != null) {
				System.out.println(stringJson);
				dto = mapper.readValue(stringJson, FormularioVenda.class);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public static class FormularioVenda {
		public String cpf;
		public Long produto;
		public String nome;
		public Double precoUnitario;
		public Integer quantidade ;
		public Double desconto;
		public Double precoTotal;
		
		public List<FormularioVenda> itens = new ArrayList<FormularioVenda>();
	}
}
