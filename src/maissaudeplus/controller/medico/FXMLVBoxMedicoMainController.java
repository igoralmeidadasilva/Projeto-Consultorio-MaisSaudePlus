package maissaudeplus.controller.medico;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import maissaudeplus.controller.funcionario.FXMLVBoxFuncionarioMainController;

public class FXMLVBoxMedicoMainController implements Initializable {

    @FXML
    private VBox vBoxRoot;
    
    @FXML
    private JFXButton buttonBarMin;

    @FXML
    private JFXButton buttonBarClose;
    
    @FXML
    private JFXButton buttonHome;
    
    @FXML
    private JFXButton buttonRealizarConsulta;
    
    @FXML
    private JFXButton buttonGraficos;
    
    @FXML
    private JFXButton buttonRelatorios; 
    
    @FXML
    private JFXButton buttonTrocarUsuario;
     
    @FXML
    private JFXButton buttonClose;

    @FXML
    private AnchorPane anchorPaneBase;

    private AnchorPane anchorPaneHome;
    private AnchorPane anchorPaneRealizarConsulta;
    private AnchorPane anchorPaneGraficos;
    private AnchorPane anchorPaneRelatorios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            handleButtonHome();
        } catch (IOException ex) {
            Logger.getLogger(FXMLVBoxFuncionarioMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void handleButtonBarClose() {
        Stage stage = (Stage) buttonBarClose.getScene().getWindow(); 
        stage.close(); 
    }

    @FXML
    void handleButtonBarMin() {
        Stage stage = (Stage) buttonBarMin.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @FXML
    void handleButtonHome() throws IOException {
        //Método de configuração do botão que troca o anchor pane base pelo anchor pane de cadastro de pacietne.
        //Este método carrega a o Anchor Pane responsavel pelo cadastro de paciente.
        if(anchorPaneHome == null){
            anchorPaneHome = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/FXMLAnchorPaneHome.fxml"));
        }
        //Os métodos abaixo configurão as restrições do anchor pane para que ele possa ser redimensionado
        AnchorPane.setTopAnchor(anchorPaneHome, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneHome,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneHome, 0.0);
        AnchorPane.setRightAnchor(anchorPaneHome, 0.0);
        //Método que carrega o anchorPaneCadastroPacietne no anchorPaneBase
        anchorPaneBase.getChildren().setAll(anchorPaneHome);
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
    
    @FXML
    public void handleButtonGraficos() throws IOException{
        anchorPaneGraficos = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/medico/FXMLAnchorPaneGraficos.fxml"));        
        AnchorPane.setTopAnchor(anchorPaneGraficos, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneGraficos,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneGraficos, 0.0);
        AnchorPane.setRightAnchor(anchorPaneGraficos, 0.0);       
        anchorPaneBase.getChildren().setAll(anchorPaneGraficos);
    }
    
    @FXML
    public void handleButtonRelatorios() throws IOException{
        anchorPaneRelatorios = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/medico/FXMLAnchorPaneRelatorios.fxml"));        
        AnchorPane.setTopAnchor(anchorPaneRelatorios, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneRelatorios,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneRelatorios, 0.0);
        AnchorPane.setRightAnchor(anchorPaneRelatorios, 0.0);       
        anchorPaneBase.getChildren().setAll(anchorPaneRelatorios);
    }
    
    @FXML
    void handleButtonTrocarUsuario() throws IOException{
        //Este método carrega a tela do funcionario
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/maissaudeplus/view/funcionario/FXMLVBoxFuncionarioMain.fxml"));
        VBox vbox = (VBox) loader.load();
        vBoxRoot.getChildren().setAll(vbox);
    }
    
    @FXML
    public void handleButtonClose(){
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close(); 
    }
}
