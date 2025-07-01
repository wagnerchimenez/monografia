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

public class Clientes extends JFrame implements ActionListener {

    /**
     *  Geters
     */
    public JTextField get_tCodigo() {
        return tCodigo;
    }

    public JTextField get_tNome() {
        return tNome;
    }

    public JComboBox get_Combo_Sexo() {
        return ComboSexo;
    }

    public JTextField get_tRG() {
        return tRG;
    }

    public JTextField get_tCPF() {
        return tCPF;
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

    public JTextField get_tCelular() {
        return tCelular;
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

    /**
     *  Seters
     */
    public void set_tCodigo(String tCodigo) {
        this.tCodigo.setText(tCodigo);
    }

    public void set_tNome(String tNome) {
        this.tNome.setText(tNome);
    }

    public void set_Combo_Sexo(String ComboSexo) {
        this.ComboSexo.setSelectedItem(ComboSexo);
    }

    public void set_tRG(String tRG) {
        this.tRG.setText(tRG);
    }

    public void set_tCPF(String tCPF) {
        this.tCPF.setText(tCPF);
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

    public void set_tCelular(String tCelular) {
        this.tCelular.setText(tCelular);
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

    public void set_Aux(Integer recording_Mode) {
        this.recording_Mode = recording_Mode;
    }

    /**
     *  Método Construtor
     *  ########################################################################
     */
    public Clientes() {
        initComponents();
        set_janela();
        set_padrao();
        add_Event_Button();
    }

    public void set_janela() {
        setVisible(true);
        setTitle("Cadastros de Clientes");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); /* Só fecha esta janela */
        setLocationRelativeTo(null);/* Inicia a janela no centro da tela */
    }

    public void set_padrao() {
        ComboCidade = Global.Prenche_ComboBox("select *from CIDADE order by NOME ", ComboCidade, "NOME");
        ComboEstado = Global.Prenche_ComboBox("select *from ESTADO order by UF ", ComboEstado, "UF");

        // Desabilitando os campos para não serem editados
        tCodigo.setEnabled(false);
        Ativa_Desativa(false);
        // Desabilitando os botões por padrão quando a tela é aberta
        Salvar.setEnabled(false);
        Cancelar.setEnabled(false);
    }

    public void add_Event_Button() {
        Novo.addActionListener(this);
        Salvar.addActionListener(this);
        Cancelar.addActionListener(this);
        Pesquisar.addActionListener(this);
    }

    public void Limpa(String l) {
        tNome.setText(l);
        tRG.setText(l);
        tCPF.setText(l);
        tEndereco.setText(l);
        tBairro.setText(l);
        tCEP.setText(l);
        tFone.setText(l);
        tFax.setText(l);
        tCelular.setText(l);
        tContato.setText(l);
        tEmail.setText(l);
        tData.setText(l);
        tObservacao.setText(l);
    }

    public void Ativa_Desativa(boolean check) {
        tNome.setEnabled(check);
        ComboSexo.setEnabled(check);
        tRG.setEnabled(check);
        tCPF.setEnabled(check);
        tEndereco.setEnabled(check);
        tBairro.setEnabled(check);
        ComboCidade.setEnabled(check);
        ComboEstado.setEnabled(check);
        tCEP.setEnabled(check);
        tFone.setEnabled(check);
        tFax.setEnabled(check);
        tCelular.setEnabled(check);
        tContato.setEnabled(check);
        tEmail.setEnabled(check);
        tData.setEnabled(check);
        tObservacao.setEnabled(check);
    }

    /* Métodos responsáveis por manipular registros */
    public void novo() {
        Novo.setEnabled(false);
        Salvar.setEnabled(true);
        Cancelar.setEnabled(true);
        Pesquisar.setEnabled(false);
        Ativa_Desativa(true);
        recording_Mode = 1;
        tData.setText(getDateTime());
    }

    public void salvar() {
        java.sql.Date data = null;
        try {
            data = Global.formataData(tData.getText());
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        Global.conection.Update("INSERT INTO CLIENTE ( NOME, SEXO, RG, CPF, ENDERECO, BAIRRO, CIDADE, ESTADO, CEP, FONE, FAX, CELULAR, CONTATO, EMAIL, DATA, OBSERVACAO ) VALUES (' "
                + tNome.getText().toString().trim() + "','"
                + ComboSexo.getSelectedItem().toString().trim() + "','"
                + tRG.getText().toString().trim() + "','"
                + tCPF.getText().toString().trim() + "','"
                + tEndereco.getText().toString().trim() + "','"
                + tBairro.getText().toString().trim() + "','"
                + ComboCidade.getSelectedItem().toString().trim() + "','"
                + ComboEstado.getSelectedItem().toString().trim() + "','"
                + tCEP.getText().toString().trim() + "','"
                + tFone.getText().toString().trim() + "','"
                + tFax.getText().toString().trim() + "','"
                + tCelular.getText().toString().trim() + "','"
                + tContato.getText().toString().trim() + "','"
                + tEmail.getText().toString().trim() + "','"
                + data + "','" + tObservacao.getText().toString().trim() + "' ) ");

        Limpa("");
        Ativa_Desativa(false);
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
        Ativa_Desativa(false);
    }

    public void alterar() {
        java.sql.Date data = null;
        try {
            data = Global.formataData(tData.getText());
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        Global.conection.Update("UPDATE CLIENTE SET nome = '" + tNome.getText().toString().trim() + "', sexo = '" + ComboSexo.getSelectedItem().toString().trim() + "', rg = '" + tRG.getText().toString().trim() + "', cpf = '" + tCPF.getText().toString().trim() + "', endereco = '" + tEndereco.getText().toString().trim() + "', bairro = '" + tBairro.getText().toString().trim() + "', cidade = '" + ComboCidade.getSelectedItem().toString().trim() + "', estado = '" + ComboEstado.getSelectedItem().toString().trim() + "', cep = '" + tCEP.getText().toString().trim() + "', fone = '" + tFone.getText().toString().trim() + "', fax = '" + tFax.getText().toString().trim() + "', celular = '" + tCelular.getText().toString().trim() + "', contato = '" + tContato.getText().toString().trim() + "', email = '" + tEmail.getText().toString().trim() + "', data = '" + data + "', observacao = '" + tObservacao.getText().toString().trim() + "'where codigo = '" + tCodigo.getText() + " ' ");
        JOptionPane.showMessageDialog(null, " Alteração feita com sucesso...");
        Limpa("");
        Ativa_Desativa(false);
        Novo.setEnabled(true);
        Salvar.setEnabled(false);
        Cancelar.setEnabled(false);
        Pesquisar.setEnabled(true);
    }

    public void pesquisar() {
        dispose();
        new consultas.Clientes();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        try
        {
            masc_fax = new MaskFormatter("(##) ####-####");
        }
        catch(Exception erro)
        {
        }
        tFax = new JFormattedTextField(masc_fax);
        try
        {
            masc_cel = new MaskFormatter("(##) ####-####");
        }
        catch(Exception erro)
        {
        }
        tCelular = new JFormattedTextField(masc_cel);
        tCodigo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tContato = new javax.swing.JTextField();
        ComboSexo = new javax.swing.JComboBox();
        tNome = new javax.swing.JTextField();
        try
        {
            masc_data = new MaskFormatter("##/##/####");
        }
        catch(Exception erro)
        {
        }
        tData = new JFormattedTextField(masc_data);
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        try
        {
            masc_cep = new MaskFormatter("#####-###");
        }
        catch(Exception erro)
        {
        }
        tCEP = new JFormattedTextField(masc_cep);
        jLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        try
        {
            masc_cpf = new MaskFormatter("###.###.###-##");
        }
        catch(Exception erro)
        {
        }
        tCPF = new JFormattedTextField(masc_cpf);
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tEndereco = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tObservacao = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        tBairro = new javax.swing.JTextField();
        ComboEstado = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        try
        {
            masc_fone = new MaskFormatter("(##) ####-####");
        }
        catch(Exception erro)
        {
        }
        tFone = new JFormattedTextField(masc_fone);
        try
        {
            masc_rg = new MaskFormatter("###.###.###");
        }
        catch(Exception erro)
        {
        }
        tRG = new JFormattedTextField(masc_rg);
        ComboCidade = new javax.swing.JComboBox();
        tEmail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        Cancelar = new javax.swing.JButton();
        Salvar = new javax.swing.JButton();
        Pesquisar = new javax.swing.JButton();
        Novo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel8.setText("Contato");

        jLabel3.setText("RG");

        jLabel5.setText("Cidade");

        jLabel9.setText("Email");

        jLabel7.setText("Fone");

        jLabel12.setText("CPF");

        ComboSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F", " " }));

        jLabel2.setText("Nome");

        jLabel11.setText("Observação");

        jLabel13.setText("Estado");

        jLabel6.setText("Bairro");

        jLabel17.setText("Sexo");

        jLabel1.setText("Código");

        jLabel15.setText("Fax");

        jLabel4.setText("Endereço");

        jLabel16.setText("Celular");

        tObservacao.setColumns(20);
        tObservacao.setRows(5);
        jScrollPane1.setViewportView(tObservacao);

        jLabel10.setText("Data");

        jLabel14.setText("CEP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)))
                    .addComponent(jLabel11)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ComboSexo, 0, 146, Short.MAX_VALUE))
                    .addComponent(tEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addComponent(tBairro, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ComboCidade, 0, 223, Short.MAX_VALUE)
                                    .addComponent(tRG, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel12))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel13)))))
                            .addComponent(tFone, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tFax)
                                    .addComponent(ComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tCelular)
                                    .addComponent(tCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(tContato, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addComponent(tEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tData, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 419, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(tCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ComboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(ComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(tCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(jLabel11))
                .addContainerGap())
        );

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CANCELAR.png"))); // NOI18N
        Cancelar.setText("Cancelar");

        Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/SALVAR.png"))); // NOI18N
        Salvar.setText("Salvar");

        Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/PESQUISAR.png"))); // NOI18N
        Pesquisar.setText("Pesquisar");

        Novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/NOVO.png"))); // NOI18N
        Novo.setText("Novo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Salvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Novo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cancelar, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(Pesquisar))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Cancelar, Novo, Pesquisar, Salvar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
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

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Cancelar, Novo, Pesquisar, Salvar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JTextField tCPF;
    private javax.swing.JTextField tCelular;
    private javax.swing.JTextField tCodigo;
    private javax.swing.JTextField tContato;
    private javax.swing.JTextField tData;
    private javax.swing.JTextField tEmail;
    private javax.swing.JTextField tEndereco;
    private javax.swing.JTextField tFax;
    private javax.swing.JTextField tFone;
    private javax.swing.JTextField tNome;
    private javax.swing.JTextArea tObservacao;
    private javax.swing.JTextField tRG;
    // End of variables declaration//GEN-END:variables
    /**
     *  recording_Mode
     *  0 - Default
     *  1 - save_Record
     *  2 - change_Record
     */
    private int recording_Mode = 0;
    MaskFormatter masc_rg;
    MaskFormatter masc_cpf;
    MaskFormatter masc_cep;
    MaskFormatter masc_fone;
    MaskFormatter masc_fax;
    MaskFormatter masc_cel;
    MaskFormatter masc_data;

    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(Novo)) {

            novo();

        } else if (e.getSource().equals(Salvar)) {
            if (recording_Mode == 1) {

                salvar();
                JOptionPane.showMessageDialog(null, " Gravação realizada com Sucesso... ");

            } else if (recording_Mode == 2) {
                alterar();
            }
        } else if (e.getSource().equals(Cancelar)) {

            cancelar();

        } else if (e.getSource().equals(Pesquisar)) {
            pesquisar();
        } 
    }
}
