/*
Esta thread tem como finalidade, receber mensagens do cliente, processar e retornar um mensagem para o cliente.
 */
package EX_04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 *
 * @author guerra
 */
public class ListennerServidor extends Thread {

    private DataInputStream in;
    private DataOutputStream outINT;
    private DataOutputStream out;
    private Socket clientSocket;
    private String svdata;
    boolean control = true;

    public ListennerServidor(Socket aClientSocket) {
        try {            
            this.clientSocket = aClientSocket;
            
            //cria objetos de entrada e saida
            this.in = new DataInputStream(this.clientSocket.getInputStream()); //intancia objeto para receber mensagens
            this.out = new DataOutputStream(this.clientSocket.getOutputStream()); //instancia objeto para enviar mensagens 
            this.outINT = new DataOutputStream(this.clientSocket.getOutputStream()); //instancia objeto para enviar mensagens
            this.start(); //inicia a thread
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        } //catch
    } //construtor

    /* metodo executado ao iniciar a thread - start() */
    @Override
    public void run() {
        while (control) {
            try {
                String data = this.in.readUTF(); //aguarda o envio de mensagens 
                //data = (this.clientSocket.getInetAddress() + ": " + data);
                System.out.println(data); //registra mensagem e quem enviou

                String[] partes = data.split(" ");//obtem a parte de string da mensagem recebida

                switch (partes[0]) {
                    case "TIME":
                        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); //instancia objeto para tratar tempo
                        Date time = new Date();
                        this.svdata = "[Servidor]: Hora local: " + timeFormat.format(time); //armazena horario atual em svdata
                        this.out.writeUTF(this.svdata);//retorna para o cliente
                        this.out.flush();//
                        break;

                    case "DATE":
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");//instancia objeto para tratar data
                        Date date = new Date();
                        this.svdata = "[Servidor]: Data local: " + dateFormat.format(date);
                        this.out.writeUTF(this.svdata); //retorna mensagem para o cliente
                        this.out.flush();
                        break;

                    case "FILES":
                        File dir = new File("/home/guerra/Downloads/teste/");
                        File[] filesList = dir.listFiles();
                        int tam = filesList.length;
                        out.writeUTF(String.valueOf(tam));
                        out.flush();
                        //out.writeInt(tam);
                        for (File file : filesList) {
                            if (file.isFile()) {
                                out.writeUTF(file.getName());
                                out.flush();
                            }
                        }
                        break;

                    case "DOWN":
                        File dir2 = new File("/home/guerra/Downloads/teste");
                        File[] filesList2 = dir2.listFiles();
                        for (File file : filesList2) {
                            if (file.getName().equals(partes[1])) {
                                out.writeUTF("tamanho" + String.valueOf(file.length()));
                                out.flush();
                                String path = ("/home/guerra/Downloads/teste/" + file.getName());
                                FileOutputStream fout = new FileOutputStream(path);
                                byte[] bytesArray = Files.readAllBytes(new File("path").toPath());
                                fout.write(bytesArray);
                            } else {
                                out.writeUTF("0");
                            }
                            out.flush();
                        }
                        break;

                    case "EXIT":
                        System.out.println(clientSocket.getInetAddress() + " Desconectou-se");
                        out.writeUTF("ACKEXIT");
                        in.close();
                        out.close();
                        clientSocket.close();
                        control = false;
                        break;

                    default:
                        out.writeUTF(data);// devolve a mensagem para o cliente, caso não seja qualquer comando inserido

                }
            } catch (EOFException e) {
                System.out.println("EOF: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("leitura: " + e.getMessage());
            } //catch
        }//end while
    } //run
}
