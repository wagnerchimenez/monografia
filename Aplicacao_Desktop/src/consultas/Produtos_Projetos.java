package consultas;

import cadastros.Projetos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import utilitarios.Global;

public class Produtos_Projetos extends javax.swing.JFrame implements ActionListener {

    private Projetos P;

    public Produtos_Projetos(cadastros.Projetos Projeto) {

        initComponents();
        P = Projeto;

        set_janela();
        set_padrao();
        add_event_botoes();
    }

    public void set_TextFields() {
        dispose();
        // linha recebe a linha selecionada do JTable
        int linha = table.getSelectedRow();
        P.set_tCodigo_Produto(table.getModel().getValueAt(linha, 0).toString());
        P.set_tReferencia_Produto(table.getModel().getValueAt(linha, 1).toString());
        P.set_tDescricao_Produto(table.getModel().getValueAt(linha, 2).toString());
        P.set_tUnidade_Produto(table.getModel().getValueAt(linha, 3).toString());
        P.set_tPreco_Produto(table.getModel().getValueAt(linha, 4).toString());
        P.set_tCor_Produto(table.getModel().getValueAt(linha, 5).toString());
        P.set_tQuantidade_Produto("1");
        
    }

    public void set_janela() {
        setVisible(true);
        setResizable(false);
        setTitle("Busca Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Só fecha esta janela
        setLocationRelativeTo(null);// Inicia a janela no centro da tela
    }

    public void set_padrao() {
        table = Global.autoResizeColWidth(table, Global.PrencherJTable1("select *from PRODUTO", "PRODUTO"));

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
        Escolher.addActionListener(this);
        Cancelar.addActionListener(this);
    }

    public void escolher() {
        set_TextFields();
    }

    public void cancelar() {
        dispose();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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
        Escolher = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
            }
        ));
        jScrollPane1.setViewportView(table);

        Escolher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ESCOLHER_DO_BANCO.png"))); // NOI18N
        Escolher.setText("Escolher");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(451, 451, 451)
                        .addComponent(Escolher, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Cancelar, Escolher});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancelar)
                    .addComponent(Escolher, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Cancelar, Escolher});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton Escolher;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Escolher)) {
            escolher();
        } else if (e.getSource().equals(Cancelar)) {
            cancelar();
        }

    }
}
