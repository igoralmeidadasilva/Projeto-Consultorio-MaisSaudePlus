package maissaudeplus.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import maissaudeplus.model.domain.ConsultaRealizada;

public class ConsultaRealizadaDAO {
    private Connection connection;
    
    public Connection getConnection(){
        return connection;
    }
    
    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public void inserir(ConsultaRealizada consultaRealizada){
        String sql = "INSERT INTO consultarealizada(consulta_codconsulta, procedimento_codprocedimento, medicamento_codmedicamento)" + "VALUES (?, ?, ?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, consultaRealizada.getConsulta().getCodConsulta());
            stmt.setInt(2, consultaRealizada.getProcedimento().getCodProcedimento());
            stmt.setInt(3, consultaRealizada.getMedicamento().getCodMedicamento());
            stmt.execute();
        }catch(SQLException e){
            Logger.getLogger(ConsultaRealizadaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public boolean buscarConsultaRealizada(ConsultaRealizada consultaRealizada) {
        String sql = "SELECT * FROM consultarealizada WHERE consulta_codconsulta=?";
        List<ConsultaRealizada> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, consultaRealizada.getConsulta().getCodConsulta());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaRealizadaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Map<String,Double> gastoPorProcedimentoSolicitado(){
        String sql = "SELECT me.nomemedico, SUM(pr.valorprocedimento) AS total FROM procedimento pr, consultarealizada cr, consulta co, medico me WHERE cr.consulta_codconsulta = co.codconsulta AND co.medico_codmedico = me.codmedico GROUP BY me.nomemedico";
        Map<String,Double> retorno = new HashMap();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                retorno.put(resultado.getString("nomemedico"), resultado.getDouble("total"));
            }
        } catch (SQLException e) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    
    }
}
