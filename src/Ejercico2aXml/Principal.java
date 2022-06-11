/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercico2aXml;

import Ejercicio2bXml.Lote;
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

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        File f = new File("datos.xml");

        Manejador m = new Manejador();

        ArrayList<Cliente> listaC = m.obtenerListaClientes();
        ArrayList<Producto> listaP = m.obtenerListaProductos();

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();

        sp.parse(f, m);

        for (Cliente cliente : listaC) {
            System.out.println(cliente);
        }

        for (Producto producto : listaP) {
            System.out.println(producto);
        }
        
        crearXml(listaC, listaP, m);

    }

    private static void crearXml(ArrayList<Cliente> listaC, ArrayList<Producto> listaP, Manejador m) {

        try {

            listaC = m.obtenerListaClientes();
            listaP = m.obtenerListaProductos();

            //System.out.println(lote.toString());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.newDocument();//org

            Element root = d.createElement("Facturas");//org
            d.appendChild(root);

            for (int i = 0; i < listaC.size(); i++) {
                int j = 0;

                while (j < listaP.size() && !listaP.get(j).getId().equals(listaC.get(i).getProducto())) {
                   j++;
                    if (j == listaP.size()) {
                        System.out.println("Ese producto no se encuentra");
                    } else {

                        if (listaC.get(i).getUnidades() < listaP.get(j).getStock()) {
                            Element factura = d.createElement("Factura");
                            root.appendChild(factura);

                            Attr atributo = d.createAttribute("NIF");
                            atributo.setValue(listaC.get(i).getNif());
                            factura.setAttributeNode(atributo);

                            Element descripcion = d.createElement("Descripcion");
                            descripcion.appendChild(d.createTextNode(listaP.get(j).getDescripcion()));
                            factura.appendChild(descripcion);

                            Element unidades = d.createElement("Unidades");
                            unidades.appendChild(d.createTextNode(String.valueOf(listaC.get(i).getUnidades())));
                            factura.appendChild(unidades);

                            listaP.get(j).setStock(listaP.get(j).getStock() - listaC.get(i).getUnidades());

                            Element precio = d.createElement("Precio");
                            precio.appendChild(d.createTextNode(String.valueOf(listaP.get(j).getPrecio())));
                            factura.appendChild(precio);

                            Element total = d.createElement("Total");
                            total.appendChild(d.createTextNode(String.valueOf(listaP.get(j).getPrecio() * listaC.get(i).getUnidades())));
                            factura.appendChild(total);

                        } else {
                            System.out.println("No hay suficientes unidades para vender");
//chekear precio y total
                        }
                    }
                }

            }

            Source source = new DOMSource(d);
            File f = new File("facturas.xml");
            Result result = new StreamResult(f);

            Transformer t = TransformerFactory.newInstance().newTransformer();

            t.transform(source, result);

            System.out.println("Fichero creado exitosamente");

        } catch (TransformerException ex) {
            Logger.getLogger(Ejercicio2bXml.Principal.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Ejercicio2bXml.Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
