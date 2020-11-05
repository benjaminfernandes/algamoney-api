package com.algamoney.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algamoney.api.event.RecursoCriadoEvent;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent recursoCriadoevent) {
		Long codigo = recursoCriadoevent.getCodigo();
		adicionarHeaderLocation(recursoCriadoevent, codigo);
	}

	private void adicionarHeaderLocation(RecursoCriadoEvent recursoCriadoevent, Long codigo) {
		HttpServletResponse response = recursoCriadoevent.getResponse();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(codigo).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
	
}
