function buscarProduto() {
    jsRoutes.controllers.GerenciamentoVenda.buscarProduto().ajax({
        type: "POST",
        data: $("#formularioVenda").serialize(),
        success: function(response) {
        	$("#nome").val(response);
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

function adicionaItem() {
	jsRoutes.controllers.GerenciamentoVenda.adicionaItem().ajax({
		success: function(response) {
			$("#itens").html(response);
		},
		error: function(response) {         
		}
	});
}
