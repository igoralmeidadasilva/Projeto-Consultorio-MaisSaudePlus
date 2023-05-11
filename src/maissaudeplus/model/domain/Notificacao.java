// classe que tem a especificção dos dados que pertencem a uma notificacao
// classe que tem os métodos get e set da notificacao
package maissaudeplus.model.domain;

import java.time.LocalDate;

public class Notificacao {
    private int codNotificao;
    private LocalDate dataNotificacao;
    private Paciente paciente;

    public Notificacao(int codNotificao, LocalDate dataNotificacao, Paciente paciente) {
        this.codNotificao = codNotificao;
        this.dataNotificacao = dataNotificacao;
        this.paciente = paciente;
    }

    public Notificacao() {
        
    }

    public int getCodNotificao() {
        return codNotificao;
    }

    public void setCodNotificao(int codNotificao) {
        this.codNotificao = codNotificao;
    }

    public LocalDate getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(LocalDate dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Notificacao{" + "codNotificao=" + codNotificao + ", dataNotificacao=" + dataNotificacao + ", paciente=" + paciente + '}';
    }
    
    
}
