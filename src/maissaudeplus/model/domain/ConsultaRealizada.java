package maissaudeplus.model.domain;

public class ConsultaRealizada {
    private int codConsultaRealizada;
    private Consulta consulta;
    private Procedimento procedimento;
    private Medicamento medicamento;
    
    public ConsultaRealizada(){}
    
    public ConsultaRealizada(int codConsultaRealizada, Consulta consulta, Procedimento procedimento, Medicamento medicamento) {
        this.codConsultaRealizada = codConsultaRealizada;
        this.consulta = consulta;
        this.procedimento = procedimento;
        this.medicamento = medicamento;
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

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    @Override
    public String toString() {
        return "ConsultaRealizada [codConsultaRealizada=" + codConsultaRealizada + ", consulta=" + consulta
                + ", procedimento=" + procedimento + ", medicamento=" + medicamento + "]";
    }
  


    
}
