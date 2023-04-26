package maissaudeplus.model.domain;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


public class Consulta {
    private int codConsulta;
    private LocalDate dataConsulta;
    private LocalTime horaConsulta;
    private int duracaoConsulta;
    private String statusConsulta;
    private Medico medico;
    private Paciente paciente;
    private Funcionario funcionario;
   
    public Consulta(){}

    public Consulta(int codConsulta, LocalDate dataConsulta, LocalTime horaConsulta, int duracaoConsulta, String statusConsulta, Medico medico, Paciente paciente, Funcionario funcionario) {
        this.codConsulta = codConsulta;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        this.duracaoConsulta = duracaoConsulta;
        this.statusConsulta = statusConsulta;
        this.medico = medico;
        this.paciente = paciente;
        this.funcionario = funcionario;
    }

   public int getCodConsulta() {
        return codConsulta;
    }

    public void setCodConsulta(int codConsulta) {
        this.codConsulta = codConsulta;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }
    
    public Date getDataConsultaToDb() {
        return Date.valueOf(dataConsulta);
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
    
    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta.toLocalDate();
    }

    public LocalTime getHoraConsulta() {
        return horaConsulta;
    }
    
    public Time getHoraConsultaToDb() {
        return Time.valueOf(horaConsulta);
    }

    public void setHoraConsulta(LocalTime horaConsulta) {
        this.horaConsulta = horaConsulta;
    }
    
    public void setHoraConsulta(Time horaConsulta) {
        this.horaConsulta = horaConsulta.toLocalTime();
    }

    public int getDuracaoConsulta() {
        return duracaoConsulta;
    }

    public void setDuracaoConsulta(int duracaoConsulta) {
        this.duracaoConsulta = duracaoConsulta;
    }

    public String getStatusConsulta() {
        return statusConsulta;
    }

    public void setStatusConsulta(String statusConsulta) {
        this.statusConsulta = statusConsulta;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "{" + "codConsulta=" + codConsulta + ", dataConsulta=" + dataConsulta + ", horaConsulta=" + horaConsulta + ", duracaoConsulta=" + duracaoConsulta + ", statusConsulta=" + statusConsulta + ", medico=" + medico + ", paciente=" + paciente + ", funcionario=" + funcionario + '}';
    }
   
   
}
