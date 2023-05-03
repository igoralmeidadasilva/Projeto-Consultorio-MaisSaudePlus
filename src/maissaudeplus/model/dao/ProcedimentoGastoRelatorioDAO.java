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
        String sql = "SELECT 	p.codprocedimento AS codigo, " +
		"p.nomeprocedimento AS nome, " +
		"p.valorprocedimento AS valor, " +
		"p.descprocedimento AS descricao," +
		"p.flagobesidade AS flag, " +
                // verificar se a sintaxe está correta
		"p.nomeprocedimento, SUM(cr.valorprocedimento) AS soma" +
		"FROM	procedimento cr, " +
                "GROUP BY codprocedimento" +
                // --------
		"procedimento p " +
		"WHERE cr.procedimento_codprocedimento = p.codprocedimento " +
		"GROUP BY p.codprocedimento, p.nomeprocedimento " +
		"ORDER BY p.codprocedimento";
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
