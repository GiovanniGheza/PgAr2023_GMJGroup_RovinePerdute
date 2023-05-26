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
	}
	
	public void addCitta(int id, String nome) {
		ids.add(id);
		nomiCitta.add(nome);
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
}