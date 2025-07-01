package cadastros;

import consultas.Produtos_Projetos;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import utilitarios.Global;

public class Projetos extends javax.swing.JFrame implements ActionListener {

    /*
     *
     */
    public void set_janela() {
        setVisible(true);
        setTitle("Cadastro de Projetos");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Só fecha esta janela
        setLocationRelativeTo(null);// Inicia a janela no centro da tela
    }

    /*
     *
     */
    public void set_padrao() {
        // Desabilitando os campos para não serem editados
        tCodigo.setEnabled(false);
        AtivaDesativa_Aba1(false);
        AtivaDesativa_Aba2(false);
        // Desabilitando os botões por padrão quando a tela é aberta
        Salvar.setEnabled(false);
        Cancelar.setEnabled(false);
        escolher_desenho.setEnabled(false);
        Botao_Buscar_Produto.setEnabled(false);
        Botao_Adicionar.setEnabled(false);
        Botao_Excluir.setEnabled(false);

        // Setando Aba 2
        // Auto Redimenciona o tamanho da tabela
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //  permiti somente a seleção de linhas ( unicas ) e nao multiplas.
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // bloquear as colunas de uma tabela para que ela não possa mais ser mudada de posição
        table.getTableHeader().setReorderingAllowed(false);

        tValor_Total_Produtos.setText("0");

        /* Desabilita abas para edição */
        // jTabbedPane1.setEnabledAt(1, false);
    }

    /*
     *
     */
    public void add_event_botoes() {
        Novo.addActionListener(this);
        Salvar.addActionListener(this);
        Cancelar.addActionListener(this);
        Pesquisar.addActionListener(this);
        escolher_desenho.addActionListener(this);
        Botao_Buscar_Produto.addActionListener(this);
        Botao_Adicionar.addActionListener(this);
        Botao_Excluir.addActionListener(this);
    }

    /*
     * Construtor
     */
    public Projetos() {
        modeloDaTabela = modeloTabela();
        vetor_produtos_excluidos = new Vector<Integer>();
        initComponents();
        set_janela();
        set_padrao();
        add_event_botoes();
    }

    /*
     * Geters
     */
    public JTextField get_tCodigo() {
        return tCodigo;
    }

    public JTextField get_tReferencia() {
        return tReferencia;
    }

    public JTextField get_tDescricao() {
        return tDescricao;
    }

    /*
     * Seters Aba 1
     */
    public void set_Aux(int aux) {
        this.aux = aux;
    }

    public void set_tCodigo(String tCodigo) {
        this.tCodigo.setText(tCodigo);
    }

    public void set_tReferencia(String tReferencia) {
        this.tReferencia.setText(tReferencia);
    }

    public void set_tDescricao(String tDescricao) {
        this.tDescricao.setText(tDescricao);
    }

    public void set_tDesenho(String tDesenho) {
        this.tDesenho.setText(tDesenho);
    }

