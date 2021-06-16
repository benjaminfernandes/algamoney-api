package com.algamoney.api.model;

import lombok.Getter;

@Getter
public enum TipoLancamento {

	RECEITA("Receita"), DESPESA("Despesa");
	
	private TipoLancamento(String descricao) {
		this.descricao = descricao;
	}

	private final String descricao;
	
}
