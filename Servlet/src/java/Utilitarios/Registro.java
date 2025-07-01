/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilitarios;

/**
 *
 * @author wagner
 */
public class Registro {
    private int codigo;
    private String nome;

    public Registro() {
        setCodigo(0);
        setNome("");
    }

    @Override
    public String toString() {
        return getNome();
    }

    public Registro(int codigo, String nome) {
        setCodigo(codigo);
        setNome(nome);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
