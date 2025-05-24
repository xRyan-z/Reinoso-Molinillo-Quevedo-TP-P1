package juego;
import java.awt.Image;
import entorno.Entorno;
import javax.swing.ImageIcon;
	public class Enemigo {
	    private int x;
	    private int y;
	    
	    private Image imagenes;

	    public Enemigo(int x, int y, int ancho, int alto) {
	        this.x = x;
	        this.y = y;
	        this.imagenes = new ImageIcon("Imagenes/mmurcielago.gif").getImage();
	        
	    }
	    
	    // Movimiento hacia el objetivo (Gondolf)
	    public void moverHacia(int objetivoX, int objetivoY) {
	        double dx = objetivoX - this.x;
	        double dy = objetivoY - this.y;
	        double distancia = Math.sqrt(dx * dx + dy * dy);

	        if (distancia > 0) {
	            this.x += (int)(dx / distancia * 2); // velocidad
	            this.y += (int)(dy / distancia * 2);
	        }
	    }

	    // Dibujar el murciélago 
	    public void dibujar(Entorno entorno) {
	    	entorno.dibujarImagen(imagenes, x, y, 0);
	    }

	    
	    // Método para detectar si colisiona con el jugador (Gondolf)
	    public boolean colisionaCon(int otroX, int otroY, int margen) {
	        return Math.abs(this.x - otroX) < margen && Math.abs(this.y - otroY) < margen;
	    }

	    // Getters
	    public int getX() { return x; }
	    public int getY() { return y; }

	}

	
