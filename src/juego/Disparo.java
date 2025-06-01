package juego;
import entorno.Entorno;
import java.awt.Image;
import java.awt.Toolkit;

public class Disparo {
	private int x; // Posición x del Disparo en la pantalla
	private int y; // Posición Y del Disparo en la pantalla
	private int alto; // Alto del Disparo
	private int ancho; // Ancho del disparo
	private Image image; // Importa una imagen
	
	public Disparo(int x,int y, int alto, int ancho) {  //Parametros para inicializar el disparo
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.image = Toolkit.getDefaultToolkit().getImage("Imagenes/Disparo.png");
	}
	
	
	public void mover() { // Metodo que mueve el disparo
		this.y += 3;
		
	}
	 public void dibujar (Entorno entorno) { // Metodo que dibuja el Disparo en la pantalla
		 entorno.dibujarImagen(image, x, y, 0, 1);
	 }
	 
	 public boolean ColisionaCon(int otroX, int otroY, int margen) { // Metodo para verificar si colisiona con otro objeto
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