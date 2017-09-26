/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EX_01;

/**
 *
 * @author guerra
 */
/**
 * TCPServer: Servidor para conexao TCP com Threads
 * Descricao: Recebe uma conexao, cria uma thread, recebe uma mensagem e finaliza a conexao
 */

import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Servidor TCP que recebe dois objetos serializados
 * @author rodrigo
 */
public class TCPserver {
    
    public static void main (String args[]) {
        Compromisso p1, p2;
        ServerSocket serverSocket;
        Socket clientSocket;
        ArrayList<Compromisso> lista = new ArrayList();
        
        ObjectInputStream objIn;
        
        try {
            System.out.println ("Mapeando porta ...");
            serverSocket = new ServerSocket (6666);
            
            System.out.println ("Servidor aguardando conexões ...");
            clientSocket = serverSocket.accept();
            
            System.out.println ("Criando objetos de leitura/escrita ...");
            objIn = new ObjectInputStream (clientSocket.getInputStream());
            
            System.out.println ("Aguardando objetos serializados ...");
            p1 = (Compromisso) objIn.readObject();
            p2 = (Compromisso) objIn.readObject();            
            System.out.println ("\nObjetos Recebidos\n");
            
            //System.out.println(p1.toString());
            //System.out.println(p2.toString());
            lista.add(p1);
            lista.add(p2);
            
            DataInputStream in = new DataInputStream( clientSocket.getInputStream());
            ObjectOutputStream objOut = new ObjectOutputStream (clientSocket.getOutputStream());
            while(true){
                if (in.readUTF().equals("listar")){
                    System.out.println("Enviando lista de compromissos...");
                    objOut.writeObject(lista);
                    objOut.flush();
                    System.out.println("Lista enviada!");
                    break;
                }
            }

            
            System.out.println("\nSistema finalizado!");
            
        } catch (Exception e) {
            System.out.println(e);
        } //catch
    } //main
    
    
}
