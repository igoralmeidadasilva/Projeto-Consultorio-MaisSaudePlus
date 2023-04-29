package maissaudeplus.controller.medico;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import org.controlsfx.control.SearchableComboBox;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import maissaudeplus.model.dao.ConsultaDAO;
import maissaudeplus.model.dao.ConsultaRealizadaDAO;
import maissaudeplus.model.dao.MedicamentoDAO;
import maissaudeplus.model.dao.PacienteDAO;
import maissaudeplus.model.dao.ProcedimentoDAO;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;
import maissaudeplus.model.domain.Consulta;
import maissaudeplus.model.domain.ConsultaRealizada;
import maissaudeplus.model.domain.Medicamento;
import maissaudeplus.model.domain.Paciente;
import maissaudeplus.model.domain.Procedimento;

public class FXMLAnchorPaneMedicoRealizarConsultaController implements Initializable{

    @FXML
    private SearchableComboBox<Consulta> comboBoxSelecionarConsulta;

    @FXML
    private SearchableComboBox<Paciente> comboBoxSelecionarPaciente;

    @FXML
    private SearchableComboBox<Procedimento> comboBoxSelecionarProcedimento;

    @FXML
    private SearchableComboBox<Medicamento> comboBoxSelecionarMedicamento;

    @FXML
    private JFXButton buttonConfirmar;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    private final ConsultaRealizadaDAO consultaRealizadaDAO = new ConsultaRealizadaDAO();
    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    private final ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
    private final MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

    private final ConsultaRealizada consultaRealizada = new ConsultaRealizada();

    public void initialize(URL url, ResourceBundle rb) {    
        loadComboBoxConsulta();
        //loadComboBoxPaciente();
        loadComboBoxProcedimento();
        loadComboBoxMedicamento();
    }  

    private void loadComboBoxConsulta() {
        consultaDAO.setConnection(connection);
        ObservableList<Consulta> lista = FXCollections.observableArrayList(consultaDAO.listarConsultasAgendadas());
        comboBoxSelecionarConsulta.setItems(lista);
    }

    @FXML
    private void loadComboBoxPaciente() {
        if(comboBoxSelecionarConsulta.getSelectionModel().getSelectedItem() != null){
            Consulta consultaSelecionada = comboBoxSelecionarConsulta.getSelectionModel().getSelectedItem();
            consultaDAO.setConnection(connection);
            ObservableList<Paciente> lista = FXCollections.observableArrayList(consultaDAO.listarPorPaciente(consultaSelecionada.getCodConsulta()));
            comboBoxSelecionarPaciente.setItems(lista);
        }
    }

    private void loadComboBoxProcedimento() {
        procedimentoDAO.setConnection(connection);
        ObservableList<Procedimento> lista = FXCollections.observableArrayList(procedimentoDAO.listar());
        comboBoxSelecionarProcedimento.setItems(lista);
    }

    private void loadComboBoxMedicamento() {
        medicamentoDAO.setConnection(connection);
        ObservableList<Medicamento> lista = FXCollections.observableArrayList(medicamentoDAO.listar());
        comboBoxSelecionarMedicamento.setItems(lista);
    }

    @FXML
    public void handleButtonConfirmar() throws IOException{
        consultaRealizadaDAO.setConnection(connection);

        if(comboBoxSelecionarConsulta.getSelectionModel().getSelectedItem() != null && comboBoxSelecionarMedicamento.getSelectionModel().getSelectedItem() != null && comboBoxSelecionarProcedimento.getSelectionModel().getSelectedItem() != null){
            consultaRealizada.setConsulta(comboBoxSelecionarConsulta.getSelectionModel().getSelectedItem());
            consultaRealizada.setMedicamento(comboBoxSelecionarMedicamento.getSelectionModel().getSelectedItem());
            consultaRealizada.setProcedimento(comboBoxSelecionarProcedimento.getSelectionModel().getSelectedItem());

            if(consultaRealizadaDAO.buscarConsultaRealizada(consultaRealizada)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Consulta já foi registrada!");
                alert.show();
            } else {
                consultaRealizadaDAO.inserir(consultaRealizada);
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText("Consulta realizada com sucesso!");
                alert.show();
            } 
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Consulta não foi registrada devido ao não preenchimento dos campos!");
            alert.show();     
        }
    }
}         
