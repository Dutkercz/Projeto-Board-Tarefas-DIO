package projeto_bootcamp_dio.board_de_tarefas.ui;

import org.springframework.stereotype.Component;
import projeto_bootcamp_dio.board_de_tarefas.entities.Board;
import projeto_bootcamp_dio.board_de_tarefas.entities.BoardColumn;
import projeto_bootcamp_dio.board_de_tarefas.enums.BoardColumnEnum;
import projeto_bootcamp_dio.board_de_tarefas.service.BoardColumnService;
import projeto_bootcamp_dio.board_de_tarefas.service.BoardService;

import java.util.Scanner;

@Component
public class Menu {

    private final BoardService boardService;
    private final BoardColumnService boardColumnService;

    public Menu(BoardService boardService, BoardColumnService boardColumnService) {
        this.boardService = boardService;
        this.boardColumnService = boardColumnService;
    }

    public  void showMenu(){
        Scanner scanner = new Scanner(System.in);
        int select;

        Board board = new Board();

        do {
            System.out.println("Este é o menu");
            String options = """
                    Selecione uma das opções do menu
                    1 - Option 1
                    2 - Option 2
                    3 - Option 3
                    4 - SAIR
                    """;
            System.out.println(options);

            select = scanner.nextInt();

            switch (select) {
                case 1:
                    scanner.nextLine();
                    System.out.println("CRIAR BOARD");

                    System.out.print("Digite o nome do Board ");
                    String boardName = scanner.nextLine();
                    board.setId(null);
                    board.setName(boardName);
                    board = boardService.insert(board);
                    break;

                case 2:
                    scanner.nextLine();

                    System.out.println(board.getName() + board.getId());
                    System.out.println("===================");
                    System.out.println("CRIAR BOARDCOLUMN");
                    System.out.print("Digite o nome da Board Column ");
                    String boardColumnName = scanner.nextLine();
                    System.out.print("Digite o Kind ");
                    BoardColumnEnum kind = BoardColumnEnum.valueOf(scanner.nextLine());
                    System.out.print("Digite a Order ");
                    int orderValue = scanner.nextInt();



                    BoardColumn boardColumn = new BoardColumn(null, boardColumnName, kind, orderValue, board);
                    boardColumnService.inset(boardColumn);
                    break;
                case 3:
                    System.out.println("Escolheu 3");
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }while (select != 4);

        scanner.close();
    }
}
