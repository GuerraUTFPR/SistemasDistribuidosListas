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
 * TCPClient: Cliente para conexao TCP
 * Descrição: Envia uma informacao ao servidor e finaliza a conexao
 */
import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 * 
 * @author rodrigo
 */
public class TCPcliente {
    
    public static void main (String args[]) {
        Compromisso a,b;
        
        Socket s;
        
        ObjectOutputStream objOut;
        
        try {
            System.out.println ("Criando instâncias da classe Pessoa ...\n");
            
            a = new Compromisso ("Entregar lista 3 de SD", "29/09/2017", "13:00");
            b = new Compromisso("Entrgar lista de análise", "03/10/2017", "13:00");
            
            
            System.out.println ("Conectando ao servidor ...\n");
            s = new Socket ("localhost",6666);
            
            System.out.println ("Criando objetos de leitura/escrita ...\n");
            objOut = new ObjectOutputStream (s.getOutputStream());
            
            System.out.println ("Enviando objetos serializados ...\n");
            objOut.writeObject(a);
            objOut.writeObject(b);
            objOut.flush();
            
            DataOutputStream out =new DataOutputStream( s.getOutputStream());
            System.out.println("Solicitando listagem...");
            out.writeUTF("listar"); 
            
            ObjectInputStream objIn = new ObjectInputStream (s.getInputStream());
            ArrayList<Compromisso> lista = (ArrayList<Compromisso>) objIn.readObject();
            
            System.out.println("Listando:\n"+ lista.get(0).toString() + "\n" + lista.get(1).toString());
            
            System.out.println ("Finalizado.");
            
            
        } catch (Exception e) {
            System.out.println(e);
        } //catch
    } //main
    
    
}
