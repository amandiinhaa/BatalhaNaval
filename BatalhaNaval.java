import java.util.Random; // Importa a classe Random para gerar números aleatórios
import java.util.Scanner; // Importa a classe Scanner para ler a entrada do usuário

public class BatalhaNaval {

    // Instancia um objeto Scanner para ler a entrada do usuário
    Scanner scanner = new Scanner(System.in);

    // Matriz que guarda a posição dos navios
    char[][] tabuleiro = new char[8][8];
    // Matriz visível ao jogador, que será modificada durante o jogo
    char[][] tabuleiroVisivel = new char[8][8];

    // Número de navios restantes no jogo
    int naviosRestantes = 10;
    // Número de tentativas restantes para o jogador
    int tentativasRestantes = 30;

    // Método principal que inicia o jogo
    public static void main(String[] args) {
        new BatalhaNaval(); // Cria uma nova instância do jogo BatalhaNaval
    }

    // Construtor que inicializa e executa o jogo
    public BatalhaNaval() {
        inicializarTabuleiro(tabuleiro); // Inicializa o tabuleiro com água
        inicializarTabuleiro(tabuleiroVisivel); // Inicializa o tabuleiro visível com água
        posicionarNavios(); // Posiciona os navios aleatoriamente no tabuleiro

        // Loop do jogo até que todos os navios sejam destruídos ou as tentativas acabem
        while (naviosRestantes > 0 && tentativasRestantes > 0) {
            exibirTabuleiro(tabuleiroVisivel); // Exibe o tabuleiro visível
            //exibirTabuleiro(tabuleiro); // Exibe o tabuleiro com os navios apenas apra testarmos.

            // Exibe o número de tentativas e navios restantes
            System.out.println("Tentativas restantes: " + tentativasRestantes);
            System.out.println("Navios restantes: " + naviosRestantes);

            // Solicita as coordenadas ao jogador
            System.out.print("Digite a coordenada (linha): ");
            int linha = scanner.nextInt();
            System.out.print("Digite a coordenada (coluna): ");
            int coluna = scanner.nextInt();

            // Verifica se as coordenadas inseridas pelo jogador são válidas
            if (validarCoordenadas(linha, coluna)) {
                // Verifica se o jogador já atacou a posição
                if (tabuleiroVisivel[linha][coluna] == 'X' ||
                    tabuleiroVisivel[linha][coluna] == 'O') {
                    System.out.println("Você já atacou essa posição.");
                }
                // Verifica se o jogador acertou um navio
                else if (tabuleiro[linha][coluna] == 'N') {
                    System.out.println("Você acertou um navio!");
                    tabuleiroVisivel[linha][coluna] = 'X'; // Marca a posição no tabuleiro visível
                    naviosRestantes--; // Decrementa o número de navios restantes
                }
                // Caso o jogador erre
                else {
                    System.out.println("Você errou.");
                    tabuleiroVisivel[linha][coluna] = 'O'; // Marca a posição como errada no tabuleiro visível
                }
                tentativasRestantes--; // Decrementa o número de tentativas restantes
            }
            // Se as coordenadas forem inválidas
            else {
                System.out.println("Coordenadas inválidas. Tente novamente.");
            }
        }

        // Mensagem final dependendo do resultado do jogo
        if (naviosRestantes == 0) {
            System.out.println("Parabéns! Você destruiu todos os navios.");
        } else {
            System.out.println("Suas 30 tentativas acabaram!");
        }
    }

    // Inicializa o tabuleiro com água
    private static void inicializarTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = '~'; // '~' representa água
            }
        }
    }

    // Posiciona os navios aleatoriamente no tabuleiro
    private void posicionarNavios() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int linha = random.nextInt(8); // Gera uma linha aleatória
            int coluna = random.nextInt(8); // Gera uma coluna aleatória
            if (tabuleiro[linha][coluna] == '~') {
                tabuleiro[linha][coluna] = 'N'; // 'N' representa um navio
            } else {
                i--; // Se a posição já estiver ocupada, decrementa i para tentar novamente
            }
        }
    }

    // Exibe o tabuleiro visível ao jogador
    private static void exibirTabuleiro(char[][] tabuleiro) {
        System.out.println("Tabuleiro:");
        // Numera as linhas e colunas para o jogador
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int i = 0; i < tabuleiro.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println(); // Nova linha após cada linha do tabuleiro
        }
    }

    // Verifica se as coordenadas fornecidas estão dentro dos limites do tabuleiro
    private static boolean validarCoordenadas(int linha, int coluna) {
        return linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8;
    }
}