package maissaudeplus.model.domain;

import java.sql.Date;
import java.time.LocalDate;

public class Paciente extends Pessoa{
    private int codPaciente;
    private LocalDate dataNascimento;
    private double altura;
    private double peso;
    private char sexo;    
    
    public Paciente(){}
    
    public Paciente(String cpf, String nome, String email, String telefone, int codPaciente, LocalDate dataNascimento, double altura, double peso, char sexo) {
        super(cpf, nome, email, telefone);
        this.codPaciente = codPaciente;
        this.dataNascimento = dataNascimento;
        this.altura = altura;
        this.peso = peso;
        this.sexo = sexo;
    }

    public int getCodPaciente() {
        return codPaciente;
    }

    public void setCodPaciente(int codPaciente) {
        this.codPaciente = codPaciente;
    }

    public String getDataNascimento() {  
        return dataNascimento.toString();
    }
    
    public Date getDataNascimentoToDb() {  
        return Date.valueOf(dataNascimento);
    }
    
    public LocalDate getDataNascimentoToLocalDate(){
        return dataNascimento;
    }
    
    public void setDataNascimento(LocalDate dataNascimento){
        this.dataNascimento = dataNascimento;
    }
    
    public void setDataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento.toLocalDate();
    }
    
    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return super.getNome();
    } 
}
