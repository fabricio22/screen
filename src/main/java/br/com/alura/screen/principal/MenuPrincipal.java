package br.com.alura.screen.principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.alura.screen.model.DadosEpisodio;
import br.com.alura.screen.model.DadosSerie;
import br.com.alura.screen.model.DadosTemporada;
import br.com.alura.screen.model.Episodio;
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

		for (int i = 1; i <= dados.totalTemporada(); i++) {
			json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}

		temporadas.forEach(System.out::println);

		temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

		List<DadosEpisodio> dadosEpisodios = temporadas.stream().flatMap(t -> t.episodios().stream())
				.collect(Collectors.toList());

		System.out.println("Lista de episodios de todas as temporadas.");
		System.out.println(dadosEpisodios);

		System.out.println("Os 5 episodios mais bem avaliado da temporada");
		dadosEpisodios.stream().filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
				.sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed()).limit(5)
				.forEach(System.out::println);

		List<Episodio> episodios = temporadas.stream()
				.flatMap(t -> t.episodios().stream().map(e -> new Episodio(t.numero(), e)))
				.collect(Collectors.toList());
		
		episodios.forEach(System.out::println);
		
		System.out.println("A partir de que ano você deseja ver os episódios?");
		var ano = scanner.nextInt();
		scanner.nextLine();
		
		LocalDate dataBusca = LocalDate.of(ano, 1, 1);
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		episodios.stream()
		.filter(f -> f.getDataLancameto() != null && f.getDataLancameto().isAfter(dataBusca))
		.forEach(e -> System.out.println(
				"Temporada: " + e.getTemporada() +
				  " Episódio: " + e.getTitulo() + 
				    " Data Lançamento: " + e.getDataLancameto().format(formatador)
				));
		
		Map<Integer, Double> avaliacaoesPorTemporada = episodios.stream()
				.filter(e -> e.getAvaliacao() > 0.0)
				.collect(Collectors.groupingBy(Episodio::getTemporada, Collectors.averagingDouble(Episodio::getAvaliacao)));
		System.out.println(avaliacaoesPorTemporada);
		
		DoubleSummaryStatistics est = episodios.stream()
				.filter(e -> e.getAvaliacao() > 0.00)
				.collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
		
		System.out.println("Média: " + est.getAverage());
		System.out.println("Melhor episódio: " +  est.getMax());
		System.out.println("Pior episódio: " + est.getMin());
		System.out.println("Quantidade: " + est.getCount());

	}

}
