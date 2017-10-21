
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
/*
BCC36C - Sistemas Distribuídos
Departamento da Computação - DACOM
Universidade Tecnológia Federal do Paraná - UTFPR
Professor: Prof. Dr. Rodrigo Campiolo
Aluno: Matheus Sapia Guerra

Este código tem por finalidade implementar um compromisso, tal como metodos para obter ou alterar seus atributos

Desenvolvido por: Matheus Sapia Guerra
*/

public class CompromissoObj implements Serializable {

    String id;
    private String data;
    private String hora;
    private String assunto;
    private String descricao;
    private String notificacao;

    public CompromissoObj(String data, String hora, String assunto, String descricao, String notificacao, String id) {
        this.data = data;
        this.hora = hora;
        this.assunto = assunto;
        this.descricao = descricao;
        this.notificacao = notificacao;
        this.id = id;
    }

    public String toStringAdd() {
        return "\nNovo compromisso adicionado:\n" + "ID: " + id + "\nData: " + data + "\nHora: " + hora + "\nAssunto: " + assunto + "\nDescricao: " + descricao + "\nNotificacao: " + notificacao;
    }

    public String toStringRm() {
        return "\nCompromisso removido:\n" + "ID: " + id + "\nData: " + data + "\nHora: " + hora + "\nAssunto: " + assunto + "\nDescricao: " + descricao + "\nNotificacao: " + notificacao;
    }

    @Override
    public String toString() {
        return "\nCompromisso:\n" + "ID: " + id + "\nData: " + data + "\nHora: " + hora + "\nAssunto: " + assunto + "\nDescricao: " + descricao + "\nNotificacao: " + notificacao;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(String notificacao) {
        this.notificacao = notificacao;
    }

}
