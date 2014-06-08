package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Fornecedor;
import model.ETipoOperacao;
import model.ItemNotaFiscal;
import model.NotaFiscal;
import model.Produto;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import controllers.GerenciamentoCompra.FormularioCompra;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.compra.*;

public class GerenciamentoCompra extends Controller {

	public static Result index() {
		List<NotaFiscal> notasCompra = NotaFiscal.query().where().eq("tipo", ETipoOperacao.VENDA).findList();
		return ok(index.render(notasCompra));
	}

	public static Result formularioNovaCompra() {
		Application.refreshSessaoUsuario();
		Form<FormularioCompra> formulario = Form.form(FormularioCompra.class);
		return ok(formularioCompra.render(formulario, new FormularioCompra()));
	}

	public static Result buscarProduto() {
		Form<FormularioCompra> formulario = Form.form(FormularioCompra.class).bindFromRequest();
		FormularioCompra dto = formulario.get();
		String nomeProduto = "";
		Double precoProduto = 0.0;
		if (dto.produto != null) {
			Produto produto = Produto.query().where().eq("codigoBarras", dto.produto).findUnique();
			if (produto != null) {
				nomeProduto = produto.getNome();
				precoProduto = produto.getPreco();
			}
		}
		ObjectNode jsonObject = Json.newObject();
		jsonObject.put("nomeProduto", nomeProduto);
		jsonObject.put("precoProduto", precoProduto);
		
		return ok(jsonObject);
	}

	public static Result buscarFornecedor() {		
		Form<FormularioCompra> formulario = Form.form(FormularioCompra.class).bindFromRequest();
		FormularioCompra dto = formulario.get();
		String retorno = ""; 
		if (dto.cnpj != null) {
			Fornecedor fornecedor = Fornecedor.query().where().eq("cnpj", dto.cnpj).findUnique();
			if (fornecedor != null) {
				retorno = fornecedor.getNome();
			}
		}
		
		return ok(retorno);
	}

	public static Result persistirNotaFiscal() {
		Form<FormularioCompra> formulario = Form.form(FormularioCompra.class).bindFromRequest();
		FormularioCompra dto = criarOuObterDTOCompraSessao();
		Fornecedor cliente = Fornecedor.query().where().eq("cnpj", dto.cnpj).findUnique();
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setPessoa(cliente);
		notaFiscal.setTipo(ETipoOperacao.COMPRA);
		notaFiscal.setData(new Date());
		for (FormularioCompra item : dto.itens){
			ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal();
			Produto produto = GerenciamentoEstoque.incluirNoEstoque(item.produto, item.quantidade);
			itemNotaFiscal.setProduto(produto);
			itemNotaFiscal.setQuantidade(item.quantidade);
			itemNotaFiscal.setValor(item.precoTotal);
			notaFiscal.addItem(itemNotaFiscal);
		}
		
		notaFiscal.save();
		
		flash("sucesso","Compra efetuada com Sucesso! Número da Compra: "+notaFiscal.getNumero());
		return ok(formularioCompra.render(formulario, dto));
	}
	
	public static Result removerItem(Long produto) {
		Form<FormularioCompra> formulario = Form.form(FormularioCompra.class).bindFromRequest();
		if (formulario.hasErrors()) {
			return badRequest(formularioCompra.render(formulario, new FormularioCompra()));
		}

		FormularioCompra item = formulario.get();
		
		FormularioCompra dto = criarOuObterDTOCompraSessao();
		dto.itens = removerItemDoDTO(produto, dto.itens);
		gravarDTOCompraSessao(dto);
		return ok(itens.render(dto.itens));
	}

	private static List<FormularioCompra> removerItemDoDTO(Long produto, List<FormularioCompra> itens) {
		List<FormularioCompra> listaItens = new ArrayList<FormularioCompra> (itens);
		for (FormularioCompra item : itens) {
			if (produto.equals(item.produto)){
				listaItens.remove(item);
			}
		}
		return listaItens;
	}

	public static Result adicionarItem() {
		Form<FormularioCompra> formulario = Form.form(FormularioCompra.class).bindFromRequest();
		if (formulario.hasErrors()) {
			return badRequest(formularioCompra.render(formulario, new FormularioCompra()));
		}

		FormularioCompra item = formulario.get();
		FormularioCompra dto = criarOuObterDTOCompraSessao();
		dto.cnpj = item.cnpj;
		if (item.produto != null) {
			dto.itens.add(item);
			gravarDTOCompraSessao(dto);
		}
		formulario = Form.form(FormularioCompra.class);
		return ok(itens.render(dto.itens));
	}

	private static void gravarDTOCompraSessao(FormularioCompra dto) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			session("compra", mapper.writeValueAsString(dto));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	private static FormularioCompra criarOuObterDTOCompraSessao() {
		FormularioCompra dto = new FormularioCompra();
		try {
			ObjectMapper mapper = new ObjectMapper();
			String stringJson = session("compra");
			if (stringJson != null) {
				dto = mapper.readValue(stringJson, FormularioCompra.class);
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
	
	public static class FormularioCompra {
		public String cnpj;
		public Long produto;
		public String nome;
		public Double precoUnitario;
		public Integer quantidade ;
		public Double desconto;
		public Double precoTotal;
		
		public List<FormularioCompra> itens = new ArrayList<FormularioCompra>();
	}

}