    public void set_Label_Desenho(String Label_Desenho) {
        ImageIcon img = new ImageIcon(Label_Desenho);
        img.setImage(img.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
        this.Label_Desenho.setIcon(img);
        this.Label_Desenho.setText("");
    }

    /*
     * Seters Aba 2
     */
    public void set_tCodigo_Produto(String tCodigo_Produto) {
        this.tCodigo_Produto.setText(tCodigo_Produto);
    }

    public void set_tReferencia_Produto(String tReferencia_Produto) {
        this.tReferencia_Produto.setText(tReferencia_Produto);
    }

    public void set_tDescricao_Produto(String tDescricao_Produto) {
        this.tDescricao_Produto.setText(tDescricao_Produto);
    }

    public void set_tUnidade_Produto(String tUnidade_Produto) {
        this.tUnidade_Produto.setText(tUnidade_Produto);
    }

    public void set_tPreco_Produto(String tPreco_Produto) {
        this.tPreco_Produto.setText(tPreco_Produto);
    }

    public void set_tCor_Produto(String tCor_Produto) {
        this.tCor_Produto.setText(tCor_Produto);
    }

    public void set_tQuantidade_Produto(String tQuantidade_Produto) {
        this.tQuantidade_Produto.setText(tQuantidade_Produto);
    }

    public void set_tValor_Total_Produtos(String tValor_Total_Produtos) {
        this.tValor_Total_Produtos.setText(tValor_Total_Produtos);
    }

    /*
     * Carrega uma nova imabem em um JLabel
     * e também seta o tamanho da imagem de acordo com o label
     */
    public void carregaFoto() {
        jfc.setMultiSelectionEnabled(false);
        jfc.setDialogTitle("Selecione a Imagem do Projeto");
        jfc.setFileFilter(new FileNameExtensionFilter("JPG, GIF e PNG", "jpg", "gif", "png", "bmp"));
        this.Label_Desenho.setText("");

        // a linha abaixo mostra o nome do arquivo no campo file name
        //dentro da janela do jfc
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);// ha outras opcoes alem de files_only
        int resposta = jfc.showOpenDialog(this);
        //este if determina o que o programa faz ao se clicar no
        // botao de carregar ou cancelar dentro do jfc
        if (resposta == 0) {
            // se clica em carregar imagem faz isso...
            Image img = new ImageIcon(jfc.getSelectedFile().getAbsolutePath()).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
            Label_Desenho.setIcon(new ImageIcon(img));
            tDesenho.setText(jfc.getSelectedFile().getAbsolutePath());
        }
        if (resposta == 1) {
            //se a pessoa clica em cancelar nao faz nada...
        }
    }

    /*public void CopiaArquivos(File origem, File destino) throws FileNotFoundException, IOException {
    FileInputStream fisOrigem = new FileInputStream(origem);
    FileOutputStream fisDestino = new FileOutputStream(destino);
    FileChannel fcOrigem = fisOrigem.getChannel();
    FileChannel fcDestino = fisDestino.getChannel();
    fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);
    fisOrigem.close();
    fisDestino.close();
    }*/
    /*
     * Função que ativa e desativa os campos para edição
     */
    public void AtivaDesativa_Aba1(boolean verifica) {
        tReferencia.setEnabled(verifica);
        tDescricao.setEnabled(verifica);
        tDesenho.setEnabled(verifica);
        escolher_desenho.setEnabled(verifica);
    }

    public void AtivaDesativa_Aba2(boolean verifica) {
        tQuantidade_Produto.setEnabled(verifica);
        table.setEnabled(verifica);
        Botao_Buscar_Produto.setEnabled(verifica);
        Botao_Adicionar.setEnabled(verifica);
        Botao_Excluir.setEnabled(verifica);
    }

