package juego;

import java.awt.Color;

import entorno.Entorno;

public class Gondolf {
    private int x;
    private int y;
    private int alto;
    private int ancho;
    private Color color;
    private int vida;
    private int mana;


    public Gondolf (int x , int y, int alto, int ancho, Color color) {
        this.x = x;
        this.y = y;
        this.alto = alto;
        this.ancho = ancho;
        this.color = color; 

    }

        public void dibujar (Entorno entorno) {
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, color);



    }

    public void MoverIzq() {
        this.x -=5 ;
    }
    public void MoverDer() {
        this.x +=5 ;
    }
    public void MoverArriba() {
        this.y -=5 ;
    }
    public void MoverAbajo() {
        this.y +=5 ;
    }

    public boolean colisionaPorDerecha(Entorno entorno) {
        return this.x + this.ancho/2 >= entorno.ancho()-175;
    }

    public boolean colisionaPorIzquierda(Entorno entorno) {
        return this.x - this.ancho/2 <= 0;
    }

    public boolean colisionaPorArriba(Entorno entorno) {
        return this.y - this.alto / 2 <= 5;
    }

    public boolean colisionaPorAbajo(Entorno entorno) {
        return this.y + this.alto / 2 >= entorno.alto()-5;
    }

    public boolean colisionaCon(Piedra p) {
        return Math.abs(this.x - p.getX()) < (this.ancho / 2 + p.getAncho() / 2) &&
               Math.abs(this.y - p.getY()) < (this.alto / 2 + p.getAlto() / 2);

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

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }





}
