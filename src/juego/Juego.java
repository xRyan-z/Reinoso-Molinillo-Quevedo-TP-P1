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
	private Gondolf Gondolf; // Creo un objeto Gondolf
	private Image fondo; // Creo un objeto Fondo
	private Piedra[] piedras = new Piedra [4]; // Creo una cierta cantidad de piedra
	private boolean juegoTerminado = false; // Bandera que funciona si el juego está terminado
	private boolean juegoGanado = false; // Bandera que funciona si el juego se ganó
	private Pocion[] pociones= new Pocion [20];
	
	private Enemigo[] enemigos = new Enemigo[10]; 
	private int enemigosVivos = 0; // cuenta los enemigos vivos
	private int totalCreados = 0; // cuenta los enemigos creados
	
	private Menu tituloHechizos;
	private Menu pvida; // creo un objeto menu que mostrará la vida del personaje
	private Menu pmana;// creo un objeto menu que mostrará el maná del personaje
	private Menu CDEnemigosEliminados; // creo un objeto menu que mostrará la cantidad de enemigos eliminados
    private Boton botonBombaAgua;
    private Boton botonTormentaFuego;
    private Boton botonSeleccionado;
    private Boton botonRociador;
    private Hechizos circuloAgua;
    private Hechizos circuloFuego;
    private Hechizos circuloRociador;
    boolean hechizoAguaActivo = false;
    boolean hechizoFuegoActivo = false;
    boolean hechizoRociadorActivo = false;
    private Image imagenFuego;
    private Image imagenAgua;
    private Image imagenRociador;
    private Jefe jefe; // Creo un objeto jefe
    private boolean jefeGenerado = false; // Bandera que se pone en true si el jefe está generado
    private Disparo[] disparo = new Disparo[10]; // Creo objetos disparo y que serán 10
    private int contadorDisparoJefe = 0; // Contador de disparos del jefe
    private int EnemigosEliminados; // Cuenta la cantidad de enemigos eliminados
    private Menu JefeVida; // Objeto tipo menú que mostrará la vida del personaje
	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego() {
		this.fondo = new ImageIcon("Imagenes/Pisodetierra.png").getImage(); // Muestra el fondo del juego
		// Idnicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600); // Proporciones de la pantalla
		
		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
		this.Gondolf = new Gondolf(300, 300, 50, 50, 100, 100);
		this.piedras[0] = new Piedra (200, 300, 50,50); // Creo una piedra en las posiciones dadas.
		this.piedras[1] = new Piedra (300, 100, 50,50); // Creo una piedra en las posiciones dadas.
		this.piedras[2] = new Piedra (500, 300, 50,50); // Creo una piedra en las posiciones dadas.
		this.piedras[3] = new Piedra (250, 500, 50,50); // Creo una piedra en las posiciones dadas.
		this.jefe = new Jefe(320,0,70,70); // Creo al jefe con los parametros dados.
		this.EnemigosEliminados = 0; // Inicio el contador en 0 de enemigos eliminados
		
		
		int panelWidth = entorno.ancho();  // 800
		int menuAncho = 150;
		int menuAlto = 90;
		int margenDerecho = 10;
		int posX = panelWidth - menuAncho / 2 - margenDerecho;
	
		int tituloAlto = 40;
		int posY_Titulo = 50;
		int posY_BombaAgua = 140;  
		int posY_TormentaFuego = 230;
		int posY_Rociador = 330;
		
		botonBombaAgua = new Boton(posX, posY_BombaAgua, menuAncho, menuAlto, Color.black);
        botonBombaAgua.setTexto("BOMBA DE AGUA"); //texto que muestra en pantalla
        botonBombaAgua.setFuente("impact", 17, Menu.getlight_brown()); //fuente, tamaño y color del texto

        botonTormentaFuego = new Boton(posX, posY_TormentaFuego, menuAncho, menuAlto, Color.black);
        botonTormentaFuego.setTexto("TORMENTA DE FUEGO"); //texto que muestra en pantalla
        botonTormentaFuego.setFuente("impact", 17, Menu.getlight_brown()); //fuente, tamaño y color del texto

        tituloHechizos = new Menu(posX, posY_Titulo, tituloAlto, menuAncho, Menu.getBrown());
        tituloHechizos.setTexto("HECHIZOS"); //texto que muestra en pantalla
        tituloHechizos.setFuente("impact", 22, Color.WHITE); //fuente, tamaño y color del texto
        
        botonRociador = new Boton(posX, posY_Rociador, menuAncho, menuAlto, Color.black);
        botonRociador.setTexto("ROCIADOR"); //texto que muestra en pantalla
        botonRociador.setFuente("impact", 17, Menu.getlight_brown()); //fuente, tamaño y color del texto
        
        pvida = new Menu(posX, posY_Titulo + 400, tituloAlto , menuAncho, Color.red);  // Posición de la barra de vida y color
        pvida.setFuente("Impact", 20, Color.BLACK); // Fuente y color de las letras
        
        pmana = new Menu(posX, posY_Titulo + 450, tituloAlto , menuAncho, Color.blue); // Posición de la barra de mana y color
        pmana.setFuente("Impact", 20, Color.BLACK);  // Fuente y color de las letras
        
        CDEnemigosEliminados = new Menu(posX, posY_Titulo + 500, tituloAlto , menuAncho, Color.ORANGE); // Posición de la barra de enemigos eliminados
        CDEnemigosEliminados.setFuente("Impact", 13, Color.BLACK); // Fuente y color de las letras
        
        JefeVida = new Menu(300,570, tituloAlto, menuAncho, Color.cyan); // Posición de la barra de la vida del jefe
        JefeVida.setFuente("Impact",13, Color.BLACK); // Fuente y color de las letra
         
        
        circuloAgua = new Hechizos(100, 100, 200, 200, 40, Color.green); //tamaño y color del circulo de Bomba de Agua
        circuloFuego = new Hechizos(300, 300, 600, 600, 80, Color.blue); //tamaño y color del circulo de Tormenta de Fuego
        circuloRociador = new Hechizos(150, 150, 250, 250, 50, Color.red); //tamaño y color del circulo de Rociador
        this.imagenFuego = new ImageIcon("Imagenes/tormentaDeFuego.gif").getImage();
        this.imagenAgua = new ImageIcon("Imagenes/bombaDeAgua.gif").getImage();
        this.imagenRociador = new ImageIcon("Imagenes/rociador.png").getImage();        
        	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	private void reiniciarJuego() { //Metodo que reinicia el juego con los parametros iniciales
		  Gondolf = new Gondolf(300, 300, 50, 50, 100, 100);
		    enemigos = new Enemigo[10];
		    enemigosVivos = 0;
		    totalCreados = 0;
		    EnemigosEliminados = 0;
		    disparo = new Disparo[500];
		    contadorDisparoJefe = 0;
		    jefeGenerado = false;
		    juegoTerminado = false;
		    botonSeleccionado = null;
		    hechizoAguaActivo = false;
		    hechizoFuegoActivo = false;
		    botonBombaAgua.setSeleccionado(false);
		    botonTormentaFuego.setSeleccionado(false);
		    botonRociador.setSeleccionado(false);

		    for (int i = 0; i < pociones.length; i++) {
		        pociones[i] = null;
		    }
		}
	
	private void reiniciarJuegoGanado() {  //Metodo que reinicia el juego con los parametros iniciales pero tambien reinicia la jefe
		  Gondolf = new Gondolf(300, 300, 50, 50, 100, 100);
		    enemigos = new Enemigo[10];
		    enemigosVivos = 0;
		    totalCreados = 0;
		    EnemigosEliminados = 0;
		    disparo = new Disparo[500];
		    contadorDisparoJefe = 0;
		    jefeGenerado = false;
		    juegoTerminado = false;
		    botonSeleccionado = null;
		    hechizoAguaActivo = false;
		    hechizoFuegoActivo = false;
		    hechizoRociadorActivo = false;
		    botonBombaAgua.setSeleccionado(false);
		    botonTormentaFuego.setSeleccionado(false);
		    botonRociador.setSeleccionado(false);
		    juegoGanado = false;
		    jefe = new Jefe(320, 0, 70, 70); // ← Esto es clave

		    for (int i = 0; i < pociones.length; i++) {
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
	    //  Una vez que la vida del jefe llega a 0, se muestra en pantalla: (reinicia ENTER).
	    if (juegoGanado) {
	    	Image imagenFondo = new ImageIcon("Imagenes/GANASTE.png").getImage();
	    	entorno.dibujarImagen(imagenFondo, entorno.ancho() / 2, entorno.alto() / 2, 0);
	        if (entorno.estaPresionada(entorno.TECLA_ENTER)) {
	            reiniciarJuegoGanado();
	        }

	        return;
	    }

	    entorno.escribirTexto("La cantidad de Enemigos eliminados son: "+ EnemigosEliminados, 700, 500); // escribe la cantidad de enemigos dentro del cuadro del menu
	    
	    // Chequeo real de vida para activar game over
	    if (Gondolf.getVida() <= 0) {
	        juegoTerminado = true;
	        return; // cancelo el resto del tick
	    }
	    
	    if (jefe != null && jefe.getVida() <= 0) { // activo la muerte del jefe
	        juegoGanado = true;
	        return; // cancelo el resto del tick
	    }
	    
	    if (entorno.estaPresionada('a') && !Gondolf.colisionaPorIzquierda(entorno) && !Gondolf.colisionaConPiedra(-3, 0, piedras)) { // movimiento del jugador y colisiones con piedra
		    Gondolf.MoverIzq();
		}
		if (entorno.estaPresionada('d') && !Gondolf.colisionaPorDerecha(entorno) && !Gondolf.colisionaConPiedra (3, 0,piedras)) {  // movimiento del jugador y colisiones con piedra
		    Gondolf.MoverDer();
		}
		if (entorno.estaPresionada('w') && !Gondolf.colisionaPorArriba(entorno) && !Gondolf.colisionaConPiedra(0, -3, piedras)) {  // movimiento del jugador y colisiones con piedra
		    Gondolf.MoverArriba();
		}
		if (entorno.estaPresionada('s') && !Gondolf.colisionaPorAbajo(entorno) && !Gondolf.colisionaConPiedra(0, 3, piedras)) {  // movimiento del jugador y colisiones con piedra
		    Gondolf.MoverAbajo();
		}  
		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0); // Dibujo el fondo
		
		if (enemigosVivos < 10 && totalCreados < 50) {
			    for (int i = 0; i < enemigos.length; i++) {
			        if (enemigos[i] == null) {
			            enemigos[i] = Enemigo.generarMurcielagoAleatorio();
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
                  
               // Verificar si el hechizo toca a este enemigo
                  if (hechizoAguaActivo && entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) { // se selecciona el boton
                      if (circuloAgua != null && circuloAgua.colisionaCon(e)) {
                    	  int ex = enemigos[i].getX();
                          int ey = enemigos[i].getY();
                    	  enemigos[i] = null; //si el hechizo colisiona con el murcielago, este muere
                          EnemigosEliminados++;
                          enemigosVivos--; //si el murcielago muere se resta de los enemigos 
                          if (Gondolf.getVida() <= 30) {
                        	  for (int j = 0; j < pociones.length; j++) {
                                  if (pociones[j] == null) {
                                      pociones[j] = new Pocion(ex, ey);
                                      break;
                          }
                      }
                     
                  }
                      }     
                      }           
                  
                  if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) { // se selecciona el boton
                	  if (hechizoFuegoActivo && circuloFuego != null && circuloFuego.colisionaCon(e)) {
                          enemigos[i] = null; //si el hechizo colisiona con el murcielago, este muere
                          EnemigosEliminados++; 
                          enemigosVivos--; //si el murcielago muere se resta de los enemigos 
                      }
                  }
                  if (circuloRociador !=null && circuloRociador.colisionaCon(e)) {
                      enemigos[i] = null;
                      EnemigosEliminados++;
                      enemigosVivos--;
                      Gondolf.restarMana(5); 
                  }
                  
                  if (e.colisionaCon(Gondolf.getX(), Gondolf.getY(), 20)) {
                      enemigos[i] = null;
                      enemigosVivos--;
                      EnemigosEliminados++;
                      Gondolf.restarvida(10);
                      
                      if (Gondolf.getVida() <= 0) {
                          juegoTerminado = true;
                          return;
                      }
                   	}
                 }
              }
        if (totalCreados == 50 && enemigosVivos == 0 && !jefeGenerado) {
			jefeGenerado = true;
			// Genera al jefe
		}
        if (hechizoAguaActivo && entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
        	if (circuloAgua !=null && circuloAgua.colisionaCon(jefe)) {
          	  jefe.restarvida(10); 
            }
        }
        if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
        	if (hechizoFuegoActivo && circuloFuego != null && circuloFuego.colisionaCon(jefe)) {
        		 jefe.restarvida(30); 
        	}
        }
        
        if (jefe.colisionaCon(Gondolf.getX(), Gondolf.getY(), 20)) {
        	Gondolf.restarvida(1);
        }
        
        
     // Desactivar hechizos (fuera del for)
        if (hechizoAguaActivo && entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) { // se selecciona el boton
            hechizoAguaActivo = false; // se desactiva el hechio luego de ser lanzado
            botonBombaAgua.setSeleccionado(false); // una vez que se lanza se deselecciona el boton
        }

        if (hechizoFuegoActivo && entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) { // se selecciona el boton
            hechizoFuegoActivo = false; // se desactiva el hechio luego de ser lanzado
            botonTormentaFuego.setSeleccionado(false); // una vez que se lanza se deselecciona el boton
            Gondolf.restarMana(20); // baja el mana por lanzamiento
        }
        if (hechizoRociadorActivo && entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) { // se selecciona el boton
            hechizoRociadorActivo = false; // se desactiva el hechizo luego de ser lanzado
            botonRociador.setSeleccionado(false); // una vez que se lanza se deselecciona el boton
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
            else {
            	if(botonRociador.estaDentro(mx, my)) {
            		seleccionarBoton(botonRociador);		
            	}
            }
        }
        
     // Activar el hechizo cuando se hace click en el botón
        if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
            if (botonBombaAgua.estaDentro(entorno.mouseX(), entorno.mouseY())) {
                hechizoAguaActivo = true;
                entorno.dibujarImagen(imagenAgua, circuloAgua.getX(), circuloAgua.getY(), 0, 0.2);
            }
        }

        // Si el hechizo está activo, actualizar su posición y dibujarlo
        if (hechizoAguaActivo && circuloAgua != null) {
            circuloAgua.setX(entorno.mouseX());
            circuloAgua.setY(entorno.mouseY());
            entorno.dibujarImagen(imagenAgua, circuloAgua.getX(), circuloAgua.getY(), 0, 0.2);
        } 
        
        if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
            if (botonTormentaFuego.estaDentro(entorno.mouseX(), entorno.mouseY())) {
            	if (Gondolf.getMana() >= 20) {
            		hechizoFuegoActivo = true;
            		entorno.dibujarImagen(imagenFuego, circuloFuego.getX(), circuloFuego.getY(), 0, 0.5);
            	}
            	else {
            		if(Gondolf.getMana()==0) {
            			botonTormentaFuego.setSeleccionado(false);
            		}
            	}
            }
        }
        
        if (hechizoFuegoActivo && circuloFuego != null) {
            circuloFuego.setX(entorno.mouseX());
            circuloFuego.setY(entorno.mouseY());
            entorno.dibujarImagen(imagenFuego, circuloFuego.getX(), circuloFuego.getY(), 0, 0.5);
        }
     // Activar hechizo si se hace click en el botón y hay mana suficiente
        if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
            if (botonRociador.estaDentro(entorno.mouseX(), entorno.mouseY())) {
                if (Gondolf.getMana() >= 10) {
                    hechizoRociadorActivo = true;
                } else {
                    botonRociador.setSeleccionado(false);
                }
            }
        }
     // Mientras el hechizo está activo, actualizar la posición y dibujar
        if (hechizoRociadorActivo && circuloRociador != null) {
            circuloRociador.setX(entorno.mouseX());
            circuloRociador.setY(entorno.mouseY());
            entorno.dibujarImagen(imagenRociador, circuloRociador.getX(), circuloRociador.getY(), 0, 0.2);
        }
     // Verificar si el maná llegó a 0
        if (Gondolf.getMana() == 0) {
            hechizoRociadorActivo = false;
            botonRociador.setSeleccionado(false);
        }

        this.dibujarObjetos();
        
        entorno.dibujarRectangulo(713, 200, 176, 370, 0, Color.black); //rectangulo largo negro atras de los botones
        tituloHechizos.dibujar(entorno);
        botonBombaAgua.dibujar(entorno);
        botonTormentaFuego.dibujar(entorno);
        botonRociador.dibujar(entorno);
        pvida.setTexto("Vida: " + Gondolf.getVida()+"%"); // Muestra la vida del personaje
        pmana.setTexto("Mana: " + Gondolf.getMana()+"%"); //Muestra la vida del personaje
        CDEnemigosEliminados.setTexto("     Enemigos Eliminados: " + EnemigosEliminados);
        pvida.dibujar(entorno);
        pmana.dibujar(entorno);
        CDEnemigosEliminados.dibujar(entorno);
        
        for (int i = 0; i < pociones.length; i++) {
            Pocion p = pociones[i];
            if (p != null && p.colisionaCon(Gondolf.getX(), Gondolf.getY(), 20)) { //SUMA VIDA AL COLISIONAR GONDOLF Y POCION
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
        if (jefeGenerado && jefe != null) {
        	jefe.dibujar(entorno);
        	jefe.mover(entorno);
        	JefeVida.setTexto("Vida del Jefe Final: "  + jefe.getVida());
        	JefeVida.dibujar(entorno);
        	
        	
        	contadorDisparoJefe++;
        	
                 // Crear un nuevo disparo si hay espacio libre
                 for (int i = 0; i < disparo.length; i++) {
                     if (disparo[i] == null && contadorDisparoJefe % 50 == 0){ // controlar que el jefe dispare despues de 50 ticks
                         disparo[i] = new Disparo(jefe.getX(), jefe.getY() + jefe.getAlto() / 2, 15, 15);
                         break; // solo crea un disparo por tick
                     }
                 }
                 for (int j = 0; j < disparo.length; j++) { 
                     if (disparo[j] != null) {
                         disparo[j].dibujar(entorno); // lo dibujamos en pantalla
                         disparo[j].mover(); // movimiento del disparo
                         if(disparo[j].ColisionaCon(Gondolf.getX(), Gondolf.getY(), 20)) { // Colisión con gondolf
                        	 disparo[j] = null;
                        	 Gondolf.restarvida(10); // quita 10 de vida a gondolf
                        	 break;
                         }    
                         
                         if (disparo[j].getY() > entorno.alto()) { // Si el disparo pasa por el limite la pantalla desaparece
                             disparo[j] = null;
                         }        
                         
       }
      }
   }
        for (int i=0; i< pociones.length; i++) {
        	if (pociones[i]!= null) {
        		pociones[i].dibujar(entorno);
        	}
         }
  }
	

	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
		
	}
}
