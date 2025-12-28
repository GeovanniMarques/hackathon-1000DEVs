package ia;

public class InteligenciaIA {

    public static String retornarPosicoesLivres(char[][] tabuleiro){
        StringBuilder posicoes = new StringBuilder();
        int tamanho = tabuleiro.length;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; i++) {
                if (tabuleiro [i][j] ==' ') {
                    posicoes.append(i).append(j).append(";");
                }
            }
        }
        return posicoes.toString();
    }
}
