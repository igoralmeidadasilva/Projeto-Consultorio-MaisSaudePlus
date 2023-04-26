package maissaudeplus.model.domain;

public class ConsultaRealizada {
    private int codConsultaRealizada;
    private Consulta consulta;
    private Procedimento procedimento;

    
    public ConsultaRealizada(){}
    
    public ConsultaRealizada(int codConsultaRealizada, Consulta consulta, Procedimento procedimento) {
        this.codConsultaRealizada = codConsultaRealizada;
        this.consulta = consulta;
        this.procedimento = procedimento;
    }

    public int getCodConsultaRealizada() {
        return codConsultaRealizada;
    }

    public void setCodConsultaRealizada(int codConsultaRealizada) {
        this.codConsultaRealizada = codConsultaRealizada;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    
}
