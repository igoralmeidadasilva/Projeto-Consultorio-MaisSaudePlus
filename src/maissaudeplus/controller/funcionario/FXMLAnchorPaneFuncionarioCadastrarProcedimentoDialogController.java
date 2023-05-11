// Classe que contém os métodos que fazem o cadastro do procedimento
// Raphael Pavani Manhães Bersot - 20211si017

package maissaudeplus.controller.funcionario;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import maissaudeplus.model.domain.Procedimento;

/**
 * FXML Controller class
 *
 * @author Raphael
 */
public class FXMLAnchorPaneFuncionarioCadastrarProcedimentoDialogController implements Initializable {

    //estão referenciando os elementos do Anchorpane
    @FXML
    private JFXTextField textFieldNomeProcedimento;
    @FXML
    private JFXTextField textFieldValorProcedimento;
    @FXML
    private CheckBox checkBoxProcedimento;
    @FXML
    private JFXTextArea textAreaDescricaoProcedimento;
    @FXML
    private JFXButton buttonConfirmarCadastroProcedimento;
    @FXML
    private JFXButton buttonCancelarCadastroProcedimento;

    
    
    
    //Atributos para a manipulação do Stage
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Procedimento procedimento;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }  
    
    //Método de manipulação do botão "Confirmar"
    @FXML
    void handleButtonConfirmar() {
        //If que só é executado caso todos os campos estejam no formato correto
        if(validarEntradaDeDados()){
            //Colocando os dados fornecidos na instância do paciente
            procedimento.setNomeProcedimento(textFieldNomeProcedimento.getText().toUpperCase());
            procedimento.setDescProcedimento(textAreaDescricaoProcedimento.getText().toUpperCase());
            procedimento.setValorProcedimento(Double.parseDouble(textFieldValorProcedimento.getText()));
            procedimento.setFlagObesidade(checkBoxProcedimento.isSelected());
            //Retorna verdadeiro sinalizando que o botão "Confirmar" foi pressionado
            buttonConfirmarClicked = true;
            //Fechando o dialog
            dialogStage.close();
        }
    }
        
    //Método de manipulação do botão "Cancelar"
    @FXML
    void handleButtonCancelar() {
        //Fechando o dialog, note que não a necessidade de informar que este botão foi pressionado já que a flag "buttonConfirmarClicked" foi declarada como falsa
        dialogStage.close();
    }
    
    //Getter e Setters dos atributos de manipulação do stage
    public Stage getDialogStage() {
        return dialogStage;
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }
    
    //Método que valida os dados inseridos nos campos, note que cada campo necessitou de um tratamento diferente.
    public boolean validarEntradaDeDados(){
        //Mensagem de erro declarada como uma String vazia, serve de flag para o método
        String errorMessage = "";
        //If para válidar se o campo nome não esta vazio
        if (textFieldNomeProcedimento.getText().length() == 0 || textFieldNomeProcedimento == null){
            errorMessage += "Campo \"Nome\" inválido!\n";
        }
        if (textAreaDescricaoProcedimento.getText().length() == 0 || textAreaDescricaoProcedimento == null){
            errorMessage += "Campo \"Nome\" inválido!\n";
        }       
        //If para válidar se o campo Peso não esta vazio e não contém letras
        if (textFieldValorProcedimento.getText().length() == 0 || textFieldValorProcedimento == null){
            errorMessage += "Campo \"Valor\" inválido!\n";
        } else {
            try{
                Double.parseDouble(textFieldValorProcedimento.getText());
            } catch (NumberFormatException e){
            errorMessage += "Campo \"Valor\" inválido!\n";
            }
        }
        //If para verificar se a String "errorMessage" esta vazia, caso ela esteja vazia, o metodo retorna true provando que os campos preenchidos estão corretos
        if (errorMessage.length() == 0){
            //Retorno verdadeiro sinalizando que os campos estão corretos
            return true;
        } else {
            //Criação da mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro o procedimento");
            alert.setHeaderText("Encontramos campos Inválidos, por favor verifique já.");
            alert.setContentText(errorMessage);
            alert.show();
            //Retorno falso sinalizando que os campos estão errados
            return false;
        }
    }
}
