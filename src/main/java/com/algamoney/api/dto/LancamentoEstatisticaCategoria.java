package com.algamoney.api.dto;

import java.math.BigDecimal;

import com.algamoney.api.model.Categoria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LancamentoEstatisticaCategoria {
	
	public LancamentoEstatisticaCategoria(Categoria categoria, BigDecimal total) {
		this.categoria = categoria;
		this.total = total;
	}
	
	private Categoria categoria;
	private BigDecimal total;
}
