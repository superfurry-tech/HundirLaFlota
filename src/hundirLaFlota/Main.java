package hundirLaFlota;

import java.util.Scanner;

public class Main {
  public static final int FILAS = 10;
  public static final int COLUMNAS = 10;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // Tamaños de los barcos (podéis cambiarlos si queréis)
    // Esto implica que hay 5 barcos de 5, 4, dos de 3, 2 casillas.
    int[] tamanosBarcos = {5, 4, 3, 3, 2};
    int numBarcos = tamanosBarcos.length;

    // Tableros de barcos: guardan IDs de barco o -1 si no hay barco
    int[][] tableroBarcosJugador = Tablero.crearTableroBarcos(FILAS, COLUMNAS);
    int[][] tableroBarcosCPU = Tablero.crearTableroBarcos(FILAS, COLUMNAS);

    // Tableros de disparos: lo que ve cada jugador (agua, tocado, hundido, sin disparar)
    char[][] tableroDisparosJugador = Tablero.crearTableroDisparos(FILAS, COLUMNAS);
    char[][] tableroDisparosCPU = Tablero.crearTableroDisparos(FILAS, COLUMNAS);

    // Impactos por barco.
    // Aquí guardaremos cuantos impactos lleva cada barco.
    // Por ejemplo:
    // [5, 4, 3, 3, 2] es el array de tamaños de barcos. Si el barco grande lleva 2 impactos, y
    // el primer barco de "3" lleva 1 impacto, el array de impactos será:
    // [2, 0, 1, 0, 0]. Si en algún momento algún elemento del array de impactos equivale al array de
    // tamaños, ese barco está hundido:
    // [5, 3, 2, 2, 2]  en los impactos implica que el barco de 5 está hundido, y el barco de 2 también.
    int[] impactosJugador = new int[numBarcos];
    int[] impactosCPU = new int[numBarcos];

    // Inicializar los arrays impactosJugador e impactosCPU a 0
    // (aunque Java ya los inicializa a 0, hacedlo explícitamente con un for)
    for (int i = 0; i < impactosJugador.length; i++) {
      impactosJugador [i] = 0;
      impactosCPU [i] = 0;
    }

    // Colocar barcos
    System.out.println("Colocando barcos del jugador...");
    Barcos.colocarBarcosAleatorios(tableroBarcosJugador, tamanosBarcos);

    System.out.println("Colocando barcos de la CPU...");
    Barcos.colocarBarcosAleatorios(tableroBarcosCPU, tamanosBarcos);

    boolean finPartida = false;
    boolean turnoJugador = true;

    // Bucle principal del juego
    // Cada jugador juega su turno hasta que ha derrotado a todos.
    while (!finPartida) {
      System.out.println();
      System.out.println("=============================");
      System.out.println("HUNDIR LA FLOTA - NUEVO TURNO");
      System.out.println("=============================");
      if (turnoJugador) {
        System.out.println("Turno del JUGADOR");
        // Mostrar tableros relevantes
        System.out.println("Tu tablero (tus barcos):");
        Tablero.mostrarTableroConBarcos(tableroBarcosJugador, tableroDisparosCPU);

        System.out.println("Tus disparos sobre la CPU:");
        Tablero.mostrarTableroDisparos(tableroDisparosJugador);

        // TODO: Validar la coordenada (formato mínimo, longitud, etc.)
        // TODO: Convertir la coordenada (ej. 'A5') en fila y columna (int) HECHO
        // TODO: si no es una coordenada válida, hay que volver a pedirla.
        // TODO: si el formato de la coordenada no es válido, vuelve a pedirla.

        int fila = -1;
        int columna = -1;
        boolean formatoValido = false;
        do {
          // Pedir coordenada. AÑADO FUNCIÓN EN UTILIDADES
          String coord = Utilidades.pedirCoordenada(sc);
          fila = Utilidades.convertirFila(coord);
          columna = Utilidades.convertirColumna(coord);

          if (fila == -1 || columna == -1){
            System.out.println("Formato incorrecto. Inténtalo de nuevo.");
          } else {
            formatoValido = true;
          }
        }while (!formatoValido);

          if (!Tablero.esCoordenadaValida(fila, columna, FILAS, COLUMNAS)){
            System.out.println("Coordenada fuera del tablero. Pierdes el turno.");
          } else if (Disparos.yaDisparado(tableroDisparosJugador, fila, columna)) {
            System.out.println("Ya habías disparado ahí. Pierdes el turno.");
          } else {
            // Y AQUÍ EMPEZARÍA A METER EL DISPARO
          }

        // TODO: Comprobar si la CPU ha perdido todos los barcos con la función adecuada
        // si es así, configura el final de la partida y di el resultado.

      } else {
        System.out.println("Turno de la CPU");
        // Hacemos el turno de la CPU
        int filaCPU = Utilidades.numeroAleatorio(0, FILAS - 1);
        int columnaCPU = Utilidades.numeroAleatorio(0, COLUMNAS - 1);

        // TODO: si ya había disparado a la coordenada, no ejecutamos el disparo, generamos otra
        // coordenada.

        System.out.println("La CPU dispara a (" + filaCPU + ", " + columnaCPU + ")");

        // Ejecutamos el disparo de la CPU
        boolean barcoHundidoJugador = Disparos.procesarDisparo(
            filaCPU,
            columnaCPU,
            tableroBarcosJugador,
            tableroDisparosCPU,
            impactosJugador,
            tamanosBarcos
        );

        if (barcoHundidoJugador) {
          System.out.println("La CPU te ha hundido un barco...");
        }

        // Comprobamos si el jugador ha perdido todos los barcos.
        if (Barcos.todosHundidos(impactosJugador, tamanosBarcos)) {
          System.out.println("Has perdido. La CPU ha hundido todos tus barcos.");
          finPartida = true;
        }
      }

      // Cambiar turno
      turnoJugador = !turnoJugador;
    }

    sc.close();
    System.out.println("Fin de la partida.");
  }
}