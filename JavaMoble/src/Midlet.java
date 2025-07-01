
import Utilitarios.RegistroProdutos;
import Utilitarios.Registro;
import Utilitarios.RegistroOrcamento;
import Utilitarios.RegistroProjetos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import java.util.Vector;

public class Midlet extends MIDlet implements CommandListener {

    /* ###################################################################### */
    /*                        Declaração de Variáveis                         */
    /* ###################################################################### */
    // Cadastro de Clientes - formCadastroClientes
    private TextField cadastro_clienteBairro;
    private TextField cadastro_clienteCEP;
    private TextField cadastro_clienteCPF;
    private TextField cadastro_clienteCelular;
    private TextField cadastro_clienteCidade;
    private TextField cadastro_clienteContato;
    private DateField cadastro_clienteData;
    private TextField cadastro_clienteEmail;
    private TextField cadastro_clienteEndereco;
    private TextField cadastro_clienteEstado;
    private TextField cadastro_clienteFax;
    private TextField cadastro_clienteFone;
    private TextField cadastro_clienteNome;
    private TextField cadastro_clienteObservacao;
    private TextField cadastro_clienteRG;
    private ChoiceGroup choiceGroupSexo;
    // Dados do Orçamento - formOrcamentoDados
    private TextField orcamento_dadosDescricao;
    private ChoiceGroup orcamento_dadosConcluido;
    private DateField orcamento_dadosData;
    private TextField orcamento_dadosObra;
    private TextField orcamento_dadosObservacao;
    //
    private TextField vidroConsultaNome;
    // Projetos do Orçamento -
    private TextField orcamento_projetosNome;
    private TextField orcamento_projetosQuantidade;
    // Valores do Orçamento - formValores
    private TextField valores_Desconto;
    private TextField valores_PorcDesconto;
    private StringItem valores_Bruto;
    private StringItem valores_Final;
    // Form's
    private Form formCadastroCliente;
    private Form formConsultaCliente;
    private Form formConsultaVendedor;
    private Form formConsultaProjeto;
    private Form formConsultaVidro;
    private Form formOrcamentoDados;
    private Form formOrcamentoProjetos;
    private Form formDadosDoVidro;
    private Form formOrcamentoProdutos;
    private Form formOrcamentoValores;
    // Consulta Produtos
    private Form formConsultaProduto;
    private TextField produtoConsultaNome;
    // List's
    private List listPrincipal;
    private List listConsultaClienteResultado;
    private List listConsultaVendedorResultado;
    private List listConsultaProjetos; // Consulta projetos para incluir no orçamento
    private List listConsultaVidros;
    private List listProjetosCadastrados; // Mostra projetos cadastrados no orçamento
    private List listOrcamento; // Formulário que direciona orçamentos e vendas
    private List listConsultaProdutos;
    // Command's
    private Command commandSair;
    private Command commandProximo;
    private Command commandCancelar;
    private Command commandOK;
    private Command commandAdicionar;
    private Command commandCalcular;
    private Command commandListarProjetos;
    private Command commandListarProdutos;
    private Command commandEscolheCliente;
    private Command commandEscolheVendedor;
    private TextField clienteConsultaNome;
    private TextField vendedorConsultaNome;
    private TextField projetoConsultaNome;
    private TextField orcamento_projetosVidroLargura;
    private TextField orcamento_projetosVidroAltura;
    // Variáveis da Aplicação
    private Vector clientes = new Vector();
    private Vector produtos = new Vector();
    private Vector vendedores = new Vector();
    private Vector projetos = new Vector();
    private Vector vidros = new Vector();
    private Vector projetosOrcamento = new Vector(); // Armazena projetos cadastrados no orçamento corrente
    private boolean ret;
    private TextField orcamento_produtosQuantidade;
    private List listProdutosCadastrados;
    private Vector produtosOrcamento = new Vector();
    private Display display; // Referencia o objeto Display para essa MIDlet
    private Alert alertMensagem;
    private HttpConnection http;
    private InputStream inputStream;
    private String str = ""; // Utilizada para armazenar resultado da consulta ao Banco de Dados
    private RegistroOrcamento regOrcamento = new RegistroOrcamento();
    private float somaTotalProjetos = 0;
    private float somaTotalProdutos = 0;
    private float totalbrutoOrcamentoVenda = 0;
    private float totalfinalOrcamentoVenda = 0;
    //private String dominio = "localhost";
    private String dominio = "glasswindow.kinghost.net";
    //private String porta = "8084";
    private String porta = "80";

    /* ###################################################################### */
    /*                              MIDLET                                    */
    /* ###################################################################### */
    public Midlet() {
	display = Display.getDisplay(this);

	listPrincipal = null;
	formCadastroCliente = null;
	formConsultaCliente = null;
	formConsultaProjeto = null;
	listOrcamento = null;
	formOrcamentoDados = null;
	formOrcamentoProjetos = null;
	formOrcamentoProdutos = null;
	formOrcamentoValores = null;
	listProjetosCadastrados = null;
	listProdutosCadastrados = null;
	listConsultaClienteResultado = null;
	listConsultaVendedorResultado = null;
	listOrcamento = null;

	commandSair = new Command("Sair", Command.EXIT, 0);
	commandProximo = new Command("Próximo", Command.OK, 1);
	commandCancelar = new Command("Cancelar", Command.CANCEL, 0);
	commandOK = new Command("OK", Command.OK, 1);
	commandAdicionar = new Command("Adicionar", Command.OK, 1);
	commandListarProjetos = new Command("Listar Projetos", Command.OK, 2);
	commandListarProdutos = new Command("Listar Produtos", Command.OK, 2);
	commandEscolheCliente = new Command("Cliente", Command.OK, 2);
	commandEscolheVendedor = new Command("Vendedor", Command.OK, 3);
	commandCalcular = new Command("Calcular", Command.OK, 2);

    }

    public void startApp() {
	criaListPrincipal();
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    /* ###################################################################### */
    /*    Métodos que direcionam todas as operações disponíveis no sistema    */
    /* ###################################################################### */
    private void criaListPrincipal() {
	if (listPrincipal == null) {
	    listPrincipal = new List("Glass Window", List.IMPLICIT);
	    listPrincipal.append("Cadastra Cliente", null);
	    listPrincipal.append("Consulta Cliente", null);
	    listPrincipal.append("Orçamento / Venda", null);
	    listPrincipal.addCommand(commandSair);
	    listPrincipal.addCommand(commandProximo);
	    listPrincipal.setCommandListener(this);
	}
	display.setCurrent(listPrincipal);
    }

    private void criaFormCadastroCliente() {

	if (formCadastroCliente == null) {
	    // Inicializando Variáveis
	    cadastro_clienteNome = new TextField("Nome", null, 80, TextField.ANY);
	    choiceGroupSexo = new ChoiceGroup("Sexo", ChoiceGroup.EXCLUSIVE);
	    cadastro_clienteRG = new TextField("RG", null, 9, TextField.NUMERIC);
	    cadastro_clienteCPF = new TextField("CPF", null, 11, TextField.NUMERIC);
	    cadastro_clienteEndereco = new TextField("Endereço", null, 80, TextField.ANY);
	    cadastro_clienteBairro = new TextField("Bairro", null, 80, TextField.ANY);
	    cadastro_clienteCidade = new TextField("Cidade", "Dourados", 80, TextField.ANY);
	    cadastro_clienteEstado = new TextField("Estado", "MS", 80, TextField.ANY);
	    cadastro_clienteCEP = new TextField("CEP", null, 8, TextField.NUMERIC);
	    cadastro_clienteFone = new TextField("Fone", null, 8, TextField.NUMERIC);
	    cadastro_clienteFax = new TextField("Fax", null, 8, TextField.NUMERIC);
	    cadastro_clienteCelular = new TextField("Celular", null, 8, TextField.NUMERIC);
	    cadastro_clienteContato = new TextField("Contato", null, 80, TextField.ANY);
	    cadastro_clienteEmail = new TextField("E-mail", null, 80, TextField.EMAILADDR);
	    cadastro_clienteData = new DateField("Data", DateField.DATE);
	    cadastro_clienteObservacao = new TextField("Observacao", null, 80, TextField.ANY);

	    choiceGroupSexo.append("M", null);
	    choiceGroupSexo.append("F", null);

	    cadastro_clienteData.setDate(new Date()); // Pegando a data atual do sistema

	    // Adicionando componentes ao formCadastroCliente
	    formCadastroCliente = new Form("Cadastro Cliente");
	    formCadastroCliente.append(cadastro_clienteNome);
	    formCadastroCliente.append(choiceGroupSexo);
	    formCadastroCliente.append(cadastro_clienteRG);
	    formCadastroCliente.append(cadastro_clienteCPF);
	    formCadastroCliente.append(cadastro_clienteEndereco);
	    formCadastroCliente.append(cadastro_clienteBairro);
	    formCadastroCliente.append(cadastro_clienteCidade);
	    formCadastroCliente.append(cadastro_clienteEstado);
	    formCadastroCliente.append(cadastro_clienteCEP);
	    formCadastroCliente.append(cadastro_clienteFone);
	    formCadastroCliente.append(cadastro_clienteFax);
	    formCadastroCliente.append(cadastro_clienteCelular);
	    formCadastroCliente.append(cadastro_clienteContato);
	    formCadastroCliente.append(cadastro_clienteEmail);
	    formCadastroCliente.append(cadastro_clienteData);
	    formCadastroCliente.append(cadastro_clienteObservacao);
	    formCadastroCliente.addCommand(commandCancelar);
	    formCadastroCliente.addCommand(commandOK);
	    formCadastroCliente.setCommandListener(this);
	}
	display.setCurrent(formCadastroCliente);
    }

    private void criaFormConsultaCLiente() {

	if (formConsultaCliente == null) {
	    clienteConsultaNome = new TextField("Nome", null, 80, TextField.ANY);

	    commandCancelar = new Command("Cancelar", Command.CANCEL, 0);
	    commandOK = new Command("OK", Command.OK, 1);

	    formConsultaCliente = new Form("Consulta Cliente");
	    formConsultaCliente.append(clienteConsultaNome);
	    formConsultaCliente.addCommand(commandCancelar);
	    formConsultaCliente.addCommand(commandOK);
	    formConsultaCliente.setCommandListener(this);
	}
	display.setCurrent(formConsultaCliente);
    }

    private void criaFormConsultaVendedor() {

	if (formConsultaVendedor == null) {
	    vendedorConsultaNome = new TextField("Nome", null, 80, TextField.ANY);

	    commandCancelar = new Command("Cancelar", Command.CANCEL, 0);
	    commandOK = new Command("OK", Command.OK, 1);

	    formConsultaVendedor = new Form("Consulta Vendedor");
	    formConsultaVendedor.append(vendedorConsultaNome);
	    formConsultaVendedor.addCommand(commandCancelar);
	    formConsultaVendedor.addCommand(commandOK);
	    formConsultaVendedor.setCommandListener(this);
	}
	display.setCurrent(formConsultaVendedor);
    }

    private void criaListOrcamento() {

	if (listOrcamento == null) {
	    listOrcamento = new List("Orçamento / Venda", List.IMPLICIT);
	    listOrcamento.append("Dados", null);
	    listOrcamento.append("Projetos", null);
	    listOrcamento.append("Produtos", null);
	    listOrcamento.append("Valores", null);
	    listOrcamento.addCommand(commandCancelar);
	    listOrcamento.addCommand(commandProximo);
	    listOrcamento.setCommandListener(this);
	}
	display.setCurrent(listOrcamento);
    }

    /* ###################################################################### */
    /*              Métodos que realizam o cadastro de Clientes               */
    /* ###################################################################### */
    private String geraXMLCadastroCliente() {
	String xmlCadastroCliente = "";

	xmlCadastroCliente = xmlCadastroCliente + "<cliente>";
	xmlCadastroCliente = xmlCadastroCliente + "<nome>" + cadastro_clienteNome.getString() + "</nome>";
	xmlCadastroCliente = xmlCadastroCliente + "<sexo>" + choiceGroupSexo.getString(choiceGroupSexo.getSelectedIndex()) + "</sexo>";
	xmlCadastroCliente = xmlCadastroCliente + "<rg>" + cadastro_clienteRG.getString() + "</rg>";
	xmlCadastroCliente = xmlCadastroCliente + "<cpf>" + cadastro_clienteCPF.getString() + "</cpf>";
	xmlCadastroCliente = xmlCadastroCliente + "<endereco>" + cadastro_clienteEndereco.getString() + "</endereco>";
	xmlCadastroCliente = xmlCadastroCliente + "<bairro>" + cadastro_clienteBairro.getString() + "</bairro>";
	xmlCadastroCliente = xmlCadastroCliente + "<cidade>" + cadastro_clienteCidade.getString() + "</cidade>";
	xmlCadastroCliente = xmlCadastroCliente + "<estado>" + cadastro_clienteEstado.getString() + "</estado>";
	xmlCadastroCliente = xmlCadastroCliente + "<cep>" + cadastro_clienteCEP.getString() + "</cep>";
	xmlCadastroCliente = xmlCadastroCliente + "<Fone>" + cadastro_clienteFone.getString() + "</Fone>";
	xmlCadastroCliente = xmlCadastroCliente + "<fax>" + cadastro_clienteFax.getString() + "</fax>";
	xmlCadastroCliente = xmlCadastroCliente + "<celular>" + cadastro_clienteCelular.getString() + "</celular>";
	xmlCadastroCliente = xmlCadastroCliente + "<contato>" + cadastro_clienteContato.getString() + "</contato>";
	xmlCadastroCliente = xmlCadastroCliente + "<email>" + cadastro_clienteEmail.getString() + "</email>";
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(cadastro_clienteData.getDate());
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH) + 1;
	int date = calendar.get(Calendar.DATE);
	xmlCadastroCliente = xmlCadastroCliente + "<data>" + date + "/" + month + "/" + year + "</data>";
	xmlCadastroCliente = xmlCadastroCliente + "<observacao>" + cadastro_clienteObservacao.getString() + "</observacao>";
	xmlCadastroCliente = xmlCadastroCliente + "</cliente>";

	return xmlCadastroCliente;
    }

