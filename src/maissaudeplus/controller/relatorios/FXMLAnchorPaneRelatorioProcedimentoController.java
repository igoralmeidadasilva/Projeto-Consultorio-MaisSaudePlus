package maissaudeplus.controller.relatorios;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;
import maissaudeplus.model.domain.ProcedimentoRelatorio;
import maissaudeplus.model.dao.ProcedimentoRelatorioDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FXMLAnchorPaneRelatorioProcedimentoController implements Initializable {

    @FXML
    private JFXButton buttonImprimir;

    @FXML
    private TableView<ProcedimentoRelatorio> tableView;

    @FXML
    private TableColumn<ProcedimentoRelatorio, Integer> tableColumnCodigo;

    @FXML
    private TableColumn<ProcedimentoRelatorio, String> tableColumnNome;
    
    @FXML
    private TableColumn<ProcedimentoRelatorio, Integer> tableColumnValor;

    @FXML
    private TableColumn<ProcedimentoRelatorio, Integer> tableColumnQuantidade;

    @FXML
    private TableColumn<ProcedimentoRelatorio, String> tableColumnDescricao;
    
    //ObservableList para preencher a tableview
    private ObservableList<ProcedimentoRelatorio> listaProcedimento;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ProcedimentoRelatorioDAO procedimentoRelatorioDAO = new ProcedimentoRelatorioDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codProcedimento"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeProcedimento"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valorProcedimento"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("qtde"));
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descProcedimento"));
        loadTableView();
    }
    
    public void loadTableView(){
        procedimentoRelatorioDAO.setConnection(connection);
        listaProcedimento = FXCollections.observableArrayList(procedimentoRelatorioDAO.listar());
        tableView.setItems(listaProcedimento);  
    }

    @FXML
    public void handleButtonImprimir() throws JRException{
        URL url = getClass().getResource("/maissaudeplus/relatorios/MaisSaudePlus_RelatorioProcedimento.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
    }  
}




