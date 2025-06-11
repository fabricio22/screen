package br.com.alura.screen.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.alura.screen.model.DadosEpisodio;
import br.com.alura.screen.model.DadosSerie;
import br.com.alura.screen.model.DadosTemporada;
import br.com.alura.screen.services.ConsumoAPI;
import br.com.alura.screen.services.ConverterDados;

public class MenuPrincipal {
	
	private Scanner scanner = new Scanner(System.in);
	private final String ENDERECO = "https://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=6585022c";
	private ConsumoAPI consumoApi = new ConsumoAPI();
	private ConverterDados conversor = new ConverterDados();
	List<DadosTemporada> temporadas = new ArrayList<>();
	
	
	public void exibirMenu() {
		System.out.println("Digite o nome da série para busca:");
		var nomeSerie = scanner.nextLine();
		var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		
		
		for(int i = 1; i <= dados.totalTemporada(); i++) {
			json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		
		temporadas.forEach(System.out::println);
		
//		for(int i = 0; i < dados.totalTemporada(); i++) {
//			List<DadosEpisodio> episodiosTemporadas = temporadas.get(i).episodios();
//			for(int j = 0; j < episodiosTemporadas.size(); j++) {
//				System.out.println(episodiosTemporadas.get(j).titulo());
//			}
//		}
//		
		temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
		
		
		
	}

}
