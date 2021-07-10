package classes;

import java.text.ParseException;
import java.util.Scanner;

public class Start {
  public  Scanner Teclado = new Scanner(System.in);

  private EstudanteRepositorio estudanteRepositorio = new EstudanteRepositorio("estudante.cov");
  private static String[] opcoesMenu = { 
    "Criar Estudante", 
    "Pesquisar Estudante Por (BI/Numero)",
    "Listar casos que foram a óbito agrupados por unidade orgânica",
    "Imprimir mapa estatistico de gravidade com as quantidades",
    "Listar casos criticos por municipios",
    "Listar casos agrupando por genero"
  };

  public void eliminarEstudante () throws ParseException {
    Ajuda.limpaTela();
    String biOuNumero = Ajuda.requisitarBiOuNumEstudante();
    estudanteRepositorio.pesquisarBiOuNumero(biOuNumero);
    Ajuda.requisitar("Pretende eliminar o estudante actual? (s/n)");
    if (Conversor.respostaBooleana(Ajuda.Teclado.next())) {
      estudanteRepositorio.elimina(biOuNumero);
    }
    this.mostarMenu();
  }

  public void obitosPorUnidade() throws ParseException {
    Ajuda.limpaTela();
    estudanteRepositorio.listarPorObitosPorUnidadeOrganica();
    this.mostarMenu();
  }

  public void criarEstudante() throws ParseException {
    Ajuda.limpaTela();
    Estudante novoEstudante = Ajuda.requisitarEstudante();
    estudanteRepositorio.registrar(novoEstudante.getEstudanteToString());
    this.mostarMenu();
  }

  public void mapaGravidade() throws ParseException {
    Ajuda.limpaTela();
    estudanteRepositorio.mapaEstatisticoDasGravidades();
    this.mostarMenu();
  }

  public void casosCriticosPorMunicipio() throws ParseException {
    Ajuda.limpaTela();
    estudanteRepositorio.listarCasosCriticosPorMunicipio();
    this.mostarMenu();
  }
  
  public void mapaPorGenro() throws ParseException {
    Ajuda.limpaTela();
    estudanteRepositorio.mapaEstatisticoGenero();
    this.mostarMenu();
  }

  public void mostarMenu() throws ParseException {
    int indice = 0;
    Ajuda.requisitar("### Opções de menu ###");
    for (String opcao : opcoesMenu) {
      Ajuda.requisitar((indice + 1) + " - " + opcao);
      indice++;
    }

    int OpcaoEscolhida = this.Teclado.nextInt() - 1;

    if (OpcaoEscolhida == 0)  criarEstudante();
    else if (OpcaoEscolhida == 1) eliminarEstudante();
    else if (OpcaoEscolhida == 2) obitosPorUnidade();
    else if (OpcaoEscolhida == 3) this.mapaGravidade();
    else if (OpcaoEscolhida == 4) this.casosCriticosPorMunicipio();
    else if (OpcaoEscolhida == 5) this.mapaPorGenro();
  }
}
