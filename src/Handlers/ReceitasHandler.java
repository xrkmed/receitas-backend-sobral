package Handlers;

import Models.Receita;
import DAO.ReceitasDAO;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Handler responsável por gerenciar requisições relacionadas à lista de receitas
 * Lida com as rotas /receitas (GET e POST)
 */
public class ReceitasHandler extends BaseHandler {
    // DAO (Data Access Object) que gerencia o acesso aos dados das receitas
    private final ReceitasDAO receitasDAO;

    public ReceitasHandler() {
        this.receitasDAO = ReceitasDAO.getInstance();
    }

    /**
     * Processa requisições GET para /receitas
     * Retorna a lista completa de todas as receitas
     */
    @Override
    protected void handleGet(HttpExchange exchange, String path) throws IOException {
        System.out.println("Handling GET request for /receitas");
        // Busca todas as receitas no banco de dados
        List<Receita> receitas = receitasDAO.listarTodas();
        System.out.println("Found " + receitas.size() + " recipes");
        
        // Começa a construir a resposta JSON
        StringBuilder response = new StringBuilder("[");
        
        // Para cada receita, adiciona seus dados ao JSON
        for (Receita receita : receitas) {
            System.out.println("Processing recipe: " + receita.getNome());
            response.append("{")
                   .append("\"id\":\"").append(receita.getId()).append("\",")
                   .append("\"nome\":\"").append(receita.getNome()).append("\",")
                   .append("\"descricao\":\"").append(receita.getDescricao()).append("\",")
                   .append("\"ingredientes\":[");
            
            // Adiciona cada ingrediente da receita
            for (String ingrediente : receita.getIngredientes()) {
                response.append("\"").append(ingrediente).append("\",");
            }
            // Remove a vírgula extra do último ingrediente
            if (!receita.getIngredientes().isEmpty()) {
                response.deleteCharAt(response.length() - 1);
            }
            response.append("]},");
        }
        
        // Remove a vírgula extra da última receita
        if (!receitas.isEmpty()) {
            response.deleteCharAt(response.length() - 1);
        }
        response.append("]");
        
        // Envia a resposta
        String finalResponse = response.toString();
        System.out.println("Sending response: " + finalResponse);
        sendResponse(exchange, 200, finalResponse);
    }

    /**
     * Processa requisições POST para /receitas
     * Cria uma nova receita
     */
    @Override
    protected void handlePost(HttpExchange exchange, String path) throws IOException {
        // Em uma aplicação real, aqui seria feita a leitura do JSON enviado
        // Por simplicidade, estamos criando uma receita com dados fixos
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Farinha");
        ingredientes.add("Ovos");
        ingredientes.add("Leite");
        
        // Cria uma nova receita
        Receita novaReceita = new Receita(
            "Bolo Simples",
            "Um delicioso bolo caseiro",
            ingredientes
        );
        
        // Salva a receita no banco de dados
        novaReceita = receitasDAO.criar(novaReceita);
        
        // Retorna o ID da receita criada
        String response = "{\"id\":\"" + novaReceita.getId() + "\"}";
        sendResponse(exchange, 201, response);
    }
}
