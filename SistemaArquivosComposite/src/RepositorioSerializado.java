import java.io.*;

public class RepositorioSerializado implements RepositorioSistema {

    private final String caminhoArquivo;

    public RepositorioSerializado(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    @Override
    public void salvar(Pasta raiz) {

        try (ObjectOutputStream saida =
                new ObjectOutputStream(
                    new FileOutputStream(caminhoArquivo))) {

            saida.writeObject(raiz);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao salvar os dados.", e);
        }
    }

    @Override
    public Pasta carregar() {

        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()) {
            return new Pasta("Documentos");
        }

        try (ObjectInputStream entrada =
                new ObjectInputStream(
                    new FileInputStream(arquivo))) {

            Object objeto = entrada.readObject();

            if (objeto instanceof Pasta) {
                return (Pasta) objeto;
            }

            throw new IOException("Arquivo de dados inválido.");

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(
                    "Erro ao carregar os dados.", e);
        }
    }
}
