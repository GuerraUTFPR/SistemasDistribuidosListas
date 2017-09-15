/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EX_01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guerra
 */
public class Send_Thread extends Thread {

    DatagramSocket sendSocket;

    public Send_Thread(DatagramSocket socket) {
        this.sendSocket = socket;
        this.run();
    }

    public void run() {
        System.out.print("Digite um IP: ");
        Scanner scanner = new Scanner(System.in);
        String IP = scanner.nextLine();
        int serverPort = 6666;

        InetAddress aHost = null;
        try {
            aHost = InetAddress.getByName(IP);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Send_Thread.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {
            System.out.print("Você: ");
            String data = scanner.nextLine();
            byte[] m = data.getBytes(); // transforma a mensagem em bytes

            /* armazena o IP do destino */
            try {
                /* cria um pacote datagrama */
                DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
                /* envia o pacote */
                sendSocket.send(request);

            } catch (UnknownHostException ex) {
                Logger.getLogger(Send_Thread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Send_Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
