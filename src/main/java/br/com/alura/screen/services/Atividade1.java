package br.com.alura.screen.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.screen.model.Tarefa;

public class Atividade1 {

	public void metodoPrincipal() {
		Scanner valueInput = new Scanner(System.in);
		System.out.println("Digite um número: ");
		String value = valueInput.nextLine();
		this.inicializandoContagem(value);
		System.out.println("--------------------------- Atividade 2 --------------------------");

		this.gerarTarefa();

		System.out.println("--------------------------- Atividade 3 --------------------------");
		this.obterTextoDeUmArquivoJsonConverterParaObjeto();
	}

	private void inicializandoContagem(String value) {
		Integer numero = Integer.valueOf(value);
		int i = 1;
		String contagem = "";
		while (i <= numero) {
			contagem += i + " ";
			i++;
		}
		System.out.println(contagem);
	}

	private void gerarTarefa() {
		try {
			Tarefa tarefa = new Tarefa();
			tarefa.setDescricao("Colocar o lixo na lixeira");
			tarefa.setConcluida(true);
			tarefa.setPessoaResponsavel("Fabrício");

			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(
					new File("/screen/target/tarefa.json"),
					tarefa);

			System.out.println("Arquivo tarefa.json gerado com sucesso na pasta target");

		} catch (IOException e) {
			System.out.println("Ops erro ao gerar o arquivo.");
			throw new RuntimeException(e);
		}
	}

	private void obterTextoDeUmArquivoJsonConverterParaObjeto() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Tarefa tarefa = mapper.readValue(new File("/screen/target/tarefa.json"), Tarefa.class);
			System.out.println(tarefa.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}