package main;

import java.util.Collection;

public class Dijkstra {

	//la mappa delle Ande o forse delle Alpi?
	//la mappa che contiene tutte le citta'
	AgglomeratoUrbano mappa;

	/**
	 * Costruttore
	 * @param mappa - la mappa su cui ci si sposta
	 */
	public Dijkstra(AgglomeratoUrbano mappa) {
		this.mappa = mappa;
	}

	/**
	 * Calcola la Rotta piu' corta per raggiungere le Rovine Perdute
	 * @param veicolo - il veicolo con cui si viaggia
	 * @return la Rotta per raggiungere le Rovine Perdute
	 */
	public Rotta dijkstraMagic(Veicolo veicolo) {

		//tabella con i nodi, la loro distanza dall'origine e il nodo da cui provengono
		AgglomeratoUrbano tabella = new AgglomeratoUrbano(mappa);
		//insieme Q dei nodi da visitare
		AgglomeratoUrbano cittaDaVisitare = new AgglomeratoUrbano(tabella);
		//le Rovine Perdute sono sempre l'ultima citta' dell'elenco
		int rovinePerduteID = cittaDaVisitare.getAgglomerato().size() - 1;
		//il Campo Base e' sempre la prima citta' dell'elenco
		int campoBaseID = 0;
		//il costo del carburante tra una citta' e l'altra
		double costoCarburante = 0;
		//se ho trovato le rovine
		boolean hoTrovatoLeRovine = false;

		//cerco le Rovine Perdute e calcolo la distanza avanzando
		//questo e' l'argoritmo di Dijkstra
		while(!cittaDaVisitare.isVuoto() && !hoTrovatoLeRovine) {
			//prendo la citta' piu' vicina al campo base, non gia' visitata
			Citta c =  getPiuVicinaAlCampo((cittaDaVisitare.getAgglomerato().values()));

			//guardo le citta' vicine
			for(int ID: c.getCollegamenti()) {
				//se non ho gia' visitato la citta' vicina
				if(cittaDaVisitare.contieneCitta(ID)) {
					//sto controllando verso quella citta'
					Citta cittaCheStoControllando = tabella.getAgglomerato().get(ID);
					//calcolo quanto mi costa il viaggio
					costoCarburante = tabella.calcolaCostoCarburante(veicolo,c.getID(), ID);
					//calcolo la distanza dal campo base
					double distanzaDalCampo = c.getDistanzaDalCampo() + costoCarburante;

					//se la distanza dal campo calcolata ora e' minore dalla distanza gia' calcolata o quella 
					//di default la sostituisco e aggiorno
					if(distanzaDalCampo < cittaCheStoControllando.getDistanzaDalCampo()){
						cittaCheStoControllando.setDistanzaDalUltimoNodo(costoCarburante);
						cittaCheStoControllando.setDistanzaDalCampo(distanzaDalCampo);
						cittaCheStoControllando.setUltimoNodoID(c.getID());
					}

					//se sono arrivato alle Rovine Perdute e' inutile controllara altre citta che magari non
					//ho ancora visitato, quindi esco
					if(cittaCheStoControllando.getID() == rovinePerduteID){
						hoTrovatoLeRovine = true;
						break;
					}
				}
			}

			//dopo aver visitato una citta la devo rimuovere dall'insieme delle citta da visitare
			cittaDaVisitare.rimuoviCitta(c.getID());
		}

		//dopo aver trovato le Rovine Perdute ripercorro i miei passi per raggiungere il campo base
		//visto che ogni citta ha l'ID della citta' subito piu' vicina al campo base mi basta seguire
		//gli ID fino all'origine
		Rotta rotta = new Rotta(veicolo);
		//incomincio dalle rovine perdute
		int cittaInCuiMiTrovoID = rovinePerduteID;

		//finche' non sono al campo base continuo ad avvicinarmi
		while(cittaInCuiMiTrovoID != campoBaseID){
			//aggiungo la citta alla rotta, visto che so andando dalla fina all'inizio devo inserire le citta
			//non in fondo ma in cima alla rotta
			rotta.addCittaInCima(tabella.getCitta(cittaInCuiMiTrovoID));
			//torno indietro
			cittaInCuiMiTrovoID = tabella.getCitta(cittaInCuiMiTrovoID).getUltimaCittaID();
		}

		//aggiungo il campo base visto che esco dal ciclo prima di poterlo inserire
		rotta.addCittaInCima(tabella.getCitta(cittaInCuiMiTrovoID));

		//ritorno alla rotta
		return rotta;
	}

	/**
	 * Metodo che trova in un gruppo di citta' la piu' vicina al campo
	 * @param cittaDaVisitare - le citta' da controllare
	 * @return la Citta piu' vicina
	 */
	private Citta getPiuVicinaAlCampo(Collection<Citta> cittaDaVisitare) {
		Citta cittaPiuVicina = cittaDaVisitare.toArray(new Citta[0])[0];
		double dist = Double.POSITIVE_INFINITY;

		for(Citta c : cittaDaVisitare) {
			if(c.getDistanzaDalCampo() < dist){
				cittaPiuVicina = c;
				dist = cittaPiuVicina.getDistanzaDalCampo();
			}
		}

		return cittaPiuVicina;
	}
}