package maissaudeplus.controller.funcionario;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Classe de controle que é responsável pelo perfil do funcionário.
 */
public class FXMLVBoxFuncionarioMainController implements Initializable {
    
    @FXML
    private VBox vBoxRoot;
    
    @FXML
    private JFXButton buttonBarMin;

    @FXML
    private JFXButton buttonBarClose;
      
    @FXML
    private JFXButton buttonHome;
    
    //Botão que carrega a tela de agendamentos
    @FXML
    private JFXButton buttonAgendamentos;
    
    @FXML
    private JFXButton buttonCadastroPaciente;
    
    @FXML
    private JFXButton buttonCadastrarMedico;

    @FXML
    private JFXButton buttonCadastrarProcedimento;
    
    @FXML
    private JFXButton buttonTrocarUsuario;
    
    //Botão que fecha o programa
    @FXML
    private JFXButton buttonClose;
    
    //AnchorPane base, na verdade não existem regras de negocio relacionados a ele, sendo apenas substituido pelos outros Anchor Pane.
    @FXML
    private AnchorPane anchorPaneBase;
    
    //Os Anchor Pane abaixo são elementos que serão carregados a partir de outro arquivo .fxml
    private AnchorPane anchorPaneHome;
    private AnchorPane anchorPaneAgendarConsulta;
    private AnchorPane anchorPaneCadastrarPaciente;
    private AnchorPane anchorPaneCadastrarMedico;
    private AnchorPane anchorPaneCadastrarProcedimento;
    
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
    public void handleButtonAgendamentos() throws IOException {
        //Método de configuração do botão que troca o anchor pane base pelo anchor pane de agendamentos.
        //Este método carrega a o Anchor Pane responsavel pelo agendamento de consultas.
        if(anchorPaneAgendarConsulta == null){
            anchorPaneAgendarConsulta = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/funcionario/FXMLAnchorPaneFuncionarioVisualizarConsulta.fxml"));
        }
        //Os métodos abaixo configurão as restrições do anchor pane para que ele possa ser redimensionado
        AnchorPane.setTopAnchor(anchorPaneAgendarConsulta, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneAgendarConsulta,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneAgendarConsulta, 0.0);
        AnchorPane.setRightAnchor(anchorPaneAgendarConsulta, 0.0);
        //Método que carrega o anchorPaneAgendarConsulta no anchorPaneBase
        anchorPaneBase.getChildren().setAll(anchorPaneAgendarConsulta);
    }
    
    @FXML
    public void handleButtonCadastrarPaciente() throws IOException {
        //Método de configuração do botão que troca o anchor pane base pelo anchor pane de cadastro de pacietne.
        //Este método carrega a o Anchor Pane responsavel pelo cadastro de paciente.
        if(anchorPaneCadastrarPaciente == null){
            anchorPaneCadastrarPaciente = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/funcionario/FXMLAnchorPaneFuncionarioVisualizarPaciente.fxml"));
        }
        //Os métodos abaixo configurão as restrições do anchor pane para que ele possa ser redimensionado
        AnchorPane.setTopAnchor(anchorPaneCadastrarPaciente, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneCadastrarPaciente,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneCadastrarPaciente, 0.0);
        AnchorPane.setRightAnchor(anchorPaneCadastrarPaciente, 0.0);
        //Método que carrega o anchorPaneCadastroPacietne no anchorPaneBase
        anchorPaneBase.getChildren().setAll(anchorPaneCadastrarPaciente);
    }
    
    @FXML
    public void handleButtonCadastrarMedico() throws IOException {
        //Método de configuração do botão que troca o anchor pane base pelo anchor pane de cadastro de medico.
        //Este método carrega a o Anchor Pane responsavel pelo cadastro de medico.
        if(anchorPaneCadastrarMedico == null){
            anchorPaneCadastrarMedico = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/funcionario/FXMLAnchorPaneFuncionarioVisualizarMedico.fxml"));
        }
        //Os métodos abaixo configurão as restrições do anchor pane para que ele possa ser redimensionado
        AnchorPane.setTopAnchor(anchorPaneCadastrarMedico, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneCadastrarMedico,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneCadastrarMedico, 0.0);
        AnchorPane.setRightAnchor(anchorPaneCadastrarMedico, 0.0);
        //Método que carrega o anchorPaneCadastroMedico no anchorPaneBase
        anchorPaneBase.getChildren().setAll(anchorPaneCadastrarMedico);
    }
    
    @FXML
    public void handleButtonCadastrarProcedimento() throws IOException {
        //Método de configuração do botão que troca o anchor pane base pelo anchor pane de cadastro de medico.
        //Este método carrega a o Anchor Pane responsavel pelo cadastro de medico.
        if(anchorPaneCadastrarProcedimento == null){
            anchorPaneCadastrarProcedimento = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/funcionario/FXMLAnchorPaneFuncionarioVisualizarProcedimento.fxml"));
        }
        //Os métodos abaixo configurão as restrições do anchor pane para que ele possa ser redimensionado
        AnchorPane.setTopAnchor(anchorPaneCadastrarProcedimento, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneCadastrarProcedimento,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneCadastrarProcedimento, 0.0);
        AnchorPane.setRightAnchor(anchorPaneCadastrarProcedimento, 0.0);
        //Método que carrega o anchorPaneCadastroMedico no anchorPaneBase
        anchorPaneBase.getChildren().setAll(anchorPaneCadastrarProcedimento);
    }
    
    @FXML
    void handleButtonTrocarUsuario() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/maissaudeplus/view/medico/FXMLVBoxMedicoMain.fxml"));
        VBox vbox = (VBox) loader.load();
        vBoxRoot.getChildren().setAll(vbox);
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
