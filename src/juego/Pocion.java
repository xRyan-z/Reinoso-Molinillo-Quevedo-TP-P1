package juego;

	import java.awt.Image;
	import javax.swing.ImageIcon;
	import entorno.Entorno;

	public class Pocion {
	    private int x;
	    private int y;
	    private Image imagen;
	    private long tiempoCreacion;

	    public Pocion(int x, int y) {
	        this.x = x;
	        this.y = y;
	        this.imagen = new ImageIcon("Imagenes/pocion.png").getImage();
	        this.tiempoCreacion = System.currentTimeMillis();
	    }

	    public void dibujar(Entorno entorno) {
	        entorno.dibujarImagen(imagen, x, y, 0);
	    }

	    public boolean colisionaCon(int x, int y, int radio) { //colision con la pocion
	        return Math.hypot(this.x - x, this.y - y) < radio;
	    }

	    public int getX() { return x; }
	    public int getY() { return y; }
	    
	    public boolean expirada() {
	        return System.currentTimeMillis() - tiempoCreacion >= 6000; // 6 segundos
	    }
	    
	}
