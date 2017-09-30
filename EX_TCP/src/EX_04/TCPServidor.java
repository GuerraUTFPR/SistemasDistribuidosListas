/* Este código tem por finalidade criar um servidor TCP e instanciar uma thread para ouvir os clientes*/

package EX_04;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author guerra
 */
public class TCPServidor {

    public static void main(String args[]) {
        int serverPort = 7896; //define a porta de escuta 7896

        System.out.println("Servidor iniciado!");        

        try {
            ServerSocket listenSocket = new ServerSocket(serverPort); // cria um serverSocket

            while (true) {
                Socket clientSocket = new Socket(); //cria um socket novo
                clientSocket = listenSocket.accept(); //aceita um socket               

                System.out.println(clientSocket.getInetAddress() + " Conectou-se ao servidor."); //log de cliente conectado

                ListennerServidor ls = new ListennerServidor(clientSocket);//criauma thread para cada socket de cliente criado.
            }

        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }

}
