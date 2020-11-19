package com.algamoney.api.exception;

public class PessoaInexistenteOuInativaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public PessoaInexistenteOuInativaException(String msg) {
		super(msg);
	}
	
	public PessoaInexistenteOuInativaException(Long codigo) {
		super(String.format("Pessoa inexistente ou inativa para incluir no lançamento", codigo));
	}
	
	public PessoaInexistenteOuInativaException() {
		super(String.format("Pessoa inexistente ou inativa para incluir no lançamento"));
	}

}
