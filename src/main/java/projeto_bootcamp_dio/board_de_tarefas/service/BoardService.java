package projeto_bootcamp_dio.board_de_tarefas.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import projeto_bootcamp_dio.board_de_tarefas.entities.Board;
import projeto_bootcamp_dio.board_de_tarefas.repositories.BoardRepository;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Board insert(Board board){
        return boardRepository.save(board);
    }
}
