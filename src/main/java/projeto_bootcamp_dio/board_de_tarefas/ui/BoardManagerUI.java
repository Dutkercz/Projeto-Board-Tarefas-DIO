package projeto_bootcamp_dio.board_de_tarefas.ui;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import projeto_bootcamp_dio.board_de_tarefas.entities.Board;
import projeto_bootcamp_dio.board_de_tarefas.entities.BoardColumn;
import projeto_bootcamp_dio.board_de_tarefas.enums.BoardColumnEnum;
import projeto_bootcamp_dio.board_de_tarefas.service.BoardColumnService;
import projeto_bootcamp_dio.board_de_tarefas.service.BoardService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class BoardManagerUI {

    private final BoardService boardService;
    private final BoardColumnService boardColumnService;
    private final CardManagerUI cardManagerUI;
    private final Scanner scanner = new Scanner(System.in);

    public BoardManagerUI(BoardService boardService, BoardColumnService boardColumnService, CardManagerUI cardManagerUI) {
        this.boardService = boardService;
        this.boardColumnService = boardColumnService;
        this.cardManagerUI = cardManagerUI;
    }


    public void createBoard() {

        Board board = new Board();
        System.out.println("CRIAR BOARD");
        System.out.print("Digite o nome do Board: ");
        String boardName = scanner.nextLine();
        board.setId(null);
        board.setName(boardName);
        board = boardService.insert(board);

        System.out.print("""
                
                Seu BOARD tera colunas além das 3 padrões?
                Se SIM digite a quantidade
                Se NÃO digite 0
                >>\s""");
        int additionalColumns = scanner.nextInt();

        List<BoardColumn> boardColumnList = new ArrayList<>();

        scanner.nextLine();
        System.out.print("Informe o nome da COLUNA INICIAL do Board: ");
        String initialColumnName = scanner.nextLine();

        boardColumnList.add(boardColumnService.createBoardColumn(
                initialColumnName, BoardColumnEnum.INITIAL, 0, board));

        if (additionalColumns > 0) {
            for (int i = 1; i <= additionalColumns; i++) {
                System.out.print("Digite o nome da COLUNA ADICIONAL nº" + i + ": ");
                String boardColumnName = scanner.nextLine();

                try {
                    boardColumnList.add(boardColumnService.createBoardColumn(
                            boardColumnName, BoardColumnEnum.PENDING, i, board));
                } catch (RuntimeException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        System.out.print("Digite o nome da COLUNA FINAL: ");
        boardColumnList.add(boardColumnService.createBoardColumn(
                scanner.nextLine(), BoardColumnEnum.FINAL, additionalColumns + 1, board));

        System.out.print("Digite o nome da COLUNA de CANCELAMENTO: ");
        boardColumnList.add(boardColumnService.createBoardColumn(
                scanner.nextLine(), BoardColumnEnum.CANCEL, additionalColumns + 2, board));


        for (BoardColumn col : boardColumnList) {
            board.addBoardColumn(col);
        }
        System.out.printf("\nBoard criado!\n  nome: %s, com o ID %s\n", board.getName(), board.getId());


        boardService.insert(board);
    }

    public void selectBoard() {
        Board board;
        System.out.println("Informe o ID do Board que deseja selecionar");
        Long boardID = scanner.nextLong();
        try {
            board = boardService.findBoard(boardID);
            cardManagerUI.setBoard(board);
            cardManagerUI.manageCards();

        } catch (EntityNotFoundException e) {
            scanner.nextLine();
            System.out.println(e.getMessage());
        }

    }

    public void boardList() {
        System.out.println("Lista de BOARDS Disponíveis");
        List<Board> boardList = boardService.boardList();
        boardList.forEach(x -> {
            System.out.println("ID " + x.getId() + " - " + x.getName());
        });
    }

    public void deleteBoard() {
        System.out.print("\nDigite o ID do board que deseja deletar: ");
        long boardId = scanner.nextLong();
        Board board;


        try {
            board = boardService.findBoard(boardId);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        scanner.nextLine();
        System.out.printf("""
                \nDeseja deletar o BOARD: %s ?
                1 - Sim
                Ou aperte qualquer tecla para CANCELAR
                >>\s""", board.getName());
        String escolha = scanner.nextLine();
        if (escolha.equals("1")) {
            boardService.deleteBoard(boardId);
            System.out.print("\t\n XXX Board excluido! XXX\n");
        } else {
            System.out.println("\nOperação CANCELADA!\n");
        }
    }
}
