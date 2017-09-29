/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EX_03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author guerra
 */
public class ListenPort extends Thread {

    Integer port;
    ArrayList<Socket> list;
    ServidorGUI gui;

    public ListenPort(Integer port, ArrayList<Socket> list, ServidorGUI gui) {
        this.port = port;
        this.gui = gui;

    }

    public void run() {
        try {
            ServerSocket listenSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = listenSocket.accept();

                gui.exibeMsg(clientSocket.getInetAddress() + "Conectou\n");
                
                list.add(clientSocket);
                ListennerServidor ls = new ListennerServidor(gui, clientSocket);
                ls.start();
               
                 gui.listaClientes(list);
            }
            
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }

}
