package utilitarios;

import consultas.Clientes;
import java.awt.Component;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class Global {

    public static conexao conection = null;

    static {
        try {
            conection = new conexao();
        } catch (IOException ex) {
            Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Converte uma String para um objeto Date. Caso a String seja vazia ou nula,
     * retorna null - para facilitar em casos onde formulários podem ter campos
     * de datas vazios.
     * @param data String no formato dd/MM/yyyy a ser formatada
     * @return Date Objeto Date ou null caso receba uma String vazia ou nula
     * @throws Exception Caso a String esteja no formato errado
     */
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
     * Converte datas do formato yyyy-MMM-dd para dd/MM/yyyy
     * @param input - String data não formatada
     * @return date - String data formatada
     */
    public static String formataDateToString(String input) {
        String date = "";
        SimpleDateFormat toDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat fromDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = toDate.format(fromDate.parse(input));
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    /**
     * Preenche JTable do cadastros.projetos.table
     * @param sql - SQL passado
     * @return modelo - modelo do tipo DefaultTableModel
     * @author - Wagner Lima Chimenez
     */
    public static DefaultTableModel PrencherJtable(String sql, DefaultTableModel modelo) {

        try {
            conection.stm = conection.getConection().createStatement();
            conection.rs = conection.stm.executeQuery(sql);
            while (conection.rs.next()) {
                modelo.addRow(new Object[]{conection.rs.getInt("CODIGO"),
                            conection.rs.getString("REFERENCIA"),
                            conection.rs.getString("DESCRICAO"),
                            conection.rs.getString("UNIDADE"),
                            conection.rs.getFloat("VALOR_UNITARIO"),
                            conection.rs.getString("COR"),
                            conection.rs.getInt("QUANTIDADE")});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelo;

    }

    public static DefaultTableModel PrencherJtable_projetos(String sql, DefaultTableModel modelo) {

        try {
            conection.stm = conection.getConection().createStatement();
            conection.rs = conection.stm.executeQuery(sql);
            while (conection.rs.next()) {
                modelo.addRow(new Object[]{conection.rs.getInt("PROJETO_CODIGO"),
                            conection.rs.getString("REFERENCIA"),
                            conection.rs.getString("DESCRICAO"),
                            conection.rs.getString("COR"),
                            conection.rs.getString("VIDRO"),
                            conection.rs.getFloat("LARGURA"),
                            conection.rs.getFloat("ALTURA"),
                            conection.rs.getFloat("PRECO_VIDRO_M"),
                            conection.rs.getInt("QUANTIDADE"),
                            conection.rs.getFloat("VALOR_PROJETO")});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelo;

    }

        public static DefaultTableModel PrencherJtable_produtos(String sql, DefaultTableModel modelo) {

        try {
            conection.stm = conection.getConection().createStatement();
            conection.rs = conection.stm.executeQuery(sql);
            while (conection.rs.next()) {
                modelo.addRow(new Object[]{conection.rs.getInt("PRODUTO_CODIGO"),
                            conection.rs.getString("REFERENCIA"),
                            conection.rs.getString("DESCRICAO"),
                            conection.rs.getInt("QUANTIDADE_PRODUTO"),
                            conection.rs.getFloat("VALOR_UNITARIO"),
                            conection.rs.getFloat("VALOR_TOTAL")});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelo;

    }

    /**
     * Preenche um Jtable
     * @param sql - Consulta SQL
     * @param tabela - Tabela referente a consulta
     * @return modelo - Retorna um novo modelo do tipo DefaultTableModel preenchido
     * com os dados da consulta SQL do BANCO DE DADOS
     */
    public static DefaultTableModel PrencherJTable1(String sql, String tabela) {
        Vector camp = new Vector();
        Vector informacoes = new Vector();
        String fields[] = new String[]{};
        String dados[][] = new String[][]{};

        DefaultTableModel modelo = new DefaultTableModel();

        try {
            modelo.setNumRows(0);

            conection.stm = conection.getConection().createStatement();

            conection.campos = conection.stm.executeQuery("select f.rdb$relation_name, f.rdb$field_name as FIELD_NAME from rdb$relation_fields f join rdb$relations r on f.rdb$relation_name = r.rdb$relation_name and r.rdb$view_blr is null and (r.rdb$system_flag is null or r.rdb$system_flag = 0) where f.rdb$relation_name = '" + tabela.toUpperCase() + "' order by 1, f.rdb$field_position;");

            while (conection.campos.next()) {
                camp.add(conection.campos.getString("FIELD_NAME").trim());
            }

            modelo = new DefaultTableModel(dados, (String[]) camp.toArray(new String[camp.size()]));

            conection.rs = conection.stm.executeQuery(sql);

            while (conection.rs.next()) {
                for (int i = 0; i < camp.size(); i++) {
                    if (((String) camp.elementAt(i)).toLowerCase().contains("data")) {
                        informacoes.add(Global.formataDateToString(conection.rs.getString(camp.elementAt(i).toString())));
                    } else {
                        informacoes.add(conection.rs.getString(camp.elementAt(i).toString()));
                    }
                }

                modelo.insertRow(modelo.getRowCount(), (Object[]) informacoes.toArray(new Object[informacoes.size()]));
                informacoes.clear();

            }
        } catch (Exception e) {
            System.out.print("erro");
        }

        return modelo;
    }

    /*
     * Preenche uum JComboBox
     * @param sql - Consulta SQL
     * @param combobox - JComboBox que deverá ser preenchido
     * @param campo - Campo que deverá ser pego do resultado do SQL
     * @return combobox - JComboBox preenchido
     */
    public static JComboBox Prenche_ComboBox(String sql, JComboBox combobox, String campo) {
        try {
            conection.stm = conection.getConection().createStatement();
            conection.rs = conection.stm.executeQuery(sql);
            while (conection.rs.next()) {
                String resultado = conection.rs.getString(campo);
                combobox.addItem(resultado);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "\n Erro ao Preencher ComboBox \n");
        }

        return combobox;
    }

    /*
     *
     */
    public static JTable autoResizeColWidth(JTable table, DefaultTableModel model) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setModel(model);

        int margin = 5;

        for (int i = 0; i < table.getColumnCount(); i++) {
            int vColIndex = i;
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
            TableColumn col = colModel.getColumn(vColIndex);
            int width = 0;

            // Get width of column header
            TableCellRenderer renderer = col.getHeaderRenderer();

            if (renderer == null) {
                renderer = table.getTableHeader().getDefaultRenderer();
            }

            Component comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, 0);

            width = comp.getPreferredSize().width;

            // Get maximum width of column data
            for (int r = 0; r < table.getRowCount(); r++) {
                renderer = table.getCellRenderer(r, vColIndex);
                comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, vColIndex), false, false,
                        r, vColIndex);
                width = Math.max(width, comp.getPreferredSize().width);
            }

            // Add margin
            width += 2 * margin;

            // Set the width
            col.setPreferredWidth(width);
        }

        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(
                SwingConstants.LEFT);

        // table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);

        return table;
    }

    /*
     * Retorna o código da chave primária
     * @param sql - Select para selecionar o código
     * @return rs.getInt("CODIGO");, retorna o código referente ao label da tabela passado
     */
    public static int ReturnKey(String sql) {
        int saida = -1;
        try {
            conection.stm = conection.getConection().createStatement();
            conection.rs = conection.stm.executeQuery(sql);
            conection.rs.next();
            saida = conection.rs.getInt("CODIGO");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    // Retorna o resultado de um SQL
    public static String ReturnSQL(String sql,String campo) {
        String saida = "";
        try {
            conection.stm = conection.getConection().createStatement();
            conection.rs = conection.stm.executeQuery(sql);
            conection.rs.next();
            saida = conection.rs.getString(campo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

     // Retorna o resultado de um SQL
    public static int ReturnSQL(String sql) {
        int cont = 0;
        try {
            conection.stm = conection.getConection().createStatement();
            conection.rs = conection.stm.executeQuery(sql);
            while(conection.rs.next()){
                cont++;
            }
            return cont;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cont;
    }

    public static float arredondaValor(float x)
    {
         return x;//((x)*100)/100;
    }
}
