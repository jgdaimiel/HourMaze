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
public class Tablero {
    
    //matriz de Celda
    private Celda[][] tablero;
    
    //número de celdas del tablero
    private int tam;
    
    //número de filas
    private int filas;
    
    //número de columnas
    private int col;
    
    
    //Contruye un tablero dado un tamaño de entrada, y lo inicializa a tablero vacío
    public Tablero(int tam) {
        
        switch (tam){
            case 24:
                this.tablero = new Celda[4][6];
                this.filas = 4;
                this.col = 6;
                break;
            case 36:
                this.tablero = new Celda[4][9];
                this.filas = 4;
                this.col = 9;
                break;
            case 48:
                this.tablero = new Celda[6][8];
                this.filas = 6;
                this.col = 8;
                break;
            case 60:
                this.tablero = new Celda[6][10];
                this.filas = 6;
                this.col = 10;
                break;
            default:
                System.out.println("Tamaño incorrecto.\n");//Parar ejecución
                break;
        }
        
        this.tam = tam;
        inicializaTablero();        
    }
    
    
    private void inicializaTablero(){
        for(int i=0;i<filas;i++){
            for(int j=0;j<col;j++){
                tablero[i][j] = new Celda();
            }
        }
    }
    
    
    public int getTam() {
        return tam;
    }

    public int getFilas() {
        return filas;
    }

    public int getCol() {
        return col;
    }
    
    public Celda[][] getTablero() {
        return tablero;
    }

    
    public void imprimeTablero(){
        for(int i=0;i<filas;i++){
            for(int j=0;j<col;j++){
                System.out.print(" ");
                System.out.print(tablero[i][j].toString());
            }
            System.out.print("\n");
        }
    }

    /**
     * Método para crear una copia de un tablero pero sin copiar los valores de sus celdas, solo el tamaño y los muros.
     * @param t tablero del que hay que hacer una copia
     * @return Tablero copia de 't'
     */
    public static Tablero copiaMurosTablero(Tablero t){    //idea: opción de añadir flag booleano para copia completa o solo muros a parámetros de entrada
        
        Tablero copiaT = new Tablero(t.getTam());
        Celda[][] tabOriginal = t.getTablero();
        Celda[][] tabCopia = copiaT.getTablero();
        
        for(int i=0;i<t.getFilas();i++){
            for(int j=0;j<t.getCol();j++){
                tabCopia[i][j].setMuroAbajo(tabOriginal[i][j].isMuroAbajo());
                tabCopia[i][j].setMuroArriba(tabOriginal[i][j].isMuroArriba());
                tabCopia[i][j].setMuroDerecha(tabOriginal[i][j].isMuroDerecha());
                tabCopia[i][j].setMuroIzquierda(tabOriginal[i][j].isMuroIzquierda());
            }
        }
                
        return copiaT;
    }
    
    
}
