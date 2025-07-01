package Utilitarios;

/**
 *
 * @author Wagner
 */
public class RegistroListConsultaProjeto {

    private int codigo;
    private String nome;
    private float valor;

    public RegistroListConsultaProjeto(int codigo, String nome, float valor) {
        setCodigo(codigo);
        setNome(nome);
        setValor(valor);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public float getValor() {
        return valor;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String toString() {
        return getCodigo()+" - "+getNome()+"\nValor: "+getValor();
    }
}



