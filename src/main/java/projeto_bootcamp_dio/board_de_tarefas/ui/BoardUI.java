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

        if (additionalColumns > 0){
            for (int i = 1; i <= additionalColumns; i++) {
                System.out.print("Digite o nome da COLUNA ADICIONAL nº" + i +": ");
                String boardColumnName = scanner.nextLine();

                try {
                    boardColumnList.add(boardColumnService.createBoardColumn(
                            boardColumnName, BoardColumnEnum.PENDING, i, board));
                } catch (RuntimeException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        System.out.print("Digite o nome da COLUNA final: ");
        boardColumnList.add(boardColumnService.createBoardColumn(
                scanner.nextLine(), BoardColumnEnum.FINAL, additionalColumns+1, board ));

        System.out.print("Digite o nome da COLUNA de cancelamento: ");
        boardColumnList.add(boardColumnService.createBoardColumn(
                scanner.nextLine(), BoardColumnEnum.CANCEL, additionalColumns+2, board ));


        for (BoardColumn col : boardColumnList){
            board.addBoardColumn(col);
        }

        return boardService.insert(board);
    }

}
