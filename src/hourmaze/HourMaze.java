/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hourmaze;

/**
 *
 * @author joaquin
 */
public class HourMaze {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Pruebas
        Tablero tab = new Tablero(24);
        Tablero tabInicial;
        
        Solucion.creaSolucion(tab);
        tabInicial = Solucion.creaTableroInicial(tab);
        
        System.out.print("\n");
        System.out.print("Tablero solucion: \n");
        tab.imprimeTablero();
        
        System.out.print("\n");
        System.out.print("Tablero inicial: \n");
        tabInicial.imprimeTablero();
        
    }
    
}
