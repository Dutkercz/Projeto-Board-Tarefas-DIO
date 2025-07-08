package projeto_bootcamp_dio.board_de_tarefas.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import projeto_bootcamp_dio.board_de_tarefas.entities.Card;
import projeto_bootcamp_dio.board_de_tarefas.repositories.CardRepository;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public int countCardByColumn(Long id) {
        return cardRepository.countByBoardColumnId(id);
    }

    public Card findById(Long id){
        return cardRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("!!! Card com o ID "+ id + " não encotrado !!!" ));
    }

    public List<Card> findAllByBoardColumnId(long selectedColumn) {
        return cardRepository.findAllByBoardColumnId(selectedColumn);
    }

    public void save(Card card) {
        cardRepository.save(card);
    }
}
