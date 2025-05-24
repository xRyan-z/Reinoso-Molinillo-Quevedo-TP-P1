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
	private Piedra[] piedras = new Piedra [4];
	private boolean juegoTerminado = false;
	private Pocion[] pociones= new Pocion [20];
	
	private Enemigo[] enemigos = new Enemigo[10];
	private int enemigosVivos = 0;
	private int totalCreados = 0;
	
	private Menu tituloHechizos;
	private Menu pvida;
	private Menu pmana;
    private Boton botonBombaAgua;
    private Boton botonTormentaFuego;
    private Boton botonSeleccionado;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego() {
		this.fondo = new ImageIcon("Imagenes/Pisodetierra.png").getImage();
		// Idnicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
		this.Gondolf = new Gondolf(300, 300, 50, 50, 100, 100);
		this.piedras[0] = new Piedra (200, 300, 50,50, "Imagenes/piedra.png");
		this.piedras[1] = new Piedra (300, 100, 50,50, "Imagenes/piedra.png");
		this.piedras[2] = new Piedra (500, 300, 50,50, "Imagenes/piedra.png");
		this.piedras[3] = new Piedra (250, 500, 50,50, "Imagenes/piedra.png");
		
		int panelWidth = entorno.ancho();  // 800
		int menuAncho = 150;
		int menuAlto = 90;
		int margenDerecho = 10;
		int posX = panelWidth - menuAncho / 2 - margenDerecho;
	
		int tituloAlto = 40;
		int posY_Titulo = 50;
		int posY_BombaAgua = 190;  
		int posY_TormentaFuego = 290;
		
		botonBombaAgua = new Boton(posX, posY_BombaAgua, menuAncho, menuAlto, Color.black);
        botonBombaAgua.setTexto("BOMBA DE AGUA");

        botonTormentaFuego = new Boton(posX, posY_TormentaFuego, menuAncho, menuAlto, Color.black);
        botonTormentaFuego.setTexto("TORMENTA DE FUEGO");

        tituloHechizos = new Menu(posX, posY_Titulo, tituloAlto, menuAncho, Color.black);
        tituloHechizos.setTexto("HECHIZOS");
        tituloHechizos.setFuente("Impact", 20, Color.WHITE);
        
        pvida = new Menu(posX, posY_Titulo + 400, tituloAlto , menuAncho, Color.red); 
        pvida.setFuente("Impact", 20, Color.BLACK);
        
        pmana = new Menu(posX, posY_Titulo + 450, tituloAlto , menuAncho, Color.blue);
        pmana.setFuente("Impact", 20, Color.BLACK);
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	private void reiniciarJuego() {
	    Gondolf = new Gondolf(300, 300, 50, 50, 100, 100);
	    enemigos = new Enemigo[10];
	    enemigosVivos = 0;
	    totalCreados = 0;
	    juegoTerminado = false;
	    for (int i = 0; i < pociones.length; i++) { // Limpiar todas las pociones anteriores
	        pociones[i] = null;
	    }
	}
	public void tick()
	{ 		//System.out.println(">> tick() frame");
		// Procesamiento de un instante de tiempo
		// ...
		     
		 //  Una vez que la vida del mago llega a 0, se muestra en pantalla: (reinicia ENTER).
	    if (juegoTerminado) {
	    	Image imagenFondo = new ImageIcon("Imagenes/go.png").getImage();
	    	entorno.dibujarImagen(imagenFondo, entorno.ancho() / 2, entorno.alto() / 2, 0);
	        if (entorno.estaPresionada(entorno.TECLA_ENTER)) {
	            reiniciarJuego();
	        }

	        return;
	    }

	    // Chequeo real de vida para activar game over
	    if (Gondolf.getVida() <= 0) {
	        juegoTerminado = true;
	        return; // cancelo el resto del tick
	    }
	    
		if (entorno.estaPresionada('a') && !Gondolf.colisionaPorIzquierda(entorno) && !colisionaConPiedra(-5, 0)) {
		    Gondolf.MoverIzq();
		}
		if (entorno.estaPresionada('d') && !Gondolf.colisionaPorDerecha(entorno) && !colisionaConPiedra(5, 0)) {
		    Gondolf.MoverDer();
		}
		if (entorno.estaPresionada('w') && !Gondolf.colisionaPorArriba(entorno) && !colisionaConPiedra(0, -5)) {
		    Gondolf.MoverArriba();
		}
		if (entorno.estaPresionada('s') && !Gondolf.colisionaPorAbajo(entorno) && !colisionaConPiedra(0, 5)) {
		    Gondolf.MoverAbajo();
		}
	
		
		if (enemigosVivos < 10 && totalCreados < 50) {
			    for (int i = 0; i < enemigos.length; i++) {
			        if (enemigos[i] == null) {
			            enemigos[i] = generarMurcielagoAleatorio();
			            enemigosVivos++;
			            totalCreados++;
			            break; // salimos del for después de generar uno
			        }
			    }
		}
		 // Mover, dibujar y colisionar murciélagos
        for (int i = 0; i < enemigos.length; i++) {
        	  Enemigo e = enemigos[i];
              if (e != null) {
                  e.moverHacia(Gondolf.getX(), Gondolf.getY());
                  
                  if (e.colisionaCon(Gondolf.getX(), Gondolf.getY(), 20)) {
                      enemigos[i] = null;
                      enemigosVivos--;
                      Gondolf.restarvida();
                      
                      if (Gondolf.getVida() <= 0) {
                          juegoTerminado = true;
                          return;
                      }
                   // Murcielagos=null y vida del mago <=30, se deposita mediante el murcielago una pocion.
                      	if (Gondolf.getVida() <= 30) {
                    	  for(int j=0; j<pociones.length; j++) {
                    		  if (pociones[j]==null){
                    			  pociones[j]= new Pocion (e.getX(), e.getY());
                    			  break;
                    		}
                          }
                       }
                    }
                  }   
               }

        			for (int i = 0; i < pociones.length; i++) {
        				if (pociones[i] != null) {
        					// Dibujar la poción
        					pociones[i].dibujar(entorno);

        					// Si ya pasaron 6 segundos, eliminarla
        					if (pociones[i].expirada()) {
        						pociones[i] = null;
                }
            }
        }
        
        
     // Detectar click sobre botones de hechizo
        if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
            int mx = entorno.mouseX();
            int my = entorno.mouseY();

            if (botonBombaAgua.estaDentro(mx, my)) {
                seleccionarBoton(botonBombaAgua);
            } else if (botonTormentaFuego.estaDentro(mx, my)) {
                seleccionarBoton(botonTormentaFuego);
            }
        }
        
        entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);

        this.dibujarObjetos();
        
        tituloHechizos.dibujar(entorno);
        botonBombaAgua.dibujar(entorno);
        botonTormentaFuego.dibujar(entorno);
        pvida.setTexto("Vida: " + Gondolf.mostrarvida()+"%"); // Muestra la vida del personaje
        pmana.setTexto("Mana: " + Gondolf.mostrarmana()+"%"); //Muestra la vida del personaje
        pvida.dibujar(entorno);
        pmana.dibujar(entorno);
        
        for (int i = 0; i < pociones.length; i++) {
            Pocion p = pociones[i];
            if (p != null && p.colisionaCon(Gondolf.getX(), Gondolf.getY(), 20)) {
                Gondolf.sumarvida(); 
                pociones[i] = null;  // Quita la poción del suelo
            }
        }
	}
    

    private void seleccionarBoton(Boton b) {
        if (botonSeleccionado != null) {
            botonSeleccionado.setSeleccionado(false);
        }
        botonSeleccionado = b;
        botonSeleccionado.setSeleccionado(true);
    }
    
 // Método para lanzar hechizo que limpia la selección
    public void lanzarHechizo() {
        if (botonSeleccionado != null) {
            // lógica para lanzar el hechizo según botonSeleccionado
            // ...

            // Luego deseleccionamos el botón
            botonSeleccionado.setSeleccionado(false);
            botonSeleccionado = null;
        }
    }
	
	public void dibujarObjetos() {
		Gondolf.dibujar(entorno);
		for (int i = 0; i < piedras.length; i++) {
			if (piedras[i] != null) {
				piedras[i].dibujar(entorno);
			}
		}
        for (Enemigo e : enemigos) {
            if (e != null) e.dibujar(entorno);
		  }
        for (int i=0; i< pociones.length; i++) {
        	if (pociones[i]!= null) {
        		pociones[i].dibujar(entorno);
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
	
	public boolean colisionaConPiedra(int dx, int dy) {
	    for (Piedra p : piedras) {
	        // Calculamos dónde estaría Gondolf si se moviera
	        int futuroX = Gondolf.getX() + dx;
	        int futuroY = Gondolf.getY() + dy;

	        // Verificamos si en esa posición estaría tocando una piedra
	        if (Math.abs(futuroX - p.getX()) < (Gondolf.getAncho() / 2 + p.getAncho() / 2) &&
	            Math.abs(futuroY - p.getY()) < (Gondolf.getAlto() / 2 + p.getAlto() / 2)) {
	            return true; // Hay colisión
	        }
	    }
	    return false; // No hay colisión
	}
	
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
		
	}
}