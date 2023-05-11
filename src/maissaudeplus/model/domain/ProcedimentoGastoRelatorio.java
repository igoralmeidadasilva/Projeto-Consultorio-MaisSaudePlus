// classe que tem a especificção dos dados que pertencem a ao relatório gasto por procedimeto
package maissaudeplus.model.domain;


public class ProcedimentoGastoRelatorio extends Procedimento{
    private Double soma;
    
    public ProcedimentoGastoRelatorio() {}

    public ProcedimentoGastoRelatorio(Procedimento p, Double soma) {
        super(p.getCodProcedimento(), p.getNomeProcedimento(), p.getDescProcedimento(), p.getValorProcedimento(), p.isFlagObesidade(),p.getGrupoCorpo());
        this.soma = soma;
    }

    public Double getSoma() {
        return soma;
    }

    @Override
    public String toString() {
        return  super.getNomeProcedimento() + " " + soma;
    } 
}
