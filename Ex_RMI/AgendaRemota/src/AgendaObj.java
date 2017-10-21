import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
/*
BCC36C - Sistemas Distribuídos
Departamento da Computação - DACOM
Universidade Tecnológia Federal do Paraná - UTFPR
Professor: Prof. Dr. Rodrigo Campiolo
Aluno: Matheus Sapia Guerra

Este código implementa os métodos da interface Agenda.

Desenvolvido por: Matheus Sapia Guerra
*/

public class AgendaObj extends UnicastRemoteObject implements Agenda {

    ArrayList<CompromissoObj> compList = new ArrayList();
    CompromissoObj c;

    public AgendaObj() throws RemoteException {
        super();
        System.out.println("Objeto remoto instanciado...");
    }

    @Override
    public int addCompr(String[] dados) throws RemoteException {
        c = new CompromissoObj(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5]); //cria um novo objeto comprimisso
        compList.add(c);//adciona em uma lista
        System.out.println(c.toStringAdd());//o servidor imprime o log
        return 1;
    }

    @Override
    public int altCompr(String[] dados) throws RemoteException {
        //percorre buscando o compromisso a ser alterado
        for (int i = 0; i < compList.size(); i++) {
            if (compList.get(i).getId().equals(dados[5])) {
                System.out.println("\nAlteração realizada\nAntes:" + compList.get(i).toString());
                //realiza a alteração
                compList.get(i).setData(dados[0]);
                compList.get(i).setHora(dados[1]);
                compList.get(i).setAssunto(dados[2]);
                compList.get(i).setDescricao(dados[3]);
                compList.get(i).setNotificacao(dados[4]);

                System.out.println("\nAlterado para:" + compList.get(i).toString());
            }
        }
        return 1;
    }

    @Override
    public int delCompr(String id) throws RemoteException {
        //percorre a lista buscando o compromisso a ser alterado
        for (int i = 0; i < compList.size(); i++) {
            if (compList.get(i).getId().equals(id)) {
                System.out.println(compList.get(i).toStringRm());
                compList.remove(i);//remove o compromisso
            }
        }

        return 1;
    }

    @Override
    public ArrayList<CompromissoObj> listCompr(String dado) throws RemoteException {
        ArrayList<CompromissoObj> ListByDate = new ArrayList<CompromissoObj>();
        
        if (dado.equals("")) {//envia a listagem completa de compromissos
          return compList;

        } else {//envia os compromissos filtrados por data

            System.out.println("\nSolicitação de listagem por data recebida.");

            for (int i = 0; i < compList.size(); i++) {
                if (compList.get(i).getData().equals(dado)) {
                    ListByDate.add(compList.get(i));
                }
            }

            System.out.println("Solitação de Listagem por data respondida.");
        }

        return ListByDate;
    }

}
