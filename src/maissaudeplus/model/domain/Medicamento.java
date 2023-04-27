package maissaudeplus.model.domain;

public class Medicamento {
    private Integer codMedicamento;
    private String nomeMedicamento;
    private String descMedicamento;
    private Double valorMedicamento;   

    public Medicamento() {
    }

    public Medicamento(Integer codMedicamento, String nomeMedicamento, String descMedicamento, Double valorMedicamento) {
        this.codMedicamento = codMedicamento;
        this.nomeMedicamento = nomeMedicamento;
        this.descMedicamento = descMedicamento;
        this.valorMedicamento = valorMedicamento;
    }

    public Integer getCodMedicamento() {
        return codMedicamento;
    }

    public void setCodMedicamento(Integer codMedicamento) {
        this.codMedicamento = codMedicamento;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    public String getDescMedicamento() {
        return descMedicamento;
    }

    public void setDescMedicamento(String descMedicamento) {
        this.descMedicamento = descMedicamento;
    }

    public Double getValorMedicamento() {
        return valorMedicamento;
    }

    public void setValorMedicamento(Double valorMedicamento) {
        this.valorMedicamento = valorMedicamento;
    }

    @Override
    public String toString() {
        return " " + nomeMedicamento;
 }

}
