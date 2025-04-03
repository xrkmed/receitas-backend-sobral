# O que é o App?

O App é como a porta de entrada do restaurante. É aqui que tudo começa, onde o servidor é iniciado e configurado.

## O que ele faz?

1. **Inicia o servidor**
   - Cria o Router (maitre)
   - Configura a porta onde o servidor vai escutar

2. **Configura o ambiente**
   - Prepara tudo para receber clientes
   - Garante que tudo está funcionando

3. **Mantém o servidor rodando**
   - Fica esperando por pedidos
   - Garante que o restaurante está aberto

## Exemplo Prático

Imagine que você está abrindo um restaurante:

1. Você precisa:
   - Abrir as portas
   - Ligar as luzes
   - Preparar a cozinha

2. O App faz isso tudo:
   - Inicia o servidor (abre as portas)
   - Configura o Router (prepara o maitre)
   - Deixa tudo pronto para receber clientes

## Como ele funciona no código?

```java
public class App {
    public static void main(String[] args) {
        try {
            // Cria um novo Router na porta 6678
            Router router = new Router(6678);
            
            // Inicia o servidor
            router.start();
            
            // Mostra que o servidor está rodando
            System.out.println("Servidor iniciado na porta 6678");
        } catch (Exception e) {
            // Se algo der errado, mostra o erro
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}
```

## Por que ele é importante?

1. **Inicialização**
   - É o ponto de partida do sistema
   - Garante que tudo está configurado corretamente

2. **Controle**
   - Gerencia o ciclo de vida do servidor
   - Controla quando o servidor começa e termina

3. **Simplicidade**
   - Mantém o código principal limpo e simples
   - Facilita entender como o sistema começa

## Exemplo de Uso

Quando você quer iniciar o servidor:

1. Compila o código
2. Roda o App
3. O servidor começa a escutar na porta 6678

É como ter um botão que liga todo o restaurante de uma vez! 