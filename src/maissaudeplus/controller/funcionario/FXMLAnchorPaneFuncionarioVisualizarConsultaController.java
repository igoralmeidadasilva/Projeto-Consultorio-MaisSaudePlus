package maissaudeplus.controller.funcionario;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import maissaudeplus.model.dao.ConsultaDAO;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;
import maissaudeplus.model.domain.Consulta;
/**
 * FXML Controller class
 *
 */
public class FXMLAnchorPaneFuncionarioVisualizarConsultaController implements Initializable {
 
    @FXML
    private TableView<Consulta> tableViewConsultas;

    @FXML
    private TableColumn<Consulta, Integer> tableColumnConsultaCodigo;

    @FXML
    private TableColumn<Consulta, String> tableColumnConsultaPacienteNome;

    @FXML
    private TableColumn<Consulta, LocalDate> tableColumnConsultaData;

    @FXML
    private Label labelConsultaPacienteNome;

    @FXML
    private Label labelConsultaMedicoNome;

    @FXML
    private Label labelConsultaFuncionarioNome;

    @FXML
    private Label labelConsultaDuracao;

    @FXML
    private Label labelConsultaData;

    @FXML
    private Label labelConsultaHora;

    @FXML
    private Label labelConsultaStatus;

    @FXML
    private JFXButton buttonPacienteAlterar;

    @FXML
    private JFXButton buttonPacienteInserir;

    @FXML
    private JFXButton buttonPacienteRemover;
    
    //ObservableList para preencher a tableview
    private ObservableList<Consulta> listaConsultas;
    
    //Estabelecer conexão com o banco de dados postgres
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    //Lista de DAOs usados neste controller
    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Configurando colunas da tabela
        tableColumnConsultaCodigo.setCellValueFactory(new PropertyValueFactory<>("codConsulta"));
        tableColumnConsultaPacienteNome.setCellValueFactory(new PropertyValueFactory<>("paciente"));
        tableColumnConsultaData.setCellValueFactory(new PropertyValueFactory<>("dataConsulta"));
        //Carregando o table view
        loadTableView();
        //Adicionando o Listener do TableView
        listenerTableView();
    } 
    
    //Método para carregar o table view
    private void loadTableView(){
        consultaDAO.setConnection(connection);
        listaConsultas = FXCollections.observableArrayList(consultaDAO.listar());
        tableViewConsultas.setItems(listaConsultas);  
    }
    
    //Listener para selecionar o objeto do table view em tempo real
    private void listenerTableView(){
        tableViewConsultas.getSelectionModel().selectedItemProperty().addListener(
                (obervable, oldValue, newValue) -> selecionarItemsTableViewConsultas(newValue));
    }
    
    //Metodo que seleciona as informações e as dispõe nos labels correspondentes
    private void selecionarItemsTableViewConsultas(Consulta consulta){
        if(consulta != null){
            labelConsultaPacienteNome.setText(consulta.getPaciente().getNome());
            labelConsultaMedicoNome.setText(consulta.getMedico().getNome());
            labelConsultaFuncionarioNome.setText(consulta.getFuncionario().getNome());
            labelConsultaDuracao.setText(String.valueOf(consulta.getDuracaoConsulta()));
            labelConsultaData.setText(consulta.getDataConsulta().toString());
            labelConsultaHora.setText(consulta.getHoraConsulta().toString());
            labelConsultaStatus.setText(consulta.getStatusConsulta());
        } else {
            labelConsultaPacienteNome.setText("");
            labelConsultaMedicoNome.setText("");
            labelConsultaFuncionarioNome.setText("");
            labelConsultaDuracao.setText("");
            labelConsultaData.setText("");
            labelConsultaHora.setText("");
            labelConsultaStatus.setText("");
        }
    }
  
    @FXML
    void handleButtonInserir() throws IOException {
        //Instancia de um novo paciente vazio
        Consulta consulta = new Consulta();
        //If que só é executado caso a botão "Confirmar" do dialog tiver sido pressionado.
        if(loadAnchorPaneCadastrarConsultaDialog(consulta)){
            //Passando a conexão para a DAO
            consultaDAO.setConnection(connection);
            //Efetuando a inserção
            consultaDAO.inserir(consulta);
            //Recarregando a table view com o novo paciente
            loadTableView();
            //Criação de um alerta de confirmação
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso");
            alert.setContentText("Consulta Agendada sucesso!!");
            alert.show();
        }
    }
    
    @FXML
    void handleButtonAlterar() {

    }

    @FXML
    void handleButtonRemover() {

    }
    
    //Método responsável pela abertura de uma nova janela dialog.fxml, retorna true caso o botão "Confirmar" seja clicado e false caso o botão "Cancelar" seja clicado
    public boolean loadAnchorPaneCadastrarConsultaDialog(Consulta consulta) throws IOException{
        //Instancia de um FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        //Passando a localização do .fxml para o FXMLLoader
        loader.setLocation(FXMLAnchorPaneFuncionarioCadastrarPacienteDialogController
                .class.getResource("/maissaudeplus/view/funcionario/FXMLAnchorPaneFuncionarioCadastrarConsultaDialog.fxml"));
        //Criando um objeto anchor pane para receber o .fxml
        AnchorPane page = (AnchorPane) loader.load();
        //Criando um novo stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Agendar Consulta");
        //Definindo que o novo stage não pode ser maximilizado
        dialogStage.setResizable(false);
        //Instancia de uma nova cena passando o anchor pane
        Scene scene = new Scene(page);
        //Passando a cena para o stage
        dialogStage.setScene(scene);
        //Carregando um Controller para o dialog
        FXMLAnchorPaneFuncionarioCadastrarConsultaDialogController controller = loader.getController();
        //Passagem de parâmetro do stage atual para o controller poder manipulá-lo
        controller.setDialogStage(dialogStage);
        //Passagem de parâmetro de uma instancia do paciente para o controller poder adicionar os dados a ele.
        controller.setConsulta(consulta);
        //Mostrando a tela
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }     
}
