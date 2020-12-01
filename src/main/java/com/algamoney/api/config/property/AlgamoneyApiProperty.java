package com.algamoney.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {
	
	private String originPermitida = "http://localhost:8000";
	private final Seguranca seguranca = new Seguranca();
	
	@Getter
	@Setter
	public static class Seguranca{
		private boolean enableHttps;
	}
	
}
