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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    String sql = "SELECT * FROM Procedimento";
    List<Procedimento> retorno = new ArrayList<Procedimento>();
    try{
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet resultado = stmt.executeQuery();
        while(resultado.next()){
            Procedimento procedimento = new Procedimento();

            //Obtendo os atributos "básicos"
            procedimento.setCodProcedimento(resultado.getInt("codprocedimento"));
            procedimento.setNomeProcedimento(resultado.getString("nomeProcedimento"));
            procedimento.setDescProcedimento(resultado.getString("descProcedimento"));
            procedimento.setValorProcedimento(resultado.getDouble("valorProcedimento"));
            procedimento.setFlagObesidade(resultado.getBoolean("flagObesidade"));
            //Adicionando a Lista de retorno
            retorno.add(procedimento); 
            }
        } catch (SQLException e){
            Logger.getLogger(Procedimento.class.getName()).log(Level.SEVERE, null, e);
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
        String sql = "UPDATE procedimento SET nomeprocedimento=?, descprocedimento=?, valorprocedimento=?, flagobesidade=? WHERE codprocedimento=?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, procedimento.getNomeProcedimento());
            stmt.setString(2, procedimento.getDescProcedimento());
            stmt.setDouble(3, procedimento.getValorProcedimento());
            stmt.setBoolean(4, procedimento.isFlagObesidade());
            stmt.setInt(5, procedimento.getCodProcedimento());
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
                retorno.setFlagObesidade(resultado.getBoolean("flagobesidade"));
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
        
    public Map<Integer, Integer> listarQuantidadeProcedimentoPorMes(int ano) {
        String sql = "SELECT COUNT (cr.procedimento_codprocedimento) AS qtde, EXTRACT (MONTH FROM co.dataconsulta) AS mes " +
                        "from consultarealizada cr, consulta co " +
                        "WHERE cr.consulta_codconsulta = co.codconsulta " +
                        "GROUP BY mes ORDER BY mes ASC";
        Map<Integer, Integer> retorno = new HashMap();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            //stmt.setInt(1, ano);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                retorno.put(resultado.getInt("mes"), resultado.getInt("qtde"));
            }
        } catch (SQLException e) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }
        
    
    public Map<Procedimento, Integer> relatorioQtde(){
        String sql = "SELECT 	p.codprocedimento AS codigo, " +
		"p.nomeprocedimento AS nome, " +
		"p.valorprocedimento AS valor, " +
		"p.descprocedimento AS descricao," +
		"p.flagobesidade AS flag, " +
		"COUNT(cr.procedimento_codprocedimento) AS quantidade " +
		"FROM	consultaRealizada cr, " +
		"procedimento p " +
		"WHERE cr.procedimento_codprocedimento = p.codprocedimento " +
		"GROUP BY p.codprocedimento, p.nomeprocedimento " +
		"ORDER BY p.codprocedimento";
        Map<Procedimento, Integer> retorno = new HashMap();  
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Procedimento procedimento = new Procedimento();
                procedimento.setCodProcedimento(resultado.getInt("codigo"));
                procedimento.setNomeProcedimento(resultado.getString("nome"));
                procedimento.setValorProcedimento(resultado.getDouble("valor"));
                procedimento.setDescProcedimento(resultado.getString("descricao"));
                procedimento.setFlagObesidade(resultado.getBoolean("flag"));
                int quantidade = resultado.getInt("quantidade");
                retorno.put(procedimento, quantidade);
            }
        } catch (SQLException e){
            Logger.getLogger(ProcedimentoDAO.class.getName()).log(Level.SEVERE, null, e);
        }  
        return retorno;
    }
    
        public Procedimento buscarPorCodigo(int codigo){
        Procedimento retorno = new Procedimento();
        String sql = "SELECT * FROM procedimento WHERE codprocedimento=?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()){
                retorno.setCodProcedimento(resultado.getInt("codprocedimento"));
                retorno.setNomeProcedimento(resultado.getString("nomeprocedimento"));
                retorno.setDescProcedimento(resultado.getString("descprocedimento")); 
                retorno.setValorProcedimento(resultado.getDouble("valorprocedimento"));
                retorno.setFlagObesidade(resultado.getBoolean("flagobesidade"));
            }
        } catch(SQLException e) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }
    
}
