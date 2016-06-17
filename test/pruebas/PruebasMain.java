/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import hourmaze.Solucion;
import hourmaze.Tablero;

/**
 *
 * @author joaquin
 */
public class PruebasMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tablero tablero, tableroInicial;
        for(int i=0;i<100;i++){
            tablero = new Tablero(48);
            Solucion.creaSolucion(tablero);
            tableroInicial = Solucion.creaTableroInicial(tablero);
            Solucion.resuelveTablero(tableroInicial);
        }
    }
    
}
