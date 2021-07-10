package classes;

import java.text.ParseException;

public class EstudanteValidator {
  static EstudanteRepositorio estudanteRepositorio = new EstudanteRepositorio("estudante.cov");

  /**
   * Esta função valida apenas os atributos não controlados porque os restantes
   * atributos ja estão predefinidos no sistema.
   * 
   * @param estudante
   * @return Boolean
   * @throws ParseException
   */
  public static Boolean invalida(Estudante estudante) throws ParseException {
    if (estudante.getNome().isEmpty()) {
      Ajuda.requisitar("Nome não pode ser vazio!");
      return false;
    } else if (estudante.getNome().length() < 4) {
      Ajuda.requisitar("O nome precisa ter ao menos 4 caracteres!");
      return false;
    } else if (estudanteRepositorio.EstudanteExiste(estudante.getNome())) {
      Ajuda.requisitar("Ja existe um estudante com esse nome!");
      return false;
    } else if (estudanteRepositorio.EstudanteExiste(estudante.getBI())) {
      Ajuda.requisitar("Esse BI ja esta em uso!");
      return false;
    }
    else if (estudante.getBI().length() != 14) {
      Ajuda.requisitar("O BI possui 14 digitos!");
      return false;
    }else if ((Integer.parseInt(estudante.getIdade()) < 14) || (Integer.parseInt(estudante.getIdade()) > 200)) {
      Ajuda.requisitar("Idade fora dos limites permitidos!");
      return false;
    }else if(estudante.getNumeroDeEstudante().isEmpty() || Integer.parseInt(estudante.getNumeroDeEstudante()) < 0) {
      Ajuda.requisitar("o numero de estudante não pode ser negativo");
      return false;
    }
    return true;
  }
}
