/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EX_01;

/**
 * UDPClient: Cliente UDP Descricao: Envia uma msg em um datagrama e recebe a
 * mesma msg do servidor
 */
import java.net.*;

public class UDPClient2 {

    public static void main(String args[]) {
        /* args[0]: mensagem  e args[1]: ip destino */
        DatagramSocket aSocket = null;

        try {
            aSocket = new DatagramSocket(6666); //cria um socket datagrama
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        }
        Recive_Thread rt = new Recive_Thread(aSocket);
        Send_Thread send = new Send_Thread(aSocket);

    } //main		      	
} //class


