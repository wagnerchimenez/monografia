package utilitarios;

import javax.swing.JFrame;

/**
 *
 * @author Wagner
 */
public class Window_Standard extends javax.swing.JFrame {

    /**
     *  Método responsável por setar as propriedades da Janela
     *  Visualização
     *  Título
     *  Fechamento e
     *  Localização
     */
    public void set_Window(String name) {
        setVisible(true);
        setTitle(name);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); /* Só fecha esta janela */
        setLocationRelativeTo(null);/* Inicia a janela no centro da tela */
    }

    /**
     *  Método responsável por fazer uma Padronização da Janela
     *  Seta os componentes da Janela
     *  quando a mesma é Instanciada
     */
    public void set_Standard() {
    }

    /**
     *  Método responsável por adicionar Evento aos Botões
     *  da Janela, para que els possam executar uma operação
     *  ao serem clicados
     */
    public void add_Event_Button() {

    }

    /**
     *  Método responsável por limpar o conteúdo
     *  dos componentes da Janela
     *  como TextField, ComboBox, etc...
     */
    public void clean(String l) {
    }

    /**
     *  Método responsável por Habilitar e Desabilitar
     *  os componentes da Janela para Edição
     */
    public void disables_Enables(boolean check) {
    }
}
