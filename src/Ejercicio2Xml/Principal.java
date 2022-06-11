/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2Xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Morad
 */
public class Principal {

    public static void main(String[] args) {
       

        try {

            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            
            Manejador m = new Manejador();
          

            File f = new File("Almacen.xml");

            sp.parse(f, m);
            
            ArrayList<Lote> lista = m.obtenerLista();
            
            for (Lote lote : lista) {
                System.out.println(lote.toString());
            }
            
            
            
           
         crearXml(lista,m);
            
            
            
            

        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
    }
    
    
    private static void crearXml(ArrayList<Lote> lista, Manejador m){
        
        try {
            lista = m.obtenerLista();
            
            
            //System.out.println(lote.toString());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.newDocument();//org
            
          
            
            Element root = d.createElement("Resumen");//org
            d.appendChild(root);
            
           
            for (int i = 0; i <lista.size(); i++) {
                
            Element articulo = d.createElement("Articulo");
            root.appendChild(articulo);
            
            Attr nombre = d.createAttribute("nombre");
            nombre.setValue(String.valueOf(lista.get(i).getContenido().getComponente()));
            articulo.setAttributeNode(nombre);
            
            
            
            Element cajas = d.createElement("cajas");
            cajas.appendChild(d.createTextNode(String.valueOf(lista.get(i).getNumerocajas())));
            articulo.appendChild(cajas);
            
            Element cantidad = d.createElement("cantidad");
            cantidad.appendChild(d.createTextNode(String.valueOf(lista.get(i).getContenido().getUnidades())));
            articulo.appendChild(cantidad);
            
            int tootal = (lista.get(i).numerocajas * lista.get(i).getContenido().getUnidades());
            
            Element total = d.createElement("total");
            total.appendChild(d.createTextNode(String.valueOf(tootal)));
            articulo.appendChild(total);
            
            Element peso = d.createElement("peso");
            peso.appendChild(d.createTextNode(lista.get(i).getPeso()));
            articulo.appendChild(peso);
               
            }
            
            
            
            Source source = new DOMSource(d);
            File f = new File("resumen.xml");
            Result result = new StreamResult(f);
            
            
            
           
           Transformer transformer  = TransformerFactory.newInstance().newTransformer();
                
           transformer.transform(source, result);
                
            System.out.println("Fichero creado exitosamente");
            
            
            
            
            
            } catch (TransformerException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            
            
            
            
            
       
        
        
        
    }

    
}
