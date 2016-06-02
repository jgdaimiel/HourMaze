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
public class HourMaze_old {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Pruebas
        Tablero tab = new Tablero(24);
        Tablero tabInicial;
        
        Solucion.creaSolucion(tab);
        System.out.print("\n");
        System.out.print("Tablero solucion: \n");
        tab.imprimeTablero();
        
        
        tabInicial = Solucion.creaTableroInicial(tab);
        System.out.print("\n");
        System.out.print("Tablero final: \n");
        tabInicial.imprimeTablero();
        
        boolean exito = Solucion.resuelveTablero(tabInicial);
        
        if(exito)
            System.out.print("Resuelto mediante algoritmo!!\n");
        else
            System.out.print("FAIL\n");
        
    }
    
}
