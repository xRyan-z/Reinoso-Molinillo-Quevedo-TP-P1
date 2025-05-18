package juego;

import java.awt.Image;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class Piedra {
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private Image imagen;

    public Piedra(int x,int y,int ancho, int alto, String imagen) {
    this.x = x;
    this.y = y;
    this.ancho = ancho;
    this.alto = alto;
    this.imagen = new ImageIcon("Imagenes/piedra.png").getImage();

}
    public void dibujar(Entorno entorno) {
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