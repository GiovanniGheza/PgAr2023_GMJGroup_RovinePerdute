package main;

import java.util.ArrayList;

public class Citta {

    private int x, y;
    private int h;
    private String nome;
    private int ID;
    private ArrayList<Integer> collegamenti = new ArrayList<Integer>();
    private double distanzaDalCampo = Double.POSITIVE_INFINITY;
    private double distanzaDalUltimoNodo = Double.POSITIVE_INFINITY;
    private int ultimoNodoID = -1;

    public Citta() {
    	this.x = 0;
        this.y = 0;
        this.h = 0;
        this.nome = "";
        this.ID = 0;
        
        this.collegamenti = new ArrayList<Integer>();
    }

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
            ultimoNodoID = 0;
            distanzaDalUltimoNodo = 0;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public String getNome() {
        return nome;
    }

    public int getID() {
        return ID;
    }

    public double getDistanzaDalCampo() {
        return distanzaDalCampo;
    }

    public double getDistanzaDalUltimoNodo() {
    	return distanzaDalUltimoNodo;
    }
    
    public ArrayList<Integer> getCollegamenti() {
        return collegamenti;
    }

    public int getUltimoNodoID() {
        return ultimoNodoID;
    }

    public void set(int x, int y, int h, String nome, int ID){
        this.x = x;
        this.y = y;
        this.h = h;
        this.nome = nome;
        this.ID = ID;
        
      //il campo base e' a distanza zero da se stesso proveniendo da se stesso
        if(ID == 0){
            distanzaDalCampo = 0;
            ultimoNodoID = 0;
            distanzaDalUltimoNodo = 0;
        }
    }

    public void setDistanzaDalCampo(double dist) {
        this.distanzaDalCampo = dist;
    }
    
    public void setDistanzaDalUltimoNodo(double dist) {
        this.distanzaDalUltimoNodo = dist;
    }

    public void setUltimoNodoID(int ID) {
        this.ultimoNodoID = ID;
    }

    public void addCollegamento(int ID) {
        collegamenti.add(ID);
    }

    public boolean haCollegamento(int ID) {
        return collegamenti.contains(ID);
    }

    public boolean checkStessoLuogo(Citta c) {
        return c.getX() != x
            && c.getY() != y;
    }

    public boolean checkNominativi(Citta c) {
        return c.getNome() != nome
            && c.getID() != ID;
    }

	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + ", h=" + h + ", nome=" + nome + ", ID=" + ID + ", collegamenti="
				+ collegamenti + ", distanzaDalCampo=" + distanzaDalCampo + ", ultimoNodoID=" + ultimoNodoID + "]";
	}
}