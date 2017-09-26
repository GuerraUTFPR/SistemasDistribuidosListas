/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EX_02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guerra
 */
public class Listener extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket sendSocket;
    String enviar;
    String recebido;
    Scanner sc = new Scanner(System.in);

    public Listener(Socket aClientSocket) {
        this.sendSocket = aClientSocket;
        this.start();
    }
    public void run(){
        while (true) {

            try {
                in = new DataInputStream(sendSocket.getInputStream());
                out = new DataOutputStream(sendSocket.getOutputStream());

                System.out.print("Você: ");
                enviar = sc.nextLine();

                out.writeUTF(enviar);      	// envia uma string para o servidor
                
                recebido = in.readUTF();
                System.out.println("Servidor: "+ recebido);
                
                if (recebido.equals("SAIR")) {
                    in.close();
                    out.close();
                    sendSocket.close();   //finaliza a conexao
                    System.out.println("Conexão finalizada.");
                    break;
                }//end if

            } catch (IOException ex) {
                Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }//end while
    }
}
