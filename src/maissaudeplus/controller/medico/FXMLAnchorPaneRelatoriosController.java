package maissaudeplus.controller.medico;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class FXMLAnchorPaneRelatoriosController implements Initializable {
    
    @FXML
    private JFXButton buttonRelatorioGastoProcedimento;

    @FXML
    private JFXButton buttonRelatorioMedico;

    @FXML
    private JFXButton buttonRelatorioProcedimento;
    
    @FXML
    private AnchorPane anchorPaneBase;
    
    private AnchorPane anchorPaneRelatorioProcedimento;
    private AnchorPane anchorPaneRelatorioGastoProcedimento;
    private AnchorPane anchorPaneRelatorioMedico;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
