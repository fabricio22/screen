package br.com.alura.screen.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo, 
		@JsonAlias("totalSeasons") Integer TotalTemporada, 
		@JsonAlias("imdbRating") String avaliacao) {

}
