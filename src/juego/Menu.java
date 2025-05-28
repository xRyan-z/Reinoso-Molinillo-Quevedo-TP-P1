package juego;

import java.awt.Color;
import entorno.Entorno;

public class Menu {

	private int x;
	private int y;
	private int ancho;
	private int alto;
	private Color color;
	private String texto;
	private String fuente;
    private int tamañoFuente;
    private Color colorTexto;
    private static final Color brown = new Color(90, 45, 15);
    private static final Color light_brown = new Color (205, 133, 63);
	
	public Menu (int x , int y, int alto, int ancho, Color color) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.color = color; 
		this.texto = "";
		this.fuente = "Showcard Gothic"; 
        this.tamañoFuente = 17; 
        this.colorTexto = Color.WHITE;
    
	}
	
	public void setTexto(String texto) {
		this.texto= texto;
	}
	
	public void setFuente(String fuente, int tamaño, Color colorTexto) {
        this.fuente = fuente;
        this.tamañoFuente = tamaño;
        this.colorTexto = colorTexto;
    }
	public static Color getBrown() {
        return brown;
    }
	public static Color getlight_brown() {
        return light_brown;
    }
	
    public void dibujar(Entorno entorno) {
    	entorno.dibujarRectangulo(x, y, ancho, alto, 0, color);
    	entorno.cambiarFont(fuente, tamañoFuente, colorTexto);
    	  
        int textoAnchoAprox = texto.length() * (tamañoFuente / 2); 
        int textoX = x - textoAnchoAprox / 2;
        int textoY = y + (tamañoFuente / 3);
    	
        entorno.escribirTexto(texto, textoX, textoY);
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
