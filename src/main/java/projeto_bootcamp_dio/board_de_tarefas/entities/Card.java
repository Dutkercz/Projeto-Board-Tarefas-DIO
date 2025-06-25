package projeto_bootcamp_dio.board_de_tarefas.entities;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

public class Card {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    @ManyToOne
    private BoardColumn boardColumn;
    @OneToMany
    private List<Block> blockList;
}
