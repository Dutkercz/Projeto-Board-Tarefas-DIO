package projeto_bootcamp_dio.board_de_tarefas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import projeto_bootcamp_dio.board_de_tarefas.service.BoardService;
import projeto_bootcamp_dio.board_de_tarefas.ui.Menu;

@SpringBootApplication
public class BoardDeTarefasApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(BoardDeTarefasApplication.class, args);
	}

	public BoardDeTarefasApplication(Menu menu) {
		this.menu = menu;
	}

	private final Menu menu;

	@Override
	public void run(String... args) throws Exception {
		menu.showMenu();
	}
}