    private void CadastraCliente(String xml) throws IOException {
	try {
	    http = (HttpConnection) Connector.open("http://" + dominio + ":" + porta + "/Servlet/CadastraCliente"); // Abre a conexão
	    http.setRequestMethod(HttpConnection.POST); // Envia método de pedido ( Neste caso é pelo método GET ).
	    DataOutputStream saida;
	    saida = (DataOutputStream) http.openDataOutputStream();
	    saida.write(xml.getBytes());

	    if (http.getResponseCode() == HttpConnection.HTTP_OK) {
		mostraMensagens("OK", "Cadastro realizado com sucesso...", AlertType.CONFIRMATION, listPrincipal);
	    } else {
		mostraMensagens("Erro", "Não foi possível realizar o cadastro", AlertType.ERROR, listPrincipal);
	    }
	    //inputStream = http.openInputStream(); // Resposta do servidor
	    //ret = processServerResponse(http, inputStream); // Processa resposta do servidor
	} finally {
	    if (inputStream != null) {
		inputStream.close();
	    }
	    if (http != null) {
		http.close();
	    }
	}

	if (ret == false) {
	    // Mostra mensagem de erro
	}
    }

    private void limpaCadastroCliente() {
	String limpa = "";
	cadastro_clienteNome.setString(limpa);
	cadastro_clienteRG.setString(limpa);
	cadastro_clienteCPF.setString(limpa);
	cadastro_clienteEndereco.setString(limpa);
	cadastro_clienteBairro.setString(limpa);
	cadastro_clienteCidade.setString(limpa); // Depois trocar por alguma coisa que deixe escolher a cidade
	cadastro_clienteEstado.setString(limpa); // Depois trocar por alguma coisa que deixe escolher o estado
	cadastro_clienteCEP.setString(limpa);
	cadastro_clienteFone.setString(limpa);
	cadastro_clienteFax.setString(limpa);
	cadastro_clienteCelular.setString(limpa);
	cadastro_clienteContato.setString(limpa);
	cadastro_clienteEmail.setString(limpa);
	cadastro_clienteObservacao.setString(limpa);
    }

    /* ###################################################################### */
    /*    Métodos que realizam a consulta do Cliente no Banco de Dados        */
    /* ###################################################################### */
    public void ConsultaCliente(String url) throws IOException {
	try {
	    http = (HttpConnection) Connector.open(url); // Abre a conexão
	    http.setRequestMethod(HttpConnection.GET); // Envia método de pedido ( Neste caso é pelo método GET ).
	    inputStream = http.openInputStream(); // Resposta do servidor
	    ret = processServerResponseCliente(http, inputStream); // Processa resposta do servidor
	} finally {
	    if (inputStream != null) {
		inputStream.close();
	    }
	    if (http != null) {
		http.close();
	    }
	}

	if (ret == false) {
	    // Mostra mensagem de erro
	}
    }

