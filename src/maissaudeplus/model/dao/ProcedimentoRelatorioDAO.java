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
import maissaudeplus.model.domain.ProcedimentoRelatorio;

public class ProcedimentoRelatorioDAO {
    private Connection connection;
    
    public Connection getConnection(){
        return connection;
    }
    
    public void setConnection(Connection connection){
        this.connection = connection;
    }
    
    public List<ProcedimentoRelatorio> listar(){
        String sql = "SELECT 	p.codprocedimento AS codigo, " +
		"p.nomeprocedimento AS nome, " +
		"p.valorprocedimento AS valor, " +
		"p.descprocedimento AS descricao," +
		"p.flagobesidade AS flag, " +
		"COUNT(cr.procedimento_codprocedimento) AS quantidade " +
		"FROM	consultaRealizada cr, " +
		"procedimento p " +
		"WHERE cr.procedimento_codprocedimento = p.codprocedimento " +
		"AND cr.procedimento_codprocedimento != 1 " +
                "GROUP BY p.codprocedimento, p.nomeprocedimento " +
		"ORDER BY p.codprocedimento";
        List<ProcedimentoRelatorio> retorno = new ArrayList<ProcedimentoRelatorio>();
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
                
                int qtde = resultado.getInt("quantidade");
                
                ProcedimentoRelatorio procedimentoRelatorio = new ProcedimentoRelatorio(procedimento, qtde);
                retorno.add(procedimentoRelatorio); 
            }
        } catch (SQLException e){
            Logger.getLogger(Procedimento.class.getName()).log(Level.SEVERE, null, e);
        }    
        return retorno;
    }
}
