package classes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ajuda {
  static Estudante estudante = new Estudante();
  public static Scanner Teclado = new Scanner(System.in);

  static String[] generosArray = { "Masculino", "Femenino", "Outros" };
  static String[] gravidadesArray = { "CRÍTICO", "GRAVE", "MODERADO", "LEVE", "ASSINTOMÁTICO" };

  static ArrayList<String> municipios = new ArrayList<String>();
  static ArrayList<String> unidadesOrganicas = new ArrayList<String>();
  static ArrayList<String> generos = new ArrayList<String>();
  static ArrayList<String> testes = new ArrayList<String>();
  static ArrayList<String> gravidades = new ArrayList<String>();

  static void addCollection(ArrayList<String> arrayList, String[] datas) {
    for (String data : datas) {
      arrayList.add(data);
    }
  }

  static void carregarRecursosEstaticos() {
    addCollection(generos, generosArray);
    addCollection(gravidades, gravidadesArray);
  }

  private static void carregaRecursosDosArquivos() {
    unidadesOrganicas = new RecursosRepositorio("unidades.cov").listar();
    municipios = new RecursosRepositorio("municipios.cov").listar();
    testes = new RecursosRepositorio("tiposTeste.cov").listar();
  }

  private static String requisitarAlgo(String pergunta, ArrayList<String> opcoes) {
    int index = 0;
    requisitar(pergunta);
    for (String opcao : opcoes) {
      requisitar((index + 1) + " - " + opcao);
      index++;
    }
    int OpcaoEscolhida = Teclado.nextInt() - 1;
    return opcoes.get(OpcaoEscolhida);
  }

  public static void limpaTela() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static String requisitarBiOuNumEstudante() {
    requisitar("Informe Bi/Numero de estudante que procura: ");
    return Teclado.next();
  }

  public static void requisitar(String text) {
    System.out.println(text);
  }

  public static String questionarioCovid() {
    requisitar("1 - Tem febre? (s/n): ");
    Boolean febre = Conversor.respostaBooleana(Teclado.next());

    requisitar("2 - Tem dificuldade em respirar? (s/n): ");
    Boolean respiracao = Conversor.respostaBooleana(Teclado.next());

    requisitar("3 - Sente algum cansaço?");
    Boolean cancaco = Conversor.respostaBooleana(Teclado.next());

    requisitar("4 - Tem alguma comorbidade? (s/n): ");
    Boolean comorbidade = Conversor.respostaBooleana(Teclado.next());

    Boolean Critico = febre && respiracao && cancaco && comorbidade;
    Boolean Grave = respiracao || (comorbidade && febre);
    Boolean Moderado = comorbidade && Integer.parseInt(estudante.getIdade()) > 60;
    Boolean Leve = febre || cancaco;

    if (Critico)
      return gravidadesArray[0];
    else if (Grave)
      return gravidadesArray[1];
    else if (Moderado)
      return gravidadesArray[2];
    else if (Leve)
      return gravidadesArray[3];

    return gravidadesArray[4];
  }

  public static void requisitarRecuperacao(Boolean recuperado) throws ParseException {
    if (recuperado) {
      requisitar("Quando é que recuperou? (dia/mês/ano): ");
      estudante.setDataRecuperacao(Conversor.stringToDate(Teclado.next()));
      limpaTela();
    }
  }

  public static void requisitarObito(Boolean obito) throws ParseException {
    if (obito) {
      requisitar("Quando é veio a obito? (dia/mês/ano): ");
      estudante.setDataFalecimento(Conversor.stringToDate(Teclado.next()));
      limpaTela();
    }
  }

  public static Estudante requisitarEstudante() throws ParseException {
    carregaRecursosDosArquivos();
    carregarRecursosEstaticos();

    try {
      requisitar("Nome do estudante: ");
      estudante.setNome(Teclado.nextLine());
      limpaTela();

      requisitar("Idade do estudante: ");
      estudante.setIdade(Teclado.nextLine());
      limpaTela();

      requisitar("Numero de estudante: ");
      estudante.setNumeroDeEstudante(Teclado.nextLine());
      limpaTela();

      estudante.setGravidade(questionarioCovid());

      requisitar("O estudante foi a obito? ");
      requisitarObito(Conversor.respostaBooleana(Teclado.next()));
      limpaTela();

      requisitar("O estudante se recuperou? ");
      requisitarRecuperacao(Conversor.respostaBooleana(Teclado.next()));
      limpaTela();

      requisitar("Informe o seu BI: ");
      estudante.setBI(Teclado.next());
      limpaTela();

      estudante.setGenero(requisitarAlgo("Qual é a sua orientação sexual? ", generos));
      limpaTela();

      estudante.setMorada(requisitarAlgo("Em que municipio você vive? ", municipios));
      limpaTela();

      estudante.setUnidadeOrganica(requisitarAlgo("A qual unidade organica você pertence? ", unidadesOrganicas));
      limpaTela();

      estudante.setTeste(requisitarAlgo("A qual teste você foi submetido? ", testes));
      limpaTela();
      EstudanteValidator.invalida(estudante);
    } catch (Exception e) {
      System.out.println("Erro ao validar os dados" + e.getMessage());
      e.printStackTrace();
    }
    return estudante;
  }
}
