
package principal;

import cadastros.Cidades;
import cadastros.Clientes;
import cadastros.Cores;
import cadastros.Estados;
import cadastros.Fornecedores;
import cadastros.Funcionarios;
import cadastros.Produtos;
import cadastros.Projetos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import pedidos.orcamento_venda;
import utilitarios.Configuracoes;

public class form_principal extends javax.swing.JFrame implements ActionListener {

    public form_principal() {
        initComponents();

        set_janela(" Glass Window ");
        add_event_menus();
    }

    public void set_janela(String nome_janela) {
	// Setando as propriedades da janela
	setVisible(true);
	setTitle(nome_janela);
        //setResizable(false);
        //setLocationRelativeTo(null);// Inicia a janela no centro da tela
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Só fecha esta janela
    }

    public void add_event_menus(){
        /* Menu Cadastros */
        estado.addActionListener(this);
        cidade.addActionListener(this);
        cliente.addActionListener(this);
        funcionario.addActionListener(this);
        fornecedor.addActionListener(this);
        cor.addActionListener(this);
        produto.addActionListener(this);
        projeto.addActionListener(this);
        /* Menu Orçamentos / Vendas */
        orcamentos.addActionListener(this);

        /* Botões de Atalho*/
        Button_Cadastro_Clientes.addActionListener(this);
        Button_Cadastro_Fornecedor.addActionListener(this);
        Button_Cadastro_Produto.addActionListener(this);
        Button_Cadastro_Projeto.addActionListener(this);
        Botao_Orcamento.addActionListener(this);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Button_Cadastro_Clientes = new javax.swing.JButton();
        Button_Cadastro_Fornecedor = new javax.swing.JButton();
        Button_Cadastro_Produto = new javax.swing.JButton();
        Button_Cadastro_Projeto = new javax.swing.JButton();
        Botao_Orcamento = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        estado = new javax.swing.JMenuItem();
        cidade = new javax.swing.JMenuItem();
        cliente = new javax.swing.JMenuItem();
        funcionario = new javax.swing.JMenuItem();
        fornecedor = new javax.swing.JMenuItem();
        cor = new javax.swing.JMenuItem();
        produto = new javax.swing.JMenuItem();
        projeto = new javax.swing.JMenuItem();
        pedidos = new javax.swing.JMenu();
        orcamentos = new javax.swing.JMenuItem();
        configuracoes = new javax.swing.JMenu();
        config = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Button_Cadastro_Clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CLIENTES.png"))); // NOI18N
        Button_Cadastro_Clientes.setText("Clientes");
        Button_Cadastro_Clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Cadastro_ClientesActionPerformed(evt);
            }
        });

        Button_Cadastro_Fornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FORNECEDORES.png"))); // NOI18N
        Button_Cadastro_Fornecedor.setText("Fornecedores");

        Button_Cadastro_Produto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/PRODUTOS.png"))); // NOI18N
        Button_Cadastro_Produto.setText("Produtos");

        Button_Cadastro_Projeto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/PROJETOS.png"))); // NOI18N
        Button_Cadastro_Projeto.setText("Projetos");

        Botao_Orcamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ORCAMENTO_VENDA.png"))); // NOI18N
        Botao_Orcamento.setText("Orçamento / Venda");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/configuracoes.png"))); // NOI18N
        jButton1.setText("Configurações");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Button_Cadastro_Clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Cadastro_Fornecedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Cadastro_Produto, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_Cadastro_Projeto, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Botao_Orcamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(249, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Botao_Orcamento, Button_Cadastro_Clientes, Button_Cadastro_Fornecedor, Button_Cadastro_Produto, Button_Cadastro_Projeto, jButton1});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button_Cadastro_Clientes)
                    .addComponent(Button_Cadastro_Fornecedor)
                    .addComponent(Button_Cadastro_Produto)
                    .addComponent(Button_Cadastro_Projeto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Botao_Orcamento)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Botao_Orcamento, Button_Cadastro_Clientes, Button_Cadastro_Fornecedor, Button_Cadastro_Produto, Button_Cadastro_Projeto, jButton1});

        jLabel1.setText("Desenvolvedor: Wagner Lima Chimenez");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/glass.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/GlassWindow.png"))); // NOI18N

        jMenuBar1.setBorder(null);

        jMenu1.setText("  Cadastros");

        estado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ESTADOS.png"))); // NOI18N
        estado.setText("Estados");
        jMenu1.add(estado);

        cidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CIDADES.png"))); // NOI18N
        cidade.setText("Cidades");
        jMenu1.add(cidade);

        cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/CLIENTES.png"))); // NOI18N
        cliente.setText("Clientes");
        jMenu1.add(cliente);

        funcionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FUNCIONARIOS.png"))); // NOI18N
        funcionario.setText("Funcionários");
        jMenu1.add(funcionario);

        fornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FORNECEDORES.png"))); // NOI18N
        fornecedor.setText("Fornecedores");
        jMenu1.add(fornecedor);

        cor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/COR.png"))); // NOI18N
        cor.setText("Cores");
        jMenu1.add(cor);

        produto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/PRODUTOS.png"))); // NOI18N
        produto.setText("Produtos");
        jMenu1.add(produto);

        projeto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/PROJETOS.png"))); // NOI18N
        projeto.setText("Projetos");
        jMenu1.add(projeto);

        jMenuBar1.add(jMenu1);

        pedidos.setText("Pedidos");

        orcamentos.setText("Orçamentos");
        pedidos.add(orcamentos);

        jMenuBar1.add(pedidos);

        configuracoes.setText("Configurações");

        config.setText("Configurações");
        config.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configActionPerformed(evt);
            }
        });
        configuracoes.add(config);

        jMenuBar1.add(configuracoes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(1155, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(148, 148, 148)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(147, 147, 147))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_Cadastro_ClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Cadastro_ClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_Cadastro_ClientesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new Configuracoes();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void configActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configActionPerformed
	// TODO add your handling code here:
	new Configuracoes();
    }//GEN-LAST:event_configActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Botao_Orcamento;
    private javax.swing.JButton Button_Cadastro_Clientes;
    private javax.swing.JButton Button_Cadastro_Fornecedor;
    private javax.swing.JButton Button_Cadastro_Produto;
    private javax.swing.JButton Button_Cadastro_Projeto;
    private javax.swing.JMenuItem cidade;
    private javax.swing.JMenuItem cliente;
    private javax.swing.JMenuItem config;
    private javax.swing.JMenu configuracoes;
    private javax.swing.JMenuItem cor;
    private javax.swing.JMenuItem estado;
    private javax.swing.JMenuItem fornecedor;
    private javax.swing.JMenuItem funcionario;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem orcamentos;
    private javax.swing.JMenu pedidos;
    private javax.swing.JMenuItem produto;
    private javax.swing.JMenuItem projeto;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        if( e.getSource().equals(estado))
        {
            Estados novo_estado = new Estados();
        }
        else if(e.getSource().equals(cidade)){
            Cidades nova_cidadde = new Cidades();
        }
        else if(e.getSource().equals(cliente)){
            Clientes novo_cliente = new Clientes();
        }
        else if(e.getSource().equals(funcionario)){
            Funcionarios novo_funcionario = new Funcionarios();
        }
        else if(e.getSource().equals(fornecedor)){
            Fornecedores novo_fornecedor = new Fornecedores();
        }
        else if(e.getSource().equals(cor)){
            Cores nova_cor = new Cores();
        }
        else if(e.getSource().equals(produto)){
            Produtos novo_produto = new Produtos();
        }
        else if(e.getSource().equals(projeto)){
            Projetos novo_projeto = new Projetos();
        }
        else if(e.getSource().equals(orcamentos)){
            orcamento_venda orcamento = new orcamento_venda();
        }

        // Botões de Atalho
        else if(e.getSource().equals(Button_Cadastro_Clientes)){
            new Clientes();
        }else if(e.getSource().equals(Button_Cadastro_Fornecedor)){
            new Fornecedores();
        }else if(e.getSource().equals(Button_Cadastro_Produto)){
            new Produtos();
        }else if(e.getSource().equals(Button_Cadastro_Projeto)){
            new Projetos();
        }else if(e.getSource().equals(Botao_Orcamento)){
            new orcamento_venda();
        }
    }
}
