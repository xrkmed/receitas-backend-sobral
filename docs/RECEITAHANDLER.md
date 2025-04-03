# O que é o ReceitaHandler?

O ReceitaHandler é como o chef especialista em uma receita específica. Ele cuida de uma única receita por vez.

## O que ele faz?

1. **Mostra uma receita específica**
   - Quando alguém pede para ver uma receita (GET /receitas/1)
   - Ele vai no livro (DAO) e pega só aquela receita
   - Mostra todos os detalhes dela

2. **Atualiza uma receita**
   - Quando alguém quer mudar uma receita (PUT /receitas/1)
   - Ele pega as novas informações
   - Atualiza no livro de receitas

3. **Remove uma receita**
   - Quando alguém quer deletar uma receita (DELETE /receitas/1)
   - Ele remove do livro de receitas

## Exemplo Prático

Imagine que você é um cliente no restaurante:

1. Você pede para ver a receita do bolo:
   - O ReceitaHandler vai até o livro
   - Pega só a receita do bolo
   - Te mostra todos os detalhes

2. Você quer mudar a receita:
   - Diz as novas instruções
   - O ReceitaHandler atualiza no livro

3. Você quer remover a receita:
   - O ReceitaHandler remove do livro

## Como ele funciona no código?

```java
public class ReceitaHandler extends BaseHandler {
    private final ReceitasDAO receitasDAO;

    public ReceitaHandler() {
        this.receitasDAO = ReceitasDAO.getInstance();
    }

    @Override
    protected void handleGet(HttpExchange exchange, String path) throws IOException {
        // Pega o ID da receita da URL
        String id = extractIdFromPath(path);
        
        // Busca a receita no livro
        Receita receita = receitasDAO.buscarPorId(id);
        
        if (receita != null) {
            // Prepara uma resposta bonita (JSON)
            String response = "{" +
                "\"id\":\"" + receita.getId() + "\"," +
                "\"nome\":\"" + receita.getNome() + "\"," +
                "\"descricao\":\"" + receita.getDescricao() + "\"" +
                "}";
            // ... envia a resposta
        } else {
            // ... envia erro 404
        }
    }
}
```

## Por que ele é importante?

1. **Especialização**
   - Foca em uma receita por vez
   - Conhece todos os detalhes da receita

2. **Controle**
   - Pode atualizar uma receita específica
   - Pode remover uma receita específica

3. **Precisão**
   - Garante que cada operação seja feita na receita certa
   - Evita confusão entre receitas

## Exemplo de Uso

Quando você acessa:
- `GET /receitas/1` -> Vê a receita com ID 1
- `PUT /receitas/1` -> Atualiza a receita com ID 1
- `DELETE /receitas/1` -> Remove a receita com ID 1

É como ter um chef especialista que conhece cada receita individualmente! 