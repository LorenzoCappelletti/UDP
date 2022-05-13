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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loren
 */
public class Server 
{
    //oggetto Socket UDP 
    DatagramSocket dSocket;
    //datagramma UDP ricevuto dal client
    DatagramPacket inPacket;
    //datagramma UDP di risposta da inviare
    DatagramPacket outPacket;
    //Buffer per il contenuto del segmento da ricevere
    byte[] buffer; // array di byte per assegnare il nostro payload( nel payload si scambiano byte)
    //Testo dei messaggi in I/O
    String messageIn, messageOut; 
    //Data e ora correnti
    Date d;
    InetAddress clientAddress;
    int clientPort;
     
    public Server (int port)
    {
        try
        {
            //si crea il socket e si associa alla porta specifica
            dSocket = new DatagramSocket(port); // costruttore socket avente come parametro solo la porta di ascolto, verrà generata una eccezione
            // si realizza la primitiva socket e la primitiva bind --> dSocket = new DatagramSocket(port);
            System.out.println("Apertura porta in corso!");
            
            while(true) // creazione del nostro ciclo infinito
            {
               ascoltoRicezione();
               invio();
            }
        } 
        catch (SocketException ex)  //catch legato a dSocket = new DatagramSocket(port);
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } 
    } 
    
    public void ascoltoRicezione()
    {
        buffer = new byte[256]; // stabilire quanti byte conterrà il nostro array. In questo caso 256 byte

        //si crea un datagramma UDP in cui trasportare il buffer di lunghezza length
        inPacket = new DatagramPacket(buffer, buffer.length); // il server può leggere i dati del client solo attraverso il buffer. Si crea una area di memoria.
        try 
        {
            //si ricevono i byte dal client e si blocca finchè arrivano i pacchetti
            dSocket.receive(inPacket);
            //si recupera l'indirizzo IP e la porta UDP del client
            clientAddress = inPacket.getAddress(); //inPacket ha le informazioni del client per poi rispondere al mittente che poi diventa il destinatario
            clientPort = inPacket.getPort(); //inPacket ha le informazioni del client per poi rispondere al mittente che poi diventa il destinatario
            //si stampa a video il messaggio ricevuto dal client
            messageIn = new String(inPacket.getData(), 0, inPacket.getLength()); //getData() contiene le informazioni del nostro payload. Si parte dal carattere 0 per poi specificare la lunghezza.
            System.out.println("SONO IL CLIENT " + clientAddress + 
                                ":" + clientPort + "> " + messageIn);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    public void invio()
    {
        d = new Date();
        //si crea il messaggio del server in uscita associandolo alla connessione aperta con il client
        messageOut = d.toString();

        //si crea un datagramma UDP in cui trasportare il messaggio di lunghezza length
        outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
        try 
        {
            //si invia il messaggio al client
            dSocket.send(outPacket);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Risposta inviata!");
    }
    
    
}
