/*###########################################################################################
# Estudante: Júlia Borges Santos (20211si016)                                               #
# Esta classe tem como objetivo remover Médico no banco de dados, que nada mais é que       #
# instanciar uma conexão ao banco para remover o médico selecionado na tableview.           #
#############################################################################################
*/

package maissaudeplus.controller.funcionario;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class FXMLAnchorPaneFuncionarioRemoverMedicoDialogController implements Initializable {

    @FXML
    private JFXButton buttonMedicoInserir;

    @FXML
    private JFXButton buttonMedicoRemover;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }   

    @FXML
    void handleButtonCancelar() {
        dialogStage.close();
    }

    @FXML
    void handleButtonConfirmar() {
        buttonConfirmarClicked = true;
        dialogStage.close();
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }    
}
