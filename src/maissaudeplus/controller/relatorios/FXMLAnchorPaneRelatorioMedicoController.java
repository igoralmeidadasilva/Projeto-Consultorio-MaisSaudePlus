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
import maissaudeplus.model.dao.MedicoRelatorioDAO;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;
import maissaudeplus.model.domain.MedicoRelatorio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FXMLAnchorPaneRelatorioMedicoController implements Initializable {

    @FXML
    private JFXButton buttonImprimir;

    @FXML
    private TableView<MedicoRelatorio> tableView;

    @FXML
    private TableColumn<MedicoRelatorio, String> tableColumnMedicoNome;

    @FXML
    private TableColumn<MedicoRelatorio, Integer> tableColumnMedicoConsultasRealizadas;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    private ObservableList<MedicoRelatorio> listaMedicos;
    private MedicoRelatorioDAO medicoRelatorioDAO = new MedicoRelatorioDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableColumnMedicoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnMedicoConsultasRealizadas.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadTableView();
    }

    public void loadTableView(){
        medicoRelatorioDAO.setConnection(connection);
        listaMedicos = FXCollections.observableArrayList(medicoRelatorioDAO.listarQuantidadeConsultasPorMedico());
        tableView.setItems(listaMedicos);     
    }    
    
    @FXML
    public void handleButtonImprimir() throws JRException{
        URL url = getClass().getResource("/maissaudeplus/relatorios/MaisSaudePlus_RelatorioQuantidadeConsultaPorMedico.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
    }
}




