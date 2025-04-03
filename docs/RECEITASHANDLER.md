# O que é o ReceitasHandler?

O ReceitasHandler é como o chef responsável pelo cardápio do restaurante. Ele cuida de todas as receitas de uma vez.

## O que ele faz?

1. **Mostra todas as receitas**
   - Quando alguém pede para ver o cardápio (GET /receitas)
   - Ele pega todas as receitas do livro (DAO)
   - Organiza elas em uma lista bonita (JSON)

2. **Adiciona novas receitas**
   - Quando alguém quer adicionar uma receita (POST /receitas)
   - Ele pega os ingredientes e instruções
   - Adiciona no livro de receitas (DAO)

## Exemplo Prático

Imagine que você é um cliente no restaurante:

1. Você pede para ver o cardápio:
   - O ReceitasHandler vai até o livro de receitas
   - Pega todas as receitas
   - Te mostra uma lista bonita

2. Você quer adicionar uma nova receita:
   - Diz os ingredientes e como fazer
   - O ReceitasHandler anota no livro
   - Agora todos podem ver sua receita

## Como ele funciona no código?

```java
public class ReceitasHandler extends BaseHandler {
    private final ReceitasDAO receitasDAO;

    public ReceitasHandler() {
        this.receitasDAO = ReceitasDAO.getInstance();
    }

    @Override
    protected void handleGet(HttpExchange exchange, String path) throws IOException {
        // Pega todas as receitas
        List<Receita> receitas = receitasDAO.listarTodas();
        
        // Prepara uma lista bonita (JSON)
        StringBuilder response = new StringBuilder("[");
        for (Receita receita : receitas) {
            response.append("{")
                   .append("\"id\":\"").append(receita.getId()).append("\",")
                   .append("\"nome\":\"").append(receita.getNome()).append("\",")
                   .append("\"descricao\":\"").append(receita.getDescricao()).append("\"")
                   .append("},");
        }
        // ... envia a resposta
    }
}
```

## Por que ele é importante?

1. **Organização**
   - Cuida de todas as receitas de uma vez
   - Mantém o cardápio organizado

2. **Facilidade**
   - Fácil ver todas as receitas
   - Fácil adicionar novas receitas

3. **Consistência**
   - Todas as receitas são mostradas do mesmo jeito
   - Todas as novas receitas são adicionadas da mesma forma

## Exemplo de Uso

Quando você acessa:
- `GET /receitas` -> Vê todas as receitas
- `POST /receitas` -> Adiciona uma nova receita

É como ter um chef especializado em gerenciar todo o cardápio do restaurante! 