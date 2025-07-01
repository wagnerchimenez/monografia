package cadastros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import utilitarios.Global;
import utilitarios.conexao;

public class Funcionarios extends javax.swing.JFrame implements ActionListener {

    private int aux = 0;
    MaskFormatter masc_fone;
    MaskFormatter masc_cel;
    MaskFormatter masc_data_nascimento;
    MaskFormatter masc_data;

    public Funcionarios() {
        initComponents();

        set_janela(" Cadastro de Funcionários ");
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

    public JComboBox get_Combo_Sexo() {
        return ComboSexo;
    }

    public JTextField get_tEmail() {
        return tEmail;
    }

    public JTextField get_tFone() {
        return tFone;
    }

    public JTextField get_tCelular() {
        return tCelular;
    }

    public JTextField get_tEndereco() {
        return tEndereco;
    }

    public JTextField get_tBairro() {
        return tBairro;
    }

    public JComboBox get_Combo_Cidade() {
        return ComboCidade;
    }

    public JComboBox get_Combo_Estado() {
        return ComboEstado;
    }

    public JTextField get_tData_Nascimento() {
        return tData_Nascimento;
    }

    public JTextField get_tData() {
        return tData;
    }

    public JTextArea get_tObservacao() {
        return tObservacao;
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /* Seters */
    public void set_tCodigo(String tCodigo) {
        this.tCodigo.setText(tCodigo);
    }

    public void set_tNome(String tNome) {
        this.tNome.setText(tNome);
    }

    public void set_Combo_Sexo(String ComboSexo) {
        this.ComboSexo.setSelectedItem(ComboSexo);
    }

    public void set_tEmail(String tEmail) {
        this.tEmail.setText(tEmail);
    }

    public void set_tFone(String tFone) {
        this.tFone.setText(tFone);
    }

    public void set_tCelular(String tCelualar) {
        this.tCelular.setText(tCelualar);
    }

    public void set_tEndereco(String tEndereco) {
        this.tEndereco.setText(tEndereco);
    }

    public void set_tBairro(String tBairro) {
        this.tBairro.setText(tBairro);
    }

    public void set_Combo_Cidade(String ComboCidade) {
        this.ComboCidade.setSelectedItem(ComboCidade);
    }

    public void set_Combo_Estado(String ComboEstado) {
        this.ComboEstado.setSelectedItem(ComboEstado);
    }

    public void set_tData_Nascimento(String tData_Nascimento) {
        this.tData_Nascimento.setText(tData_Nascimento);
    }

    public void set_tData(String tData) {
        this.tData.setText(tData);
    }

    public void set_tObservacao(String tObservacao) {
        this.tObservacao.setText(tObservacao);
    }

    public void set_Aux(int aux) {
        this.aux = aux;
    }

    // Métodos
    public void set_janela(String nome) {
        setVisible(true);
        setTitle(nome);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Só fecha esta janela
        setLocationRelativeTo(null);// Inicia a janela no centro da tela
    }

    public void set_padrao() {
        ComboCidade = Global.Prenche_ComboBox("select *from CIDADE order by NOME", ComboCidade, "NOME");
        ComboEstado = Global.Prenche_ComboBox("select *from ESTADO order by UF", ComboEstado, "UF");

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

    public void AtivaDesativa(boolean verifica) {
        tNome.setEnabled(verifica);
        ComboSexo.setEnabled(verifica);
        tEmail.setEnabled(verifica);
        tFone.setEnabled(verifica);
        tCelular.setEnabled(verifica);
        tEndereco.setEnabled(verifica);
        tBairro.setEnabled(verifica);
        ComboCidade.setEnabled(verifica);
        ComboEstado.setEnabled(verifica);
        tData_Nascimento.setEnabled(verifica);
        tData.setEnabled(verifica);
        tObservacao.setEnabled(verifica);
    }

    // Função que limpa os campos para edição
    public void Limpa(String l) {
        tNome.setText(l);
        tEmail.setText(l);
        tFone.setText(l);
        tCelular.setText(l);
        tEndereco.setText(l);
        tBairro.setText(l);
        tData_Nascimento.setText(l);
        tData.setText(l);
        tObservacao.setText(l);
    }

    /* ######################### Manipula Registros ######################### */
    public void Novo() {
        Novo.setEnabled(false);
        Salvar.setEnabled(true);
        Cancelar.setEnabled(true);
        Pesquisar.setEnabled(false);
        AtivaDesativa(true);
        aux = 1;
        tData.setText(getDateTime());
    }

    public void Salvar() {

        if(tData_Nascimento.getText().equals("  /  /    ") ){
            JOptionPane.showMessageDialog(null, "Por favor insira uma data de nascimento válida para realização do cadastro");
        }else{
            java.sql.Date data = null;
        try {
            data = Global.formataData(tData.getText());
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.sql.Date data_nascimento = null;
        try {
            data_nascimento = Global.formataData(tData_Nascimento.getText());
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        Global.conection.Update("INSERT INTO FUNCIONARIO ( NOME, SEXO, EMAIL,FONE,CELULAR, ENDERECO, BAIRRO, CIDADE, ESTADO,DATA_NASCIMENTO,DATA,OBSERVACAO ) VALUES (' "
                + tNome.getText().toString() + "','"
                + ComboSexo.getSelectedItem().toString() + "','"
                + tEmail.getText().toString() + "','"
                + tFone.getText().toString() + "','"
                + tCelular.getText().toString() + "','"
                + tEndereco.getText().toString() + "','"
                + tBairro.getText().toString() + "','"
                + ComboCidade.getSelectedItem().toString() + "','"
                + ComboEstado.getSelectedItem().toString() + "','"
                + data_nascimento + "','"
                + data + "','"
                + tObservacao.getText().toString() + "' ) ");

        Limpa("");
        AtivaDesativa(false);
        Novo.setEnabled(true);
        Salvar.setEnabled(false);
        Cancelar.setEnabled(false);
        Pesquisar.setEnabled(true);

        JOptionPane.showMessageDialog(null, " Gravação realizada com Sucesso... ");
        }
    }

    public void Atualizar() {
        Global.conection.Update("UPDATE FUNCIONARIO SET nome = '"
                + tNome.getText().toString() + "', sexo = '"
                + ComboSexo.getSelectedItem().toString() + "', email = '"
                + tEmail.getText().toString() + "', fone = '"
                + tFone.getText().toString() + "', celular = '"
                + tCelular.getText().toString() + "', endereco = '"
                + tEndereco.getText().toString() + "', bairro = '"
                + tBairro.getText().toString() + "', cidade = '"
                + ComboCidade.getSelectedItem().toString() + "', estado = '"
                + ComboEstado.getSelectedItem().toString() + "', data_nascimento = '"
                + tData_Nascimento.getText() + "', data = '"
                + tData.getText() + "', observacao = '"
                + tObservacao.getText().toString()
                + "'where codigo = '" + tCodigo.getText() + " ' ");
        JOptionPane.showMessageDialog(null, " Alteração feita com sucesso...");
        Limpa("");
        AtivaDesativa(false);
        Novo.setEnabled(true);
        Salvar.setEnabled(false);
        Cancelar.setEnabled(false);
        Pesquisar.setEnabled(true);
    }

    public void Cancelar() {
        Novo.setEnabled(true);
        Salvar.setEnabled(false);
        Cancelar.setEnabled(false);
        Pesquisar.setEnabled(true);
        AtivaDesativa(false);
    }

    public void Pesquisar() {
        dispose();
        new consultas.Funcionarios();

    }

    public void Relatorio() {
    }

    /* ######################### Manipula Registros ######################### */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tObservacao = new javax.swing.JTextArea();
        try
        {
            masc_data = new MaskFormatter("##/##/####");
        }
        catch(Exception erro)
        {
        }
        tData = new JFormattedTextField(masc_data);
        try
        {
            masc_data_nascimento = new MaskFormatter("##/##/####");
        }
        catch(Exception erro)
        {
        }
        tData_Nascimento = new JFormattedTextField(masc_data_nascimento);
        jLabel11 = new javax.swing.JLabel();
        tEndereco = new javax.swing.JTextField();
        try
        {
            masc_cel = new MaskFormatter("(##) ####-####");
        }
        catch(Exception erro)
        {
        }
        tCelular = new JFormattedTextField(masc_cel);
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tBairro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ComboCidade = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tCodigo = new javax.swing.JTextField();
        ComboEstado = new javax.swing.JComboBox();
        tNome = new javax.swing.JTextField();
        tEmail = new javax.swing.JTextField();
        try
        {
            masc_fone = new MaskFormatter("(##) ####-####");
        }
        catch(Exception erro)
        {
        }
        tFone = new JFormattedTextField(masc_fone);
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ComboSexo = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        Novo = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Salvar = new javax.swing.JButton();
        Pesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tObservacao.setColumns(20);
        tObservacao.setRows(5);
        jScrollPane1.setViewportView(tObservacao);

        jLabel11.setText("Bairro");

        jLabel10.setText("Endereco");

        jLabel9.setText("Celular");

        jLabel12.setText("Cidade");

        jLabel6.setText("Data");

        jLabel5.setText("Data Nascimento");

        jLabel13.setText("Estado");

        jLabel2.setText("Nome");

        jLabel1.setText("Código");

        jLabel4.setText("Fone");

        jLabel3.setText("Email");

        jLabel7.setText("Observação");

        jLabel8.setText("Sexo");

        ComboSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F" }));

        Novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/NOVO.png"))); // NOI18N
        Novo.setText("Novo");

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CANCELAR.png"))); // NOI18N
        Cancelar.setText("Cancelar");

        Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/SALVAR.png"))); // NOI18N
        Salvar.setText("Salvar");

        Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/PESQUISAR.png"))); // NOI18N
        Pesquisar.setText("Pesquisar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(57, 57, 57)
                .addComponent(Novo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Salvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Cancelar)
                .addGap(11, 11, 11)
                .addComponent(Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Cancelar, Novo, Pesquisar, Salvar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tFone, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                    .addComponent(tNome, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tData, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tData_Nascimento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                            .addComponent(ComboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tData, tData_Nascimento, tFone});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jLabel3)
                            .addComponent(tEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(tCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)))
                            .addComponent(jLabel10))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(ComboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tData_Nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(ComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Cancelar;
    private javax.swing.JComboBox ComboCidade;
    private javax.swing.JComboBox ComboEstado;
    private javax.swing.JComboBox ComboSexo;
    public javax.swing.JButton Novo;
    public javax.swing.JButton Pesquisar;
    public javax.swing.JButton Salvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tBairro;
    private javax.swing.JTextField tCelular;
    private javax.swing.JTextField tCodigo;
    private javax.swing.JTextField tData;
    private javax.swing.JTextField tData_Nascimento;
    private javax.swing.JTextField tEmail;
    private javax.swing.JTextField tEndereco;
    private javax.swing.JTextField tFone;
    private javax.swing.JTextField tNome;
    private javax.swing.JTextArea tObservacao;
    // End of variables declaration//GEN-END:variables

    // Métodos Geters
    public JTextField gettCodigo() {
        return tCodigo;
    }

    public JTextField gettNome() {
        return tNome;
    }

    public JComboBox getComboSexo() {
        return ComboSexo;
    }

    public JTextField gettEmail() {
        return tEmail;
    }

    public JTextField gettFone() {
        return tFone;
    }

    public JTextField gettCelular() {
        return tCelular;
    }

    public JTextField gettData_Nascimento() {
        return tData_Nascimento;
    }

    public JTextField gettData() {
        return tData;
    }

    public JTextArea gettObservacao() {
        return tObservacao;
    }

    // Métodos Seters
    public void setCodigo(JTextField tCodigo) {
        this.tCodigo = tCodigo;
    }

    public void setNome(JTextField tNome) {
        this.tNome = tNome;
    }

    public void setSexo(JComboBox ComboSexo) {
        this.ComboSexo = ComboSexo;
    }

    public void setEmail(JTextField tEmail) {
        this.tEmail = tEmail;
    }

    public void setFone(JTextField tFone) {
        this.tFone = tFone;
    }

    public void setCelular(JTextField tCelular) {
        this.tCelular = tCelular;
    }

    public void setData_Nascimento(JTextField tData_Nascimento) {
        this.tData_Nascimento = tData_Nascimento;
    }

    public void setData(JTextField tData) {
        this.tData = tData;
    }

    public void setObservacao(JTextArea tObservacao) {
        this.tObservacao = tObservacao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Novo)) {
            Novo();
        } else if (e.getSource().equals(Salvar)) {
            if (aux == 1) {

                Salvar();

            } else if (aux == 2) {

                Atualizar();

            }
        } else if (e.getSource().equals(Cancelar)) {

            Cancelar();

        } else if (e.getSource().equals(Pesquisar)) {

            Pesquisar();

        }

    }
}
