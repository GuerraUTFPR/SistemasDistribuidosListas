package EX_01;

/**
 * UDPClient: Cliente UDP
 * Descricao: Envia uma msg em um datagrama e recebe a mesma msg do servidor
 */

import java.net.*;
import java.io.*;

public class UDPClient{
    public static void main(String args[]){ 
        /* args[0]: mensagem  e args[1]: ip destino */
        DatagramSocket aSocket = null; 

        try {
            aSocket = new DatagramSocket(); //cria um socket datagrama
            Send_Thread send = new Send_Thread(aSocket);
           	                        
            
            /* cria um buffer vazio para receber datagramas */
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
            
            /* aguarda datagramas */
            aSocket.receive(reply);
            System.out.println("Resposta: " + new String(reply.getData()));

	    /* libera o socket */
	    aSocket.close();	
        } catch (SocketException e){
	    System.out.println("Socket: " + e.getMessage());
        }catch (IOException e){
	    System.out.println("IO: " + e.getMessage());
        } //catch
    } //main		      	
} //class

