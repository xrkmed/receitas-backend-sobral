import Controllers.Router;

/**
 * Classe principal da aplicação
 * Responsável por iniciar o servidor e configurar o router
 */
public class App {
    /**
     * Método principal que inicia a aplicação
     * @param args Argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) throws Exception {
        // Cria um novo router na porta 6678
        Router router = new Router(6678);
        // Inicia o servidor
        router.start();
    }
}
