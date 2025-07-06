package projeto_bootcamp_dio.board_de_tarefas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import projeto_bootcamp_dio.board_de_tarefas.ui.Menu;

@SpringBootApplication
public class BoardDeTarefasApplication implements CommandLineRunner {
    private final Menu menu;

    public BoardDeTarefasApplication(Menu menu) {
        this.menu = menu;
    }

    public static void main(String[] args) {
        SpringApplication.run(BoardDeTarefasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        menu.showMenu();
    }
}
