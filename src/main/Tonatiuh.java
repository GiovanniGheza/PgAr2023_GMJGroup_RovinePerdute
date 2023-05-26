package main;

public class Tonatiuh implements Veicolo{
    public double calcolaCostoCarburante(Citta citta1, Citta citta2) {
        return Math.abs(citta1.getH() - citta2.getH());
    }

	@Override
	public String getNomeVeicolo() {
		return "Tonatiuh";
	}
}