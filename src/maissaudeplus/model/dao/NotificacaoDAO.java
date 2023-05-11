// Classe onde tem os métodos para inserção de uma nova notificação no banco
package maissaudeplus.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import maissaudeplus.model.domain.Notificacao;


public class NotificacaoDAO {
    // cria um objeto do tipo connection
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    //Contém o sql que faz a inserção no banco
    public void inserir(Notificacao notificacao) {
        String sql = "INSERT INTO gerarnotificacao (paciente_codpaciente, datanotificacao) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, notificacao.getPaciente().getCodPaciente());
            stmt.setDate(2, Date.valueOf(notificacao.getDataNotificacao()));
            stmt.execute();
        } catch (SQLException e) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
