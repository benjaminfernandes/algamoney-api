package com.algamoney.api.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algamoney.api.event.RecursoCriadoEvent;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.repository.filter.LancamentoFilter;
import com.algamoney.api.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	@Autowired
	private LancamentoService lancamentoService;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable){
		return this.lancamentoRepository.filtrar(lancamentoFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscar(@PathVariable Long codigo){
		
		Optional<Lancamento> lancamentoOptional = this.lancamentoRepository.findById(codigo);
		
		return lancamentoOptional.isPresent() ? ResponseEntity.ok(lancamentoOptional.get()) : ResponseEntity.notFound().build();		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Lancamento> salvar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
		Lancamento lancamentoSalvo = this.lancamentoService.salvar(lancamento);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, lancamento.getCodigo(), response));
		return ResponseEntity.ok(lancamentoSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long codigo) {
		this.lancamentoService.deletar(codigo);
	}
	
	
}
