package projeto_bootcamp_dio.board_de_tarefas.ui;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import projeto_bootcamp_dio.board_de_tarefas.entities.Board;
import projeto_bootcamp_dio.board_de_tarefas.entities.BoardColumn;
import projeto_bootcamp_dio.board_de_tarefas.enums.BoardColumnEnum;
import projeto_bootcamp_dio.board_de_tarefas.service.BoardColumnService;
import projeto_bootcamp_dio.board_de_tarefas.service.BoardService;

import javax.swing.text.html.parser.Entity;
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

        String firula = "\n===================================================================\n";
        Board board = new Board();
        int select;

        do {
            System.out.println("\n== BEM VINDO AO SISTEMA DE BOARDS ==");
            String options = """
                    Selecione uma das opções do menu
                    1 - CRIAR NOVO BOARD / CREATE NEW BOARD
                    2 - SELECIONAR BOARD / SELECT BOARD
                    3 - EXCLUIR BOARD / DELETE BOARD
                    4 - SAIR
                    >>>\s""";

            System.out.print(options);
            select = scanner.nextInt();

            switch (select) {
                case 1:
                    scanner.nextLine();
                    board = boardService.insert(board);
                    System.out.printf(firula +"Board criado!\n  nome: %s, com o ID %s", board.getName(), board.getId() + firula);
                    break;

                case 2:
                    System.out.println("Informe o ID do Board que deseja selecionar");
                    Long boardID = scanner.nextLong();
                    try {
                        board = boardService.findBoard(boardID);
                        System.out.println(firula);
                        System.out.printf("Board de ID %s selecionado. Nome do board: %s", board.getId(), board.getName() + firula);
                    }catch (EntityNotFoundException e){
                        System.out.print(firula);
                        System.out.println(e.getMessage() + firula);
                    }
                    break;
                case 3:
                    System.out.print("\nDigite o ID do board que deseja deletar: ");
                    long boardId = scanner.nextLong();

                    try {
                        board = boardService.findBoard(boardId);
                    }catch (EntityNotFoundException e){
                        System.out.print(firula);
                        System.out.println(e.getMessage() + firula);
                        break;
                    }

                    System.out.printf("""
                            \nDeseja deletar o BOARD: %s ?
                            1 - Sim
                            Ou aperte qualquer tecla para CANCELAR
                            >>\s""", board.getName());
                    int escolha = scanner.nextInt();
                    if (escolha == 1){
                        boardService.deleteBoard(boardId);
                        System.out.print(firula + "\tBoard excluido!" + firula);
                    }
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
