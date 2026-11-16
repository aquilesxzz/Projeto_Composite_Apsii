
public class Arquivo implements ElementoSistema {

    private static final long serialVersionUID = 1L;

    private String nome;
    private long tamanho;

    public Arquivo(String nome, long tamanho) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O nome do arquivo não pode ficar vazio.");
        }

        if (tamanho < 0) {
            throw new IllegalArgumentException(
                    "O tamanho não pode ser negativo.");
        }

        this.nome = nome.trim();
        this.tamanho = tamanho;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public long getTamanho() {
        return tamanho;
    }

    @Override
    public void exibir(String indentacao) {
        System.out.println(indentacao + "|-- " + nome
                + " (" + tamanho + " KB)");
    }
}
