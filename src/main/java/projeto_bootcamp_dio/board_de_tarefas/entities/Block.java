package projeto_bootcamp_dio.board_de_tarefas.entities;

import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class Block {

    private Long id;
    private String blockCause;
    private LocalDateTime blockIn;
    private String unblockCause;
    private LocalDateTime unblockIn;
    @ManyToOne
    private Card card;
}
