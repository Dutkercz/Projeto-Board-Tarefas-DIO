package projeto_bootcamp_dio.board_de_tarefas.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import projeto_bootcamp_dio.board_de_tarefas.entities.Board;
import projeto_bootcamp_dio.board_de_tarefas.entities.BoardColumn;
import projeto_bootcamp_dio.board_de_tarefas.repositories.BoardRepository;

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
