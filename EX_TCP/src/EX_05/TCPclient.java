/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EX_05;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


/**
 *
 * @author guerra
 */
public class TCPclient {

    public static void main(String args[]) {
        Socket s = null;
        Scanner sc = new Scanner(System.in);

        int serverPort = 7896;
        try {
            /* especifica a porta */
            s = new Socket("127.0.0.1", serverPort);
            /* conecta com o servidor */

            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String data;

            while (true) {
                data = sc.nextLine();
                out.writeUTF(data);      	// envia uma string para o servidor
                if (data.equals("SAIR")) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("leitura:" + e.getMessage());

        } //catch

    }
}
