package projeto_bootcamp_dio.board_de_tarefas.service;

import org.springframework.stereotype.Service;
import projeto_bootcamp_dio.board_de_tarefas.entities.Board;
import projeto_bootcamp_dio.board_de_tarefas.entities.BoardColumn;
import projeto_bootcamp_dio.board_de_tarefas.enums.BoardColumnEnum;
import projeto_bootcamp_dio.board_de_tarefas.repositories.BoardColumnRepository;

import java.util.Scanner;

@Service
public class BoardColumnService {

    private final BoardColumnRepository boardColumnRepository;
    private final Scanner scanner = new Scanner(System.in);

    public BoardColumnService(BoardColumnRepository boardColumnRepository) {
        this.boardColumnRepository = boardColumnRepository;
    }

    public void save(BoardColumn boardColumn){
        boardColumnRepository.save(boardColumn);
    }

    public BoardColumn createBoardColumn(String boardColumnName, BoardColumnEnum kind, int order, Board board) {
        return boardColumnRepository.save(new BoardColumn(null, boardColumnName, kind, order, board));
    }
}
