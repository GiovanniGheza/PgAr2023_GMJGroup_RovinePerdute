package main;

import javax.xml.stream.*;
import java.io.*;
import java.util.Iterator;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * una classe che permette di creare dei lettori di XML
 * @author Kumar Mega
 */
public abstract class LettoreXML {

    private static final String LINK = "link";
    private static final String H_STRINGA = "h";
    private static final String Y_STRINGA = "y";
    private static final String X_STRINGA = "x";
    private static final String NAME = "name";
    private static final String ID_STRING = "id";
    private static final String CITY = "city";

    public static AgglomeratoUrbano leggiCitta(String nomeFile){
        AgglomeratoUrbano agglomerato = new AgglomeratoUrbano();
        try{
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = new FileInputStream(nomeFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            Citta citta = new Citta();
            
            while(eventReader.hasNext()){
                XMLEvent event =  eventReader.nextEvent();
                
                if(event.isStartElement()){
                    StartElement startElement = event.asStartElement();
                    String elementName = startElement.getName().getLocalPart();
                    
                    switch(elementName){
                        case CITY:
                            int x = 0, y = 0, h = 0;
                            String nome = new String();
                            int ID = 0;
                            citta = new Citta();

                            Iterator<Attribute> attributes = startElement.getAttributes();

                            while(attributes.hasNext()){
                                Attribute attribute = attributes.next();

                                switch(attribute.getName().toString()) {
                                    case ID_STRING:
                                        ID = Integer.parseInt(attribute.getValue());
                                        break;
                                    case NAME:
                                        nome = attribute.getValue();
                                        break;
                                    case X_STRINGA:
                                        x = Integer.parseInt(attribute.getValue());
                                        break;
                                    case Y_STRINGA:
                                        y = Integer.parseInt(attribute.getValue());
                                        break;
                                    case H_STRINGA:
                                        h = Integer.parseInt(attribute.getValue());
                                        break;
                                }
                            }

                            citta.set(x, y, h, nome, ID);
                            break;
                        case LINK:
                            event = eventReader.nextEvent();
                            Iterator<Attribute> attributi = startElement.getAttributes();
                            Attribute attribute = attributi.next();
                            citta.addCollegamento(Integer.parseInt(attribute.getValue()));
                            break;
                    }
                }
                if(event.isEndElement()){
                    EndElement endElement = event.asEndElement();
                    if(endElement.getName().getLocalPart().equals(CITY))
                        agglomerato.addCitta(citta);
                }
            }
        }catch (Exception e){
            System.out.println("Error -> "+e.getMessage());
        }
        return agglomerato;
    }
}