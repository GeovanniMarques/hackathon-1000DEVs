package ia;

public class InteligenciaIA {

    public static String retornarPosicoesLivres(char[][] tabuleiro){
        StringBuilder posicoes = new StringBuilder();
        int tamanho = tabuleiro.length;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (tabuleiro [i][j] ==' ') {
                    posicoes.append(i).append(j).append(";");
                }
            }
        }
        return posicoes.toString();
    }

    public static int[] obterJogadaComputador(String posicoesLivres) {
        String[] vetorPosicoes = posicoesLivres.split(";");


        java.util.Random random = new java.util.Random();
        int indiceSorteado = random.nextInt(vetorPosicoes.length);


        String jogadaSorteada = vetorPosicoes[indiceSorteado];

        int linha = jogadaSorteada.charAt(0) - '0';
        int coluna = jogadaSorteada.charAt(1) - '0';

        return new int[] { linha, coluna };
    }

}
