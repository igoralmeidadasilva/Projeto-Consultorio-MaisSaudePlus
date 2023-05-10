package maissaudeplus.controller.medico;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class FXMLAnchorPaneGraficosController implements Initializable {

    @FXML
    private JFXButton buttonGraficoProcedimentoMes;

    @FXML
    private JFXButton buttonGraficoConsultaMes;

    @FXML
    private JFXButton buttonGraficoProcedimentoMedico;
    
    @FXML
    private AnchorPane anchorPaneBase;
    
    private AnchorPane anchorPaneGraficoPorMes;
    
    private AnchorPane anchorPaneGraficoProcedimentoPorMes;
    
    private AnchorPane anchorPaneGraficoPorProcedimento;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    void handleButtonGraficoConsultaMes() throws IOException {
        if(anchorPaneGraficoPorMes == null){
            anchorPaneGraficoPorMes = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/graficos/FXMLAnchorPaneMedicoGraficoConsultasPorMes.fxml"));
        }
        AnchorPane.setTopAnchor(anchorPaneGraficoPorMes, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneGraficoPorMes,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneGraficoPorMes, 0.0);
        AnchorPane.setRightAnchor(anchorPaneGraficoPorMes, 0.0);
        anchorPaneBase.getChildren().setAll(anchorPaneGraficoPorMes);
    }

    @FXML
    void handleButtonGraficoProcedimentoMedico() throws IOException {
        if(anchorPaneGraficoProcedimentoPorMes == null){
            anchorPaneGraficoProcedimentoPorMes = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/graficos/FXMLAnchorPaneMedicoGraficoConsultaProcedimentosPorMes.fxml"));
        }
        AnchorPane.setTopAnchor(anchorPaneGraficoProcedimentoPorMes, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneGraficoProcedimentoPorMes,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneGraficoProcedimentoPorMes, 0.0);
        AnchorPane.setRightAnchor(anchorPaneGraficoProcedimentoPorMes, 0.0);
        anchorPaneBase.getChildren().setAll(anchorPaneGraficoProcedimentoPorMes);
    }

    @FXML
    void handleButtonGraficoProcedimentoMes() throws IOException {
        if(anchorPaneGraficoPorProcedimento == null){
            anchorPaneGraficoPorProcedimento = (AnchorPane) FXMLLoader.load(getClass().getResource("/maissaudeplus/view/graficos/FXMLAnchorPaneMedicoGraficoCustoProcedimentoPorMedico.fxml"));
        }
        AnchorPane.setTopAnchor(anchorPaneGraficoPorProcedimento, 0.0);
        AnchorPane.setBottomAnchor(anchorPaneGraficoPorProcedimento,0.0 );
        AnchorPane.setLeftAnchor(anchorPaneGraficoPorProcedimento, 0.0);
        AnchorPane.setRightAnchor(anchorPaneGraficoPorProcedimento, 0.0);
        anchorPaneBase.getChildren().setAll(anchorPaneGraficoPorProcedimento);
    }        
}
