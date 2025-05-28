package juego;
import entorno.Entorno;
import java.awt.Image;
import java.awt.Toolkit;

public class Disparo {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private Image image;
	
	public Disparo(int x,int y, int alto, int ancho) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.image = Toolkit.getDefaultToolkit().getImage("Imagenes/Disparo.png");
	}
	
	
	public void mover() {
		this.y += 4;
		
	}
	 public void dibujar (Entorno entorno) {
		 entorno.dibujarImagen(image, x, y, 0, 1);
	 }
	 
	 public boolean ColisionaCon(int otroX, int otroY, int margen) {
	        return Math.abs(this.x - otroX) < margen && Math.abs(this.y - otroY) < margen;
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
	 
	 
}