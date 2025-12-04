package hundirLaFlota;

public class Utilidades {

  public static int convertirFila(String coord) {
    String parteFila = "" ;
    int digito= 0;
    int filaFinal = 0;
    for (int i = 1; i < coord.length(); i++) {
      char caracter = coord.charAt(i);
      if (caracter < '0' || caracter > '9'){
        return -1;
      } parteFila = parteFila + coord.charAt(i);
    }

    if (parteFila.isEmpty()){ // Asegura que escribe fila y no solo letra
      return -1;
    }
    for (int i = 0; i < parteFila.length(); i++) { // Conversión de caracteres a int.
      digito = parteFila.charAt(i) - '0'; // Resto 0 (con código ASCII 48) para pasar de carácter a int. Los números están, siguiendo la numeración, a continuación del 0.
      filaFinal = (filaFinal * 10) + digito; // Mueve el acumulado una posición a la izquierda (x10) y añade el nuevo dígito.
    }
    if (filaFinal >=0 && filaFinal <=9) {
      return filaFinal;
    } else {
      return -1;
    }
  }

  public static int convertirColumna(String coord) {
    if (coord == null || coord.isEmpty()) {
      return -1;
    }
    char letra = coord.charAt(0); // Extrae letra de columna.
    int columnaFinal = letra - 'A'; // Convierte letra a número para ver la "distancia" a A.
    if (columnaFinal < 0 || columnaFinal > 9) {
      return -1;
    }
    return columnaFinal;
  }

  public static int numeroAleatorio (int min, int max) {
    return (int) ((Math.random() * (max - min + 1)) + min);
  }
}
