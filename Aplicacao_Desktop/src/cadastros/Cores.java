package cadastros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import utilitarios.Global;

public class Cores extends javax.swing.JFrame implements ActionListener {

    private int aux;

    public Cores() {
        initComponents();
        set_janela("Cadastro de Cores");
        set_padrao();
        add_event_botoes();
    }

    /* Geters */
    public JTextField get_tCodigo() {
        return tCodigo;
    }

    public JTextField get_tNome() {
        return tNome;
    }

    /* Seters */
    public void set_tCodigo(String tCodigo) {
        this.tCodigo.setText(tCodigo);
    }

    public void set_tNome(String tNome) {
        this.tNome.setText(tNome);
    }

    public void set_Aux(int aux) {
        this.aux = aux;
    }

    /* Métodos */
    public void set_janela(String nome_janela) {
        setVisible(true);
        setTitle(nome_janela);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Só fecha esta janela
        setLocationRelativeTo(null);// Inicia a janela no centro da tela
    }

    public void set_padrao() {
        // Desabilitando os campos para não serem editados
        tCodigo.setEnabled(false);
        AtivaDesativa(false);
        // Desabilitando os botões por padrão quando a tela é aberta
        Salvar.setEnabled(false);
        Cancelar.setEnabled(false);

        // Desabilitando os campos para não serem editados
        tCodigo.setEnabled(false);
        //AtivaDesativa(false);
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

    /* Função que Ativa e Desativa os campos para edição */
    public void AtivaDesativa(boolean verifica) {
        tNome.setEnabled(verifica);
    }

    // Função que limpa os campos para edição
    public void Limpa(String l) {
        tNome.setText(l);
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

        Global.conection.Update("INSERT INTO COR ( NOME ) VALUES (' "
                + tNome.getText().toString().trim() + "' ) ");

        Limpa("");
        AtivaDesativa(false);
        Novo.setEnabled(true);
        Salvar.setEnabled(false);
        Cancelar.setEnabled(false);
        Pesquisar.setEnabled(true);
    }

    public void atualizar() {
        Global.conection.Update("UPDATE COR SET nome = '"
                + tNome.getText().toString().trim()
                + "'where codigo = '" + tCodigo.getText() + " ' ");
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
        new consultas.Cores();
    }

    /* ######################### Manipula Registros ######################### */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        tNome = new javax.swing.JTextField();
        tCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Salvar = new javax.swing.JButton();
        Novo = new javax.swing.JButton();
        Pesquisar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Nome");

        jLabel1.setText("Código");

        Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/SALVAR.png"))); // NOI18N
        Salvar.setText("Salvar");

        Novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/NOVO.png"))); // NOI18N
        Novo.setText("Novo");

        Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/PESQUISAR.png"))); // NOI18N
        Pesquisar.setText("Pesquisar");

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CANCELAR.png"))); // NOI18N
        Cancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Salvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Novo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Cancelar, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Pesquisar))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Cancelar, Novo, Pesquisar, Salvar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Novo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Salvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Cancelar)
                        .addGap(11, 11, 11)
                        .addComponent(Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Cancelar, Novo, Pesquisar, Salvar});

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Cancelar;
    public javax.swing.JButton Novo;
    public javax.swing.JButton Pesquisar;
    public javax.swing.JButton Salvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField tCodigo;
    private javax.swing.JTextField tNome;
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
