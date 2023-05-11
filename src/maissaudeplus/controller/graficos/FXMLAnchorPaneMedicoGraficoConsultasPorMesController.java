package maissaudeplus.controller.graficos;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import maissaudeplus.model.dao.ConsultaDAO;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 */
public class FXMLAnchorPaneMedicoGraficoConsultasPorMesController implements Initializable {
    
    @FXML
    private BarChart<String, Integer> barChart;
    
    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;
    
    @FXML
    private JFXButton buttonRegarregar;
    
    //Estabelecer conexão com o banco de dados postgres
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    //Lista de DAOs usados neste controller
    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleButtonRecarregar();
    } 
    
    @FXML
    public void handleButtonRecarregar(){
        barChart.getData().clear();
        String[] arrayMeses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
        ObservableList<String> observableListMeses = FXCollections.observableArrayList(Arrays.asList(arrayMeses));
        categoryAxis.setCategories(observableListMeses);
        consultaDAO.setConnection(connection);
        Map<Integer, Integer> dados = consultaDAO.listarQuantidadeConsultasPorMes(LocalDate.now().getYear());
        XYChart.Series<String, Integer> serie = new XYChart.Series<>();
        for(Integer chave : dados.keySet()){
            Integer valor = dados.get(chave);
            String mes = retornaNomeMes(chave);           
            XYChart.Data<String, Integer> dado = new XYChart.Data<>(mes, valor);
            serie.getData().add(dado);
        }
        barChart.setTitle("Consultas X Mês");
        barChart.getData().add(serie);
    }
    
    public String retornaNomeMes(int mes){
        switch(mes){
            case 1:
                return "Jan";
            case 2:
                return "Fev";
            case 3:
                return "Mar";
            case 4:
                return "Abr";
            case 5:
                return "Mai";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Ago";
            case 9:
                return "Set";
            case 10:
                return "Out";
            case 11:
                return "Nov";
            case 12:
                return "Dez";
        }
        return "";
    }
}
