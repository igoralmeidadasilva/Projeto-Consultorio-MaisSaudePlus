package maissaudeplus.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import maissaudeplus.model.domain.Medico;
import maissaudeplus.model.domain.MedicoRelatorio;

public class MedicoRelatorioDAO {
    private Connection connection;
    
    public Connection getConnection(){
        return connection;
    }
    
    public void setConnection(Connection connection){
        this.connection = connection;
    }
    
    public List<MedicoRelatorio> listarQuantidadeConsultasPorMedico(){
        String sql = 
        "SELECT me.nomemedico,COUNT(cr.codconsultarealizada) AS total " +
                "FROM medico me, consulta co, consultarealizada cr WHERE "+
                "me.codmedico = co.medico_codmedico AND co.codconsulta = cr.consulta_codconsulta " +
                "GROUP BY me.nomemedico ORDER BY me.nomemedico ASC";
        List<MedicoRelatorio> retorno = new ArrayList<MedicoRelatorio>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Medico medico = new Medico();
                medico.setNome(resultado.getString("nomemedico"));
                int total = resultado.getInt("total");
                MedicoRelatorio medicoRelatorio = new MedicoRelatorio(medico.getNome(), total);
                retorno.add(medicoRelatorio); 
            }
        } catch (SQLException e){
            Logger.getLogger(Medico.class.getName()).log(Level.SEVERE, null, e);
        }    
        return retorno;
    }
}
