package projeto_bootcamp_dio.board_de_tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_bootcamp_dio.board_de_tarefas.entities.BoardColumn;
import projeto_bootcamp_dio.board_de_tarefas.enums.BoardColumnEnum;

public interface BoardColumnRepository extends JpaRepository<BoardColumn, Long> {
    BoardColumn findByKind(BoardColumnEnum boardColumnEnum);
}
