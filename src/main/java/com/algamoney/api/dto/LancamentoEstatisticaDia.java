package com.algamoney.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.algamoney.api.model.TipoLancamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LancamentoEstatisticaDia {
	
	public LancamentoEstatisticaDia(TipoLancamento tipo, LocalDate dia, BigDecimal total) {
		this.tipo = tipo;
		this.dia = dia;
		this.total = total;
	}
	
	private TipoLancamento tipo;
	private LocalDate dia;
	private BigDecimal total;
	
	
}
