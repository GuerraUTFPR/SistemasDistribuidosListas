package EX_01;

/**
 * UDPClient: Cliente UDP Descricao: Envia uma msg em um datagrama e recebe a
 * mesma msg do servidor
 */
import java.net.*;

public class UDPClient {

    public static void main(String args[]) {
        /* args[0]: mensagem  e args[1]: ip destino */
        DatagramSocket aSocket = null;

        try {
            aSocket = new DatagramSocket(6666); //cria um socket datagrama
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        }
        //System.out.println("instanciando send thread");
        Send_Thread send = new Send_Thread(aSocket);
        send.start();
        
        //System.out.println("send instanciada");
        //System.out.println("instanciando recive");
        Recive_Thread rt = new Recive_Thread(aSocket);
        rt.start();
        //System.out.println("recive inicializada");
        

    } //main		      	
} //class

