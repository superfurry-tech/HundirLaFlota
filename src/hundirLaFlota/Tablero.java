package hundirLaFlota;

public class Tablero {

  /**
   * Crea un tablero de barcos y lo inicializa a -1 (sin barco).
   * En las posiciones del barco, guardaremos su id más adelante.
   * Lo devuelve como resultado!
   */
  public static int[][] crearTableroBarcos(int filas, int columnas) {
    int[][] tableroBarcos = new int[filas][columnas];
    int celdaSinBarco = -1;
    for (int i = 0; i < tableroBarcos.length; i++) {
      for (int j = 0; j < tableroBarcos[i].length; j++) {
        tableroBarcos[i][j] = celdaSinBarco;
      }
    } return tableroBarcos;
  }

  /**
   * Crea un tablero de disparos y lo inicializa a '~' (no disparado).
   */
  public static char[][] crearTableroDisparos(int filas, int columnas) {
    char[][] tableroDisparos = new char[filas][columnas];
    char celdaNoDisparada = '~';
    for (int i = 0; i < tableroDisparos.length; i++) {
      for (int j = 0; j < tableroDisparos[i].length; j++) {
        tableroDisparos[i][j] = celdaNoDisparada;
      }
    } return tableroDisparos;
  }

  /**
   * Muestra un tablero de disparos (del jugador o de la CPU).
   * Recuerda que tienes que imprimir también el número de fila, y el número de columna
   * como un tablero real.
   * Recibe como entrada el tablero a imprimir, no devuelve nada de salida, simplemente lo imprime.
   */
  public static void mostrarTableroDisparos(char[][] tableroDisparos) {
    int numeroColumnas = Main.COLUMNAS;
    char letraEncabezado;

    System.out.print("   ");
    for (int i = 0; i < numeroColumnas; i++) {
      letraEncabezado = (char) ('A' + i);
      System.out.print(letraEncabezado + " ");
    } System.out.println();

    for (int i = 0; i < tableroDisparos.length ; i++) {
      if (i<10){
        System.out.print(i + "  ");
      } else {
        System.out.print(i + " ");
      }
      for (int j = 0; j < tableroDisparos[i].length; j++) {
        System.out.print(tableroDisparos[i][j] + " ");
      }
      System.out.println();
    }
  }

  /**
   * Muestra el tablero del jugador con sus barcos y el estado de disparos
   * de la CPU (agua, tocado, hundido). El objetivo de esta función es mostrar
   * al usuario sus barcos junto a los disparos del enemigo (en un solo tablero).
   * tableroBarcos: IDs de barcos o -1
   * tableroDisparosCPU: qué ha disparado la CPU sobre este tablero
   * Por ejemplo, si en la posición 1,1:
   * - hay agua y no ha habido disparos, '.'.
   * - hay agua y ha habido disparo, '~'.
   * - había un barco y hubo un disparo, pero el barco sigue activo: 'T'
   * - había un barco y hubo un disparo, el barco está hundido: 'H'
   * - hay un barco, el barco "1": 1
   */
  public static void mostrarTableroConBarcos(int[][] tableroBarcos, char[][] tableroDisparosCPU) {
    int numeroColumnas = Main.COLUMNAS;
    char letraEncabezado;

    System.out.print("   ");
    for (int i = 0; i < numeroColumnas; i++) {
      letraEncabezado = (char) ('A' + i);
      System.out.print(letraEncabezado + " ");
    } System.out.println();

    for (int i = 0; i < tableroBarcos.length ; i++) {
      if (i<10){
        System.out.print(i + "  ");
      } else {
        System.out.print(i + " ");
      }
      for (int j = 0; j < tableroBarcos[i].length; j++) {
        char estadoDisparo = tableroDisparosCPU[i][j];
        int idBarco = tableroBarcos[i][j];
        if (tableroDisparosCPU[i][j] == 'H'){
          System.out.print('H' + " ");
        } else if (tableroDisparosCPU[i][j] == 'T'){
          System.out.print('T' + " ");
        } else if (tableroDisparosCPU[i][j] == 'A'){
          System.out.print('~' + " ");
          } else if ((idBarco != -1 && tableroDisparosCPU[i][j] != 'T' && tableroDisparosCPU[i][j] != 'A' && tableroDisparosCPU[i][j] != 'H')){
          System.out.print ((char)('0' + idBarco) + " ");
        } else {
          System.out.print("." + " ");
        }
      }
      System.out.println();
    }
  }

  /**
   * Comprueba si una coordenada está dentro del tablero. Devuelve "true" si está, "false" si no está.
   */
  public static boolean esCoordenadaValida(int fila, int columna, int filas, int columnas) {
    if (fila >=0 && filas <Main.FILAS && columna >=0 && columnas <Main.COLUMNAS){
      return true;
    }
    return false;
  }
}

