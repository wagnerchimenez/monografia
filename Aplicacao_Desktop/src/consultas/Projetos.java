package consultas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import utilitarios.Global;

/**
 *
 * @author wagner
 */
public class Projetos extends javax.swing.JFrame implements ActionListener {

    /**
     *
     */
    public Projetos() {
        initComponents();

        set_janela();
        set_padrao();
        add_event_botoes();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable()
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        }
        ;
        Excluir = new javax.swing.JButton();
        Alterar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane1.setViewportView(table);

        Excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/EXCLUIR.png"))); // NOI18N
        Excluir.setText("Excluir");

        Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ALTERAR.png"))); // NOI18N
        Alterar.setText("Alterar");

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CANCELAR.png"))); // NOI18N
        Cancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 674, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(Alterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Excluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(147, 147, 147))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Alterar)
                    .addComponent(Excluir)
                    .addComponent(Cancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     */
    public void set_janela() {
        setVisible(true);
        setTitle("Consulta Projetos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Só fecha esta janela
        setLocationRelativeTo(null);// Inicia a janela no centro da tela
    }

    /**
     *
     */
    public void set_padrao() {
        table = Global.autoResizeColWidth(table, Global.PrencherJTable1("select *from PROJETO order by codigo", "PROJETO"));
        // Auto Redimenciona o tamanho da tabela
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //  permiti somente a seleção de linhas ( unicas ) e nao multiplas.
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // bloquear as colunas de uma tabela para que ela não possa mais ser mudada de posição
        table.getTableHeader().setReorderingAllowed(false);

        table.setEnabled(rootPaneCheckingEnabled);

    }

    /**
     *
     */
    public void add_event_botoes() {
        Alterar.addActionListener(this);
        Excluir.addActionListener(this);
        Cancelar.addActionListener(this);
    }

    /* ########################### Manipula Registros ####################### */
    /**
     *
     * @param linha
     */
    public void alterar(int linha) {
        dispose();
        cadastros.Projetos projetos = new cadastros.Projetos();
        projetos.AtivaDesativa_Aba1(true);
        projetos.AtivaDesativa_Aba2(true);
        projetos.Novo.setEnabled(false);
        projetos.Salvar.setEnabled(true);
        projetos.Cancelar.setEnabled(true);
        projetos.Pesquisar.setEnabled(false);
        projetos.set_Aux(2);
        projetos.set_tCodigo(table.getModel().getValueAt(linha, 0).toString().trim());
        projetos.set_tReferencia(table.getModel().getValueAt(linha, 1).toString().trim());
        projetos.set_tDescricao(table.getModel().getValueAt(linha, 2).toString().trim());
        projetos.set_tDesenho(table.getModel().getValueAt(linha, 3).toString().trim());
        projetos.set_Label_Desenho(table.getModel().getValueAt(linha, 3).toString().trim());

        projetos.table.setModel(Global.PrencherJtable(
                "select produto.codigo, produto.referencia, produto.descricao, produto.unidade, produto_projeto.valor_unitario, produto.cor, produto_projeto.quantidade " +
                "from produto " +
                "join produto_projeto on produto_projeto.produto_codigo = produto.codigo " +
                "join projeto on projeto.codigo = produto_projeto.projeto_codigo " +
                "where '"+Integer.parseInt(table.getModel().getValueAt(linha, 0).toString().trim()) +"' = produto_projeto.projeto_codigo", cadastros.Projetos.modeloTabela()) );

    }

    /**
     *
     * @param linha
     */
    public void excluir(int linha) {
        Global.conection.Update("DELETE from PRODUTO_PROJETO where( PROJETO_CODIGO = ' " + table.getModel().getValueAt(linha, 0).toString() + "' )");
        Global.conection.Update("DELETE from PROJETO where( codigo = ' " + table.getModel().getValueAt(linha, 0).toString() + "' )");
        // Exclui uma linha do JTable
        ((DefaultTableModel) table.getModel()).removeRow(linha);
    }

    /**
     *
     */
    public void cancelar() {
        dispose();
        cadastros.Projetos projetos = new cadastros.Projetos();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Alterar;
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton Excluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        // linha recebe a linha selecionada do JTable
        int linha = table.getSelectedRow();

        if (e.getSource() == Alterar) {
            alterar(linha);
        } else if (e.getSource() == Excluir) {
            excluir(linha);
            JOptionPane.showMessageDialog(null, "Projeto Excluido com Sucesso....");
        } else if (e.getSource() == Cancelar) {
            cancelar();
        }
    }
}
