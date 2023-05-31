package main;

import java.util.ArrayList;

/**
 * Questa classe rappresenta una citta, ovvero un nodo nel grafo di AgglomeratoCittadino
 * @author Gheza Giovanni
 *
 */
public class Citta {

	//posizione
    private int x, y;
    private int h;
    
    //nome
    private String nome;
    
    //identificativo
    private int ID;
    
    //citta' con cui si e' collegata
    private ArrayList<Integer> collegamenti = new ArrayList<Integer>();
    
    //distanza dal campo base
    private double distanzaDalCampo = Double.POSITIVE_INFINITY;
    
    //la distanza dall'ultima citta'
    private double distanzaDallUltimaCitta = Double.POSITIVE_INFINITY;
    
    //l'ultima citta' da cui si e' passati per raggiungere questa venendo dal campo base
    private int ultimaCittaID = -1;

    /**
     * Costruttore vuoto
     */
    public Citta() {
    	this.x = 0;
        this.y = 0;
        this.h = 0;
        this.nome = "";
        this.ID = 0;
        
        this.collegamenti = new ArrayList<Integer>();
    }

    /**
     * Costruttore completo
     * 
     * @param x - posizione x
     * @param y - posizione y
     * @param h - elevazione
     * @param nome - nome della citta
     * @param ID - identificativo
     * @param collegamenti - gli id delle citta adiacenti
     */
    public Citta(int x, int y, int h, String nome, int ID, ArrayList<Integer> collegamenti) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.nome = nome;
        this.ID = ID;
        this.collegamenti = collegamenti;

        //il campo base e' a distanza zero da se stesso proveniendo da se stesso
        if(ID == 0){
            distanzaDalCampo = 0;
            ultimaCittaID = 0;
            distanzaDallUltimaCitta = 0;
        }
    }

    /**
     * Prende la posizione x della citta'
     * @return la posizione x della citta'
     */
    public int getX() {
        return x;
    }

    /**
     * Prende la posizione y della citta'
     * @return la posizione y della citta'
     */
    public int getY() {
        return y;
    }

    /**
     * Prende l'altitudine della citta'
     * @return l'altitudine della citta'
     */
    public int getH() {
        return h;
    }

    /**
     * Prende il nome della citta'
     * @return il nome della citta'
     */
    public String getNome() {
        return nome;
    }

    /**
     * Prende l'id della citta'
     * @return l'id della citta'
     */
    public int getID() {
        return ID;
    }

    /**
     * Prende la distanza dal campo base della citta'
     * @return la distanza dal campo base della citta'
     */
    public double getDistanzaDalCampo() {
        return distanzaDalCampo;
    }

    /**
     * Prende la distanza che c'e' tra questa citta' e la citta' da cui si arriva venendo dal campo base
     * @return la distanza dall'ultima citta'
     */
    public double getDistanzaDallUltimaCitta() {
    	return distanzaDallUltimaCitta;
    }
    
    /**
     * Ritorna tutti gli id delle citta' a cui e' collegata
     * @return l'ArrayList con tutti gi id delle citta' adiacenti
     */
    public ArrayList<Integer> getCollegamenti() {
        return collegamenti;
    }

    /**
     * Prende l'id della citta' da cui si arriva venendo dal campo base
     * @return l'id dell'ultima citta'
     */
    public int getUltimaCittaID() {
        return ultimaCittaID;
    }

    /**
     * Setta tutti i valori base della citta, esclusi i collegamenti, in un'unico metodo
     * 
     * @param x - posizione x
     * @param y - posizione y
     * @param h - elevazione
     * @param nome - nome della citta
     * @param ID - identificativo
     */
    public void set(int x, int y, int h, String nome, int ID){
        this.x = x;
        this.y = y;
        this.h = h;
        this.nome = nome;
        this.ID = ID;
        
      //il campo base e' a distanza zero da se stesso proveniendo da se stesso
        if(ID == 0){
            distanzaDalCampo = 0;
            ultimaCittaID = 0;
            distanzaDallUltimaCitta = 0;
        }
    }

    /**
     * Setta la distanza che c'e' tra il campo base e la citta in questione
     * @param dist - distanza tra campo base  citta
     */
    public void setDistanzaDalCampo(double dist) {
        this.distanzaDalCampo = dist;
    }
    
    /**
     * Setta la distanza che c'e' tra tra questa citta' e la citta subito prima venendo dal campo base
     * @param dist - distanza tra le citta'
     */
    public void setDistanzaDalUltimoNodo(double dist) {
        this.distanzaDallUltimaCitta = dist;
    }

    /**
     * Setta qual e' la citta subito prima venendo dal campo base
     * @param ID - l'identificativo della citta prima
     */
    public void setUltimoNodoID(int ID) {
        this.ultimaCittaID = ID;
    }

    /**
     * Aggiunge un collegamento verso una citta
     * @param ID - l'identificativo della citta verso cui il collegamento va
     */
    public void addCollegamento(int ID) {
        collegamenti.add(ID);
    }

    /**
     * Controlla se la citta e' collegata con un'altra
     * @param ID - lidentificativo dell'altra citta
     * @return vero se a citta e' collegata con l'altra, falso altrimenti
     */
    public boolean isCollegataCon(int ID) {
        return collegamenti.contains(ID);
    }

    /**
     * Controlla se due citta' sono sulle stesse cordinate
     * @param c - l'altra citta'
     * @return vero se le citta' hanno le stesse cordinate, falso altrimenti
     */
    public boolean checkStessoLuogo(Citta c) {
        return c.getX() == x
            && c.getY() == y;
    }

    /**
     * Controlla se due citta' hanno nome o ID uguale
     * @param c - l'altra citta'
     * @return vero se le citta' hanno nome o ID uguale, falso se uno dei due e' uguale
     */
    public boolean checkNominativi(Citta c) {
        return c.getNome() == nome
            || c.getID() == ID;
    }

	/**
	 * Funzione toString
	 */
	public String toString() {
		return "[x=" + x + ", y=" + y + ", h=" + h + ", nome=" + nome + ", ID=" + ID + ", collegamenti="
				+ collegamenti + ", distanzaDalCampo=" + distanzaDalCampo + ", ultimoNodoID=" + ultimaCittaID + "]";
	}
}