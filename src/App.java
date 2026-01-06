import java.util.Random;
import java.util.Scanner;

public class App {
    // Estes caracteres são aceitos como caracteres para representarem
    // os jogadores. Utizado para evitar caracteres que não combinem com
    // o desenho do tabuleiro.
    final static String CARACTERES_IDENTIFICADORES_ACEITOS = "XOUC"; //U -> usuário, C -> Computador

    // Tamanho do tabuleiro 3x3. Para o primeiro nivel de dificuldade
    // considere que este valor não será alterado. 
    // Depois que você conseguir implementar o raciocionio para o tabuleiro 3x3
    // tente ajustar o código para funcionar para qualquer tamanho de tabuleiro
    final static int TAMANHO_TABULEIRO = 3;

    static char[][] tabuleiro = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
    
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        
        inicializarTabuleiro();

        char caractereUsuario = obterCaractereUsuario();
        char caractereComputador = obterCaractereComputador(caractereUsuario);

        boolean vezUsuarioJogar = sortearValorBooleano();

        boolean jogoContinua;

        do {
            // controla se o jogo terminou
            jogoContinua = true;
            limparTela();
            exibirTabuleiro();

            if (vezUsuarioJogar){
               
                processarVezUsuario(caractereUsuario);

                if (teveGanhador(caractereUsuario)) {
                    exibirTabuleiro();
                    exibirVitoriaUsuario();
                    jogoContinua = false;
                }

                // define que na proxima execucao do laco o jogador nao joga, ou seja, será a vez do computador
                vezUsuarioJogar = false;
            } else {

                processarVezComputador(caractereComputador);
                
                if (teveGanhador(caractereComputador)) {
                    exibirTabuleiro();
                    exibirVitoriaComputador();
                    jogoContinua = false;
                }

                vezUsuarioJogar = true;
            }
        
            if (jogoContinua && teveEmpate()) {
                exibirTabuleiro();
                exibirEmpate();
                jogoContinua = false;
            }
        } while (jogoContinua);

