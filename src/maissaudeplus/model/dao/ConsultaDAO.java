package maissaudeplus.model.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import maissaudeplus.model.domain.Consulta;
import maissaudeplus.model.domain.Funcionario;
import maissaudeplus.model.domain.Medico;
import maissaudeplus.model.domain.Paciente;

public class ConsultaDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<Consulta> listar() {
        String sql = "SELECT * FROM Consulta WHERE statusconsulta = 'Agendada' ORDER BY codconsulta ASC";
        List<Consulta> retorno = new ArrayList<Consulta>();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarioDAO.setConnection(connection);
        MedicoDAO medicoDAO = new MedicoDAO();
        medicoDAO.setConnection(connection);
        PacienteDAO pacienteDAO = new PacienteDAO();
        pacienteDAO.setConnection(connection);
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Consulta consulta = new Consulta();
                Paciente paciente = new Paciente();
                Medico medico = new Medico();
                Funcionario funcionario = new Funcionario();

                // Obtendo os atributos "básicos"
                consulta.setCodConsulta(resultado.getInt("codconsulta"));
                consulta.setDataConsulta(resultado.getDate("dataconsulta"));
                consulta.setHoraConsulta(resultado.getTime("horaconsulta"));
                consulta.setDuracaoConsulta(resultado.getInt("duracaoconsulta"));
                consulta.setStatusConsulta(resultado.getString("statusconsulta"));

                // Obtendo os códigos do médico, funcionário e paciente
                medico.setCodMedico(resultado.getInt("medico_codmedico"));
                funcionario.setCodFuncionario(resultado.getInt("funcionario_codfuncionario"));
                paciente.setCodPaciente(resultado.getInt("paciente_codpaciente"));

                // Fazendo a conexão com outros DAOs para buscar os objetos medico, funcionario
                // e paciente
                medico = medicoDAO.buscar(medico);
                funcionario = funcionarioDAO.buscar(funcionario);
                paciente = pacienteDAO.buscar(paciente);

                // Terminando de preencher a Consulta
                consulta.setMedico(medico);
                consulta.setPaciente(paciente);
                consulta.setFuncionario(funcionario);

