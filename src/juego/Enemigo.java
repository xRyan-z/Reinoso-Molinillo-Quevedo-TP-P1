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
	    
	    public static Enemigo generarMurcielagoAleatorio() {
		    int lado = (int)(Math.random() * 4); // 0=arriba, 1=derecha, 2=abajo, 3=izquierda
		    int x = 0;
		    int y = 0;
		    
		    switch (lado) {
		        case 0: x = (int)(Math.random() * 625); y = -20; break;       // arriba
		        case 1: x = 620; y = (int)(Math.random() * 600); break;       // derecha
		        case 2: x = (int)(Math.random() * 625); y = 620; break;       // abajo
		        case 3: x = -20; y = (int)(Math.random() * 600); break;       // izquierda
		    }

		    return new Enemigo(x, y, 20, 20);
		}
		

	}

	
