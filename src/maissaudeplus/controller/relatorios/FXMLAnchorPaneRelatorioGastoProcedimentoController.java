package maissaudeplus.controller.relatorios;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import maissaudeplus.model.dao.ProcedimentoGastoRelatorioDAO;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;
import maissaudeplus.model.domain.ProcedimentoGastoRelatorio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class FXMLAnchorPaneRelatorioGastoProcedimentoController implements Initializable {


    @FXML
    private JFXButton buttonImprimir;

    @FXML
    private TableView<ProcedimentoGastoRelatorio> tableView;

    @FXML
    private TableColumn<ProcedimentoGastoRelatorio, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<ProcedimentoGastoRelatorio, String> tableColumnNome;
    
    @FXML
    private TableColumn<ProcedimentoGastoRelatorio, Integer> tableColumnValor;

    @FXML
    private TableColumn<ProcedimentoGastoRelatorio, Integer> tableColumnValorTotal;

    @FXML
    private TableColumn<ProcedimentoGastoRelatorio, String> tableColumnDescricao;
    
    //ObservableList para preencher a tableview
    private ObservableList<ProcedimentoGastoRelatorio> listaProcedimento;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ProcedimentoGastoRelatorioDAO procedimentoGastoRelatorioDAO = new ProcedimentoGastoRelatorioDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codProcedimento"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeProcedimento"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valorProcedimento"));
        tableColumnValorTotal.setCellValueFactory(new PropertyValueFactory<>("soma"));
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descProcedimento"));
        loadTableView();
    }
    
    public void loadTableView(){
        procedimentoGastoRelatorioDAO.setConnection(connection);
        listaProcedimento = FXCollections.observableArrayList(procedimentoGastoRelatorioDAO.listar());
        tableView.setItems(listaProcedimento);  
    }

    // necessáio criar o arquivo com o mesmo nome
    @FXML
    public void handleButtonImprimir() throws JRException{
        URL url = getClass().getResource("/maissaudeplus/relatorios/MaisSaudePlus_RelatorioGastoProcedimento.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
    }  
}

