package juego;

import java.awt.Color;
import entorno.Entorno;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Jefe {
	private int x;
    private int y;
    private int ancho;
    private int alto;
    private int direccioninicial;
    private Image imagen;
    private int vida;
    
    
    public Jefe(int x, int y, int ancho, int alto, Color color) {
    	this.x = x;
    	this.y = y;
    	this.ancho = ancho;
    	this.alto = alto;
    	this.direccioninicial = 1;
    	this.imagen = new ImageIcon("Imagenes/golbat.gif").getImage();
    	this.vida = 100;
    	
    	
    }
    
    public void dibujar(Entorno entorno) {
    	entorno.dibujarImagen(imagen, x, y, 0);
    }
    
    public void mover(Entorno entorno) {
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
    
    public boolean colisionaPorDerecha(Entorno entorno) {
        return this.x + this.ancho/2 >= entorno.ancho()-175;
    }

    public boolean colisionaPorIzquierda(Entorno entorno) {
        return this.x - this.ancho/2 <= 0;
    }

    public int restarvida(int a) {
    	if(vida > 0) {
    	vida = vida - a;
    	}
    		
    return vida;

}
    public String mostrarvida() {
        String vidamostrada = Integer.toString(vida);
        return vidamostrada;
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

	public void setVida(int vida) {
		this.vida = vida;
	}

    
    
    

}