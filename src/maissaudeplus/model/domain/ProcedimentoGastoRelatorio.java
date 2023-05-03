package maissaudeplus.model.domain;


public class ProcedimentoGastoRelatorio extends Procedimento{
    private Double soma;
    
    public ProcedimentoGastoRelatorio() {}

    public ProcedimentoGastoRelatorio(Procedimento p, Double soma) {
        super(p.getCodProcedimento(), p.getNomeProcedimento(), p.getDescProcedimento(), p.getValorProcedimento(), p.isFlagObesidade(),p.getGrupoCorpo());
        this.soma = soma;
    }

    public Double getQtde() {
        return soma;
    }

    @Override
    public String toString() {
        return  super.getNomeProcedimento() + " " + soma;
    } 
}
