import java.io.Serializable;

public interface ElementoSistema extends Serializable {

    String getNome();

    long getTamanho();

    void exibir(String indentacao);
}
