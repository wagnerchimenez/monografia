package Utilitarios;

/**
 *
 * @author Wagner
 */
public class RegistroProjetos {

    private int codigoProjeto;
    private String referenciaProjeto;
    private float valorProjeto;
    private String corProjeto;
    private int quantidadeProjeto;
    private String referenciaVidro;
    private float valorVidro;
    private float largura;
    private float altura;

    public RegistroProjetos(int codigoProjeto, String referenciaProjeto, float valorProjeto){
        setCodigoProjeto(codigoProjeto);
        setReferenciaProjeto(referenciaProjeto);
        setValorProjeto(valorProjeto);
    }

    public RegistroProjetos(int codigoProjeto, String referenciaProjeto, float valorProjeto, String corProjeto, int quantidadeProjeto, String referenciaVidro, float largura, float altura) {
        setCodigoProjeto(codigoProjeto);
        setReferenciaProjeto(referenciaProjeto);
        setValorProjeto(valorProjeto);
        setCorProjeto(corProjeto);
        setQuantidadeProjeto(quantidadeProjeto);
        setReferenciaVidro(referenciaVidro);
        setLargura(largura);
        setAltura(altura);
    }

    public RegistroProjetos(String referenciaProjeto, float valorProjeto) {
        setReferenciaProjeto(referenciaProjeto);
        setValorProjeto(valorProjeto);
    }

    public int getCodigoProjeto() {
        return codigoProjeto;
    }

    public String getReferenciaProjeto() {
        return referenciaProjeto;
    }

    public float getValorProjeto() {
        return valorProjeto;
    }

    public String getCorProjeto() {
        return corProjeto;
    }

    public int getQuantidadeProjeto() {
        return quantidadeProjeto;
    }

    public String getReferenciaVidro() {
        return referenciaVidro;
    }

    public float getValorVidro() {
        return valorVidro;
    }

    public float getLargura() {
        return largura;
    }

    public float getAltura() {
        return altura;
    }

    // Seters
    public void setCodigoProjeto(int codigoProjeto) {
        this.codigoProjeto = codigoProjeto;
    }

    public void setReferenciaProjeto(String referenciaProjeto) {
        this.referenciaProjeto = referenciaProjeto;
    }

    public void setCorProjeto(String corProjeto) {
        this.corProjeto = corProjeto;
    }

    public void setValorProjeto(float valorProjeto) {
        this.valorProjeto = valorProjeto;
    }

    public void setQuantidadeProjeto(int quantidadeProjeto) {
        this.quantidadeProjeto = quantidadeProjeto;
    }

    public void setReferenciaVidro(String referenciaVidro) {
        this.referenciaVidro = referenciaVidro;
    }

    public void setValorVidro(float valorVidro) {
        this.valorVidro = valorVidro;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String toString(){
        return "Referencia = "+getReferenciaProjeto();
    }
}
