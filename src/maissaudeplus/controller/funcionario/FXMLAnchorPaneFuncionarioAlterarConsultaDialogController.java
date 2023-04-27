package maissaudeplus.controller.funcionario;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import maissaudeplus.model.dao.ConsultaDAO;
import maissaudeplus.model.dao.FuncionarioDAO;
import maissaudeplus.model.dao.MedicoDAO;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;
import maissaudeplus.model.domain.Consulta;
import maissaudeplus.model.domain.Funcionario;
import maissaudeplus.model.domain.Medico;
import org.controlsfx.control.SearchableComboBox;

/**
 * FXML Controller class
 *
 */
public class FXMLAnchorPaneFuncionarioAlterarConsultaDialogController implements Initializable {

    @FXML
    private JFXButton buttonConfirmar;

    @FXML
    private JFXButton buttonCancelar;

    @FXML
    private SearchableComboBox<Medico> comboBoxConsultaMedico;

    @FXML
    private SearchableComboBox<Funcionario> comboBoxConsultaFuncionario;

    @FXML
    private JFXDatePicker datePickerConsultaData;

    @FXML
    private SearchableComboBox<LocalTime> comboBoxConsultaHora;

    @FXML
    private SearchableComboBox<Integer> comboBoxConsultaDuracao;

    @FXML
    private SearchableComboBox<String> comboBoxConsultaStatus;
    
    //Lista de horarios disponiveis
    List<LocalTime> listHorarios = new ArrayList();
    List<LocalTime> listHorariosFiltrado;
    
