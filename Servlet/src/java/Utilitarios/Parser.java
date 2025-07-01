package Utilitarios;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author Wagner
 */
public abstract class Parser {

    private static int codOrcamento;

    public static Boolean parserCliente(String str) {
        //Inicia o XMLParser
        Boolean retorno = true;
        try {
            KXmlParser parser = new KXmlParser(); //instância da classe KXmlParser
            parser.setInput(new InputStreamReader(new ByteArrayInputStream(str.getBytes())));


            parser.nextTag();
            //Posiciona na tag <clientes>
            parser.require(XmlPullParser.START_TAG, null, "cliente");
            //posiciona o parserCliente na tag desejada, neste caso: <clientes>.

            //Enquanto é diferente de END_TAG
            //while (parserCliente.nextTag() != XmlPullParser.END_TAG) {

            //Posiciona na tag <pessoa>

            parserCadastraCliente(parser);//para cada cliente encontrada é chamado o método
            //parserCadastraCliente para ler os seus elementos.

            //}

            // criaListConsultaClienteResultado(clientes);

            parser.require(XmlPullParser.END_TAG, null, "cliente");
            parser.next();
            parser.require(XmlPullParser.END_DOCUMENT, null, null);
        } catch (Exception ex) {
            retorno = false;
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    public static void parserCadastraCliente(KXmlParser parser) throws Exception {

        String name[] = new String[16];
        String text[] = new String[16];
        while (parser.nextTag() != XmlPullParser.END_TAG) {
            for (int i = 0; i < 16; i++) {
                parser.require(XmlPullParser.START_TAG, null, null);
                name[i] = parser.getName();
                text[i] = parser.nextText();
                System.out.println(i + " : " + text[i]);

                parser.require(XmlPullParser.END_TAG, null, name[i]);
                if (i < 15) {
                    parser.nextTag();
                }
            }

            Conexao connection = new Conexao();

            connection.Update("INSERT INTO CLIENTE ( NOME, SEXO, RG, CPF, ENDERECO, BAIRRO, CIDADE, ESTADO, CEP, FONE, FAX, CELULAR, CONTATO, EMAIL, DATA, OBSERVACAO ) VALUES (' "
                    + text[0] + "','"
                    + text[1] + "','"
                    + text[2] + "','"
                    + text[3] + "','"
                    + text[4] + "','"
                    + text[5] + "','"
                    + text[6] + "','"
                    + text[7] + "','"
                    + text[8] + "','"
                    + text[9] + "','"
                    + text[10] + "','"
                    + text[11] + "','"
                    + text[12] + "','"
                    + text[13] + "','"
                    + Conexao.formataData(text[14]) + "','"
                    + text[15]
                    + "' ) ");
        }
    }

    public static Boolean parserOrcamento(String str) {
        //Inicia o XMLParser
        Boolean retorno = true;
        try {
            KXmlParser parser = new KXmlParser(); //instância da classe KXmlParser
            parser.setInput(new InputStreamReader(new ByteArrayInputStream(str.getBytes())));

            parser.nextTag();

            parser.require(XmlPullParser.START_TAG, null, "orcamento");

            parser.nextTag();

            ////////////////////////////////////////////////////////////////////
            // Dados
            parser.require(XmlPullParser.START_TAG, null, "dados");
            parserCadastraDadosOrcamento(parser);
            parser.require(XmlPullParser.END_TAG, null, "dados");

            parser.nextTag();

            parser.require(XmlPullParser.START_TAG, null, "projetos");

            while (parser.nextTag() != XmlPullParser.END_TAG) {

                parser.require(XmlPullParser.START_TAG, null, "projeto");
                parserCadastraProjetosOrcamento(parser);
                parser.require(XmlPullParser.END_TAG, null, "projeto");

            }
            parser.require(XmlPullParser.END_TAG, null, "projetos");

            parser.nextTag();

            parser.require(XmlPullParser.START_TAG, null, "produtos");

            while (parser.nextTag() != XmlPullParser.END_TAG) {

                parser.require(XmlPullParser.START_TAG, null, "produto");
                parserCadastraProdutosOrcamento(parser);
                parser.require(XmlPullParser.END_TAG, null, "produto");

            }

            parser.require(XmlPullParser.END_TAG, null, "produtos");

            parser.nextTag();

            parser.require(XmlPullParser.END_TAG, null, "orcamento");
            parser.nextTag();
            parser.require(XmlPullParser.END_DOCUMENT, null, null);
        } catch (Exception ex) {
            retorno = false;
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    public static void parserCadastraDadosOrcamento(KXmlParser parser) {

        String name[] = new String[11];
        String text[] = new String[11];

        try {
            while (parser.nextTag() != XmlPullParser.END_TAG) {
                for (int i = 0; i < 11; i++) {
                    parser.require(XmlPullParser.START_TAG, null, null);
                    name[i] = parser.getName();
                    text[i] = parser.nextText();

                    parser.require(XmlPullParser.END_TAG, null, name[i]);
                    if (i < 10) {
                        parser.nextTag();
                    }
                }

                Conexao connection = new Conexao();

                connection.Update("INSERT INTO ORCAMENTO_VENDA ( DESCRICAO, OBRA, CLIENTE_CODIGO, FUNCIONARIO_CODIGO, DATA, OBSERVACAO, CONCLUIDO, VALOR_BRUTO, VALOR_DESCONTO , PORC_DESCONTO ,  VALOR_FINAL ) VALUES ( '"
                        + text[0] + "','"
                        + text[1] + "','"
                        + Integer.parseInt(text[2]) + "','"
                        + Integer.parseInt(text[3]) + "','"
                        + Conexao.formataData(text[4]) + "','"
                        + text[5] + "','"
                        + text[6] + "','"
                        + Float.parseFloat(text[7]) + "','"
                        + Float.parseFloat(text[8]) + "','"
                        + Float.parseFloat(text[9]) + "','"
                        + Float.parseFloat(text[10])
                        + "' ) ");

                codOrcamento = Conexao.ReturnKey("select codigo from orcamento_venda where descricao = '" + text[0] + "'");

            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Erro");
        }
    }

    private static void parserCadastraProjetosOrcamento(KXmlParser parser) {
        String name[] = new String[8];
        String text[] = new String[8];
        try {
            while (parser.nextTag() != XmlPullParser.END_TAG) {
                for (int i = 0; i < 8; i++) {
                    parser.require(XmlPullParser.START_TAG, null, null);
                    name[i] = parser.getName();
                    text[i] = parser.nextText();
                    System.out.println(i + " : " + text[i]);

                    parser.require(XmlPullParser.END_TAG, null, name[i]);
                    if (i < 7) {
                        parser.nextTag();
                    }
                }

                Conexao connection = new Conexao();

                int codProjeto, precoVidro;

                codProjeto = Conexao.ReturnKey("Select CODIGO from PROJETO where REFERENCIA = '" + text[0] + "'");

                connection.Update("INSERT INTO PROJETO_ORCAMENTO_VENDA (PROJETO_CODIGO, ORCAMENTO_VENDA_CODIGO, COR, VIDRO, LARGURA, ALTURA,PRECO_VIDRO_M, VALOR_PROJETO ,QUANTIDADE ) VALUES ( '"
                        + codProjeto + "','"
                        + codOrcamento + "','"
                        + "BRONZE" + "','"
                        + text[4] + "','"
                        + Float.parseFloat(text[5]) + "','"
                        + Float.parseFloat(text[6]) + "','"
                        + Float.parseFloat(text[7]) + "','"
                        + Float.parseFloat(text[3]) + "','"
                        + Float.parseFloat(text[2])
                        + "' ) ");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro Cadastrar Projetos");
        }

    }

    private static void parserCadastraProdutosOrcamento(KXmlParser parser) {
        String name[] = new String[2];
        String text[] = new String[2];
        try {
            while (parser.nextTag() != XmlPullParser.END_TAG) {
                for (int i = 0; i < 2; i++) {
                    parser.require(XmlPullParser.START_TAG, null, null);
                    name[i] = parser.getName();
                    text[i] = parser.nextText();
                    System.out.println(i + " : " + text[i]);

                    parser.require(XmlPullParser.END_TAG, null, name[i]);
                    if (i < 1) {
                        parser.nextTag();
                    }
                }

                Conexao connection = new Conexao();

                int codProduto;

                codProduto = Conexao.ReturnKey("Select CODIGO from PRODUTO where REFERENCIA = '" + text[0] + "'");


                connection.Update("INSERT INTO PRODUTO_ORCAMENTO_VENDA (ORCAMENTO_VENDA_CODIGO, QUANTIDADE_PRODUTO ,PRODUTO_CODIGO, VALOR_UNITARIO, VALOR_TOTAL ) VALUES ( '"
                        + codOrcamento + "','"
                        + 1 + "','"
                        + codProduto + "','"
                        + Float.parseFloat(text[1]) + "','"
			+ Float.parseFloat(text[1])
                        + "' ) ");
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro Cadastrar Produtos");
        }
    }
}
