package projeto_bootcamp_dio.board_de_tarefas.service;

import org.springframework.stereotype.Service;
import projeto_bootcamp_dio.board_de_tarefas.repositories.CardRepository;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public int countCardByColumn(Long id) {
        return cardRepository.countByBoardColumnId(id);
    }
}
