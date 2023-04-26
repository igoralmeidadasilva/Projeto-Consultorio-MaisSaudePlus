package maissaudeplus.controller.medico;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import maissaudeplus.Main;

public class FXMLVBoxMedicoMainController implements Initializable {
    
    @FXML
    //MenuItem que chama o perfil do funcionário
    private MenuItem menuItemTrocarFuncionario;

    @FXML
    //MenuItem que chama o perfil do médico
    private MenuItem menuItemTrocarMedico;
    
    @FXML
    //Botão que fecha o programa
    private JFXButton buttonClose;
    
    @FXML
    //AnchorPane base, na verdade não existem regras de negocio relacionados a ele, sendo apenas substituido pelos outros Anchor Pane.
    private AnchorPane anchorPaneBase;
    
    
    //Os Anchor Pane abaixo são elementos que serão carregados a partir de outro arquivo .fxml
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
  
    @FXML
    void handleMenuItemTrocarFuncionario() throws IOException {
        //Este método carrega a tela do fúncionario
        //Método estático da classe Main que troca o fxml carregado pelo VBox principal do perfil funcionario.
        Main.setRoot("/maissaudeplus/view/funcionario/FXMLVBoxFuncionarioMain.fxml");
    }

    @FXML
    void handleMenuItemTrocarMedico() throws IOException {
        //Este método carrega a tela do médico
        //Método estático da classe Main que troca o fxml carregado pelo VBox principal do perfil médico.
        Main.setRoot("/maissaudeplus/view/medico/FXMLVBoxMedicoMain.fxml");
    }
    
    @FXML
    public void handleButtonClose(){
        //Este botão fecha o programa
        //Obtendo a janela atual
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        //Fechando o Stage
        stage.close(); 
    }
}
