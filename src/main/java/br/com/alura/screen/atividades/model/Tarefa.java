package br.com.alura.screen.atividades.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Tarefa {
	
	@JsonProperty("descricao")
	private String descricao;
	@JsonProperty("concluida")
	private boolean concluida;
	@JsonProperty("pessoaResponsavel")
	private String pessoaResponsavel;

}
