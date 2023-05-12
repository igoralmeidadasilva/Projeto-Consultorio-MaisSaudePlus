package maissaudeplus.controller.funcionario;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import maissaudeplus.model.dao.PacienteDAO;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;
import maissaudeplus.model.domain.Paciente;
/**
 * FXML Controller class
 * @author Igor
 * Classe de controle que é responsável pelos processos relacionados a CRUDs do paciente
 * Esta tela apresenta uma listagens dos pacientes em uma tabela, quando o paciente é selecionado ela exibe a direita da tabela as informações
 * do paciente. Ela também é responsável pelos botões "Inserir", "Alterar" e "Remover".
 */
public class FXMLAnchorPaneFuncionarioVisualizarPacienteController implements Initializable {

    @FXML
    private TableView<Paciente> tableViewPacientes;

    @FXML
    private TableColumn<Paciente, Integer> tableColumnPacienteCodigo;

    @FXML
    private TableColumn<Paciente, String> tableColumnPacienteNome;

    @FXML
    private Label labelPacienteTelefone;

    @FXML
    private Label labelPacienteCPF;

    @FXML
    private Label labelPacienteDataNascimento;

    @FXML
    private Label labelPacienteSexo;

    @FXML
    private Label labelPacienteNomePeso;

    @FXML
    private Label labelPacienteAltura;

    @FXML
    private Label labelPacienteEmail;

    @FXML
    private Label labelPacienteNome;

    @FXML
    private JFXButton buttonPacienteAlterar;

    @FXML
    private JFXButton buttonPacienteInserir;

    @FXML
    private JFXButton buttonPacienteRemover;
    
    //ObservableList para preencher a tableview
    private ObservableList<Paciente> listaPacientes;
    
