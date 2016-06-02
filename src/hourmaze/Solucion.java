/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hourmaze;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author joaquin
 */
public class Solucion {
    
    /*
    Método que genera una solución, es decir, a partir de un tablero vacío, lo rellena de valores y muros creando así un tablero resuelto.
    */
    public static void creaSolucion(Tablero t){
        int[] valoresAsignables = new int[t.getTam()];
        ArrayList<String> direcciones = new ArrayList();
        int indice;
        boolean atascado;
        Random r = new Random();
        Celda[][] tablero = t.getTablero();
        int fila = 0, columna = 0;//coordenadas en el tablero de la celda en la que nos encontramos en cada momento
        String direccionElegida = "";
        
        
        
        
        //inicializar array valores asignables
        valoresAsignables[0] = r.nextInt(12) + 1;
        for(int i=1; i<valoresAsignables.length;i++){
            
            if(valoresAsignables[i-1] == 12){
                valoresAsignables[i] = 1;
            }
            else{
                valoresAsignables[i] = valoresAsignables[i-1] + 1;
            }
        }
        
        
        //mientras queden celdas vacías seguiremos rellenando el tablero
        indice = 0;
        while(indice < valoresAsignables.length)
        {            
            //buscamos la primera celda libre y le asignamos el valor apuntado por 'indice'
            
            int i=0;
            boolean encontrado = false;
            while(i<t.getFilas() && !encontrado)
            {
                int j=0;
                while(j<t.getCol() && !encontrado)
                {
                    if(tablero[i][j].getValor() == 0)
                    {
                        tablero[i][j].setValor(valoresAsignables[indice]);
                        indice++;
                        encontrado = true;                      
                        fila = i;//actualizamos la celda en la que nos encontramos
                        columna = j;
                    }
                    j++;
                }
                i++;
            }
            
            
            //avanzamos por el tablero de manera aleatoria asignando valores consecutivos
            atascado = false;
            while(!atascado)
            {
                direcciones.clear();
                
                
                //Comprobamos las celdas adyacentes vacias para la celda actual en la que nos encontremos
                //si está disponible, guardamos esa dirección en el array direcciones
                
                //si tiene adyacente derecha
                if(columna < t.getCol()-1)
                {
                    //si está vacía
                    if(tablero[fila][columna+1].getValor() == 0)
                        direcciones.add("E");
                }
                
                //si tiene adyacente izquierda
                if(columna > 0)
                {
                    //si está vacía
                    if(tablero[fila][columna-1].getValor() == 0)
                        direcciones.add("O");
                }
                
                //si tiene adyacente arriba
                if(fila > 0)
                {
                    //si está vacía
                    if(tablero[fila-1][columna].getValor() == 0)
                        direcciones.add("N");
                }
                
                //si tiene adyacente abajo
                if(fila < t.getFilas()-1)
                {
                    //si está vacía
                    if(tablero[fila+1][columna].getValor() == 0)
                        direcciones.add("S");
                }
                
                
                //escogemos una dirección entre las posibles 
                if(direcciones.size() == 1){
                    direccionElegida = direcciones.get(0);
                }
                else if(direcciones.size() > 1){
                    int random = r.nextInt(direcciones.size());
                    direccionElegida = direcciones.get(random);
                }
                else{
                    atascado = true;
                }
                
                if(!atascado){
                    //nos posicionamos en la nueva celda
                    switch(direccionElegida){
                        case "N":
                            fila--;   
                            break;
                        case "S":
                            fila++;
                            break;
                        case "E":
                            columna++;
                            break;
                        case "O":
                            columna--;
                            break;
                    }
                    //una vez actualizada la celda actual, le asignamos el valor correspondiente del array de valores
                    //despues incrementamos el indice para que apunte al siguiente valor asignable
                    tablero[fila][columna].setValor(valoresAsignables[indice]);
                    indice++;
                }                               
            }           
        }
        //cuando salga de este bucle quiere decir que ya se ha rellenado el tablero con valores
        
        //ahora debemos repasar el tablero para colocar los muros
        for(int i=0;i<t.getFilas();i++){
            for(int j=0;j<t.getCol();j++){
                
                //si tiene adyacente derecha
                if(j < t.getCol()-1)
                {
                    //si celda actual no es compatible con su adyacente derecha
                    //se coloca un muro derecho en actual y un muro izquierdo en su adyacente derecha
                    if(!esCompatible(tablero[i][j].getValor(), tablero[i][j+1].getValor())){
                        tablero[i][j].setMuroDerecha(true);
                        tablero[i][j+1].setMuroIzquierda(true);
                    }
                }
                
                
                //si tiene adyacente abajo
                if(i < t.getFilas()-1)
                {
                    //si celda actual no es compatible con su adyacente abajo
                    //se coloca un muro abajo en actual y un muro arriba en su adyacente abajo
                    if(!esCompatible(tablero[i][j].getValor(), tablero[i+1][j].getValor())){
                        tablero[i][j].setMuroAbajo(true);
                        tablero[i+1][j].setMuroArriba(true);
                    }
                }
            }
        }       
    }
    
    
    
