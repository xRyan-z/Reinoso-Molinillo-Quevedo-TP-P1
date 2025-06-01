package juego;

import java.awt.Color;
import entorno.Entorno;

public class Boton {
	
	// Coordenadas del centro del botón
	private int x;
    private int y;
 // Dimensiones del botón
    private int ancho;
    private int alto;
   
    private Color colorFondo; 
    private String texto; 
    private String fuente; 
    private int tamañoFuente;
    private Color colorTexto;
    private boolean seleccionado;
    private static final Color GRIS_OSCURO = new Color(64, 64, 64); // Color gris oscuro usado cuando el botón está seleccionado

    // Constructor: inicializa posición, tamaño, color y valores por defecto del texto
    public Boton(int x, int y, int ancho, int alto, Color colorFondo) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.colorFondo = colorFondo;
        this.texto = ""; // Texto vacío por defecto
        this.fuente = "Impact";
        this.tamañoFuente = 17;
        this.colorTexto = Color.WHITE;
        this.seleccionado = false; // No seleccionado al iniciar
    }

 // Método para cambiar el texto del botón
    public void setTexto(String texto) {
        this.texto = texto;
    }

 // Método para cambiar fuente, tamaño y color del texto
    public void setFuente(String fuente, int tamaño, Color colorTexto) {
        this.fuente = fuente;
        this.tamañoFuente = tamaño;
        this.colorTexto = colorTexto;
    }

 // Método para marcar o desmarcar el botón como seleccionado
    public void setSeleccionado(boolean sel) {
        this.seleccionado = sel;
    }

 // Devuelve si el botón está seleccionado o no
    public boolean isSeleccionado() {
        return this.seleccionado;
    }

 // Dibuja el botón en pantalla
    public void dibujar(Entorno entorno) {
    	Color colorParaDibujar = seleccionado ? GRIS_OSCURO : colorFondo; // Usa gris oscuro si está seleccionado, si no, su color de fondo normal
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, colorParaDibujar); // Dibuja el rectángulo del botón
        entorno.cambiarFont(fuente, tamañoFuente, colorTexto);  // Cambia la fuente para el texto

     // Calcula la posición horizontal aproximada del texto para centrarlo
        int textoAnchoAprox = texto.length() * (tamañoFuente / 2);
        int textoX = x - textoAnchoAprox / 2;
        int textoY = y + (tamañoFuente / 3); // Calcula la posición vertical del texto

        entorno.escribirTexto(texto, textoX, textoY); // Dibuja el texto en el botón
    }

 // Verifica si un punto (px, py) está dentro del área del botón
    public boolean estaDentro(int px, int py) {
    	// Coordenadas de los bordes del botón
        int left = x - ancho / 2;
        int right = x + ancho / 2;
        int top = y - alto / 2;
        int bottom = y + alto / 2;

        return (px >= left && px <= right && py >= top && py <= bottom); // Devuelve true si el punto está dentro del área
    }
    
 // Devuelve la coordenada x del botón
    public int getX() {
        return this.x;
    }

 // Devuelve la coordenada y del botón
    public int getY() {
        return this.y;
    }
}
