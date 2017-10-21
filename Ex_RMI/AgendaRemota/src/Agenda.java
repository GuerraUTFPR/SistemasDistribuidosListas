
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
BCC36C - Sistemas Distribuídos
Departamento da Computação - DACOM
Universidade Tecnológia Federal do Paraná - UTFPR
Professor: Prof. Dr. Rodrigo Campiolo
Aluno: Matheus Sapia Guerra

Este código implementa a interface Agenda.

Desenvolvido por: Matheus Sapia Guerra
*/

public interface Agenda extends Remote{
    public int  addCompr(String[] dados) throws RemoteException;
    public int  altCompr(String[] dados) throws RemoteException;
    public int  delCompr(String id) throws RemoteException;
    public ArrayList<CompromissoObj>  listCompr(String dado) throws RemoteException;    

}
