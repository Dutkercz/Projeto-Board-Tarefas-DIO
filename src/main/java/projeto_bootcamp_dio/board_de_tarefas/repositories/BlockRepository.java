package projeto_bootcamp_dio.board_de_tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_bootcamp_dio.board_de_tarefas.entities.Block;

public interface BlockRepository extends JpaRepository<Block, Long> {
}
