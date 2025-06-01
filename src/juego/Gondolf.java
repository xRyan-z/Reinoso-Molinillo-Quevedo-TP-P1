package juego;

import java.awt.Image;
import java.awt.Toolkit;
import entorno.Entorno;

public class Gondolf {
    private int x; // Posición x de Gondolf en la pantalla
    private int y; // Posición y de Gondolf en la pantalla
    private int alto; // Alto de Gondolf
    private int ancho; // Ancho de Gondolf
    private int vida; // Vida de Gondolf
    private int mana; // Mana de gondolf
    private Image imagenDerecha; // Imagen del personaje a la derecha
    private Image imagenIzquierda; // Imagen del personaje a la izquierda
    private Image imagenActual; // Imagen del personaje guardada
    private Image imagenArriba; // Imagen del personaje cuando va a arriba
    private Image imagenAbajo; // Imagen del personaje cuando va a abajo
    

    public Gondolf (int x , int y, int alto, int ancho, int vida, int mana) { // Inicializamos a Gondolf
        this.x = x;
        this.y = y;
        this.alto = alto;
        this.ancho = ancho;
        this.vida = vida;
        this.mana = mana;
        this.imagenDerecha = Toolkit.getDefaultToolkit().getImage("Imagenes/Gondolfder.png");
        this.imagenIzquierda = Toolkit.getDefaultToolkit().getImage("Imagenes/Gondolfizq.png");
        this.imagenAbajo = Toolkit.getDefaultToolkit().getImage("Imagenes/GondolfNormaloAbajo.png");
        this.imagenArriba = Toolkit.getDefaultToolkit().getImage("Imagenes/Gondolfarriba.png");

        
        this.imagenActual = imagenAbajo;   // Imagen inicial por defecto

    }

    public void dibujar(Entorno entorno) { // Metodo que dibuja a Gondolf en pantalla en la posición x,y y escala
        entorno.dibujarImagen(imagenActual, x, y, 0, 1.0);
    }
           		
    	
        public int restarvida(int v) { // Metodo que resta cantidad v de vida.
        	if(vida > 0) {
        	vida = vida - v;
        	} else {
        	}
        		
        return vida;

    }
        public int sumarvida() { // Metodo que suma cantidad 10 de vida.
        	if(vida > 0 && vida < 100) {
        	vida = vida + 10;
        	} 
        return vida;
    }
        
        public int restarMana(int j) { // Metodo que resta cantidad J de Maná
        	if(mana > 0) {
        	mana = mana - j;
        	
        	} 
        return mana;

    }
        public int sumarMana() { // Metodo que suma cantidad 10 de Maná.
        	if(mana >= 0 && mana <= 100) {
        	mana = mana + 10;
      
        	} 
        return mana;
    }
           
    public void MoverIzq() { // Metodo que mueve a Gondfolf -3 en X .
        this.x -=3 ;
        this.imagenActual = imagenIzquierda;
    }
    public void MoverDer() { // Metodo que mueve a Gondfolf +3 en X .
        this.x +=3 ;
        this.imagenActual = imagenDerecha;
    }
    public void MoverArriba() { // Metodo que mueve a Gondfolf -3 en Y .
        this.y -=3 ;
        this.imagenActual = imagenArriba; // Utiliza la imagen asignada
    }
    public void MoverAbajo() { // Metodo que mueve a Gondfolf +3 en Y .
        this.y +=3 ;
        this.imagenActual = imagenAbajo;
    }

    public boolean colisionaPorDerecha(Entorno entorno) { // Metodo boolean que retorna True si Gondolf toca el borde derecho - El menú.
        return this.x + this.ancho/2 >= entorno.ancho()-175;
    }

    public boolean colisionaPorIzquierda(Entorno entorno) { // Metodo boolean que retorna True si Gondolf toca el borde izquierdo
        return this.x - this.ancho/2 <= 0;
    }

    public boolean colisionaPorArriba(Entorno entorno) {  // Metodo boolean que retorna True si Gondolf toca el borde de Arriba
        return this.y - this.alto / 2 <= 5;
    }

    public boolean colisionaPorAbajo(Entorno entorno) { // Metodo boolean que retorna True si Gondolf toca el borde de Abajo
        return this.y + this.alto / 2 >= entorno.alto()-5;
    }

    public boolean colisionaConPiedra(int dx, int dy, Piedra[] piedras) {
	    for (Piedra p :  piedras ) {
	        // Calculamos dónde estaría Gondolf si se moviera
	        int futuroX = getX() + dx;
	        int futuroY = getY() + dy;

	        // Verificamos si en esa posición estaría tocando una piedra
	        if (Math.abs(futuroX - p.getX()) < (getAncho() / 2 + p.getAncho() / 2) &&
	            Math.abs(futuroY - p.getY()) < (getAlto() / 2 + p.getAlto() / 2)) {
	            return true; // Hay colisión
	        }
	    }
	    return false; // No hay colisión
	}
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAlto() {
        return alto;
    }


    public int getAncho() {
        return ancho;
    }

    public int getVida() {
        return this.vida;
    }

	public int getMana() {
	    return mana;
	}

}