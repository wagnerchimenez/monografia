/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilitarios;

/**
 *
 * @author wagner
 */
public class RegistroVidros {
    private int codigo;
    private String referencia;
    private float valor_projeto;

    public RegistroVidros(){
        setCodigo(0);
        setReferencia("");
        setValor_projeto(0);
    }

    public RegistroVidros(int codigo, String referencia, float valor_projeto){
        setCodigo(codigo);
        setReferencia(referencia);
        setValor_projeto(valor_projeto);
    }

     @Override
    public String toString() {
        return getReferencia();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getReferencia() {
        return referencia;
    }

    public float getValor_projeto() {
        return valor_projeto;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setValor_projeto(float valor_projeto) {
        this.valor_projeto = valor_projeto;
    }

}
