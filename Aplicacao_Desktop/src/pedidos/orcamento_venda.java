package pedidos;

import cadastros.Clientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import utilitarios.Global;

public class orcamento_venda extends javax.swing.JFrame implements ActionListener {

    public int get_Aux() {
	return aux;
    }

    public void set_Aux(int aux) {
	this.aux = aux;
    }

    public void settCodigo(String tCodigo) {
	this.tCodigo.setText(tCodigo);
    }

    public void settDescricao(String tDescricao) {
	this.tDescricao.setText(tDescricao);
    }

    public void settObra(String tObra) {
	this.tObra.setText(tObra);
    }

    public void setComboCliente(String ComboCliente) {
	this.ComboCliente.setSelectedItem(ComboCliente);
    }

    public void setComboVendedor(String ComboVendedor) {
	this.ComboVendedor.setSelectedItem(ComboVendedor);
    }

    public void settData(String tData) {
	this.tData.setText(tData);
    }

    public void setRadio_Sim(boolean Radio_Sim) {
	this.Radio_Sim.setSelected(Radio_Sim);
    }

    public void setRadio_Nao(boolean Radio_Nao) {
	this.Radio_Nao.setSelected(Radio_Nao);
    }

    public void settObservacao(String tObservacao) {
	this.tObservacao.setText(tObservacao);
    }

    public void setTotal_bruto(String total_bruto) {
	this.total_bruto.setText(total_bruto);
    }

    public void settDesconto(String tDesconto) {
	this.tDesconto.setText(tDesconto);
    }

    public void settPorcentagem_Desconto(String tPorcentagem_Desconto) {
	this.tPorcentagem_Desconto.setText(tPorcentagem_Desconto);
    }

    public void settValor_Final(String tValor_Final) {
	this.tValor_Final.setText(tValor_Final);
    }

    public orcamento_venda() {
	modeloDaTabela_projetos = modeloTabela_projetos();
	modeloDaTabela_produtos = modeloTabela_produtos();
	initComponents();
	set_janela();
	set_padrao();
	add_event_botoes();

    }

    /**
     *
     */
    public void set_janela() {
	setVisible(true);
	setTitle("Orçamentos / Vendas");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Só fecha esta janela
	setLocationRelativeTo(null);// Inicia a janela no centro da tela
    }

    /**
     *
     */
    public void set_padrao() {
	ComboCliente = Global.Prenche_ComboBox("select *from CLIENTE ", ComboCliente, "NOME");
	ComboVendedor = Global.Prenche_ComboBox("select *from FUNCIONARIO ", ComboVendedor, "NOME");
	ComboProjeto = Global.Prenche_ComboBox("select *from PROJETO", ComboProjeto, "DESCRICAO");
	ComboCor = Global.Prenche_ComboBox("select *from cor", ComboCor, "NOME");
	ComboVidro = Global.Prenche_ComboBox("select *from produto where descricao like 'VIDRO%' ", ComboVidro, "DESCRICAO");
	ComboProduto = Global.Prenche_ComboBox("select *from produto where descricao not like'%VIDRO%'", ComboProduto, "DESCRICAO");

	// Desabilitando os campos para não serem editados
	tCodigo.setEnabled(false);
	AtivaDesativa(false);
	// Desabilitando os botões por padrão quando a tela é aberta
	Salvar.setEnabled(false);
	Cancelar.setEnabled(false);

	// Setando Aba 2
	// Auto Redimenciona o tamanho da tabela
	table_projetos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	//  permiti somente a seleção de linhas ( unicas ) e nao multiplas.
	table_projetos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	// bloquear as colunas de uma tabela para que ela não possa mais ser mudada de posição
	table_projetos.getTableHeader().setReorderingAllowed(false);

	table_produtos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	//  permiti somente a seleção de linhas ( unicas ) e nao multiplas.
	table_produtos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	// bloquear as colunas de uma tabela para que ela não possa mais ser mudada de posição
	table_produtos.getTableHeader().setReorderingAllowed(false);

	tUnidade.setText(Global.ReturnSQL("select unidade from produto where '" + ComboProduto.getSelectedItem().toString() + "' = descricao", "unidade"));
	tValor_Unitario.setText(Global.ReturnSQL("select valor_unitario from produto where '" + ComboProduto.getSelectedItem().toString() + "' = descricao", "valor_unitario"));

	tDesconto.setText("0.00");
	tPorcentagem_Desconto.setText("0.00");
    }

