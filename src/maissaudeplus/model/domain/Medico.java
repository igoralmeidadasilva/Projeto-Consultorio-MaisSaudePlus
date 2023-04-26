package maissaudeplus.model.domain;

import java.sql.Date;
import java.time.LocalDate;

public class Medico extends Pessoa{
    private int codMedico;
    private String crmMedico;
    private LocalDate dataAdmissao;

    public Medico(){}
    
    public Medico(int codMedico, String crmMedico, LocalDate dataAdmissao, String cpf, String nome, String email, String telefone) {
        super(cpf, nome, email, telefone);
        this.codMedico = codMedico;
        this.crmMedico = crmMedico;
        this.dataAdmissao = dataAdmissao;
    }

    public int getCodMedico() {
        return codMedico;
    }

    public void setCodMedico(int codMedico) {
        this.codMedico = codMedico;
    }

    public String getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(String crmMedico) {
        this.crmMedico = crmMedico;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }
    
    public Date getDataAdmissaoToDb() {
        return Date.valueOf(dataAdmissao);
    }
    
     public void setDataAdmissao(LocalDate dataAdmissao){
        this.dataAdmissao = dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao.toLocalDate();
    } 
    
    @Override
    public String toString() {
        return super.getNome();
    }
}
