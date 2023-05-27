package main;

import java.util.ArrayList;

public class MainRovinePerdute {
    public static void main(String[] args) throws Exception {

        AgglomeratoUrbano a = LettoreXML.leggiCitta(".\\data\\PgAr_Map_10000.xml");

        Dijkstra d = new Dijkstra(a);

        Rotta rottaM = d.dijkstraMagicV4(new Metztli());

        System.out.println(rottaM.toString() + "\n\n");
        
        Rotta rottaT = d.dijkstraMagicV4(new Tonatiuh());

        System.out.println(rottaT.toString());
    }
}
