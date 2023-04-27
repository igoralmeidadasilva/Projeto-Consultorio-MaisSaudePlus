package maissaudeplus.controller.medico;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import org.controlsfx.control.SearchableComboBox;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import maissaudeplus.model.dao.ConsultaDAO;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;
import maissaudeplus.model.domain.Consulta;
import maissaudeplus.model.domain.Medicamento;
import maissaudeplus.model.domain.Procedimento;

public class FXMLAnchorPaneMedicoRealizarConsultaController {

    @FXML
    private SearchableComboBox<Consulta> comboBoxSelecionarConsulta;

    @FXML
    private SearchableComboBox<Procedimento> comboBoxSelecionarProcedimento;

    @FXML
    private SearchableComboBox<Medicamento> comboBoxSelecionarMedicamento;

    @FXML
    private JFXButton buttonConfirmar;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    private final ConsultaDAO consultaDAO = new ConsultaDAO();

    
    public void initialize(URL url, ResourceBundle rb) {      
        loadComboBoxConsulta();
        // loadComboBoxProcedimento();
        // loadComboBoxMedicamento();
    }  
    
    // @FXML
    // public void handleSelecionarConsulta() throws IOException{
    //     if(comboBoxSelecionarConsulta.getValue() != null){
    //         loadComboBoxConsulta();
    //     }
    // }

    private void loadComboBoxConsulta() {
        consultaDAO.setConnection(connection);
        ObservableList<Consulta> lista = FXCollections.observableArrayList(consultaDAO.listar());
        comboBoxSelecionarConsulta.setItems(lista);
    }


}
