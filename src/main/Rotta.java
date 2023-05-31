package main;

import java.util.ArrayList;

/**
 * Classe che rappresenta il percorso da fare per raggiungere le Rovine Perdute piu' il carburante usato
 * e il veicolo usato.
 * Un oggetto di questa classe contiene tutte le info da stampare nel file di output
 * @author Gheza Giovanni
 *
 */
public class Rotta {
	private static final String SEPARATORE = " - ";
	private static final String A_CAPO = "\n";
	private static final String STRING_FORMAT = "%.2f";
	private static final String ELENCO_CITTA_TOCCATE = "\nElenco citta' toccate: ";
	private static final String NUMERO_CITTA_TOCCATE = "\nNumero citta' toccate: ";
	private static final String COSTO_CARBURANTE = "\nCosto carburante: ";
	private static final String TEAM = "Team: ";
	String nomeVeicolo;
	double carburanteUsato = 0;
	ArrayList<Integer> ids = new ArrayList<Integer>();
	ArrayList<String> nomiCitta = new ArrayList<String>();
	
	/**
	 * Costruttore
	 * @param v - il veicolo da usare
	 */
	public Rotta(Veicolo v) {
		nomeVeicolo = v.getNomeVeicolo();
		carburanteUsato = 0;
	}
	
	/**
	 * Aggiunge carburante al carburante totale usato
	 * @param valore - la quantita da aggiungere
	 */
	public void addCarburanteUsato(double valore) {
		carburanteUsato += valore;
	}
	
	/**
	 * Aggiunge una citta' in fondo all'elenco chiendendo un oggetto di tipo Citta
	 * @param c - la citta' da aggiungere
	 */
	public void addCitta(Citta c) {
		ids.add(c.getID());
		nomiCitta.add(c.getNome());
		carburanteUsato += c.getDistanzaDallUltimaCitta();
	}
	
	/**
	 * Aggiunge una citta' in fondo all'elenco chiedendo i dati necessari
	 * @param id - l'id della citta'
	 * @param nome - il nome della citta'
	 * @param carburante - il carburante usato per raggiungere questa citta'
	 */
	public void addCitta(int id, String nome, double carburante) {
		ids.add(id);
		nomiCitta.add(nome);
		carburanteUsato += carburante;
	}
	
	/**
	 * Aggiunge una citta' in cima all'elenco chiendendo un oggetto di tipo Citta
	 * @param c - la citta' da aggiungere
	 */
	public void addCittaInCima(Citta c) {
		ids.add(0,c.getID());
		nomiCitta.add(0,c.getNome());
		carburanteUsato += c.getDistanzaDallUltimaCitta();
	}
	
	/**
	 * Aggiunge una citta' in cima all'elenco chiedendo i dati necessari
	 * @param id - l'id della citta'
	 * @param nome - il nome della citta'
	 * @param carburante - il carburante usato per raggiungere questa citta'
	 */
	public void addCittaInCima(int id, String nome, double carburante) {
		ids.add(0,id);
		nomiCitta.add(0,nome);
		carburanteUsato += carburante;
	}
	
	/**
	 * Setta il carburante usato
	 * @param valore - il nuovo calore del carburante usato
	 */
	public void setCarburanteUsato (double valore) {
		carburanteUsato = valore;
	}
	
	/**
	 * Prende il carburante usato nel viaggio
	 * @return il carburante usato come double
	 */
	public double getCarburanteUsato() {
		return carburanteUsato;
	}
	
	/**
	 * Prende tutti gli id delle citta nella rotta
	 * @return un ArrayList con tutti gli id delle citta'
	 */
	public ArrayList<Integer> getIds() {
		return ids;
	}

	/**
	 * Prende l'id della i-esima citta' nella rotta
	 * @param i - il numero della citta'
	 * @return l'id della citta' che si vuole
	 */
	public int getID(int i) {
		return ids.get(i);
	}
	
	/**
	 * Prende tutti i nomi delle citta nella rotta
	 * @return un ArrayList con tutti i nomi delle citta'
	 */
	public ArrayList<String> getNomiCitta(){
		return nomiCitta;
	}
	
	/**
	 * Prende il nome della i-esima citta' nella rotta
	 * @param i - il numero della citta'
	 * @return il numero della citta' che si vuole
	 */
	public String getNomeCitta(int i) {
		return nomiCitta.get(i);
	}
	
	/**
	 * Prende il numero delle citta' toccate
	 * @return il numero delle citta' toccate
	 */
	public int getNumeroCittaToccate() {
		return ids.size();
	}
	
	/**
	 * Prende il nome del veicolo usato
	 * @return il nome del veicolo
	 */
	public String getNomeVeicolo() {
		return nomeVeicolo;
	}
	
	/**
	 * Il metodo toString
	 */
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append(TEAM + nomeVeicolo);
		str.append(COSTO_CARBURANTE + String.format(STRING_FORMAT, carburanteUsato) );
		str.append(NUMERO_CITTA_TOCCATE + ids.size());
		
		str.append(ELENCO_CITTA_TOCCATE);
		
		for(int i = 0; i < ids.size(); i++) {
			str.append(A_CAPO + ids.get(i));
			str.append(SEPARATORE + nomiCitta.get(i));
		}
		
		return str.toString();
	}
}