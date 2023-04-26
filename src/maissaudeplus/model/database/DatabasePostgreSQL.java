package maissaudeplus.model.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabasePostgreSQL implements Database {
    private Connection connection;
    private String url = "";
    private String login = "";
    private String senha = "";

    @Override
    public Connection conectar() {
        try {
            carregarPropriedades();
        } catch (IOException ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, login, senha);
            return this.connection;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void desconectar(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void carregarPropriedades() throws FileNotFoundException, IOException{
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src\\maissaudeplus\\propriedades\\conf.properties");
        prop.load(fis);
        
        url = prop.getProperty("db.postgres.url");
        login = prop.getProperty("db.postgres.login");
        senha = prop.getProperty("db.postgres.password");
    }
}
