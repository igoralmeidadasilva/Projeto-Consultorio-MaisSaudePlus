package maissaudeplus.model.domain;

import java.sql.Date;
import java.time.LocalDate;

public class Funcionario extends Pessoa {

    private int codFuncionario;
    private LocalDate dataAdmissao;
    
    public Funcionario(){}

    public Funcionario(int codFuncionario, LocalDate dataAdmissao, String cpf, String nome, String email, String telefone) {
        super(cpf, nome, email, telefone);
        this.codFuncionario = codFuncionario;
        this.dataAdmissao = dataAdmissao;
    }

    public int getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
    public void setDataAdmissao(Date dataAdmissao){
        this.dataAdmissao = dataAdmissao.toLocalDate();
    }

    @Override
    public String toString() {
        return super.getNome();
    }
}
