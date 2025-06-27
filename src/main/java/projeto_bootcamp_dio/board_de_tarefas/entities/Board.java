package projeto_bootcamp_dio.board_de_tarefas.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "board")
    private List<BoardColumn> boardColumnList = new ArrayList<>();

    public Board() {
    }

    public Board(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public void addBoardColumn(BoardColumn boardColumn){
        boardColumnList.add(boardColumn);
        boardColumn.setBoard(this);
    }

    public void removeBoardColumn(BoardColumn boardColumn){
        boardColumnList.remove(boardColumn);
        boardColumn.setBoard(null);
    }

    public List<BoardColumn> getBoardColumnList(){
        return this.boardColumnList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;
        return id.equals(board.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
