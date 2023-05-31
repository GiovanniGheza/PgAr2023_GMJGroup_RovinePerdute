package main;

//Veicolo Tonatiuh
public class Tonatiuh implements Veicolo{
    private static final String TONATIUH = "Tonatiuh";

	public double calcolaCostoCarburante(Citta citta1, Citta citta2) {
        return Math.abs(citta1.getH() - citta2.getH());
    }

	public String getNomeVeicolo() {
		return TONATIUH;
	}
}