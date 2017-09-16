/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EX_01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guerra
 */
public class Recive_Thread extends Thread {

    DatagramSocket reciveSocket;

    public Recive_Thread(DatagramSocket socket) {
        this.reciveSocket = socket;
        this.run();

    }

    public void run() {
        while (true) {
            byte[] buffer = new byte[1000]; // cria um buffer para receber requisições

            /* cria um pacote vazio */
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            try {
                reciveSocket.receive(request);  // aguarda a chegada de datagramas
            } catch (IOException ex) {
                Logger.getLogger(Recive_Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
            /* imprime e envia o datagrama de volta ao cliente */
            System.out.println("Cliente: " + new String(request.getData()));
        } //while
    }
}
