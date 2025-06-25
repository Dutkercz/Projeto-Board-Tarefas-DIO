package projeto_bootcamp_dio.board_de_tarefas.entities;

import jakarta.persistence.OneToMany;

import java.util.List;

public class Board {

    private Long id;
    private String name;
    @OneToMany
    private List<BoardColumn> boardColumnList;
}
