package main;

import java.util.ArrayList;

public class MainRovinePerdute {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        AgglomeratoUrbano a = LettoreXML.leggiCitta(".\\data\\PgAr_Map_10000.xml");

        Dijkstra d = new Dijkstra(a);

        ArrayList<Integer> ar = d.dijkstraMagicV3(new Metztli());

        System.out.println(ar.toString());
        
        ar = d.dijkstraMagicV3(new Tonatiuh());

        System.out.println(ar.toString());
    }
}
