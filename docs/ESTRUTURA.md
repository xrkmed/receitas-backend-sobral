# Estrutura do Projeto de Receitas

Este é um projeto de backend simples que gerencia receitas. Vamos entender como ele funciona de forma bem básica.

## O que é um Backend?

Imagine que você está em um restaurante. O backend é como a cozinha do restaurante:
- O cliente (frontend) faz o pedido
- A cozinha (backend) prepara o prato
- O garçom entrega o prato pronto

No nosso caso:
- O cliente é quem acessa o site
- A cozinha é nosso servidor
- O garçom é a internet

## Partes do Projeto

1. **App.java** - É a porta de entrada do restaurante. É aqui que tudo começa.

2. **Router** - É o maitre do restaurante. Ele decide para onde cada pedido vai:
   - Se você pedir uma lista de receitas, ele te leva para a mesa certa
   - Se você pedir uma receita específica, ele te leva para outra mesa

3. **Handlers** - São os chefs de cozinha:
   - BaseHandler: É o chef principal que ensina os outros chefs
   - ReceitasHandler: É o chef que lida com todas as receitas
   - ReceitaHandler: É o chef que lida com uma receita específica

4. **DAO** - É o livro de receitas:
   - Guarda todas as receitas
   - Permite adicionar novas receitas
   - Permite procurar receitas específicas

5. **Models** - São os ingredientes:
   - Define como uma receita deve ser
   - Diz quais informações uma receita precisa ter

## Como Funciona?

1. Alguém acessa o site (faz um pedido)
2. O Router (maitre) recebe o pedido
3. O Router decide qual Handler (chef) vai preparar
4. O Handler pega as informações no DAO (livro de receitas)
5. O Handler prepara a resposta (prato)
6. A resposta é enviada de volta (prato é servido)

## Exemplo Prático

Quando você acessa `localhost:6678/receitas`:

1. O Router vê que você quer ver receitas
2. Ele chama o ReceitasHandler
3. O ReceitasHandler pega todas as receitas no DAO
4. O ReceitasHandler prepara uma lista bonita
5. Você recebe a lista de receitas

É como pedir um cardápio no restaurante! 