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
	private boolean juegoGanado = false;
	private Pocion[] pociones= new Pocion [20];
	
	private Enemigo[] enemigos = new Enemigo[10];
	private int enemigosVivos = 0;
	private int totalCreados = 0;
	
	private Menu tituloHechizos;
	private Menu pvida;
	private Menu pmana;
	private Menu CDEnemigosEliminados;
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
    private Jefe jefe;
    private boolean jefeGenerado = false;
    private Disparo[] disparo = new Disparo[1];
    private int contadorDisparoJefe = 0;
    private int EnemigosEliminados;
    private Menu JefeVida;
	
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
		this.jefe = new Jefe(320,0,70,70, Color.blue);
		this.EnemigosEliminados = 0;
		
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
        
        pvida = new Menu(posX, posY_Titulo + 400, tituloAlto , menuAncho, Color.red); 
        pvida.setFuente("Impact", 20, Color.BLACK);
        
        pmana = new Menu(posX, posY_Titulo + 450, tituloAlto , menuAncho, Color.blue);
        pmana.setFuente("Impact", 20, Color.BLACK);
        
        circuloAgua = new Hechizos(100, 100, 200, 200, 40, Color.green); //tamaño y color del circulo de Bomba de Agua
        circuloFuego = new Hechizos(300, 300, 600, 600, 80, Color.blue); //tamaño y color del circulo de Tormenta de Fuego
        circuloRociador = new Hechizos(150, 150, 250, 250, 50, Color.red); //tamaño y color del circulo de Rociador
        this.imagenFuego = new ImageIcon("Imagenes/tormentaDeFuego.gif").getImage();
        this.imagenAgua = new ImageIcon("Imagenes/bombaDeAgua.gif").getImage();
        this.imagenRociador = new ImageIcon("Imagenes/rociador.png").getImage();        
        
        CDEnemigosEliminados = new Menu(posX, posY_Titulo + 500, tituloAlto , menuAncho, Color.ORANGE); 
        CDEnemigosEliminados.setFuente("Impact", 13, Color.BLACK);
        
        JefeVida = new Menu(300,570, tituloAlto, menuAncho, Color.cyan);
        JefeVida.setFuente("Impact",13, Color.BLACK);
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
	
	private void reiniciarJuegoGanado() {
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
		    juegoGanado = false;
		    jefe = new Jefe(320, 0, 70, 70, Color.blue); // ← Esto es clave

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
	    
	    if (juegoGanado) {
	    	Image imagenFondo = new ImageIcon("Imagenes/GANASTE.png").getImage();
	    	entorno.dibujarImagen(imagenFondo, entorno.ancho() / 2, entorno.alto() / 2, 0);
	        if (entorno.estaPresionada(entorno.TECLA_ENTER)) {
	            reiniciarJuegoGanado();
	        }

	        return;
	    }

	    entorno.escribirTexto("La cantidad de Enemigos eliminados son: "+ EnemigosEliminados, 700, 500);
	    
	    // Chequeo real de vida para activar game over
	    if (Gondolf.getVida() <= 0) {
	        juegoTerminado = true;
	        return; // cancelo el resto del tick
	    }
	    
	    if (jefe != null && jefe.getVida() <= 0) {
	        juegoGanado = true;
	        return; // cancelo el resto del tick
	    }
	    
	    if (entorno.estaPresionada('a') && !Gondolf.colisionaPorIzquierda(entorno) && !Gondolf.colisionaConPiedra(-3, 0, piedras)) {
		    Gondolf.MoverIzq();
		}
		if (entorno.estaPresionada('d') && !Gondolf.colisionaPorDerecha(entorno) && !Gondolf.colisionaConPiedra (3, 0,piedras)) {
		    Gondolf.MoverDer();
		}
		if (entorno.estaPresionada('w') && !Gondolf.colisionaPorArriba(entorno) && !Gondolf.colisionaConPiedra(0, -3, piedras)) {
		    Gondolf.MoverArriba();
		}
		if (entorno.estaPresionada('s') && !Gondolf.colisionaPorAbajo(entorno) && !Gondolf.colisionaConPiedra(0, 3, piedras)) {
		    Gondolf.MoverAbajo();
		}  
		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);
		
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
            Gondolf.restarMana(10); // baja el mana por uso
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
            	if (Gondolf.getMana() >= 10) {
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

        this.dibujarObjetos();
        
        entorno.dibujarRectangulo(713, 200, 176, 370, 0, Color.black); //rectangulo largo negro atras de los botones
        tituloHechizos.dibujar(entorno);
        botonBombaAgua.dibujar(entorno);
        botonTormentaFuego.dibujar(entorno);
        botonRociador.dibujar(entorno);
        pvida.setTexto("Vida: " + Gondolf.mostrarvida()+"%"); // Muestra la vida del personaje
        pmana.setTexto("Mana: " + Gondolf.mostrarmana()+"%"); //Muestra la vida del personaje
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
                     if (disparo[i] == null && contadorDisparoJefe % 50 == 0){
                         disparo[i] = new Disparo(jefe.getX(), jefe.getY() + jefe.getAlto() / 2, 15, 15);
                         break; // solo crea un disparo por tick
                     }
                 }
                 for (int j = 0; j < disparo.length; j++) {
                     if (disparo[j] != null) {
                         disparo[j].dibujar(entorno);
                         disparo[j].mover();
                         if(disparo[j].ColisionaCon(Gondolf.getX(), Gondolf.getY(), 20)) {
                        	 disparo[j] = null;
                        	 Gondolf.restarvida(10);
                        	 break;
                         }    
                         
                         if (disparo[j].getY() > entorno.alto()) {
                             disparo[j] = null;
                         }        
                         
                 
     
        for (int i=0; i< pociones.length; i++) {
        	if (pociones[i]!= null) {
        		pociones[i].dibujar(entorno);
        	}
         }
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