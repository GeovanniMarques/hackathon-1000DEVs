public class Hackathon {
    // Classe pública exigida pelo Java
}

/* =======================
   PARTE 1 – TABULEIRO
   ======================= */
class Parte1 {

    public static void main(String[] args) {
        char[][] tabuleiro = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        exibirTabuleiro(tabuleiro);
    }

    public static void exibirTabuleiro(char[][] tabuleiro) {
        System.out.println("\n    1   2   3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + "   ");
            System.out.println(tabuleiro[i][0] + " | " + tabuleiro[i][1] + " | " + tabuleiro[i][2]);

            if (i < 2) {
                System.out.println("   ---+---+---");
            }
        }
        System.out.println();
    }
}

/* =======================
   PARTE 2 – VITÓRIA COMPUTADOR
   ======================= */
class Parte2 {

    public static void main(String[] args) {
        exibirVitoriaComputador();
    }

    public static void exibirVitoriaComputador() {
        System.out.println("\n=======================================");
        System.out.println(" O COMPUTADOR VENCEU!");
        System.out.println(" Ele esta todo feliz!");
        System.out.println("=======================================\n");

        System.out.println(
            "         +------------------------+\n" +
            "         |   (^_^)  VITORIA!      |\n" +
            "         |   COMPUTADOR GANHOU    |\n" +
            "         +------------------------+\n" +
            "                 |        |      \n" +
            "             +---+--------+----+  \n" +
            "             |      BASE       | \n" +
            "             +-----------------+ \n"
        );
    }
}

/* =======================
   PARTE 3 – VITÓRIA USUÁRIO
   ======================= */
class Parte3 {

    public static void main(String[] args) {
        exibirVitoriaUsuario();
    }

    public static void exibirVitoriaUsuario() {
        System.out.println("\n=======================================");
        System.out.println(" VOCE VENCEU! PARABENS!");
        System.out.println(" O computador ficou triste...");
        System.out.println("=======================================\n");

        System.out.println(
            "           \\(^_^)/\n" +
            "              | |\n" +
            "             /   \\\n" +
            "         VOCE GANHOU!\n"
        );
    }
}

/* =======================
   PARTE 4 – EMPATE
   ======================= */
class Parte4 {

    public static void main(String[] args) {
        exibirEmpate();
    }

    public static void exibirEmpate() {
        System.out.println("\n=======================================");
        System.out.println(" DEU VELHA! EMPATE!");
        System.out.println(" Ninguem venceu dessa vez!");
        System.out.println("=======================================\n");

        System.out.println(
            "        +-------+     +-------+\n" +
            "        |   O   |     |   O   |\n" +
            "        |   |   |     |   |   |\n" +
            "        |  / \\  |     |  / \\  |\n" +
            "        +-------+  X  +-------+\n"
        );
    }
}
