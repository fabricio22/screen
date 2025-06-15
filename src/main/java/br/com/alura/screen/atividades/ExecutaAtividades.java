package br.com.alura.screen.atividades;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.alura.screen.atividades.model.Produto;
import br.com.alura.screen.atividades.services.Calculadora;
import br.com.alura.screen.atividades.services.Divisor;
import br.com.alura.screen.atividades.services.ManipulacaoTexto;
import br.com.alura.screen.atividades.services.Operacao;
import br.com.alura.screen.atividades.services.Palindromo;

public class ExecutaAtividades {

	public static void main(String[] args) {
		Calculadora calculadora = new Calculadora() {

			@Override
			public int multiplicacao(int a, int b) {
				return a * b;
			}
		};

		System.out.println(calculadora.multiplicacao(5, 2));

		Calculadora calcula = (a, b) -> a * b;
		System.out.println("7 * 6 = " + calcula.multiplicacao(7, 6));

		Operacao numeroPrimo = (valor) -> ((valor > 1) && ((valor == 2) || (valor % 2) > 0));
		System.out.println("O numero 99 informado seria um numero Primo? " + numeroPrimo.verificaNumeroPrimo(99));

		ManipulacaoTexto texto = (txt) -> txt.toUpperCase();
		System.out.println(texto.deixarTextoEmCaixaAlta("vou treinar agora."));

		Palindromo palindromo = (poli) -> (poli.equalsIgnoreCase(new StringBuilder(poli).reverse().toString()));
		System.out.println("O valor 1001 é um palindromo? " + palindromo.isPalindormo("1001"));

		List<Integer> listaInteiros = Arrays.asList(1, 2, 3, 4, 5);
		listaInteiros.replaceAll(l -> l * 3);
		System.out.println(listaInteiros);

		List<String> nomes = Arrays.asList("Joao", "Chico", "Salvador", "Lucas");
		nomes.sort((a, b) -> a.compareTo(b));
		System.out.println(nomes);

		Divisor divisor = (a, b) -> {
			if (b == 0) {
				throw new ArithmeticException("não é permitido dividir por zero.");
			} else {
				return a / b;
			}
		};
		
		try {
		System.out.println(divisor.dividir(2, 0));
		} catch(ArithmeticException e) {
			System.out.println(e.getMessage());
		}
		
		List<Integer> listaInteiro = Arrays.asList(1, 2, 3, 4, 5, 6);
		listaInteiro = listaInteiro.stream().filter(f -> (f % 2 == 0)).collect(Collectors.toList());
		System.out.println(listaInteiro);
		
		List<String> listaNomes = Arrays.asList("Jose", "Moises", "Kaleb", "Benjamin");
		listaNomes.replaceAll(n -> n.toUpperCase());
		System.out.println(listaNomes);
		
		List<Integer> listaInteiro2 = Arrays.asList(1, 2, 3, 4, 5, 6);
		List<Integer> novaLista = listaInteiro2.stream()
				.filter(i -> (i % 2 != 0))
		          .map(n -> n * 2).toList();
		
		System.out.println(novaLista);
		
		List<String> palavras = Arrays.asList("apple", "banana", "apple", "orange", "banana");
		palavras = palavras.stream().distinct().collect(Collectors.toList());
		System.out.println(palavras);
		
		List<List<Integer>> listaDeNumeros = Arrays.asList(
	            Arrays.asList(1, 2, 3, 4),
	            Arrays.asList(5, 6, 7, 8),
	            Arrays.asList(9, 10, 11, 12)
	        );
		
		System.out.println(listaDeNumeros);
		List<Integer> numerosPrimos = listaDeNumeros.stream()
				.flatMap(List::stream)
				.filter(ExecutaAtividades::ehPrimo)
				.sorted().collect(Collectors.toList());
		
	   System.out.println(numerosPrimos);
	   
	   List<Integer> listaInteirosEst = Arrays.asList(10, 20, 30, 40 , 50);
	   
	   Optional<Integer> maxValue = listaInteirosEst.stream().max(Integer::compare);
	   maxValue.ifPresent(System.out::println);
	   
	   List<String> palavrasLengh = Arrays.asList("java", "stream", "lambda", "code");
	   Map<Integer, List<String>> agrupamentoPorTamanho = palavrasLengh.stream()
			   .collect(Collectors.groupingBy(String::length));
	   
	   System.out.println(agrupamentoPorTamanho);
	   
	   List<String> nomesConcat = Arrays.asList("Alice", "Bob", "Charlie");
	   String newNomes = nomesConcat.stream().collect(Collectors.joining(", "));
	   System.out.println(newNomes);
	   
	   List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
	   Integer somaDosQuadrados = numeros.stream()
			   .filter(n -> n % 2 == 0)
			   .map(n -> n * n)
			   .reduce(0, Integer::sum);
	   System.out.println(somaDosQuadrados);
	   
	   List<Integer> numerosParesImpares = Arrays.asList(1, 2, 3, 4, 5, 6);
	   Map<Boolean, List<Integer>> mapSplit = numerosParesImpares.stream()
			   .collect(Collectors.partitioningBy(n -> n % 2 == 0));
	   
	   System.out.println("Pares: " + mapSplit.get(true));
	   System.out.println("Impares: " + mapSplit.get(false));
	   
	   List<Produto> produtos = Arrays.asList(
	            new Produto("Smartphone", 800.0, "Eletrônicos"),
	            new Produto("Notebook", 1500.0, "Eletrônicos"),
	            new Produto("Teclado", 200.0, "Eletrônicos"),
	            new Produto("Cadeira", 300.0, "Móveis"),
	            new Produto("Monitor", 900.0, "Eletrônicos"),
	            new Produto("Mesa", 700.0, "Móveis"),
	            new Produto("Caneta", 5.0, "Papelaria")
	        );
	   
	   List<Produto> novaListaProduto = produtos.stream()
	   .filter(p -> p.getCategoria().contains("Eletrônicos") && p.getPreco() < 1000.0)
	   .sorted(Comparator.comparing(Produto::getPreco))
	   .collect(Collectors.toList());
	   
	   System.out.println(novaListaProduto);
	   
	   Map<String, List<Produto>> produtosPorCategoria = produtos.stream()
	            .collect(Collectors.groupingBy(Produto::getCategoria));
	        System.out.println(produtosPorCategoria);
	        
	  Map<String, Long> quantidaProdustosPorCategoria = produtos.stream()
			  .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));
	  
	  System.out.println(quantidaProdustosPorCategoria);
	  
	  Map<String, Optional<Produto>> produtoMaisCaroPorCategoria = produtos.stream()
			  .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.maxBy(Comparator.comparingDouble(Produto::getPreco))));

	  System.out.println(produtoMaisCaroPorCategoria);
	   
	  Map<String, Double> totalPrecoDeProdutoPorCategoria = produtos.stream()
			  .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.summingDouble(Produto::getPreco)));
	  
	  System.out.println(totalPrecoDeProdutoPorCategoria);

	}
	
	private static boolean ehPrimo(int numero) {
        if (numero < 2) return false; // Números menores que 2 não são primos
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false; // Divisível por outro número que não 1 e ele mesmo
            }
        }
        return true;
    }
}