    /**
     *
     */
    public void add_event_botoes() {
	Novo.addActionListener(this);
	Salvar.addActionListener(this);
	Cancelar.addActionListener(this);
	Pesquisar.addActionListener(this);
	Radio_Sim.addActionListener(this);
	Radio_Nao.addActionListener(this);
	Button_Adicionar_Projeto.addActionListener(this);
	Button_Excluir_Projeto.addActionListener(this);
	Button_Adicionar_Produto.addActionListener(this);
	Button_Excluir_Produto.addActionListener(this);
	ComboProduto.addActionListener(this);
    }

    /*
     * Método que Ativa e Desativa Campos para Edição
     */
    public void AtivaDesativa(boolean verifica) {
	tDescricao.setEnabled(verifica);
	tObra.setEnabled(verifica);
	ComboCliente.setEnabled(verifica);
	ComboVendedor.setEnabled(verifica);
	tData.setEnabled(verifica);
	tDesconto.setEnabled(verifica);
	tPorcentagem_Desconto.setEnabled(verifica);
	Radio_Sim.setEnabled(verifica);
	Radio_Nao.setEnabled(verifica);
	tObservacao.setEnabled(verifica);
	ComboProjeto.setEnabled(verifica);
	ComboCor.setEnabled(verifica);
	tQuantidade_Projeto.setEnabled(verifica);
	ComboVidro.setEnabled(verifica);
	tLargura.setEnabled(verifica);
	tAltura.setEnabled(verifica);
	Button_Adicionar_Projeto.setEnabled(verifica);
	Button_Adicionar_Produto.setEnabled(verifica);
	table_projetos.setEnabled(verifica);
	ComboProduto.setEnabled(verifica);
	tQuantidade.setEnabled(verifica);
	Button_Adicionar_Produto.setEnabled(verifica);
	Button_Excluir_Produto.setEnabled(verifica);
	table_produtos.setEnabled(verifica);
    }

    /*
     * Função que limpa os campos para edição
     */
    public void Limpa(String l) {
	tCodigo.setText(l);
	tDescricao.setText(l);
	tObra.setText(l);
	ComboCliente.setSelectedItem(l);
	ComboVendedor.setSelectedItem(l);
	tData.setText(l);
	total_bruto.setText(l);
	tPorcentagem_Desconto.setText(l);
	tValor_Final.setText(l);
	tObservacao.setText(l);
	ComboProjeto.setSelectedItem(l);
	ComboCor.setSelectedItem(l);
	tQuantidade_Projeto.setText(l);
	ComboVidro.setSelectedItem(l);
	tLargura.setText(l);
	tAltura.setText(l);
	ComboProduto.setSelectedItem(l);
	tQuantidade.setText(l);
	tUnidade.setText(l);
	tValor_Unitario.setText(l);
	table_projetos.setModel(modeloTabela_projetos());
	table_produtos.setModel(modeloTabela_projetos());
    }

    private String getDateTime() {
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	return dateFormat.format(date);
    }

    public javax.swing.table.DefaultTableModel modeloTabela_projetos() {
	return new javax.swing.table.DefaultTableModel(
		new Object[][]{},
		new String[]{
		    "Código", "Referência", "Descrição", "Cor", "Vidro", "Largura", "Altura", " Preço Vidro por M ", " Quantidade", " Valor Projeto "
		});
    }

    public javax.swing.table.DefaultTableModel modeloTabela_produtos() {
	return new javax.swing.table.DefaultTableModel(
		new Object[][]{},
		new String[]{
		    "Código", "Referência", "Descrição", " Quantidade ", "Valor Unitário", "Valor Total"
		});
    }

