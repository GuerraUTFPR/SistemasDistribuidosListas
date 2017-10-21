/*
BCC36C - Sistemas Distribuídos
Departamento da Computação - DACOM
Universidade Tecnológia Federal do Paraná - UTFPR
Professor: Prof. Dr. Rodrigo Campiolo
Aluno: Matheus Sapia Guerra

Este código implementa o Servidor.

Desenvolvido por: Prof. Dr. Rodrigo Campiolo
Modificado por: Matheus Sapia Guerra
*/
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

public class Servidor {

    public static void main(String args[]) {
        ArrayList<CompromissoObj> compList = new ArrayList();
       
        
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            /* inicializa um objeto remoto */
            Agenda agenda = new AgendaObj();
            
            /* registra o objeto remoto no Binder */
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            registry.bind("ServicoAgenda", agenda);

            /* aguardando invocacoes remotas */
            System.out.println("Servidor pronto ...");            
                     
        } catch (Exception e) {
            System.out.println(e);
        } //catch
    } //main
} //Servidor
