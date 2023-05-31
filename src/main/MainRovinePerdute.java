package main;

public class MainRovinePerdute {
    public static void main(String[] args) throws Exception {
    	
    	//lettura dati
    	System.out.println("Lettura Dati...");
        AgglomeratoUrbano a = LettoreXML.leggiCitta(".\\data\\PgAr_Map_10000.xml");
        System.out.println("Lettura Dati Completata!\n");

        //creo dijkstra
        Dijkstra d = new Dijkstra(a);

        //calcolo la rotta della Metztli
        System.out.println("Calcolo Rotta Metztli...");
        Rotta rottaM = d.dijkstraMagic(new Metztli());
        System.out.println("Rotta Metztli Calcolata!\n");
        
        //calcolo la rotta della Tonatiuh
        System.out.println("Calcolo Rotta Tonatiuh...");
        Rotta rottaT = d.dijkstraMagic(new Tonatiuh());
        System.out.println("Rotta Tonatiuh Calcolata!\n");
        
        //scrivo i dati nel file di output
        System.out.println("Scrittura Dati...");
        ScrittoreXML.salvaRotte(".\\data\\Routes.xml", rottaM, rottaT);
        System.out.println("Scrittura Dati Completata!\n");
    }
}
