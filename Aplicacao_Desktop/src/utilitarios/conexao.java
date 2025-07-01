package utilitarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import javax.swing.*;

public class conexao {

    public String url_BD;
    public static String usuario;
    public String senha;
    public Connection con;
    public Statement stm;
    public ResultSet rs;
    public ResultSet campos;
    public String sql;
    public static boolean salve_OK = true;

    /*
     * 
     */
    public conexao() throws IOException {

        final JFileChooser fc = new JFileChooser();
        FileInputStream caminho_gravado;
        FileInputStream usuario_gravado;
        Scanner sc;
        Scanner user;
        String usuarioBD = null;
        String caminho = null;

        try {
            caminho_gravado = new FileInputStream(new File("CaminhoBanco.txt"));
            usuario_gravado = new FileInputStream(new File("usuarioBancoDeDados.txt"));
            user = new Scanner(usuario_gravado);
            sc = new Scanner(caminho_gravado);
            usuarioBD = user.nextLine();
            caminho = sc.nextLine();
        } catch (Exception e) {
        }

        try {
	url_BD = caminho;
            usuario = usuarioBD;
            //usuario = "SYSDBA";
            //usuario = "glasswindow";
            senha = "masterkey";
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            con = DriverManager.getConnection(url_BD, usuario, senha);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de conexao!");
        }
    }

    public conexao(String caminhoBD, String usuario, String senha) throws IOException {

        try {
            caminhoBD = caminhoBD.replace('\\', '/');
            this.url_BD = caminhoBD;
            conexao.usuario = usuario;
            this.senha = senha;
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            con = DriverManager.getConnection(url_BD, conexao.usuario, this.senha);
            JOptionPane.showMessageDialog(null, "Conexão estabelecida com sucesso...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de conexao!");
            con = null;
            e.printStackTrace();
        }


    } /*
     *
     */


    public Connection getConection() {
        return con;
    }

    /*
     *
     */
    public void Update(String sql) {
        try {
            stm = getConection().createStatement();
            stm.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            salve_OK = false;
            JOptionPane.showMessageDialog(null, "Não foi possível realizar essa operação " + e);
        }
    }
}
