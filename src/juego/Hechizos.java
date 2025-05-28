package juego;

import java.awt.Color;
import entorno.Entorno;

public class Hechizos {
	private double x;
    private double y;
    private double diametro;
    private Color color;

    public Hechizos(int origenX, int origenY, int destinoX, int destinoY, int diametro, Color color) {
    	this.x = origenX;
        this.y = origenY;
        this.diametro = diametro;
        this.color = color;

    }
    public void dibujar(Entorno entorno) {
    	entorno.dibujarCirculo(x, y, diametro, color);
    }
    
    public boolean colisionaCon(Enemigo enemigo) {
        double dx = this.x - enemigo.getX();
        double dy = this.y - enemigo.getY();
        double distancia = Math.sqrt(dx * dx + dy * dy);
        return distancia < this.diametro / 2 + 20; // 20 es un radio aproximado del murciélago
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

