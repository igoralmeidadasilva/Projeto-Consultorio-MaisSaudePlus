/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maissaudeplus.controller.funcionario;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import maissaudeplus.model.domain.Procedimento;

/**
 * FXML Controller class
 *
 * @author Raphael
 */
public class FXMLAnchorPaneFuncionarioVisualizarProcedimentoController implements Initializable {

    @FXML
    private TableView<Procedimento> tableViewProcedimento;
    @FXML
    private TableColumn<Procedimento, Integer> tableColumnProcedimentoCodigo;
    @FXML
    private TableColumn<Procedimento, String> tableColumnProcedimentoNome;

    @FXML
    private Label labelProcedimentoValor;
    @FXML
    private Label labelProcedimentoDescricao;
    @FXML
    private Label labelProcedimentoNome;
    @FXML
    private Label labelProcediimentoRespostaObesidade;
    @FXML
    private JFXButton buttonProcedimentoAlterar;
    @FXML
    private JFXButton buttoProcedimentoInserir;
    @FXML
    private JFXButton buttonProcedimentoRemover;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
