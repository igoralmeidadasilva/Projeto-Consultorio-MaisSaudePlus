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
import maissaudeplus.model.domain.Procedimento;

public class ProcedimentoDAO {
    
    
    private Connection connection;
    
    public Connection getConnection(){
        return connection;
    }
    
    public void setConnection(Connection connection){
        this.connection = connection;
    }
    
    public List<Procedimento> listar(){
        String sql = "SELECT * FROM procedimento";
        List<Procedimento> retorno = new ArrayList();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()){
                Procedimento procedimento = new Procedimento();
                procedimento.setCodProcedimento(resultado.getInt("codprocedimento"));
                procedimento.setNomeProcedimento(resultado.getString("nomeprocedimento"));
                procedimento.setDescProcedimento(resultado.getString("descprocedimento")); 
                procedimento.setValorProcedimento(resultado.getDouble("valorprocedimento"));
                procedimento.setFlagObesidade(resultado.getBoolean("flagobesidade"));
                retorno.add(procedimento);
            }
        } catch(SQLException e){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }
    
    public void inserir(Procedimento procedimento){
        String sql = "INSERT INTO procedimento (nomeprocedimento, descprocedimento, valorprocedimento, flagobesidade) "
                        + "VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, procedimento.getNomeProcedimento());
            stmt.setString(2, procedimento.getDescProcedimento());
            stmt.setDouble(3, procedimento.getValorProcedimento());
            stmt.setBoolean(4, procedimento.isFlagObesidade());
            stmt.execute();
        } catch(SQLException e){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public boolean remover(Procedimento procedimento){
        String sql = "DELETE FROM procedimento WHERE codprocedimento = ?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, procedimento.getCodProcedimento());
            stmt.execute();
            return true;
        } catch (SQLException e){
            Logger.getLogger(ProcedimentoDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public boolean alterar(Procedimento procedimento){
        String sql = "UPDATE procedimento SET nomeprocedimento=?, descprocedimento=?, valorprocedimento=?, flagobesidade=?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, procedimento.getNomeProcedimento());
            stmt.setString(2, procedimento.getDescProcedimento());
            stmt.setDouble(3, procedimento.getValorProcedimento());
            stmt.setBoolean(4, procedimento.isFlagObesidade());
            stmt.execute();
            return true;
        } catch (SQLException e){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public Procedimento buscar(Procedimento paciente){
        Procedimento retorno = new Procedimento();
        String sql = "SELECT * FROM procedimento WHERE codprocedimento=?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, paciente.getCodProcedimento());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()){
                retorno.setCodProcedimento(resultado.getInt("codprocedimento"));
                retorno.setNomeProcedimento(resultado.getString("nomeprocedimento"));
                retorno.setDescProcedimento(resultado.getString("descprocedimento")); 
                retorno.setValorProcedimento(resultado.getDouble("valorprocedimento"));
                retorno.setFlagObesidade(resultado.getBoolean("flagobesidade"));;
            }
        } catch(SQLException e) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }
        public String getNomeProcedimento(Procedimento procedimento){
        String sql = "SELECT nomeprocedimento FROM procedimento WHERE codprocedimento = ?;";
        String retorno = "";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, procedimento.getCodProcedimento());
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                retorno = resultado.getString("nomeprocedimento");
            }
        } catch (SQLException e){
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }
}