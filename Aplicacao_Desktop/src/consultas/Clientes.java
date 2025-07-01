package consultas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import utilitarios.Global;

public class Clientes extends JFrame implements ActionListener {

    public Clientes() {
        initComponents();
        set_janela();
        set_padrao();
        add_event_botoes();
    }

    public void set_janela() {
        setVisible(true);
        setTitle("Consulta Cadastro de Clientes ");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Só fecha esta janela
        setLocationRelativeTo(null);// Inicia a janela no centro da tela
    }

    public void set_padrao() {
        table = Global.autoResizeColWidth(table, Global.PrencherJTable1("select *from CLIENTE", "CLIENTE"));

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

    public void alterar(int line){
        dispose();
        cadastros.Clientes clientes = new cadastros.Clientes();
        clientes.Ativa_Desativa(true);
        clientes.Novo.setEnabled(false);
        clientes.Salvar.setEnabled(true);
        clientes.Cancelar.setEnabled(true);
        clientes.Pesquisar.setEnabled(false);
        clientes.set_Aux(2);
        clientes.set_tCodigo(table.getModel().getValueAt(line, 0).toString());
        clientes.set_tNome(table.getModel().getValueAt(line, 1).toString());
        clientes.set_Combo_Sexo(table.getModel().getValueAt(line, 2).toString());
        clientes.set_tRG(table.getModel().getValueAt(line, 3).toString());
        clientes.set_tCPF(table.getModel().getValueAt(line, 4).toString());
        clientes.set_tEndereco(table.getModel().getValueAt(line, 5).toString());
        clientes.set_tBairro(table.getModel().getValueAt(line, 6).toString());
        clientes.set_Combo_Cidade(table.getModel().getValueAt(line, 7).toString());
        clientes.set_Combo_Estado(table.getModel().getValueAt(line, 8).toString());
        clientes.set_tCEP(table.getModel().getValueAt(line, 9).toString());
        clientes.set_tFone(table.getModel().getValueAt(line, 10).toString());
        clientes.set_tFax(table.getModel().getValueAt(line, 11).toString());
        clientes.set_tCelular(table.getModel().getValueAt(line, 12).toString());
        clientes.set_tContato(table.getModel().getValueAt(line, 13).toString());
        clientes.set_tEmail(table.getModel().getValueAt(line, 14).toString());
        clientes.set_tData(table.getModel().getValueAt(line, 15).toString());
        clientes.set_tObservacao(table.getModel().getValueAt(line, 16).toString());
    }

    public void deletar(int line){
        Global.conection.Update("DELETE from CLIENTE where( codigo = ' " + table.getModel().getValueAt(line, 0).toString() + "' )");
        // Exclui uma linha do JTable
        ((DefaultTableModel) table.getModel()).removeRow(line);
        JOptionPane.showMessageDialog(null, "Cliente Excluido com Sucesso....");
    }

    public void cancelar(){
        dispose();
        cadastros.Clientes clientes = new cadastros.Clientes();
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
        setMinimumSize(new java.awt.Dimension(631, 345));

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
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
        /* linha recebe a linha selecionada do JTable */
        int line = table.getSelectedRow();

        if (e.getSource() == Alterar) {
           alterar(line);
        } else if (e.getSource() == Excluir) {
            deletar(line);
        } else if (e.getSource() == Cancelar) {
            cancelar();
        }
    }
}
