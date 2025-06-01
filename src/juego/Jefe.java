package juego;

import entorno.Entorno;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Jefe {
	private int x; // Posición x del jefe
    private int y; // Posición y del jefe
    private int ancho; // Ancho del Jefe
    private int alto; // Alto del personaje
    private int direccioninicial; // Dirección donde comienza a moverse el jefe
    private Image imagen; //Imagen del Jefe
    private int vida; // Vida el Jefe
    
    
    public Jefe(int x, int y, int ancho, int alto) { // Inicializamos al Jefe
    	this.x = x;
    	this.y = y;
    	this.ancho = ancho;
    	this.alto = alto;
    	this.direccioninicial = 1;
    	this.imagen = new ImageIcon("Imagenes/golbat.gif").getImage(); // Asignamos la imagen al jefe
    	this.vida = 100; // Asignamos la cantidad de vida al jefe
    	
    	
    }
    
    public void dibujar(Entorno entorno) { // Metodo para dibujar al jefe
    	entorno.dibujarImagen(imagen, x, y, 0);
    }
    
    public void mover(Entorno entorno) { // Metodo para mover al jefe
        if (this.y < 50) {
            this.y += 2; // Primero baja hasta y == 50
        } else {
            // Cuando ya bajó, se mueve en la dirección actual
            this.x += 3 * direccioninicial;

            // Revisa colisiones con los bordes
            if (colisionaPorDerecha(entorno)) {
                direccioninicial = -1; // Cambia a izquierda
            } else if (colisionaPorIzquierda(entorno)) {
                direccioninicial = 1; // Cambia a derecha
            }
        }
    }
    
    public boolean colisionaPorDerecha(Entorno entorno) { //Metodo que revisa que colisione por derecha - el menu
        return this.x + this.ancho/2 >= entorno.ancho()-175;
    }

    public boolean colisionaPorIzquierda(Entorno entorno) { //Metodo que revisa que colisione por izquierda
        return this.x - this.ancho/2 <= 0;
    }

    public boolean colisionaCon(int otroX, int otroY, int margen) { // Metodo que revisa que colisione con algun objeto
        return Math.abs(this.x - otroX) < margen && Math.abs(this.y - otroY) < margen;
    }
    
    public int restarvida(int a) { // se le resta la cantidad a de vida al jefe
    	if(vida > 0) {
    	vida = vida - a;
    	}
    		
    return vida;

}
    
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public int getVida() {
		return vida;
	}


    
    
    

}