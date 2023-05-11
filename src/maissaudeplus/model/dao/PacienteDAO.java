package maissaudeplus.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import maissaudeplus.model.domain.Paciente;

public class PacienteDAO {
    
    private Connection connection;
    
    public Connection getConnection(){
        return connection;
    }
    
    public void setConnection(Connection connection){
        this.connection = connection;
    }
    
    public List<Paciente> listar(){
        String sql = "SELECT * FROM paciente ORDER BY codpaciente ASC";
        List<Paciente> retorno = new ArrayList<>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()){
                Paciente paciente = new Paciente();
                paciente.setCodPaciente(resultado.getInt("codpaciente"));
                paciente.setCpf(resultado.getString("cpf"));
                paciente.setNome(resultado.getString("nomepaciente"));
                paciente.setDataNascimento(resultado.getDate("dataNascimento"));   
                paciente.setAltura(resultado.getDouble("altura"));
                paciente.setPeso(resultado.getDouble("peso"));
                paciente.setSexo(resultado.getString("sexo").charAt(0));
                paciente.setEmail(resultado.getString("email"));
                paciente.setTelefone(resultado.getString("telefone"));
                paciente.setNumConsultas(resultado.getInt("numConsultas"));
                retorno.add(paciente);
            }
        } catch(SQLException e){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }
    
    public void inserir(Paciente paciente){
        String sql = "INSERT INTO Paciente (cpf, nomePaciente, dataNascimento, altura, peso, sexo, email, telefone) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, paciente.getCpf());
            stmt.setString(2, paciente.getNome());
            stmt.setDate(3, paciente.getDataNascimentoToDb());
            stmt.setDouble(4, paciente.getAltura());
            stmt.setDouble(5, paciente.getPeso());
            stmt.setString(6, Character.toString(paciente.getSexo()));
            stmt.setString(7, paciente.getEmail());
            stmt.setString(8, paciente.getTelefone());
            stmt.execute();
        } catch(SQLException e){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public boolean remover(Paciente paciente){
        String sql = "DELETE FROM Paciente WHERE codpaciente = ?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, paciente.getCodPaciente());
            stmt.execute();
            return true;
        } catch (SQLException e){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public boolean alterar(Paciente paciente){
        String sql = "UPDATE Paciente SET cpf=?, nomePaciente=?, dataNascimento=?, altura=?, peso=?, sexo=?, email=?, telefone=? WHERE codpaciente=?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, paciente.getCpf());
            stmt.setString(2, paciente.getNome());
            stmt.setDate(3, paciente.getDataNascimentoToDb());
            stmt.setDouble(4, paciente.getAltura());
            stmt.setDouble(5, paciente.getPeso());
            stmt.setString(6, Character.toString(paciente.getSexo()));
            stmt.setString(7, paciente.getEmail());
            stmt.setString(8, paciente.getTelefone());
            stmt.setInt(9, paciente.getCodPaciente());
            stmt.execute();
            return true;
        } catch (SQLException e){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public Paciente buscar(Paciente paciente){
        Paciente retorno = new Paciente();
        String sql = "SELECT * FROM Paciente WHERE codpaciente=?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, paciente.getCodPaciente());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()){
                retorno.setCodPaciente(resultado.getInt("codpaciente"));
                retorno.setAltura(resultado.getDouble("altura"));
                retorno.setCpf(resultado.getString("cpf"));
                retorno.setDataNascimento(resultado.getDate("datanascimento"));
                retorno.setEmail(resultado.getString("email"));
                retorno.setNome(resultado.getString("nomepaciente"));
                retorno.setPeso(resultado.getDouble("peso"));
                retorno.setSexo(resultado.getString("sexo").charAt(0));
                retorno.setTelefone(resultado.getString("telefone"));
            }
        } catch(SQLException e) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }
        public int getNumConsultas (Paciente paciente){
        String sql = "SELECT numconsultas FROM paciente WHERE codPaciente = ?;";
        int retorno = 0;
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, paciente.getCodPaciente());
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                retorno = resultado.getInt("numconsultas");
            }
        } catch (SQLException e){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }
    
    public boolean addNumConsultas(Paciente paciente){
        String sql = "UPDATE Paciente SET numConsultas = ? WHERE codPaciente = ?";
        int numConsultas = 1 + getNumConsultas(paciente);
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, numConsultas);
            stmt.setInt(2, paciente.getCodPaciente());
            stmt.execute();
            return true;
        }catch (SQLException e){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
        public boolean removeNumConsultas(Paciente paciente){
        String sql = "UPDATE Paciente SET numConsultas = ? WHERE codPaciente = ?";
        int numConsultas = 1 - getNumConsultas(paciente);
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, numConsultas);
            stmt.setInt(2, paciente.getCodPaciente());
            stmt.execute();
            return true;
        }catch (SQLException e){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
}
