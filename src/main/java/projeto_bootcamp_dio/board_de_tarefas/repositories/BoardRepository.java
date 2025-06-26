package projeto_bootcamp_dio.board_de_tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_bootcamp_dio.board_de_tarefas.entities.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
