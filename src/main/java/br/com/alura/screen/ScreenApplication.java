package br.com.alura.screen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screen.principal.MenuPrincipal;

@SpringBootApplication
public class ScreenApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MenuPrincipal principal = new MenuPrincipal();
		principal.exibirMenu();	
	}

}
