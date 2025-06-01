package juego;

import java.awt.Color;
import entorno.Entorno;

public class Menu {

	// Coordenadas del centro del menú en pantalla
	private int x;
	private int y;
	// Dimensiones del menú
	private int ancho;
	private int alto;
	
	private Color color;
	private String texto;
	private String fuente;
    private int tamañoFuente;
    private Color colorTexto;
 // Colores predefinidos para usar en los menús (tipo madera)
    private static final Color brown = new Color(90, 45, 15);
    private static final Color light_brown = new Color (205, 133, 63);
	
 // Constructor: recibe posición, tamaño y color. Inicializa valores por defecto del texto.
	public Menu (int x , int y, int alto, int ancho, Color color) {
		this.x = x; // Posición horizontal del centro del menú
		this.y = y; // Posición vertical del centro del menú
		this.alto = alto; // Altura del menú (botones)
		this.ancho = ancho; // Ancho del menú (botones)
		this.color = color; // Color de fondo del menú (botones)
		this.texto = ""; // Texto vacío por defecto
		this.fuente = "Showcard Gothic"; // Fuente predeterminada
        this.tamañoFuente = 17; // Tamaño de fuente por defecto
        this.colorTexto = Color.WHITE; // Texto blanco por defecto
    
	}
	// Setter para cambiar el texto que se muestra
	public void setTexto(String texto) {
		this.texto= texto;
	}
	// Setter para cambiar la fuente, tamaño y color del texto
	public void setFuente(String fuente, int tamaño, Color colorTexto) {
        this.fuente = fuente;
        this.tamañoFuente = tamaño;
        this.colorTexto = colorTexto;
    }
	// Getter estático para obtener el color marrón oscuro
	public static Color getBrown() {
        return brown;
    }
	// Getter estático para obtener el marrón claro
	public static Color getlight_brown() {
        return light_brown;
    }
	// Dibuja el menú en pantalla
    public void dibujar(Entorno entorno) {
    	entorno.dibujarRectangulo(x, y, ancho, alto, 0, color); // Dibuja el rectángulo del menú (botones)
    	entorno.cambiarFont(fuente, tamañoFuente, colorTexto); // Configura la fuente y color del texto
    	// Calcula la posición horizontal del texto para centrarlo
        int textoAnchoAprox = texto.length() * (tamañoFuente / 2); 
        int textoX = x - textoAnchoAprox / 2;
     // Calcula la posición vertical del texto
        int textoY = y + (tamañoFuente / 3); // Escribe el texto en pantalla
    	
        entorno.escribirTexto(texto, textoX, textoY);
	}
 // Getters para acceder a la posición y tamaño del menú
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
