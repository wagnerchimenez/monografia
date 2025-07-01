package Utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Conexao {

    private static Connection conection;
    private static Statement stm;
    private static ResultSet rs;

    public Conexao() {
        try {
            //String url_BD = "jdbc:firebirdsql://localhost:3050/C:/Users/wagner/Desktop/BANCO.FDB";
            //String usuario = "SYSDBA";
            String url_BD = "jdbc:firebirdsql://firebird.glasswindow.kinghost.net:3050//firebird/glasswindow.gdb";
            String usuario = "glasswindow";
            String senha = "masterkey";
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            conection = DriverManager.getConnection(url_BD, usuario, senha);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro de Conexão: " + e.getMessage());
        }
    }

    public void Update(String sql) {
        try {
            stm = conection.createStatement();
            stm.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível realizar essa operação " + e.getMessage());
        }
    }

    public String ReturnSQL(String sql, String campo) {
        String saida = "";
        try {
            stm = conection.createStatement();
            rs = stm.executeQuery(sql);
            rs.next();
            saida = rs.getString(campo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public Float ReturnSQL_NumReal(String sql, String campo) {
        Float saida = null;
        try {
            stm = conection.createStatement();
            rs = stm.executeQuery(sql);
            rs.next();
            saida = rs.getFloat(campo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    /**
     * 
     * @param sql
     * @param campos
     * @return Vector de registro com os valores da consulta
     */
    public Vector<Registro> ReturnSQLCollection(String sql, Vector<String> campos) {
        Vector<Registro> saida = new Vector<Registro>();
        try {
            stm = conection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String campo = rs.getString(campos.elementAt(0));//pog
                for (int i = 1; i < campos.size(); i++) {
                    campo += "\n" + rs.getString(campos.elementAt(i));
                }
                saida.addElement(new Registro(rs.getInt("CODIGO"), campo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public Vector<Registro> ReturnSQLCollectionVendedor(String sql, Vector<String> campos) {
        Vector<Registro> saida = new Vector<Registro>();
        try {
            stm = conection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String campo = rs.getString(campos.elementAt(0));//pog
                for (int i = 1; i < campos.size(); i++) {
                    campo += "\n" + rs.getString(campos.elementAt(i));
                }
                saida.addElement(new Registro(rs.getInt("CODIGO"), campo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;

    }

    public Vector<RegistroProjetos> ReturnSQLCollectionProjetos(String sql, Vector<String> campos) {
        Vector<RegistroProjetos> saida = new Vector<RegistroProjetos>();
        try {
            stm = conection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String referenciaProjeto = rs.getString(campos.elementAt(0));//pog

                saida.addElement(new RegistroProjetos(rs.getInt("CODIGO"), referenciaProjeto, Float.parseFloat(rs.getString(campos.elementAt(1)))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public Vector<RegistroVidros> ReturnSQLCollectionVidros(String sql, Vector<String> campos) {
        Vector<RegistroVidros> saida = new Vector<RegistroVidros>();
        try {
            stm = conection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String referenciaProjeto = rs.getString(campos.elementAt(0));//pog

                saida.addElement(new RegistroVidros(rs.getInt("CODIGO"), referenciaProjeto, Float.parseFloat(rs.getString(campos.elementAt(1)))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;

    }

    // Consulta Produtos
    public Vector<RegistroProdutos> ReturnSQLCollectionProdutos(String sql, Vector<String> campos) {
        Vector<RegistroProdutos> saida = new Vector<RegistroProdutos>();
        try {
            stm = conection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String referenciaProduto = rs.getString(campos.elementAt(0));//pog

                saida.addElement(new RegistroProdutos(rs.getInt("CODIGO"), referenciaProduto, Float.parseFloat(rs.getString(campos.elementAt(1)))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public static java.sql.Date formataData(String data) throws Exception {
        if (data == null || data.equals("")) {
            return null;
        }

        java.sql.Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = new java.sql.Date(((java.util.Date) formatter.parse(data)).getTime());
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }

        /*
     * Retorna o código da chave primária
     * @param sql - Select para selecionar o código
     * @return rs.getInt("CODIGO");, retorna o código referente ao label da tabela passado
     */
    public static int ReturnKey(String sql) {
        int saida = -1;
        try {
            stm = conection.createStatement();
            rs = stm.executeQuery(sql);
            rs.next();
            saida = rs.getInt("CODIGO");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

}

