package projeto_bootcamp_dio.board_de_tarefas.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String blockCause;
    private LocalDateTime blockIn;
    private String unblockCause;
    private LocalDateTime unblockIn;

    @ManyToOne
    private Card card;

    public Block() {
    }

    public Block(Long id, String blockCause, LocalDateTime blockIn,
                 String unblockCause, LocalDateTime unblockIn, Card card) {
        this.id = id;
        this.blockCause = blockCause;
        this.blockIn = blockIn;
        this.unblockCause = unblockCause;
        this.unblockIn = unblockIn;
        this.card = card;
    }

    public Long getId() {
        return id;
    }

    public String getBlockCause() {
        return blockCause;
    }

    public void setBlockCause(String blockCause) {
        this.blockCause = blockCause;
    }

    public LocalDateTime getBlockIn() {
        return blockIn;
    }

    public void setBlockIn(LocalDateTime blockIn) {
        this.blockIn = blockIn;
    }

    public String getUnblockCause() {
        return unblockCause;
    }

    public void setUnblockCause(String unblockCause) {
        this.unblockCause = unblockCause;
    }

    public LocalDateTime getUnblockIn() {
        return unblockIn;
    }

    public void setUnblockIn(LocalDateTime unblockIn) {
        this.unblockIn = unblockIn;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
