package DAO;

import Models.Receita;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ReceitasDAO {
    private static final List<Receita> receitas = new ArrayList<>();
    private static ReceitasDAO instance;

    private ReceitasDAO() {
        // create mock data
        receitas.add(new Receita(UUID.randomUUID().toString(), "Bolo de Chocolate", "Delicioso bolo de chocolate caseiro", Arrays.asList("Chocolate em pó", "Farinha", "Ovos", "Leite", "Açúcar")));
        receitas.add(new Receita(UUID.randomUUID().toString(), "Pão de Queijo", "Tradicional pão de queijo mineiro", Arrays.asList("Polvilho", "Queijo", "Óleo", "Ovos", "Leite")));
        receitas.add(new Receita(UUID.randomUUID().toString(), "Brigadeiro", "Doce brasileiro feito com chocolate", Arrays.asList("Leite condensado", "Chocolate em pó", "Manteiga", "Granulado")));
        receitas.add(new Receita(UUID.randomUUID().toString(), "Feijoada", "Prato típico brasileiro", Arrays.asList("Feijão preto", "Linguiça", "Bacon", "Carne seca", "Couve")));
        receitas.add(new Receita(UUID.randomUUID().toString(), "Coxinha", "Salgado brasileiro de frango", Arrays.asList("Farinha", "Frango desfiado", "Cebola", "Temperos", "Massa")));
    }

    public static ReceitasDAO getInstance() {
        if (instance == null) {
            instance = new ReceitasDAO();
        }
        return instance;
    }

    public List<Receita> listarTodas() {
        return new ArrayList<>(receitas);
    }

    public Receita buscarPorId(String id) {
        return receitas.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Receita criar(Receita receita) {
        if (receita.getId() == null) {
            receita.setId(UUID.randomUUID().toString());
        }
        receitas.add(receita);
        return receita;
    }

    public boolean atualizar(Receita receita) {
        for (int i = 0; i < receitas.size(); i++) {
            if (receitas.get(i).getId().equals(receita.getId())) {
                receitas.set(i, receita);
                return true;
            }
        }
        return false;
    }

    public boolean deletar(String id) {
        return receitas.removeIf(r -> r.getId().equals(id));
    }
}
