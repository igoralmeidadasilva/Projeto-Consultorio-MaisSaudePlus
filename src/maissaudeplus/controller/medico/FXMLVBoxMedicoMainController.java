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
    private JFXButton buttonConsultaPorMes;

    @FXML
    private JFXButton buttonProcedimentosPorMedico;

    @FXML
    private JFXButton buttonProcedimentosPorMes;
    
    @FXML
    private JFXButton buttonRelatorioProcedimento;
    
    @FXML
    private JFXButton buttonRelatorioGastoProcedimento;

    @FXML
    private JFXButton buttonRelatorioMedico; 

    @FXML
    private JFXButton buttonClose;

    @FXML
    private AnchorPane anchorPaneBase;

    private AnchorPane anchorPaneRealizarConsulta;
    private AnchorPane anchorPaneGraficoPorMes;
    private AnchorPane anchorPaneGraficoPorProcedimento;
    private AnchorPane anchorPaneGraficoProcedimentoPorMes;
    private AnchorPane anchorPaneRelatorioProcedimento;
    private AnchorPane anchorPaneRelatorioGastoProcedimento;
    private AnchorPane anchorPaneRelatorioMedico;
    private AnchorPane AnchorPaneMedicoNotificarPaciente;

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
    
    @FXML
    public void handleButtonConsultaPorMes() throws IOException {
        if(anchorPaneGraficoPorMes == null){
            anchorPaneGraficoPorMes = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/medico/FXMLAnchorPaneMedicoGraficoConsultasPorMes.fxml"));
        }
        AnchorPane.setTopAnchor(anchorPaneGraficoPorMes, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneGraficoPorMes,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneGraficoPorMes, 0.0);
        AnchorPane.setRightAnchor(anchorPaneGraficoPorMes, 0.0);
        anchorPaneBase.getChildren().setAll(anchorPaneGraficoPorMes);
    }
    
    @FXML
    public void handleButtonProcedimentosPorMes() throws IOException {
        if(anchorPaneGraficoProcedimentoPorMes == null){
            anchorPaneGraficoProcedimentoPorMes = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/medico/FXMLAnchorPaneMedicoGraficoConsultaProcedimentosPorMes.fxml"));
        }
        AnchorPane.setTopAnchor(anchorPaneGraficoProcedimentoPorMes, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneGraficoProcedimentoPorMes,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneGraficoProcedimentoPorMes, 0.0);
        AnchorPane.setRightAnchor(anchorPaneGraficoProcedimentoPorMes, 0.0);
        anchorPaneBase.getChildren().setAll(anchorPaneGraficoProcedimentoPorMes);
    }

    @FXML
    public void handleButtonCustoProcedimentoPorMedico() throws IOException {
        if(anchorPaneGraficoPorProcedimento == null){
            anchorPaneGraficoPorProcedimento = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/medico/FXMLAnchorPaneMedicoGraficoCustoProcedimentoPorMedico.fxml"));
        }
        AnchorPane.setTopAnchor(anchorPaneGraficoPorProcedimento, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneGraficoPorProcedimento,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneGraficoPorProcedimento, 0.0);
        AnchorPane.setRightAnchor(anchorPaneGraficoPorProcedimento, 0.0);
        anchorPaneBase.getChildren().setAll(anchorPaneGraficoPorProcedimento);
    }
    
    @FXML
    void handleButtonRelatorioProcedimento() throws IOException {
        if(anchorPaneRelatorioProcedimento == null){
            anchorPaneRelatorioProcedimento = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/relatorios/FXMLAnchorPaneRelatorioProcedimento.fxml"));
        }
        AnchorPane.setTopAnchor(anchorPaneRelatorioProcedimento, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneRelatorioProcedimento,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneRelatorioProcedimento, 0.0);
        AnchorPane.setRightAnchor(anchorPaneRelatorioProcedimento, 0.0);
        anchorPaneBase.getChildren().setAll(anchorPaneRelatorioProcedimento);
    }    

    @FXML
    void handleButtonRelatorioGastoProcedimento() throws IOException {
        if(anchorPaneRelatorioGastoProcedimento == null){
            anchorPaneRelatorioGastoProcedimento = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/relatorios/FXMLAnchorPaneRelatorioGastoProcedimento.fxml"));
        }
        AnchorPane.setTopAnchor(anchorPaneRelatorioGastoProcedimento, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneRelatorioGastoProcedimento,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneRelatorioGastoProcedimento, 0.0);
        AnchorPane.setRightAnchor(anchorPaneRelatorioGastoProcedimento, 0.0);
        anchorPaneBase.getChildren().setAll(anchorPaneRelatorioGastoProcedimento);
    }
    
    @FXML
    void handleButtonRelatorioMedico() throws IOException {
        if(anchorPaneRelatorioMedico == null){
            anchorPaneRelatorioMedico = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/relatorios/FXMLAnchorPaneRelatorioMedico.fxml"));
        }
        AnchorPane.setTopAnchor(anchorPaneRelatorioMedico, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneRelatorioMedico,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneRelatorioMedico, 0.0);
        AnchorPane.setRightAnchor(anchorPaneRelatorioMedico, 0.0);
        anchorPaneBase.getChildren().setAll(anchorPaneRelatorioMedico);
    }    
}
