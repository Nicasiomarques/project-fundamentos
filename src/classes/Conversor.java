package classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversor {
  public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

  static Date stringToDate(String data) throws ParseException {
    Date date = null;
    try {
      date = simpleDateFormat.parse(data);
      return date;
    } catch (Exception e) {
    }
    return date;
  }

  public static String formatDate(Date date) {
    return date != null ? simpleDateFormat.format(date) : null;
  }

  public static boolean respostaBooleana(String reposta) {
    return reposta.equalsIgnoreCase("s") || reposta.equalsIgnoreCase("sim");
  }

  public static Estudante stringToEstudante(String campos[]) throws ParseException {
    Estudante estudante = new Estudante();
    estudante.setNome(campos[0]);
    estudante.setIdade(campos[1]);
    estudante.setGenero(campos[2]);
    estudante.setMorada(campos[3]);
    estudante.setNumeroDeEstudante(campos[4]);
    estudante.setBI(campos[5]);
    estudante.setUnidadeOrganica(campos[6]);
    estudante.setTeste(campos[7]);
    estudante.setGravidade(campos[8]);
    estudante.setDataRecuperacao(stringToDate(campos[9]));
    estudante.setDataFalecimento(stringToDate(campos[10]));
    return estudante;
  }
}
