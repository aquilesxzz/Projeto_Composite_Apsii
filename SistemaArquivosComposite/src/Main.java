
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final GerenciadorArquivos gerenciador =
            new GerenciadorArquivos(
                    new RepositorioSerializado("dados.dat"));

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE ARQUIVOS =====");
            System.out.println("1 - Criar pasta");
            System.out.println("2 - Criar arquivo");
            System.out.println("3 - Listar conteúdo de uma pasta");
            System.out.println("4 - Calcular tamanho de uma pasta");
            System.out.println("5 - Remover arquivo ou pasta");
            System.out.println("6 - Exibir árvore completa");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro();

            try {
                switch (opcao) {
                    case 1:
                        criarPasta();
                        break;
                    case 2:
                        criarArquivo();
                        break;
                    case 3:
                        listarPasta();
                        break;
                    case 4:
                        calcularTamanho();
                        break;
                    case 5:
                        removerElemento();
                        break;
                    case 6:
                        gerenciador.exibirArvore();
                        break;
                    case 0:
                        System.out.println("Sistema encerrado.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void criarPasta() {
        System.out.print("Caminho da pasta de destino: ");
        String caminho = lerTexto();

        System.out.print("Nome da nova pasta: ");
        String nome = lerTexto();

        gerenciador.criarPasta(caminho, nome);

        System.out.println("Pasta criada com sucesso!");
    }

    private static void criarArquivo() {
        System.out.print("Caminho da pasta de destino: ");
        String caminho = lerTexto();

        System.out.print("Nome do arquivo: ");
        String nome = lerTexto();

        System.out.print("Tamanho do arquivo em KB: ");
        long tamanho = lerLong();

        gerenciador.criarArquivo(caminho, nome, tamanho);

        System.out.println("Arquivo criado com sucesso!");
    }

    private static void listarPasta() {
        System.out.print("Caminho da pasta: ");
        String caminho = lerTexto();

        gerenciador.listarPasta(caminho);
    }

    private static void calcularTamanho() {
        System.out.print("Caminho da pasta: ");
        String caminho = lerTexto();

        long tamanho = gerenciador.calcularTamanho(caminho);

        System.out.println("Tamanho total: " + tamanho + " KB");
    }

    private static void removerElemento() {
        System.out.print("Caminho da pasta onde está o elemento: ");
        String caminho = lerTexto();

        System.out.print("Nome do arquivo ou pasta: ");
        String nome = lerTexto();

        boolean removido =
                gerenciador.removerElemento(caminho, nome);

        if (removido) {
            System.out.println("Elemento removido com sucesso!");
        } else {
            System.out.println("Elemento não encontrado.");
        }
    }

    private static String lerTexto() {
        String texto = scanner.nextLine().trim();

        while (texto.isEmpty()) {
            System.out.print("O campo não pode ficar vazio. Digite novamente: ");
            texto = scanner.nextLine().trim();
        }

        return texto;
    }

    private static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }

    private static long lerLong() {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }
}
