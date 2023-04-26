package maissaudeplus.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import maissaudeplus.model.domain.Funcionario;

public class FuncionarioDAO {
    private Connection connection;
    
    public Connection getConnection(){
        return connection;
    }
  
    public void setConnection(Connection connection){
        this.connection = connection;
    }
    
    public List<Funcionario> listar(){
        String sql = "SELECT * FROM Funcionario";
        List<Funcionario> retorno = new ArrayList();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setCodFuncionario(resultado.getInt("codfuncionario"));
                funcionario.setNome(resultado.getString("nomefuncionario"));
                funcionario.setDataAdmissao(resultado.getDate("dataadmissao"));
                funcionario.setCpf(resultado.getString("cpffuncionario"));
                funcionario.setEmail(resultado.getString("emailfuncionario"));
                funcionario.setTelefone(resultado.getString("telefone"));
                retorno.add(funcionario);
            }
        } catch (SQLException e){
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }
    
    public Funcionario buscar(Funcionario funcionario){
        Funcionario retorno = new Funcionario();
        String sql = "SELECT * FROM Funcionario WHERE codFuncionario=?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcionario.getCodFuncionario());
            ResultSet resultado = stmt.executeQuery();
            if(resultado.next()){
                retorno.setCodFuncionario(resultado.getInt("codFuncionario"));
                retorno.setNome(resultado.getString("nomefuncionario"));
                retorno.setDataAdmissao(resultado.getDate("dataadmissao"));
                retorno.setCpf(resultado.getString("cpffuncionario"));
                retorno.setEmail(resultado.getString("emailfuncionario"));
                retorno.setTelefone(resultado.getString("telefone"));
            }
        }catch(SQLException e){
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }
}
