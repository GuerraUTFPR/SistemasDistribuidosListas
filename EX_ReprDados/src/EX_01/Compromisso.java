/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EX_01;

import java.io.Serializable;


/**
 *
 * @author guerra
 */
public class Compromisso implements Serializable{
    static Integer n_id = 0;
    private Integer id = 0;
    private final String compromisso;
    private final String data;
    private final String hora;

    public Compromisso(String compromisso, String data, String hora) {
        this.compromisso = compromisso;        
        this.data = data;
        this.hora = hora;
        n_id = n_id + 1;
        this.id = n_id;
    }

    @Override
    public String toString() {
        return ("Compromisso número: " + id + "\ncompromisso: " + compromisso + "\ndata: " + data + "\nhora: " + hora + "\n");
    }
    
 
    
}
