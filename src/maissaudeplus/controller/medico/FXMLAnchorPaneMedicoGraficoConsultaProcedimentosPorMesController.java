package maissaudeplus.controller.medico;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import maissaudeplus.model.dao.ConsultaRealizadaDAO;
import maissaudeplus.model.dao.MedicoDAO;


public class FXMLAnchorPaneMedicoGraficoConsultaProcedimentosPorMesController implements Initializable {

    @FXML
    private BarChart<String, Double> barChart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    private JFXButton buttonRecarregar;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final ConsultaRealizadaDAO consultaRealizadaDAO = new ConsultaRealizadaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarGrafico();
    } 
    // MODIFICAR A FUNÇÃO PARA EXIBIR A QUANTIDADE DE PROCEDIMENTO POR MES 
    private void carregarGrafico(){
        barChart.getData().clear();
        medicoDAO.setConnection(connection);
        consultaRealizadaDAO.setConnection(connection);
        List<String> arrayMedicos = medicoDAO.listarMedicoPorNome();
        ObservableList<String> observableListMedicos = FXCollections.observableArrayList(arrayMedicos);
        categoryAxis.setCategories(observableListMedicos);
        Map<String,Double> dataset = consultaRealizadaDAO.gastoPorProcedimentoSolicitado();
        XYChart.Series<String, Double> serie = new XYChart.Series();
        for(String nome : dataset.keySet()){
            Double total = dataset.get(nome);          
            XYChart.Data<String, Double> dado = new XYChart.Data<>(nome, total);
            serie.getData().add(dado);
        }
        barChart.setTitle("Custo Procedimentos x Médico (em R$)");
        barChart.getData().add(serie);
    }

}
