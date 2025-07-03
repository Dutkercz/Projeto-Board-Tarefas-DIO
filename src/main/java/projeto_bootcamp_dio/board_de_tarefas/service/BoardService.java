package projeto_bootcamp_dio.board_de_tarefas.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import projeto_bootcamp_dio.board_de_tarefas.entities.Board;
import projeto_bootcamp_dio.board_de_tarefas.entities.BoardColumn;
import projeto_bootcamp_dio.board_de_tarefas.enums.BoardColumnEnum;
import projeto_bootcamp_dio.board_de_tarefas.repositories.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final Scanner scanner = new Scanner(System.in);
    private final BoardColumnService boardColumnService;


    public BoardService(BoardRepository boardRepository, BoardColumnService boardColumnService) {
        this.boardRepository = boardRepository;
        this.boardColumnService = boardColumnService;
    }

    @Transactional
    public Board insert(Board board){
        System.out.println("CRIAR BOARD");
        System.out.print("Digite o nome do Board: ");
        String boardName = scanner.nextLine();
        board.setId(null);
        board.setName(boardName);
        System.out.println("Seu BOARD tera colunas além das 3 padrões?\n" +
                "Se SIM digite a quantidade\n" +
                "Se NÃO digite 0\n" +
                ">> ");
        int additionalColumns = scanner.nextInt();

        List<BoardColumn> boardColumnList = new ArrayList<>();
        BoardColumn boardColumn = new BoardColumn();

        scanner.nextLine();
        System.out.print("\nInforme o nome da COLUNA inicial do Board: ");
        String initialColumnName = scanner.nextLine();
        boardColumn.setName(initialColumnName);
        boardColumn.setOrder(0);
        boardColumn.setKind(BoardColumnEnum.INITIAL);
        boardColumn.setBoard(board);

        boardColumnService.insert(boardColumn);

        boardColumnList.add(boardColumn);

        if (additionalColumns > 0){
            List<BoardColumn> newBoardColumns = boardColumnService.createBoardColumn(additionalColumns);
            for(BoardColumn col : newBoardColumns){
                col.setBoard(board);
                boardColumnService.insert(col);
            }
            boardColumnList.addAll(newBoardColumns);
        }
        for (BoardColumn col : boardColumnList){
            board.addBoardColumn(col);
        }


        return boardRepository.save(board);
    }

    public Board findBoard(Long boardID) {
        return boardRepository.findById(boardID)
                .orElseThrow(() -> new EntityNotFoundException("Board com o id " + boardID + " não encontrado."));
    }

    public void deleteBoard(long boardId) {
        boardRepository.deleteById(boardId);
    }
}
