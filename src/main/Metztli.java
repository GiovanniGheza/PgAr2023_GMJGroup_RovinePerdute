package main;

public class Metztli implements Veicolo{
    public double calcolaCostoCarburante(Citta citta1, Citta citta2) {
        return Math.sqrt(Math.pow(citta1.getX() - citta2.getX(), 2)
                    + Math.pow(citta1.getY() - citta2.getY(), 2));
    }

	@Override
	public String getNomeVeicolo() {
		return "Metztli";
	}
}