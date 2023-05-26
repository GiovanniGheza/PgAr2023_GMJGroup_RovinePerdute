package main;

import java.util.HashMap;
import java.util.Map;

public class AgglomeratoUrbano {

    Map<Integer,Citta> agglomerato = new HashMap<Integer,Citta>();

    public AgglomeratoUrbano() {
    }

    public AgglomeratoUrbano(AgglomeratoUrbano agg) {
        this.agglomerato = new HashMap<Integer,Citta>(agg.getAgglomerato());
    }

    public AgglomeratoUrbano(Citta citta) {
        agglomerato.put(citta.getID(), citta);
    }

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
    
    public void rimuoviCitta(int id) {
    	agglomerato.remove(id);
    }

    public Map<Integer, Citta> getAgglomerato() {
        return agglomerato;
    }

    public Citta getCitta(int ID) {
        return agglomerato.get(ID);
    }
    
    public boolean contieneCitta(int id) {
    	return agglomerato.containsKey(id);
    }
    
    public boolean isVuoto() {
    	return agglomerato.isEmpty();
    }

    public boolean checkPresenzaStrada(int ID1, int ID2) {
        return agglomerato.get(ID1).haCollegamento(ID2);
    }

    public double calcolaDistanza(int ID1, int ID2) {
        return Math.sqrt(calcolaDifferenzaAltitudine(ID1, ID2) + calcolaDistanzaViaAria(ID1, ID2));
    }

    public double calcolaDistanzaViaAria(int ID1, int ID2) {
        return Math.sqrt(Math.pow(agglomerato.get(ID1).getX() - agglomerato.get(ID2).getX(), 2)
                    + Math.pow(agglomerato.get(ID1).getY() - agglomerato.get(ID2).getY(), 2));
    }

    public double calcolaDifferenzaAltitudine(int ID1, int ID2) {
        return Math.abs(agglomerato.get(ID2).getH() - agglomerato.get(ID1).getH());
    }

    public double calcolaCostoCarburante(Veicolo v, int ID1, int ID2) {
        return v.calcolaCostoCarburante(agglomerato.get(ID1), agglomerato.get(ID2));
    }
}