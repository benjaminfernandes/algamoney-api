package com.algamoney.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cidade")
public class Cidade {

	@EqualsAndHashCode.Include
	@Id
	private Long codigo;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="codigo_estado")
	private Estado estado;
}
