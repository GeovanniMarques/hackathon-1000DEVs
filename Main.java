public class Main {

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
            System.out.print((i + 1) + "   "); // números do lado
        System.out.println(tabuleiro[i][0] + " | " + tabuleiro[i][1] + " | " + tabuleiro[i][2]);

        if (i < 2) {
            System.out.println("   ---+---+---");

            }
        }
        System.out.println();
    }
}