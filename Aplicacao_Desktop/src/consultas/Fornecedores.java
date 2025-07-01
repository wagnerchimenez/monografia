package consultas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import utilitarios.Global;

public class Fornecedores extends javax.swing.JFrame implements ActionListener {

    public Fornecedores() {
        initComponents();

        set_janela();
        set_padrao();
        add_event_botoes();
    }

    public void set_janela() {
        setVisible(true);
        setTitle("Consulta Cadastro Fornecedor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Só fecha esta janela
        setLocationRelativeTo(null);// Inicia a janela no centro da tela
    }

    public void set_padrao() {
        table = Global.autoResizeColWidth(table, Global.PrencherJTable1("select *from FORNECEDOR", "FORNECEDOR"));

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
        cadastros.Fornecedores fornecedor = new cadastros.Fornecedores();
        fornecedor.AtivaDesativa(true);
        fornecedor.Novo.setEnabled(false);
        fornecedor.Salvar.setEnabled(true);
        fornecedor.Cancelar.setEnabled(true);
        fornecedor.Pesquisar.setEnabled(false);
        fornecedor.set_Aux(2);
        fornecedor.set_tCodigo(table.getModel().getValueAt(linha, 0).toString());
        fornecedor.set_tRazao_Social(table.getModel().getValueAt(linha, 1).toString());
        fornecedor.set_tFantasia(table.getModel().getValueAt(linha, 2).toString());
        fornecedor.set_tCNPJ(table.getModel().getValueAt(linha, 3).toString());
        fornecedor.set_tIE(table.getModel().getValueAt(linha, 4).toString());
        fornecedor.set_tEndereco(table.getModel().getValueAt(linha, 5).toString());
        fornecedor.set_tBairro(table.getModel().getValueAt(linha, 6).toString());
        fornecedor.set_Combo_Cidade(table.getModel().getValueAt(linha, 7).toString());
        fornecedor.set_Combo_Estado(table.getModel().getValueAt(linha, 8).toString());
        fornecedor.set_tCEP(table.getModel().getValueAt(linha, 9).toString());
        fornecedor.set_tFone(table.getModel().getValueAt(linha, 10).toString());
        fornecedor.set_tFax(table.getModel().getValueAt(linha, 11).toString());
        fornecedor.set_tContato(table.getModel().getValueAt(linha, 12).toString());
        fornecedor.set_tEmail(table.getModel().getValueAt(linha, 13).toString());
        fornecedor.set_tData(table.getModel().getValueAt(linha, 14).toString());
        fornecedor.set_tObservacao(table.getModel().getValueAt(linha, 15).toString());
    }

    public void deletar(int linha) {
        Global.conection.Update("DELETE from FORNECEDOR where( codigo = ' " + table.getModel().getValueAt(linha, 0).toString() + "' )");
        // Exclui uma linha do JTable
        ((DefaultTableModel) table.getModel()).removeRow(linha);
    }

    public void cancelar() {
        dispose();
        cadastros.Fornecedores fornecedor = new cadastros.Fornecedores();
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(Alterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Excluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(129, 129, 129))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Alterar)
                    .addComponent(Excluir)
                    .addComponent(Cancelar))
                .addGap(11, 11, 11))
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

            JOptionPane.showMessageDialog(null, "Fornecedor Excluido com Sucesso....");

        } else if (e.getSource() == Cancelar) {
            cancelar();
        }
    }
}
