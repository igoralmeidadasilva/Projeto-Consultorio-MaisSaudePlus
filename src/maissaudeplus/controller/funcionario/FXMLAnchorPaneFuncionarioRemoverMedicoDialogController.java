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

public class FXMLAnchorPaneFuncionarioRemoverMedicoDialogController implements Initializable {

    @FXML
    private JFXButton buttonMedicoInserir;

    @FXML
    private JFXButton buttonMedicoRemover;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }   

    @FXML
    void handleButtonCancelar() {
        dialogStage.close();
    }

    @FXML
    void handleButtonConfirmar() {
        buttonConfirmarClicked = true;
        dialogStage.close();
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }    
}
