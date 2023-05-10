package maissaudeplus.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import maissaudeplus.Main;
import maissaudeplus.controller.funcionario.FXMLVBoxFuncionarioMainController;
import maissaudeplus.controller.medico.FXMLVBoxMedicoMainController;

/**
 * FXML Controller class
 * Classe de controle que é responsável por carregar o Vbox do Funcionario ou do Médico dependendo da escolha do usúario.
 */
public class FXMLAnchorPaneSelecaoDePerfilController implements Initializable {
    
    //Botão que carrega o Vbox do Funcionario
    @FXML
    private JFXButton buttonFuncionario; 

    //Botão que carrega o Vbox do Médico
    @FXML
    private JFXButton buttonMedico;
    
    @FXML
    private AnchorPane anchorPaneBase;           

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void handleButtonLoadFuncionarios() throws IOException { 
        //Este método carrega a tela do funcionario
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/maissaudeplus/view/funcionario/FXMLVBoxFuncionarioMain.fxml"));
        VBox vbox = (VBox) loader.load();
        FXMLVBoxFuncionarioMainController controller = loader.getController();
        anchorPaneBase.getChildren().setAll(vbox);
        //Este método tem a única função de aumentar um pouco a janela médico assim que é aberta.
        Main.setPrimarySize();
    }
    
    @FXML
    private void handleButtonLoadMedico() throws IOException {
        //Este método carrega a tela do médico
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/maissaudeplus/view/medico/FXMLVBoxMedicoMain.fxml"));
        VBox vbox = (VBox) loader.load();
        FXMLVBoxMedicoMainController controller = loader.getController();
        anchorPaneBase.getChildren().setAll(vbox);
        //Este método tem a única função de aumentar um pouco a janela médico assim que é aberta.
        Main.setPrimarySize();
    } 
}
