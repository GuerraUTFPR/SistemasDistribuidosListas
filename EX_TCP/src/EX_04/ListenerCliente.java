/*
Esta código é uma thread de um cliente, que tme por objetivo ficar ouvindo mensagens do servidor.
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
public class ListenerCliente extends Thread {

    DataInputStream inUTF;
    DataInputStream inINT;
    Socket clientSocket;
    ClienteGUI gui;
    String data;
    int qtd;
    boolean ctrl = true;

    int flag = 0;

    public ListenerCliente(ClienteGUI gui, Socket aClientSocket) {
        this.gui = gui;

        try {
            clientSocket = aClientSocket;
            
            //instancia objetos de entrada
            inUTF = new DataInputStream(clientSocket.getInputStream());
            
            this.start();
            /* inicializa a thread */
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        } //catch
    } //construtor

    /* metodo executado ao iniciar a thread - start() */
    @Override
    public void run() {

        while (ctrl) {
            try {
                //System.out.println("esperando um dado");
                this.data = inUTF.readUTF(); //aguarda uma string ser enviada
                //System.out.println("recebi "+ data);
                

                if (data.equals("ACKEXIT")) {
                    inUTF.close();
                    clientSocket.close();
                    ctrl = false;
                    gui.setVisible(false);                    
                    this.interrupt();
                }
                System.out.println(data); //imprime a string no console
//                if (data.equals("FILES")) {
//                    System.out.println("esperando um int");
//                    qtd = inINT.readInt();
//                    System.out.println("recebi um int");
//                    data = "";
//                }
            } catch (EOFException e) {
                System.out.println("EOF: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("leitura: " + e.getMessage());
            } //catch
        }//end while
    } //run

    void change(int i) {
        this.flag = i;
    }
} //class

