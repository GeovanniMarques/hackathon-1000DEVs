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

        // Definimos aqui qual é o caractere que cada jogador irá utilizar no jogo.
        //TODO 01: chame as funções obterCaractereUsuario() e obterCaractereComputador
        //para definir quais caracteres da lista de caracteres aceitos que o jogador
        //quer configurar para ele e para o computador.
        char caractereUsuario = obterCaractereUsuario();
        char caractereComputador = obterCaractereComputador(caractereUsuario);

        // Esta variavel é utilizada para definir se o usuário começa a jogar ou não.
        // Valor true, usuario começa jogando, valor false computador começa.
        //TODO 02: obtenha o valor booleano sorteado
        boolean vezUsuarioJogar = sortearValorBooleano();

        boolean jogoContinua;

        do {
            // controla se o jogo terminou
            jogoContinua = true;
            exibirTabuleiro();

            if (vezUsuarioJogar){
               
                //TODO 03: Execute a chamada processar vez do usuario
                processarVezUsuario(caractereUsuario);

                // Verifica se o usuario venceu
                //TODO 04: Este if deve executar apenas se teve ganhador 
                if (teveGanhador(caractereUsuario)) {
                    exibirTabuleiro();
                    exibirVitoriaUsuario();
                    jogoContinua = false;
                }

                // define que na proxima execucao do laco o jogador nao joga, ou seja, será a vez do computador
                vezUsuarioJogar = false;
            } else {

                //TODO 05: Execute a chamada processar vez do computador
                processarVezComputador(caractereComputador);
                
                // Verifica se o computador venceu
                //TODO 06: Este if deve executar apenas se teve ganhador
                if (teveGanhador(caractereComputador)) {
                    //TODO 07: Exiba que o computador ganhou
                    exibirTabuleiro();
                    exibirVitoriaComputador();
                    jogoContinua = false;
                }

                //TODO 08: defina qual o vaor a variavel abaixo deve possuir para que a proxima execucao do laco seja a vez do usuário
                vezUsuarioJogar = true;
            }
        
            //TODO 09: Este if deve executar apenas se o jogo continua E 
            //ocorreu tempate. Utilize o metodo teveEmpate()
            if (jogoContinua && teveEmpate()) {
                exibirTabuleiro();
                exibirEmpate();
                jogoContinua = false;
            }
        } while (jogoContinua);

        teclado.close();
    }


    /*
     * Descrição: Utilizado para iniciar a matriz/tabuleiro com o caractere ' '
     * espaço, no início do jogo. Matrizes de char precisam ter um valor
     * diferente de '' vazio. A idéia é, se tiver ' ' espaço, a posição está
     * livre. Qualquer outro caractere presente na posição, representa o
     * caractere do jogador em questão: usuário ou computador. Um exemplo seria,
     * 'X' para usuário e 'O' para computador. Para o primeiro nível de
     * complexidade considere um tabuleiro apenas de tamanho 3x3, 3 linhas e 3
     * colunas. 
     * Nível de complexidade: 3 de 10
     */
    static void inicializarTabuleiro() {
        //TODO 10: Implementar método conforme explicação

        
    for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
        for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
            tabuleiro[i][j] = ' ';
        }
    }


    }

    /*
     * Descrição: Utilizado para obter no início do jogo qual o caractere que o
     * usuário quer utilizar para representar ele próprio. Este método recebe o
     * teclado para permitir que o usuário digite o caractere desejado. Faça a
     * leitura do caractere desejado pelo usuário, através do teclado, realize
     * as validações para não aceitar caracteres que não estejam definidos pela
     * constante CARACTERES_IDENTIFICADORES_ACEITOS, e retorne o caractere lido
     * através do return.
     * Nível de complexidade: 4 de 10
     */
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
        //TODO 11: Implementar método conforme explicação

    }

    /*
     * Descrição: Utilizado para obter no início do jogo qual o caractere que o
     * usuário quer utilizar para representar o computador. Este método recebe o
     * teclado e recebe o caractere que foi configurado para o usuário, pois o
     * usuário e o computador não podem jogar com o mesmo caractere. Por exemplo,
     * se o usuário configurou para ele o caractere X ele não pode escolher o X
     * como o caractere também para o computador. Neste método apenas os seguintes
     * caracteres definidos pela constante CARACTERES_IDENTIFICADORES_ACEITOS devem
     * ser aceitos. Lembre-se que o caractere armazenado em caractereUsuario não
     * pode ser aceito. Após realizar a leitura do caractere pelo teclado e
     * validá-lo, faça o return deste caractere.
     * Nível de complexidade: 4 de 10
     */
    static char obterCaractereComputador(char caractereUsuario) {
        char caractereComputador;
        caractereUsuario = Character.toUpperCase(caractereUsuario);
        int posicao;

        do{
            String entrada = teclado.nextLine();
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
        //TODO 12: Implementar método conforme explicação
    }

    /*
     * Descrição: Utilizado para validar se a jogada do usuário é uma jogada válida.
     * Uma jogada é considerada válida quando ela está presente dentro da lista de
     * posicoesLivres. Desta forma, o método recebe a string com as posições livres,
     * além da linha e coluna jogada pelo usuário. O método verifica se a linha e
     * coluna está presente dentro da string de posições livres, se estiver retorna
     * true se não retorna false. Para descobrir se a linha e coluna esta presente
     * dentro da lista de posições livres pense em usar método contanis da string.
     * Nível de complexidade: 3 de 10
     */
    static boolean jogadaValida(String posicoesLivres, int linha, int coluna) {
        //TODO 13: Implementar método conforme explicação

        String jogada = "" + linha + coluna;
        return posicoesLivres.contains(jogada);
    }

    /*
     * Descrição: Utilizado para obter do usuário a linha e a coluna que ele deseja
     * jogar. Para isto o método deve exibir um mensagem informando que o jogador
     * deve digitar a linha e a coluna separados por um espaço. O método deve
     * realizar as validações necessárias para os casos do usuário não digitar
     * dois valores e também para o caso do usuário não digitar números.
     * O método deve garantir que o usuário digite os valores conforme solicitado
     * e devolva os valores lidos somente quando estes atenderam as regras.
     * Após a leitura dos valores de linha e coluna, o método deve retornar os
     * valores já no formato de índice, ou seja, no tabuleiro exibimos para o
     * usuário linha 1, linha 2, linha 3, coluna 1, coluna 2 e coluna 3. O
     * usuário digita os valores neste formato, no entanto o método ao retonar
     * os valores deve ajustar a linha 1 para o índice 0, a linha 2 para o índice
     * 1 e assim sucessivamente, da mesma forma que as colunas.
     * Após a validação e ajuste dos índices, o método deve verificar se a jogada
     * do usuário está presente na lista de posicoesLivres que ele recebeu como
     * parametro. Para isto, o método faz a chamada ao método jogadaValida()
     * para determinar se a jogada é aceita. Se a jogada não for aceita, é exibido
     * uma mensagem informando que a jogada não é permitida e reinicia o processo de
     * leitura de uma nova jogada. Se a jogada for aceita deve devolver os
     * valores no formato de um vetor de inteiro de duas posições. No índice 0 deste
     * vetor, deve ser armazenado o valor da linha jogada pelo usuário e no índice 1
     * do vetor, deve ser armazenado a coluna jogada pelo usuário.
     * Nível de complexidade: 5 de 10
     */
    static int[] obterJogadaUsuario(String posicoesLivres, Scanner teclado) {
        //TODO 14: Implementar método conforme explicação
            while (true) {
                System.out.println("Digite a linha e a coluna (ex: 1 3):");

                String entrada = teclado.nextLine().trim();

                // Verifica se a pessoa digitou dois valores
                String[] partes = entrada.split(" ");
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


    /*
     * Descrição: Utilizado para obter do computador a linha e a coluna sorteada.
     * Para isto o método utiliza as posições livres que ele recebeu como parametro.
     * Como as posições livres estão no formato de string, uma sugestão é conveter a
     * lista de pares linhacoluna que estão separados por ; em um vetor de String.
     * Pense em utilizar o método split. A conversão para um vetor de string será
     * útil para o próximo passo que é sortear uma posição livre.
     * Para sortear uma das posições no vetor de posições livres, utilize o método
     * random.nextInt() para sortear um número que esteja no intervalo de 0 até a
     * quantidade de posições no vetor de posições livres. Pesquise pelo método
     * random.nextInt() na internet para entender como ele funciona.
     * Após o random sortear um número, utilize este número como o valor da posição
     * do índice para acessar a jogada dentro do vetor de jogadas livres.
     * Ao realizar este procedimento você terá uma jogada no formato xy onde x é
     * a linha livre e y a coluna livre. Como o método obterJogadaComputador
     * precisa devolver um vetor de inteiro é necessário converter esta string para
     * um vetor de inteiro. Utilize para isto o método
     * converterJogadaStringParaVetorInt(). Após a conversão, devolva o vetor de
     * inteiro através do comando return. Para o nível de complexidade inicial,
     * com esta implementação o computador não terá "inteligência" para se defender
     * e nem para tentar ganhar.
     * Nível de complexidade: 6 de 10
     */
    static int[] obterJogadaComputador(String posicoesLivres, Scanner teclado) {
        String[] vetorPosicoes = posicoesLivres.split(";");


        java.util.Random random = new java.util.Random();
        int indiceSorteado = random.nextInt(vetorPosicoes.length);


        String jogadaSorteada = vetorPosicoes[indiceSorteado];

        int linha = jogadaSorteada.charAt(0) - '0';
        int coluna = jogadaSorteada.charAt(1) - '0';

        return new int[] { linha, coluna };
        //TODO 15: Implementar método conforme explicação
    }

    /*
     * Descrição: Utilizado para converter uma jogada no formato xy (linha/coluna)
     * de string para um vetor de int. Para isto, este método recebe a jogada no
     * formato string e deve colocar o valor de x dentro do índice 0 do vetor de
     * inteiro e deve colocar o valor de y dentro do índice 1 do vetor de inteiro.
     * Após a construção do vetor de inteiro retorne este vetor com o comando
     * return.
     * Nível de complexidade: 4 de 10
     */
    static int[] converterJogadaStringParaVetorInt(String jogada) {
        //TODO 16: Implementar método conforme explicação
            int[] vetor = new int[2];

            vetor[0] = jogada.charAt(0) - '0';  // linha
            vetor[1] = jogada.charAt(1) - '0';  // coluna

            return vetor;
        }

    /*
     * Descrição: Utilizado para realizar as ações necessárias para processar a vez
     * do usuário jogar. Este método deve exibir uma mensagem que é a vez do usuário
     * jogar. Este método é encarregado de obter a jogada do usuário através do
     * método obterJogadaUsuario, depois realizar a atualização do tabuleiro através
     * do método atualizaTabuleiro. Lembre-se que para chamar o método obterJogadaUsuario
     * é necessário saber quais posições estão livres
     * Nível de complexidade: 5 de 10
     */
    static void processarVezUsuario(char caractereUsuario) {
        //TODO 17: Implementar método conforme explicação
        System.out.println("Vez do usuário jogar!");

        String posicoesLivres = retornarPosicoesLivres();
        
        int[] jogada = obterJogadaUsuario(posicoesLivres, teclado);
        
        atualizaTabuleiro(jogada, caractereUsuario);
    }

    /*
     * Descrição: Utilizado para realizar as ações necessárias para processar a vez
     * do computador jogar. Este método é encarregado de obter a jogada do
     * computador através do método obterJogadaComputador, depois realizar a
     * atualização do tabuleiro através do método atualizaTabuleiro. 
     * Lembre-se que para chamar o método obterJogadaUsuario
     * é necessário saber quais posições estão livres 
     * Nível de complexidade: 5 de 10 se o computador for jogar aleatoriamente
     * Nível de complexidade: 8 de 10 se o computador for jogar sempre para se defender
     * Nível de complexidade: 10 de 10 se o computador for jogar para ganhar
     */
    static void processarVezComputador(char caractereComputador) {
        //TODO 18: Implementar método conforme explicação
        System.out.println("Vez do computador jogar!");

        String posicoesLivres = retornarPosicoesLivres();
        
        int[] jogada = obterJogadaComputador(posicoesLivres, teclado);
        
        atualizaTabuleiro(jogada, caractereComputador);
    }

    /*
     * Descrição: Utilizado para identificar a lista de posições livres no
     * tabuleiro. Esta lista é uma string no formato xy. Onde x é a linha e y a
     * coluna. Se existir mais de uma posição livre, teremos uma lista de valores xy
     * separados por ; exemplo: 00;01;20; Neste exemplo as posições linha 0 e
     * coluna 0; linha 0 e coluna 1; linha 2 e coluna 0 estão livres.
     * Lembre-se que os índices nas matrizes iniciam em 0. Para o primeiro nível
     * de complexidade considere um tabuleiro apenas de tamanho 3x3, 3 linhas e 3
     * colunas. Depois de montar a string retorne a mesma através do comando return
     * Nível de complexidade: 5 de 10
     */
    static String retornarPosicoesLivres() {
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
        //TODO 19: Implementar método conforme explicação
    }
    
    
    /*
     * Descrição: Utilizado para verificar se o jogador identificado por
     * caractereJogador ganhou o jogo. No jogo da velha um usuário ganha
     * quando ele completa uma linha ou uma coluna ou uma diagonal. Assim
     * este método verifica todas as possibilidades. No entanto, este método
     * utiliza outros métodos para auxiliar nesta verificação. Para identificar
     * se o usuário em questão ganhou na linha, é invocado o método
     * teveGanhadorLinha(), para identificar na coluna é invocado o método
     * teveGanhadorColuna(), para identificar na diagonal principal é invocado
     * o método teveGanhadorDiagonalPrincipal() e para identificar na diagonal
     * secundária é utilizado o método teveGanhadorDiagonalSecundaria(). Se
     * o pelo menos um destes métodos retornar verdadeiro, o método teveGanhador
     * retorna true, caso contrário retorna false
     * Nível de complexidade: 4 de 10 se o tabuleiro for fixo 3x3
     * Nível de complexidade: 8 de 10 se o tabuleiro dinâmico 
     */
    static boolean teveGanhador(char caractereJogador) {
        if (teveGanhadorLinha(caractereJogador)) return true;
        if (teveGanhadorColuna(caractereJogador)) return true;
        if (teveGanhadorDiagonalPrincipal(caractereJogador)) return true;
        if (teveGanhadorDiagonalSecundaria(caractereJogador)) return true;
        return false;
        //TODO 20: Implementar método conforme explicação
    }

    /*
     * Descrição: Todos os métodos abaixo, teveGanhador... funcionam da mesma forma.
     * Recebem como parametro o tabuleiro e o caractereJogador. Cada um dos métodos
     * verificam no tabuleiro se o caractere do jogador está presente em todas as
     * posições, ou seja, o método teveGanhadorLinha verifica em todas as posicoes
     * de uma determinada linha se elas estão preenchidas com o caractere informado
     * no caractereJogador. Se estiver presente retorna true, caso contrário retorna
     * false.
     * Nível de complexidade: 4 de 10 se o tabuleiro for fixo 3x3
     * Nível de complexidade: 8 de 10 se o tabuleiro dinâmico 
     */
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

    static boolean teveGanhadorDiagonalPrincipal( char caractereJogador) {
        //TODO 23: Implementar método conforme explicação
            for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
                if (tabuleiro[i][i] != caractereJogador) {
                    return false;
                }
            }
            return true;
        }

    static boolean teveGanhadorDiagonalSecundaria(char caractereJogador) {
        //TODO 24: Implementar método conforme explicação
            for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
                int col = TAMANHO_TABULEIRO - 1 - i; // coluna invertida
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
        
        //TODO 25: Implementar método conforme explicação        


    /*
     * Descrição: Utilizado para imprimir o tabuleiro o conteúdo do tabuleiro na
     * tela. Recebe o tabuleiro como parametro e imprime o conteúdo de cada posição
     * do tabuleiro na tela. Imprimi o conteúdo no formato de uma grade. Para o
     * primeiro nível de complexidade considere um tabuleiro apenas de tamanho 3x3,
     * 3 linhas e 3 colunas.
     * Nível de complexidade: 4 de 10
     */
    static void exibirTabuleiro() {
        char[][] tabuleiro = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        
        
            };

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

    



        
        
        //TODO 26: Implementar método conforme explicação
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
        //TODO 27: Implementar método conforme explicação
        int linha = jogada[0];
        int coluna = jogada[1];
        tabuleiro[linha][coluna] = caractereJogador;
    }

    /*
     * Descrição: Utilizado para exibir a frase: O computador venceu!, e uma ART
     * ASCII do computador feliz. Este método é utilizado quando é identificado que
     * o computador venceu a partida. Lembre-se que para imprimir uma contrabara \ é
     * necessário duas contra barras \\
     * Nível Complexidade: 2 de 10
     */
    static void exibirVitoriaComputador() {

        System.out.println("\n=======================================");
    System.out.println(" 💻 O COMPUTADOR VENCEU! 💻");
    System.out.println("   Ele está todo feliz! 😄");
    System.out.println("=======================================\n");

    System.out.println(
        "        ______________________ \n" +
        "       |                      |\n" +
        "       |   (^‿^)  YOU LOSE!   |\n" +
        "       |______________________|\n" +
        "          ||            ||     \n" +
        "          ||   ____     ||     \n" +
        "          ||  |    |    ||     \n" +
        "          ||  |____|    ||     \n" +
        "         (_|____________|_)    \n"
    );
    }
        //TODO 28: Implementar método conforme explicação
    

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

    System.out.println(
        "          \\(^_^)/        \n" +
        "           /| |\\         \n" +
        "            | |          \n" +
        "           /   \\         \n" +
        "        VOCÊ ARRASOU!    \n"
    );
}
        //TODO 29: Implementar método conforme explicação
    

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

    System.out.println(
        "       ┌─────────┐   \n" +
        "       │   0     │   \n" +
        "       │   │     │   \n" +
        "       │  / \\    │   \n" +
        "       │         │   \n" +
        "       └─────────┘   \n" +
        "           X         \n" +
        "       ┌─────────┐   \n" +
        "       │   0     │   \n" +
        "       │   │     │   \n" +
        "       │  / \\    │   \n" +
        "       │         │   \n" +
        "       └─────────┘   \n"
    );

        //TODO 30: Implementar método conforme explicação
    }

    /*
     * Descrição: Utilizado para analisar se ocorreu empate no jogo. Para o primeiro
     * nível de deficuldade, basta verificar se todas as posições do tabuleiro não
     * estão preenchidas com o caractere ' '. Não se preocupe se teve ganhador, não
     * é responsabilidade deste método esta análise. Sugestão: pense em utilizar a
     * função retornarPosicoesLivres. Retorne true se teve empate ou false
     * Nível de complexidade: 3 de 10
     */
    static boolean teveEmpate() {
        //TODO 31: Implementar método conforme explicação
        String posicoesLivres = retornarPosicoesLivres();
        return posicoesLivres.isEmpty();
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
        //TODO 32: Implementar método conforme explicação
    }


}