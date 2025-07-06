package projeto_bootcamp_dio.board_de_tarefas.ui;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import projeto_bootcamp_dio.board_de_tarefas.entities.Board;
import projeto_bootcamp_dio.board_de_tarefas.entities.BoardColumn;
import projeto_bootcamp_dio.board_de_tarefas.entities.Card;
import projeto_bootcamp_dio.board_de_tarefas.service.BoardColumnService;
import projeto_bootcamp_dio.board_de_tarefas.service.CardService;

import java.util.List;
import java.util.Scanner;

@Component
public class CardManagerUI {

    private final Scanner scanner = new Scanner(System.in);
    private final CardService cardService;
    private final BoardColumnService boardColumnService;
    private Board board;
    private BoardColumn boardColumn;

    public CardManagerUI(CardService cardService, BoardColumnService boardColumnService) {
        this.cardService = cardService;
        this.boardColumnService = boardColumnService;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void manageCards() {
        System.out.printf("==== Bem VIndo ao Board %s! ==== %n", board.getName());

        String menu = """
                
                SELECIONE A OPERAÇÃO DESEJADA
                1 - Criar um Card
                2 - Mover um Card
                3 - Bloquar um Card
                4 - Desbloquear um Card
                5 - Cancelar um Card
                6 - Visualizar Board
                7 - Visualizar Colunas com Cards
                8 - Ver Card
                9 - Voltar ao menu anterior
                10 - Sair
                >>\s""";
        int option;

        do {
            System.out.print(menu);
            option = scanner.nextInt();

            switch (option) {
                case 1 -> createCard();
                case 2 -> moveCardToNextColumn();
                case 3 -> blockCard();
                case 4 -> unblockCard();
                case 5 -> cancelCard();
                case 6 -> showBoard();
                case 7 -> showColumnWithCard();
                case 8 -> showCard();
                case 9 -> System.out.println(" === Voltando ao menu principal... ===");
                case 10 -> {
                    System.out.println("XXX Saindo... XXX");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (option != 9);
    }

    private void createCard() {
    }

    private void moveCardToNextColumn() {

    }

    private void blockCard() {

    }

    private void unblockCard() {

    }

    private void cancelCard() {

    }

    private void showBoard() {
        System.out.println("Board: " + board.getName() + " ID: " + board.getId());
        System.out.println("COLUNAS: ");
        board.getBoardColumnList().forEach(x -> {
            int totalCards = cardService.countCardByColumn(x.getId());
            System.out.printf("   Coluna %s , do tipo %s - tem um total de %s cards %n", x.getName(), x.getKind(), totalCards);
        });
    }

    private void showColumnWithCard() {
        System.out.printf("Escolha uma coluna do Board %s %n" , board.getName());
        List<Long> columnsIds = board.getBoardColumnList().stream().map(BoardColumn::getId).toList();
        long selectedColumnID = -1L;

        while (!columnsIds.contains(selectedColumnID)) {//roda até encontrar um ID que esteja na listade columnsIds
            board.getBoardColumnList().forEach(x ->
                    System.out.printf("%s - %s [%s] %n", x.getId(), x.getName(), x.getKind()));
            selectedColumnID = scanner.nextLong();
        }
        try {
            boardColumn = boardColumnService.findById(selectedColumnID);
            System.out.printf("Coluna %s - Tipo %s %n", boardColumn.getName(), boardColumn.getKind());
            boardColumn.getCardList().forEach(x ->
                    System.out.printf("Card %s - %s.\nDescrição %s", x.getId(), x.getTitle(), x.getDescription()));

        }catch (EntityNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    private void showCard() {
        List<Card> cardsList = cardService.findAllByBoardColumnId(boardColumn.getId());
        long selectedCardId;
        Card card;
        if (!cardsList.isEmpty()){
            do{
                System.out.println("Esolha um card da lista ");
                cardsList.forEach(x ->
                        System.out.printf("Card ID %s - Titulo: %s %n", x.getId(), x.getTitle()));
                selectedCardId = scanner.nextLong();
                card = cardService.findById(selectedCardId);
            }while (card.getId() == null);

        }else {
            System.out.printf("Não há Cards na coluna %s [tipo - %s]%n", boardColumn.getName(), boardColumn.getKind());
        }

    }

}