        teclado.close();
    }


    static void inicializarTabuleiro() {

        
    for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
        for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
            tabuleiro[i][j] = ' ';
        }
    }


    }

    static char obterCaractereUsuario() {
        char caractereUsuario;
        int posicao;

        do {
            String entrada = teclado.next();
            caractereUsuario = entrada.charAt(0);

            posicao = CARACTERES_IDENTIFICADORES_ACEITOS.indexOf(caractereUsuario);

            if (posicao != -1) {
                System.out.println("Verificação validada");
            } else {
                System.out.println("Verificação não validada");
            }
        } while (posicao == -1);

        return caractereUsuario;
    }

    static char obterCaractereComputador(char caractereUsuario) {
        char caractereComputador;
        caractereUsuario = Character.toUpperCase(caractereUsuario);
        int posicao;

        do{
            String entrada = teclado.next();
            caractereComputador = Character.toUpperCase(entrada.charAt(0));

            posicao = CARACTERES_IDENTIFICADORES_ACEITOS.indexOf(caractereComputador);

            if (posicao == -1) {
                System.out.println("Caractere inválido. Use apenas: " + CARACTERES_IDENTIFICADORES_ACEITOS);
            } else if (caractereComputador == caractereUsuario) {
            System.out.println("O computador não pode usar o mesmo caractere do usuário.");
            posicao = -1;
            }   
            } while(posicao == -1);

        return caractereComputador;
    }

    static boolean jogadaValida(String posicoesLivres, int linha, int coluna) {
        String jogada = "" + linha + coluna;
        return posicoesLivres.contains(jogada);
    }

    static int[] obterJogadaUsuario(String posicoesLivres, Scanner teclado) {
            while (true) {
                System.out.println("Digite a linha e a coluna (ex: 1 3):");

                String entrada = teclado.nextLine();
                if (entrada == null) continue;
                entrada = entrada.trim();
                if (entrada.isEmpty()) continue;

                // Verifica se a pessoa digitou dois valores (aceita múltiplos espaços)
                String[] partes = entrada.split("\\s+");
                if (partes.length != 2) {
                    System.out.println("Erro: você deve digitar exatamente dois números separados por espaço.");
                    continue;
                }

                int linha, coluna;

                try {
                    linha = Integer.parseInt(partes[0]);
                    coluna = Integer.parseInt(partes[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Erro: digite apenas números.");
                    continue;
                }

                // Ajustar para índice de matriz
                linha--;
                coluna--;

                // Validar se está dentro do tabuleiro
                if (linha < 0 || linha >= TAMANHO_TABULEIRO || coluna < 0 || coluna >= TAMANHO_TABULEIRO) {
                    System.out.println("Posição fora do tabuleiro. Tente novamente.");
                    continue;
                }

                // Validar se é uma jogada permitida
                if (!jogadaValida(posicoesLivres, linha, coluna)) {
                    System.out.println("Jogada inválida! Essa posição já está ocupada.");
                    continue;
                }

                // Está tudo OK → devolve vetor
                return new int[]{linha, coluna};
            }
        }


    static int[] obterJogadaComputador(String posicoesLivres, Scanner teclado) {
        // remove eventual ponto-e-vírgula final e divide em posições válidas
        if (posicoesLivres.endsWith(";")) {
            posicoesLivres = posicoesLivres.substring(0, posicoesLivres.length() - 1);
        }
        String[] vetorPosicoes = posicoesLivres.isEmpty() ? new String[0] : posicoesLivres.split(";");

        java.util.Random random = new java.util.Random();
        int indiceSorteado = random.nextInt(vetorPosicoes.length);

        String jogadaSorteada = vetorPosicoes[indiceSorteado];

        int linha = jogadaSorteada.charAt(0) - '0';
        int coluna = jogadaSorteada.charAt(1) - '0';

        return new int[] { linha, coluna };
    }

    static void processarVezUsuario(char caractereUsuario) {
        System.out.println("Vez do usuário jogar!");

        String posicoesLivres = retornarPosicoesLivres();
        
        int[] jogada = obterJogadaUsuario(posicoesLivres, teclado);
        
        atualizaTabuleiro(jogada, caractereUsuario);
    }

    static void processarVezComputador(char caractereComputador) {
        System.out.println("Vez do computador jogar!");

        String posicoesLivres = retornarPosicoesLivres();
        
        int[] jogada = obterJogadaComputador(posicoesLivres, teclado);
        
        atualizaTabuleiro(jogada, caractereComputador);
    }

    static String retornarPosicoesLivres() {
        StringBuilder posicoes = new StringBuilder();
        int tamanho = tabuleiro.length;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (tabuleiro [i][j] ==' ') {
                    if (posicoes.length() > 0) posicoes.append(";");
                    posicoes.append(i).append(j);
                }
            }
        }
        return posicoes.toString();
    }
    
    
    static boolean teveGanhador(char caractereJogador) {
        return teveGanhadorLinha(caractereJogador) || 
               teveGanhadorColuna(caractereJogador) || 
               teveGanhadorDiagonalPrincipal(caractereJogador) || 
               teveGanhadorDiagonalSecundaria(caractereJogador);
    }

   static boolean teveGanhadorLinha(char caractereJogador) {
    // Percorre cada linha do tabuleiro (0, 1, 2)
    for (int linha = 0; linha < TAMANHO_TABULEIRO; linha++) {
        
        // Assume que o jogador ganhou esta linha
        boolean ganhouLinha = true;
        
        // Percorre cada coluna desta linha (0, 1, 2)
        for (int coluna = 0; coluna < TAMANHO_TABULEIRO; coluna++) {
            
            // Verifica se a posição é diferente do caractere do jogador
            if (tabuleiro[linha][coluna] != caractereJogador) {
                // Se encontrou algo diferente, não ganhou esta linha
                ganhouLinha = false;
                break;
            }
        }
        
        // Se ganhou, ainda é true, o jogador ganhou!
        if (ganhouLinha) {
            return true;
        }
    }
    
    // Não ganhou em nenhuma linha
    return false;
}

    static boolean teveGanhadorColuna(char caractereJogador) {
    // Percorre cada coluna do tabuleiro (0, 1, 2)
    for (int coluna = 0; coluna < TAMANHO_TABULEIRO; coluna++) {
        
        // Assume que o jogador ganhou esta coluna
        boolean ganhouColuna = true;
        
        // Percorre cada linha desta coluna (0, 1, 2)
        for (int linha = 0; linha < TAMANHO_TABULEIRO; linha++) {
            
            // Verifica se a posição é diferente do caractere do jogador
            if (tabuleiro[linha][coluna] != caractereJogador) {
                // Se encontrou algo diferente, não ganhou esta coluna
                ganhouColuna = false;
                break;
            }
        }
        
        // Se ganhouColuna ainda é true, o jogador ganhou!
        if (ganhouColuna) {
            return true;
        }
    }
    
    // Não ganhou em nenhuma coluna
    return false;
}

    static boolean teveGanhadorDiagonalPrincipal(char caractereJogador) {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            if (tabuleiro[i][i] != caractereJogador) {
                return false;
            }
        }
        return true;
    }

    static boolean teveGanhadorDiagonalSecundaria(char caractereJogador) {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            int col = TAMANHO_TABULEIRO - 1 - i;
            if (tabuleiro[i][col] != caractereJogador) {
                return false;
            }
        }
        return true;
    }

    /*
     * Descrição: Utilizado para limpar a console, para que seja exibido apenas o
     * conteúdo atual do jogo. Dica: Pesquisa na internet por "Como limpar console
     * no java ProcessBuilder"
     * Nível de complexidade: 3 de 10
     */
    static void limparTela() {
         try {
        // Detecta o sistema operacional
        String sistemaOperacional = System.getProperty("os.name");
        
        if (sistemaOperacional.contains("Windows")) {
            // Comando para Windows
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            // Comando para Linux/Mac/Unix
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
    } catch (Exception e) {
        // Se houver erro, imprime várias linhas em branco 
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
        
    
    static void exibirTabuleiro() {
        System.out.println("\n    1   2   3");
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            System.out.print((i + 1) + "   "); // números do lado
            System.out.println(tabuleiro[i][0] + " | " + tabuleiro[i][1] + " | " + tabuleiro[i][2]);

            if (i < TAMANHO_TABULEIRO - 1) {
                System.out.println("   ---+---+---");
            }
        }
        System.out.println();
    }

    



        
        

    // execute no início deste método a chamada ao método limparTela
    // para garantir que seja exibido o tabuleiro sem nenhum conteúdo antes dele.

    /*
     * Descrição: Utilizado para atualizar o tabuleiro com o caractere que
     * identifica o jogador. Este método recebe o tabuleiro, um vetor jogada com
     * duas posicoes. jogada[0] representa a linha escolhida pelo jogador. jogada[1]
     * representa a coluna escolhida pelo jogador. Os valores armazenados no vetor
     * já deve estar no formato de índice, ou seja, se jogada[0] contiver o valor
     * 1 e jogada[1] contiver o valor 2, significa que o índice/linha 1 e
     * índice/coluna 2 da matriz devem ser atualizados com o caractere informado na
     * variável caractereJogador. Depois de atualizar o tabuleiro, o mesmo deve ser
     * retornado através do comando return
     * Nível de complexidade: 3 de 10
     */
    static void atualizaTabuleiro(int[] jogada, char caractereJogador) {
        int linha = jogada[0];
        int coluna = jogada[1];
        tabuleiro[linha][coluna] = caractereJogador;
    }

    static void exibirVitoriaComputador() {

        System.out.println("\n=======================================");
    System.out.println(" 💻 O COMPUTADOR VENCEU! 💻");
    System.out.println("   Ele está todo feliz! 😄");
    System.out.println("=======================================\n");

    System.out.println("""
        ______________________ 
       |                      |
       |   (^‿^)  YOU LOSE!   |
       |______________________|
          ||            ||     
          ||   ____     ||     
          ||  |    |    ||     
          ||  |____|    ||     
         (_|____________|_)    
        """);
    }

    /*
     * Descrição: Utilizado para exibir a frase: O usuário venceu!, e uma ARTE ASCII
     * do usuário feliz. Este método é utilizado quando é identificado que o usuário
     * venceu a partida. Lembre-se que para imprimir uma contrabara \ é necessário
     * duas contra barras \\
     * Nível Complexidade: 2 de 10
     */
    static void exibirVitoriaUsuario() {
            
    System.out.println("\n=======================================");
    System.out.println(" 🎉 VOCÊ VENCEU! PARABÉNS! 🎉");
    System.out.println("   O computador ficou triste... 😢");
    System.out.println("=======================================\n");

    System.out.println("""
          \\(^_^)/        
           /| |\\         
            | |          
           /   \\         
        VOCÊ ARRASOU!    
        """);
}

    /*
     * Descrição: Utilizado para exibir a frase: Ocorreu empate!, e uma ARTE ASCII
     * do placar 0 X 0. Este método é utilizado quando é identificado que ocorreu
     * empate. Lembre-se que para imprimir uma contrabara \ é necessário duas contra
     * barras \\
     * Nível Complexidade: 2 de 10
     */
    static void exibirEmpate() {

        System.out.println("\n=======================================");
    System.out.println(" 🤝 DEU VELHA! EMPATE! 🤝");
    System.out.println("     Ninguém venceu dessa vez!");
    System.out.println("=======================================\n");

    System.out.println("""
        ┌─────────┐   
        │   0     │   
        │   │     │   
        │  / \\    │   
        │         │   
        └─────────┘   
            X         
        ┌─────────┐   
        │   0     │   
        │   │     │   
        │  / \\    │   
        │         │   
        └─────────┘   
        """);
    }

    /*
     * Descrição: Utilizado para realizar o sorteio de um valor booleano. Este
     * método deve sortear um valor entre true ou false. Este valor será
     * utilizado para identificar quem começa a jogar. Dica: pesquise sobre
     * o método random.nextBoolean() na internet. Após ralizar o sorteio o 
     * método deve retornar o valor sorteado.
     * Nível de complexidade: 3 de 10
     */
    static boolean sortearValorBooleano() {
        Random random = new Random();

        return random.nextBoolean();
    }

    static boolean teveEmpate() {
        String posicoesLivres = retornarPosicoesLivres();
        return posicoesLivres.isEmpty();
    }

}