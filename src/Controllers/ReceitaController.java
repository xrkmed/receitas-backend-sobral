package Controllers;

import DAO.ReceitasDAO;
import Models.Receita;

public class ReceitaController {
  private ReceitasDAO receitasDAO;

  public ReceitaController() {
    this.receitasDAO = ReceitasDAO.getInstance();
  }

  
}
