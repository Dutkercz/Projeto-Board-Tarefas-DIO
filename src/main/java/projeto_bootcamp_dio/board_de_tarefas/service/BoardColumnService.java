package projeto_bootcamp_dio.board_de_tarefas.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import projeto_bootcamp_dio.board_de_tarefas.entities.Board;
import projeto_bootcamp_dio.board_de_tarefas.entities.BoardColumn;
import projeto_bootcamp_dio.board_de_tarefas.enums.BoardColumnEnum;
import projeto_bootcamp_dio.board_de_tarefas.repositories.BoardColumnRepository;

@Service
public class BoardColumnService {

    private final BoardColumnRepository boardColumnRepository;

    public BoardColumnService(BoardColumnRepository boardColumnRepository) {
        this.boardColumnRepository = boardColumnRepository;
    }

    public BoardColumn createBoardColumn(String boardColumnName, BoardColumnEnum kind, int order, Board board) {
        return boardColumnRepository.save(new BoardColumn(null, boardColumnName, kind, order, board));
    }

    public BoardColumn findById(long selectedColumn) {
        return boardColumnRepository.findById(selectedColumn).orElseThrow(() ->
                new EntityNotFoundException("!!! Coluna de id " + selectedColumn + " não encontrada !!!"));
    }
}
