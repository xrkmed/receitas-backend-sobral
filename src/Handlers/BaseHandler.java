package Handlers;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe base que define como lidar com requisições HTTP
 * Todas as outras classes de handler herdam desta
 */
public abstract class BaseHandler implements HttpHandler {
    // Mapa que associa cada método HTTP (GET, POST, etc) com sua função de tratamento
    private final Map<String, RouteHandler> routeHandlers = new HashMap<>();
    
    // Construtor que inicializa os handlers para cada método HTTP
    protected BaseHandler() {
        routeHandlers.put("GET", this::handleGet);
        routeHandlers.put("POST", this::handlePost);
        routeHandlers.put("PUT", this::handlePut);
        routeHandlers.put("DELETE", this::handleDelete);
    }
    
    /**
     * Método principal que recebe todas as requisições HTTP
     * Decide qual método usar baseado no tipo da requisição (GET, POST, etc)
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod(); // Pega o método da requisição (GET, POST, etc)
        String path = exchange.getRequestURI().getPath(); // Pega o caminho da URL
        
        try {
            // Procura o handler apropriado para o método da requisição
            RouteHandler handler = routeHandlers.get(method);
            if (handler != null) {
                handler.handle(exchange, path);
            } else {
                // Se não encontrar um handler, retorna erro 405 (Método não permitido)
                sendErrorResponse(exchange, 405, "Method Not Allowed");
            }
        } catch (Exception e) {
            // Se ocorrer qualquer erro, retorna erro 500
            handleError(exchange, e);
        }
    }

    /**
     * Envia uma resposta HTTP com o status code e conteúdo especificados
     */
    protected void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        System.out.println("Sending response with status code: " + statusCode);
        // Configura os cabeçalhos da resposta
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        // Envia os cabeçalhos com o status code e tamanho da resposta
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        // Escreve o conteúdo da resposta
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    /**
     * Envia uma resposta de erro com mensagem personalizada
     */
    protected void sendErrorResponse(HttpExchange exchange, int statusCode, String message) throws IOException {
        String response = "{\"error\":\"" + message + "\"}";
        sendResponse(exchange, statusCode, response);
    }

    /**
     * Trata erros genéricos, enviando erro 500
     */
    protected void handleError(HttpExchange exchange, Exception e) throws IOException {
        sendErrorResponse(exchange, 500, e.getMessage());
    }

    // Métodos que devem ser implementados pelas classes filhas
    protected abstract void handleGet(HttpExchange exchange, String path) throws IOException;
    protected abstract void handlePost(HttpExchange exchange, String path) throws IOException;
    
    // Métodos com implementação padrão (retornam erro 405)
    protected void handlePut(HttpExchange exchange, String path) throws IOException {
        sendErrorResponse(exchange, 405, "Method Not Allowed");
    }
    protected void handleDelete(HttpExchange exchange, String path) throws IOException {
        sendErrorResponse(exchange, 405, "Method Not Allowed");
    }

    // Interface funcional que define como um handler deve processar uma requisição
    @FunctionalInterface
    private interface RouteHandler {
        void handle(HttpExchange exchange, String path) throws IOException;
    }
} 