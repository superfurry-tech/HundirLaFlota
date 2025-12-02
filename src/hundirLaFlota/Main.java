package hundirLaFlota;

import java.util.Scanner;

public class Main
{
  public static final int FILAS = 10;
  public static final int COLUMNAS = 10;

public static void main(String[] args){
  Scanner sc = new Scanner(System.in);

  int filaValida = -1;
  int columnaValida = -1;
  while (filaValida == -1 || columnaValida == -1){
    System.out.println("Introduce coordenada (ej. A5): ");
    String coord = sc.nextLine().trim().toUpperCase(); // .trim() elimina espacios en blanco
    filaValida = Utilidades.convertirFila(coord);
    columnaValida = Utilidades.convertirColumna(coord);
    if (filaValida == -1 || columnaValida == -1){
      System.out.println("Coordenada no válida. Rango columnas A - J y rango filas 0 - 9");
    }
  }
}
}



