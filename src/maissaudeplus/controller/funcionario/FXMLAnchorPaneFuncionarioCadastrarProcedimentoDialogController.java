/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maissaudeplus.controller.funcionario;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
 * @author Raphael
 */
public class FXMLAnchorPaneFuncionarioCadastrarProcedimentoDialogController implements Initializable {

    @FXML
    private JFXTextField textFieldNomeProcedimento;
    @FXML
    private JFXTextField textFieldValorProcedimento;
    @FXML
    private CheckBox checkBoxProcedimento;
    @FXML
    private JFXTextField textFieldDescricaoProcedimento;
    @FXML
    private JFXButton buttonConfirmarCadastroProcedimento;
    @FXML
    private JFXButton buttonCancelarCadastroProcedimento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
