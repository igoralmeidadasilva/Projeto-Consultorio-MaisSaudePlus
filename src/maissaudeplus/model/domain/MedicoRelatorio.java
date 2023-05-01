package maissaudeplus.model.domain;

import java.time.LocalDate;

public class MedicoRelatorio extends Medico{
    private Integer total;
    private String nome;
    
    public MedicoRelatorio(){
         
    }

    public MedicoRelatorio(String nome, Integer total) {
        this.nome = nome;
        this.total = total;
    }
    
    public String getNome(){
        return nome;
    }
      
    public Integer getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "MedicoRelatorio{" + "total=" + total + ", nome=" + nome + '}';
    } 
     
}
