package projeto_bootcamp_dio.board_de_tarefas.entities;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public class BoardColumn {

    private Long id;
    private String name;
    private String kind;
    private Integer order;
    @ManyToOne
    private Board board;
    @OneToMany
    private List<Card> cardList;



}
