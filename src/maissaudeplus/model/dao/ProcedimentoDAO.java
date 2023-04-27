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
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }    
        return retorno;
    }
    
}
