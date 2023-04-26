package maissaudeplus.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import maissaudeplus.Main;

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
        //Este método carrega a tela do fúncionario
        //Método estático da classe Main que troca o fxml carregado pelo VBox principal do perfil funcionario.
        Main.setRoot("/maissaudeplus/view/funcionario/FXMLVBoxFuncionarioMain.fxml");
        //Método estático da classe Main que que permite redimensionalizar o programa.
        Main.setResizableTrue();
        //Este método tem a única função de aumentar um pouco a janela funcionarios assim que é aberta.
        Main.setPrimarySize();     
    }
    
    @FXML
    private void handleButtonLoadMedico() throws IOException {
        //Este método carrega a tela do médico
        //Método estático da classe Main que troca o fxml carregado pelo VBox principal do perfil médico.
        Main.setRoot("/maissaudeplus/view/medico/FXMLVBoxMedicoMain.fxml");
        //Método estático da classe Main que que permite redimensionalizar o programa.
        Main.setResizableTrue();
        //Este método tem a única função de aumentar um pouco a janela médico assim que é aberta.
        Main.setPrimarySize();  
    } 
}