    /**
     * ########################### Manipula Registros ##########################
     */
    public void novo() {
	Novo.setEnabled(false);
	Salvar.setEnabled(true);
	Cancelar.setEnabled(true);
	Pesquisar.setEnabled(false);
	AtivaDesativa(true);
	set_Aux(1);
	tData.setText(getDateTime());
	Radio_Nao.setSelected(true);
	tQuantidade_Projeto.setText("1");
	tQuantidade.setText("1");
	soma_produtos = 0;
	soma_projetos = 0;
	soma_total = 0;
	total_bruto.setText("0.0");
	tDesconto.setText("0.0");
	tPorcentagem_Desconto.setText("0.0");
	total_bruto.setText("0.0");
	tValor_Final.setText("0.0");
    }

    public void salvar() {
	java.sql.Date data = null;
	try {
	    data = Global.formataData(tData.getText());
	} catch (Exception ex) {
	    Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
	}

	int cod_cliente, cod_vendedor;
	int codigo_orcamento, codigo_projeto;
	cod_cliente = Global.ReturnKey("select codigo from cliente where '" + ComboCliente.getSelectedItem().toString() + "' = nome");
	cod_vendedor = Global.ReturnKey("select codigo from funcionario where '" + ComboVendedor.getSelectedItem().toString() + "' = nome");

	Global.conection.Update("INSERT INTO ORCAMENTO_VENDA ( DESCRICAO, OBRA, CLIENTE_CODIGO, FUNCIONARIO_CODIGO, DATA, OBSERVACAO, CONCLUIDO, VALOR_BRUTO, VALOR_DESCONTO, PORC_DESCONTO, VALOR_FINAL ) VALUES ( '"
		+ tDescricao.getText().toString().trim() + "','"
		+ tObra.getText().toString().trim() + "','"
		+ cod_cliente + "','"
		+ cod_vendedor + "','"
		+ data + "','"
		+ tObservacao.getText().toString().trim() + "','"
		+ concluido + "','"
		+ Float.parseFloat(total_bruto.getText().toString()) + "','"
		+ Float.parseFloat(tDesconto.getText().toString()) + "','"
		+ Float.parseFloat(tPorcentagem_Desconto.getText().toString()) + "','"
		+ Float.parseFloat(tValor_Final.getText().toString()) + "' ) ");

	codigo_orcamento = Global.ReturnKey("select codigo from orcamento_venda where descricao like '%" + tDescricao.getText().toString().trim() + "'");

	/** 26/08/2010
	 * Varrer a table_projetos e gravando os dados no banco de dados
	 * / código / pegar o código do projeto na table_projetos.getValueAt(linha,0 ) pra inserir no banco
	 * / cor / pegar o nome da cor na table_projetos.getValueAt(linha,3 ) e procurar o Código da cor pra inserir no banco
	 * / quantidade / pegar a quantidade do projeto na table_projetos.getValueAt(linha,8 )
	 * / vidro / pegar a descrição do produto na table_projetos.getValueAt(linha,4 ) e procurar o código do vidro pra gravar no banco
	 * / largura / pegar o valor da largura do vidro na table_projetos.getValueAt(linha,5 ) pra gravar no banco
	 * / altura / pegar o valor da altura do vidro na table_projetos.getValueAt(linha,6 ) pra gravar no banco
	 */
	for (int linha = 0; linha < table_projetos.getRowCount(); linha++) {
	    Global.conection.Update("INSERT INTO PROJETO_ORCAMENTO_VENDA (PROJETO_CODIGO, ORCAMENTO_VENDA_CODIGO, COR, VIDRO, LARGURA, ALTURA,PRECO_VIDRO_M,QUANTIDADE,VALOR_PROJETO ) VALUES ( '"
		    + table_projetos.getValueAt(linha, 0) + "','"
		    + codigo_orcamento + "','"
		    + table_projetos.getValueAt(linha, 3).toString().trim() + "','"
		    + table_projetos.getValueAt(linha, 4).toString().trim() + "','"
		    + Float.parseFloat(table_projetos.getValueAt(linha, 5).toString()) + "','"
		    + Float.parseFloat(table_projetos.getValueAt(linha, 6).toString()) + "','"
		    + Float.parseFloat(table_projetos.getValueAt(linha, 7).toString()) + "','"
		    + Integer.parseInt(table_projetos.getValueAt(linha, 8).toString()) + "','"
		    + Float.parseFloat(table_projetos.getValueAt(linha, 9).toString()) + "')");
	}

	/**
	 * Varrer a table_produtos e gravando os dados no banco de dados
	 *
	 */
	for (int linha = 0; linha < table_produtos.getRowCount(); linha++) {
	    Global.conection.Update("INSERT INTO PRODUTO_ORCAMENTO_VENDA (ORCAMENTO_VENDA_CODIGO, PRODUTO_CODIGO, QUANTIDADE_PRODUTO, VALOR_UNITARIO, VALOR_TOTAL ) VALUES ( '"
		    + codigo_orcamento + "','"
		    + table_produtos.getValueAt(linha, 0).toString().trim() + "','"
		    + table_produtos.getValueAt(linha, 3).toString().trim() + "','"
		    + Float.parseFloat(table_produtos.getValueAt(linha, 4).toString()) + "','"
		    + Float.parseFloat(table_produtos.getValueAt(linha, 5).toString()) + "')");
	}

	Limpa("");
	AtivaDesativa(false);
	Novo.setEnabled(true);
	Salvar.setEnabled(false);
	Cancelar.setEnabled(false);
	Pesquisar.setEnabled(true);

    }

