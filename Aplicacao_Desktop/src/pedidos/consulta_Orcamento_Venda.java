package pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import utilitarios.Global;

public class consulta_Orcamento_Venda extends javax.swing.JFrame implements ActionListener {

    public consulta_Orcamento_Venda() {
        initComponents();

        set_janela();
        set_padrao();
        add_event_botoes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cancelar = new javax.swing.JButton();
        Alterar = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CANCELAR.png"))); // NOI18N
        Cancelar.setText("Cancelar");

        Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ALTERAR.png"))); // NOI18N
        Alterar.setText("Alterar");

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Alterar;
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton Excluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    public void set_janela() {
        setVisible(true);
        setTitle("Consulta Orçamento / Venda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Só fecha esta janela
        setLocationRelativeTo(null);// Inicia a janela no centro da tela
    }

    /**
     *
     */
    public void set_padrao() {
        table = Global.autoResizeColWidth(table, Global.PrencherJTable1("select *from ORCAMENTO_VENDA order by codigo", "ORCAMENTO_VENDA"));
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

    public void alterar(int linha) {
        String cliente, vendedor;

        dispose();

        orcamento_venda orcamento = new orcamento_venda();
        orcamento.AtivaDesativa(true);
        orcamento.Novo.setEnabled(false);
        orcamento.Salvar.setEnabled(true);
        orcamento.Cancelar.setEnabled(true);
        orcamento.Pesquisar.setEnabled(false);
        orcamento.set_Aux(2);
        orcamento.settCodigo(table.getModel().getValueAt(linha, 0).toString().trim());
        orcamento.settDescricao(table.getModel().getValueAt(linha, 3).toString().trim());
        orcamento.settObra(table.getModel().getValueAt(linha, 4).toString().trim());
        cliente = Global.ReturnSQL("select nome from cliente where codigo = '" + Integer.parseInt(table.getModel().getValueAt(linha, 1).toString().trim()) + "'", "NOME");
        orcamento.setComboCliente(cliente);
        vendedor = Global.ReturnSQL("select nome from funcionario where codigo = '" + Integer.parseInt(table.getModel().getValueAt(linha, 2).toString().trim()) + "'", "NOME");
        orcamento.setComboVendedor(vendedor);
        orcamento.settData(table.getModel().getValueAt(linha, 6).toString().trim());
        orcamento.settObservacao(table.getModel().getValueAt(linha, 7).toString().trim());
        orcamento.setTotal_bruto(table.getModel().getValueAt(linha, 8).toString().trim());
        orcamento.settDesconto(table.getModel().getValueAt(linha, 9).toString().trim());
        orcamento.settPorcentagem_Desconto(table.getModel().getValueAt(linha, 10).toString().trim());
        orcamento.settValor_Final(table.getModel().getValueAt(linha, 11).toString().trim());

        if (table.getModel().getValueAt(linha, 5).toString().trim().equals("SIM")) {
            orcamento.setRadio_Sim(true);
            orcamento.setRadio_Nao(false);
        } else if (table.getModel().getValueAt(linha, 5).toString().trim().equals("NAO")) {
            orcamento.setRadio_Nao(true);
            orcamento.setRadio_Sim(false);
        }

        orcamento.table_projetos.setModel(Global.PrencherJtable_projetos(
                "select projeto_orcamento_venda.projeto_codigo, projeto.referencia, projeto.descricao, "
                + "projeto_orcamento_venda.cor, projeto_orcamento_venda.vidro, projeto_orcamento_venda.largura, "
                + "projeto_orcamento_venda.altura, projeto_orcamento_venda.preco_vidro_m,projeto_orcamento_venda.quantidade, "
                + "projeto_orcamento_venda.valor_projeto "
                + "from projeto_orcamento_venda "
                + "join projeto on projeto.codigo = projeto_orcamento_venda.projeto_codigo "
                + "where projeto_orcamento_venda.orcamento_venda_codigo = ' " + Integer.parseInt(table.getModel().getValueAt(linha, 0).toString().trim()) + " '", orcamento.modeloTabela_projetos()));

        
        orcamento.table_produtos.setModel(Global.PrencherJtable_produtos(
                "select produto_orcamento_venda.produto_codigo, produto.referencia,produto.descricao, "
                + "produto_orcamento_venda.quantidade_produto, produto_orcamento_venda.valor_unitario, "
                + "produto_orcamento_venda.valor_total "
                + "from produto_orcamento_venda "
                + "join produto on produto.codigo = produto_orcamento_venda.produto_codigo "
                + "where produto_orcamento_venda.orcamento_venda_codigo = '" + Integer.parseInt(table.getModel().getValueAt(linha, 0).toString().trim()) + " '", orcamento.modeloTabela_produtos()));
    }

    /**
     *
     * @param linha
     */
    public void excluir(int linha) {

        Global.conection.Update("DELETE from PROJETO_ORCAMENTO_VENDA where( PROJETO_ORCAMENTO_VENDA.ORCAMENTO_VENDA_CODIGO = ' " + Integer.parseInt(table.getModel().getValueAt(linha, 0).toString()) + "' )");
        Global.conection.Update("DELETE from PRODUTO_ORCAMENTO_VENDA where( PRODUTO_ORCAMENTO_VENDA.ORCAMENTO_VENDA_CODIGO = ' " + Integer.parseInt(table.getModel().getValueAt(linha, 0).toString()) + "' )");
        Global.conection.Update("DELETE from ORCAMENTO_VENDA where( ORCAMENTO_VENDA.CODIGO = ' " + Integer.parseInt(table.getModel().getValueAt(linha, 0).toString()) + "' )");
        // Exclui uma linha do JTable
        ((DefaultTableModel) table.getModel()).removeRow(linha);
    }

    /**
     *
     */
    public void cancelar() {
        dispose();
        new orcamento_venda();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // linha recebe a linha selecionada do JTable
        int linha = table.getSelectedRow();

        if (e.getSource() == Alterar) {
            alterar(linha);
        } else if (e.getSource() == Excluir) {
            excluir(linha);
            JOptionPane.showMessageDialog(null, "Orçamento / Venda Excluido com Sucesso....");
        } else if (e.getSource() == Cancelar) {
            cancelar();
        }
    }
}
