package projeto_bootcamp_dio.board_de_tarefas.service;

import org.springframework.stereotype.Service;
import projeto_bootcamp_dio.board_de_tarefas.entities.BoardColumn;
import projeto_bootcamp_dio.board_de_tarefas.enums.BoardColumnEnum;
import projeto_bootcamp_dio.board_de_tarefas.repositories.BoardColumnRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class BoardColumnService {

    private final BoardColumnRepository boardColumnRepository;
    private final Scanner scanner = new Scanner(System.in);

    public BoardColumnService(BoardColumnRepository boardColumnRepository) {
        this.boardColumnRepository = boardColumnRepository;
    }

    public void insert(BoardColumn boardColumn){
        boardColumnRepository.save(boardColumn);
    }

    public List<BoardColumn> createBoardColumn(int additionalColumns) {
        List<BoardColumn> boardColumnList = new ArrayList<>();

        for (int i = 1; i <= additionalColumns; i++) {
            System.out.print("Digite o nome da Board Column adicional: ");
            String boardColumnName = scanner.nextLine();

            System.out.print("Digite o Kind: ");
            BoardColumnEnum kind = BoardColumnEnum.valueOf(scanner.nextLine());

            BoardColumn boardColumn = new BoardColumn();

            boardColumn.setId(null);
            boardColumn.setName(boardColumnName);
            boardColumn.setKind(kind);
            boardColumn.setOrder(i);

            boardColumnList.add(boardColumn);
        }

        return boardColumnList;
    }
}
