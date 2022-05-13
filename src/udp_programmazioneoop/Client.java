/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp_programmazioneoop;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loren
 */
public class Client 
{
   //numero di porta
    int port = 2000;
    //indirizzo del server
    
    //socket UDP
    DatagramSocket dSocket;
    //Datagramma UDP con la richiesta da inviare al server
    DatagramPacket outPacket;
    //Datagramma UDP di risposta ricevuto dal server
    DatagramPacket inPacket;

    //buffer per i dati da inviare
    byte[] buffer;
    //messaggio di risposta
    String response;
    
    public Client(InetAddress ip, int port)
    {
        try
        {
            
            System.out.println("Indirizzo del server trovato!");
            dSocket = new DatagramSocket();
            String message=invio();
            outPacket = new DatagramPacket(message.getBytes(), message.length(), ip, port); //message.getBytes() da stringa passiamo a byte. 
            dSocket.send(outPacket);
            
            //si prepara il buffer di ricezione
            buffer = new byte[256];
            
            //e il datagramma UDP per ricevere i dati del buffer
            inPacket = new DatagramPacket(buffer, buffer.length);

            //si accetta il datagramma di risposta
            dSocket.receive(inPacket);

            //si estrae il messaggio
            response = new String(inPacket.getData(), 0, inPacket.getLength());

            System.out.println("Connessione stabilita!");
            System.out.println("Data e ora del server: " + response);
            System.out.println("Connessione chiusa!");

            //si chiude la connessione
            dSocket.close();
            
        } 
        catch (SocketException ex) // catch collegato al dSocket
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (UnknownHostException ex) // catch legato al getLocalHost()
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) // catch legato a dSocket.send(outPacket);
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String invio()
    {
        
        return "RICHIESTA DATA E ORA\"";
    } 
}