    //Estabelecer conexão com o banco de dados postgres
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    //Lista de DAOs usados neste controller
    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    
    //Atributos para a manipulação do Stage
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Consulta consulta;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadComboBoxMedico();
        loadComboBoxStatus();
        loadComboBoxFuncionario();
        loadComboBoxDuracao();
        loadHorario();
        loadComboBoxHora();
    } 
    
    @FXML
    void handleButtonCancelar() {
        dialogStage.close();
    }

    @FXML
    void handleButtonConfirmar() {
        if(validarEntradaDeDados()){
            consulta.setMedico(comboBoxConsultaMedico.getSelectionModel().getSelectedItem());
            consulta.setFuncionario(comboBoxConsultaFuncionario.getSelectionModel().getSelectedItem());
            consulta.setDataConsulta(datePickerConsultaData.getValue());
            consulta.setHoraConsulta(comboBoxConsultaHora.getValue());
            consulta.setDuracaoConsulta(comboBoxConsultaDuracao.getValue());
            consulta.setStatusConsulta(comboBoxConsultaStatus.getValue());
            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    void handleComboBoxConsultaMedico() {
        //Este if será executado caso a data seja selecionada e um médico esteja selecionado
        if((comboBoxConsultaMedico.getSelectionModel().getSelectedItem()) != null && (datePickerConsultaData.getValue() != null)){
            filtroHorario();
        }
    }

    @FXML
    void handleDatePickerConsultaData() {
        //Este if será executado caso a data seja selecionada e um médico esteja selecionado
        if((comboBoxConsultaMedico.getSelectionModel().getSelectedItem()) != null && (datePickerConsultaData.getValue() != null)){
            filtroHorario();
        }
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
        this.comboBoxConsultaMedico.setValue(consulta.getMedico());
        this.comboBoxConsultaFuncionario.setValue(consulta.getFuncionario());
        this.datePickerConsultaData.setValue(consulta.getDataConsulta());
        this.comboBoxConsultaDuracao.setValue(consulta.getDuracaoConsulta());
        this.comboBoxConsultaStatus.setValue(consulta.getStatusConsulta());
        filtroHorario();
        this.comboBoxConsultaHora.setValue(consulta.getHoraConsulta());
    }
    
    private void loadComboBoxMedico(){
        medicoDAO.setConnection(connection);
        ObservableList<Medico> lista = FXCollections.observableArrayList(medicoDAO.listar());
        comboBoxConsultaMedico.setItems(lista); 
    }
    
    private void loadComboBoxStatus(){
        ObservableList<String> lista = FXCollections.observableArrayList("Cancelada", "Realizada", "Agendada");
        comboBoxConsultaStatus.setItems(lista);
    }
    
    private void loadComboBoxFuncionario(){
        funcionarioDAO.setConnection(connection);
        ObservableList<Funcionario> lista = FXCollections.observableArrayList(funcionarioDAO.listar());
        comboBoxConsultaFuncionario.setItems(lista);
    }
    
    private void loadComboBoxDuracao(){
        ObservableList<Integer> lista = FXCollections.observableArrayList(30, 45, 60);
        comboBoxConsultaDuracao.setItems(lista);
    }
    
    private void loadComboBoxHora(){
        ObservableList<LocalTime> lista = FXCollections.observableArrayList(listHorarios);
        comboBoxConsultaHora.setItems(lista);
    }
    
    // Primeiro Processo de négocio
    private void loadHorario(){
        //Este método cria uma sequência de objetos LocalTime para preencher o comboBox de horas 
        LocalTime abertura = LocalTime.of(8, 0);
        LocalTime fechamento = LocalTime.of(18, 0);

        while(!abertura.equals(fechamento)){
            abertura = abertura.plusMinutes(60);
            listHorarios.add(abertura);
        }
    }
    
    // Primeiro Processo de négocio
    private void loadHorarioFiltro(){
        //Carrega uma segunda lista, com o obejtivo de filtra-lá para preservar a lista original
        listHorariosFiltrado = new ArrayList();
        for (LocalTime hora : listHorarios){
            listHorariosFiltrado.add(hora);
        }
    }
      
    // Primeiro Processo de négocio
    private void filtroHorario(){
        //Método principal de filtragem da lista HorarioFiltrado, tem como obejtivo criar uma lista que se adéque aos horários disponiveis dos médicos 
        consultaDAO.setConnection(connection);
        loadHorarioFiltro();
        
        //Pegando objetos selecionados
        Medico medico = comboBoxConsultaMedico.getSelectionModel().getSelectedItem();
        LocalDate data = datePickerConsultaData.getValue();
        
        //Consulta no Banco
        List<Consulta> lista = consultaDAO.listarPorMedico(medico, data);
        //Instanciando uma lista auxiliar
        List<LocalTime> listaRemover = new ArrayList();
        
        //Percorrendo a lista do banco de dados e preenchendo os valores que serão excluidos, note que a primeira "lista" é do tipo Consulta enquanto esta segunda
        //é do tipo LocalTime
        for (Consulta c : lista){
            listaRemover.add(c.getHoraConsulta());
        }
     
        //Removendo horários onde o médico tem uma consulta
        for (LocalTime hora : listaRemover){
            if (listHorariosFiltrado.contains(hora)){
                listHorariosFiltrado.remove(hora);
            }
        }
        
        //Criando uma obervableList e carregando o comboBox com ela
        ObservableList<LocalTime> listaFiltrada = FXCollections.observableArrayList(listHorariosFiltrado);
        comboBoxConsultaHora.setItems(listaFiltrada);
    }
    
    //Método que valida os dados inseridos nos campos, note que cada campo necessitou de um tratamento diferente.
    public boolean validarEntradaDeDados(){
        //Mensagem de erro declarada como uma String vazia, serve de flag para o método
        String errorMessage = "";
        
        if (comboBoxConsultaMedico.getSelectionModel().getSelectedItem() == null){
            errorMessage += "Médico inválido!\n";
        }
        
        if (comboBoxConsultaFuncionario.getSelectionModel().getSelectedItem() == null){
            errorMessage += "Funcionário inválido!\n";
        }
        
        if (datePickerConsultaData.getValue() == null){
            errorMessage += "Data inválida!\n";
        }  
        
        if (comboBoxConsultaHora.getSelectionModel().getSelectedItem() == null){
            errorMessage += "Horário inválido!\n";
        }
        
        if (comboBoxConsultaDuracao.getSelectionModel().getSelectedItem() == null){
            errorMessage += "Duração inválida!\n";
        }
               
        if (comboBoxConsultaStatus.getSelectionModel().getSelectedItem() == null){
            errorMessage += "Status inválido!\n";
        }
        
        if (errorMessage.length() == 0){
            //Retorno verdadeiro sinalizando que os campos estão corretos
            return true;
        } else {
            //Criação da mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro do paciente");
            alert.setHeaderText("Encontramos campos Inválidos, por favor verifique já.");
            alert.setContentText(errorMessage);
            alert.show();
            //Retorno falso sinalizando que os campos estão errados
            return false;
        }    
    }
}
