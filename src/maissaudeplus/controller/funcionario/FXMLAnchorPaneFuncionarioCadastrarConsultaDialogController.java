package maissaudeplus.controller.funcionario;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import maissaudeplus.model.dao.ConsultaDAO;
import maissaudeplus.model.dao.FuncionarioDAO;
import maissaudeplus.model.dao.MedicoDAO;
import maissaudeplus.model.dao.PacienteDAO;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;
import maissaudeplus.model.domain.Consulta;
import maissaudeplus.model.domain.Funcionario;
import maissaudeplus.model.domain.Medico;
import maissaudeplus.model.domain.Paciente;
import org.controlsfx.control.SearchableComboBox;

/**
 * FXML Controller class
 * Classe de controle que é responsável pelos processos relacionados a Agendar Consulta
 */
public class FXMLAnchorPaneFuncionarioCadastrarConsultaDialogController implements Initializable {

    @FXML
    private JFXButton buttonConfirmar;

    @FXML
    private JFXButton buttonCancelar;
    
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
    private Label labelPromptDuracao;
    
    @FXML
    private Label labelPromptHora;
    
    @FXML
    private SearchableComboBox<Medico> comboBoxConsultaMedico;

    @FXML
    private SearchableComboBox<Funcionario> comboBoxConsultaFuncionario;
    
    @FXML
    private SearchableComboBox<Integer> comboBoxConsultaDuracao;

    @FXML
    private JFXDatePicker datePickerConsultaData;

    @FXML
    private SearchableComboBox<LocalTime> comboBoxConsultaHora;

    @FXML
    private SearchableComboBox<String> comboBoxConsultaStatus;
    
    //ObservableList para preencher a tableview
    private ObservableList<Paciente> listaPacientes;
    
    //Estabelecer conexão com o banco de dados postgres
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    //Lista de DAOs usados neste controller
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    
    //Atributos para a manipulação do Stage
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Consulta consulta;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
        loadTableView();
        listenerTableView();
        loadComboBoxStatus();
        loadComboBoxMedico();
        loadComboBoxFuncionario();
        loadComboBoxDuracao();
        loadComboBoxHora();
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
        medicoDAO.setConnection(connection);
        ObservableList<Medico> lista = FXCollections.observableArrayList(medicoDAO.listar());
        comboBoxConsultaMedico.setItems(lista);
    }
    
    private void loadComboBoxFuncionario(){
        funcionarioDAO.setConnection(connection);
        ObservableList<Funcionario> lista = FXCollections.observableArrayList(funcionarioDAO.listar());
        comboBoxConsultaFuncionario.setItems(lista);
    }
    
    private void loadComboBoxDuracao(){
        ObservableList<Integer> lista = FXCollections.observableArrayList(15, 30);
        comboBoxConsultaDuracao.setItems(lista);
    }
    
    private void loadComboBoxHora(){
        ObservableList<LocalTime> lista = FXCollections.observableArrayList(loadHorario());
        comboBoxConsultaHora.setItems(lista);
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
    
    @FXML
    private void handleComboBoxDuracao(){
        if(comboBoxConsultaDuracao.getValue() != null){
            labelPromptDuracao.setText("");
        }
    }
    
    @FXML 
    private void handleComboBoxHora(){
        if(comboBoxConsultaHora.getValue() != null){
            labelPromptHora.setText("");
        }
    }
    
    //Método de manipulação do botão "Confirmar"
    @FXML
    private void handleButtonConfirmar() {
        //If que só é executado caso todos os campos estejam no formato correto
        if(validarEntradaDeDados()){
            //Colocando os dados fornecidos na instância da consulta
            consulta.setPaciente(tableViewPacientes.getSelectionModel().getSelectedItem());
            consulta.setMedico(comboBoxConsultaMedico.getSelectionModel().getSelectedItem());
            consulta.setFuncionario(comboBoxConsultaFuncionario.getSelectionModel().getSelectedItem());
            consulta.setDataConsulta(datePickerConsultaData.getValue());
            //consulta.setHoraConsulta(comboBoxConsultaHora.getValue());
            consulta.setHoraConsulta(LocalTime.now());
            consulta.setDuracaoConsulta(comboBoxConsultaDuracao.getValue());
            consulta.setStatusConsulta(comboBoxConsultaStatus.getValue());
            //Retorna verdadeiro sinalizando que o botão "Confirmar" foi pressionado
            buttonConfirmarClicked = true;
            //Fechando o dialog
            dialogStage.close();
        }
    }
    
    //Método de manipulação do botão "Cancelar"
    @FXML
    private void handleButtonCancelar() {
        //Fechando o dialog, note que não a necessidade de informar que este botão foi pressionado já que a flag "buttonConfirmarClicked" foi declarada como falsa
        dialogStage.close();
    }
    
    //Getter e Setters dos atributos de manipulação do stage
    public Stage getDialogStage() {
        return dialogStage;
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    private boolean validarEntradaDeDados(){
        String errorMessage = "";  
        return true;
    }
    
    //Processo de négocio
    private List<LocalTime> loadHorario(){
        List<LocalTime> horarios = new ArrayList();
        
        LocalTime abertura = LocalTime.of(8, 0);
        LocalTime fechamento = LocalTime.of(18, 0);

        horarios.add(abertura);
        while(!abertura.equals(fechamento)){
            abertura = abertura.plusMinutes(30);
            horarios.add(abertura);
        }
        return horarios;
    }
}