    public void alterar() {

	java.sql.Date data = null;
	try {
	    data = Global.formataData(tData.getText());
	} catch (Exception ex) {
	    Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
	}

	int cod_cliente = Global.ReturnKey("select codigo from cliente where '" + ComboCliente.getSelectedItem().toString() + "' = nome");
	int cod_vendedor = Global.ReturnKey("select codigo from funcionario where '" + ComboVendedor.getSelectedItem().toString() + "' = nome");

	Global.conection.Update("UPDATE ORCAMENTO_VENDA SET DESCRICAO = ' "
		+ tDescricao.getText().toString().trim() + "', OBRA = '"
		+ tObra.getText().toString().trim() + "', CLIENTE_CODIGO = '"
		+ cod_cliente + "', FUNCIONARIO_CODIGO = '"
		+ cod_vendedor + "', DATA = '"
		+ data + "', OBSERVACAO = '"
		+ tObservacao.getText().toString().trim() + "', CONCLUIDO = '"
		+ concluido + "', VALOR_BRUTO = '"
		+ Float.parseFloat(total_bruto.getText().toString()) + "', VALOR_DESCONTO = '"
		+ Float.parseFloat(tDesconto.getText().toString()) + "', PORC_DESCONTO = '"
		+ Float.parseFloat(tPorcentagem_Desconto.getText().toString()) + "', VALOR_FINAL = '"
		+ Float.parseFloat(tValor_Final.getText().toString())
		+ "'where ORCAMENTO_VENDA.CODIGO = '" + Integer.parseInt(tCodigo.getText().toString().trim()) + " ' ");

	Global.conection.Update("DELETE from PROJETO_ORCAMENTO_VENDA where( PROJETO_ORCAMENTO_VENDA.ORCAMENTO_VENDA_CODIGO = ' " + Integer.parseInt(tCodigo.getText().toString().trim()) + "' )");
	Global.conection.Update("DELETE from PRODUTO_ORCAMENTO_VENDA where( PRODUTO_ORCAMENTO_VENDA.ORCAMENTO_VENDA_CODIGO = ' " + Integer.parseInt(tCodigo.getText().toString().trim()) + "' )");

	for (int linha = 0; linha < table_projetos.getRowCount(); linha++) {
	    Global.conection.Update("INSERT INTO PROJETO_ORCAMENTO_VENDA (PROJETO_CODIGO, ORCAMENTO_VENDA_CODIGO, COR, VIDRO, LARGURA, ALTURA,PRECO_VIDRO_M,QUANTIDADE,VALOR_PROJETO ) VALUES ( '"
		    + table_projetos.getValueAt(linha, 0) + "','"
		    + Integer.parseInt(tCodigo.getText().toString().trim()) + "','"
		    + table_projetos.getValueAt(linha, 3).toString().trim() + "','"
		    + table_projetos.getValueAt(linha, 4).toString().trim() + "','"
		    + Float.parseFloat(table_projetos.getValueAt(linha, 5).toString()) + "','"
		    + Float.parseFloat(table_projetos.getValueAt(linha, 6).toString()) + "','"
		    + Float.parseFloat(table_projetos.getValueAt(linha, 7).toString()) + "','"
		    + Integer.parseInt(table_projetos.getValueAt(linha, 8).toString()) + "','"
		    + Float.parseFloat(table_projetos.getValueAt(linha, 9).toString()) + "')");
	}

	for (int linha = 0; linha < table_produtos.getRowCount(); linha++) {
	    Global.conection.Update("INSERT INTO PRODUTO_ORCAMENTO_VENDA (ORCAMENTO_VENDA_CODIGO, PRODUTO_CODIGO, QUANTIDADE_PRODUTO, VALOR_UNITARIO, VALOR_TOTAL ) VALUES ( '"
		    + Integer.parseInt(tCodigo.getText().toString().trim()) + "','"
		    + table_produtos.getValueAt(linha, 0).toString().trim() + "','"
		    + table_produtos.getValueAt(linha, 3).toString().trim() + "','"
		    + Float.parseFloat(table_produtos.getValueAt(linha, 4).toString()) + "','"
		    + Float.parseFloat(table_produtos.getValueAt(linha, 5).toString()) + "')");
	}

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
	new consulta_Orcamento_Venda();
    }

