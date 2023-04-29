package maissaudeplus.model.domain;

public class ProcedimentoRelatorio extends Procedimento{
    private Integer qtde;
    
    public ProcedimentoRelatorio() {}

    public ProcedimentoRelatorio(Procedimento p, Integer qtde) {
        super(p.getCodProcedimento(), p.getNomeProcedimento(), p.getDescProcedimento(), p.getValorProcedimento(), p.isFlagObesidade(),p.getGrupoCorpo());
        this.qtde = qtde;
    }

    public Integer getQtde() {
        return qtde;
    }

    @Override
    public String toString() {
        return  super.getNomeProcedimento() + " " + qtde;
    } 
}
