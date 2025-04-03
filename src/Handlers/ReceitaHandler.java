package Handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import DAO.ReceitasDAO;
import Models.Receita;

/**
 * Handler responsável por gerenciar requisições relacionadas a uma receita específica
 * Lida com as rotas /receitas/{id} (GET, PUT e DELETE)
 */
public class ReceitaHandler extends BaseHandler {
    // DAO (Data Access Object) que gerencia o acesso aos dados das receitas
    private final ReceitasDAO receitasDAO;

    public ReceitaHandler() {
        this.receitasDAO = ReceitasDAO.getInstance();
    }

    /**
     * Processa requisições GET para /receitas/{id}
     * Retorna os dados de uma receita específica
     */
    @Override
    protected void handleGet(HttpExchange exchange, String path) throws IOException {
        // Extrai o ID da receita da URL
        String id = extractIdFromPath(path);
        
        // Busca a receita no banco de dados
        Receita receita = receitasDAO.buscarPorId(id);
        
        // Se não encontrar a receita, retorna erro 404
        if (receita == null) {
            sendErrorResponse(exchange, 404, "Receita não encontrada");
            return;
        }
        
        // Constrói a resposta JSON com os dados da receita
        StringBuilder response = new StringBuilder("{")
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
        response.append("]}");
        
        // Envia a resposta
        sendResponse(exchange, 200, response.toString());
    }

    /**
     * Processa requisições POST para /receitas/{id}
     * Não é permitido criar receitas com ID específico
     */
    @Override
    protected void handlePost(HttpExchange exchange, String path) throws IOException {
        sendErrorResponse(exchange, 405, "Method Not Allowed");
    }

    /**
     * Processa requisições PUT para /receitas/{id}
     * Atualiza os dados de uma receita existente
     */
    @Override
    protected void handlePut(HttpExchange exchange, String path) throws IOException {
        // Extrai o ID da receita da URL
        String id = extractIdFromPath(path);
        
        // Em uma aplicação real, aqui seria feita a leitura do JSON enviado
        // Por simplicidade, estamos criando uma receita com dados fixos
        List<String> ingredientes = new ArrayList<>();
        ingredientes.add("Farinha");
        ingredientes.add("Ovos");
        ingredientes.add("Leite");
        ingredientes.add("Açúcar");
        
        // Cria uma nova versão da receita com os dados atualizados
        Receita receitaAtualizada = new Receita(
            "Bolo Simples Atualizado",
            "Um delicioso bolo caseiro com açúcar",
            ingredientes
        );
        receitaAtualizada.setId(id);
        
        // Tenta atualizar a receita no banco de dados
        boolean sucesso = receitasDAO.atualizar(receitaAtualizada);
        
        // Se não encontrar a receita, retorna erro 404
        if (!sucesso) {
            sendErrorResponse(exchange, 404, "Receita não encontrada");
            return;
        }
        
        // Retorna mensagem de sucesso
        sendResponse(exchange, 200, "{\"message\":\"Receita atualizada com sucesso\"}");
    }

    /**
     * Processa requisições DELETE para /receitas/{id}
     * Remove uma receita do banco de dados
     */
    @Override
    protected void handleDelete(HttpExchange exchange, String path) throws IOException {
        // Extrai o ID da receita da URL
        String id = extractIdFromPath(path);
        
        // Tenta deletar a receita do banco de dados
        boolean sucesso = receitasDAO.deletar(id);
        
        // Se não encontrar a receita, retorna erro 404
        if (!sucesso) {
            sendErrorResponse(exchange, 404, "Receita não encontrada");
            return;
        }
        
        // Retorna mensagem de sucesso
        sendResponse(exchange, 200, "{\"message\":\"Receita deletada com sucesso\"}");
    }

    /**
     * Extrai o ID da receita da URL
     * Exemplo: /receitas/123 -> retorna "123"
     */
    private String extractIdFromPath(String path) {
        return path.substring("/receitas/".length());
    }
}