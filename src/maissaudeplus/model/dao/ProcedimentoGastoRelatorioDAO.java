//FEITO A ALTERAÇÃO DE QUANTIDADE DE PROCEDIMENTO PARA A SOMA POR PROCEDIMENTO
// VEIFICAR SE O SQL ESTÁ CORRETO

package maissaudeplus.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import maissaudeplus.model.domain.Procedimento;
import maissaudeplus.model.domain.ProcedimentoGastoRelatorio;

public class ProcedimentoGastoRelatorioDAO {
    private Connection connection;
    
    public Connection getConnection(){
        return connection;
    }
    
    public void setConnection(Connection connection){
        this.connection = connection;
    }
    //########################## mudar para valor total
    public List<ProcedimentoGastoRelatorio> listar(){
        String sql = " SELECT pr.codprocedimento   AS codigo, " +
"		pr.nomeprocedimento  AS nome, " +
"		pr.valorprocedimento AS valor, " +
"		pr.descprocedimento  AS descricao, " +
"		pr.flagobesidade     AS flag, " +
"		pr.nomeprocedimento, SUM(pr.valorprocedimento) AS soma" + 
"		FROM	procedimento pr, consultarealizada cr " +
"		WHERE cr.procedimento_codprocedimento = pr.codprocedimento " +
"		AND cr.procedimento_codprocedimento != 1" +
"		GROUP BY pr.codprocedimento, pr.nomeprocedimento " +
"		ORDER BY pr.codprocedimento";
        List<ProcedimentoGastoRelatorio> retorno = new ArrayList<ProcedimentoGastoRelatorio>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Procedimento procedimento = new Procedimento();
                //Obtendo os atributos "básicos"
                procedimento.setCodProcedimento(resultado.getInt("codigo"));
                procedimento.setNomeProcedimento(resultado.getString("nome"));
                procedimento.setDescProcedimento(resultado.getString("descricao"));
                procedimento.setValorProcedimento(resultado.getDouble("valor"));
                procedimento.setFlagObesidade(resultado.getBoolean("flag"));
           
                double soma = resultado.getDouble("soma");
                
                ProcedimentoGastoRelatorio procedimentoRelatorio = new ProcedimentoGastoRelatorio(procedimento, soma);
                retorno.add(procedimentoRelatorio); 
            }
        } catch (SQLException e){
            Logger.getLogger(Procedimento.class.getName()).log(Level.SEVERE, null, e);
        }    
        return retorno;
    }
}
