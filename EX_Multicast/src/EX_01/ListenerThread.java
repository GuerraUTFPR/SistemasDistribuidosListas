/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EX_01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.SocketException;


/**
 *
 * @author guerra
 */
public class ListenerThread extends Thread {

    private final MulticastSocket mcSocket;
    private final ChatMultiCast_GUI chatGUI;

    public ListenerThread(ChatMultiCast_GUI chatGUI, MulticastSocket mcSocket) {
        this.mcSocket = mcSocket;
        this.chatGUI = chatGUI;
        
    }

    @Override
    public void run() {
        while (true) {
            byte[] buffer = new byte[1000];            
            DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
            //System.out.println("teste");
            
            try {
                mcSocket.receive(messageIn);                
                String msg = new String(messageIn.getData(), 0, messageIn.getLength());
                chatGUI.exibeMsgOutros(msg);  
                
            } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
            }
        }
        
    }

}
