# O que é o DAO?

O DAO (Data Access Object) é como o livro de receitas do restaurante. Ele é responsável por guardar e gerenciar todas as receitas.

## O que ele faz?

1. **Guarda as receitas**
   - Armazena todas as receitas em um lugar seguro
   - Mantém as receitas organizadas

2. **Permite acesso às receitas**
   - Os chefs (handlers) podem pegar receitas quando precisam
   - Pode buscar uma receita específica
   - Pode listar todas as receitas

3. **Gerencia as receitas**
   - Adiciona novas receitas
   - Atualiza receitas existentes
   - Remove receitas

## Exemplo Prático

Imagine que você é um chef no restaurante:

1. Você precisa de uma receita:
   - Vai até o livro (DAO)
   - Pega a receita que precisa

2. Você tem uma nova receita:
   - Vai até o livro
   - Adiciona a nova receita

3. Você quer mudar uma receita:
   - Vai até o livro
   - Atualiza a receita existente

## Como ele funciona no código?

```java
public class ReceitasDAO {
    // Lista que guarda todas as receitas
    private final List<Receita> receitas = new ArrayList<>();

    // Método para pegar todas as receitas
    public List<Receita> listarTodas() {
        return new ArrayList<>(receitas);
    }

    // Método para buscar uma receita específica
    public Receita buscarPorId(String id) {
        for (Receita receita : receitas) {
            if (receita.getId().equals(id)) {
                return receita;
            }
        }
        return null;
    }

    // Método para adicionar uma nova receita
    public Receita criar(Receita receita) {
        receitas.add(receita);
        return receita;
    }
}
```

## Por que ele é importante?

1. **Organização**
   - Mantém todas as receitas em um só lugar
   - Facilita encontrar o que precisa

2. **Segurança**
   - Controla quem pode acessar as receitas
   - Protege as receitas de mudanças indevidas

3. **Manutenção**
   - Fácil adicionar novas funcionalidades
   - Fácil mudar como as receitas são guardadas

## Exemplo de Uso

Quando um handler precisa de uma receita:

```java
// No ReceitaHandler
Receita receita = receitasDAO.buscarPorId("1");

// No ReceitasHandler
List<Receita> todasReceitas = receitasDAO.listarTodas();
```

É como ter um livro de receitas bem organizado que todos os chefs podem usar! 