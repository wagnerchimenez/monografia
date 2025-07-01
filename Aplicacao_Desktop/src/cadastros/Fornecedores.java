package cadastros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import utilitarios.Global;

public class Fornecedores extends javax.swing.JFrame implements ActionListener {

    private int aux = 0;

    public Fornecedores() {
        initComponents();

        set_janela();
        set_padrao();
        add_event_botoes();
    }

    /* Geters */
    public JTextField get_tCodigo(){
        return tCodigo;
    }

    public JTextField get_tRazao_Social(){
        return tRazao_Social;
    }

    public JTextField get_tFantasia(){
        return tFantasia;
    }

    public JTextField get_tCNPJ(){
        return tCNPJ;
    }

    public JTextField get_tIE(){
        return tIE;
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

    public JTextField get_CEP() {
        return tCEP;
    }

    public JTextField get_tFone() {
        return tFone;
    }

    public JTextField get_tFax() {
        return tFax;
    }

    public JTextField get_tContato() {
        return tContato;
    }

    public JTextField get_tEmail() {
        return tEmail;
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
    public void set_Aux(int aux){
        this.aux = aux;
    }

    public void set_tCodigo(String tCodigo){
        this.tCodigo.setText(tCodigo);
    }

    public void set_tRazao_Social(String tRazao_Social){
        this.tRazao_Social.setText(tRazao_Social);
    }

    public void set_tFantasia(String tFantasia){
        this.tFantasia.setText(tFantasia);
    }

    public void set_tCNPJ(String tCNPJ){
        this.tCNPJ.setText(tCNPJ);
    }

    public void set_tIE(String tIE){
        this.tIE.setText(tIE);
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

    public void set_tCEP(String tCEP) {
        this.tCEP.setText(tCEP);
    }

    public void set_tFone(String tFone) {
        this.tFone.setText(tFone);
    }

    public void set_tFax(String tFax) {
        this.tFax.setText(tFax);
    }

    public void set_tContato(String tContato) {
        this.tContato.setText(tContato);
    }

    public void set_tEmail(String tEmail) {
        this.tEmail.setText(tEmail);
    }

    public void set_tData(String tData) {
        this.tData.setText(tData);
    }

    public void set_tObservacao(String tObservacao) {
        this.tObservacao.setText(tObservacao);
    }

    /* Métodos */
    public void set_janela() {
        setVisible(true);
        setTitle("Cadastro de Fornecedores");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Só fecha esta janela
        setLocationRelativeTo(null);// Inicia a janela no centro da tela
    }

    public void set_padrao() {
        ComboCidade = Global.Prenche_ComboBox("select *from CIDADE order by CODIGO", ComboCidade, "NOME");
        ComboEstado = Global.Prenche_ComboBox("select *from ESTADO order by CODIGO", ComboEstado, "UF");

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
        tRazao_Social.setEnabled(verifica);
        tFantasia.setEnabled(verifica);
        tCNPJ.setEnabled(verifica);
        tIE.setEnabled(verifica);
        tEndereco.setEnabled(verifica);
        tBairro.setEnabled(verifica);
        ComboCidade.setEnabled(verifica);
        ComboEstado.setEnabled(verifica);
        tCEP.setEnabled(verifica);
        tFone.setEnabled(verifica);
        tFax.setEnabled(verifica);
        tContato.setEnabled(verifica);
        tEmail.setEnabled(verifica);
        tData.setEnabled(verifica);
        tObservacao.setEnabled(verifica);
    }

    // Função que limpa os campos para edição
    public void Limpa(String l) {
        tRazao_Social.setText(l);
        tFantasia.setText(l);
        tCNPJ.setText(l);
        tEndereco.setText(l);
        tBairro.setText(l);
        ComboCidade.setSelectedItem(l);
        ComboEstado.setSelectedItem(l);
        tCEP.setText(l);
        tFone.setText(l);
        tFax.setText(l);
        tContato.setText(l);
        tEmail.setText(l);
        tData.setText(l);
        tObservacao.setText(l);
    }

    /* ######################### Manipula Registros ######################### */
    public void novo() {
        Novo.setEnabled(false);
        Salvar.setEnabled(true);
        Cancelar.setEnabled(true);
        Pesquisar.setEnabled(false);
        AtivaDesativa(true);
        aux = 1;
        tData.setText(getDateTime());
    }

    public void salvar() {

        java.sql.Date data = null;
        try {
            data = Global.formataData(tData.getText());
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        Global.conection.Update("INSERT INTO FORNECEDOR ( RAZAO_SOCIAL, FANTASIA, CNPJ, IE, ENDERECO, BAIRRO, CIDADE, ESTADO, CEP, FONE, FAX, CONTATO, EMAIL,DATA, OBSERVACAO ) VALUES (' "
                + tRazao_Social.getText().toString().trim() + "','"
                + tFantasia.getText().toString().trim() + "','"
                + tCNPJ.getText().toString().trim() + "','"
                + tIE.getText().toString().trim() + "','"
                + tEndereco.getText().toString().trim() + "','"
                + tBairro.getText().toString().trim() + "','"
                + ComboCidade.getSelectedItem().toString().trim() + "','"
                + ComboEstado.getSelectedItem().toString().trim() + "','"
                + tCEP.getText().toString().trim() + "','"
                + tFone.getText().toString().trim() + "','"
                + tFax.getText().toString().trim() + "','"
                + tContato.getText().toString().trim() + "','"
                + tEmail.getText().toString().trim() + "','"
                + data + "','"
                + tObservacao.getText().toString().trim() + "' ) ");

        Limpa("");
        AtivaDesativa(false);
        Novo.setEnabled(true);
        Salvar.setEnabled(false);
        Cancelar.setEnabled(false);
        Pesquisar.setEnabled(true);
    }

    public void atualizar() {
        java.sql.Date data = null;
        try {
            data = Global.formataData(tData.getText());
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        Global.conection.Update("UPDATE FORNECEDOR SET razao_social = '"
                + tRazao_Social.getText().toString().trim() + "', fantasia = '"
                + tFantasia.getText().toString().trim() + "', cnpj = '"
                + tCNPJ.getText().toString().trim() + "', ie = '"
                + tIE.getText().toString().trim() + "', endereco = '"
                + tEndereco.getText().toString().trim() + "', bairro = '"
                + tBairro.getText().toString().trim() + "', cidade = '"
                + ComboCidade.getSelectedItem().toString().trim() + "', estado = '"
                + ComboEstado.getSelectedItem().toString().trim() + "', cep = '"
                + tCEP.getText().toString().trim() + "', fone = '"
                + tFone.getText().toString().trim() + "', fax = '"
                + tFax.getText().toString().trim() + "', contato = '"
                + tContato.getText().toString().trim() + "', email = '"
                + tEmail.getText().toString().trim() + "', data = '"
                + data + "', observacao = '"
                + tObservacao.getText().toString().trim()
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
        new consultas.Fornecedores();

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Novo = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Pesquisar = new javax.swing.JButton();
        Salvar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tEndereco = new javax.swing.JTextField();
        tFone = new javax.swing.JTextField();
        tFantasia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tObservacao = new javax.swing.JTextArea();
        tRazao_Social = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tFax = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tContato = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tEmail = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tData = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tCEP = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ComboCidade = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        tCodigo = new javax.swing.JTextField();
        tBairro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ComboEstado = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        tCNPJ = new javax.swing.JTextField();
        tIE = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Cancelar, Novo, Pesquisar, Salvar});

        tObservacao.setColumns(20);
        tObservacao.setRows(5);
        jScrollPane1.setViewportView(tObservacao);

        jLabel2.setText("Razão Social");

        jLabel9.setText("Contato");

        jLabel1.setText("Código");

        jLabel11.setText("Data");

        jLabel6.setText("Bairro");

        jLabel14.setText("Estado");

        jLabel13.setText("Observação");

        jLabel8.setText("Fone");

        jLabel15.setText("CEP");

        jLabel5.setText("Endereço");

        jLabel10.setText("Email");

        jLabel12.setText("IE");

        jLabel7.setText("Cidade");

        jLabel4.setText("CNPJ");

        jLabel16.setText("Fax");

        jLabel3.setText("Fantasia");

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
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tData, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addComponent(tEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addComponent(tContato, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addComponent(tRazao_Social, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addComponent(tFantasia, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tIE, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                    .addComponent(tEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addComponent(tBairro, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tFone, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboEstado, 0, 76, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tFax, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ComboCidade, tCNPJ});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tFax, tFone});

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
                    .addComponent(tRazao_Social, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tIE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(ComboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(ComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(tFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Cancelar;
    private javax.swing.JComboBox ComboCidade;
    private javax.swing.JComboBox ComboEstado;
    public javax.swing.JButton Novo;
    public javax.swing.JButton Pesquisar;
    public javax.swing.JButton Salvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tBairro;
    private javax.swing.JTextField tCEP;
    private javax.swing.JTextField tCNPJ;
    private javax.swing.JTextField tCodigo;
    private javax.swing.JTextField tContato;
    private javax.swing.JTextField tData;
    private javax.swing.JTextField tEmail;
    private javax.swing.JTextField tEndereco;
    private javax.swing.JTextField tFantasia;
    private javax.swing.JTextField tFax;
    private javax.swing.JTextField tFone;
    private javax.swing.JTextField tIE;
    private javax.swing.JTextArea tObservacao;
    private javax.swing.JTextField tRazao_Social;
    // End of variables declaration//GEN-END:variables

    @Override
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
