package maissaudeplus.controller.medico;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import maissaudeplus.Main;

public class FXMLVBoxMedicoMainController implements Initializable {
    
    @FXML
    private MenuItem menuItemTrocarFuncionario;

    @FXML
    private MenuItem menuItemTrocarMedico;

    @FXML
    private JFXButton buttonRealizarConsulta;

    @FXML
    private JFXButton buttonClose;

    @FXML
    private AnchorPane anchorPaneBase;

    private AnchorPane anchorPaneRealizarConsulta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
  
    @FXML
    void handleMenuItemTrocarFuncionario() throws IOException {
        Main.setRoot("/maissaudeplus/view/funcionario/FXMLVBoxFuncionarioMain.fxml");
    }

    @FXML
    void handleMenuItemTrocarMedico() throws IOException {
        Main.setRoot("/maissaudeplus/view/medico/FXMLVBoxMedicoMain.fxml");
    }
    
    @FXML
    public void handleButtonClose(){
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    public void handleRealizarConsulta() throws IOException{
        if(anchorPaneRealizarConsulta == null){
            anchorPaneRealizarConsulta = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/medico/FXMLAnchorPaneMedicoRealizarConsulta.fxml"));
        }


        AnchorPane.setTopAnchor(anchorPaneRealizarConsulta, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneRealizarConsulta,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneRealizarConsulta, 0.0);
        AnchorPane.setRightAnchor(anchorPaneRealizarConsulta, 0.0);
        
        anchorPaneBase.getChildren().setAll(anchorPaneRealizarConsulta);
        
    }
}