    /*
     *
     */
    public static javax.swing.table.DefaultTableModel modeloTabela() {
        return new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Código", "Referência", "Descrição", "Unidade", "Preço", "Cor", "Quantidade"
                });
    }

    /*
     * Função que limpa os campos para edição
     */
    public void Limpa(String l) {
        // Aba 1
        tReferencia.setText(l);
        tDescricao.setText(l);
        tDesenho.setText(l);
        Label_Desenho.setText("Imagem");

        // Aba 2
        tCodigo_Produto.setText(l);
        tReferencia_Produto.setText(l);
        tDescricao_Produto.setText(l);
        tUnidade_Produto.setText(l);
        tPreco_Produto.setText(l);
        tCor_Produto.setText(l);

        //table.getModel().set
        modeloDaTabela.setRowCount(0);
    }

    /*
     * Seta Valor Total dos Produtos
     */
    private void Atualiza_Valor_Total_Produtos() {
        float total = 0; // Valor Total dos Produtos Adicionados no Projeto

        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            total = total + (Float.parseFloat(table.getModel().getValueAt(i, 4).toString().trim()) * Float.parseFloat(table.getModel().getValueAt(i, 6).toString().trim()));
        }

        set_tValor_Total_Produtos(Float.toString(total));
    }

    /*
     *
     */
    public void Adicionar_Produtos() {

        // Verifico se o elemento que desejo inserir está na tabela
        // Se ele estiver flag recebe false para que o elemento não possa ser inserido
        for (int linha = 0; linha < table.getModel().getRowCount(); linha++) {
            if (Integer.parseInt(tCodigo_Produto.getText().toString().trim()) == Integer.parseInt(table.getValueAt(linha, 0).toString().trim())) {
                flag = false;
                linha = table.getModel().getRowCount();
                JOptionPane.showMessageDialog(null, " O produto que você deseja inserir já está contido no projeto \n Se deseja "
                        + " inserir o mesmo produto por favor exclua o mesmo e aumente a quantidade ");
            } else {
                flag = true;
            }
        }

        // Verifica se o elemento que desejo inserir está no vetor de produtos excluidos
        // Se ele estiver removo o elemento do vetor e flag = true para que o elemento possa ser inserido
        for (int i = 0; i < vetor_produtos_excluidos.size(); i++) {
            if (Integer.parseInt(tCodigo_Produto.getText().toString().trim()) == vetor_produtos_excluidos.elementAt(i)) {
                vetor_produtos_excluidos.removeElementAt(i);
                flag = true;
            }
        }

        if (flag == true) {
            DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) table.getModel();
            dtm.addRow(new Object[]{tCodigo_Produto.getText().toString().trim(),
                        tReferencia_Produto.getText().toString().trim(),
                        tDescricao_Produto.getText().toString().trim(),
                        tUnidade_Produto.getText().toString().trim(),
                        tPreco_Produto.getText().toString().trim(),
                        tCor_Produto.getText().toString().trim(),
                        tQuantidade_Produto.getText().toString().trim()});
        }

        /*for (int i = 0; i < vetor_produtos_excluidos.size(); i++) {
            JOptionPane.showMessageDialog(null, vetor_produtos_excluidos.elementAt(i) + "\n");
        }*/


        /*

        for (int i = 0; i < vetor_produtos_excluidos.size(); i++) {
        if (vetor_produtos_excluidos.elementAt(i) == Integer.parseInt(tCodigo_Produto.getText().toString().trim())) {
        vetor_produtos_excluidos.removeElementAt(i);
        }
        }

        if (table.getModel().getRowCount() <= 0) {
        flag = false;
        } else {
        for (int row = 0; row < table.getModel().getRowCount(); row++) {
        if (vetor_produtos_excluidos.isEmpty()) {
        if (Integer.parseInt(tCodigo_Produto.getText().toString()) == Integer.parseInt(table.getModel().getValueAt(row, 0).toString())) {
        JOptionPane.showMessageDialog(null, " O produto que você deseja inserir já está contido no projeto \n Se deseja "
        + " inserir o mesmo produto por favor exclua o mesmo e aumente a quantidade ");
        flag = true;
        } else {
        flag = false;
        }
        } else {
        for (int pos_vetor = 0; pos_vetor < vetor_produtos_excluidos.size(); pos_vetor++) {
        if (Integer.parseInt(tCodigo_Produto.getText().toString()) == vetor_produtos_excluidos.elementAt(pos_vetor)) {
        vetor_produtos_excluidos.removeElementAt(pos_vetor);
        JOptionPane.showMessageDialog(null, "else  O produto que você deseja inserir já está contido no projeto \n Se deseja "
        + " inserir o mesmo produto por favor exclua o mesmo e aumente a quantidade ");
        flag = true;
        pos_vetor = vetor_produtos_excluidos.size();
        }
        }
        }
        }



        }
         */
        /*
         * Verifica se a tabela de produtos está vazia ou nao e o produto que desejo inserir não esta na tabela
         * caso esteja vazia ou nao e o elemento não se encontre na tabela insero elementos na tabela de produtos
         */
        /*if (table.getModel().getRowCount() <= 0 || table.getModel().getRowCount() > 0 && flag == false) {
        DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) table.getModel();
        dtm.addRow(new Object[]{tCodigo_Produto.getText().toString().trim(),
        tReferencia_Produto.getText().toString().trim(),
        tDescricao_Produto.getText().toString().trim(),
        tUnidade_Produto.getText().toString().trim(),
        tPreco_Produto.getText().toString().trim(),
        tCor_Produto.getText().toString().trim(),
        tQuantidade_Produto.getText().toString().trim()});
        }*/
    }

    /*
     *
     */
    private void Excluir_Produtos(int linha) {
        vetor_produtos_excluidos.addElement(Integer.parseInt(table.getModel().getValueAt(linha, 0).toString().trim()));
        ((DefaultTableModel) table.getModel()).removeRow(linha);
    }

    /*
     * ######################### Manipula Registros #########################
     */
    public void novo() {
        Novo.setEnabled(false);
        Salvar.setEnabled(true);
        Cancelar.setEnabled(true);
        Pesquisar.setEnabled(false);
        escolher_desenho.setEnabled(true);
        AtivaDesativa_Aba1(true);
        AtivaDesativa_Aba2(true);
        aux = 1;


    }

    public void salvar() {
        int codigo_projeto;

        Global.conection.Update("INSERT INTO PROJETO ( REFERENCIA, DESCRICAO, DESENHO, VALOR_PROJETO ) VALUES (' "
                + tReferencia.getText().toString().trim() + "','"
                + tDescricao.getText().toString().trim() + "','"
                + tDesenho.getText().toString().trim() + "','"
                + Float.parseFloat(tValor_Total_Produtos.getText().toString()) + "' )");

        codigo_projeto = Global.ReturnKey("SELECT CODIGO FROM PROJETO WHERE REFERENCIA like '%" + tReferencia.getText().toString().trim() + "'");

        for (int linha = 0; linha < table.getModel().getRowCount(); linha++) {
            Global.conection.Update("INSERT INTO PRODUTO_PROJETO ( PRODUTO_CODIGO, PROJETO_CODIGO, QUANTIDADE, VALOR_UNITARIO ) VALUES (' "
                    + Integer.parseInt(table.getModel().getValueAt(linha, 0).toString().trim()) + "','"
                    + codigo_projeto + "','"
                    + Integer.parseInt(table.getModel().getValueAt(linha, 6).toString().trim()) + "','"
                    + Float.parseFloat(table.getModel().getValueAt(linha, 4).toString().trim()) + "' ) ");
        }


        Limpa("");
        AtivaDesativa_Aba1(
                false);
        AtivaDesativa_Aba2(
                false);
        Novo.setEnabled(true);
        Salvar.setEnabled(false);
        Cancelar.setEnabled(false);
        Pesquisar.setEnabled(true);


    }

    public void alterar() {

        Global.conection.Update("UPDATE PROJETO SET referencia = '"
                + tReferencia.getText().toString().trim() + "', descricao = '"
                + tDescricao.getText().trim() + "',desenho ='"
                + tDesenho.getText().trim() + "', valor_projeto = '"
		+ Float.parseFloat(tValor_Total_Produtos.getText().toString())
                + "' where codigo = '" + Integer.parseInt(tCodigo.getText().toString().trim()) + " ' ");

        Global.conection.Update("DELETE from PRODUTO_PROJETO where( PROJETO_CODIGO = ' " + Integer.parseInt(tCodigo.getText().toString().trim()) + "' )");

        for (int linha = 0; linha < table.getModel().getRowCount(); linha++) {
            Global.conection.Update("INSERT INTO PRODUTO_PROJETO ( PRODUTO_CODIGO, PROJETO_CODIGO, QUANTIDADE, VALOR_UNITARIO ) VALUES (' "
                    + Integer.parseInt(table.getModel().getValueAt(linha, 0).toString().trim()) + "','"
                    + Integer.parseInt(tCodigo.getText().toString().trim()) + "','"
                    + Integer.parseInt(table.getModel().getValueAt(linha, 6).toString().trim()) + "','"
                    + Float.parseFloat(table.getModel().getValueAt(linha, 4).toString().trim()) + "' ) ");
        }

        JOptionPane.showMessageDialog(null, " Alteração feita com sucesso...");
        Limpa("");
        AtivaDesativa_Aba1(false);
        AtivaDesativa_Aba2(false);
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
        AtivaDesativa_Aba1(false);
        AtivaDesativa_Aba2(false);
        Limpa("");
    }

    public void pesquisar() {
        dispose();
        new consultas.Projetos();

    }

    /* ######################### Manipula Registros ######################### */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Novo = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Pesquisar = new javax.swing.JButton();
        Salvar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        ;
        Aba1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tDesenho = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Label_Desenho = new javax.swing.JLabel();
        escolher_desenho = new javax.swing.JButton();
        tReferencia = new javax.swing.JTextField();
        tCodigo = new javax.swing.JTextField();
        tDescricao = new javax.swing.JTextField();
        Aba2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tCodigo_Produto = new javax.swing.JTextField();
        tQuantidade_Produto = new javax.swing.JTextField();
        tPreco_Produto = new javax.swing.JTextField();
        tValor_Total_Produtos = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        }
        ;
        Botao_Buscar_Produto = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tReferencia_Produto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tDescricao_Produto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tUnidade_Produto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tCor_Produto = new javax.swing.JTextField();
        Botao_Excluir = new javax.swing.JButton();
        Botao_Adicionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/NOVO.png"))); // NOI18N
        Novo.setText("Novo");

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CANCELAR.png"))); // NOI18N
        Cancelar.setText("Cancelar");

        Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/PESQUISAR.png"))); // NOI18N
        Pesquisar.setText("Pesquisar");

        Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/SALVAR.png"))); // NOI18N
        Salvar.setText("Salvar");

        jLabel5.setText("Código");

        tDesenho.setBackground(new java.awt.Color(204, 204, 204));
        tDesenho.setEditable(false);

        jLabel6.setText("Referência");

        jLabel7.setText("Descrição");

        jLabel8.setText("Desenho");

        Label_Desenho.setText("Imagem");

        escolher_desenho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/BUSCAR_IMAGEM.png"))); // NOI18N

        javax.swing.GroupLayout Aba1Layout = new javax.swing.GroupLayout(Aba1);
        Aba1.setLayout(Aba1Layout);
        Aba1Layout.setHorizontalGroup(
            Aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Aba1Layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(Aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Aba1Layout.createSequentialGroup()
                        .addGroup(Aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addGroup(Aba1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tReferencia, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(tDesenho, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)))
                    .addGroup(Aba1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(Label_Desenho)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                        .addComponent(escolher_desenho, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89))
        );

        Aba1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tDescricao, tReferencia});

        Aba1Layout.setVerticalGroup(
            Aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Aba1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(Aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tDesenho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Aba1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_Desenho)
                    .addComponent(escolher_desenho))
                .addContainerGap(214, Short.MAX_VALUE))
        );

        Aba1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tDescricao, tReferencia});

        jTabbedPane1.addTab("Projeto", Aba1);

        jLabel1.setText("Código");

        jLabel2.setText("Quantidade");

        jLabel3.setText("Preço");

        jLabel4.setText("Valor Total  Produtos");

        tCodigo_Produto.setBackground(new java.awt.Color(204, 204, 204));
        tCodigo_Produto.setEditable(false);

        tPreco_Produto.setBackground(new java.awt.Color(204, 204, 204));
        tPreco_Produto.setEditable(false);

        tValor_Total_Produtos.setBackground(new java.awt.Color(204, 204, 204));
        tValor_Total_Produtos.setEditable(false);

        jLabel9.setText("Relação de Produtos Neste Projeto");

        table.setModel(modeloDaTabela);
        table.setMaximumSize(new java.awt.Dimension(500, 200));
        jScrollPane1.setViewportView(table);

        Botao_Buscar_Produto.setText("Buscar Produto");

        jLabel10.setText("Referência");

        tReferencia_Produto.setBackground(new java.awt.Color(204, 204, 204));
        tReferencia_Produto.setEditable(false);

        jLabel11.setText("Descrição");

        tDescricao_Produto.setBackground(new java.awt.Color(204, 204, 204));
        tDescricao_Produto.setEditable(false);

        jLabel12.setText("Unidade");

        tUnidade_Produto.setBackground(new java.awt.Color(204, 204, 204));
        tUnidade_Produto.setEditable(false);

        jLabel13.setText("Cor");

        tCor_Produto.setBackground(new java.awt.Color(204, 204, 204));
        tCor_Produto.setEditable(false);

        Botao_Excluir.setText("Excluir");

        Botao_Adicionar.setText("Adicionar");

        javax.swing.GroupLayout Aba2Layout = new javax.swing.GroupLayout(Aba2);
        Aba2.setLayout(Aba2Layout);
        Aba2Layout.setHorizontalGroup(
            Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Aba2Layout.createSequentialGroup()
                .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Aba2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(Botao_Buscar_Produto))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Aba2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tCodigo_Produto, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tReferencia_Produto, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(tDescricao_Produto, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(tQuantidade_Produto, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Aba2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tUnidade_Produto, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Aba2Layout.createSequentialGroup()
                                .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tCor_Produto)
                                    .addComponent(tPreco_Produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(Aba2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                            .addGroup(Aba2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tValor_Total_Produtos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Aba2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Botao_Adicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Botao_Excluir)))
                .addContainerGap())
        );

        Aba2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tCodigo_Produto, tPreco_Produto, tQuantidade_Produto, tValor_Total_Produtos});

        Aba2Layout.setVerticalGroup(
            Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Aba2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Aba2Layout.createSequentialGroup()
                        .addComponent(Botao_Buscar_Produto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tCodigo_Produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(tReferencia_Produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(tDescricao_Produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Aba2Layout.createSequentialGroup()
                        .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(tUnidade_Produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tPreco_Produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(tCor_Produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tQuantidade_Produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tValor_Total_Produtos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Aba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Botao_Excluir)
                    .addComponent(Botao_Adicionar))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Produtos do Projeto", Aba2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Salvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Novo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cancelar, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(Pesquisar))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Cancelar, Novo, Pesquisar, Salvar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jTabbedPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(Novo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Salvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Cancelar)
                        .addGap(11, 11, 11)
                        .addComponent(Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Cancelar, Pesquisar});

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Aba1;
    private javax.swing.JPanel Aba2;
    private javax.swing.JButton Botao_Adicionar;
    private javax.swing.JButton Botao_Buscar_Produto;
    private javax.swing.JButton Botao_Excluir;
    public javax.swing.JButton Cancelar;
    private javax.swing.JLabel Label_Desenho;
    public javax.swing.JButton Novo;
    public javax.swing.JButton Pesquisar;
    public javax.swing.JButton Salvar;
    private javax.swing.JButton escolher_desenho;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField tCodigo;
    private javax.swing.JTextField tCodigo_Produto;
    private javax.swing.JTextField tCor_Produto;
    private javax.swing.JTextField tDescricao;
    private javax.swing.JTextField tDescricao_Produto;
    private javax.swing.JTextField tDesenho;
    private javax.swing.JTextField tPreco_Produto;
    private javax.swing.JTextField tQuantidade_Produto;
    private javax.swing.JTextField tReferencia;
    private javax.swing.JTextField tReferencia_Produto;
    private javax.swing.JTextField tUnidade_Produto;
    private javax.swing.JTextField tValor_Total_Produtos;
    public javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();
    private int aux = 0;
    private javax.swing.table.DefaultTableModel modeloDaTabela;
    private Vector<Integer> vetor_produtos_excluidos; // Vetor de produtos a serem excluidos do Banco de Dados
    boolean flag = true; // true insere elemento, false não insere elemento

    public void actionPerformed(ActionEvent e) {
        int linha = table.getSelectedRow();



        if (e.getSource().equals(Novo)) {
            novo();


        } else if (e.getSource().equals(Salvar)) {
            if (aux == 1) {
                salvar();
                JOptionPane.showMessageDialog(null, " Gravação realizada com Sucesso... ");


            } else if (aux == 2) {
                alterar();


            }
        } else if (e.getSource().equals(Cancelar)) {
            cancelar();


        } else if (e.getSource().equals(Pesquisar)) {
            pesquisar();


        } else if (e.getSource().equals(escolher_desenho)) {
            carregaFoto();


        } else if (e.getSource().equals(Botao_Buscar_Produto)) {
            Produtos_Projetos consulta = new Produtos_Projetos(this);


        } else if (e.getSource().equals(Botao_Adicionar)) {
            Adicionar_Produtos();
            Atualiza_Valor_Total_Produtos();


        } else if (e.getSource().equals(Botao_Excluir)) {
            Excluir_Produtos(linha);
            Atualiza_Valor_Total_Produtos();

        }
    }
}
