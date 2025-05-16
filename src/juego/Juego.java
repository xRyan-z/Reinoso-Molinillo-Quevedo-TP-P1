package juego;


import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Gondolf Gondolf;
	private Image fondo;
	private Enemigo[] enemigos = new Enemigo[50];
	private int enemigosVivos = 0;
	private int totalCreados = 0;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		this.fondo = new ImageIcon("Imagenes/Pisodetierra.png").getImage();
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
		
		this.Gondolf = new Gondolf(300, 300, 25, 10, Color.red);
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{	entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);
		this.dibujarObjetos();
		// Procesamiento de un instante de tiempo
		// ...
		if(entorno.estaPresionada(entorno.TECLA_DERECHA) &&  !Gondolf.colisionaPorDerecha(entorno)) {
			Gondolf.MoverIzq();
			}
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !Gondolf.colisionaPorIzquierda(entorno)) {
			Gondolf.MoverDer();
			}
		if(entorno.estaPresionada(entorno.TECLA_ARRIBA)&& !Gondolf.colisionaPorArriba(entorno)) {
			Gondolf.MoverArriba();
			}
		if(entorno.estaPresionada(entorno.TECLA_ABAJO)&& !Gondolf.colisionaPorAbajo(entorno)) {
			Gondolf.MoverAbajo();
			}
		if (enemigosVivos < 10 && totalCreados < 50) {
		    enemigos[totalCreados] = generarMurcielagoAleatorio(); // genera uno nuevo
		    enemigosVivos++;
		    totalCreados++;
		}
		 // Mover, dibujar y colisionar murciélagos
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] != null) {
                enemigos[i].moverHacia(Gondolf.getX(), Gondolf.getY());
                //enemigos[i].dibujar(entorno);

                if (enemigos[i].colisionaCon(Gondolf.getX(), Gondolf.getY(), 20)) {
                    enemigos[i] = null;
                    enemigosVivos--;
                   // Gondolf.restarVida(10); // método que vos deberías tener en Gondolf
                }
            }
	  }   
	} 	
	public void dibujarObjetos() {
	this.Gondolf.dibujar(entorno);
	for (int i = 0; i < enemigos.length; i++) {
		        if (enemigos[i] != null) {
		            enemigos[i].dibujar(entorno);
		        }
		    }
		}
	}
	private Enemigo generarMurcielagoAleatorio() {
	    int lado = (int)(Math.random() * 4); // 0=arriba, 1=derecha, 2=abajo, 3=izquierda
	    int x = 0;
	    int y = 0;

	    switch (lado) {
	        case 0: x = (int)(Math.random() * 625); y = -20; break;       // arriba
	        case 1: x = 620; y = (int)(Math.random() * 600); break;       // derecha
	        case 2: x = (int)(Math.random() * 625); y = 620; break;       // abajo
	        case 3: x = -20; y = (int)(Math.random() * 600); break;       // izquierda
	    }

	    return new Enemigo(x, y, 20, 20);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
