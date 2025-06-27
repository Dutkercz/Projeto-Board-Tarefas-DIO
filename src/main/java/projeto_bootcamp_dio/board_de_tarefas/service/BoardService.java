package projeto_bootcamp_dio.board_de_tarefas.service;

import org.springframework.stereotype.Service;
import projeto_bootcamp_dio.board_de_tarefas.repositories.BoardRepository;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
}
