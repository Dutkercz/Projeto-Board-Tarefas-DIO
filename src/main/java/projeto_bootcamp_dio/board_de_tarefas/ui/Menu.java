package projeto_bootcamp_dio.board_de_tarefas.ui;

import org.springframework.stereotype.Component;
import projeto_bootcamp_dio.board_de_tarefas.service.BoardService;

import java.util.Scanner;

@Component
public class Menu {

    private final BoardService boardService;
    private final BoardManagerUI boardManagerUI;

    public Menu(BoardService boardService, BoardManagerUI boardManagerUI) {
        this.boardService = boardService;
        this.boardManagerUI = boardManagerUI;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);

        int select;

        do {
            System.out.println("\n== BEM VINDO AO SISTEMA DE BOARDS ==\n");
            String options = """
                    Selecione uma das opções do menu
                    1 - CRIAR NOVO BOARD / CREATE NEW BOARD
                    2 - SELECIONAR BOARD / SELECT BOARD
                    3 - EXCLUIR BOARD / DELETE BOARD
                    4 - LISTAR TODOS OS BOARDS / SHOW ALL BOARDS
                    5 - SAIR
                    >>>\s""";

            System.out.print(options);
            select = scanner.nextInt();
            scanner.nextLine();

            switch (select) {
                case 1 -> boardManagerUI.createBoard();
                case 2 -> boardManagerUI.selectBoard();
                case 3 -> boardManagerUI.deleteBoard();
                case 4 -> boardManagerUI.boardList();
                case 5 -> System.out.println("XXX Saindo... XXX");
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (select != 5);

        scanner.close();
    }
}
