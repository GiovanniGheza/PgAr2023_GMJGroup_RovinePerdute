package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * una classe che permette di creare degli scrittori di XML
 * @author Kumar Mega
 */
public abstract class ScrittoreXML {

	private static final String TAB = "\t";
	private static final String A_CAPO = "\n";
	private static final String ERRORE_SCRITTURA = "Errore nella scrittura: ";
	private static final String NAME = "name";
	private static final String ID = "id";
	private static final String CITY = "city";
	private static final String CITIES = "cities";
	private static final String COST = "cost";
	private static final String TEAM = "team";
	private static final String ROUTE = "route";
	private static final String ROUTES = "routes";
	private static final String ERRORE_INIZIALIZZAZIONE_WRITER = "Errore nell'inizializzazione del writer:";
	private static final String VERSION = "1.0";
	private static final String ENCODING = "utf-8";

	public static void salvaRotte(String configFile, Rotta rotta1, Rotta rotta2) throws FileNotFoundException, XMLStreamException{
		XMLOutputFactory xmlof = null;
		XMLStreamWriter xmlw = null;

		Rotta rotte[] = new Rotta[2];
		rotte[0] = rotta1;
		rotte[1] = rotta2;

		//inizializazzione del writer
		try {
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(configFile), ENCODING);
			xmlw.writeStartDocument(ENCODING, VERSION);
		} catch (Exception e) {
			System.out.println(ERRORE_INIZIALIZZAZIONE_WRITER);
			System.out.println(e.getMessage());
		}

		try { // blocco try per raccogliere eccezioni
			xmlw.writeCharacters(A_CAPO);
			xmlw.writeCharacters(TAB);
			xmlw.writeStartElement(ROUTES); // scrittura del tag radice <routes>
			xmlw.writeCharacters(A_CAPO);
			xmlw.writeCharacters(TAB);
			xmlw.writeCharacters(TAB);

			for(int i = 0; i < rotte.length; i++) {
				xmlw.writeStartElement(ROUTE); //scrittura del tag route
				xmlw.writeAttribute(TEAM, rotte[i].getNomeVeicolo());
				xmlw.writeAttribute(COST, Integer.toString((int)rotte[i].getCarburanteUsato()));
				xmlw.writeAttribute(CITIES, Integer.toString(rotte[i].getNumeroCittaToccate()));


				for (int j = 0; j < rotte[i].getNumeroCittaToccate(); j++) {
					xmlw.writeCharacters(A_CAPO);
					xmlw.writeCharacters(TAB);
					xmlw.writeCharacters(TAB);
					xmlw.writeCharacters(TAB);
					xmlw.writeEmptyElement(CITY); // scrittura del tag city...
					xmlw.writeAttribute(ID, Integer.toString(rotte[i].getID(j))); // ...con attributo id...
					xmlw.writeAttribute(NAME, rotte[i].getNomeCitta(j)); // ...con attributo name...
				}
				xmlw.writeCharacters(A_CAPO);
				xmlw.writeCharacters(TAB);
				xmlw.writeCharacters(TAB);
				xmlw.writeEndElement(); // chiusura di </route>
				if(i < rotte.length - 1) {
					xmlw.writeCharacters(A_CAPO);
					xmlw.writeCharacters(TAB);
					xmlw.writeCharacters(TAB);
				}
			}
			xmlw.writeCharacters(A_CAPO);
			xmlw.writeEndDocument(); // scrittura della fine del documento
			xmlw.flush(); // svuota il buffer e procede alla scrittura
			xmlw.close(); // chiusura del documento e delle risorse impiegate

		} catch (Exception e) { // se c’è un errore viene eseguita questa parte
			System.out.println(ERRORE_SCRITTURA + e);
		}
	}
}