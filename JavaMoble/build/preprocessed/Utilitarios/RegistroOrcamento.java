/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilitarios;

/**
 *
 * @author wagner
 */
public class RegistroOrcamento {
    // Aba 1
    private String descricao;
    private String obra;
    private int clienteCodigo;
    private String clienteNome;
    private int vendedorCodigo;
    private String vendedorNome;
    private String observacao;
    private String concluido;
    private String data;
    // Aba 2
    private String referenciaProjeto;
    private float valorProjeto;
    private String cor;
    private int quantidadeProjeto;
    private String referenciaVidro;
    private float largura;
    private float altura;

    public RegistroOrcamento() {
        // Aba 1
        descricao="";
        obra="";
        concluido="";
        data="";
        clienteNome ="";
        vendedorNome="";
        quantidadeProjeto=0;

        // Aba 2

        
    }

    public String separaNome(String nome){
        String NomeCliente="";

        for(int i=0;i<nome.length();i++){
            if( (nome.charAt(i) >= '0' && nome.charAt(i) <= '9') || nome.charAt(i) == '.' || nome.charAt(i) == '-'){
                i++;
            }else{
                NomeCliente = NomeCliente + nome.charAt(i);
            }
        }

       // System.out.println("Nome separado = "+NomeCliente);
        return NomeCliente.trim();
    }

    // Aba 1
    public void limpaCamposOrcamentoDados(){
        setDescricao("");
        setObra("");
        setClienteCodigo(-1);
        setClienteNome("");
        setVendedorCodigo(-1);
        setVendedorNome("");
        setObservacao("");
        setConcluido("");
    }

    public String getDescricao() {
        return descricao;
    }

    public String getObra() {
        return obra;
    }

    public int getClienteCodigo() {
        return clienteCodigo;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public int getVendedorCodigo() {
        return vendedorCodigo;
    }

    public String getVendedorNome() {
        return vendedorNome;
    }

    public String getObservacao() {
        return observacao;
    }

    public String getConcluido() {
        return concluido;
    }

    public String getData() {
        return data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public void setClienteCodigo(int clienteCodigo) {
        this.clienteCodigo = clienteCodigo;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public void setVendedorCodigo(int vendedorCodigo) {
        this.vendedorCodigo = vendedorCodigo;
    }

    public void setVendedorNome(String vendedorNome) {
        this.vendedorNome = vendedorNome;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setConcluido(String concluido) {
        this.concluido = concluido;
    }

    public void setData(String data) {
        this.data = data;
    }

    // Aba 2


    public String getReferenciaProjeto() {
        return referenciaProjeto;
    }

    public float getValorProjeto() {
        return valorProjeto;
    }

    public String getCor() {
        return cor;
    }

    public int getQuantidadeProjeto() {
        return quantidadeProjeto;
    }

    public String getReferenciaVidro() {
        return referenciaVidro;
    }

    public float getLargura() {
        return largura;
    }

    public float getAltura() {
        return altura;
    }

    public void setReferenciaProjeto(String referenciaProjeto) {
        this.referenciaProjeto = referenciaProjeto;
    }

    public void setValorProjeto(float valorProjeto) {
        this.valorProjeto = valorProjeto;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setQuantidadeProjeto(int quantidadeProjeto) {
        this.quantidadeProjeto = quantidadeProjeto;
    }

    public void setReferenciaVidro(String referenciaVidro) {
        this.referenciaVidro = referenciaVidro;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }


}
