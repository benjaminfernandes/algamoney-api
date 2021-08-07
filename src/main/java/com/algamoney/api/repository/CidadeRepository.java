package com.algamoney.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoney.api.model.Cidade;
import com.algamoney.api.model.Estado;

public interface CidadeRepository extends JpaRepository<Estado, Long> {

	List<Cidade> findByEstadoCodigo(Long estadoCodigo);
}
