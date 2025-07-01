package utilitarios;

/**
 *
 * @author Wagner
 */
public interface Manipulation_of_Records {

    /**
     *  Método responsável por Preparar os componentes da Janela
     *  para inserção de um novo registro
     */
    public void new_Record();

    /**
     *  Método responsável por Gravar registro
     *  no Banco de Dados da Aplicação
     */
    public void save_Record();

    /**
     *  Método responsável por Cancelar uma operação
     *  na Janela da Aplicação
     */
    public void cancel();

    /**
     *  Método responsável por Alterar um registro
     *  do Banco de Dados
     */
    public void change_Record();
    public void change_Record(int line);

    /**
     *  Método responsável por Apagar um registro
     *  no Banco de Dados da Aplicação
     */
    public void delete_Record();
    public void delete_Record(int line);

    /**
     *  Método responsável por Buscar um registro
     *  no Banco de Dados da Aplicação
     */
    public void search_Record();
}
