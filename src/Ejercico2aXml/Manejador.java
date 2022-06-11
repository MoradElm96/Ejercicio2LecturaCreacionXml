/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercico2aXml;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Morad
 */
public class Manejador extends DefaultHandler {
    
    ArrayList<Cliente> listaClientes = new ArrayList();
    ArrayList<Producto> listaProductos = new ArrayList();
    
    StringBuilder sb = new StringBuilder();
    
    Cliente cliente;
    Producto producto;
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sb.append(ch, start, length);
        
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        switch (qName) {
            
            case "nombre":
                cliente.setNombre(sb.toString());
                
                break;
            
            case "NIF":
                cliente.setNif(sb.toString());
                
                break;
            
            case "fecha":
                
                cliente.setFecha(sb.toString());
                break;
            
            case "producto":
                
                cliente.setProducto(sb.toString());
                
                break;
            
            case "unidades":
                
                cliente.setUnidades(Integer.parseInt(sb.toString()));
                
                break;
                
            case "descripcion": producto.setDescripcion(sb.toString());
                break;
            case "pecio": producto.setPrecio(Integer.parseInt(sb.toString()));
                break;
            case "stock": producto.setStock(Integer.parseInt(sb.toString()));
                break;
            
        }
        
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        switch (qName) {
            
            case "cliente":
                cliente = new Cliente();
                listaClientes.add(cliente);
                
                cliente.setId(attributes.getValue("id"));
                
                break;
                
            case "producto":
                
                producto = new Producto();
                listaProductos.add(producto);
                producto.setId(attributes.getValue("id"));
                break;
            
            case "nombre":
            case "NIF":
            case "fecha":
            
            case "unidades":
            
            case "descripcion":
            case "precio":
            case "stock":
                 sb.delete(0, sb.length());
                break;
               
            
        }
        
    }
    
    
    
    
    public ArrayList<Cliente> obtenerListaClientes() {
        
        return listaClientes;
    }
    
     public ArrayList<Producto> obtenerListaProductos() {
        
        return listaProductos;
    }
    
    
}
