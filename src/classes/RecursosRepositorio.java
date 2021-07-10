package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class RecursosRepositorio {
  String arquivo;

  public RecursosRepositorio(String arquivo) {
    this.arquivo = arquivo;
  }

  public ArrayList<String> listar() {
    ArrayList<String> valores = new ArrayList<String>();
    try (BufferedReader br = new BufferedReader(new FileReader(this.arquivo))) {
      String valor = br.readLine();
      while (valor != null) {
        valores.add(valor);
        valor = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }

    return valores;
  }
}
