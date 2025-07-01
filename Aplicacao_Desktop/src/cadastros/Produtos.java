package cadastros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import utilitarios.Global;

public class Produtos extends javax.swing.JFrame implements ActionListener {

    private int aux = 0;

    public Produtos() {
        initComponents();

        set_janela();
        set_padrao();
        add_event_botoes();
    }
    /* Geters */

    public JTextField get_tCodigo() {
        return tCodigo;
    }

    public JTextField get_tReferencia() {
        return tReferencia;
    }

    public JTextField get_tDescricao() {
        return tDescricao;
    }

    public JTextField get_tUnidade() {
        return tUnidade;
    }

    public JTextField get_tPreco() {
        return tPreco;
    }

    public JComboBox get_Combo_Cor() {
        return ComboCor;
    }

    /* Seters */
    public void set_tCodigo(String tCodigo) {
        this.tCodigo.setText(tCodigo);
    }

    public void set_tReferencia(String tReferencia) {
        this.tReferencia.setText(tReferencia);
    }

    public void set_tDescricao(String tDescricao) {
        this.tDescricao.setText(tDescricao);
    }

    public void set_tUnidade(String tUnidade) {
        this.tUnidade.setText(tUnidade);
    }

    public void set_tPreco(String tPreco) {
        this.tPreco.setText(tPreco);
    }

    public void set_Combo_Cor(String ComboCor) {
        this.ComboCor.setSelectedItem(ComboCor);
    }

    public void set_Aux(int aux) {
        this.aux = aux;
    }

    /* Métodos */
    public void set_janela() {
        setVisible(true);
        setTitle("Cadastro de Produtos");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Só fecha esta janela
        setLocationRelativeTo(null);// Inicia a janela no centro da tela
    }

    public void set_padrao() {
        ComboCor = Global.Prenche_ComboBox("select *from COR order by codigo", ComboCor, "NOME");
        // Desabilitando os campos para não serem editados
        tCodigo.setEnabled(false);
        AtivaDesativa(false);
        // Desabilitando os botões por padrão quando a tela é aberta
        Salvar.setEnabled(false);
        Cancelar.setEnabled(false);
    }

    public void add_event_botoes() {
        Novo.addActionListener(this);
        Salvar.addActionListener(this);
        Cancelar.addActionListener(this);
        Pesquisar.addActionListener(this);
    }

    // Função que ativa e desativa os campos para edição
    public void AtivaDesativa(boolean verifica) {
        tReferencia.setEnabled(verifica);
        tDescricao.setEnabled(verifica);
        tUnidade.setEnabled(verifica);
        tPreco.setEnabled(verifica);
        ComboCor.setEnabled(verifica);
    }

    // Função que limpa os campos para edição
    public void Limpa(String l) {
        tReferencia.setText(l);
        tDescricao.setText(l);
        tUnidade.setText(l);
        tPreco.setText(l);
        ComboCor.setSelectedItem(l);
    }

    /* ######################### Manipula Registros ######################### */
    public void novo() {
        Novo.setEnabled(false);
        Salvar.setEnabled(true);
        Cancelar.setEnabled(true);
        Pesquisar.setEnabled(false);
        AtivaDesativa(true);
        aux = 1;
    }

    public void salvar() {
        Global.conection.Update("INSERT INTO PRODUTO ( REFERENCIA, DESCRICAO, UNIDADE, VALOR_UNITARIO, COR ) VALUES (' "
                + tReferencia.getText().toString().trim() + "','"
                + tDescricao.getText().toString().trim() + "','"
                + tUnidade.getText().trim().toString() + "','"
                + tPreco.getText().trim() + "','"
                + ComboCor.getSelectedItem().toString().trim() + "' ) ");
        Limpa("");
        AtivaDesativa(false);
        Novo.setEnabled(true);
        Salvar.setEnabled(false);
        Cancelar.setEnabled(false);
        Pesquisar.setEnabled(true);
    }

    public void atualizar() {
        Global.conection.Update("UPDATE PRODUTO SET referencia = '"
                + tReferencia.getText().toString().trim() + "', descricao = '"
                + tDescricao.getText().trim() + "', unidade = '"
                + tUnidade.getText() + "', valor_unitario  = '"
                + tPreco.getText() + "', cor  = '"
                + ComboCor.getSelectedItem().toString().trim()
                + "'where codigo = '" + tCodigo.getText().trim() + " ' ");

        JOptionPane.showMessageDialog(null, " Alteração feita com sucesso...");
        Limpa("");
        AtivaDesativa(false);
        Novo.setEnabled(true);
        Salvar.setEnabled(false);
        Cancelar.setEnabled(false);
        Pesquisar.setEnabled(true);
    }

    public void cancelar() {
        Novo.setEnabled(true);
        Salvar.setEnabled(false);
        Cancelar.setEnabled(false);
        Pesquisar.setEnabled(true);
        AtivaDesativa(false);
    }

    public void pesquisar() {
        dispose();
        new consultas.Produtos();
    }

    /* ######################### Manipula Registros ######################### */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Novo = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Pesquisar = new javax.swing.JButton();
        Salvar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tPreco = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ComboCor = new javax.swing.JComboBox();
        tDescricao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tUnidade = new javax.swing.JTextField();
        tReferencia = new javax.swing.JTextField();
        tCodigo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/NOVO.png"))); // NOI18N
        Novo.setText("Novo");

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CANCELAR.png"))); // NOI18N
        Cancelar.setText("Cancelar");

        Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/PESQUISAR.png"))); // NOI18N
        Pesquisar.setText("Pesquisar");

        Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/SALVAR.png"))); // NOI18N
        Salvar.setText("Salvar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Salvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Novo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cancelar, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(Pesquisar))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Cancelar, Novo, Pesquisar, Salvar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Novo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Salvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Cancelar)
                .addGap(11, 11, 11)
                .addComponent(Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Cancelar, Novo, Pesquisar, Salvar});

        jLabel2.setText("Referência");

        jLabel6.setText("Preço");

        jLabel4.setText("Unidade");

        jLabel3.setText("Descrição");

        jLabel5.setText("Cor");

        jLabel1.setText("Código");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tReferencia, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(ComboCor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tPreco, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tUnidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tCodigo, tUnidade});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ComboCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Cancelar;
    private javax.swing.JComboBox ComboCor;
    public javax.swing.JButton Novo;
    public javax.swing.JButton Pesquisar;
    public javax.swing.JButton Salvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField tCodigo;
    private javax.swing.JTextField tDescricao;
    private javax.swing.JTextField tPreco;
    private javax.swing.JTextField tReferencia;
    private javax.swing.JTextField tUnidade;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Novo)) {

            novo();

        } else if (e.getSource().equals(Salvar)) {
            if (aux == 1) {

                salvar();
                JOptionPane.showMessageDialog(null, " Gravação realizada com Sucesso... ");

            } else if (aux == 2) {
                atualizar();
            }
        } else if (e.getSource().equals(Cancelar)) {

            cancelar();

        } else if (e.getSource().equals(Pesquisar)) {
            pesquisar();
        } 

    }
}