    /*
    Método que crea un tablero inicial para jugar, a partir de una solución generada previamente.
    El parámero de entrada es el tablero resuelto.
    El parámetro de salida es el tablero inicial, listo para jugar.
    */
    public static Tablero creaTableroInicial(Tablero t){
        Tablero tableroInicial, tableroAuxiliar;
        boolean resuelto;
        int valoresAdicionales = 0;
        
        tableroInicial = Tablero.copiaMurosTablero(t);
        tableroAuxiliar = Tablero.copiaMurosTablero(t);
        
        fijaValor(t, tableroInicial, tableroAuxiliar, true);
        System.out.print("Tablero inicial: \n");
        t.imprimeTablero();
        //resuelveTablero(tableroAuxiliar);
        
        resuelto = false;
        while(!resuelto){
            
            resuelto = resuelveTablero(tableroAuxiliar);
            
            if(!resuelto){
                //fijamos un valor más
                valoresAdicionales++;
                System.out.print("fijamos un valor más \n");
                fijaValor(t, tableroInicial, tableroAuxiliar, false);
            }
        }
        
        System.out.print("total de valores adicionales: " + valoresAdicionales + "\n");
        
        return tableroInicial;
    }
        
    
    /**
     * 
     * @param t Tablero que hay que resolver 
     * @return booleano que indica si se ha resuelto el tablero de entrada o no
     */    
    public static boolean resuelveTablero(Tablero t){
        int celdasPorResolver = t.getTam();
        int[] usosRestantes = new int[12];
        int[] apariciones = new int[12];
        Celda[][] tablero = t.getTablero();
        boolean progreso = true;
        Celda celdaActual;
        
        //**comienzo del proceso de inicialización**
        
        //usosRestantes con el máximo valor para cada elemento, y apariciones a cero
        for(int i=0;i<12;i++){
            usosRestantes[i] = t.getTam()/12;
            apariciones[i] = 0;
        }
        
        //recorremos el tablero procesando los valores resueltos de inicio
        for(int i=0;i<t.getFilas();i++){
            for(int j=0;j<t.getCol();j++){
                if(tablero[i][j].getValor() != 0){
                    tablero[i][j].getValoresPosibles().add(tablero[i][j].getValor());
                    celdasPorResolver--;
                    usosRestantes[tablero[i][j].getValor()-1]--;
                }
                else{
                    for(int k=0;k<12;k++){
                        tablero[i][j].getValoresPosibles().add(k+1);
                        apariciones[k]++;
                    }
                }
            }
        }
              
        //repasamos los usos restantes por si uno de ellos está agotado de inicio, si es así, lo eliminamos de los conjuntos de valores posibles de todas las celdas
        for(int i=0;i<12;i++){
            if(usosRestantes[i] == 0){
                eliminaValorAgotado(t,i+1);
                //una vez eliminado, el número de apariciones de este valor es cero
                apariciones[i] = 0;
            }
        }
        //**fin del proceso de inicialización**
        
        
        
        //**bucle principal**
        while (progreso) {
            progreso = false;
            for (int i=0; i<t.getFilas(); i++) {
                for (int j=0; j<t.getCol(); j++) {
                    
                    celdaActual = tablero[i][j];

                    //si celdaActual no resuelta y tiene adyacente izquierda
                    if (celdaActual.getValor() == 0 && j > 0 && !celdaActual.isMuroIzquierda()) {
                        progreso = comparaYElimina(celdaActual, tablero[i][j - 1], apariciones) || progreso;
                        
                        //comprobamos si nos ha quedado un solo elemento en el conjunto de valores posibles para esta celda, si es así resolvemos la celda
                        if (celdaActual.getValoresPosibles().size() == 1) {
                            progreso = true;
                            celdasPorResolver--;
                            //asignamos el valor de la celda, quedando así marcada como resuelta
                            Iterator it = celdaActual.getValoresPosibles().iterator();
                            celdaActual.setValor((int) it.next());                         
                            //decrementamos en 1 los usos restantes del valor solución
                            usosRestantes[celdaActual.getValor()-1]--;
                            
                            //si hemos agotado los usos restantes de este valor
                            if(usosRestantes[celdaActual.getValor()-1] == 0){
                                //ese valor ya no se puede considerar para una solucion, así que lo eliminamos de todos los conjuntos de valores posibles de todas las celdas
                                eliminaValorAgotado(t, celdaActual.getValor());
                                //una vez eliminado, el número de apariciones de este valor es cero
                                apariciones[celdaActual.getValor()-1] = 0;
                            }
                            else{
                                //si el valor solución no ha quedado agotado, decrementamos en 1 las apariciones de ese valor en el tablero
                                apariciones[celdaActual.getValor()-1]--;
                            }
                        }
                    }
                    //si celdaActual no resuelta y tiene adyacente derecha
                    if (celdaActual.getValor() == 0 && j < t.getCol() - 1 && !celdaActual.isMuroDerecha()) {
                        progreso = comparaYElimina(celdaActual, tablero[i][j + 1], apariciones) || progreso;
                        if (celdaActual.getValoresPosibles().size() == 1) {
                            progreso = true;
                            celdasPorResolver--;
                            Iterator it = celdaActual.getValoresPosibles().iterator();
                            celdaActual.setValor((int) it.next());                         
                            usosRestantes[celdaActual.getValor()-1]--;
                            
                            if(usosRestantes[celdaActual.getValor()-1] == 0){
                                eliminaValorAgotado(t, celdaActual.getValor());
                                apariciones[celdaActual.getValor()-1] = 0;
                            }
                            else{
                                apariciones[celdaActual.getValor()-1]--;
                            }
                        }

                    }
                    //si celdaActual no resuelta y tiene adyacente arriba
                    if (celdaActual.getValor() == 0 && i > 0 && !celdaActual.isMuroArriba() ) {
                        progreso = comparaYElimina(celdaActual, tablero[i - 1][j], apariciones) || progreso;
                        if (celdaActual.getValoresPosibles().size() == 1) {
                            progreso = true;
                            celdasPorResolver--;
                            Iterator it = celdaActual.getValoresPosibles().iterator();
                            celdaActual.setValor((int) it.next());                          
                            usosRestantes[celdaActual.getValor()-1]--;
                            
                            if(usosRestantes[celdaActual.getValor()-1] == 0){
                                eliminaValorAgotado(t, celdaActual.getValor());
                                apariciones[celdaActual.getValor()-1] = 0;
                            }
                            else{
                                apariciones[celdaActual.getValor()-1]--;
                            }
                        }

                    }
                    //si celdaActual no resuelta y tiene adyacente abajo
                    if (celdaActual.getValor() == 0 && i < t.getFilas() - 1 && !celdaActual.isMuroAbajo()) {
                        progreso = comparaYElimina(celdaActual, tablero[i + 1][j], apariciones) || progreso;
                        if (celdaActual.getValoresPosibles().size() == 1) {
                            progreso = true;
                            celdasPorResolver--;
                            Iterator it = celdaActual.getValoresPosibles().iterator();
                            celdaActual.setValor((int) it.next());                            
                            usosRestantes[celdaActual.getValor()-1]--;
                            
                            if(usosRestantes[celdaActual.getValor()-1] == 0){
                                eliminaValorAgotado(t, celdaActual.getValor());
                                apariciones[celdaActual.getValor()-1] = 0;
                            }
                            else{
                                apariciones[celdaActual.getValor()-1]--;
                            }
                        }
                    }
                }
            }
            
            //comprobamos el array apariciones por si hay algún valor que solo aparece en una celda, si es así hay que buscar y resolver esa celda
            //esto quiere decir también que este valor solo tiene un uso restante
            for(int i=0;i<12;i++){
                if(apariciones[i] == 1){
                    //el valor i+1 aparece solo en una celda, por tanto i+1 es la solución a dicha celda
                    int solucion = i+1;
                    
                   System.out.println("Apariciones de " + solucion + " = 1");
                   
                    boolean encontrado = false;
                    int f=0;
                    //recorremos el tablero en busca de la celda que contiene el valor solución
                    while(f<t.getFilas() && !encontrado){
                        int c=0;
                        while(c<t.getCol() && !encontrado){
                            
                            Celda celda = tablero[f][c];
                            //si celda no resuelta
                            if(celda.getValor() == 0){
                                //si el conjunto de valores posibles contiene el elemento que buscamos
                                if(celda.getValoresPosibles().contains(solucion)){
                                    //celda encontrada
                                    encontrado = true;
                                    //resolvemos la celda
                                    progreso = true;
                                    celdasPorResolver--;
                                    celda.setValor(solucion);
                                    usosRestantes[i]--;
                                    
                                    //en caso de que esta celda tuviera más elementos en el conjunto de valores posibles, debemos eliminarlos y decrementar en 1 sus apariciones
                                    Iterator it = celda.getValoresPosibles().iterator();
                                    while(it.hasNext()){
                                        int x = (int)it.next();
                                        if(x != solucion){
                                            apariciones[x-1]--;
                                            it.remove();
                                        }
                                    }
                                }
                            }
                            c++;                            
                        }
                        f++;
                    }
                    //sabemos que este valor solo aparecia en una celda, una vez resuelta ya no hay más apariciones en el tablero
                    apariciones[i] = 0;
                }
            }
        }
        //**fin del bucle principal**
        
        return (celdasPorResolver == 0);
    }
    
       
    /*
    Método que limpia un tablero poniendo todas sus casilla a valor 0, pero conservando los muros.
    */
    public static void limpiaTablero(Tablero t){
        Celda[][] tablero = t.getTablero();
        for(int i=0;i<t.getFilas();i++){
            for(int j=0;j<t.getCol();j++){
                tablero[i][j].setValor(0);
            }
        }
    }   
    
