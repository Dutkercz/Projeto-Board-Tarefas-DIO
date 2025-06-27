package projeto_bootcamp_dio.board_de_tarefas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import projeto_bootcamp_dio.board_de_tarefas.ui.Menu;

@SpringBootApplication
public class BoardDeTarefasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BoardDeTarefasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu.showMenu();
	}
}
