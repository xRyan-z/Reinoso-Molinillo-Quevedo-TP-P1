package juego;

import java.awt.Image;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class Piedra {
    private int x; // Posición X de la piedra
    private int y; // Posición Y de la piedra
    private int ancho; // Ancho de la piedra
    private int alto; // Alto de la piedra
    private Image imagen; // imagen de la piedra piedra

    public Piedra(int x,int y,int ancho, int alto) { // Inicializo los parametros de la piedra
    this.x = x;
    this.y = y;
    this.ancho = ancho;
    this.alto = alto;
    this.imagen = new ImageIcon("Imagenes/piedra.png").getImage();

}
    public void dibujar(Entorno entorno) { // Metodo que dibuja la piedra
		entorno.dibujarImagen(imagen, x, y,0,1); // 1 = escala (100%)
	}


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}