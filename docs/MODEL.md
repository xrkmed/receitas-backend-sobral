# O que é o Model?

O Model é como a receita em si. Ele define como uma receita deve ser, quais informações ela precisa ter.

## O que ele faz?

1. **Define a estrutura**
   - Diz quais informações uma receita precisa ter
   - Define o formato de cada informação

2. **Guarda os dados**
   - Armazena o nome da receita
   - Armazena a descrição
   - Armazena os ingredientes

3. **Permite acesso aos dados**
   - Métodos para pegar o nome
   - Métodos para pegar a descrição
   - Métodos para pegar os ingredientes

## Exemplo Prático

Imagine que você está escrevendo uma receita:

1. Você precisa definir:
   - Nome da receita
   - Descrição do que é
   - Lista de ingredientes

2. O Model é como um formulário que diz:
   - "Toda receita precisa ter um nome"
   - "Toda receita precisa ter uma descrição"
   - "Toda receita precisa ter ingredientes"

## Como ele funciona no código?

```java
public class Receita {
    // Informações que toda receita precisa ter
    private String id;
    private String nome;
    private String descricao;
    private List<String> ingredientes;

    // Construtor que cria uma nova receita
    public Receita(String nome, String descricao, List<String> ingredientes) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.descricao = descricao;
        this.ingredientes = ingredientes;
    }

    // Métodos para pegar as informações
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }
}
```

## Por que ele é importante?

1. **Consistência**
   - Garante que todas as receitas tenham as mesmas informações
   - Evita receitas incompletas

2. **Organização**
   - Mantém os dados organizados
   - Facilita encontrar o que precisa

3. **Segurança**
   - Controla como os dados podem ser acessados
   - Protege os dados de mudanças indevidas

## Exemplo de Uso

Quando você quer criar uma nova receita:

```java
// Lista de ingredientes
List<String> ingredientes = new ArrayList<>();
ingredientes.add("Farinha");
ingredientes.add("Ovos");
ingredientes.add("Leite");

// Cria uma nova receita
Receita bolo = new Receita(
    "Bolo Simples",
    "Um delicioso bolo caseiro",
    ingredientes
);
```

É como ter um formulário padrão que garante que todas as receitas tenham as informações necessárias! 