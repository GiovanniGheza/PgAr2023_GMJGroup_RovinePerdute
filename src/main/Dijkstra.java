package main;

import java.util.ArrayList;
import java.util.Collection;

public class Dijkstra {

    //tabella con i nodi, la loro distanza dall'origine e il nodo da cui provengono
    AgglomeratoUrbano tabella;

    public Dijkstra(AgglomeratoUrbano mappa) {
        this.tabella = mappa;
    }

    public void dijkstraMagic() {

        //insieme Q dei nodi da visitare
        ArrayList<Citta> nodiDaVisitare = new ArrayList<Citta> (tabella.getAgglomerato().values());
        
        for(int i = 0; !nodiDaVisitare.isEmpty(); i++) {

            Citta c =  getPiuVicinaAlCampo(nodiDaVisitare);

            for(int ID: c.getCollegamenti()) {
                Citta versoACuiStoAndando = tabella.getAgglomerato().get(ID);
                //c.setDistanzaDalCampo(mappa.calcolaDistanza(c.getID(), ID));
                double calc_dist = c.getDistanzaDalCampo() + tabella.calcolaDistanza(c.getID(), ID);

                if(calc_dist < versoACuiStoAndando.getDistanzaDalCampo()){
                    versoACuiStoAndando.setDistanzaDalCampo(calc_dist);
                    versoACuiStoAndando.setUltimoNodoID(c.getID());
                }
            }
            nodiDaVisitare.remove(i);
        }
    }


    public ArrayList<Integer> dijkstraMagicV1() {

        //insieme Q dei nodi da visitare
        ArrayList<Citta> nodiDaVisitare = new ArrayList<Citta> (tabella.getAgglomerato().values());
        int rovinePerduteID = nodiDaVisitare.size() - 1;
        int campoBaseID = 0;
        boolean hoTrovatoLeRovine = false;
        
        //cerco le Rovine Perdute e calcolo la distanza avanzando
        for(int i = 0; !nodiDaVisitare.isEmpty() && !hoTrovatoLeRovine; i++) {
            Citta c =  getPiuVicinaAlCampo(nodiDaVisitare);

            for(int ID: c.getCollegamenti()) {
                Citta versoACuiStoAndando = tabella.getAgglomerato().get(ID);
                //c.setDistanzaDalCampo(mappa.calcolaDistanza(c.getID(), ID));
                double calc_dist = c.getDistanzaDalCampo() + tabella.calcolaDistanza(c.getID(), ID);

                if(calc_dist < versoACuiStoAndando.getDistanzaDalCampo()){
                    versoACuiStoAndando.setDistanzaDalCampo(calc_dist);
                    versoACuiStoAndando.setUltimoNodoID(c.getID());
                }

                if(versoACuiStoAndando.getID() == rovinePerduteID){
                    hoTrovatoLeRovine = true;
                    break;
                }
            }

            nodiDaVisitare.remove(i);
        }

        ArrayList<Integer> percorso = new ArrayList<>();

        int cittaInCuiMiTrovoID = rovinePerduteID;

        //dopo averle trovate torno indietro
        while(cittaInCuiMiTrovoID != campoBaseID){
            percorso.add(0,cittaInCuiMiTrovoID);
            cittaInCuiMiTrovoID = tabella.getCitta(cittaInCuiMiTrovoID).getUltimoNodoID();
        }

        return percorso;
    }

    public ArrayList<Integer> dijkstraMagicV2(Veicolo veicolo) {

        //insieme Q dei nodi da visitare
        ArrayList<Citta> nodiDaVisitare = new ArrayList<Citta> (tabella.getAgglomerato().values());
        int rovinePerduteID = nodiDaVisitare.size() - 1;
        int campoBaseID = 0;
        boolean hoTrovatoLeRovine = false;
        
        //cerco le Rovine Perdute e calcolo la distanza avanzando
        for(int i = 0; !nodiDaVisitare.isEmpty() && !hoTrovatoLeRovine; i++) {
            Citta c =  getPiuVicinaAlCampo(nodiDaVisitare);

            for(int ID: c.getCollegamenti()) {
                Citta versoACuiStoAndando = tabella.getAgglomerato().get(ID);
                double calc_dist = c.getDistanzaDalCampo() + tabella.calcolaCostoCarburante(veicolo,c.getID(), ID);

                if(calc_dist < versoACuiStoAndando.getDistanzaDalCampo()){
                    versoACuiStoAndando.setDistanzaDalCampo(calc_dist);
                    versoACuiStoAndando.setUltimoNodoID(c.getID());
                }

                if(versoACuiStoAndando.getID() == rovinePerduteID){
                    hoTrovatoLeRovine = true;
                    break;
                }
            }

            nodiDaVisitare.remove(i);
            i--;
        }

        //dopo averle trovate torno indietro
        ArrayList<Integer> percorso = new ArrayList<>();
        int cittaInCuiMiTrovoID = rovinePerduteID;

        while(cittaInCuiMiTrovoID != campoBaseID){
            percorso.add(0,cittaInCuiMiTrovoID);
            cittaInCuiMiTrovoID = tabella.getCitta(cittaInCuiMiTrovoID).getUltimoNodoID();
        }

        percorso.add(0,0);
        
        return percorso;
    }
    
