/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        String sql = "SELECT * FROM paciente";
        List<Paciente> retorno = new ArrayList();
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
}
