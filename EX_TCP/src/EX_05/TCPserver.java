/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EX_05;

/**
 *
 * @author guerra
 */
/**
 * TCPServer: Servidor para conexao TCP com Threads Descricao: Recebe uma
 * conexao, cria uma thread, recebe uma mensagem e finaliza a conexao
 */
import java.net.*;
import java.io.*;

public class TCPserver {

    public static void main(String args[]) {
        int i = 1;

        try {
            int serverPort = 7896; // porta do servidor
            /* cria um socket e mapeia a porta para aguardar conexão */
            ServerSocket listenSocket = new ServerSocket(serverPort);

            System.out.println("Servidor aguardando conexão ...");
            /* aguarda conexões */
            Socket clientSocket1 = listenSocket.accept();
            System.out.println("Cliente " + i + " conectado ... Criando thread ...");
            /* cria um thread para atender a conexão */
            Connection c1 = new Connection(clientSocket1);
            i++;

            System.out.println("Servidor aguardando conexão ...");
            /* aguarda conexões */
            Socket clientSocket2 = listenSocket.accept();
            System.out.println("Cliente " + i + " conectado ... Criando thread ...");
            /* cria um thread para atender a conexão */
            Connection c2 = new Connection(clientSocket2);
            i++;

            System.out.println("Clientes conectados");
            
            //Share share = new Share(c1, c2);

        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        } //catch
    } //main
} //class

/**
 * Classe Connection: Thread responsavel pela comunicacao Descricao: Rebebe um
 * socket, cria os objetos de leitura e escrita e aguarda msgs clientes
 */
class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        } //catch
    } //construtor

    /* metodo executado ao iniciar a thread - start() */
    public void run() {
        String data = "";
        try {

            while (!data.equals("SAIR")) {
                data = in.readUTF();
                /* aguarda o envio de dados */
                System.out.println("Cliente disse: " + data);

                if (data.equals("SAIR")) {
                    out.writeUTF("ACKSAIR");
                    in.close();
                    out.close();
                    clientSocket.close();
                }
                out.writeUTF(data);

            }
        } catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("leitura: " + e.getMessage());
        } //catch

    } //run
} //class
