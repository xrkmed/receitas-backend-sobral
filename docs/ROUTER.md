# O que é o Router?

O Router é como o maitre de um restaurante. Ele é responsável por receber os clientes e levá-los para a mesa certa.

## O que ele faz?

1. **Recebe os pedidos**
   - Quando alguém acessa o site, ele é o primeiro a receber
   - Ele analisa o que a pessoa quer (qual URL ela acessou)

2. **Direciona para o lugar certo**
   - Se você quer ver todas as receitas, ele te leva para o ReceitasHandler
   - Se você quer ver uma receita específica, ele te leva para o ReceitaHandler

3. **Configura o restaurante**
   - Define quais são as mesas disponíveis (rotas)
   - Atribui um garçom (handler) para cada mesa

## Exemplo Prático

Imagine que você entra em um restaurante:

1. Você diz ao maitre (Router) o que quer:
   - "Quero ver o cardápio" (acessa `/receitas`)
   - "Quero ver a receita do bolo" (acessa `/receitas/1`)

2. O maitre te leva para a mesa certa:
   - Cardápio -> ReceitasHandler
   - Receita específica -> ReceitaHandler

## Como ele funciona no código?

```java
public class Router {
    private final HttpServer server;

    public Router(int port) throws Exception {
        // Cria o servidor na porta especificada
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        // Configura as rotas
        setupRoutes();
    }

    private void setupRoutes() {
        // Quando alguém acessa /receitas
        server.createContext("/receitas", new ReceitasHandler());
        
        // Quando alguém acessa /receitas/algum-número
        server.createContext("/receitas/", new ReceitaHandler());
    }
}
```

## Por que ele é importante?

1. **Organização**
   - Mantém tudo organizado
   - Cada pedido vai para o lugar certo

2. **Flexibilidade**
   - Fácil adicionar novas rotas
   - Fácil mudar quem cuida de cada rota

3. **Segurança**
   - Controla quem pode acessar o quê
   - Garante que cada pedido vá para o handler certo

## Exemplo de Uso

Quando você acessa:
- `localhost:6678/receitas` -> Router te leva para o ReceitasHandler
- `localhost:6678/receitas/1` -> Router te leva para o ReceitaHandler

É como ter um maitre que sempre sabe exatamente para onde te levar no restaurante! 