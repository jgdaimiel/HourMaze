/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hourmaze;

import java.util.HashSet;

/**
 *
 * @author joaquin
 */
public class Celda {
    //valor de la celda
    private int valor; 
    
    //valores que podrían ser solución a la celda
    private HashSet<Integer> valoresPosibles;
    
    //muros que rodean la celda
    boolean muroDerecha, muroIzquierda, muroArriba, muroAbajo;

    
    public Celda(){
        this.valor = 0;
        this.muroAbajo = false;
        this.muroArriba = false;
        this.muroDerecha = false;
        this.muroIzquierda = false;
        valoresPosibles = new HashSet<Integer>();
    }
    
    public Celda(int valor, HashSet<Integer> valoresPosibles, boolean muroDerecha, boolean muroIzquierda, boolean muroArriba, boolean muroAbajo) {
        this.valor = valor;
        this.valoresPosibles = valoresPosibles;
        this.muroDerecha = muroDerecha;
        this.muroIzquierda = muroIzquierda;
        this.muroArriba = muroArriba;
        this.muroAbajo = muroAbajo;
    }
       
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isMuroDerecha() {
        return muroDerecha;
    }

    public void setMuroDerecha(boolean muroDerecha) {
        this.muroDerecha = muroDerecha;
    }

    public boolean isMuroIzquierda() {
        return muroIzquierda;
    }

    public void setMuroIzquierda(boolean muroIzquierda) {
        this.muroIzquierda = muroIzquierda;
    }

    public boolean isMuroArriba() {
        return muroArriba;
    }

    public void setMuroArriba(boolean muroArriba) {
        this.muroArriba = muroArriba;
    }

    public boolean isMuroAbajo() {
        return muroAbajo;
    }

    public void setMuroAbajo(boolean muroAbajo) {
        this.muroAbajo = muroAbajo;
    }

    public HashSet<Integer> getValoresPosibles() {
        return valoresPosibles;
    }

    @Override
    public String toString() {
        String cadena = "";
        if(this.muroAbajo == true){
            cadena = cadena.concat("_" + valor + "_");
        }
        else{
            cadena = cadena.concat(" " + String.valueOf(valor) + " ");
        }            
            
            
        if(this.muroDerecha == true){
            cadena = cadena.concat("|");
        }
        return cadena; //"Celda{" + "valor=" + valor + ", valoresPosibles=" + valoresPosibles + ", muroDerecha=" + muroDerecha + ", muroIzquierda=" + muroIzquierda + ", muroArriba=" + muroArriba + ", muroAbajo=" + muroAbajo + '}';
    }
    
    
}
