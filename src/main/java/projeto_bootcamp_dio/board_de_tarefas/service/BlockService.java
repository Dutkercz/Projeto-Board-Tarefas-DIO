package projeto_bootcamp_dio.board_de_tarefas.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import projeto_bootcamp_dio.board_de_tarefas.entities.Block;
import projeto_bootcamp_dio.board_de_tarefas.entities.Card;
import projeto_bootcamp_dio.board_de_tarefas.repositories.BlockRepository;

import java.time.LocalDateTime;

@Service
public class BlockService {

    private final BlockRepository blockRepository;

    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    public int countTotalBlocks(Long id) {
        return blockRepository.countByCardId(id);
    }

    @Transactional
    public Block blockCard(Card card, String blockReason) {
        Block block = new Block();
        block.setBlockReason(blockReason);
        block.setCard(card);
        block.setBlockedAt(LocalDateTime.now());
        return block;
    }
}
