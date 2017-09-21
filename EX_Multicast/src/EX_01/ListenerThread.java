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
        while (mcSocket != null) {
            byte[] buffer = new byte[1000];            
            DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);

            try {
                mcSocket.receive(messageIn);
            } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
            }
            chatGUI.exibeMsgOutros(new String(messageIn.getData()));            
            
        }
        
    }

}
