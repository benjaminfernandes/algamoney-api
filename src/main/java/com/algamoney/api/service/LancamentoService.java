package com.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algamoney.api.exception.LancamentoNaoEncontradoException;
import com.algamoney.api.exception.PessoaInexistenteOuInativaException;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.repository.PessoaRepository;

@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		Optional<Pessoa> pessoa = this.pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		if(pessoa.isEmpty() || pessoa.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return this.lancamentoRepository.save(lancamento);
	}
	
	@Transactional
	public void deletar(Long codigo) {
		this.lancamentoRepository.findById(codigo).orElseThrow(() -> new LancamentoNaoEncontradoException(codigo));
		
		this.lancamentoRepository.deleteById(codigo);
	}
	
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
		if (!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())) {
			validarPessoa(lancamento);
		}

		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");

		return lancamentoRepository.save(lancamentoSalvo);
	}
	
	private void validarPessoa(Lancamento lancamento) {
		Pessoa pessoa = null;
		if (lancamento.getPessoa().getCodigo() != null) {
			pessoa = pessoaRepository.getOne(lancamento.getPessoa().getCodigo());
		}

		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}
	
	private Lancamento buscarLancamentoExistente(Long codigo) {
		Lancamento lancamentoSalvo = lancamentoRepository.getOne(codigo);
		if (lancamentoSalvo == null) {
			throw new IllegalArgumentException();
		}
		return lancamentoSalvo;
	}
}
