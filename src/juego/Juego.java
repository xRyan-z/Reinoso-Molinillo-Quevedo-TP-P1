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
	{
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
			
		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);
		this.dibujarObjetos();
		}	
	
	public void dibujarObjetos() {
		this.Gondolf.dibujar(entorno);
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
