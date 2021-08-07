package com.algamoney.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "estado")
public class Estado {

	@EqualsAndHashCode.Include
	@Id
	private Long codigo;
	
	private String nome;
}
