package hundirLaFlota;

public class Disparos {

  /**
   * Devuelve true si ya se ha disparado previamente a esta casilla
   * (es decir, tableroDisparos[fila][columna] != '~'). Deberías de llamarlo cuando se intenta disparar,
   * para evitar disparar en zonas que ya se han probado.
   */
  public static boolean yaDisparado(char[][] tableroDisparos, int fila, int columna) {
    return tableroDisparos[fila][columna] != '~';
  }

  /**
   * Procesa un disparo sobre (fila, columna).
   * - Si no hay barco (idBarco == -1): marca 'A' (agua).
   * - Si hay barco:
   * - incrementa impactosBarco[idBarco] - ya que tiene un impacto más.
   * - si impactos < tamaño → 'T' (tocado) - ya que es por ejemplo el segundo impacto en un barco de 3
   * - si impactos == tamaño → marcar TODAS sus celdas como 'H' (hundido)
   * Devuelve true si el disparo ha hundido un barco, false en caso contrario.
   */
  public static boolean procesarDisparo(
      int fila,
      int columna,
      int[][] tableroBarcos,
      char[][] tableroDisparos,
      int[] impactosBarco,
      int[] tamanosBarco
  ) {
    int idBarco = tableroBarcos[fila][columna];
    if (idBarco == -1) {
      tableroDisparos[fila][columna] = 'A';
      return false;
    } else {
      impactosBarco[idBarco]++;
      if (impactosBarco[idBarco] < tamanosBarco[idBarco]) {
        tableroDisparos[fila][columna] = 'T';
        return false;
      } else {
        for (int i = 0; i < tableroBarcos.length; i++) {
          for (int j = 0; j < tableroBarcos[i].length; j++) {
            if (idBarco == tableroBarcos[i][j]){
              tableroDisparos[i][j] = 'H';
            }
          }
        } return true;
      }
    }
  }
}



