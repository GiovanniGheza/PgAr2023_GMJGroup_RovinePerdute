package main;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe che rappresenta un'insieme di Citta
 * @author Giovanni Gheza
 */
public class AgglomeratoUrbano {

	//l'insieme delle citta' come mappa la cui chiave e' l'identificativo della citta'
	Map<Integer,Citta> agglomerato;

	/**
	 * Costruttore vuoto
	 */
	public AgglomeratoUrbano() {
		agglomerato = new HashMap<Integer,Citta>();
	}

	/**
	 * Costruttore che copia un altro agglomerato urbano
	 * @param agg - l'agglomerato da copiare
	 */
	public AgglomeratoUrbano(AgglomeratoUrbano agg) {
		this.agglomerato = new HashMap<Integer,Citta>(agg.getAgglomerato());
	}

	/**
	 * Costruttore che crea un'agglomerato partendo da una citta'
	 * @param citta - la citta
	 */
	public AgglomeratoUrbano(Citta citta) {
		agglomerato = new HashMap<Integer,Citta>();
		agglomerato.put(citta.getID(), citta);
	}

	/**
	 * Aggiunge una citta all'agglomerato
	 * @param citta - citta' da aggiungere
	 * @return vero se la citta' e' stata aggiunta correttamente, falso se la citta' e' gia' presente
	 */
	public boolean addCitta(Citta citta) {
		if(agglomerato.containsKey(citta.getID()))
			return false;

		for(Citta c: agglomerato.values()){
			if(c.equals(citta))
				return false;
		}

		agglomerato.put(citta.getID(), citta);

		return true;
	}

	/**
	 * Rimuove la citta scelta
	 * @param id - l'dentificativo della citta da togliere
	 */
	public void rimuoviCitta(int id) {
		agglomerato.remove(id);
	}

	public Map<Integer, Citta> getAgglomerato() {
		return agglomerato;
	}

	/**
	 * Prende una citta' dato l'id
	 * @param ID - l'ID della citta'
	 * @return la citta' in questione
	 */
	public Citta getCitta(int ID) {
		return agglomerato.get(ID);
	}

	/**
	 * Controlla se l'agglomerato contiene una citta'
	 * @param id - l'id della citta' 
	 * @return vero se c'e' la citta', falso altrienti
	 */
	public boolean contieneCitta(int id) {
		return agglomerato.containsKey(id);
	}

	/**
	 * controlla se l'agglomerato e' semza citta'
	 * @return vero se l'agglomerato non contiene citta'
	 */
	public boolean isVuoto() {
		return agglomerato.isEmpty();
	}

	/**
	 * Controlla se la prima citta' inserita e' collegata con la seconda
	 * @param ID1 - l'id della citta' da cui si parte
	 * @param ID2 - l'id della citta' a cui si arriva
	 * @return vero se il collegamento e' presente
	 */
	public boolean checkPresenzaStrada(int ID1, int ID2) {
		return agglomerato.get(ID1).isCollegataCon(ID2);
	}

	/**
	 * Calcola il costo in carburante del visggiare tra due citta
	 * @param v - il veicolo con cui si viaggia
	 * @param ID1 - l'id della citta' da cui si parte
	 * @param ID2 - l'id della citta' a cui si arriva
	 * @return il carburante usato
	 */
	public double calcolaCostoCarburante(Veicolo v, int ID1, int ID2) {
		return v.calcolaCostoCarburante(agglomerato.get(ID1), agglomerato.get(ID2));
	}
}