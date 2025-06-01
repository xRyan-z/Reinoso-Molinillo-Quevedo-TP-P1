package juego;

import java.awt.Color;
import entorno.Entorno;

public class Hechizos {
	// Posición del centro del hechizo
	private double x;
	private double y;
	
    private double diametro; 
    private Color color;

 // Constructor: recibe origen y destino, diámetro y color
    public Hechizos(int origenX, int origenY, int destinoX, int destinoY, int diametro, Color color) {
    	this.x = origenX; // Se guarda la coordenada X del punto de origen
        this.y = origenY; // Se guarda la coordenada Y del punto de origen
        this.diametro = diametro; // Se guarda el diámetro del hechizo
        this.color = color; // Se guarda el color del hechizo

    }
 // Dibuja el hechizo como un círculo en pantalla usando la librería Entorno
    public void dibujar(Entorno entorno) {
    	entorno.dibujarCirculo(x, y, diametro, color);
    }
 // Verifica si el hechizo colisiona con un enemigo común
    public boolean colisionaCon(Enemigo enemigo) {
    	// Calcula distancia entre el hechizo y el enemigo
        double dx = this.x - enemigo.getX();
        double dy = this.y - enemigo.getY();
        double distancia = Math.sqrt(dx * dx + dy * dy);
     // Retorna true si la distancia entre centros es menor que la suma de los radios
        return distancia < this.diametro / 2 + 20; // 20 es un radio aproximado del murciélago
    }
 // Verifica si el hechizo colisiona con el jefe
    public boolean colisionaCon(Jefe jefe) {
    	// Calcula distancia entre el hechizo y el jefe
        double dx = this.x - jefe.getX();
        double dy = this.y - jefe.getY();
        double distancia = Math.sqrt(dx * dx + dy * dy);
     // Retorna true si hay colisión con el jefe
        return distancia < this.diametro / 2 + 20; // 20 es un radio aproximado del jefe
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    // Getter y setter de Y
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Getter y setter de diametro
    public double getDiametro() {
        return diametro;
    }

    public void setDiametro(double diametro) {
        this.diametro = diametro;
    }

    // Getter y setter de color
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

