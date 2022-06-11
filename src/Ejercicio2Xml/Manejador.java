/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2Xml;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Morad
 */
public class Manejador extends DefaultHandler {
    
    ArrayList<Lote> listaLotes = new ArrayList();
    
    Contenido contenido;
    Lote lote;
    
    StringBuilder sb = new StringBuilder();
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sb.append(ch, start, length);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        switch (qName) {
            
            case "contenido":
                lote.setContenido(contenido);
                break;
            case "numcajas":
                lote.setNumerocajas(Integer.parseInt(sb.toString()));
                break;
            case "componente":
                contenido.setComponente(sb.toString());
                break;
            case "numserie":
                contenido.setNumserie(sb.toString());
                break;
            case "unidades":
                contenido.setUnidades(Integer.parseInt(sb.toString()));
                break;
            case "peso":
                lote.setPeso(sb.toString());
                break;
            case "manipulacion":
                lote.setManipulacion(sb.toString());
                break;
            
        }
        
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        switch (qName) {
            case "lote":
                lote = new Lote();
                listaLotes.add(lote);
                
                lote.setId(attributes.getValue("id"));
                
                break;
            case "contenido":// se inicia la otra lista 
                contenido = new Contenido();
            
            case "numcajas":
            case "componente":
            case "numserie":
            case "unidades":
            case "peso":
            case "manipulacion":
                
                sb.delete(0, sb.length());
                break;
            
        }
        
    }
    
    public ArrayList<Lote> obtenerLista() {
        
        return listaLotes;
    }
    
}
