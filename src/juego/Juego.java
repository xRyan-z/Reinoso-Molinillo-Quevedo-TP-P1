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
	private boolean juegoTerminado = false; // Bandera que funciona si el juego está terminado
	private boolean juegoGanado = false; // Bandera que funciona si el juego se ganó
	private Pocion[] pociones= new Pocion [20]; //Arreglo de pociones que pueden aparecer en el juego.
	private Enemigo[] enemigos = new Enemigo[10]; //Arreglo de enemigos activos al mismo tiempo (hasta 10).
	private int enemigosVivos = 0; 
	private int totalCreados = 0;  
	private Menu tituloHechizos;
	private Menu pvida; // creo un objeto menu que mostrará la vida del personaje
	private Menu pmana;
	private Menu CDEnemigosEliminados; 
	private Menu JefeVida; 
    private Boton botonBombaAgua; // Botón que selecciona el hechizo de Bomba de Agua
    private Boton botonTormentaFuego;
    private Boton botonRociador; 
    private Boton botonSeleccionado; // Referencia al botón actualmente seleccionado (para resaltarlo o activarlo)
    private Hechizos circuloAgua; // Representación visual del hechizo de Agua en forma de círculo
    private Hechizos circuloFuego; 
    private Hechizos circuloRociador; 
    boolean hechizoAguaActivo = false; // Bandera para saber si el hechizo de Agua está activo
    boolean hechizoFuegoActivo = false; 
    boolean hechizoRociadorActivo = false;
    private Image imagenFuego; 
    private Image imagenAgua;
    private Image imagenRociador;
    private Jefe jefe; // Creo un objeto jefe
    private boolean jefeGenerado = false; // Bandera que se pone en true si el jefe está generado
    private Disparo[] disparo = new Disparo[10]; // Creo objetos disparo que serán 10
    private int contadorDisparoJefe = 0; // Contador de disparos del jefe
    private int EnemigosEliminados; // Cuenta la cantidad de enemigos eliminados
    
	
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
		this.piedras[1] = new Piedra (300, 100, 50,50); 
		this.piedras[2] = new Piedra (500, 300, 50,50); 
		this.piedras[3] = new Piedra (250, 500, 50,50); 
		this.jefe = new Jefe(320,0,70,70); // Creo al jefe con los parametros dados.
		this.EnemigosEliminados = 0; // Inicio el contador en 0 de enemigos eliminados
		
		
		int panelWidth = entorno.ancho();  // 800
		int menuAncho = 150; // Define el ancho que va a tener cada botón lateral.
		int menuAlto = 90; // Define la altura que va a tener cada botón lateral.
		int margenDerecho = 10; // Margen desde el borde derecho de la pantalla hasta el menú. Para que no quede pegado al borde.
		int posX = panelWidth - menuAncho / 2 - margenDerecho; // Calcula la posición X (horizontal) del centro del menú, ubicado hacia el borde derecho con margen.
	
		int tituloAlto = 40;// Altura del menú del título o encabezado superior.
		int posY_Titulo = 50; // Posición vertical del centro del menú de título (queda arriba del todo).
		int posY_BombaAgua = 140; // Posición vertical del botón "Bomba de Agua".
		int posY_TormentaFuego = 230; // Posición vertical del botón "Tormenta de Fuego".
		int posY_Rociador = 330; // Posición vertical del botón "Rociador".
		
		botonBombaAgua = new Boton(posX, posY_BombaAgua, menuAncho, menuAlto, Color.black); //Crea una nueva instancia del botón "Bomba de Agua" y la guarda en la variable botonBombaAgua.
        botonBombaAgua.setTexto("BOMBA DE AGUA"); //texto que muestra en pantalla
        botonBombaAgua.setFuente("impact", 17, Menu.getlight_brown()); //fuente, tamaño y color del texto

        botonTormentaFuego = new Boton(posX, posY_TormentaFuego, menuAncho, menuAlto, Color.black); //Crea una nueva instancia del botón "Tormenta de Fuego" y la guarda en la variable botonTormentaFuego.
        botonTormentaFuego.setTexto("TORMENTA DE FUEGO"); //texto que muestra en pantalla
        botonTormentaFuego.setFuente("impact", 17, Menu.getlight_brown()); //fuente, tamaño y color del texto

        tituloHechizos = new Menu(posX, posY_Titulo, tituloAlto, menuAncho, Menu.getBrown()); //Crea una nueva instancia de Menu que se usará como título de la sección de hechizos en el panel lateral.
        tituloHechizos.setTexto("HECHIZOS"); //texto que muestra en pantalla
        tituloHechizos.setFuente("impact", 22, Color.WHITE); //fuente, tamaño y color del texto
        
        botonRociador = new Boton(posX, posY_Rociador, menuAncho, menuAlto, Color.black); //Crea una nueva instancia del botón "Rociador" y la guarda en la variable botonRociador.
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
        this.imagenFuego = new ImageIcon("Imagenes/tormentaDeFuego.gif").getImage(); //reemplaza el circulo por imagen del hechizo en cuestión
        this.imagenAgua = new ImageIcon("Imagenes/bombaDeAgua.gif").getImage(); //reemplaza el circulo por imagen del hechizo en cuestión
        this.imagenRociador = new ImageIcon("Imagenes/rociador.png").getImage(); //reemplaza el circulo por imagen del hechizo en cuestión        
        	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	
	//reinicia el juego a su estado inicial, como si el jugador hubiera perdido o quisiera volver a empezar desde el principio.
	private void reiniciarJuego() { //Metodo que reinicia el juego con los parametros iniciales (los que se encuentran debajo)
		
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

		    for (int i = 0; i < pociones.length; i++) { //Elimina todas las pociones del arreglo al reiniciar el juego.
		        pociones[i] = null;
		    }
		}
	
	private void reiniciarJuegoGanado() { //método para cuando ganás el juego, para permitir volver a jugar, pero reiniciando también al jefe final.
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
		
		if (enemigosVivos < 10 && totalCreados < 50) { //Si se cumple esa condicion, entra al for, busca un espacio vacío en el arreglo enemigos[] y genera un nuevo murciélago.
			    for (int i = 0; i < enemigos.length; i++) {
			        if (enemigos[i] == null) {
			            enemigos[i] = Enemigo.generarMurcielagoAleatorio(); //Genera un nuevo murciélago aleatorio.
			            enemigosVivos++;
			            totalCreados++;
			            break; // salimos del for después de generar uno
			        }
			    }
		}
		 // Mover, dibujar y colisionar murciélagos
        for (int i = 0; i < enemigos.length; i++) { //Hace que todos los enemigos (no nulos) se muevan en dirección a Gondolf.
        	  Enemigo e = enemigos[i];
              if (e != null) {
                  e.moverHacia(Gondolf.getX(), Gondolf.getY());
                  
               // Verificar si el hechizo de agua está activo y el jugador hizo click izquierdo
                  if (hechizoAguaActivo && entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) { // se selecciona el boton
                      if (circuloAgua != null && circuloAgua.colisionaCon(e)) { // Verificar si el hechizo de agua existe y colisiona con el enemigo actual 'e'
                    	  int ex = enemigos[i].getX(); // Guarda la posición X del enemigo muerto
                          int ey = enemigos[i].getY(); 
                    	  enemigos[i] = null; //si el hechizo colisiona con el murcielago, este muere
                          EnemigosEliminados++; 
                          enemigosVivos--; 
                          
                          //Coloca una nueva poción en la primera posición libre del arreglo, en la ubicación donde murió el enemigo.
                          if (Gondolf.getVida() <= 30) { //Si el jugador tiene poca vida...
                        	  for (int j = 0; j < pociones.length; j++) { //Recorre el arreglo de pociones, buscando un espacio vacío.
                                  if (pociones[j] == null) { //Cuando encuentra un lugar vacío (sin poción), entra al bloque if.
                                      pociones[j] = new Pocion(ex, ey); //Crea una poción en la posición donde murió el enemigo.
                                      break;
                          }
                      } 
                  }
              }     
          }           
                  
                  if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) { // se selecciona el boton
                	  if (hechizoFuegoActivo && circuloFuego != null && circuloFuego.colisionaCon(e)) {
                          enemigos[i] = null; //si el hechizo colisiona con el murcielago, este muere
                          EnemigosEliminados++; // Se incrementa el contador de eliminados
                          enemigosVivos--; // Se reduce el contador de enemigos vivos
                      }
                  }
               // El rociador actúa automáticamente sin necesidad de clic
                  if (circuloRociador !=null && circuloRociador.colisionaCon(e)) {
                      enemigos[i] = null; // El enemigo muere si colisiona el hechizo con el enemigo
                      EnemigosEliminados++; // Se suma al total de eliminados
                      enemigosVivos--; // Se resta del total de vivos
                      Gondolf.restarMana(5); // Gondolf pierde 5 puntos de maná por usar el rociador
                  }
                  
                  if (e.colisionaCon(Gondolf.getX(), Gondolf.getY(), 20)) {
                      enemigos[i] = null; //Si el enemigo choca con el jugador, muere.
                      enemigosVivos--;
                      EnemigosEliminados++;
                      Gondolf.restarvida(10); //El jugador pierde 10 de vida.
                      
                      if (Gondolf.getVida() <= 0) {
                          juegoTerminado = true; //Si la vida baja a 0, el juego termina.
                          return;
                      }
                   	}
                 }
              }
        
        //Cuando ya no quedan enemigos y se crearon 50, aparece el jefe.
        if (totalCreados == 50 && enemigosVivos == 0 && !jefeGenerado) {
			jefeGenerado = true;
			
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

     // Si el jefe colisiona con Gondolf (usando la posición de Gondolf y un radio de 20)
        if (jefe.colisionaCon(Gondolf.getX(), Gondolf.getY(), 20)) {
        	Gondolf.restarvida(1); // Entonces le restamos 1 punto de vida a Gondolf (el jugador recibe daño)
        }
        
        
     // Desactivar hechizos (fuera del for)
        if (hechizoAguaActivo && entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) { // se selecciona el boton
            hechizoAguaActivo = false; // se desactiva el hechizo luego de ser lanzado
            botonBombaAgua.setSeleccionado(false); // una vez que se lanza se deselecciona el boton
        }

        if (hechizoFuegoActivo && entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) { // se selecciona el boton
            hechizoFuegoActivo = false; // se desactiva el hechizo luego de ser lanzado
            botonTormentaFuego.setSeleccionado(false); // una vez que se lanza se deselecciona el boton
            Gondolf.restarMana(20); // baja el mana por lanzamiento
        }
        if (hechizoRociadorActivo && entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) { // se selecciona el boton
            hechizoRociadorActivo = false; // se desactiva el hechizo luego de ser lanzado
            botonRociador.setSeleccionado(false); // una vez que se lanza se deselecciona el boton
        }

        	for (int i = 0; i < pociones.length; i++) { //Recorre todas las pociones del arreglo pociones[].
        		if (pociones[i] != null) {
        			pociones[i].dibujar(entorno); // Dibujar la poción
        			if (pociones[i].expirada()) {
        				pociones[i] = null; // Si ya pasaron 6 segundos, eliminarla
                }
            }
        }
        
        
     // Detectar click sobre botones de hechizo
        if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
        	// Guardar la posición del mouse al hacer click
            int mx = entorno.mouseX();
            int my = entorno.mouseY();

            if (botonBombaAgua.estaDentro(mx, my)) { // Si el click está dentro del botón de bomba de agua
                seleccionarBoton(botonBombaAgua); // Seleccionar ese botón (activarlo visualmente o en lógica)
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
            if (botonBombaAgua.estaDentro(entorno.mouseX(), entorno.mouseY())) { // Verificar si el mouse está sobre el botón bomba de agua
                hechizoAguaActivo = true; // Activar el hechizo de agua
                entorno.dibujarImagen(imagenAgua, circuloAgua.getX(), circuloAgua.getY(), 0, 0.2); // Dibujar la imagen del hechizo de agua en la posición del círculo de agua, con escala 0.2
            }
        }

        // Si el hechizo está activo, actualizar su posición y dibujarlo
        if (hechizoAguaActivo && circuloAgua != null) {
        	// Actualizar la posición del círculo para que siga el mouse
            circuloAgua.setX(entorno.mouseX());
            circuloAgua.setY(entorno.mouseY());
         // Dibujar la imagen del hechizo de agua en la nueva posición
            entorno.dibujarImagen(imagenAgua, circuloAgua.getX(), circuloAgua.getY(), 0, 0.2);
        } 
     // Si se presionó el botón izquierdo y el mouse está sobre el botón tormenta de fuego
        if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
            if (botonTormentaFuego.estaDentro(entorno.mouseX(), entorno.mouseY())) {
            	if (Gondolf.getMana() >= 20) { // Verificar que Gondolf tenga al menos 20 de maná
            		hechizoFuegoActivo = true; // Activar el hechizo de fuego
            		entorno.dibujarImagen(imagenFuego, circuloFuego.getX(), circuloFuego.getY(), 0, 0.5); // Dibujar la imagen del hechizo de fuego en la posición del círculo, con escala 0.5
            	}
            	// Si no tiene maná y es 0, deseleccionar el botón para indicar que no se puede usar
            	else {
            		if(Gondolf.getMana()<20) {
            			botonTormentaFuego.setSeleccionado(false);
            		}
            	}
            }
        }
        
        if (hechizoFuegoActivo && circuloFuego != null) {
        	// Actualizar la posición del círculo para que siga el mouse
            circuloFuego.setX(entorno.mouseX());
            circuloFuego.setY(entorno.mouseY());
         // Dibujar la imagen del hechizo de fuego en la nueva posición
            entorno.dibujarImagen(imagenFuego, circuloFuego.getX(), circuloFuego.getY(), 0, 0.5);
        }
     // Activar hechizo si se hace click en el botón y hay mana suficiente
        if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
            if (botonRociador.estaDentro(entorno.mouseX(), entorno.mouseY())) {
                if (Gondolf.getMana() >= 10) { // Verificar que Gondolf tenga al menos 10 de maná
                    hechizoRociadorActivo = true; // Activar el hechizo rociador
                } else {
                    botonRociador.setSeleccionado(false); // Si no hay maná suficiente, deseleccionar el botón
                }
            }
        }
     // Mientras el hechizo está activo, actualizar la posición y dibujar
        if (hechizoRociadorActivo && circuloRociador != null) {
        	// Actualizar la posición del círculo para que siga el mouse
            circuloRociador.setX(entorno.mouseX());
            circuloRociador.setY(entorno.mouseY());
         // Dibujar la imagen del hechizo rociador en la nueva posición con escala 0.2
            entorno.dibujarImagen(imagenRociador, circuloRociador.getX(), circuloRociador.getY(), 0, 0.2);
        }
     // Verificar si el maná llegó a 0
        if (Gondolf.getMana() == 0) {
            hechizoRociadorActivo = false; // Desactivar el hechizo rociador (porque ya no puede usarse)
            botonRociador.setSeleccionado(false); // Deseleccionar el botón rociador para reflejar que no está activo
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
    
	// Método para seleccionar un botón (activar visualmente o en lógica)
    private void seleccionarBoton(Boton b) {
        if (botonSeleccionado != null) { // Si ya hay un botón seleccionado (no es null)
            botonSeleccionado.setSeleccionado(false); // Deseleccionar el botón que estaba previamente seleccionado
        }
        botonSeleccionado = b; // Actualizar la referencia para que el botón seleccionado sea el nuevo botón 'b'
        botonSeleccionado.setSeleccionado(true); // Marcar el nuevo botón como seleccionado (activar su estado)
    }
	
	public void dibujarObjetos() {
		Gondolf.dibujar(entorno);
		for (int i = 0; i < piedras.length; i++) {
			if (piedras[i] != null) {
				piedras[i].dibujar(entorno);
			}
		}
		
        for (Enemigo e : enemigos) {
            if (e != null) e.dibujar(entorno); //los enemigos activos se dibujan en pantalla. Si un enemigo es null, se lo ignora y no se dibuja.
		  }   
        
        //Esto sirve para que todas las pociones visibles (que están activas en el juego) se dibujen en pantalla.
        //Si alguna posición del arreglo está vacía, simplemente la salta para evitar errores.
        for (int i=0; i< pociones.length; i++) {
        	if (pociones[i]!= null) {
        		pociones[i].dibujar(entorno);
        	}
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
	}  
	

	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
		
	}
}
