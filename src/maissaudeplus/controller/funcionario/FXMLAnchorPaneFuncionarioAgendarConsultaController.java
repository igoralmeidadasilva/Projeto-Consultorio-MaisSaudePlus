package maissaudeplus.controller.funcionario;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import maissaudeplus.model.dao.PacienteDAO;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;
import maissaudeplus.model.domain.Funcionario;
import maissaudeplus.model.domain.Medico;
import maissaudeplus.model.domain.Paciente;
import org.controlsfx.control.SearchableComboBox;

/**
 * FXML Controller class
 * Classe de controle que é responsável pelos processos relacionados a Agendar Consulta
 */
public class FXMLAnchorPaneFuncionarioAgendarConsultaController implements Initializable {

    @FXML
    private TableView<Paciente> tableViewPacientes;

    @FXML
    private TableColumn<Paciente, Integer> tableColumnPacienteCodigo;

    @FXML
    private TableColumn<Paciente, String> tableColumnPacienteNome;
    
    @FXML
    private Label labelPacienteCodigo;

    @FXML
    private Label labelPacienteNome;

    @FXML
    private Label labelPromptStatus;

    @FXML
    private Label labelPromptFuncionario;

    @FXML
    private Label labelPromptMedico;
    
    @FXML
    private SearchableComboBox<Medico> comboBoxConsultaMedico;

    @FXML
    private SearchableComboBox<Funcionario> comboBoxConsultaFuncionario;

    @FXML
    private JFXDatePicker datePickerConsultaData;

    @FXML
    private JFXTimePicker timePickerConsultaHora;

    @FXML
    private SearchableComboBox<String> comboBoxConsultaStatus;

    @FXML
    private JFXTextField textFieldConsultaDuracao;
    
    //ObservableList para preencher a tableview
    private ObservableList<Paciente> listaPacientes;
    
    //Estabelecer conexão com o banco de dados postgres
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    //Lista de DAOs usados neste controller
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
        // TODO
        loadTableView();
        listenerTableView();
        loadComboBoxStatus();
    }  
    
    private void loadTableView(){
        tableColumnPacienteCodigo.setCellValueFactory(new PropertyValueFactory<>("codPaciente"));
        tableColumnPacienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        pacienteDAO.setConnection(connection);
        listaPacientes = FXCollections.observableArrayList(pacienteDAO.listar());
        tableViewPacientes.setItems(listaPacientes);
    }
    
    private void listenerTableView(){
        tableViewPacientes.getSelectionModel().selectedItemProperty().addListener(
                (obervable, oldValue, newValue) -> selecionarItemsTableViewPacientes(newValue));
    }
    
    private void selecionarItemsTableViewPacientes(Paciente paciente){
        if(paciente != null){
            labelPacienteCodigo.setText(String.valueOf(paciente.getCodPaciente()));
            labelPacienteNome.setText(paciente.getNome());
        } else {
            labelPacienteCodigo.setText("");
            labelPacienteCodigo.setText("");
        }
    }
    
    private void loadComboBoxStatus(){
        ObservableList<String> lista = FXCollections.observableArrayList("Cancelada", "Realizada", "Agendada");
        comboBoxConsultaStatus.setItems(lista);
    }
    
    private void loadComboBoxMedico(){
        ObservableList<Medico> lista = FXCollections.observableArrayList();
        comboBoxConsultaMedico.setItems(lista);
    }
    
    private void loadComboBoxFuncionario(){
        ObservableList<Funcionario> lista = FXCollections.observableArrayList();
        comboBoxConsultaFuncionario.setItems(lista);
    }
    
    @FXML
    private void handleComboBoxStatus(){
        if(comboBoxConsultaStatus.getValue() != null){
            labelPromptStatus.setText("");
        }
    }
    
    @FXML
    private void handleComboBoxMedico(){
        if(comboBoxConsultaMedico.getSelectionModel().getSelectedItem() != null){
            labelPromptMedico.setText("");
        }
    }
    
    @FXML
    private void handleComboBoxFuncionario(){
        if(comboBoxConsultaFuncionario.getSelectionModel().getSelectedItem() != null){
            labelPromptFuncionario.setText("");
        }
    }
}