    public void relatorio() {
    }

    public void adiciona_projeto() {

	int cod_projeto = Global.ReturnKey("select codigo from projeto where '" + ComboProjeto.getSelectedItem().toString() + "' = descricao");
	String referencia = Global.ReturnSQL("select *from projeto where '" + cod_projeto + "' = codigo", "referencia");
	String descricao = ComboProjeto.getSelectedItem().toString().trim();
	String cor = ComboCor.getSelectedItem().toString().trim();
	String vidro = ComboVidro.getSelectedItem().toString().trim();
	String largura = tLargura.getText().toString().trim();
	String altura = tAltura.getText().toString().trim();
	String valor_projeto = Global.ReturnSQL("select *from projeto where '" + cod_projeto + "' = codigo", "valor_projeto");
	String preco_vidro = Global.ReturnSQL("select *from produto where '" + ComboVidro.getSelectedItem().toString() + "' = descricao", "valor_unitario");

	float total_projeto = (Float.parseFloat(tLargura.getText().toString()) * Float.parseFloat(tAltura.getText().toString())) * Float.parseFloat(preco_vidro);
	total_projeto = total_projeto + (Float.parseFloat(valor_projeto) * Float.parseFloat(tQuantidade_Projeto.getText().toString()));

	if (table_projetos.getModel().getRowCount() <= 0 || table_projetos.getModel().getRowCount() > 0 && flag == false) {
	    DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) table_projetos.getModel();
	    dtm.addRow(new Object[]{cod_projeto, referencia, descricao, cor, vidro, largura, altura, preco_vidro, tQuantidade_Projeto.getText().toString(), total_projeto});
	}

    }

    public void exclui_projeto(int linha) {
	((DefaultTableModel) table_projetos.getModel()).removeRow(linha);
    }

    public void adiciona_produto() {
	int cod_produto = Global.ReturnKey("select *from produto where '" + ComboProduto.getSelectedItem().toString() + "' = descricao");
	String referencia = Global.ReturnSQL("select *from produto where '" + cod_produto + "' = codigo", "referencia");
	String descricao = ComboProduto.getSelectedItem().toString().trim();
	String quantidade = tQuantidade.getText().toString().trim();
	float valor_unitario = Float.parseFloat(tValor_Unitario.getText().toString().trim());
	float valor_total = valor_unitario * Float.parseFloat(quantidade);

	if (table_produtos.getModel().getRowCount() <= 0 || table_produtos.getModel().getRowCount() > 0 && flag == false) {
	    DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) table_produtos.getModel();
	    dtm.addRow(new Object[]{cod_produto, referencia, descricao, quantidade, valor_unitario, valor_total});
	}
    }

    public void exclui_produto(int linha) {
	((DefaultTableModel) table_produtos.getModel()).removeRow(linha);
    }

    public void atualiza_preco() {
	try {
	    soma_produtos = 0;
	    soma_projetos = 0;
	    soma_total = 0;
	    valor_final = 0;
	    desconto = 0;

	    for (int linha = 0; linha < table_produtos.getRowCount(); linha++) {
		soma_produtos = soma_produtos + (Float.parseFloat(table_produtos.getValueAt(linha, 5).toString()));
	    }

	    for (int linha = 0; linha < table_projetos.getRowCount(); linha++) {
		soma_projetos = soma_projetos + Float.parseFloat(table_projetos.getValueAt(linha, 9).toString());
	    }

	    soma_total = soma_produtos + soma_projetos;
	    total_bruto.setText("" + Global.arredondaValor(soma_total));

	    if (click_desconto == true) {
		desconto = Float.parseFloat(total_bruto.getText().toString()) - Float.parseFloat(tDesconto.getText().toString());
		desconto = 100 - ((desconto * 100) / Float.parseFloat(total_bruto.getText().toString()));
		tPorcentagem_Desconto.setText("" + Global.arredondaValor(desconto));
	    } else {
		desconto = (Float.parseFloat(total_bruto.getText().toString()) * Float.parseFloat(tPorcentagem_Desconto.getText().toString()) / 100);
		tDesconto.setText("" + Global.arredondaValor(desconto));
	    }

	    valor_final = Float.parseFloat(total_bruto.getText().toString()) - Float.parseFloat(tDesconto.getText().toString());
	    tValor_Final.setText("" + Global.arredondaValor(valor_final));

	    click_desconto = false;
	} catch (Exception e) {
	}
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        tData = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ComboCliente = new javax.swing.JComboBox();
        tDescricao = new javax.swing.JTextField();
        ComboVendedor = new javax.swing.JComboBox();
        tObra = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        total_bruto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tDesconto = new javax.swing.JTextField();
        tValor_Final = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tPorcentagem_Desconto = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        Radio_Sim = new javax.swing.JRadioButton();
        Radio_Nao = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tObservacao = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        tCodigo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_projetos = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        }
        ;
        jPanel5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        tQuantidade_Projeto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ComboProjeto = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        ComboCor = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        tAltura = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        ComboVidro = new javax.swing.JComboBox();
        tLargura = new javax.swing.JTextField();
        Button_Adicionar_Projeto = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        Button_Excluir_Projeto = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        tValor_Unitario = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        tQuantidade = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        tUnidade = new javax.swing.JTextField();
        ComboProduto = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        Button_Adicionar_Produto = new javax.swing.JButton();
        Button_Excluir_Produto = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_produtos = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        }
        ;
        jPanel4 = new javax.swing.JPanel();
        Salvar = new javax.swing.JButton();
        Pesquisar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        Novo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel8.setText("R$ Desconto");

        jLabel7.setText("R$ Total Bruto");

        jLabel5.setText("Vendedor");

        jLabel6.setText("Data");

        jLabel2.setText("Obra");

        jLabel4.setText("Cliente");

        total_bruto.setBackground(new java.awt.Color(204, 204, 204));
        total_bruto.setEditable(false);

        jLabel9.setText("R$ Valor Final");

        tDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tDescontoKeyReleased(evt);
            }
        });

        tValor_Final.setBackground(new java.awt.Color(204, 204, 204));
        tValor_Final.setEditable(false);

        jLabel1.setText("Descrição");

        jLabel3.setText("Desconto %");

        tPorcentagem_Desconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tPorcentagem_DescontoKeyReleased(evt);
            }
        });

        jLabel15.setText("Concluído");

        Radio_Sim.setText("Sim");

        Radio_Nao.setText("Não");

        jLabel18.setText("Observação");

        tObservacao.setColumns(20);
        tObservacao.setRows(5);
        jScrollPane1.setViewportView(tObservacao);

        jLabel23.setText("Código");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Radio_Nao)
                                    .addComponent(Radio_Sim))
                                .addGap(130, 130, 130))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel18)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tData, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tObra)
                                .addComponent(ComboCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ComboVendedor, 0, 329, Short.MAX_VALUE)
                                .addComponent(tDescricao))
                            .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tPorcentagem_Desconto)
                            .addComponent(tValor_Final, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tDesconto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(total_bruto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(57, 57, 57))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tCodigo, tData});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(ComboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(ComboVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(total_bruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tPorcentagem_Desconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tValor_Final, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addGap(26, 26, 26)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Radio_Sim)
                        .addGap(18, 18, 18)
                        .addComponent(Radio_Nao))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ComboCliente, ComboVendedor, tCodigo, tData, tDescricao, tObra});

        jTabbedPane1.addTab("Principal", jPanel1);

        jLabel21.setText("Relação de Projetos");

        table_projetos.setModel(modeloDaTabela_projetos);
        table_projetos.setMaximumSize(new java.awt.Dimension(500, 200));
        jScrollPane2.setViewportView(table_projetos);

        jLabel24.setText("Quantidade");

        jLabel10.setText("Cor");

        jLabel11.setText("Descrição");

        jLabel20.setText("Projeto:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tQuantidade_Projeto, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboCor, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(ComboProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(ComboCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(tQuantidade_Projeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel13.setText("Altura");

        jLabel14.setText("Referência");

        jLabel19.setText("Vidro:");

        Button_Adicionar_Projeto.setText("Adicionar");

        jLabel12.setText("Largura");

        Button_Excluir_Projeto.setText("Excluir");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel19))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(tLargura, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ComboVidro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(Button_Adicionar_Projeto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Button_Excluir_Projeto)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Button_Adicionar_Projeto, Button_Excluir_Projeto});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tAltura, tLargura});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(ComboVidro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tLargura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(tAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Button_Excluir_Projeto)
                    .addComponent(Button_Adicionar_Projeto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Projetos", jPanel2);

        jLabel30.setText("Produto:");

        tValor_Unitario.setBackground(new java.awt.Color(204, 204, 204));
        tValor_Unitario.setEditable(false);

        jLabel33.setText("Valor Unitario");

        jLabel31.setText("Quantidade");

        jLabel32.setText("Unidade");

        tUnidade.setBackground(new java.awt.Color(204, 204, 204));
        tUnidade.setEditable(false);

        jLabel22.setText("Relação de Produtos do Orçamento / Pedido");

        Button_Adicionar_Produto.setText("Adicionar");

        Button_Excluir_Produto.setText("Excluir");

        table_produtos.setModel(modeloDaTabela_produtos);
        table_produtos.setMaximumSize(new java.awt.Dimension(500, 200));
        jScrollPane3.setViewportView(table_produtos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel30)))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ComboProduto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tValor_Unitario, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_Adicionar_Produto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_Excluir_Produto)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tQuantidade, tUnidade, tValor_Unitario});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Button_Adicionar_Produto, Button_Excluir_Produto});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(tQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33)
                            .addComponent(tValor_Unitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)
                            .addComponent(Button_Adicionar_Produto)
                            .addComponent(Button_Excluir_Produto))
                        .addGap(40, 40, 40)
                        .addComponent(jLabel22)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Produtos", jPanel3);

        Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/SALVAR.png"))); // NOI18N
        Salvar.setText("Salvar");

        Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/PESQUISAR.png"))); // NOI18N
        Pesquisar.setText("Pesquisar");

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CANCELAR.png"))); // NOI18N
        Cancelar.setText("Cancelar");

        Novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/NOVO.png"))); // NOI18N
        Novo.setText("Novo");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Salvar, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(Novo, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(Cancelar)
                    .addComponent(Pesquisar))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Cancelar, Novo, Pesquisar, Salvar});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
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

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Cancelar, Novo, Pesquisar, Salvar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tDescontoKeyReleased
	click_desconto = true;
	try {
	    atualiza_preco();
	} catch (Exception ex) {
	    Logger.getLogger(orcamento_venda.class.getName()).log(Level.SEVERE, null, ex);
	}
    }//GEN-LAST:event_tDescontoKeyReleased

    private void tPorcentagem_DescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tPorcentagem_DescontoKeyReleased
	click_desconto = false;
	try {
	    atualiza_preco();
	} catch (Exception ex) {
	    Logger.getLogger(orcamento_venda.class.getName()).log(Level.SEVERE, null, ex);
	}

    }//GEN-LAST:event_tPorcentagem_DescontoKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Adicionar_Produto;
    private javax.swing.JButton Button_Adicionar_Projeto;
    private javax.swing.JButton Button_Excluir_Produto;
    private javax.swing.JButton Button_Excluir_Projeto;
    public javax.swing.JButton Cancelar;
    private javax.swing.JComboBox ComboCliente;
    private javax.swing.JComboBox ComboCor;
    private javax.swing.JComboBox ComboProduto;
    private javax.swing.JComboBox ComboProjeto;
    private javax.swing.JComboBox ComboVendedor;
    private javax.swing.JComboBox ComboVidro;
    public javax.swing.JButton Novo;
    public javax.swing.JButton Pesquisar;
    private javax.swing.JRadioButton Radio_Nao;
    private javax.swing.JRadioButton Radio_Sim;
    public javax.swing.JButton Salvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField tAltura;
    private javax.swing.JTextField tCodigo;
    private javax.swing.JTextField tData;
    private javax.swing.JTextField tDesconto;
    private javax.swing.JTextField tDescricao;
    private javax.swing.JTextField tLargura;
    private javax.swing.JTextField tObra;
    private javax.swing.JTextArea tObservacao;
    private javax.swing.JTextField tPorcentagem_Desconto;
    private javax.swing.JTextField tQuantidade;
    private javax.swing.JTextField tQuantidade_Projeto;
    private javax.swing.JTextField tUnidade;
    private javax.swing.JTextField tValor_Final;
    private javax.swing.JTextField tValor_Unitario;
    public javax.swing.JTable table_produtos;
    public javax.swing.JTable table_projetos;
    private javax.swing.JTextField total_bruto;
    // End of variables declaration//GEN-END:variables
    private int aux = 0;
    private javax.swing.table.DefaultTableModel modeloDaTabela_projetos;
    private javax.swing.table.DefaultTableModel modeloDaTabela_produtos;
    private Vector<Integer> vetor_projetos_excluidos; // Vetor de produtos a serem excluidos do Banco de Dados
    private boolean flag;
    private float soma_produtos = 0;
    private float soma_projetos = 0;
    private float soma_total = 0;
    private float valor_final = 0;
    private float desconto = 0;
    private boolean click_desconto = false;
    private String concluido = "NAO";

    public void actionPerformed(ActionEvent e) {
	int linha_table_projeto = table_projetos.getSelectedRow();
	int linha_table_produto = table_produtos.getSelectedRow();

	if (e.getSource().equals(Novo)) {
	    Limpa("");
	    novo();
	} else if (e.getSource().equals(Salvar)) {
	    if (aux == 1) {
		salvar();
		JOptionPane.showMessageDialog(null, " Gravação realizada com Sucesso... ");
	    } else if (aux == 2) {
		alterar();
	    }
	} else if (e.getSource().equals(Cancelar)) {
	    Limpa("");
	    cancelar();
	} else if (e.getSource().equals(Pesquisar)) {
	    pesquisar();
	} else if (e.getSource().equals(Button_Adicionar_Projeto)) {
	    adiciona_projeto();
	    atualiza_preco();
	} else if (e.getSource().equals(Button_Excluir_Projeto)) {
	    exclui_projeto(linha_table_projeto);
	    atualiza_preco();
	} else if (e.getSource().equals(Button_Adicionar_Produto)) {
	    adiciona_produto();
	    atualiza_preco();
	} else if (e.getSource().equals(Button_Excluir_Produto)) {
	    exclui_produto(linha_table_produto);
	    atualiza_preco();
	}

	if (e.getSource().equals(Radio_Sim)) {
	    Radio_Sim.setSelected(true);
	    Radio_Nao.setSelected(false);
	    concluido = "SIM";
	} else if (e.getSource().equals(Radio_Nao)) {
	    Radio_Nao.setSelected(true);
	    Radio_Sim.setSelected(false);
	    concluido = "NAO";
	}

	if (e.getSource().equals(ComboProduto)) {
	    tUnidade.setText(Global.ReturnSQL("select unidade from produto where '" + ComboProduto.getSelectedItem().toString() + "' = descricao", "unidade"));
	    tValor_Unitario.setText(Global.ReturnSQL("select valor_unitario from produto where '" + ComboProduto.getSelectedItem().toString() + "' = descricao", "valor_unitario"));
	}

    }
}
