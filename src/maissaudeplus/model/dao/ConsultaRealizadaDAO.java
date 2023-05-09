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
import maissaudeplus.model.domain.Consulta;
import maissaudeplus.model.domain.ConsultaRealizada;
import maissaudeplus.model.domain.Medicamento;
import maissaudeplus.model.domain.Paciente;
import maissaudeplus.model.domain.Procedimento;

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
    
    //#########################################################################################################
    // implementar o método listar por paciente (será usuado para carregar o paciente em minha tela
    // listatPaciente();
    public List<ConsultaRealizada> listarPorPaciente (Paciente paciente){
        String sql = "SELECT codconsultarealizada, consulta_codconsulta, procedimento_codprocedimento, medicamento_codmedicamento " +
                        "FROM	consultarealizada cr, " +
                        "consulta co " +
                        "WHERE cr.consulta_codconsulta = co.codconsulta " +
                        "AND co.paciente_codpaciente = ?";
        
        List<ConsultaRealizada> retorno = new ArrayList();
        
        
        ConsultaDAO consultaDAO = new ConsultaDAO();
        consultaDAO.setConnection(connection);
        
        ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
        procedimentoDAO.setConnection(connection);
        
        MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
        medicamentoDAO.setConnection(connection);
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, paciente.getCodPaciente());
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Consulta consulta = new Consulta();
                Procedimento procedimento = new Procedimento();
                Medicamento medicamento = new Medicamento();
                ConsultaRealizada consultaRealizada = new ConsultaRealizada();
                
                //Começando a preencher a consultaRealizada
                consultaRealizada.setCodConsultaRealizada(resultado.getInt("codconsultarealizada"));
                
                //Buscando Consulta
                consulta = consultaDAO.buscarPorCodigo(resultado.getInt("consulta_codconsulta"));
                
                //Buscando Procedimento
                procedimento = procedimentoDAO.buscarPorCodigo(resultado.getInt("procedimento_codprocedimento"));
                
                //Buscando Médico
                medicamento = medicamentoDAO.buscarPorCodigo(resultado.getInt("medicamento_codMedicamento"));
                
                //Terminando de preencher a consultaRealizada
                consultaRealizada.setConsulta(consulta);
                consultaRealizada.setProcedimento(procedimento);
                consultaRealizada.setMedicamento(medicamento);
                
                //Adicionando a lista de retorno
                retorno.add(consultaRealizada);
            }           
        } catch(SQLException e){
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }
}
