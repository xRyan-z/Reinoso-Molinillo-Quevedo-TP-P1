package juego;

import java.awt.Image;
import java.awt.Toolkit;
import entorno.Entorno;

public class Gondolf {
    private int x;
    private int y;
    private int alto;
    private int ancho;
    private int vida;
    private int mana;
    private Image imagenDerecha;
    private Image imagenIzquierda;
    private Image imagenActual;
    private Image imagenArriba;
    private Image imagenAbajo;
    

    public Gondolf (int x , int y, int alto, int ancho, int vida, int mana) {
        this.x = x;
        this.y = y;
        this.alto = alto;
        this.ancho = ancho;
        this.vida = vida;
        this.mana = mana;
        this.imagenDerecha = Toolkit.getDefaultToolkit().getImage("Imagenes/Gondolfder.png");
        this.imagenIzquierda = Toolkit.getDefaultToolkit().getImage("Imagenes/Gondolfizq.png");
        this.imagenAbajo = Toolkit.getDefaultToolkit().getImage("Imagenes/GondolfNormaloAbajo.png");
        this.imagenArriba = Toolkit.getDefaultToolkit().getImage("Imagenes/Gondolfarriba.png");

        // Imagen inicial por defecto
        this.imagenActual = imagenAbajo;

    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarImagen(imagenActual, x, y, 0, 1.0);
    }
           		
    	
        public int restarvida() {
        	if(vida > 0) {
        	vida = vida - 10;
        	} else {
        	}
        		
        return vida;

    }
        public int sumarvida() {
        	if(vida > 0 && vida < 100) {
        	vida = vida + 10;
        	
        	} else {
        		 System.out.println(vida);
        	}
        return vida;
    }
        public String mostrarvida() {
            String vidamostrada = Integer.toString(vida);
            return vidamostrada;
            }     
        
        public int restarMana() {
        	if(mana > 0) {
        	mana = mana - 10;
        	System.out.println(mana);
        	} else {
        	}
        return mana;

    }
        public int sumarMana() {
        	if(mana >= 0 && mana <= 100) {
        	mana = mana + 10;
        	System.out.println(mana);
        	} else {
        		 System.out.println(mana);
        	}
        return mana;
    }
        public String mostrarmana() {
            String manamostrada = Integer.toString(mana);
            return manamostrada;
            }  
        
        

    public void MoverIzq() {
        this.x -=3 ;
        this.imagenActual = imagenIzquierda;
    }
    public void MoverDer() {
        this.x +=3 ;
        this.imagenActual = imagenDerecha;
    }
    public void MoverArriba() {
        this.y -=3 ;
        this.imagenActual = imagenArriba;
    }
    public void MoverAbajo() {
        this.y +=3 ;
        this.imagenActual = imagenAbajo;
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

    public int getVida() {
        return this.vida;
    }

	public void sumarVida() {
		// TODO Auto-generated method stub
		
	}
	public int getMana() {
	    return mana;
	}

}