package Utilitarios;

/**
 *
 * @author Wagner
 */
public class RegistroProdutos {

    private int codigoProduto;
    private String referenciaProduto;
    private float valorUnitarioProduto;

    public RegistroProdutos(int codigoProduto, String referenciaProduto, float valorUnitarioProduto) {
        setCodigoProduto(codigoProduto);
        setReferenciaProduto(referenciaProduto);
        setValorUnitarioProduto(valorUnitarioProduto);
    }

    public RegistroProdutos(String referenciaProduto, float valorUnitarioProduto) {
        setReferenciaProduto(referenciaProduto);
        setValorUnitarioProduto(valorUnitarioProduto);
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public String getReferenciaProduto() {
        return referenciaProduto;
    }

    public float getValorUnitarioProduto() {
        return valorUnitarioProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public void setReferenciaProduto(String referenciaProduto) {
        this.referenciaProduto = referenciaProduto;
    }

    public void setValorUnitarioProduto(float valorUnitarioProduto) {
        this.valorUnitarioProduto = valorUnitarioProduto;
    }
}
