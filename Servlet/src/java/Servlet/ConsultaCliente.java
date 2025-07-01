package Servlet;

import Utilitarios.Conexao;
import Utilitarios.Registro;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsultaCliente extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/xml");
        // Estabelece conexão com Banco de Dados
        Conexao connection = new Conexao();

        Vector<Registro> itens = null;
        try {
            Vector<String> campos = new Vector<String>();
            campos.addElement("NOME");
            campos.addElement("CPF");

            itens = connection.ReturnSQLCollection("select CODIGO, NOME, CPF from CLIENTE WHERE (UPPER (NOME))" +
                    " like (UPPER ('%" + request.getParameter("nome") + "%'))", campos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PrintWriter saida = response.getWriter();

        saida.println("<?xml version=\"1.0\"?>");
        saida.println("<clientes>");
        for (int i = 0; i < itens.size(); i++) {
            saida.println("\t<cliente>");
            saida.println("\t\t<codigo>" + itens.get(i).getCodigo() + "</codigo> ");
            saida.println("\t\t<nome>" + itens.get(i).getNome() + "</nome> ");
            saida.println("\t</cliente>");
        }
        saida.println("</clientes>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
