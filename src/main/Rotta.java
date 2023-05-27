package main;

import java.util.ArrayList;

public class Rotta {
	String nomeVeicolo;
	double carburanteUsato = 0;
	ArrayList<Integer> ids = new ArrayList<Integer>();
	ArrayList<String> nomiCitta = new ArrayList<String>();
	
	public Rotta(Veicolo v) {
		nomeVeicolo = v.getNomeVeicolo();
		carburanteUsato = 0;
	}
	
	public Rotta(String nome) {
		nomeVeicolo = nome;
		carburanteUsato = 0;
	}
	
	public void addCarburanteUsato(double valore) {
		carburanteUsato += valore;
	}
	
	public void addCitta(Citta c) {
		ids.add(c.getID());
		nomiCitta.add(c.getNome());
		carburanteUsato += c.getDistanzaDalUltimoNodo();
	}
	
	public void addCitta(int id, String nome, double carburante) {
		ids.add(id);
		nomiCitta.add(nome);
		carburanteUsato += carburante;
	}
	
	public void addCittaInCima(Citta c) {
		ids.add(0,c.getID());
		nomiCitta.add(0,c.getNome());
		carburanteUsato += c.getDistanzaDalUltimoNodo();
	}
	
	public void addCittaInCima(int id, String nome, double carburante) {
		ids.add(0,id);
		nomiCitta.add(0,nome);
		carburanteUsato += carburante;
	}
	
	public void setIds(ArrayList<Integer> ids) {
		this.ids = ids;
	}
	
	public void setCarburanteUsato (double valore) {
		carburanteUsato = valore;
	}
	
	public double getCarburanteUsato() {
		return carburanteUsato;
	}
	
	public ArrayList<Integer> getIds() {
		return ids;
	}

	public int getID(int i) {
		return ids.get(i);
	}
	
	public ArrayList<String> getNomiCitta(){
		return nomiCitta;
	}
	
	public String getNomeCitta(int i) {
		return nomiCitta.get(i);
	}
	
	public int getNumeroCittaToccate() {
		return ids.size();
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("Team: " + nomeVeicolo);
		str.append("\nCosto carburante: " + String.format("%.2f", carburanteUsato) );
		str.append("\nNumero citta' toccate: " + ids.size());
		
		for(int i = 0; i < ids.size(); i++) {
			str.append("\n" + ids.get(i));
			str.append(" " + nomiCitta.get(i));
		}
		
		return str.toString();
	}
}