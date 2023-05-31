package main;

/**
 * Interfaccia per i veicoli d'esplorazione
 * @author Gheza Giovanni
 */
public interface Veicolo {
	/**
	 * Calcola il costo in carburante per viaggiare tra due citta'
	 * @param citta1 - citta' di partenza
	 * @param citta2 - citta' d'arrivo
	 * @return il costo del carburante
	 */
    public double calcolaCostoCarburante(Citta citta1, Citta citta2);
    
    /**
     * Da il nome del veicolo
     * @return il nome del veicolo
     */
    public String getNomeVeicolo();
}