package maissaudeplus.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import maissaudeplus.model.domain.Medicamento;

public class MedicamentoDAO {

    private Connection connection;
    
    public Connection getConnection(){
        return connection;
    }
    
    public void setConnection(Connection connection){
        this.connection = connection;
    }
    
    public List<Medicamento> listar(){
        String sql = "SELECT * FROM medicamento";
        List<Medicamento> retorno = new ArrayList<>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()){
                Medicamento medicamento = new Medicamento();
                medicamento.setCodMedicamento(resultado.getInt("codMedicamento"));
                medicamento.setNomeMedicamento(resultado.getString("nomeMedicamento"));
                medicamento.setDescMedicamento(resultado.getString("descMedicamento"));  
                medicamento.setValorMedicamento(resultado.getDouble("valorMedicamento"));
                retorno.add(medicamento);
            }
        } catch(SQLException e){
            Logger.getLogger(MedicamentoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }
    
       public Medicamento buscarPorCodigo(int codigo){
        String sql = "SELECT * FROM medicamento WHERE codmedicamento = ?";
        Medicamento retorno = new Medicamento();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()){
                retorno.setCodMedicamento(resultado.getInt("codMedicamento"));
                retorno.setNomeMedicamento(resultado.getString("nomeMedicamento"));
                retorno.setDescMedicamento(resultado.getString("descMedicamento"));  
                retorno.setValorMedicamento(resultado.getDouble("valorMedicamento"));
            }
        } catch(SQLException e){
            Logger.getLogger(MedicamentoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }
}
