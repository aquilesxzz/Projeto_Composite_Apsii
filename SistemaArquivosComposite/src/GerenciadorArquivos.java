public class GerenciadorArquivos {

    private Pasta raiz;
    private RepositorioSistema repositorio;

    public GerenciadorArquivos(RepositorioSistema repositorio) {
        this.repositorio = repositorio;
        this.raiz = repositorio.carregar();
    }

    public void criarPasta(String caminho, String nome) {
        Pasta destino = localizarPasta(caminho);

        destino.adicionar(new Pasta(nome));

        repositorio.salvar(raiz);
    }

    public void criarArquivo(String caminho, String nome, long tamanho) {

        Pasta destino = localizarPasta(caminho);

        destino.adicionar(new Arquivo(nome, tamanho));

        repositorio.salvar(raiz);
    }

    public boolean removerElemento(String caminho, String nome) {

        Pasta destino = localizarPasta(caminho);

        boolean removido = destino.remover(nome);

        if (removido) {
            repositorio.salvar(raiz);
        }

        return removido;
    }

    public void listarPasta(String caminho) {
        localizarPasta(caminho).exibir("");
    }

    public long calcularTamanho(String caminho) {
        return localizarPasta(caminho).getTamanho();
    }

    public void exibirArvore() {
        raiz.exibir("");
    }

    private Pasta localizarPasta(String caminho) {

        Pasta atual = raiz;

        String[] partes = caminho.trim()
                .replace("\\", "/").split("/");

        int inicio = 0;

        if (partes.length > 0 &&
                partes[0].equalsIgnoreCase(raiz.getNome())) {
            inicio = 1;
        }

        for (int i = inicio; i < partes.length; i++) {

            if (partes[i].isEmpty()) {
                continue;
            }

            ElementoSistema elemento = atual.buscar(partes[i]);

            if (!(elemento instanceof Pasta)) {
                throw new IllegalArgumentException(
                        "Pasta não encontrada: " + partes[i]);
            }

            atual = (Pasta) elemento;
        }

        return atual;
    }
}
