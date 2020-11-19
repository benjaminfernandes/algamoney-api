package com.algamoney.api.exception;

public class LancamentoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 2190793104528356297L;
	
	public LancamentoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public LancamentoNaoEncontradoException(Long codigo) {
		super(String.format("Não foi possível encontrar um lançamento com o código %s", codigo));
	}

}
