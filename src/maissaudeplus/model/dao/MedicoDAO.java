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

public class MedicoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<Medico> listar() {
        String sql = "SELECT * FROM medico";
        List<Medico> retorno = new ArrayList();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Medico medico = new Medico();
                medico.setCodMedico(resultado.getInt("codmedico"));
                medico.setCpf(resultado.getString("cpf"));
                medico.setCrmMedico(resultado.getString("crmMedico"));
                medico.setNome(resultado.getString("nomeMedico"));
                medico.setDataAdmissao(resultado.getDate("dataAdmissao"));
                medico.setEmail(resultado.getString("email"));
                medico.setTelefone(resultado.getString("telefone"));
                retorno.add(medico);
            }
        } catch (SQLException e) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }

    public List<String> listarMedicoPorNome() {
        String sql = "SELECT nomemedico FROM medico";
        List<String> retorno = new ArrayList();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Medico medico = new Medico();
                medico.setNome(resultado.getString("nomeMedico"));
                retorno.add(medico.getNome());
            }
        } catch (SQLException e) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }

    public void inserir(Medico medico) {
        String sql = "INSERT INTO medico(cpf, crmmedico, nomemedico, dataadmissao, email, telefone) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, medico.getCpf());
            stmt.setString(2, medico.getCrmMedico());
            stmt.setString(3, medico.getNome());
            stmt.setDate(4, medico.getDataAdmissaoToDb());
            stmt.setString(5, medico.getEmail());
            stmt.setString(6, medico.getTelefone());
            stmt.execute();
        } catch (SQLException e) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public boolean remover(Medico medico){
        String sql = "DELETE FROM Medico WHERE codmedico = ?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, medico.getCodMedico());
            stmt.execute();
            return true;
        } catch (SQLException e){
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public boolean alterar(Medico medico){
        String sql = "UPDATE medico SET cpf=?, crmmedico=?, nomemedico=?, dataadmissao=?, email=?, telefone=? WHERE codmedico=?";
        System.out.println(medico);
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, medico.getCpf());
            stmt.setString(2, medico.getCrmMedico());
            stmt.setString(3, medico.getNome());
            stmt.setDate(4, medico.getDataAdmissaoToDb());
            stmt.setString(5, medico.getEmail());
            stmt.setString(6, medico.getTelefone());
            stmt.setInt(7, medico.getCodMedico());
            stmt.execute();
            return true;
        } catch (SQLException e){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public Medico buscar(Medico medico){
        Medico retorno = new Medico();
        String sql = "SELECT * FROM Medico WHERE codmedico=?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, medico.getCodMedico());
            ResultSet resultado = stmt.executeQuery();
            if(resultado.next()){
                retorno.setCodMedico(resultado.getInt("codmedico"));
                retorno.setCpf(resultado.getString("cpf"));
                retorno.setCrmMedico(resultado.getString("crmmedico"));
                retorno.setDataAdmissao(resultado.getDate("dataadmissao"));
                retorno.setEmail(resultado.getString("email"));
                retorno.setNome(resultado.getString("nomemedico"));
                retorno.setTelefone(resultado.getString("telefone"));
            }
        } catch (SQLException e){
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, e);
        }        
        return retorno;
    }
}
