package com.algamoney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.algamoney.api.repository.listener.LancamentoAnexoListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EntityListeners(LancamentoAnexoListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name="lancamento")
public class Lancamento {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	@NotNull
	private String descricao;
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;
	@Column(name="data_pagamento")
	private LocalDate dataPagamento;
	@NotNull
	private BigDecimal valor;
	private String observacao;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipo;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_categoria")
	private Categoria categoria;
	@JsonIgnoreProperties("contatos")
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_pessoa")
	private Pessoa pessoa;
	
	private String anexo;
	
	@Transient
	private String urlAnexo;
	
	@JsonIgnore
	public boolean isReceita() {
		return TipoLancamento.RECEITA.equals(this.tipo);
	}
	
}
