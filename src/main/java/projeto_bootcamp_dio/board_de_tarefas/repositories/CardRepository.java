package projeto_bootcamp_dio.board_de_tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_bootcamp_dio.board_de_tarefas.entities.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

    int countByBoardColumnId(Long id);
}
