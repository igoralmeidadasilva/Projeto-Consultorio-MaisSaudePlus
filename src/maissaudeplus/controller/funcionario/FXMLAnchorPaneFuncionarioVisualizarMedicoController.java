package maissaudeplus.controller.funcionario;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import maissaudeplus.model.dao.MedicoDAO;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;
import maissaudeplus.model.domain.Medico;

public class FXMLAnchorPaneFuncionarioVisualizarMedicoController implements Initializable {

    @FXML
    private TableView<Medico> tableViewMedicos;

    @FXML
    private TableColumn<Medico, Integer> tableColumnMedicoCodigo;

    @FXML
    private TableColumn<Medico, String> tableColumnMedicoNome;

    @FXML
    private Label labelMedicoCPF;

    @FXML
    private Label labelMedicoEmail;

    @FXML
    private Label labelMedicoTelefone;

    @FXML
    private Label labelMedicoCRM;

    @FXML
    private Label labelMedicoDataAdmissao;

    @FXML
    private Label labelMedicoNome;

    @FXML
    private JFXButton buttonMedicoAlterar;

    @FXML
    private JFXButton buttonMedicoInserir;

    @FXML
    private JFXButton buttonMedicoRemover;

    //ObservableList para preencher a tableview
    private ObservableList<Medico> listaMedico;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    private final MedicoDAO medicoDAO = new MedicoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableColumnMedicoCodigo.setCellValueFactory(new PropertyValueFactory<>("codMedico"));
        tableColumnMedicoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        loadTableView();
        listenerTableView();
    }

    private void loadTableView() {
        medicoDAO.setConnection(connection);
        listaMedico = FXCollections.observableArrayList(medicoDAO.listar());
        tableViewMedicos.setItems(listaMedico);
    }

    private void listenerTableView() {
        tableViewMedicos.getSelectionModel().selectedItemProperty().addListener(
                (obervable, oldValue, newValue) -> selecionarItemsTableViewMedicos(newValue));
    }

    private void selecionarItemsTableViewMedicos(Medico medico) {
        if (medico != null) {
            labelMedicoNome.setText(medico.getNome());
            labelMedicoCPF.setText(medico.getCpf());
            labelMedicoCRM.setText(medico.getCrmMedico());
            labelMedicoDataAdmissao.setText((medico.getDataAdmissao().toString()));
            labelMedicoEmail.setText(medico.getEmail());
            labelMedicoTelefone.setText(medico.getTelefone());
        } else {
            labelMedicoNome.setText("");
            labelMedicoCPF.setText("");
            labelMedicoCRM.setText("");
            labelMedicoDataAdmissao.setText((""));
            labelMedicoEmail.setText("");
            labelMedicoTelefone.setText("");
        }
    }

    @FXML
    public void handleButtonInserir() throws SQLException, IOException {
        Medico medico = new Medico();

        if (loadAnchorPaneCadastrarMedicoDialog(medico)) {
            medicoDAO.setConnection(connection);
            medicoDAO.inserir(medico);
            loadTableView();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso");
            alert.setContentText("Médico Cadastrado com sucesso!!");
            alert.show();
        }
    }
    
        public boolean loadAnchorPaneCadastrarMedicoDialog(Medico medico) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneFuncionarioCadastrarMedicoDialogController.class.getResource("/maissaudeplus/view/funcionario/FXMLAnchorPaneFuncionarioCadastrarMedicoDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastrar Médico");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLAnchorPaneFuncionarioCadastrarMedicoDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setMedico(medico);
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }

    @FXML
    public void handleButtonAlterar() throws SQLException, IOException {
        Medico medico = tableViewMedicos.getSelectionModel().getSelectedItem();
        if (medico != null) {
            if (loadAnchorPaneAlterarMedicoDialog(medico)) {
                medicoDAO.setConnection(connection);
                medicoDAO.alterar(medico);
                loadTableView();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sucesso");
                alert.setContentText("Médico Alterado com sucesso!!");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selecione um médico para ser alterado!!");
            alert.show();
        }

    }

    public boolean loadAnchorPaneAlterarMedicoDialog(Medico medico) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneFuncionarioAlterarMedicoDialogController.class.getResource("/maissaudeplus/view/funcionario/FXMLAnchorPaneFuncionarioAlterarMedicoDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Alterar Médico");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLAnchorPaneFuncionarioAlterarMedicoDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setMedico(medico);
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }
    
    @FXML
    public void handleButtonRemover() throws IOException {
        Medico medico = tableViewMedicos.getSelectionModel().getSelectedItem();
        if (medico != null){
            if(loadAnchorPaneRemoverMedicoDialog()){
                medicoDAO.setConnection(connection);
                medicoDAO.remover(medico);
                loadTableView();
            }
        }
    }
    
    public boolean loadAnchorPaneRemoverMedicoDialog() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneFuncionarioRemoverMedicoDialogController
               .class.getResource("/maissaudeplus/view/funcionario/FXMLAnchorPaneFuncionarioRemoverMedicoDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Remover Médico");
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLAnchorPaneFuncionarioRemoverMedicoDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }
}
