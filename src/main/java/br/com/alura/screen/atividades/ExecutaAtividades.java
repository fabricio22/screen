package br.com.alura.screen.atividades;

import java.util.Arrays;
import java.util.List;

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
	}

}
