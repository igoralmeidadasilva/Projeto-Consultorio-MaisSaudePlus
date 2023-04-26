package maissaudeplus.controller.funcionario;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import maissaudeplus.model.domain.Paciente;

/**
 * FXML Controller class
 * Dialog responsável pela alteração dos pacientes
 */
public class FXMLAnchorPaneFuncionarioAlterarPacienteDialogController implements Initializable {
    
    @FXML
    private JFXTextField textFieldNome;

    @FXML
    private JFXTextField textFieldCPF;

    @FXML
    private JFXTextField textFieldPeso;

    @FXML
    private JFXTextField textFieldAltura;

    @FXML
    private JFXTextField textFieldEmail;

    @FXML
    private JFXTextField textFieldTelefone;

    @FXML
    private JFXComboBox<String> comboBoxSexo;

    @FXML
    private JFXDatePicker datePickerDataNascimento;

    @FXML
    private JFXButton buttonConfirmar;

    @FXML
    private JFXButton buttonCancelar;
    
    
    //Atributos para a manipulação do Stage
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Paciente paciente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Carregando o comboBox
        loadComboBox();
    }   
    


    //Método de manipulação do botão "Confirmar"
    @FXML
    void handleButtonConfirmar() {
        //If que só é executado caso todos os campos estejam no formato correto
        if(validarEntradaDeDados()){
            paciente.setNome(textFieldNome.getText().toUpperCase());
            paciente.setCpf(textFieldCPF.getText());
            paciente.setDataNascimento(datePickerDataNascimento.getValue());
            paciente.setSexo(comboBoxSexo.getValue().charAt(0));
            paciente.setPeso(Double.parseDouble(textFieldPeso.getText()));
            paciente.setAltura(Double.parseDouble(textFieldAltura.getText()));
            paciente.setEmail(textFieldEmail.getText());
            paciente.setTelefone(textFieldTelefone.getText());
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
        
    //Método para carregar o ComboBox
    public void loadComboBox(){
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("M - Masculino");
        list.add("F - Feminino");
        comboBoxSexo.setItems(list);
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

    public Paciente getPaciente() {
        return paciente;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        this.textFieldNome.setText(paciente.getNome());
        this.textFieldCPF.setText(paciente.getCpf());
        this.textFieldPeso.setText(String.valueOf(paciente.getPeso()));
        this.textFieldAltura.setText(String.valueOf(paciente.getAltura()));
        this.textFieldEmail.setText(paciente.getEmail());
        this.textFieldTelefone.setText(paciente.getTelefone());
        if(paciente.getSexo() == 'M'){
            this.comboBoxSexo.setValue("M - Masculino");
        } else {
            this.comboBoxSexo.setValue("F - Feminino");
        }
        this.datePickerDataNascimento.setValue(paciente.getDataNascimentoToLocalDate());
    } 
    
    //Método que valida os dados inseridos nos campos, note que cada campo necessitou de um tratamento diferente.
    public boolean validarEntradaDeDados(){
        //Mensagem de erro declarada como uma String vazia, serve de flag para o método
        String errorMessage = "";
        //Variaveis necessarias para a validação do telefone
        String validadorTelefone = textFieldTelefone.getText();
        validadorTelefone = validadorTelefone.replace(String.valueOf("("), "");
        validadorTelefone = validadorTelefone.replace(String.valueOf(")"), "");
        validadorTelefone = validadorTelefone.replace(String.valueOf("-"), "");
        //If para válidar se o campo nome não esta vazio
        if (textFieldNome.getText().length() == 0 || textFieldNome == null){
            errorMessage += "Campo \"Nome\" inválido!\n";
        }
        //If para válidar se o campo cpf não está vazio e não contem letras
        if (textFieldCPF.getText().length() != 11 || textFieldCPF == null){
            errorMessage += "Campo \"CPF\" inválido!\n";
        } else if (!textFieldCPF.getText().matches("[0-9]+")){
            errorMessage += "Campo \"CPF\" inválido!\n";
        }
        //If para válidar se o campo Data de Nascimento não esta vazio
        if (datePickerDataNascimento.getValue() == null){
            errorMessage += "Campo \"Data\" inválido!\n";
        }
        //If para válidar se o campo Sexo não esta vazio
        if (comboBoxSexo.getValue() == null){
            errorMessage += "Campo \"Sexo\" inválido!\n";
        }
        //If para válidar se o campo Peso não esta vazio e não contém letras
        if (textFieldPeso.getText().length() == 0 || textFieldPeso == null){
            errorMessage += "Campo \"Peso\" inválido!\n";
        } else {
            try{
                Double.parseDouble(textFieldPeso.getText());
            } catch (NumberFormatException e){
            errorMessage += "Campo \"Peso\" inválido!\n";
            }
        }
        //If para válidar se o campo Altura não esta vazio e não contém letras
        if (textFieldAltura.getText().length() == 0 || textFieldAltura == null){
            errorMessage += "Campo \"Altura\" inválido!\n";
        } else {
            try{
                Double.parseDouble(textFieldAltura.getText());
            } catch (NumberFormatException e){
            errorMessage += "Campo \"Altura\" inválido!!\n";
            }
        }
        //If para válidar se o campo E-mail não esta vazio
        if (textFieldEmail.getText().length() == 0 || textFieldEmail == null){
            errorMessage += "Campo \"E-Mail\" inválido!\n";
        }
        //If para validar se o campo telefone esta na formatação correta e não contem letras
        if (textFieldTelefone.getText().length() != 14 || textFieldTelefone == null){
            errorMessage += "Campo \"Telefone\" inválido!\n";
        } else if (textFieldTelefone.getText().charAt(0) != '(' || textFieldTelefone.getText().charAt(3) != ')' || textFieldTelefone.getText().charAt(9) != '-'){
            errorMessage += "Campo \"Telefone\" inválido!\n";
        } else if(!validadorTelefone.matches("[0-9]+")){
            errorMessage += "Campo \"Telefone\" inválido!\n";
        } 
        //If para verificar se a String "errorMessage" esta vazia, caso ela esteja vazia, o metodo retorna true provando que os campos preenchidos estão corretos        
        if (errorMessage.length() == 0){
            //Retorno verdadeiro sinalizando que os campos estão corretos
            return true;
        } else {
            //Criação da mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro para alterar o paciente");
            alert.setHeaderText("Encontramos campos Inválidos, por favor verifique já.");
            alert.setContentText(errorMessage);
            alert.show();
            //Retorno falso sinalizando que os campos estão errados
            return false;
        }
    }
}
