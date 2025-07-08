package projeto_bootcamp_dio.board_de_tarefas.ui;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import projeto_bootcamp_dio.board_de_tarefas.entities.Board;
import projeto_bootcamp_dio.board_de_tarefas.entities.BoardColumn;
import projeto_bootcamp_dio.board_de_tarefas.entities.Card;
import projeto_bootcamp_dio.board_de_tarefas.enums.BoardColumnEnum;
import projeto_bootcamp_dio.board_de_tarefas.service.BlockService;
import projeto_bootcamp_dio.board_de_tarefas.service.BoardColumnService;
import projeto_bootcamp_dio.board_de_tarefas.service.BoardService;
import projeto_bootcamp_dio.board_de_tarefas.service.CardService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Component
public class CardManagerUI {

    private final Scanner scanner = new Scanner(System.in);
    private final BlockService blockService;
    private final CardService cardService;
    private final BoardService boardService;
    private final BoardColumnService boardColumnService;
    private Board board;
    private BoardColumn boardColumn;


    public CardManagerUI(BlockService blockService, CardService cardService, BoardService boardService, BoardColumnService boardColumnService) {
        this.blockService = blockService;
        this.cardService = cardService;
        this.boardService = boardService;
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
            scanner.nextLine();

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
                    System.out.println("XXX Saindo... ADEUS, SENTIREI SAUDADES MEU GRANDE AMIGO! XXX");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (option != 9);
    }

    private void createCard() {
        Card card = new Card();
        System.out.print("Informe o titulo do CARD: ");
        card.setTitle(scanner.nextLine());
        System.out.print("Informa e Descrição do CARD: ");
        card.setDescription(scanner.nextLine());
        card.setBoardColumn(board.getInitialBoardColumn());
        card.setCreatedAt(LocalDateTime.now());
        card.setId(null);

        try {
            cardService.save(card);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    private void moveCardToNextColumn() {
        System.out.print("Informe o ID do CARD que deseja mover: ");
        long cardID = scanner.nextLong();
        Card card = cardService.findById(cardID);
        if (card != null){
            BoardColumn column = boardColumnService.findById(card.getBoardColumn().getId()+1);
            card.setBoardColumn(column);
            if (column.getKind() == BoardColumnEnum.CANCEL){
                System.out.println("Esse card já está finalizado.");
                return;
            }else {
                cardService.save(card);
                System.out.printf("Card %s movido para a COLUNA: %s%n",card.getTitle(), card.getBoardColumn().getName());
            }
        }

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
        board = boardService.findBoard(board.getId());
        System.out.printf("Escolha uma coluna do Board %s %n" , board.getName());
        List<Long> columnsIds = board.getBoardColumnList().stream().map(BoardColumn::getId).toList();
        long selectedColumnID = -1L;

        while (!columnsIds.contains(selectedColumnID)) {//roda até encontrar um ID que esteja na listade columnsIds
            board.getBoardColumnList().forEach(x -> {
                    System.out.printf("%s - %s [%s] %n", x.getId(), x.getName(), x.getKind());
                    if (!x.getCardList().isEmpty()){
                        for (int i = 0; i < x.getCardList().size(); i++) {
                            System.out.printf(" Card ID %s - %s %n" ,x.getCardList().get(i).getId(),
                                    x.getCardList().get(i).getDescription());
                        }
                        System.out.println();
                    }
            });
            selectedColumnID = scanner.nextLong();
        }
        try {
            boardColumn = boardColumnService.findById(selectedColumnID);
            System.out.printf("Coluna %s - Tipo %s %n", boardColumn.getName(), boardColumn.getKind());
            boardColumn.getCardList().forEach(x ->
                    System.out.printf("Card %s - %s.\nDescrição: %s%n", x.getId(), x.getTitle(), x.getDescription()));

        }catch (EntityNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    private void showCard() {
        if (boardColumn == null) {
            System.out.println("!!! Selecione uma Coluna na opção 7 !!!");
            return;
        }
        List<Card> cardsList = cardService.findAllByBoardColumnId(boardColumn.getId());

        if (cardsList.isEmpty()) {
            System.out.printf("Não há Cards na coluna %s [tipo - %s]%n",
                    boardColumn.getName(), boardColumn.getKind());
        }else {
            Card card = new Card();
            List<Long> cardListIds = cardsList.stream().map(Card::getId).toList();
            long selectedCardId = -1L;

            while (!cardListIds.contains(selectedCardId)) {
                System.out.println("Escolha um card da lista:");
                cardsList.forEach(x -> System.out.printf("Card ID - %s%n", x.getId()));
                System.out.print(">> ");
                try {
                    String input = scanner.next();
                    selectedCardId = Long.parseLong(input);

                    card = cardService.findById(selectedCardId);

                } catch (EntityNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Digite um número válido.");
                    scanner.nextLine();
                }
            }
            System.out.printf("Card ID %s - Título: %s%n", card.getId(), card.getTitle());

            System.out.println(card.getBlockList() != null && !card.getBlockList().isEmpty()
                    ? "Está bloqueado: " + card.getBlockList().getLast().getBlockReason()
                    : "Não está bloqueado.");

            System.out.printf("Atualmente na coluna %s - %s%n",
                    card.getBoardColumn().getId(), card.getBoardColumn().getName());

            System.out.printf("Número total de bloqueios: %s%n",
                    blockService.countTotalBlocks(card.getId()));
        }
    }

}
