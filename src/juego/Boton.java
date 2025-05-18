package juego;

import java.awt.Color;
import entorno.Entorno;

public class Boton {

	private int x;
    private int y;
    private int ancho;
    private int alto;
    private Color colorFondo;
    private String texto;
    private String fuente;
    private int tamañoFuente;
    private Color colorTexto;
    private boolean seleccionado;
    private static final Color GRIS_OSCURO = new Color(64, 64, 64);

    public Boton(int x, int y, int ancho, int alto, Color colorFondo) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.colorFondo = colorFondo;
        this.texto = "";
        this.fuente = "Impact";
        this.tamañoFuente = 17;
        this.colorTexto = Color.WHITE;
        this.seleccionado = false;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setFuente(String fuente, int tamaño, Color colorTexto) {
        this.fuente = fuente;
        this.tamañoFuente = tamaño;
        this.colorTexto = colorTexto;
    }

    public void setSeleccionado(boolean sel) {
        this.seleccionado = sel;
    }

    public boolean isSeleccionado() {
        return this.seleccionado;
    }

    public void dibujar(Entorno entorno) {
    	Color colorParaDibujar = seleccionado ? GRIS_OSCURO : colorFondo;
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, colorParaDibujar);
        entorno.cambiarFont(fuente, tamañoFuente, colorTexto);

        int textoAnchoAprox = texto.length() * (tamañoFuente / 2);
        int textoX = x - textoAnchoAprox / 2;
        int textoY = y + (tamañoFuente / 3);

        entorno.escribirTexto(texto, textoX, textoY);
    }

    public boolean estaDentro(int px, int py) {
        int left = x - ancho / 2;
        int right = x + ancho / 2;
        int top = y - alto / 2;
        int bottom = y + alto / 2;

        return (px >= left && px <= right && py >= top && py <= bottom);
    }
}