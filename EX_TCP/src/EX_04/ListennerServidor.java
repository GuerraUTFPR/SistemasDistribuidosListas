/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EX_04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 *
 * @author guerra
 */
public class ListennerServidor extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    ServidorGUI gui;
    String svdata;

    public ListennerServidor(ServidorGUI gui, Socket aClientSocket) {
        this.gui = gui;
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            /* inicializa a thread */
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        } //catch
    } //construtor

    /* metodo executado ao iniciar a thread - start() */
    public void run() {
        while (true) {
            try {
                String data = in.readUTF();
                /* aguarda o envio de svdatas */
                data = (clientSocket.getInetAddress() + ": " + data);
                gui.exibeMsg(data);
                //out.writeUTF(data);
                gui.sendMsg(data);

                String[] partes = data.split(" ");

                switch (partes[1]) {
                    case "!TIME":
                        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                        Date time = new Date();
                        svdata = "[Servidor]: Hora local: " + timeFormat.format(time);
                        out.writeUTF(svdata);
                        gui.exibeMsg(svdata);
                        break;

                    case "!DATE":
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        svdata = "[Servidor]: Data local: " + dateFormat.format(date);
                        out.writeUTF(svdata);
                        gui.exibeMsg(svdata);
                        break;

                    case "!FILES":
                        svdata = "";
                        File dir = new File("/home/guerra/Downloads/");
                        File[] filesList = dir.listFiles();
                        String cabecalho = "[Servidor]: \n"
                                + "=-=-=-=-=-= FILES =-=-=-=-=-=\n";
                        for (File file : filesList) {
                            if (file.isFile()) {
                                svdata = svdata + "-> " + file.getName() + "\n";
                            }

                        }
                        String rodape = "=-=-=-=-= END FILES =-=-=-=-=\n";
                        svdata = cabecalho + svdata + rodape;

                        out.writeUTF(svdata);
                        gui.exibeMsg(svdata);
                        break;

                }

                /*if(data.equals("!SAIR")){
                    gui.removeList(clientSocket);
                    clientSocket.close();
                    in.close();
                    out.close();
                    this.interrupt();
                    break;
                }*/
            } catch (EOFException e) {
                System.out.println("EOF: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("leitura: " + e.getMessage());
            } //catch
        }//end while
    } //run
}
