function buscarProduto() {
    jsRoutes.controllers.GerenciamentoVenda.buscarProduto().ajax({
        type: "POST",
        data: $("#formularioVenda").serialize(),
        success: function(response) {
        	$("#nome").val(response.nomeProduto);
        	$("#precoUnitario").val(response.precoProduto);
        },
        error: function(response) {         
        }
    });
}

function buscarCliente() {
    jsRoutes.controllers.GerenciamentoVenda.buscarCliente().ajax({
        type: "POST",
        data: $("#formularioVenda").serialize(),
        success: function(response) {
        	$("#nomeCliente").html(response);
        },
        error: function(response) {         
        }
    });
}

function removerItem(produto) {
	jsRoutes.controllers.GerenciamentoVenda.removerItem(produto).ajax({
		success: function(response) {
			$("#itens").html(response);
		},
		error: function(response) {         
		}
	});
}

function adicionarItem() {
	jsRoutes.controllers.GerenciamentoVenda.adicionarItem().ajax({
        type: "POST",
        data: $("#formularioVenda").serialize(),
		success: function(response) {
			$("#itens").html(response);
		},
		error: function(response) {         
		}
	});
}

function calcularPrecoTotal() {
	var precoUnitario = $("#precoUnitario").val();
	var desconto = 1- ($("#desconto").val()/100);
	var quantidade = $("#quantidade").val();
	$("#precoTotal").val(precoUnitario * desconto * quantidade);
}

function buscarProdutoCompra() {
    jsRoutes.controllers.GerenciamentoCompra.buscarProduto().ajax({
        type: "POST",
        data: $("#formularioCompra").serialize(),
        success: function(response) {
        	$("#nome").val(response.nomeProduto);
        	$("#precoUnitario").val(response.precoProduto);
        },
        error: function(response) {         
        }
    });
}

function buscarFornecedor() {
    jsRoutes.controllers.GerenciamentoCompra.buscarFornecedor().ajax({
        type: "POST",
        data: $("#formularioCompra").serialize(),
        success: function(response) {
        	$("#nomeFornecedor").html(response);
        },
        error: function(response) {
        }
    });
}

function removerItemCompra(produto) {
	jsRoutes.controllers.GerenciamentoCompra.removerItem(produto).ajax({
		success: function(response) {
			$("#itens").html(response);
		},
		error: function(response) {         
		}
	});
}

function adicionarItemCompra() {
	jsRoutes.controllers.GerenciamentoCompra.adicionarItem().ajax({
        type: "POST",
        data: $("#formularioCompra").serialize(),
		success: function(response) {
			$("#itens").html(response);
		},
		error: function(response) {         
		}
	});
}
