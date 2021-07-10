package classes;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EstudanteRepositorio {
  private String arquivo;
  private ArrayList<String> unidadesOrganicas = new ArrayList<String>();
  private ArrayList<String> municipios = new ArrayList<String>();
  private ArrayList<String> gravidades = new ArrayList<String>();
  private ArrayList<String> generos = new ArrayList<String>();

  private String[] gravidadesArray = { "CRÍTICO", "GRAVE", "MODERADO", "LEVE", "ASSINTOMÁTICO" };
  static String[] generosArray = { "Masculino", "Femenino", "Outros" };


  public EstudanteRepositorio(String arquivo) {
    this.arquivo = arquivo;
    unidadesOrganicas = new RecursosRepositorio("unidades.cov").listar();
    municipios = new RecursosRepositorio("municipios.cov").listar();
    addCollection(gravidades, gravidadesArray);
    addCollection(generos, generosArray);

  }

  static void addCollection(ArrayList<String> arrayList, String[] datas) {
    for (String data : datas) {
      arrayList.add(data);
    }
  }

  public void mapaEstatisticoDasGravidades() {
    int totalDeCasos = 0;
    ArrayList<Estudante> estudantes = this.listar();
    System.out.println("Mapa Estatistico por Gravidades");

    for (String gravidade : this.gravidades) {
      System.out.print(gravidade + ": ");
      totalDeCasos = 0;
      for (Estudante estudante : estudantes) {
        if (estudante.getGravidade().equals(gravidade)) {
          totalDeCasos++;
        }
      }
      System.out.print(totalDeCasos + "\n");
    }
  }

  public void mapaEstatisticoGenero() {
    int totalDeCasos = 0;
    ArrayList<Estudante> estudantes = this.listar();
    System.out.println("Mapa Estatistico por genero");

    for (String genero : this.generos) {
      System.out.print(genero + ": ");
      totalDeCasos = 0;
      for (Estudante estudante : estudantes) {
        if (estudante.getGenero().equals(genero)) {
          totalDeCasos++;
        }
      }
      System.out.print(totalDeCasos + "\n");
    }
  }

  public void listarPorObitosPorUnidadeOrganica() {
    ArrayList<Estudante> estudantes = this.listar();
    for (String unidadeOrganica : this.unidadesOrganicas) {
      System.out.println("### " + unidadeOrganica + " ###\n");
      for (Estudante estudante : estudantes) {
        if (estudante.getDataFalecimento() != null && estudante.getUnidadeOrganica().equals(unidadeOrganica)) {
          estudante.imprime();
          System.out.print("\n");
        }
      }
      System.out.print("\n");
    }
  }

  public void listarCasosCriticosPorMunicipio() {
    ArrayList<Estudante> estudantes = this.listar();
    for (String municipio : this.municipios) {
      System.out.println("### " + municipio + " ###\n");
      for (Estudante estudante : estudantes) {
        if (estudante.getGravidade().equals("CRÍTICO") && estudante.getMorada().equals(municipio)) {
          estudante.imprime();
          System.out.print("\n");
        }
      }
      System.out.print("\n");
    }
  }

  public boolean registrar(String registro) {
    try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(this.arquivo, true))) {
      printWriter.println(registro.trim());
      return true;
    } catch (Exception error) {
      System.out.println("Não foi possivel Criar o Estudante detalhes: " + error.getMessage());
      error.printStackTrace();
    }
    return false;
  }

  public ArrayList<Estudante> listar() {
    ArrayList<Estudante> estudantes = new ArrayList<Estudante>();
    try (BufferedReader br = new BufferedReader(new FileReader(this.arquivo))) {
      String linha = br.readLine();
      while (linha != null) {
        estudantes.add(Conversor.stringToEstudante(linha.split(";")));
        linha = br.readLine();
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return estudantes;
  }

  public Estudante pesquisarBiOuNumero(String numeroOuBi) {
    ArrayList<Estudante> estudantes = this.listar();
    for (Estudante estudante : estudantes) {
      if (estudante.getBI().equalsIgnoreCase(numeroOuBi)
          || estudante.getNumeroDeEstudante().equalsIgnoreCase(numeroOuBi)) {
        estudante.imprime();
        return estudante;
      }
    }
    return null;
  }

  public boolean EstudanteExiste(String nomeOuBi) {
    ArrayList<Estudante> estudantes = this.listar();
    for (Estudante estudante : estudantes) {
      if (estudante.getBI().equalsIgnoreCase(nomeOuBi) || estudante.getNome().equalsIgnoreCase(nomeOuBi)) {
        return true;
      }
    }
    return false;
  }

  public Boolean elimina(String numeroOuBi) {
    ArrayList<Estudante> estudantes = listar();

    try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(this.arquivo))) {
      for (Estudante estudante : estudantes) {
        if (estudante.getBI().equalsIgnoreCase(numeroOuBi)
            || estudante.getNumeroDeEstudante().equalsIgnoreCase(numeroOuBi)) {
          System.out.println(estudante.getBI());
          continue;
        }
        printWriter.println(estudante.getEstudanteToString());
      }
      return true;
    } catch (Exception error) {
      System.out.println("Não foi possivel Eliminar o Estudante detalhes: " + error.getMessage());
      error.printStackTrace();
    }
    return false;
  }
}
