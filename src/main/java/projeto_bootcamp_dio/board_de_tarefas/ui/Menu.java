package projeto_bootcamp_dio.board_de_tarefas.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    public static void showMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Este é o menu");
            String options = """
                    Selecione uma das opções do menu
                    1 - Option 1
                    2 - Option 2
                    3 - Option 3
                    4 - SAIR
                    """;
            System.out.println(options);
            int select = scanner.nextInt();

            while (select != 4) {
                switch (select) {
                    case 1:
                        System.out.println("Ecolheu 1");
                        break;
                    case 2:
                        System.out.println("Escolheu 2");
                        break;
                    case 3:
                        System.out.println("Escolheu 3");
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Escolha errada");
                        break;
                }
                select = scanner.nextInt();
            }
        }
    }
}