    //Estabelecer conexão com o banco de dados postgres
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    //Lista de DAOs usados neste controller
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Configurando colunas da tabela
        tableColumnPacienteCodigo.setCellValueFactory(new PropertyValueFactory<>("codPaciente"));
        tableColumnPacienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        //Carregando o table view
        loadTableView();
        //Adicionando o Listener do TableView
        listenerTableView();
    }

    //Método para carregar o table view
    private void loadTableView(){
        pacienteDAO.setConnection(connection);
        listaPacientes = FXCollections.observableArrayList(pacienteDAO.listar());
        tableViewPacientes.setItems(listaPacientes);
    }
    
    //Listener para selecionar o objeto do table view em tempo real
    private void listenerTableView(){
        tableViewPacientes.getSelectionModel().selectedItemProperty().addListener(
                (obervable, oldValue, newValue) -> selecionarItemsTableViewPacientes(newValue));
    }
    //Metodo que seleciona as informações e as dispõe nos labels correspondentes
    private void selecionarItemsTableViewPacientes(Paciente paciente){
        if(paciente != null){
            labelPacienteNome.setText(paciente.getNome());
            labelPacienteCPF.setText(paciente.getCpf());
            labelPacienteDataNascimento.setText((paciente.getDataNascimento()));
            labelPacienteSexo.setText(Character.toString(paciente.getSexo()));
            labelPacienteNomePeso.setText(Double.toString(paciente.getPeso()));
            labelPacienteAltura.setText(Double.toString(paciente.getAltura()));
            labelPacienteEmail.setText(paciente.getEmail());
            labelPacienteTelefone.setText(paciente.getTelefone());
        } else {
            labelPacienteNome.setText("");
            labelPacienteCPF.setText("");
            labelPacienteDataNascimento.setText("");
            labelPacienteSexo.setText("");
            labelPacienteNomePeso.setText("");
            labelPacienteAltura.setText("");
            labelPacienteEmail.setText("");
            labelPacienteTelefone.setText("");
        }
    }   
    
    //Método responsável pela inserção no banco de dados de um novo paciente, os dados são inseridos em um dialog
    @FXML
    public void handleButtonInserir() throws IOException {
        //Instancia de um novo paciente vazio
        Paciente paciente = new Paciente();
        //If que só é executado caso a botão "Confirmar" do dialog tiver sido pressionado.
        if(loadAnchorPaneCadastrarPacienteDialog(paciente)){
            //Passando a conexão para a DAO
            pacienteDAO.setConnection(connection);
            //Efetuando a inserção
            pacienteDAO.inserir(paciente);
            //Recarregando a table view com o novo paciente
            loadTableView();
            //Criação de um alerta de confirmação
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso");
            alert.setContentText("Paciente Cadastrado com sucesso!!");
            alert.show();
        } 
    }
     
    //Método responsável pela alteração no banco de dados de um paciente já cadastrado, os dados são disponibilizados em um dialog
    @FXML
    public void handleButtonAlterar() throws IOException {
        //Recebendo o paciente selecionado na table view
        Paciente paciente = tableViewPacientes.getSelectionModel().getSelectedItem();
        //If para verificar se o paciente foi realmente selecionado
        if(paciente != null){
            //If que só é executado caso a botão "Confirmar" do dialog tiver sido pressionado.
            if(loadAnchorPaneAlterarPacienteDialog(paciente)){
                //Passando a conexão para a DAO
                pacienteDAO.setConnection(connection);
                //Efetuando a alteração
                pacienteDAO.alterar(paciente);
                //Recarregando a table view com os dados do paciente atualizados
                loadTableView();
                //Criação de um alerta de confirmação
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sucesso");
                alert.setContentText("Paciente Alterado com sucesso!!");
                alert.show();
            }
        } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setContentText("Selecione um Paciente para ser alterado!!");
                alert.show();          
        }
    }

    //Método responsável pela remoção no banco de dados de um paciente, o paciente só é excluido após a confirmação em um dialog
    @FXML
    public void handleButtonRemover() throws IOException {
        //Recebendo o paciente selecionado na table view
        Paciente paciente = tableViewPacientes.getSelectionModel().getSelectedItem();
        //If para verificar se o paciente foi realmente selecionado
        if (paciente != null){
            if(loadAnchorPaneRemoverPacienteDialog()){
                //Passando a conexão para a DAO
                pacienteDAO.setConnection(connection);
                //Efetuando a exclusõa
                pacienteDAO.remover(paciente);
                //Recarregando a table view sem o paciente
                loadTableView();
            }
        } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setContentText("Escolha um paciente para ser removido!!");
                alert.show();
        }
    }
    
    //Método responsável pela abertura de uma nova janela dialog.fxml, retorna true caso o botão "Confirmar" seja clicado e false caso o botão "Cancelar" seja clicado
    public boolean loadAnchorPaneCadastrarPacienteDialog(Paciente paciente) throws IOException{
        //Instancia de um FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        //Passando a localização do .fxml para o FXMLLoader
        loader.setLocation(FXMLAnchorPaneFuncionarioCadastrarPacienteDialogController
                .class.getResource("/maissaudeplus/view/funcionario/FXMLAnchorPaneFuncionarioCadastrarPacienteDialog.fxml"));
        //Criando um objeto anchor pane para receber o .fxml
        AnchorPane page = (AnchorPane) loader.load();
        //Criando um novo stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastrar Paciente");
        //Definindo que o novo stage não pode ser maximilizado
        dialogStage.setResizable(false);
        //Instancia de uma nova cena passando o anchor pane
        Scene scene = new Scene(page);
        //Passando a cena para o stage
        dialogStage.setScene(scene);
        //Carregando um Controller para o dialog
        FXMLAnchorPaneFuncionarioCadastrarPacienteDialogController controller = loader.getController();
        //Passagem de parâmetro do stage atual para o controller poder manipulá-lo
        controller.setDialogStage(dialogStage);
        //Passagem de parâmetro de uma instancia do paciente para o controller poder adicionar os dados a ele.
        controller.setPaciente(paciente);
        //Mostrando a tela
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }
    
    //Método responsável pela abertura de uma nova janela dialog.fxml, retorna true caso o botão "Confirmar" seja clicado e false caso o botão "Cancelar" seja clicado
    public boolean loadAnchorPaneAlterarPacienteDialog(Paciente paciente) throws IOException{
        //Instancia de um FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        //Passando a localização do .fxml para o FXMLLoader
        loader.setLocation(FXMLAnchorPaneFuncionarioAlterarPacienteDialogController
                .class.getResource("/maissaudeplus/view/funcionario/FXMLAnchorPaneFuncionarioAlterarPacienteDialog.fxml"));
        //Criando um objeto anchor pane para receber o .fxml
        AnchorPane page = (AnchorPane) loader.load();
        //Criando um novo stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Alterar Paciente");
        //Definindo que o novo stage não pode ser maximilizado
        dialogStage.setResizable(false);
        //Instancia de uma nova cena passando o anchor pane
        Scene scene = new Scene(page);
        //Passando a cena para o stage
        dialogStage.setScene(scene);
        //Carregando um Controller para o dialog
        FXMLAnchorPaneFuncionarioAlterarPacienteDialogController controller = loader.getController();
        //Passagem de parâmetro do stage atual para o controller poder manipulá-lo
        controller.setDialogStage(dialogStage);
        //Passagem de parametro de uma instancia do paciente para o controller poder adicionar os dados a ele.
        controller.setPaciente(paciente);
        //Mostrando a tela
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }

    //Método responsável pela abertura de uma nova janela dialog.fxml, retorna true caso o botão "Confirmar" seja clicado e false caso o botão "Cancelar" seja clicado
    public boolean loadAnchorPaneRemoverPacienteDialog() throws IOException{
        //Instancia de um FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        //Passando a localização do .fxml para o FXMLLoader
        loader.setLocation(FXMLAnchorPaneFuncionarioRemoverPacienteDialogController
               .class.getResource("/maissaudeplus/view/funcionario/FXMLAnchorPaneFuncionarioRemoverPacienteDialog.fxml"));
        //Criando um objeto anchor pane para receber o .fxml
        AnchorPane page = (AnchorPane) loader.load();
        //Criando um novo stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Remover Paciente");
        //Definindo que o novo stage não pode ser maximilizado
        dialogStage.setResizable(false);
        //Instancia de uma nova cena passando o anchor pane
        Scene scene = new Scene(page);
        //Passando a cena para o stage
        dialogStage.setScene(scene);
        //Carregando um Controller para o dialog
        FXMLAnchorPaneFuncionarioRemoverPacienteDialogController controller = loader.getController();
        //Passagem de parâmetro do stage atual para o controller poder manipulá-lo
        controller.setDialogStage(dialogStage);
        //Mostrando a tela
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }
}

