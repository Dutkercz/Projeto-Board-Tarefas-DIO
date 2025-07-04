package projeto_bootcamp_dio.board_de_tarefas.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import projeto_bootcamp_dio.board_de_tarefas.entities.Board;
import projeto_bootcamp_dio.board_de_tarefas.repositories.BoardRepository;

import java.util.List;
import java.util.Scanner;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final Scanner scanner = new Scanner(System.in);



    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
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

    public List<Board> boardList() {
        return boardRepository.findAll();
    }
}
