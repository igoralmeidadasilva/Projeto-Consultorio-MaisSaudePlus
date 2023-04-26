package maissaudeplus.model.domain;

import java.time.LocalDate;

public class GerarNotificacao {
    private int codGerarNotificao;
    private LocalDate dataNotificacao;
    private Funcionario funcionario;

    public GerarNotificacao(int codGerarNotificao, LocalDate dataNotificacao, Funcionario funcionario) {
        this.codGerarNotificao = codGerarNotificao;
        this.dataNotificacao = dataNotificacao;
        this.funcionario = funcionario;
    }

    public int getCodGerarNotificao() {
        return codGerarNotificao;
    }

    public void setCodGerarNotificao(int codGerarNotificao) {
        this.codGerarNotificao = codGerarNotificao;
    }

    public LocalDate getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(LocalDate dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    
}