    public ArrayList<Integer> dijkstraMagicV3(Veicolo veicolo) {

        //insieme Q dei nodi da visitare
        AgglomeratoUrbano nodiDaVisitare = new AgglomeratoUrbano(tabella);
        int rovinePerduteID = nodiDaVisitare.getAgglomerato().size() - 1;
        int campoBaseID = 0;
        boolean hoTrovatoLeRovine = false;
        
        //cerco le Rovine Perdute e calcolo la distanza avanzando
        for(int i = 0; !nodiDaVisitare.isVuoto() && !hoTrovatoLeRovine; i++) {
            Citta c =  getPiuVicinaAlCampo((nodiDaVisitare.getAgglomerato().values()));

            for(int ID: c.getCollegamenti()) {
            	if(nodiDaVisitare.contieneCitta(ID)) {
            		Citta versoACuiStoAndando = tabella.getAgglomerato().get(ID);
            		double calc_dist = c.getDistanzaDalCampo() + tabella.calcolaCostoCarburante(veicolo,c.getID(), ID);

            		if(calc_dist < versoACuiStoAndando.getDistanzaDalCampo()){
            			versoACuiStoAndando.setDistanzaDalCampo(calc_dist);
            			versoACuiStoAndando.setUltimoNodoID(c.getID());
            		}

            		if(versoACuiStoAndando.getID() == rovinePerduteID){
            			hoTrovatoLeRovine = true;
            			break;
            		}
            	}
            }

            nodiDaVisitare.rimuoviCitta(c.getID());
            i--;
        }

        //dopo averle trovate torno indietro
        ArrayList<Integer> percorso = new ArrayList<>();
        int cittaInCuiMiTrovoID = rovinePerduteID;

        while(cittaInCuiMiTrovoID != campoBaseID){
            percorso.add(0,cittaInCuiMiTrovoID);
            cittaInCuiMiTrovoID = tabella.getCitta(cittaInCuiMiTrovoID).getUltimoNodoID();
        }

        percorso.add(0,0);
        
        return percorso;
    }

    private Citta getPiuVicinaAlCampo(Collection<Citta> nodiDaVisitare) {
    	Citta cittaPiuVicina = nodiDaVisitare.toArray(new Citta[0])[0];
        double dist = Double.POSITIVE_INFINITY;

        for(Citta c : nodiDaVisitare) {
            if(c.getDistanzaDalCampo() < dist){
                cittaPiuVicina = c;
                dist = cittaPiuVicina.getDistanzaDalCampo();
            }
        }

        return cittaPiuVicina;
	}

	public ArrayList<Citta> trovaPercorso(int IDiniziale) {
        for(Citta c: tabella.getAgglomerato().values()) {
            for(int ID: c.getCollegamenti()) {
                c.setDistanzaDalCampo(tabella.calcolaDistanza(c.getID(), ID));
            }
        }
        return null;
    }

    public Citta getPiuVicinaAlCampo(ArrayList<Citta> nodiDaVisitare) {
        Citta cittaPiuVicina = nodiDaVisitare.get(0);
        double dist = Double.POSITIVE_INFINITY;

        for(Citta c : nodiDaVisitare) {
            if(c.getDistanzaDalCampo() < dist){
                cittaPiuVicina = c;
                dist = cittaPiuVicina.getDistanzaDalCampo();
            }
        }

        return cittaPiuVicina;
    }
}