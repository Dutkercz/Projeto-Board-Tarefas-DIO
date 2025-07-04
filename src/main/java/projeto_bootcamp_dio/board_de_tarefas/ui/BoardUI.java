package projeto_bootcamp_dio.board_de_tarefas.ui;

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
public class BoardUI {

    private final BoardService boardService;
    private final BoardColumnService boardColumnService;
    private final Scanner scanner = new Scanner(System.in);

    public BoardUI(BoardService boardService, BoardColumnService boardColumnService) {
        this.boardService = boardService;
        this.boardColumnService = boardColumnService;
    }


    public Board boardInteraction(){

        Board board = new Board();
        System.out.println("CRIAR BOARD");
        System.out.print("Digite o nome do Board: ");
        String boardName = scanner.nextLine();
        board.setId(null);
        board.setName(boardName);
        board = boardService.insert(board);

        System.out.print("Seu BOARD tera colunas além das 3 padrões?\n" +
                "Se SIM digite a quantidade\n" +
                "Se NÃO digite 0\n" +
                ">> ");
        int additionalColumns = scanner.nextInt();

        List<BoardColumn> boardColumnList = new ArrayList<>();

        scanner.nextLine();
        System.out.print("\nInforme o nome da COLUNA inicial do Board: ");
        String initialColumnName = scanner.nextLine();

        BoardColumn boardColumn = new BoardColumn(null, initialColumnName, BoardColumnEnum.INITIAL, 0, board);

        boardColumnService.save(boardColumn);
        boardColumnList.add(boardColumn);

        if (additionalColumns > 0){
            for (int i = 1; i <= additionalColumns; i++) {
                System.out.print("Digite o nome da Board Column adicional: ");
                String boardColumnName = scanner.nextLine();

                System.out.print("Digite o Kind: ");
                BoardColumnEnum kind = BoardColumnEnum.valueOf(scanner.nextLine().toUpperCase());
                try {
                    boardColumnList.add(boardColumnService.createBoardColumn(boardColumnName, kind, i, board));
                } catch (RuntimeException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        for(BoardColumn col : boardColumnList){
            col.setBoard(board);
            boardColumnService.save(col);
        }
        for (BoardColumn col : boardColumnList){
            board.addBoardColumn(col);
        }

        return boardService.insert(board);
    }

}