    /*
    Método para saber si dos número son compatibles entre sí, siguiendo las reglas de Hour Maze.
    */
    public static boolean esCompatible(int a, int b){
        boolean compatible = false;
        
        if(a>1 && a<12){
            if((b == a-1) || (b == a+1))
                compatible = true;
        }
        else if(a == 1){
            if(b == 2 || b == 12)
                compatible = true;
        }
        else if(a == 12){
            if(b == 1 || b == 11)
                compatible = true;
        }        
        
        return compatible;
    }    
    

    /**
     * 
     * @param tSolucion
     * @param tInicial
     * @param tAuxiliar
     * @param flag 
     */
    private static void fijaValor(Tablero tSolucion, Tablero tInicial, Tablero tAuxiliar, boolean flag) {
        Celda[][] tabSolucion = tSolucion.getTablero();
        Celda[][] tabInicial = tInicial.getTablero();
        Celda[][] tabAuxiliar = tAuxiliar.getTablero();
        Random random = new Random();
        ArrayList<Coordenada> arrayCoordenadas = new ArrayList();
        int numValoresIniciales = 1;
        int r, fila, columna;
        
        for(int i=0;i<tSolucion.getFilas();i++){
            for(int j=0;j<tSolucion.getCol();j++){
                if(tabAuxiliar[i][j].getValor() == 0)
                    arrayCoordenadas.add(new Coordenada(i,j));
            }
        }
        
        if(flag){
            switch(tSolucion.getTam()){
            case 24:
                numValoresIniciales = 3;
                break;
            case 36:
                numValoresIniciales = 5;
                break;
            case 48:
                numValoresIniciales = 7;
                break;
            case 60:
                numValoresIniciales = 9;
                break;
            }     
        }
                
        for(int i=0;i<numValoresIniciales;i++){
            r = random.nextInt(arrayCoordenadas.size());
            fila = (arrayCoordenadas.get(r)).getX();
            columna = (arrayCoordenadas.get(r)).getY();
            
            tabInicial[fila][columna].setValor(tabSolucion[fila][columna].getValor());
            tabAuxiliar[fila][columna].setValor(tabSolucion[fila][columna].getValor());
            //limpiamos el conjunto de valores posibles de la celda del tablero auxiliar
            //en caso de que estemos fijando un valor adicional
            tabAuxiliar[fila][columna].getValoresPosibles().clear();
            
            arrayCoordenadas.remove(r);
        }
    }

    
    /**
     * Método que elimina el valor pasado por parámetro de todos los conjuntos de valores posibles de todas las celdas
     * @param tablero
     * @param i 
     */
    private static void eliminaValorAgotado(Tablero t, int valor) {
        Celda[][] tablero = t.getTablero();
        for(int i=0;i<t.getFilas();i++){
            for(int j=0;j<t.getCol();j++){
                if(tablero[i][j].getValor() == 0){
                    tablero[i][j].getValoresPosibles().remove(valor);
                }
            }
        }
    }

    
    /**
     * 
     * @param celdaActual
     * @param celdaAdyacente
     * @param apariciones 
     * @return 
     */
    private static boolean comparaYElimina(Celda celdaActual, Celda celdaAdyacente, int[] apariciones) {
        boolean compatible;
        Iterator<Integer> itActual;
        Iterator<Integer> itAdyacente;
        boolean progreso = false;
        int a,b;
        
        if(celdaAdyacente.getValoresPosibles().size()<12){//Si la celda adyacente tiene los 12 valores en su conjunto,
                                                           //entonces los valores de la celda actual siempre van a ser compatibles con dos de ellos
            itActual = celdaActual.getValoresPosibles().iterator();
            while(itActual.hasNext()){
                compatible = false;
                a = itActual.next();
                itAdyacente = celdaAdyacente.getValoresPosibles().iterator();
                while(itAdyacente.hasNext() && !compatible){
                    b = itAdyacente.next();
                    compatible = esCompatible(a,b);
                }
                if(!compatible){
                    itActual.remove();
                    apariciones[a-1]--;
                    progreso = true;
                }
            }
        }
                
        return progreso;
    }

    
}