                // Adicionando a Lista de retorno
                retorno.add(consulta);
            }
        } catch (SQLException e) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }

    public List<Consulta> listarConsultasAgendadas() {
        String sql = "SELECT * FROM Consulta WHERE statusconsulta = 'Agendada' ORDER BY codconsulta ASC ";
        List<Consulta> retorno = new ArrayList<Consulta>();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarioDAO.setConnection(connection);
        MedicoDAO medicoDAO = new MedicoDAO();
        medicoDAO.setConnection(connection);
        PacienteDAO pacienteDAO = new PacienteDAO();
        pacienteDAO.setConnection(connection);
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Consulta consulta = new Consulta();
                Paciente paciente = new Paciente();
                Medico medico = new Medico();
                Funcionario funcionario = new Funcionario();

                consulta.setCodConsulta(resultado.getInt("codconsulta"));
                consulta.setDataConsulta(resultado.getDate("dataconsulta"));
                consulta.setHoraConsulta(resultado.getTime("horaconsulta"));
                consulta.setDuracaoConsulta(resultado.getInt("duracaoconsulta"));
                consulta.setStatusConsulta(resultado.getString("statusconsulta"));

                medico.setCodMedico(resultado.getInt("medico_codmedico"));
                funcionario.setCodFuncionario(resultado.getInt("funcionario_codfuncionario"));
                paciente.setCodPaciente(resultado.getInt("paciente_codpaciente"));

                medico = medicoDAO.buscar(medico);
                funcionario = funcionarioDAO.buscar(funcionario);
                paciente = pacienteDAO.buscar(paciente);

                consulta.setMedico(medico);
                consulta.setPaciente(paciente);
                consulta.setFuncionario(funcionario);

                retorno.add(consulta);
            }
        } catch (SQLException e) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }

    public Paciente listarPorPaciente(int consultaSelecionada) {
        String sql = "SELECT nomepaciente FROM paciente p, consulta co WHERE p.codpaciente = co.paciente_codpaciente AND co.codconsulta = ?";
        Paciente retorno = new Paciente();
        PacienteDAO pacienteDAO = new PacienteDAO();
        pacienteDAO.setConnection(connection);
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, consultaSelecionada);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                retorno.setNome(resultado.getString("nomepaciente"));
            }
        } catch (SQLException e) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }

    public void inserir(Consulta consulta) {
        String sql = "INSERT INTO Consulta (medico_codmedico, funcionario_codfuncionario, paciente_codpaciente, dataconsulta, horaconsulta, duracaoconsulta, statusconsulta) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, consulta.getMedico().getCodMedico());
            stmt.setInt(2, consulta.getFuncionario().getCodFuncionario());
            stmt.setInt(3, consulta.getPaciente().getCodPaciente());
            stmt.setDate(4, consulta.getDataConsultaToDb());
            stmt.setTime(5, consulta.getHoraConsultaToDb());
            stmt.setInt(6, consulta.getDuracaoConsulta());
            stmt.setString(7, consulta.getStatusConsulta());
            stmt.execute();
        } catch (SQLException e) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public boolean remover(Consulta consulta) {
        String sql = "DELETE FROM consulta WHERE codconsulta = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, consulta.getCodConsulta());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean alterar(Consulta consulta) {
        String sql = "UPDATE Consulta "
                + " SET medico_codmedico=? , funcionario_codfuncionario=?, dataconsulta=?, horaconsulta=?, duracaoconsulta=?, statusconsulta=? "
                + " WHERE codconsulta = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, consulta.getMedico().getCodMedico());
            stmt.setInt(2, consulta.getFuncionario().getCodFuncionario());
            stmt.setDate(3, consulta.getDataConsultaToDb());
            stmt.setTime(4, consulta.getHoraConsultaToDb());
            stmt.setInt(5, consulta.getDuracaoConsulta());
            stmt.setString(6, consulta.getStatusConsulta());
            stmt.setInt(7, consulta.getCodConsulta());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public boolean alterarStatusConsulta(String status, int consulta) {
        String sql = "UPDATE Consulta "
                + " SET statusconsulta=? "
                + " WHERE codconsulta = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, consulta);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public List<Consulta> listarPorMedico(Medico m, LocalDate data) {
        List<Consulta> retorno = new ArrayList();
        String sql = "SELECT * FROM Consulta WHERE medico_codmedico = ? AND dataconsulta = ?";
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarioDAO.setConnection(connection);
        MedicoDAO medicoDAO = new MedicoDAO();
        medicoDAO.setConnection(connection);
        PacienteDAO pacienteDAO = new PacienteDAO();
        pacienteDAO.setConnection(connection);
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, m.getCodMedico());
            stmt.setDate(2, Date.valueOf(data));
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Consulta consulta = new Consulta();
                Paciente paciente = new Paciente();
                Medico medico = new Medico();
                Funcionario funcionario = new Funcionario();

                // Obtendo os atributos "básicos"
                consulta.setCodConsulta(resultado.getInt("codconsulta"));
                consulta.setDataConsulta(resultado.getDate("dataconsulta"));
                consulta.setHoraConsulta(resultado.getTime("horaconsulta"));
                consulta.setDuracaoConsulta(resultado.getInt("duracaoconsulta"));
                consulta.setStatusConsulta(resultado.getString("statusconsulta"));

                // Obtendo os códigos do médico, funcionário e paciente
                medico.setCodMedico(resultado.getInt("medico_codmedico"));
                funcionario.setCodFuncionario(resultado.getInt("funcionario_codfuncionario"));
                paciente.setCodPaciente(resultado.getInt("paciente_codpaciente"));

                // Fazendo a conexão com outros DAOs para buscar os objetos medico, funcionario
                // e paciente
                medico = medicoDAO.buscar(medico);
                funcionario = funcionarioDAO.buscar(funcionario);
                paciente = pacienteDAO.buscar(paciente);

                // Terminando de preencher a Consulta
                consulta.setMedico(medico);
                consulta.setPaciente(paciente);
                consulta.setFuncionario(funcionario);

                // Adicionando a Lista de retorno
                retorno.add(consulta);
            }
        } catch (SQLException e) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }

    public Map<Integer, Integer> listarQuantidadeConsultasPorMes(int ano) {
        String sql = "SELECT COUNT(codconsulta) AS qtde, EXTRACT (MONTH FROM dataconsulta) AS mes FROM consulta "
                + "WHERE EXTRACT (YEAR FROM dataconsulta)=? GROUP BY mes ORDER BY mes;";
        Map<Integer, Integer> retorno = new HashMap();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, ano);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                retorno.put(resultado.getInt("mes"), resultado.getInt("qtde"));
            }
        } catch (SQLException e) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }

    public int listarQuantidadeConsultasPorDia(int medicoSelecionado){
        String sql = "SELECT CAST(COUNT(co.codconsulta) AS INT) AS total FROM consulta co, consultarealizada cr, medico me WHERE co.codconsulta = cr.consulta_codconsulta AND co.medico_codmedico = ? GROUP BY me.nomemedico";
        int retorno = 0;
        MedicoDAO medicoDAO = new MedicoDAO();
        medicoDAO.setConnection(connection);
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, medicoSelecionado);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                retorno = resultado.getInt("total");
            }
        } catch (SQLException e) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }
    
    public Consulta buscarPorCodigo (int codigo){
        String sql = "SELECT * FROM Consulta WHERE codconsulta = ?";
        Consulta retorno = new Consulta();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarioDAO.setConnection(connection);
        MedicoDAO medicoDAO = new MedicoDAO();
        medicoDAO.setConnection(connection);
        PacienteDAO pacienteDAO = new PacienteDAO();
        pacienteDAO.setConnection(connection);
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Paciente paciente = new Paciente();
                Medico medico = new Medico();
                Funcionario funcionario = new Funcionario();

                // Obtendo os atributos "básicos"
                retorno.setCodConsulta(resultado.getInt("codconsulta"));
                retorno.setDataConsulta(resultado.getDate("dataconsulta"));
                retorno.setHoraConsulta(resultado.getTime("horaconsulta"));
                retorno.setDuracaoConsulta(resultado.getInt("duracaoconsulta"));
                retorno.setStatusConsulta(resultado.getString("statusconsulta"));

                // Obtendo os códigos do médico, funcionário e paciente
                medico.setCodMedico(resultado.getInt("medico_codmedico"));
                funcionario.setCodFuncionario(resultado.getInt("funcionario_codfuncionario"));
                paciente.setCodPaciente(resultado.getInt("paciente_codpaciente"));

                // Fazendo a conexão com outros DAOs para buscar os objetos medico, funcionario
                // e paciente
                medico = medicoDAO.buscar(medico);
                funcionario = funcionarioDAO.buscar(funcionario);
                paciente = pacienteDAO.buscar(paciente);

                // Terminando de preencher a Consulta
                retorno.setMedico(medico);
                retorno.setPaciente(paciente);
                retorno.setFuncionario(funcionario);
            }

        } catch (SQLException e){
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, e);
        }       
        return retorno;
    }
}
