package projeto_bootcamp_dio.board_de_tarefas.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime blockedAt;
    private String blockReason;
    private LocalDateTime unblockAt;
    private String unblockReason;

    @ManyToOne
    private Card card;

    public Block() {
    }

    public Block(Long id, String blockReason, LocalDateTime blockedAt,
                 String unblockReason, LocalDateTime unblockAt, Card card) {
        this.id = id;
        this.blockReason = blockReason;
        this.blockedAt = blockedAt;
        this.unblockReason = unblockReason;
        this.unblockAt = unblockAt;
        this.card = card;
    }

    public Long getId() {
        return id;
    }

    public String getBlockReason() {
        return blockReason;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

    public LocalDateTime getBlockedAt() {
        return blockedAt;
    }

    public void setBlockedAt(LocalDateTime blockedAt) {
        this.blockedAt = blockedAt;
    }

    public String getUnblockReason() {
        return unblockReason;
    }

    public void setUnblockReason(String unblockReason) {
        this.unblockReason = unblockReason;
    }

    public LocalDateTime getUnblockAt() {
        return unblockAt;
    }

    public void setUnblockAt(LocalDateTime unblockAt) {
        this.unblockAt = unblockAt;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
