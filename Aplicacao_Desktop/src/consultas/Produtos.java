package consultas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import utilitarios.Global;

public class Produtos extends javax.swing.JFrame implements ActionListener {

    public Produtos() {
        initComponents();

        set_janela();
        set_padrao();
        add_event_botoes();
    }

    public void set_janela() {
        setVisible(true);
        setTitle("Consulta Cadastro Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Só fecha esta janela
        setLocationRelativeTo(null);// Inicia a janela no centro da tela
    }

    public void set_padrao() {
        //table.setModel(Global.conection.PrencherJTable1("select *from CADASTRO_CLIENTE", "CADASTRO_CLIENTE"));
        table = Global.autoResizeColWidth(table, Global.PrencherJTable1("select *from PRODUTO order by codigo", "PRODUTO"));

        // Não deixa editar os campos da tabela

        // Auto Redimenciona o tamanho da tabela
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //  permiti somente a seleção de linhas ( unicas ) e nao multiplas.
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // bloquear as colunas de uma tabela para que ela não possa mais ser mudada de posição
        table.getTableHeader().setReorderingAllowed(false);

        table.setEnabled(rootPaneCheckingEnabled);
    }

    public void add_event_botoes() {
        Alterar.addActionListener(this);
        Excluir.addActionListener(this);
        Cancelar.addActionListener(this);
    }

    /* ########################### Manipula Registros ####################### */
    public void atualizar(int linha) {
        dispose();
        cadastros.Produtos produtos = new cadastros.Produtos();
        produtos.AtivaDesativa(true);
        produtos.Novo.setEnabled(false);
        produtos.Salvar.setEnabled(true);
        produtos.Cancelar.setEnabled(true);
        produtos.Pesquisar.setEnabled(false);
        produtos.set_Aux(2);
        produtos.set_tCodigo(table.getModel().getValueAt(linha, 0).toString());
        produtos.set_tReferencia(table.getModel().getValueAt(linha, 1).toString());
        produtos.set_tDescricao(table.getModel().getValueAt(linha, 2).toString());
        produtos.set_tUnidade(table.getModel().getValueAt(linha, 3).toString());
        produtos.set_tPreco(table.getModel().getValueAt(linha, 4).toString());
        produtos.set_Combo_Cor(table.getModel().getValueAt(linha, 5).toString());
    }

    public void deletar(int linha) {
        Global.conection.Update("DELETE from PRODUTO where( codigo = ' " + table.getModel().getValueAt(linha, 0).toString() + "' )");
        // Exclui uma linha do JTable
        ((DefaultTableModel) table.getModel()).removeRow(linha);
    }

    public void cancelar() {
        dispose();
        cadastros.Produtos produtos = new cadastros.Produtos();
    }

    /* ########################### Manipula Registros ####################### */
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
        Alterar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Excluir = new javax.swing.JButton();

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

        Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ALTERAR.png"))); // NOI18N
        Alterar.setText("Alterar");

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CANCELAR.png"))); // NOI18N
        Cancelar.setText("Cancelar");

        Excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/EXCLUIR.png"))); // NOI18N
        Excluir.setText("Excluir");

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
            .addGap(0, 315, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Alterar)
                    .addComponent(Excluir)
                    .addComponent(Cancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
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

            atualizar(linha);

        } else if (e.getSource() == Excluir) {

            deletar(linha);

            JOptionPane.showMessageDialog(null, "Cliente Excluido com Sucesso....");

        } else if (e.getSource() == Cancelar) {
            cancelar();
        }
    }
}
