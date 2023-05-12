/*###########################################################################################
# Estudante: Júlia Borges Santos (20211si016)                                               #
# Esta classe tem como objetivo cadastrar Médico no banco de dados, que nada mais é que     #
# instanciar uma conexão ao banco para inserir os valores passados para o objeto médico     #
#############################################################################################
*/

package maissaudeplus.controller.funcionario;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import maissaudeplus.model.domain.Medico;

public class FXMLAnchorPaneFuncionarioCadastrarMedicoDialogController implements Initializable {

    @FXML
    private JFXButton buttonConfirmar;

    @FXML
    private JFXButton buttonCancelar;

    @FXML
    private JFXTextField textFieldNome;

    @FXML
    private JFXTextField textFieldCPF;

    @FXML
    private JFXTextField textFieldEmail;

    @FXML
    private JFXTextField textFieldTelefone;

    @FXML
    private JFXDatePicker datePickerDataAdmissao;

    @FXML
    private JFXTextField textFieldCrm;

    private boolean buttonConfirmarClicked = false;

    private Stage dialogStage;

    private Medico medico;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    @FXML
    void handleButtonConfirmar() {
        if(validarEntradaDeDados()){
            medico.setNome(textFieldNome.getText().toUpperCase());
            medico.setCpf(textFieldCPF.getText());
            medico.setCrmMedico(textFieldCrm.getText());
            medico.setDataAdmissao(datePickerDataAdmissao.getValue());
            medico.setEmail(textFieldEmail.getText());
            medico.setTelefone(textFieldTelefone.getText());
            buttonConfirmarClicked = true;
            dialogStage.close();   
        }
    }
    
    public boolean validarEntradaDeDados(){
        String errorMessage = "";
        
        String validadorTelefone = textFieldTelefone.getText();
        validadorTelefone = validadorTelefone.replace(String.valueOf("("), "");
        validadorTelefone = validadorTelefone.replace(String.valueOf(")"), "");
        validadorTelefone = validadorTelefone.replace(String.valueOf("-"), "");
        
        if (textFieldNome.getText().length() == 0 || textFieldNome == null){
            errorMessage += "Campo \"Nome\" inválido!\n";
        }       
        
        if (textFieldCPF.getText().length() != 11 || textFieldCPF == null){
            errorMessage += "Campo \"CPF\" inválido!\n";
        } else if (!textFieldCPF.getText().matches("[0-9]+")){
            errorMessage += "Campo \"CPF\" inválido!\n";
        }     
        
        if (datePickerDataAdmissao.getValue() == null){
            errorMessage += "Campo \"Data\" inválido!\n";
        }
        
        if (textFieldEmail.getText().length() == 0 || textFieldEmail == null){
            errorMessage += "Campo \"E-Mail\" inválido!\n";
        }
        
        if (textFieldTelefone.getText().length() != 14 || textFieldTelefone == null){
            errorMessage += "Campo \"Telefone\" inválido!\n";
        } else if (textFieldTelefone.getText().charAt(0) != '(' || textFieldTelefone.getText().charAt(3) != ')' || textFieldTelefone.getText().charAt(9) != '-'){
            errorMessage += "Campo \"Telefone\" inválido!\n";
        } else if(!validadorTelefone.matches("[0-9]+")){
            errorMessage += "Campo \"Telefone\" inválido!\n";
        } 
        
        if(textFieldCrm.getText().length() != 8 || textFieldCrm == null){
            errorMessage += "Campo \"CRM\" inválido!\n";
        } 
        
        if (errorMessage.length() == 0){
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro do médico");
            alert.setHeaderText("Encontramos campos Inválidos, por favor verifique já.");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}
