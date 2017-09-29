/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EX_04;


import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author guerra
 */
public class ListenerCliente extends Thread{
    DataInputStream in;    
    Socket clientSocket;
    ClienteGUI gui;

    public ListenerCliente(ClienteGUI gui, Socket aClientSocket) {
        this.gui = gui;
        
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());           
            
            /* inicializa a thread */
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        } //catch
    } //construtor

    /* metodo executado ao iniciar a thread - start() */
    public void run() {
        while (true) {
            try {
                String data = in.readUTF();
                /* aguarda o envio de dados */
                //System.out.println(data);
                gui.exibeMsg(data);
                
            } catch (EOFException e) {
                System.out.println("EOF: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("leitura: " + e.getMessage());
            } //catch
        }//end while
    } //run
} //class

