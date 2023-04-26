package maissaudeplus.model.domain;

public class Procedimento {
    private int codProcedimento;
    private String nomeProcedimento;
    private String descProcedimento;
    private double valorProcedimento;
    private boolean flagObesidade;
    private String grupoCorpo;
    
    public Procedimento(){}

    public Procedimento(int codProcedimento, String nomeProcedimento, String descProcedimento, double valorProcedimento, boolean flagObesidade, String grupoCorpo) {
        this.codProcedimento = codProcedimento;
        this.nomeProcedimento = nomeProcedimento;
        this.descProcedimento = descProcedimento;
        this.valorProcedimento = valorProcedimento;
        this.flagObesidade = flagObesidade;
        this.grupoCorpo = grupoCorpo;
    }

    public int getCodProcedimento() {
        return codProcedimento;
    }

    public void setCodProcedimento(int codProcedimento) {
        this.codProcedimento = codProcedimento;
    }

    public String getNomeProcedimento() {
        return nomeProcedimento;
    }

    public void setNomeProcedimento(String nomeProcedimento) {
        this.nomeProcedimento = nomeProcedimento;
    }

    public String getDescProcedimento() {
        return descProcedimento;
    }

    public void setDescProcedimento(String descProcedimento) {
        this.descProcedimento = descProcedimento;
    }

    public double getValorProcedimento() {
        return valorProcedimento;
    }

    public void setValorProcedimento(double valorProcedimento) {
        this.valorProcedimento = valorProcedimento;
    }

    public boolean isFlagObesidade() {
        return flagObesidade;
    }

    public void setFlagObesidade(boolean flagObesidade) {
        this.flagObesidade = flagObesidade;
    }

    public String getGrupoCorpo() {
        return grupoCorpo;
    }

    public void setGrupoCorpo(String grupoCorpo) {
        this.grupoCorpo = grupoCorpo;
    }
    
    
}
