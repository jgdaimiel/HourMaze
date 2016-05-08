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
public class Coordenada {
    
    private int x;
    private int y;
    
    
    public Coordenada(){
        this(0,0);
    }
    
    public Coordenada(int x, int y){
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
