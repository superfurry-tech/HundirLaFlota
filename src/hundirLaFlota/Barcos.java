package hundirLaFlota;

public class Barcos {

  /**
   * Coloca todos los barcos indicados en tamanosBarcos. El array tamanosBarcos da los tamaños,
   * por ejemplo, 5, 4, 3, 3, 2. Así que para el barco "0" tienes que colocarlo en 5 posiciones consecutivas,
   * ya sea en horizontal o en vertical.
   * Usa valores aleatorios para fila, columna y orientación (horizontal/vertical),
   * comprobando que:
   *  - no se sale del tablero
   *  - no se solapa con otros barcos
   *  Recuerda, hay una función específica para colocar un barco. No dupliques el código aquí, desde aquí
   *  llama a esa función.
   */
  public static void colocarBarcosAleatorios(int[][] tableroBarcos, int[] tamanosBarcos) {
    for (int i= 0; i < tamanosBarcos.length; i++) {
      int idBarco = i;
      while (true)
      {
        int filaAzar = Utilidades.numeroAleatorio(0, Main.FILAS-1);
        int columnaAzar = Utilidades.numeroAleatorio(0, Main.COLUMNAS-1);
        int horizontalAzar = Utilidades.numeroAleatorio(0,1);
        boolean horizontalAzarBoolean;
        if (horizontalAzar %2 == 0 ){
          horizontalAzarBoolean = true;
        } else {
          horizontalAzarBoolean = false;
        }
        if (sePuedeColocarBarco(tableroBarcos, filaAzar, columnaAzar, tamanosBarcos[i], horizontalAzarBoolean)) {
          colocarBarco(tableroBarcos, filaAzar, columnaAzar, tamanosBarcos[i], horizontalAzarBoolean, idBarco);
          break;
        }
      }
    }
  }

  /**
   * Comprueba si un barco de cierto tamaño cabe desde (fila, columna) en la orientación
   * indicada, sin salirse del mapa y sin solaparse con otros barcos.
   * Nos devuelve true si se puede colocar, false si no se puede colocar.
   */
  public static boolean sePuedeColocarBarco(int[][] tablero, int fila, int columna, int tamano, boolean horizontal) {
    for (int i = 0; i < tamano; i++) {
      int filaActual;
      int columnaActual;
      if (horizontal){
        filaActual = fila;
        columnaActual = columna + i;
      } else {
        filaActual = fila + i;
        columnaActual = columna;
      }
      if ((filaActual < 0 || filaActual >= Main.FILAS) || columnaActual <0 || columnaActual >= Main.COLUMNAS) {
        return false;
      } if (tablero[filaActual][columnaActual] !=-1) {
        return false;
      }
    } return true;
  }


  /**
   * Coloca realmente el barco en el tablero, escribiendo su ID en todas las celdas.
   */
  public static void colocarBarco(int[][] tablero, int fila, int columna, int tamano, boolean horizontal, int idBarco) {
    for (int i = 0; i < tamano; i++) {
      if (horizontal){
        tablero[fila][columna+i] = idBarco;
      } else {
        tablero [fila+i][columna] = idBarco;
      }
    }
  }

  /**
   * Comprueba si TODOS los barcos han sido hundidos.
   * Esto ocurre cuando impactos[i] == tamanosBarcos[i] para todos los barcos.
   * Lo puedes llamar tras hacer un impacto con los arrays de impactos y tamaños
   * del jugador atacado para comprobar si ha acabado la partida.
   */
  public static boolean todosHundidos(int[] impactos, int[] tamanosBarcos) {
    for (int i = 0; i < tamanosBarcos.length; i++) {
      if (impactos[i] != tamanosBarcos[i]){
        return false; }
    } return true;
  }
}

