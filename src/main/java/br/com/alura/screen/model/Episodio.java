package br.com.alura.screen.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {

	private Integer temporada;
	private String titulo;
	private Integer numeroEpisodio;
	private Double avaliacao;
	private LocalDate dataLancameto;

	public Episodio(Integer numeroTemporada, DadosEpisodio dadosEpisodio) {
		this.temporada = numeroTemporada;
		this.titulo = dadosEpisodio.titulo();
		this.numeroEpisodio = dadosEpisodio.numero();

		try {
			this.avaliacao = Double.valueOf(dadosEpisodio.avaliacao());
		} catch (NumberFormatException e) {
			this.avaliacao = 0.0;
		}

		try {
			this.dataLancameto = LocalDate.parse(dadosEpisodio.dataLancamento());
		} catch (DateTimeParseException e) {
			this.dataLancameto = null;
		}
	}

	public Integer getTemporada() {
		return temporada;
	}

	public void setTemporada(Integer temporada) {
		this.temporada = temporada;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getNumeroEpisodio() {
		return numeroEpisodio;
	}

	public void setNumeroEpisodio(Integer numeroEpisodio) {
		this.numeroEpisodio = numeroEpisodio;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public LocalDate getDataLancameto() {
		return dataLancameto;
	}

	public void setDataLancameto(LocalDate dataLancameto) {
		this.dataLancameto = dataLancameto;
	}

	@Override
	public String toString() {
		return "temporada=" + temporada + ", titulo=" + titulo + ", numeroEpisodio=" + numeroEpisodio + ", avaliacao="
				+ avaliacao + ", dataLancameto=" + dataLancameto;
	}

}
