package juego;

import java.awt.Color;

import entorno.Entorno;

public class Gondolf {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private Color color;
	
	public Gondolf (int x , int y, int alto, int ancho, Color color) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.color = color; 
		
	}
		
		public void dibujar (Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, color);
		
		
		
	}
	public int getX() {
		return x;
	}

	public void MoverIzq() {
		this.x +=5 ;
	}
	public void MoverDer() {
		this.x -=5 ;
	}
	public void MoverArriba() {
		this.y -=5 ;
	}
	public void MoverAbajo() {
		this.y +=5 ;
	}
	
	public boolean colisionaPorDerecha(Entorno entorno) {
		return this.x + this.ancho/2 >= entorno.ancho()-175;
	}

	public boolean colisionaPorIzquierda(Entorno entorno) {
		return this.x - this.ancho/2 <= 0;
	}
	
	public boolean colisionaPorArriba(Entorno entorno) {
	    return this.y - this.alto / 2 <= 5;
	}

	public boolean colisionaPorAbajo(Entorno entorno) {
	    return this.y + this.alto / 2 >= entorno.alto()-5;
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

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	
	
	
	

}
