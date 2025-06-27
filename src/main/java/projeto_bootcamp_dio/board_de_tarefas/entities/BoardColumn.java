package projeto_bootcamp_dio.board_de_tarefas.entities;

import jakarta.persistence.*;
import projeto_bootcamp_dio.board_de_tarefas.enums.BoardColumnEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class BoardColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BoardColumnEnum kind;
    private Integer order;

    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "boardColumn")
    private List<Card> cardList = new ArrayList<>();

    public BoardColumn() {
    }

    public BoardColumn(Long id, String name, BoardColumnEnum kind, Integer order, Board board) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.order = order;
        this.board = board;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoardColumnEnum getKind() {
        return kind;
    }

    public void setKind(BoardColumnEnum kind) {
        this.kind = kind;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void addCard (Card card){
        cardList.add(card);
        card.setBoardColumn(this);
    }

    public void removeCard(Card card){
        cardList.remove(card);
        card.setBoardColumn(this);
    }

    public List<Card> getCardList(){
        return this.cardList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        BoardColumn that = (BoardColumn) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
