package classes;

import java.util.Date;

public class Estudante {
  private String nome;
  private String idade;
  private String genero;
  private String morada;
  private String numeroDeEstudante;
  private String BI;
  private String unidadeOrganica;
  private String teste;
  private String gravidade;
  private Date dataRecuperacao;
  private Date dataFalecimento;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getIdade() {
    return idade;
  }

  public void setIdade(String idade) {
    this.idade = idade;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public String getMorada() {
    return morada;
  }

  public void setMorada(String morada) {
    this.morada = morada;
  }

  public String getNumeroDeEstudante() {
    return numeroDeEstudante;
  }

  public void setNumeroDeEstudante(String numeroDeEstudante) {
    this.numeroDeEstudante = numeroDeEstudante;
  }

  public String getBI() {
    return BI;
  }

  public void setBI(String bI) {
    BI = bI;
  }

  public String getUnidadeOrganica() {
    return unidadeOrganica;
  }

  public void setUnidadeOrganica(String unidadeOrganica) {
    this.unidadeOrganica = unidadeOrganica;
  }

  public String getTeste() {
    return teste;
  }

  public void setTeste(String teste) {
    this.teste = teste;
  }

  public String getGravidade() {
    return gravidade;
  }

  public void setGravidade(String gravidade) {
    this.gravidade = gravidade;
  }

  public Date getDataRecuperacao() {
    return dataRecuperacao;
  }

  public void setDataRecuperacao(Date dataRecuperacao) {
    this.dataRecuperacao = dataRecuperacao;
  }

  public Date getDataFalecimento() {
    return dataFalecimento;
  }

  public void setDataFalecimento(Date dataFalecimento) {
    this.dataFalecimento = dataFalecimento;
  }

  public String getEstudanteToString() {
    return this.getNome() + ";" + this.getIdade() + ";" + this.getGenero() + ";" + this.getMorada() + ";"
        + this.getNumeroDeEstudante() + ";" + this.getBI() + ";" + this.getUnidadeOrganica() + ";" + this.getTeste()
        + ";" + this.getGravidade() + ";" + Conversor.formatDate(this.getDataRecuperacao()) + ";"
        + Conversor.formatDate(this.getDataFalecimento());
  }

  public void imprime() {
    System.out.println("Nome: " + this.getNome());
    System.out.println("Idade: " + this.getIdade());
    System.out.println("Genero: " + this.getGenero());
    System.out.println("Morada: " + this.getMorada());
    System.out.println("Numero De Estudante: " + this.getNumeroDeEstudante());
    System.out.println("BI: " + this.getBI());
    System.out.println("Unidade Organica: " + this.getUnidadeOrganica());
    System.out.println("Teste: " + this.getTeste());
    System.out.println("Gravidade: " + this.getGravidade());
    System.out.println("Data Recuperacao: " + Conversor.formatDate(this.getDataRecuperacao()));
    System.out.println("Data obito: " + Conversor.formatDate(this.getDataFalecimento()));
  }
}
