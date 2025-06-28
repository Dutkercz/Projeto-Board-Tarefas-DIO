package projeto_bootcamp_dio.board_de_tarefas.service;

import org.springframework.stereotype.Service;
import projeto_bootcamp_dio.board_de_tarefas.entities.BoardColumn;
import projeto_bootcamp_dio.board_de_tarefas.repositories.BoardColumnRepository;

@Service
public class BoardColumnService {

    private final BoardColumnRepository boardColumnRepository;

    public BoardColumnService(BoardColumnRepository boardColumnRepository) {
        this.boardColumnRepository = boardColumnRepository;
    }

    public void inset(BoardColumn boardColumn){
        boardColumnRepository.save(boardColumn);
    }
}
