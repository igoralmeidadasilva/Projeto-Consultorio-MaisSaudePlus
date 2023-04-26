package maissaudeplus.controller.funcionario;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import maissaudeplus.Main;

/**
 * FXML Controller class
 * Classe de controle que é responsável pelo perfil do funcionário.
 */
public class FXMLVBoxFuncionarioMainController implements Initializable {
    
    //MenuItem que chama o perfil do funcionário
    @FXML
    private MenuItem menuItemTrocarFuncionario;

    //MenuItem que chama o perfil do médico
    @FXML
    private MenuItem menuItemTrocarMedico;
    
    //Botão que carrega a tela de agendamentos
    @FXML
    private JFXButton buttonAgendamentos;
    
    @FXML
    private JFXButton buttonCadastroPaciente;
    
    @FXML
    private JFXButton buttonCadastrarMedico;
    
    //Botão que fecha o programa
    @FXML
    private JFXButton buttonClose;
    
    //AnchorPane base, na verdade não existem regras de negocio relacionados a ele, sendo apenas substituido pelos outros Anchor Pane.
    @FXML
    private AnchorPane anchorPaneBase;
    
    //Os Anchor Pane abaixo são elementos que serão carregados a partir de outro arquivo .fxml
    private AnchorPane anchorPaneAgendarConsulta;
    private AnchorPane anchorPaneCadastrarPaciente;
    private AnchorPane anchorPaneCadastrarMedico;
    
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
    public void handleButtonClose(){
        //Este botão fecha o programa
        //Obtendo a janela atual
        Stage stage = (Stage) buttonClose.getScene().getWindow(); 
        //Fechando o Stage
        stage.close(); 
    }

}
