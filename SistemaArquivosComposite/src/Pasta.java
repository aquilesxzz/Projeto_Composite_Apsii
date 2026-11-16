
import java.util.ArrayList;
import java.util.List;

public class Pasta implements ElementoSistema {

    private static final long serialVersionUID = 1L;

    private String nome;
    private List<ElementoSistema> elementos;

    public Pasta(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O nome da pasta não pode ficar vazio.");
        }

        this.nome = nome.trim();
        this.elementos = new ArrayList<>();
    }

    public void adicionar(ElementoSistema elemento) {

        if (elemento == null) {
            throw new IllegalArgumentException(
                    "Elemento inválido.");
        }

        if (elemento == this) {
            throw new IllegalArgumentException(
                    "Uma pasta não pode conter a si mesma.");
        }

        if (elemento instanceof Pasta) {
            Pasta outra = (Pasta) elemento;

            if (outra.contem(this)) {
                throw new IllegalArgumentException(
                        "Essa operação criaria um ciclo.");
            }
        }

        for (ElementoSistema existente : elementos) {
            if (existente.getNome().equalsIgnoreCase(
                    elemento.getNome())) {
                throw new IllegalArgumentException(
                        "Já existe um elemento com esse nome.");
            }
        }

        elementos.add(elemento);
    }

    public boolean remover(String nome) {

        for (ElementoSistema elemento : elementos) {

            if (elemento.getNome().equalsIgnoreCase(nome)) {
                elementos.remove(elemento);
                return true;
            }
        }

        return false;
    }

    public ElementoSistema buscar(String nome) {

        for (ElementoSistema elemento : elementos) {
            if (elemento.getNome().equalsIgnoreCase(nome)) {
                return elemento;
            }
        }

        return null;
    }

    private boolean contem(Pasta procurada) {

        if (this == procurada) {
            return true;
        }

        for (ElementoSistema elemento : elementos) {

            if (elemento instanceof Pasta) {
                if (((Pasta) elemento).contem(procurada)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public long getTamanho() {

        long total = 0;

        for (ElementoSistema elemento : elementos) {
            total += elemento.getTamanho();
        }

        return total;
    }

    @Override
    public void exibir(String indentacao) {

        System.out.println(indentacao + "|-- " + nome + "/");

        for (ElementoSistema elemento : elementos) {
            elemento.exibir(indentacao + "    ");
        }
    }
}
