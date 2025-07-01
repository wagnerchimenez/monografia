package consultas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import utilitarios.Global;

public class Cidades extends javax.swing.JFrame implements ActionListener {

    public Cidades() {
        initComponents();

        set_janela();
        set_padrao();
        add_event_botoes();

    }

    public void set_janela() {
        setVisible(true);
        setTitle("Consulta Cadastro Cidade");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Só fecha esta janela
        setLocationRelativeTo(null);// Inicia a janela no centro da tela
    }

    public void set_padrao() {

        table = Global.autoResizeColWidth(table, Global.PrencherJTable1("select *from CIDADE order by CODIGO", "CIDADE"));

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

    public void atualizar( int linha ) {
        dispose();
        cadastros.Cidades cidades = new cadastros.Cidades();
        cidades.AtivaDesativa(true);
        cidades.Novo.setEnabled(false);
        cidades.Salvar.setEnabled(true);
        cidades.Cancelar.setEnabled(true);
        cidades.Pesquisar.setEnabled(false);
        cidades.setAux(2);
        cidades.set_tCodigo(table.getModel().getValueAt(linha, 0).toString());
        cidades.set_tNome(table.getModel().getValueAt(linha, 1).toString());
        cidades.set_Combo_Estado(table.getModel().getValueAt(linha, 2).toString());
    }

    public void deletar(int linha) {
        Global.conection.Update("DELETE from CIDADE where( codigo = ' " + table.getModel().getValueAt(linha, 0).toString() + "' )");
        // Exclui uma linha do JTable
        ((DefaultTableModel) table.getModel()).removeRow(linha);
        JOptionPane.showMessageDialog(null, "Cidade Excluida com Sucesso....");
    }

    public void cancelar() {
        dispose();
        cadastros.Cidades cidades = new cadastros.Cidades();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Excluir = new javax.swing.JButton();
        Alterar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable()
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        }
        ;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(404, 238));

        Excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/EXCLUIR.png"))); // NOI18N
        Excluir.setText("Excluir");

        Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ALTERAR.png"))); // NOI18N
        Alterar.setText("Alterar");

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CANCELAR.png"))); // NOI18N
        Cancelar.setText("Cancelar");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Alterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Excluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(41, 41, 41))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
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

        } else if (e.getSource() == Cancelar) {
            cancelar();
        }
    }
}
