package projeto_bootcamp_dio.board_de_tarefas.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import projeto_bootcamp_dio.board_de_tarefas.entities.Board;
import projeto_bootcamp_dio.board_de_tarefas.entities.BoardColumn;
import projeto_bootcamp_dio.board_de_tarefas.entities.Card;
import projeto_bootcamp_dio.board_de_tarefas.enums.BoardColumnEnum;
import projeto_bootcamp_dio.board_de_tarefas.repositories.CardRepository;

import java.util.List;
import java.util.Objects;

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

    @Transactional
    public void save(Card card) {
        cardRepository.save(card);
    }

    public List<Card> findAllCardAtivos(Board board) {
        return cardRepository.findAll().stream()
                .filter(x -> Objects.equals(x.getBoardColumn().getBoard().getId(), board.getId()))
                .filter(x -> x.getBoardColumn().getKind() != BoardColumnEnum.CANCEL)
                .filter(x -> x.getBoardColumn().getKind() != BoardColumnEnum.FINAL)
                .toList();
    }

    public List<Card> findAllWithBlock() {
        return cardRepository.findAll()
                .stream()
                .filter(x -> x.getBlockList().stream()
                        .anyMatch(y -> y.getBlockReason() != null
                                && y.getUnblockReason() == null)).toList();
    }

    @Transactional
    public void alterBoardColumn(Card card, BoardColumn boardColumn) {
        card.setBoardColumn(boardColumn);
        cardRepository.save(card);
    }
}
