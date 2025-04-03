package Controllers;

import Handlers.ReceitasHandler;
import Handlers.ReceitaHandler;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

/**
 * Classe responsável por configurar e gerenciar todas as rotas da aplicação
 * Define quais URLs serão atendidas e por quais handlers
 */
public class Router {
    // Servidor HTTP que vai processar as requisições
    private final HttpServer server;

    /**
     * Cria um novo router na porta especificada
     * @param port Porta onde o servidor vai escutar
     */
    public Router(int port) throws Exception {
        // Cria o servidor HTTP na porta especificada
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        // Configura as rotas
        setupRoutes();
    }

    /**
     * Configura todas as rotas da aplicação
     * Cada rota é associada a um handler específico
     */
    private void setupRoutes() {
        // Rota para a coleção de receitas (/receitas)
        server.createContext("/receitas", new ReceitasHandler());
        
        // Rota para uma receita específica (/receitas/{id})
        server.createContext("/receitas/", new ReceitaHandler());
    }

    /**
     * Inicia o servidor HTTP
     * A partir deste momento, o servidor começa a escutar requisições
     */
    public void start() {
        server.start();
        System.out.println("Server started on port " + server.getAddress().getPort());
    }
} 