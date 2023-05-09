package maissaudeplus.controller.medico;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import org.controlsfx.control.SearchableComboBox;
import com.jfoenix.controls.JFXButton;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
//import javafimport javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import maissaudeplus.controller.funcionario.FXMLAnchorPaneFuncionarioCadastrarPacienteDialogController;
//x.scene.layout.AnchorPane;
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
import maissaudeplus.model.domain.Medico;
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

    @FXML
    private Label labelTotalConsultasRealizadas;

    @FXML
    private CheckBox checkBoxPaciente;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    private final ConsultaRealizadaDAO consultaRealizadaDAO = new ConsultaRealizadaDAO();
    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    private final ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
    private final MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

    private final ConsultaRealizada consultaRealizada = new ConsultaRealizada();

    int TotalConsultasRealizadas = 0;

    public void initialize(URL url, ResourceBundle rb) { 
        loadComboBoxConsulta();
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
        ObservableList<Paciente> lista = FXCollections.observableArrayList();
            
        if(comboBoxSelecionarConsulta.getSelectionModel().getSelectedItem() != null){
            mostrarTotalConsultasPorMedico();
            Consulta consultaSelecionada = comboBoxSelecionarConsulta.getSelectionModel().getSelectedItem();
            consultaDAO.setConnection(connection);
            lista = FXCollections.observableArrayList(consultaDAO.listarPorPaciente(consultaSelecionada.getCodConsulta()));
            comboBoxSelecionarPaciente.setItems(lista);
        } else {
            lista.clear();
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

    private void mostrarTotalConsultasPorMedico(){
            Consulta consultaSelecionada = comboBoxSelecionarConsulta.getSelectionModel().getSelectedItem();
            consultaDAO.setConnection(connection);
            TotalConsultasRealizadas = consultaDAO.listarQuantidadeConsultasPorDia(consultaSelecionada.getMedico().getCodMedico());
            labelTotalConsultasRealizadas.setText(Integer.toString(TotalConsultasRealizadas));
    }

    private void pacienteEstaPresente(){
        ConsultaDAO consultaDAO = new ConsultaDAO();
        consultaDAO.setConnection(connection);
        boolean selecionado = checkBoxPaciente.isSelected();
        String status = "";
        int consulta = consultaRealizada.getConsulta().getCodConsulta();
        if(selecionado){
            status = "Realizada";
        } else {
            status = "Não compareceu";    
        }
        consultaDAO.alterarStatusConsulta(status,consulta);
    }

    @FXML
    public void handleButtonConfirmar() throws IOException{
        consultaRealizadaDAO.setConnection(connection);

        if(comboBoxSelecionarConsulta.getSelectionModel().getSelectedItem() != null && comboBoxSelecionarMedicamento.getSelectionModel().getSelectedItem() != null && comboBoxSelecionarProcedimento.getSelectionModel().getSelectedItem() != null){
            consultaRealizada.setConsulta(comboBoxSelecionarConsulta.getSelectionModel().getSelectedItem());
            consultaRealizada.setMedicamento(comboBoxSelecionarMedicamento.getSelectionModel().getSelectedItem());
            consultaRealizada.setProcedimento(comboBoxSelecionarProcedimento.getSelectionModel().getSelectedItem());
            pacienteEstaPresente();
              
            if(consultaRealizadaDAO.buscarConsultaRealizada(consultaRealizada)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Consulta já foi registrada!");
                alert.show();
            } else {
                consultaRealizadaDAO.inserir(consultaRealizada);
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText("Consulta realizada com sucesso!");
                alert.show(); 
                // se o teste de obesidade retornar verdadeira é carregado o anchorpane
                Consulta consulta = comboBoxSelecionarConsulta.getSelectionModel().getSelectedItem();
                Paciente paciente = consulta.getPaciente();
                if (testeDeObesidade(paciente)){
                    loadAnchorPaneNotificarPaciente(paciente);
                }
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Consulta não foi registrada devido ao não preenchimento dos campos!");
            alert.show();     
        }

        limparCampos();
    }

    public void limparCampos(){
        loadComboBoxConsulta();
        loadComboBoxPaciente();
        loadComboBoxProcedimento();
        loadComboBoxMedicamento();
    }
    
    //Método responsável pela abertura de uma nova janela dialog.fxml, retorna true caso o botão "Confirmar" seja clicado e false caso o botão "Cancelar" seja clicado
    public boolean loadAnchorPaneNotificarPaciente(Paciente paciente) throws IOException{
        //Instancia de um FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        //Passando a localização do .fxml para o FXMLLoader
        loader.setLocation(FXMLAnchorPaneMedicoNotificarPacienteController
                .class.getResource("/maissaudeplus/view/medico/FXMLAnchorPaneMedicoNotificarPaciente.fxml"));
        //Criando um objeto anchor pane para receber o .fxml
        AnchorPane page = (AnchorPane) loader.load();
        //Criando um novo stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Notificar Paciente");
        //Definindo que o novo stage não pode ser maximilizado
        dialogStage.setResizable(false);
        //Instancia de uma nova cena passando o anchor pane
        Scene scene = new Scene(page);
        //Passando a cena para o stage
        dialogStage.setScene(scene);
        //Carregando um Controller para o dialog
        FXMLAnchorPaneMedicoNotificarPacienteController controller = loader.getController();
        //Passagem de parâmetro do stage atual para o controller poder manipulá-lo
        controller.setDialogStage(dialogStage);
        //Passagem de parâmetro de uma instancia do paciente para o controller poder adicionar os dados a ele.
        controller.setPaciente(paciente);
        //Mostrando a tela
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }
    
    public boolean testeDeObesidade(Paciente paciente){
        int count = 0;
        consultaRealizadaDAO.setConnection(connection);
        LocalDate dataHoje = LocalDate.now();
        LocalDate dataLimite = LocalDate.now().minusYears(1).minusMonths(6);
        List <ConsultaRealizada> listar = consultaRealizadaDAO.listarPorPaciente(paciente);
        for(ConsultaRealizada item : listar){
            if(item.getConsulta().getDataConsulta().isAfter(dataLimite) && item.getProcedimento().isFlagObesidade()){
                count++;
                if (count >= 5) {
                    return true;
                }
            }
        }
        return false; 
    }  
}