    private boolean processServerResponseCliente(HttpConnection http, InputStream inputStream) throws IOException {

	if (http.getResponseCode() == HttpConnection.HTTP_OK) {
	    int length = (int) http.getLength();

	    if (length != -1) {
		byte servletData[] = new byte[length];
		inputStream.read(servletData);
		str = new String(servletData);
	    } else {
		ByteArrayOutputStream bStrm = new ByteArrayOutputStream();
		int ch;
		while ((ch = inputStream.read()) != -1) {
		    bStrm.write(ch);
		}
		str = new String(bStrm.toByteArray());
		bStrm.close();
	    }
	    // Imprime string com resultado da consulta
	    System.out.println(str);
	    try {
		parserCliente(str);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    return true;
	} else {
	    // Mensagem de erro
	    mostraMensagens("Erro", "Conexão não estabelecida", AlertType.ERROR, listPrincipal);
	    // erroMsg = new String(http.getResponseCode() + http.getResponseMessage());
	    return false;
	}
    }

    public void parserCliente(String str) throws Exception {
	//Inicia o XMLParser
	KXmlParser parser = new KXmlParser(); //instância da classe KXmlParser
	parser.setInput(new InputStreamReader(new ByteArrayInputStream(str.getBytes())));

	parser.nextTag();
	//Posiciona na tag <clientes>
	parser.require(XmlPullParser.START_TAG, null, "clientes");
	//posiciona o parserCliente na tag desejada, neste caso: <clientes>.

	//Enquanto é diferente de END_TAG
	while (parser.nextTag() != XmlPullParser.END_TAG) {

	    //Posiciona na tag <pessoa>
	    parser.require(XmlPullParser.START_TAG, null, "cliente");

	    parserClienteDados(parser);//para cada cliente encontrada é chamado o método
	    //parserPessoa para ler os seus elementos.

	    parser.require(XmlPullParser.END_TAG, null, "cliente");
	}

	criaListConsultaClienteResultado(clientes);

	parser.require(XmlPullParser.END_TAG, null, "clientes");
	parser.next();
	parser.require(XmlPullParser.END_DOCUMENT, null, null);
    }

    private void parserClienteDados(KXmlParser parser) throws Exception {

	String name[] = new String[2];
	String text[] = new String[2];
	while (parser.nextTag() != XmlPullParser.END_TAG) {
	    for (int i = 0; i <= 1; i++) {
		parser.require(XmlPullParser.START_TAG, null, null);
		name[i] = parser.getName();
		text[i] = parser.nextText();

		parser.require(XmlPullParser.END_TAG, null, name[i]);
		if (i == 0) {
		    parser.nextTag();
		}
	    }

	    clientes.addElement(new Registro(Integer.parseInt(text[0]), text[1]));
	}

	/* Imprime
	for (int i = 0; i < clientes.size(); i++) {
	System.out.print(((Registro) clientes.elementAt(i)).getCodigo() + " ");
	System.out.println(((Registro) clientes.elementAt(i)).getNome());
	}
	 */
    }

    private void criaListConsultaClienteResultado(Vector clientes) {
	listConsultaClienteResultado = new List("Resultado Consulta", Choice.IMPLICIT);
	String nome = "";

	// System.out.println("size vetor = " + clientes.size());

	for (int i = 0; i < clientes.size(); i++) {
	    nome = ((Registro) clientes.elementAt(i)).getNome();
	    listConsultaClienteResultado.append(nome, null);
	    // System.out.println("i=" + i + ", " + nome);
	}

	listConsultaClienteResultado.addCommand(commandOK);
	listConsultaClienteResultado.addCommand(commandCancelar);
	listConsultaClienteResultado.setCommandListener(this);
	display.setCurrent(listConsultaClienteResultado);
    }

    /* ###################################################################### */
    /* Métodos que consultam o vendedor no Banco de Dados tabela Funcionário  */
    /* ###################################################################### */
    private void ConsultaVendedor(String url) throws Exception {
	try {
	    http = (HttpConnection) Connector.open(url); // Abre a conexão
	    http.setRequestMethod(HttpConnection.GET); // Envia método de pedido ( Neste caso é pelo método GET ).
	    inputStream = http.openInputStream(); // Resposta do servidor
	    ret = processServerResponseVendedor(http, inputStream); // Processa resposta do servidor
	} finally {
	    if (inputStream != null) {
		inputStream.close();
	    }
	    if (http != null) {
		http.close();
	    }
	}

	if (ret == false) {
	    // Mostra mensagem de erro
	}
    }

    private boolean processServerResponseVendedor(HttpConnection http, InputStream inputStream) throws IOException {
	if (http.getResponseCode() == HttpConnection.HTTP_OK) {
	    int length = (int) http.getLength();

	    if (length != -1) {
		byte servletData[] = new byte[length];
		inputStream.read(servletData);
		str = new String(servletData);
	    } else {
		ByteArrayOutputStream bStrm = new ByteArrayOutputStream();
		int ch;
		while ((ch = inputStream.read()) != -1) {
		    bStrm.write(ch);
		}
		str = new String(bStrm.toByteArray());
		bStrm.close();
	    }
	    // Imprime string com resultado da consulta
	    System.out.println(str);
	    try {
		parserVendedor(str);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    return true;
	} else {
	    // Mensagem de erro
	    mostraMensagens("Erro", "Conexão não estabelecida", AlertType.ERROR, listPrincipal);
	    // erroMsg = new String(http.getResponseCode() + http.getResponseMessage());
	    return false;
	}
    }

    public void parserVendedor(String str) throws Exception {
	//Inicia o XMLParser
	KXmlParser parser = new KXmlParser(); //instância da classe KXmlParser
	parser.setInput(new InputStreamReader(new ByteArrayInputStream(str.getBytes())));

	parser.nextTag();
	//Posiciona na tag <clientes>
	parser.require(XmlPullParser.START_TAG, null, "vendedores");
	//posiciona o parserCliente na tag desejada, neste caso: <clientes>.

	//Enquanto é diferente de END_TAG
	while (parser.nextTag() != XmlPullParser.END_TAG) {

	    //Posiciona na tag <pessoa>
	    parser.require(XmlPullParser.START_TAG, null, "vendedor");

	    parserVendedorDados(parser);//para cada cliente encontrada é chamado o método
	    //parserPessoa para ler os seus elementos.

	    parser.require(XmlPullParser.END_TAG, null, "vendedor");
	}

	criaListConsultaVendedorResultado(vendedores);

	parser.require(XmlPullParser.END_TAG, null, "vendedores");
	parser.next();
	parser.require(XmlPullParser.END_DOCUMENT, null, null);
    }

    private void parserVendedorDados(KXmlParser parser) throws Exception {

	String name[] = new String[2];
	String text[] = new String[2];
	while (parser.nextTag() != XmlPullParser.END_TAG) {
	    for (int i = 0; i <= 1; i++) {
		parser.require(XmlPullParser.START_TAG, null, null);
		name[i] = parser.getName();
		text[i] = parser.nextText();

		parser.require(XmlPullParser.END_TAG, null, name[i]);
		if (i == 0) {
		    parser.nextTag();
		}
	    }

	    vendedores.addElement(new Registro(Integer.parseInt(text[0]), text[1]));
	}

	/* Imprime
	for (int i = 0; i < clientes.size(); i++) {
	System.out.print(((Registro) clientes.elementAt(i)).getCodigo() + " ");
	System.out.println(((Registro) clientes.elementAt(i)).getNome());
	}
	 */
    }

    private void criaListConsultaVendedorResultado(Vector vendedores) {
	listConsultaVendedorResultado = new List("Resultado Consulta", Choice.IMPLICIT);
	String nome = "";

	// System.out.println("size vetor = " + clientes.size());

	for (int i = 0; i < vendedores.size(); i++) {
	    nome = ((Registro) vendedores.elementAt(i)).getNome();
	    listConsultaVendedorResultado.append(nome, null);
	    // System.out.println("i=" + i + ", " + nome);
	}

	listConsultaVendedorResultado.addCommand(commandOK);
	listConsultaVendedorResultado.addCommand(commandCancelar);
	listConsultaVendedorResultado.setCommandListener(this);
	display.setCurrent(listConsultaVendedorResultado);
    }

    /* ###################################################################### */
    /*    Métodos que direcionam todas as operações de Orçamento/Venda        */
    /* ###################################################################### */
    private void criaFormOrcamentoDados() {

	if (formOrcamentoDados == null) {
	    // Inicializando Variáveis
	    orcamento_dadosDescricao = new TextField("Descrição", null, 80, TextField.ANY);
	    orcamento_dadosObra = new TextField("Obra", null, 80, TextField.ANY);
	    orcamento_dadosObservacao = new TextField("Observação", null, 80, TextField.ANY);
	    orcamento_dadosConcluido = new ChoiceGroup("Concluído", ChoiceGroup.EXCLUSIVE);
	    orcamento_dadosData = new DateField("Data", DateField.DATE);

	    orcamento_dadosConcluido.append("Sim", null);
	    orcamento_dadosConcluido.append("Não", null);
	    orcamento_dadosConcluido.setSelectedIndex(1, true);
	    orcamento_dadosData.setDate(new Date()); // Pegando a data do sistema

	    // Adicionando componentes ao formOrcamentoDados
	    formOrcamentoDados = new Form("Orçamento Dados");
	    formOrcamentoDados.append(orcamento_dadosDescricao);
	    formOrcamentoDados.append(orcamento_dadosObra);
	    formOrcamentoDados.append(orcamento_dadosObservacao);
	    formOrcamentoDados.append(orcamento_dadosConcluido);
	    formOrcamentoDados.append(orcamento_dadosData);
	    formOrcamentoDados.addCommand(commandCancelar);
	    formOrcamentoDados.addCommand(commandEscolheCliente);
	    formOrcamentoDados.addCommand(commandEscolheVendedor);
	    formOrcamentoDados.addCommand(commandOK);
	    formOrcamentoDados.setCommandListener(this);
	}
	display.setCurrent(formOrcamentoDados);
    }

    private void criaFormConsultaProjeto() {
	if (formConsultaProjeto == null) {
	    projetoConsultaNome = new TextField("Nome", null, 80, TextField.ANY);

	    commandCancelar = new Command("Cancelar", Command.CANCEL, 0);
	    commandOK = new Command("OK", Command.OK, 1);

	    formConsultaProjeto = new Form("Consulta Projeto");
	    formConsultaProjeto.append(projetoConsultaNome);
	    formConsultaProjeto.addCommand(commandCancelar);
	    formConsultaProjeto.addCommand(commandOK);
	    formConsultaProjeto.setCommandListener(this);
	}
	display.setCurrent(formConsultaProjeto);
    }

    private void criaFormOrcamentoProdutos() {

	if (formOrcamentoProdutos == null) {
	    formOrcamentoProdutos = new Form("Orçamento Produtos");
	    formOrcamentoProdutos.append(new TextField("Código", null, 10, TextField.NUMERIC));
	    formOrcamentoProdutos.append(new TextField("Nome", null, 30, TextField.ANY));
	    formOrcamentoProdutos.append(new TextField("Quantidade", null, 10, TextField.NUMERIC));
	    formOrcamentoProdutos.addCommand(commandCancelar);
	    formOrcamentoProdutos.addCommand(commandAdicionar);
	    formOrcamentoProdutos.addCommand(commandListarProdutos);
	    formOrcamentoProdutos.addCommand(commandOK);
	    formOrcamentoProdutos.setCommandListener(this);
	}
	display.setCurrent(formOrcamentoProdutos);
    }

    private void criaFormOrcamentoValores() {

	if (formOrcamentoValores == null) {
	    valores_Desconto = new TextField("R$ Desconto", "", 10, TextField.DECIMAL);
	    valores_PorcDesconto = new TextField(" % Desconto", "", 10, TextField.DECIMAL);
	    valores_Bruto = new StringItem("R$ Bruto: ", "" + totalbrutoOrcamentoVenda);
	    valores_Final = new StringItem("R$ Final: ", "" + totalfinalOrcamentoVenda);

	    formOrcamentoValores = new Form("Valores");
	    formOrcamentoValores.append(valores_Desconto);
	    formOrcamentoValores.append(valores_PorcDesconto);
	    formOrcamentoValores.append(valores_Bruto);
	    formOrcamentoValores.append(valores_Final);
	    formOrcamentoValores.addCommand(commandOK);
	    formOrcamentoValores.addCommand(commandCalcular);
	    formOrcamentoValores.addCommand(commandCancelar);
	    formOrcamentoValores.setCommandListener(this);
	}

	totalbrutoOrcamentoVenda = somaTotalProjetos + somaTotalProdutos;

	// TODO Verificar tratamento dos valores

	try {

	    if (!valores_Desconto.getString().equals("") && valores_PorcDesconto.getString().equals("")) {
		totalfinalOrcamentoVenda = totalbrutoOrcamentoVenda - Float.parseFloat(valores_Desconto.getString().trim());
	    } else if (valores_Desconto.getString().equals("") && !valores_PorcDesconto.getString().equals("")) {
		totalfinalOrcamentoVenda = totalbrutoOrcamentoVenda - ((totalbrutoOrcamentoVenda * Float.parseFloat(valores_PorcDesconto.getString().trim())) / 100);
	    }

	} catch (Exception e) {
	}

	valores_Bruto.setText(""+totalbrutoOrcamentoVenda);
	valores_Final.setText(""+totalfinalOrcamentoVenda);


	display.setCurrent(formOrcamentoValores);

    }

    /* ###################################################################### */
    /*               Métodos que cadastram os dados do Orçamento              */
    /* ###################################################################### */
    private String geraXMLOrcamento() {
	String xmlOrcamento = "";

	xmlOrcamento += "<orcamento>\n";
	//------------------------------------------------------------------------------------------------------------
	xmlOrcamento += "\t<dados>\n";
	xmlOrcamento += "\t\t<descricao>" + regOrcamento.getDescricao().trim() + "</descricao>\n";
	xmlOrcamento += "\t\t<obra>" + regOrcamento.getObra().trim() + "</obra>\n";
	xmlOrcamento += "\t\t<cliente>" + regOrcamento.getClienteCodigo() + "</cliente>\n";
	xmlOrcamento += "\t\t<vendedor>" + regOrcamento.getVendedorCodigo() + "</vendedor>\n";
	xmlOrcamento += "\t\t<data>" + regOrcamento.getData() + "</data>\n";
	xmlOrcamento += "\t\t<observacao>" + regOrcamento.getObservacao().trim() + "</observacao>\n";
	xmlOrcamento += "\t\t<concluido>" + regOrcamento.getConcluido() + "</concluido>\n";
	xmlOrcamento += "\t\t<valorBruto>" + totalbrutoOrcamentoVenda + "</valorBruto>\n";

	xmlOrcamento += "\t\t<valorDesconto>" + (valores_Desconto.getString().trim().equals("") ? "0" : valores_Desconto.getString().trim()) + "</valorDesconto>";
	xmlOrcamento += "\t\t<valorPorDesconto>" + (valores_PorcDesconto.getString().trim().equals("") ? "0" : valores_PorcDesconto.getString().trim()) + "</valorPorDesconto>";

	xmlOrcamento += "\t\t<valorFinal>" + totalfinalOrcamentoVenda + "</valorFinal>\n";
	xmlOrcamento += "\t</dados>\n";
	//------------------------------------------------------------------------------------------------------------
	xmlOrcamento += "\t<projetos>\n";
	for (int i = 0; i < projetosOrcamento.size(); i++) {
	    xmlOrcamento += "\t\t<projeto>\n";
	    // TODO Lembrar que para cada referencia devo procurar o código do projeto no banco pra gravar na tabela corretamente
	    xmlOrcamento += "\t\t\t<referenciaProjeto>" + ((RegistroProjetos) projetosOrcamento.elementAt(i)).getReferenciaProjeto().trim() + "</referenciaProjeto>\n";
	    xmlOrcamento += "\t\t\t<valorProjeto>" + ((RegistroProjetos) projetosOrcamento.elementAt(i)).getValorProjeto() + "</valorProjeto>\n";
	    xmlOrcamento += "\t\t\t<quantidadeProjeto>" + ((RegistroProjetos) projetosOrcamento.elementAt(i)).getQuantidadeProjeto() + "</quantidadeProjeto>\n";
	    xmlOrcamento += "\t\t\t<valorProjeto>" + ((RegistroProjetos) projetosOrcamento.elementAt(i)).getValorProjeto() + "</valorProjeto>\n";
	    xmlOrcamento += "\t\t\t<referenciaVidro>" + ((RegistroProjetos) projetosOrcamento.elementAt(i)).getReferenciaVidro().trim() + "</referenciaVidro>\n";
	    xmlOrcamento += "\t\t\t<larguraVidro>" + ((RegistroProjetos) projetosOrcamento.elementAt(i)).getLargura() + "</larguraVidro>\n";
	    xmlOrcamento += "\t\t\t<alturaVidro>" + ((RegistroProjetos) projetosOrcamento.elementAt(i)).getAltura() + "</alturaVidro>\n";
	    xmlOrcamento += "\t\t\t<valorVidro>" + ((RegistroProjetos) projetosOrcamento.elementAt(i)).getValorVidro() + "</valorVidro>\n";

	    xmlOrcamento += "\t\t</projeto>\n";
	}
	xmlOrcamento += "\t</projetos>\n";
	//------------------------------------------------------------------------------------------------------------
	xmlOrcamento += "\t<produtos>\n";
	for (int i = 0; i < produtosOrcamento.size(); i++) {
	    xmlOrcamento += "\t\t<produto>\n";

	    // TODO Lembrar que para cada referencia devo procurar o código do produto no banco pra gravar na tabela corretamente
	    xmlOrcamento += "\t\t\t<referencia>" + ((RegistroProdutos) produtosOrcamento.elementAt(i)).getReferenciaProduto().trim() + "</referencia>\n";
	    // xmlOrcamento += "\t\t\t<quantidade>" + ((RegistroProdutos) projetosOrcamento.elementAt(i)).get + "</quantidade>";
	    xmlOrcamento += "\t\t\t<valor>" + ((RegistroProdutos) produtosOrcamento.elementAt(i)).getValorUnitarioProduto() + "</valor>\n";

	    xmlOrcamento += "\t\t</produto>\n";
	}
	xmlOrcamento += "\t</produtos>\n";
	//------------------------------------------------------------------------------------------------------------
	xmlOrcamento += "</orcamento>\n";

	System.out.println(xmlOrcamento);

	return xmlOrcamento;

    }

    private void CadastraOrcamento(String xml) throws IOException {
	try {
	    http = (HttpConnection) Connector.open("http://" + dominio + ":" + porta + "/Servlet/CadastraOrcamento"); // Abre a conexão
	    http.setRequestMethod(HttpConnection.POST); // Envia método de pedido ( Neste caso é pelo método GET ).
	    DataOutputStream saida;
	    saida = (DataOutputStream) http.openDataOutputStream();
	    saida.write(xml.getBytes());

	    if (http.getResponseCode() == HttpConnection.HTTP_OK) {
		listPrincipal.deleteAll();
		criaListPrincipal();
		mostraMensagens("OK", "Cadastro realizado com sucesso...", AlertType.CONFIRMATION, listPrincipal);
	    } else {
		listPrincipal.deleteAll();
		criaListPrincipal();
		mostraMensagens("Erro", "Não foi possível realizar o cadastro", AlertType.ERROR, listPrincipal);
	    }
	    //inputStream = http.openInputStream(); // Resposta do servidor
	    //ret = processServerResponse(http, inputStream); // Processa resposta do servidor
	} finally {
	    if (inputStream != null) {
		inputStream.close();
	    }
	    if (http != null) {
		http.close();
	    }
	}

	if (ret == false) {
	    // Mostra mensagem de erro
	}

    }

    private void limpaOrcamentoDados() {
	String limpa = "";
	orcamento_dadosDescricao.setString(limpa);
	orcamento_dadosObra.setString(limpa);
	orcamento_dadosObservacao.setString(limpa);
    }

    /* ###################################################################### */
    /*                               Mensagens                                */
    /* ###################################################################### */
    private void mostraMensagens(String titulo, String mensagem, AlertType tipo, List list) {
	alertMensagem = new Alert(titulo, mensagem, null, tipo);
	alertMensagem.setTimeout(Alert.FOREVER);//Alert.FOREVER);
	display.setCurrent(alertMensagem, list);
    }

    private void mostraMensagens(String titulo, String mensagem, AlertType tipo, Form form) {
	alertMensagem = new Alert(titulo, mensagem, null, tipo);
	alertMensagem.setTimeout(Alert.FOREVER);//Alert.FOREVER);
	display.setCurrent(alertMensagem, form);
    }

    /* ###################################################################### */
    /*             Métodos que consultam projetos no Banco de Dados           */
    /* ###################################################################### */
    private void ConsultaProjetos(String url) throws IOException {
	try {
	    http = (HttpConnection) Connector.open(url); // Abre a conexão
	    http.setRequestMethod(HttpConnection.GET); // Envia método de pedido ( Neste caso é pelo método GET ).
	    inputStream = http.openInputStream(); // Resposta do servidor
	    ret = processServerResponseProjetos(http, inputStream); // Processa resposta do servidor
	} finally {
	    if (inputStream != null) {
		inputStream.close();
	    }
	    if (http != null) {
		http.close();
	    }
	}

	if (ret == false) {
	    // Mostra mensagem de erro
	}
    }

    private boolean processServerResponseProjetos(HttpConnection http, InputStream inputStream) throws IOException {

	if (http.getResponseCode() == HttpConnection.HTTP_OK) {
	    int length = (int) http.getLength();

	    if (length != -1) {
		byte servletData[] = new byte[length];
		inputStream.read(servletData);
		str = new String(servletData);
	    } else {
		ByteArrayOutputStream bStrm = new ByteArrayOutputStream();
		int ch;
		while ((ch = inputStream.read()) != -1) {
		    bStrm.write(ch);
		}
		str = new String(bStrm.toByteArray());
		bStrm.close();
	    }
	    // Imprime string com resultado da consulta
	    System.out.println(str);
	    try {
		parserProjetos(str);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    return true;
	} else {
	    // Mensagem de erro
	    mostraMensagens("Erro", "Conexão não estabelecida", AlertType.ERROR, listPrincipal);
	    // erroMsg = new String(http.getResponseCode() + http.getResponseMessage());
	    return false;
	}
    }

    private void parserProjetos(String str) throws Exception {
	projetos.removeAllElements();

	//Inicia o XMLParser
	KXmlParser parser = new KXmlParser(); //instância da classe KXmlParser
	parser.setInput(new InputStreamReader(new ByteArrayInputStream(str.getBytes())));

	parser.nextTag();
	//Posiciona na tag <clientes>
	parser.require(XmlPullParser.START_TAG, null, "projetos");
	//posiciona o parserCliente na tag desejada, neste caso: <clientes>.

	//Enquanto é diferente de END_TAG
	while (parser.nextTag() != XmlPullParser.END_TAG) {

	    //Posiciona na tag <pessoa>
	    parser.require(XmlPullParser.START_TAG, null, "projeto");

	    parserProjeto(parser);//para cada cliente encontrada é chamado o método
	    //parserPessoa para ler os seus elementos.

	    parser.require(XmlPullParser.END_TAG, null, "projeto");
	}

	criaListConsultaProjetos(projetos);

	parser.require(XmlPullParser.END_TAG, null, "projetos");
	parser.next();
	parser.require(XmlPullParser.END_DOCUMENT, null, null);
    }

    private void parserProjeto(KXmlParser parser) throws Exception {

	String name[] = new String[3];
	String text[] = new String[3];
	while (parser.nextTag() != XmlPullParser.END_TAG) {
	    for (int i = 0; i < 3; i++) {
		parser.require(XmlPullParser.START_TAG, null, null);
		name[i] = parser.getName();
		text[i] = parser.nextText();

		parser.require(XmlPullParser.END_TAG, null, name[i]);
		if (i == 0 || i == 1) {
		    parser.nextTag();
		}
	    }

	    projetos.addElement(new RegistroProjetos(Integer.parseInt(text[0]), text[1], Float.parseFloat(text[2])));
	}
    }

    private void criaListConsultaProjetos(Vector projetos) {
	listConsultaProjetos = new List("Escolha do Projeto", Choice.IMPLICIT);
	String referencia = "";
	float valor;

	for (int i = 0; i < projetos.size(); i++) {
	    referencia = ((RegistroProjetos) projetos.elementAt(i)).getReferenciaProjeto() + "\n= ";
	    valor = ((RegistroProjetos) projetos.elementAt(i)).getValorProjeto();
	    listConsultaProjetos.append(referencia + valor, null);
	}

	listConsultaProjetos.addCommand(commandAdicionar);
	listConsultaProjetos.addCommand(commandListarProjetos);
	listConsultaProjetos.addCommand(commandOK);
	listConsultaProjetos.addCommand(commandCancelar);
	listConsultaProjetos.setCommandListener(this);
	display.setCurrent(listConsultaProjetos);
    }

    /* ###################################################################### */
    /*             Métodos que adicionam projetos ao Orçamento                */
    /* ###################################################################### */
    private void criaFormOrcamentoProjetos() {

	if (formOrcamentoProjetos == null) {
	    orcamento_projetosQuantidade = new TextField("Quantidade", null, 10, TextField.NUMERIC);

	    formOrcamentoProjetos = new Form("Orçamento Projetos");
	    formOrcamentoProjetos.append(orcamento_projetosQuantidade);
	    formOrcamentoProjetos.addCommand(commandOK);
	    formOrcamentoProjetos.addCommand(commandCancelar);
	    formOrcamentoProjetos.setCommandListener(this);
	}
	display.setCurrent(formOrcamentoProjetos);
    }

    // Cria List para visualizar projetos cadastrados no Orçamento
    private void criaListProjetosCadastrados() {
	if (listProjetosCadastrados != null) {
	    listProjetosCadastrados.deleteAll();
	    listProjetosCadastrados = null;
	}

	listProjetosCadastrados = new List("Projetos do Orçamento", List.IMPLICIT);

	for (int i = 0; i < projetosOrcamento.size(); i++) {
	    listProjetosCadastrados.append(((RegistroProjetos) projetosOrcamento.elementAt(i)).getReferenciaProjeto(), null);
	    //System.out.println(((RegistroProjetos) projetosOrcamento.elementAt(i)).getReferenciaProjeto());
	}
	listProjetosCadastrados.addCommand(commandOK);
	listProjetosCadastrados.setCommandListener(this);

	display.setCurrent(listProjetosCadastrados);
    }

    /* ###################################################################### */
    /*             Métodos que consultam produtos com referencia = VIDRO      */
    /* ###################################################################### */
    private void criaFormConsultaVidro() {
	if (formConsultaVidro == null) {
	    vidroConsultaNome = new TextField("Nome", null, 80, TextField.ANY);

	    commandCancelar = new Command("Cancelar", Command.CANCEL, 0);
	    commandOK = new Command("OK", Command.OK, 1);

	    formConsultaVidro = new Form("Consulta Vidro");
	    formConsultaVidro.append(vidroConsultaNome);
	    formConsultaVidro.addCommand(commandCancelar);
	    formConsultaVidro.addCommand(commandOK);
	    formConsultaVidro.setCommandListener(this);
	}
	display.setCurrent(formConsultaVidro);
    }

    private void ConsultaVidro(String url) throws IOException {
	try {
	    http = (HttpConnection) Connector.open(url); // Abre a conexão
	    http.setRequestMethod(HttpConnection.GET); // Envia método de pedido ( Neste caso é pelo método GET ).
	    inputStream = http.openInputStream(); // Resposta do servidor
	    ret = processServerResponseVidro(http, inputStream); // Processa resposta do servidor
	} finally {
	    if (inputStream != null) {
		inputStream.close();
	    }
	    if (http != null) {
		http.close();
	    }
	}

	if (ret == false) {
	    // Mostra mensagem de erro
	}
    }

    private boolean processServerResponseVidro(HttpConnection http, InputStream inputStream) throws IOException {

	if (http.getResponseCode() == HttpConnection.HTTP_OK) {
	    int length = (int) http.getLength();

	    if (length != -1) {
		byte servletData[] = new byte[length];
		inputStream.read(servletData);
		str = new String(servletData);
	    } else {
		ByteArrayOutputStream bStrm = new ByteArrayOutputStream();
		int ch;
		while ((ch = inputStream.read()) != -1) {
		    bStrm.write(ch);
		}
		str = new String(bStrm.toByteArray());
		bStrm.close();
	    }
	    // Imprime string com resultado da consulta
	    System.out.println(str);
	    try {
		parserVidros(str);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    return true;
	} else {
	    // Mensagem de erro
	    mostraMensagens("Erro", "Conexão não estabelecida", AlertType.ERROR, listPrincipal);
	    // erroMsg = new String(http.getResponseCode() + http.getResponseMessage());
	    return false;
	}
    }

    private void parserVidros(String str) throws Exception {
	vidros.removeAllElements();

	//Inicia o XMLParser
	KXmlParser parser = new KXmlParser(); //instância da classe KXmlParser
	parser.setInput(new InputStreamReader(new ByteArrayInputStream(str.getBytes())));

	parser.nextTag();
	//Posiciona na tag <clientes>
	parser.require(XmlPullParser.START_TAG, null, "vidros");
	//posiciona o parserCliente na tag desejada, neste caso: <clientes>.

	//Enquanto é diferente de END_TAG
	while (parser.nextTag() != XmlPullParser.END_TAG) {

	    //Posiciona na tag <pessoa>
	    parser.require(XmlPullParser.START_TAG, null, "vidro");

	    parserVidro(parser);//para cada cliente encontrada é chamado o método
	    //parserPessoa para ler os seus elementos.

	    parser.require(XmlPullParser.END_TAG, null, "vidro");
	}

	criaListConsultaVidro(vidros);

	parser.require(XmlPullParser.END_TAG, null, "vidros");
	parser.next();
	parser.require(XmlPullParser.END_DOCUMENT, null, null);
    }

    private void parserVidro(KXmlParser parser) throws Exception {

	String name[] = new String[3];
	String text[] = new String[3];
	while (parser.nextTag() != XmlPullParser.END_TAG) {
	    for (int i = 0; i < 3; i++) {
		parser.require(XmlPullParser.START_TAG, null, null);
		name[i] = parser.getName();
		text[i] = parser.nextText();

		parser.require(XmlPullParser.END_TAG, null, name[i]);
		if (i == 0 || i == 1) {
		    parser.nextTag();
		}
	    }

	    vidros.addElement(new RegistroProjetos(Integer.parseInt(text[0]), text[1], Float.parseFloat(text[2])));
	}
    }

    private void criaListConsultaVidro(Vector vidros) {
	listConsultaVidros = new List("Escolha do Vidro", Choice.IMPLICIT);
	String referencia = "";
	float valor;

	for (int i = 0; i < vidros.size(); i++) {
	    referencia = ((RegistroProjetos) vidros.elementAt(i)).getReferenciaProjeto() + "\n= ";
	    valor = ((RegistroProjetos) vidros.elementAt(i)).getValorProjeto();
	    listConsultaVidros.append(referencia + valor, null);
	}

	listConsultaVidros.addCommand(commandCancelar);
	listConsultaVidros.addCommand(commandOK);
	listConsultaVidros.setCommandListener(this);
	display.setCurrent(listConsultaVidros);
    }

    private void criaFormOrcamentoProjetosDadosDoVidro() {

	if (formDadosDoVidro == null) {
	    orcamento_projetosVidroLargura = new TextField("Largura", null, 10, TextField.DECIMAL);
	    orcamento_projetosVidroAltura = new TextField("Altura", null, 10, TextField.DECIMAL);

	    formDadosDoVidro = new Form("Dados do Vidro");
	    formDadosDoVidro.append(orcamento_projetosVidroLargura);
	    formDadosDoVidro.append(orcamento_projetosVidroAltura);
	    formDadosDoVidro.addCommand(commandOK);
	    formDadosDoVidro.addCommand(commandCancelar);
	    formDadosDoVidro.setCommandListener(this);
	}
	display.setCurrent(formDadosDoVidro);
    }

    /* ###################################################################### */
    /*                          Consulta Produtos                             */
    /* ###################################################################### */
    private void criaFormConsultaProdutos() {
	if (formConsultaProduto == null) {
	    produtoConsultaNome = new TextField("Nome", null, 80, TextField.ANY);

	    commandCancelar = new Command("Cancelar", Command.CANCEL, 0);
	    commandOK = new Command("OK", Command.OK, 1);

	    formConsultaProduto = new Form("Consulta Produto");
	    formConsultaProduto.append(produtoConsultaNome);
	    formConsultaProduto.addCommand(commandCancelar);
	    formConsultaProduto.addCommand(commandOK);
	    formConsultaProduto.setCommandListener(this);
	}
	display.setCurrent(formConsultaProduto);
    }

    private void ConsultaProdutos(String url) throws IOException {
	try {
	    http = (HttpConnection) Connector.open(url); // Abre a conexão
	    http.setRequestMethod(HttpConnection.GET); // Envia método de pedido ( Neste caso é pelo método GET ).
	    inputStream = http.openInputStream(); // Resposta do servidor
	    ret = processServerResponseProduto(http, inputStream); // Processa resposta do servidor
	} finally {
	    if (inputStream != null) {
		inputStream.close();
	    }
	    if (http != null) {
		http.close();
	    }
	}

	if (ret == false) {
	    // Mostra mensagem de erro
	}
    }

    private boolean processServerResponseProduto(HttpConnection http, InputStream inputStream) throws IOException {

	if (http.getResponseCode() == HttpConnection.HTTP_OK) {
	    int length = (int) http.getLength();

	    if (length != -1) {
		byte servletData[] = new byte[length];
		inputStream.read(servletData);
		str = new String(servletData);
	    } else {
		ByteArrayOutputStream bStrm = new ByteArrayOutputStream();
		int ch;
		while ((ch = inputStream.read()) != -1) {
		    bStrm.write(ch);
		}
		str = new String(bStrm.toByteArray());
		bStrm.close();
	    }
	    // Imprime string com resultado da consulta
	    System.out.println(str);
	    try {
		parserProdutos(str);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    return true;
	} else {
	    // Mensagem de erro
	    mostraMensagens("Erro", "Conexão não estabelecida", AlertType.ERROR, listPrincipal);
	    // erroMsg = new String(http.getResponseCode() + http.getResponseMessage());
	    return false;
	}
    }

    private void parserProdutos(String str) throws Exception {
	produtos.removeAllElements();

	//Inicia o XMLParser
	KXmlParser parser = new KXmlParser(); //instância da classe KXmlParser
	parser.setInput(new InputStreamReader(new ByteArrayInputStream(str.getBytes())));

	parser.nextTag();
	//Posiciona na tag <clientes>
	parser.require(XmlPullParser.START_TAG, null, "produtos");
	//posiciona o parserCliente na tag desejada, neste caso: <clientes>.

	//Enquanto é diferente de END_TAG
	while (parser.nextTag() != XmlPullParser.END_TAG) {

	    //Posiciona na tag <pessoa>
	    parser.require(XmlPullParser.START_TAG, null, "produto");

	    parserProduto(parser);//para cada cliente encontrada é chamado o método
	    //parserPessoa para ler os seus elementos.

	    parser.require(XmlPullParser.END_TAG, null, "produto");
	}

	criaListConsultaProduto(produtos);

	parser.require(XmlPullParser.END_TAG, null, "produtos");
	parser.next();
	parser.require(XmlPullParser.END_DOCUMENT, null, null);
    }

    private void parserProduto(KXmlParser parser) throws Exception {

	String name[] = new String[3];
	String text[] = new String[3];
	while (parser.nextTag() != XmlPullParser.END_TAG) {
	    for (int i = 0; i < 3; i++) {
		parser.require(XmlPullParser.START_TAG, null, null);
		name[i] = parser.getName();
		text[i] = parser.nextText();

		parser.require(XmlPullParser.END_TAG, null, name[i]);
		if (i == 0 || i == 1) {
		    parser.nextTag();
		}
	    }

	    produtos.addElement(new RegistroProdutos(Integer.parseInt(text[0]), text[1], Float.parseFloat(text[2])));
	}
    }

    private void criaListConsultaProduto(Vector vidros) {
	listConsultaProdutos = new List("Escolha do Produto", Choice.IMPLICIT);
	String referencia = "";
	float valor;

	for (int i = 0; i < produtos.size(); i++) {
	    referencia = ((RegistroProdutos) produtos.elementAt(i)).getReferenciaProduto() + "\n= ";
	    valor = ((RegistroProdutos) produtos.elementAt(i)).getValorUnitarioProduto();
	    listConsultaProdutos.append(referencia + valor, null);
	}

	listConsultaProdutos.addCommand(commandAdicionar);
	listConsultaProdutos.addCommand(commandListarProdutos);
	listConsultaProdutos.addCommand(commandOK);
	listConsultaProdutos.addCommand(commandCancelar);
	listConsultaProdutos.setCommandListener(this);
	display.setCurrent(listConsultaProdutos);
    }

    /* ###################################################################### */
    /*                           Consulta Produtos                            */
    /* ###################################################################### */
    private void criaFormProdutos() {

	if (formOrcamentoProdutos == null) {
	    orcamento_produtosQuantidade = new TextField("Quantidade", null, 10, TextField.NUMERIC);

	    formOrcamentoProdutos = new Form("Produto");
	    formOrcamentoProdutos.append(orcamento_produtosQuantidade);
	    formOrcamentoProdutos.addCommand(commandOK);
	    formOrcamentoProdutos.addCommand(commandCancelar);
	    formOrcamentoProdutos.setCommandListener(this);
	}
	display.setCurrent(formOrcamentoProjetos);
    }

    // Cria List para visualizar projetos cadastrados no Orçamento
    private void criaListProdutosCadastrados() {
	if (listProdutosCadastrados != null) {
	    listProdutosCadastrados.deleteAll();
	    listProdutosCadastrados = null;
	}

	listProdutosCadastrados = new List("Produtos do Orçamento", List.IMPLICIT);

	for (int i = 0; i < produtosOrcamento.size(); i++) {
	    listProdutosCadastrados.append(((RegistroProdutos) produtosOrcamento.elementAt(i)).getReferenciaProduto(), null);
	}
	listProdutosCadastrados.addCommand(commandOK);
	listProdutosCadastrados.setCommandListener(this);

	display.setCurrent(listProdutosCadastrados);
    }


    /* ###################################################################### */
    /*                  Command Action - Implementação                        */
    /* ###################################################################### */
    public void commandAction(Command c, Displayable d) {

	/**     LIST PRINCIPAL
	 * Esta tela é reponsável por direcionar todas as operações disponíveis no sistema
	 * e finalizar a aplicação
	 */
	if (c == commandSair && (listPrincipal == null ? false : d.getTitle().equals(listPrincipal.getTitle()))) {
	    destroyApp(true);
	    notifyDestroyed();
	} else if ((c.getLabel().equals(commandProximo.getLabel())) && (listPrincipal == null ? false : d.getTitle().equals(listPrincipal.getTitle())) || (c == List.SELECT_COMMAND && (listPrincipal == null ? false : d.getTitle().equals(listPrincipal.getTitle())))) {
	    switch (listPrincipal.getSelectedIndex()) {
		case 0:
		    criaFormCadastroCliente();
		    break;
		case 1:
		    criaFormConsultaCLiente();
		    formConsultaCliente.setTitle("Consulta Cliente");
		    break;
		case 2:
		    criaListOrcamento();
		    break;
		default:
		    break;
	    }
	} /**     FORM CADASTRO CLIENTE
	 * Nesta tela é possivel realizar o cadastro de clientes
	 * Nela temos o botão OK que faz a comunicação com o banco de dados
	 * para realização do cadastro e o botão cancelar que anula a operação
	 */
	else if (c == commandCancelar && (formCadastroCliente == null ? false : d.getTitle().equals(formCadastroCliente.getTitle()))) {
	    limpaCadastroCliente();
	    criaListPrincipal();
	} else if (c == commandOK && (formCadastroCliente == null ? false : d.getTitle().equals(formCadastroCliente.getTitle()))) {
	    try {
		CadastraCliente(geraXMLCadastroCliente());
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	    listPrincipal.setSelectedIndex(1, true);
	} /**     FORM CONSULTA CLIENTE
	 * Nesta tela é possível realizar a consulta de clientes
	 * Nela temos o botão OK que faz a comunicação com o banco de dados
	 * para realização da consulta e o botão cancelar que anula a operação
	 */
	else if (c == commandCancelar && (formConsultaCliente == null ? false : d.getTitle().equals("Consulta Cliente"))) {
	    //clienteConsultaNome.setString("");
	    formConsultaCliente.deleteAll();
	    criaListPrincipal();
	} else if (c == commandCancelar && (formConsultaCliente == null ? false : d.getTitle().equals("Cliente Orçamento"))) {
	    //clienteConsultaNome.setString("");
	    formConsultaCliente.deleteAll();
	    criaFormOrcamentoDados();
	} else if (c == commandOK && (formConsultaCliente == null ? false : d.getTitle().equals(formConsultaCliente.getTitle()))) {
	    try {
		ConsultaCliente("http://" + dominio + ":" + porta + "/Servlet/ConsultaCliente?nome=" + clienteConsultaNome.getString());
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	}

	/**     LIST CONSULTA CLIENTE RESULTADO
	 * Nesta tela é mostrado o resultado da consutla de clientes
	 */
	if (c == commandCancelar && (listConsultaClienteResultado == null ? false : d.getTitle().equals(listConsultaClienteResultado.getTitle()))) {
	    listConsultaClienteResultado.deleteAll();
	    criaFormConsultaCLiente();
	} else if (c == commandOK && (listConsultaClienteResultado == null ? false : d.getTitle().equals(listConsultaClienteResultado.getTitle()))) {
	    // para que percorre o vetor de clientes encontrados na consulta e armazena o código
	    // do cliente em um regOrçamento que é utilizado para gravar as informações no
	    // momento da venda
	    for (int i = 0; i < clientes.size(); i++) {
		if (((Registro) clientes.elementAt(i)).getNome().equals(listConsultaClienteResultado.getString(listConsultaClienteResultado.getSelectedIndex()))) {
		    regOrcamento.setClienteCodigo(((Registro) clientes.elementAt(i)).getCodigo());
		    regOrcamento.setClienteNome(((Registro) clientes.elementAt(i)).getNome());
		}

	    }

	    regOrcamento.setClienteNome(regOrcamento.separaNome(regOrcamento.getClienteNome()));

	    criaFormOrcamentoDados();
	} /**     LIST ORCAMENTO
	 * Esta tela é reponsável por direcionar todas as operações de Orçamento/Venda disponíveis no sistema
	 */
	else if ((c.getLabel().equals(commandProximo.getLabel()) && (listOrcamento == null ? false : d.getTitle().equals(listOrcamento.getTitle())))
		|| c.getLabel().equals(commandProximo.getLabel()) && (c == List.SELECT_COMMAND && (listOrcamento == null ? false : d.getTitle().equals(listOrcamento.getTitle())))) {
	    switch (listOrcamento.getSelectedIndex()) {
		case 0: // Dados
		    criaFormOrcamentoDados();
		    break;
		case 1: // Projetos
		    criaFormConsultaProjeto();
		    break;
		case 2: // Produtos
		    criaFormConsultaProdutos();
		    break;
		case 3: // Valores
		    criaFormOrcamentoValores();
		    break;
		default:
		    break;
	    }
	} else if ((c.getLabel().equals(commandCancelar.getLabel())) && (listOrcamento == null ? false : d.getTitle().equals(listOrcamento.getTitle()))) {
	    totalbrutoOrcamentoVenda = 0;
	    totalfinalOrcamentoVenda = 0;
	    criaListPrincipal();
	}

	/**        FORM ORCAMENTO DADOS
	 * Nesta tela é possível cadastrar os dados do Orçamento
	 * Nela temos os botões
	 * OK - Armazena os dados cadastrais do orçamento no regOrcamento e Direciona para o listPrincipal para escolha dos projetos
	 * Escolhe Cliente - Direciona a uma nova tela para escolha do cliente que estará no orçamento
	 * Escolhe Vendedor - Direciona a uma nova tela para esoolha do vendedor que está realizando o orçamento
	 * Cancelar - Anula operação de inserção dos dados do orçamento
	 *
	 */
	if (c.getLabel().equals(commandCancelar.getLabel()) && (formOrcamentoDados == null ? false : d.getTitle().equals(formOrcamentoDados.getTitle()))) {
	    //limpaOrcamentoDados();
	    //regOrcamento.limpaCamposOrcamentoDados();
	    criaListOrcamento();
	    listOrcamento.setSelectedIndex(0, true);
	} else if (c.getLabel().equals(commandEscolheCliente.getLabel()) && (formOrcamentoDados == null ? false : d.getTitle().equals(formOrcamentoDados.getTitle()))) {
	    // Chama tela para escolher o cliente
	    if (formConsultaCliente != null) {
		formConsultaCliente = null;
	    }
	    criaFormConsultaCLiente();
	    formConsultaCliente.setTitle("Cliente Orçamento");

	} else if (c.getLabel().equals(commandEscolheVendedor.getLabel()) && d.getTitle().equals(formOrcamentoDados.getTitle())) {
	    // Chama a tela para escolher o vendedor
	    //formConsultaVendedor.deleteAll();
	    if (formConsultaVendedor != null) {
		formConsultaVendedor = null;
	    }

	    criaFormConsultaVendedor();

	} else if (c.getLabel().equals(commandOK.getLabel()) && (formOrcamentoDados == null ? false : d.getTitle().equals(formOrcamentoDados.getTitle()))) {

	    if (!regOrcamento.getClienteNome().equals("") && !regOrcamento.getVendedorNome().equals("")) {
		// Armazena dados do orçamento no registro
		regOrcamento.setDescricao(orcamento_dadosDescricao.getString().trim());
		regOrcamento.setObra(orcamento_dadosObra.getString().trim());
		regOrcamento.setObservacao(orcamento_dadosObservacao.getString().trim());

		int concluido = orcamento_dadosConcluido.getSelectedIndex();
		if (concluido == 0) {
		    regOrcamento.setConcluido("SIM");
		} else if (concluido == 1) {
		    regOrcamento.setConcluido("NAO");
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(orcamento_dadosData.getDate());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int date = calendar.get(Calendar.DATE);

		regOrcamento.setData("" + date + "/" + month + "/" + year);


		criaListOrcamento();
		listOrcamento.setSelectedIndex(1, true);
	    } else if (regOrcamento.getClienteNome().equals("") || regOrcamento.getVendedorNome().equals("")) {
		mostraMensagens("Alerta", "Por favor informe um cliente e um vendedor para realizar o Orçamento", AlertType.INFO, formOrcamentoDados);
	    }

	}

	/**     FORM CONSULTA VENDEDOR
	 *
	 */
	if (c == commandCancelar && (formConsultaVendedor == null ? false : d.getTitle().equals(formConsultaVendedor.getTitle()))) {
	    vendedorConsultaNome.setString("");
	    criaFormOrcamentoDados();
	} else if (c == commandOK && (formConsultaVendedor == null ? false : d.getTitle().equals(formConsultaVendedor.getTitle()))) {
	    try {
		ConsultaVendedor("http://" + dominio + ":" + porta + "/Servlet/ConsultaVendedor?nome=" + vendedorConsultaNome.getString());
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}

	/**     FORM CONSULTA PROJETO
	 * Nesta tela é possivel consultar um projeto cadastrado no banco de dados
	 * para que o mesmo possa ser escolhido para fazer parte do orçamento
	 */
	if (c.getLabel().equals(commandCancelar.getLabel()) && (formConsultaProjeto == null ? false : d.getTitle().equals(formConsultaProjeto.getTitle()))) {
	    criaListOrcamento();
	} else if (c.getLabel().equals(commandOK.getLabel()) && (formConsultaProjeto == null ? false : d.getTitle().equals(formConsultaProjeto.getTitle()))) {
	    try {
		ConsultaProjetos("http://" + dominio + ":" + porta + "/Servlet/ConsultaProjetos?referencia=" + projetoConsultaNome.getString());
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	} /**     LIST CONSULTA PROJETOS
	 * Nesta tela é mostrado todos os projetos que estão no banco de dados, para que o usuário possa adicionar no orçamento
	 * Nela temos os botões
	 * Adicionar - Armazena os dados do projeto (como referência e valor) no regOrcamento e direciona a uma nova tela
	 *             para que seja informado a quantidade do projeto que esta sendo escolhido
	 * Cancelar - Anula operação de consulta de projetos
	 */
	else if (c.getLabel().equals(commandCancelar.getLabel()) && (listConsultaProjetos == null ? false : d.getTitle().equals(listConsultaProjetos.getTitle()))) {
	    projetosOrcamento.setSize(0);
	    listProjetosCadastrados = null;
	    somaTotalProjetos = 0;
	    listConsultaProjetos.deleteAll();
	    criaFormConsultaProjeto();
	} else if ((c.getLabel().equals(commandAdicionar.getLabel())) && (listConsultaProjetos == null ? false : d.getTitle().equals(listConsultaProjetos.getTitle()))) {
	    String refProjeto = "";
	    String nomeProjeto = "";
	    String valProjeto = "";
	    refProjeto = listConsultaProjetos.getString(listConsultaProjetos.getSelectedIndex());

	    // Trecho de código que separa a referencia do projeto do seu respectivo valor
	    int i = 0;

	    while (i < refProjeto.length()) {
		if (refProjeto.charAt(i) != '=') {
		    nomeProjeto = nomeProjeto + refProjeto.charAt(i);
		} else if (refProjeto.charAt(i) == '=') {
		    i++;
		    while (i < refProjeto.length()) {
			valProjeto = valProjeto + refProjeto.charAt(i);
			i++;
		    }
		}
		i++;
	    }
	    // Trecho de código que separa a referencia do projeto do seu respectivo valor

	    projetosOrcamento.addElement(new RegistroProjetos(nomeProjeto, Float.parseFloat(valProjeto)));

	    //System.out.println("Referencia do projeto escolhido = "+ ((RegistroProjetos) projetosOrcamento.elementAt(0)).getReferencia()+" Tamanho = "+projetosOrcamento.size());//
	    criaFormOrcamentoProjetos();

	} else if ((c.getLabel().equals(commandListarProjetos.getLabel())) && (listConsultaProjetos == null ? false : d.getTitle().equals(listConsultaProjetos.getTitle()))) {
	    criaListProjetosCadastrados();
	} else if ((c.getLabel().equals(commandOK.getLabel())) && (listConsultaProjetos == null ? false : d.getTitle().equals(listConsultaProjetos.getTitle()))) {


	    criaListOrcamento();
	    listOrcamento.setSelectedIndex(2, true);
	}

	/**     LIST CONSULTA VENDEDOR RESULTADO
	 *
	 */
	if (c.getLabel().equals(commandCancelar.getLabel()) && (listConsultaVendedorResultado == null ? false : d.getTitle().equals(listConsultaVendedorResultado.getTitle()))) {
	    listConsultaVendedorResultado.deleteAll();
	    criaFormOrcamentoDados();
	} else if (c.getLabel().equals(commandOK.getLabel()) && (listConsultaVendedorResultado == null ? false : d.getTitle().equals(listConsultaVendedorResultado.getTitle()))) {
	    // para que percorre o vetor de clientes encontrados na consulta e armazena o código
	    // do cliente em um regOrçamento que é utilizado para gravar as informações no
	    // momento da venda
	    for (int i = 0; i < vendedores.size(); i++) {
		if (((Registro) vendedores.elementAt(i)).getNome().equals(listConsultaVendedorResultado.getString(listConsultaVendedorResultado.getSelectedIndex()))) {
		    regOrcamento.setVendedorCodigo(((Registro) vendedores.elementAt(i)).getCodigo());
		    regOrcamento.setVendedorNome(((Registro) vendedores.elementAt(i)).getNome());
		}
	    }

	    regOrcamento.setVendedorNome(regOrcamento.getVendedorNome());


	    criaFormOrcamentoDados();

	    System.out.println(regOrcamento.getVendedorCodigo());
	    System.out.println(regOrcamento.getVendedorNome());

	    // è necessário quando clicar no botão OK do formOrcamentoDados chamar a tela para escolha do vendedor, porque
	    // nesse momento o cliente ja foi escolhido

	    //mostraMensagens("", "Cliente Escolhido = \n"+regOrcamento.getClienteNome() , AlertType.ALARM, listPrincipal);
            /*
	    String texto = (regOrcamento.getClienteCodigo() + "-" + regOrcamento.getClienteNome()).trim();
	    orcamento_dadosCliente.setString(texto);*/

	} /**     FORM ORCAMENTO PROJETOS
	 *
	 */
	else if (c.getLabel().equals(commandCancelar.getLabel()) && (formOrcamentoProjetos == null ? false : d.getTitle().equals(formOrcamentoProjetos.getTitle()))) {

	    if (projetosOrcamento.size() > 0) {
		projetosOrcamento.removeElementAt((projetosOrcamento.size() - 1));
	    }

	    listConsultaProjetos.deleteAll();
	    criaListConsultaProjetos(projetos);

	} else if (c.getLabel().equals(commandOK.getLabel()) && (formOrcamentoProjetos == null ? false : d.getTitle().equals(formOrcamentoProjetos.getTitle()))) {

	    if ((orcamento_projetosQuantidade.getString().equals("") ? true : false) || (Integer.parseInt(orcamento_projetosQuantidade.getString()) <= 0)) {
		mostraMensagens("Alerta", "Informe uma quantidade válida de projetos", AlertType.INFO, formOrcamentoProjetos);
	    } else {
		((RegistroProjetos) projetosOrcamento.lastElement()).setQuantidadeProjeto(Integer.parseInt(orcamento_projetosQuantidade.getString()));

		// Armazena os valores dos projetos para exibir na tela de valores
		// Direciona a tela para escolha dos produtos referentes ao orçamento


		somaTotalProjetos += ((((RegistroProjetos) projetosOrcamento.lastElement()).getQuantidadeProjeto()) * (((RegistroProjetos) projetosOrcamento.lastElement()).getValorProjeto()));
		//System.out.println("soma (projeto * quantidade) = "+somaTotalProjetos);
		criaFormConsultaVidro();
	    }

	} /**     FORM CONSULTA VIDRO
	 *
	 */
	else if (c.getLabel().equals(commandCancelar.getLabel()) && (formConsultaVidro == null ? false : d.getTitle().equals(formConsultaVidro.getTitle()))) {

	    criaFormOrcamentoProjetos();

	} else if (c.getLabel().equals(commandOK.getLabel()) && (formConsultaVidro == null ? false : d.getTitle().equals(formConsultaVidro.getTitle()))) {

	    try {
		ConsultaVidro("http://" + dominio + ":" + porta + "/Servlet/ConsultaVidro?referencia=" + vidroConsultaNome.getString());
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }

	} /**     LIST CONSULTA VIDROS
	 *
	 */
	else if (c.getLabel().equals(commandCancelar.getLabel()) && (listConsultaVidros == null ? false : d.getTitle().equals(listConsultaVidros.getTitle()))) {

	    // Apaga a referencia do vidro e o valor do vidro armazenado no registro
	    // e retorna a tela de consulta do vidro
	    ((RegistroProjetos) projetosOrcamento.lastElement()).setReferenciaVidro("");
	    ((RegistroProjetos) projetosOrcamento.lastElement()).setValorVidro(0);

	    criaFormConsultaVidro();

	} else if (c.getLabel().equals(commandOK.getLabel()) && (listConsultaVidros == null ? false : d.getTitle().equals(listConsultaVidros.getTitle()))) {

	    /* Separr a referencia do vidro, do valor do vidro
	    e armazena no vetor projetosOrcamento na última posição */

	    String refVidro = "";
	    String nomeVidro = "";
	    String valVidro = "";
	    refVidro = listConsultaVidros.getString(listConsultaVidros.getSelectedIndex());

	    // Trecho de código que separa a referencia do vidro do seu respectivo valor
	    int i = 0;

	    while (i < refVidro.length()) {
		if (refVidro.charAt(i) != '=') {
		    nomeVidro = nomeVidro + refVidro.charAt(i);
		} else if (refVidro.charAt(i) == '=') {
		    i++;
		    while (i < refVidro.length()) {
			valVidro = valVidro + refVidro.charAt(i);
			i++;
		    }
		}
		i++;
	    }
	    // Trecho de código que separa a referencia do vidro do seu respectivo valor

	    ((RegistroProjetos) projetosOrcamento.lastElement()).setReferenciaVidro(nomeVidro);
	    ((RegistroProjetos) projetosOrcamento.lastElement()).setValorVidro(Float.parseFloat(valVidro));

	    //System.out.println("Referencia = " + ((RegistroProjetos) projetosOrcamento.lastElement()).getReferenciaVidro());
	    //System.out.println("Valor = " + ((RegistroProjetos) projetosOrcamento.lastElement()).getValorVidro());

	    criaFormOrcamentoProjetosDadosDoVidro();

	} /**     FORM DADOS DO VIDRO
	 *
	 */
	else if (c.getLabel().equals(commandCancelar.getLabel()) && (formDadosDoVidro == null ? false : d.getTitle().equals(formDadosDoVidro.getTitle()))) {
	    ((RegistroProjetos) projetosOrcamento.lastElement()).setLargura(0);
	    ((RegistroProjetos) projetosOrcamento.lastElement()).setAltura(0);

	    criaFormConsultaVidro();
	} else if (c.getLabel().equals(commandOK.getLabel()) && (formDadosDoVidro == null ? false : d.getTitle().equals(formDadosDoVidro.getTitle()))) {
	    // Armazena largura do vidro e altura do vidro na última posição do registro
	    // e direciona a tela para escolha de outro projeto para que possa ser colocado no orçamento


	    if (((orcamento_projetosVidroLargura.getString().equals("") ? true : false) || (Float.parseFloat(orcamento_projetosVidroLargura.getString()) <= 0))
		    || ((orcamento_projetosVidroAltura.getString().equals("") ? true : false) || (Float.parseFloat(orcamento_projetosVidroAltura.getString()) <= 0))) {
		mostraMensagens("Alerta", "Informe uma largura e altura válida paar o vidro", AlertType.INFO, formDadosDoVidro);
	    } else {

		((RegistroProjetos) projetosOrcamento.lastElement()).setLargura(Float.parseFloat(orcamento_projetosVidroLargura.getString()));
		((RegistroProjetos) projetosOrcamento.lastElement()).setAltura(Float.parseFloat(orcamento_projetosVidroAltura.getString()));

		float metrosQuadrados = 0;

		metrosQuadrados = ((RegistroProjetos) projetosOrcamento.lastElement()).getLargura() * ((RegistroProjetos) projetosOrcamento.lastElement()).getAltura();
		somaTotalProjetos += ((RegistroProjetos) projetosOrcamento.lastElement()).getValorVidro() * metrosQuadrados;

		System.out.println("Total bruto = " + somaTotalProjetos);

		criaListConsultaProjetos(projetos);
	    }
	}


	/**     LIST PROJETOS CADASTRADOS
	 * Nesta tela são mostrados todos os projetos que foram incluidos no orçamento
	 * Nela só existe um botão que tem a finalidade de retornar a tela de escolha de projetos
	 */
	if (c == commandOK && (listProjetosCadastrados == null ? false : d.getTitle().equals(listProjetosCadastrados.getTitle()))) {
	    criaListConsultaProjetos(projetos);
	}

	/**     FORM ORCAMENTO PRODUTOS
	 *
	 */
	if (c == commandCancelar && (formOrcamentoProdutos == null ? false : d.getTitle().equals(formOrcamentoProdutos.getTitle()))) {
	    formOrcamentoProdutos.deleteAll();
	    display.setCurrent(listOrcamento);
	} else if (c == commandOK && (formOrcamentoProdutos == null ? false : d.getTitle().equals(formOrcamentoProdutos.getTitle()))) {
	    // Armazena valore dos campos em um arquivo XML
	    display.setCurrent(listOrcamento);
	    listOrcamento.setSelectedIndex(3, true);
	}

	/**     FORM ORCAMENTO VALORES
	 *
	 */
	if (c.getLabel().equals(commandCancelar.getLabel()) && (formOrcamentoValores == null ? false : d.getTitle().equals(formOrcamentoValores.getTitle()))) {
	    totalbrutoOrcamentoVenda = 0;
	    criaListOrcamento();

	} else if (c.getLabel().equals(commandOK.getLabel()) && (formOrcamentoValores == null ? false : d.getTitle().equals(formOrcamentoValores.getTitle()))) {
	    // TODO Gerar xml dos dados, projetos, produtos e valores do orçamento para que possa ser gravado no banco de dados
	    String orcamento="";

	    try {
		orcamento = geraXMLOrcamento();

		if ((orcamento.indexOf("<projeto>") >= 0) || (orcamento.indexOf("<produto>") >= 0)) {
		    CadastraOrcamento(geraXMLOrcamento());
		} else{
		    mostraMensagens("Alerta", "Não é possivel gravar o orçamento no banco de dados, por favor escolha produtos ou projetos para efetuar a gravação .....", AlertType.INFO, listPrincipal);
		    limpaOrcamentoDados();
		}

		
	    } catch (Exception e) {
	    }

	    /*
	    criaListOrcamento();
	    listOrcamento.setSelectedIndex(1, true); */


	} else if (c == commandCalcular && (formOrcamentoValores == null ? false : d.getTitle().equals(formOrcamentoValores.getTitle()))) {
	    criaFormOrcamentoValores();
	}

	/** Consulta Produtos
	 *  FORM CONSULTA PRODUTO
	 */
	if (c == commandCancelar && (formConsultaProduto == null ? false : d.getTitle().equals(formConsultaProduto.getTitle()))) {

	    criaListOrcamento();

	} else if (c == commandOK && (formConsultaProduto == null ? false : d.getTitle().equals(formConsultaProduto.getTitle()))) {
	    try {
		ConsultaProdutos("http://" + dominio + ":" + porta + "/Servlet/ConsultaProdutos?referencia=" + produtoConsultaNome.getString());
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	}

	/** Consulta Produtos
	 *   LIST CONSULTA PRODUTOS
	 */
	if (c == commandCancelar && (listConsultaProdutos == null ? false : d.getTitle().equals(listConsultaProdutos.getTitle()))) {

	    produtosOrcamento.removeAllElements();
	    listProdutosCadastrados = null;
	    listConsultaProdutos.deleteAll();
	    criaFormConsultaProdutos();

	} else if (c == commandAdicionar && (listConsultaProdutos == null ? false : d.getTitle().equals(listConsultaProdutos.getTitle()))) {
	    // Armazena o produto escolhido na última posição do registro e direciona a tela para escolha
	    // de um novo produto para que possa ser inserido no orçamento

	    String refProduto = "";
	    String nomeProduto = "";
	    String valProduto = "";
	    refProduto = listConsultaProdutos.getString(listConsultaProdutos.getSelectedIndex());

	    // Trecho de código que separa a referencia do produto do seu respectivo valor
	    int i = 0;

	    while (i < refProduto.length()) {
		if (refProduto.charAt(i) != '=') {
		    nomeProduto = nomeProduto + refProduto.charAt(i);
		} else if (refProduto.charAt(i) == '=') {
		    i++;
		    while (i < refProduto.length()) {
			valProduto = valProduto + refProduto.charAt(i);
			i++;
		    }
		}
		i++;
	    }
	    // Trecho de código que separa a referencia do produto do seu respectivo valor
	    produtosOrcamento.addElement(new RegistroProdutos(nomeProduto.trim(), Float.parseFloat(valProduto)));

	    somaTotalProdutos += Float.parseFloat(valProduto);

	    //System.out.println("Produto");
	    //System.out.println("Referencia = " + ((RegistroProdutos) produtosOrcamento.lastElement()).getReferenciaProduto() );
	    //System.out.println("Valor = " + ((RegistroProdutos) produtosOrcamento.lastElement()).getValorUnitarioProduto() );

	    criaListOrcamento();

	} else if (c == commandOK && (listConsultaProdutos == null ? false : d.getTitle().equals(listConsultaProdutos.getTitle()))) {
	    // Direciona a tela para observar os valores do orçamento
	    criaListOrcamento();
	    listOrcamento.setSelectedIndex(3, true);

	} else if (c == commandListarProdutos && (listConsultaProdutos == null ? false : d.getTitle().equals(listConsultaProdutos.getTitle()))) {
	    criaListProdutosCadastrados();
	} /** Consulta Produtos
	 *  LIST CONSULTA PRODUTOS CADASTRADOS
	 */
	else if (c == commandOK && (listProdutosCadastrados == null ? false : d.getTitle().equals(listProdutosCadastrados.getTitle()))) {
	    criaListConsultaProduto(produtos);
	}

    }
}


