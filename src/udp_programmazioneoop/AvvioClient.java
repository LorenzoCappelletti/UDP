/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp_programmazioneoop;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loren
 */
public class AvvioClient
{
     public static void main(String[] args) 
    {
        try 
        {
            Client cli = new Client(InetAddress.getLocalHost(), 2000);
        } 
        catch (UnknownHostException ex) 
        {
            Logger.getLogger(AvvioClient.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
      
}
