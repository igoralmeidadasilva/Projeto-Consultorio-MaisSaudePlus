/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maissaudeplus.controller.funcionario;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import maissaudeplus.model.dao.ProcedimentoDAO;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;
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
    private JFXTextArea textAreaDescricaoProcedimento;
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


    //ObservableList para preencher a tableview
    private ObservableList<Procedimento> listaProcedimento;
    
    //Estabelecer conexão com o banco de dados postgres
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    //Lista de DAOs usados neste controller
    private final ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Configurando colunas da tabela
        tableColumnProcedimentoCodigo.setCellValueFactory(new PropertyValueFactory<>("codProcedimento"));
        tableColumnProcedimentoNome.setCellValueFactory(new PropertyValueFactory<>("nomeProcedimento"));
        //Carregando o table view
        loadTableView();
        //Adicionando o Listener do TableView
        listenerTableView();
    }

    //Método para carregar o table view
    private void loadTableView(){
        procedimentoDAO.setConnection(connection);
        listaProcedimento = FXCollections.observableArrayList(procedimentoDAO.listar());
        tableViewProcedimento.setItems(listaProcedimento);
    }
    
    //Listener para selecionar o objeto do table view em tempo real
    private void listenerTableView(){
        tableViewProcedimento.getSelectionModel().selectedItemProperty().addListener(
                (obervable, oldValue, newValue) -> selecionarItemsTableViewProcedimento(newValue));
    }
    //Metodo que seleciona as informações e as dispõe nos labels correspondentes
    private void selecionarItemsTableViewProcedimento(Procedimento procedimento){
        if(procedimento != null){
            labelProcedimentoNome.setText(procedimento.getNomeProcedimento());
            textAreaDescricaoProcedimento.setText(procedimento.getDescProcedimento());;
            labelProcedimentoValor.setText(Double.toString(procedimento.getValorProcedimento()));
            if (procedimento.isFlagObesidade() == true){
                labelProcediimentoRespostaObesidade.setText("Sim");
            } else {
                labelProcediimentoRespostaObesidade.setText("Não");
            }
        } else {
            labelProcedimentoNome.setText("");
            textAreaDescricaoProcedimento.setText("");
            labelProcedimentoValor.setText("");
            labelProcediimentoRespostaObesidade.setText("");
        }
    } 
    
    //Método responsável pela inserção no banco de dados de um novo paciente, os dados são inseridos em um dialog
    @FXML
    public void handleButtonInserir() throws IOException {
        //Instancia de um novo paciente vazio
        Procedimento procedimento = new Procedimento();
        //If que só é executado caso a botão "Confirmar" do dialog tiver sido pressionado.
        if(loadAnchorPaneCadastrarProcedimentoDialog(procedimento)){
            //Passando a conexão para a DAO
            procedimentoDAO.setConnection(connection);
            //Efetuando a inserção
            procedimentoDAO.inserir(procedimento);
            //Recarregando a table view com o novo paciente
            loadTableView();
            //Criação de um alerta de confirmação
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso");
            alert.setContentText("Procedimento Cadastrado com sucesso!!");
            alert.show();
        } 
    }
     
    //Método responsável pela alteração no banco de dados de um paciente já cadastrado, os dados são disponibilizados em um dialog
    @FXML
    public void handleButtonAlterar() throws IOException {
        //Recebendo o paciente selecionado na table view
        Procedimento procedimento = tableViewProcedimento.getSelectionModel().getSelectedItem();
        //If para verificar se o paciente foi realmente selecionado
        if(procedimento != null){
            //If que só é executado caso a botão "Confirmar" do dialog tiver sido pressionado.
            if(loadAnchorPaneAlterarProcedimentoDialog(procedimento)){
                //Passando a conexão para a DAO
                procedimentoDAO.setConnection(connection);
                //Efetuando a alteração
                procedimentoDAO.alterar(procedimento);
                //Recarregando a table view com os dados do paciente atualizados
                loadTableView();
                //Criação de um alerta de confirmação
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sucesso");
                alert.setContentText("Procedimento Alterado com sucesso!!");
                alert.show();
            }
        } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setContentText("Selecione um procedimento para ser alterado!!");
                alert.show();          
        }
    }

    //Método responsável pela remoção no banco de dados de um paciente, o paciente só é excluido após a confirmação em um dialog
    @FXML
    public void handleButtonRemover() throws IOException {
        //Recebendo o paciente selecionado na table view
        Procedimento procedimento = tableViewProcedimento.getSelectionModel().getSelectedItem();
        //If para verificar se o paciente foi realmente selecionado
        if (procedimento != null){
            if(loadAnchorPaneRemoverProcedimentoDialog()){
                //Passando a conexão para a DAO
                procedimentoDAO.setConnection(connection);
                //Efetuando a exclusõa
                procedimentoDAO.remover(procedimento);
                //Recarregando a table view sem o paciente
                loadTableView();
            }
        } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setContentText("Escolha um procedimento para ser removido!!");
                alert.show();
        }
    }
    
    //Método responsável pela abertura de uma nova janela dialog.fxml, retorna true caso o botão "Confirmar" seja clicado e false caso o botão "Cancelar" seja clicado
    public boolean loadAnchorPaneCadastrarProcedimentoDialog(Procedimento procedimento) throws IOException{
        //Instancia de um FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        //Passando a localização do .fxml para o FXMLLoader
        loader.setLocation(FXMLAnchorPaneFuncionarioCadastrarProcedimentoDialogController
                .class.getResource("/maissaudeplus/view/funcionario/FXMLAnchorPaneFuncionarioCadastrarProcedimentoDialog.fxml"));
        //Criando um objeto anchor pane para receber o .fxml
        AnchorPane page = (AnchorPane) loader.load();
        //Criando um novo stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastrar Procedimento");
        //Definindo que o novo stage não pode ser maximilizado
        dialogStage.setResizable(false);
        //Instancia de uma nova cena passando o anchor pane
        Scene scene = new Scene(page);
        //Passando a cena para o stage
        dialogStage.setScene(scene);
        //Carregando um Controller para o dialog
        FXMLAnchorPaneFuncionarioCadastrarProcedimentoDialogController controller = loader.getController();
        //Passagem de parâmetro do stage atual para o controller poder manipulá-lo
        controller.setDialogStage(dialogStage);
        //Passagem de parâmetro de uma instancia do paciente para o controller poder adicionar os dados a ele.
        controller.setProcedimento(procedimento);
        //Mostrando a tela
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }  
    
    //Método responsável pela abertura de uma nova janela dialog.fxml, retorna true caso o botão "Confirmar" seja clicado e false caso o botão "Cancelar" seja clicado
    public boolean loadAnchorPaneAlterarProcedimentoDialog(Procedimento procedimento) throws IOException{
        //Instancia de um FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        //Passando a localização do .fxml para o FXMLLoader
        loader.setLocation(FXMLAnchorPaneFuncionarioAlterarProcedimentoDialogController
                .class.getResource("/maissaudeplus/view/funcionario/FXMLAnchorPaneFuncionarioAlterarProcedimentoDialog.fxml"));
        //Criando um objeto anchor pane para receber o .fxml
        AnchorPane page = (AnchorPane) loader.load();
        //Criando um novo stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Alterar Procedimento");
        //Definindo que o novo stage não pode ser maximilizado
        dialogStage.setResizable(false);
        //Instancia de uma nova cena passando o anchor pane
        Scene scene = new Scene(page);
        //Passando a cena para o stage
        dialogStage.setScene(scene);
        //Carregando um Controller para o dialog
        FXMLAnchorPaneFuncionarioAlterarProcedimentoDialogController controller = loader.getController();
        //Passagem de parâmetro do stage atual para o controller poder manipulá-lo
        controller.setDialogStage(dialogStage);
        //Passagem de parametro de uma instancia do paciente para o controller poder adicionar os dados a ele.
        controller.setProcedimento(procedimento);
        //Mostrando a tela
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }

    //Método responsável pela abertura de uma nova janela dialog.fxml, retorna true caso o botão "Confirmar" seja clicado e false caso o botão "Cancelar" seja clicado
    public boolean loadAnchorPaneRemoverProcedimentoDialog() throws IOException{
        //Instancia de um FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        //Passando a localização do .fxml para o FXMLLoader
        loader.setLocation(FXMLAnchorPaneFuncionarioRemoverProcedimentoDialogController
               .class.getResource("/maissaudeplus/view/funcionario/FXMLAnchorPaneFuncionarioRemoverProcedimentoDialog.fxml"));
        //Criando um objeto anchor pane para receber o .fxml
        AnchorPane page = (AnchorPane) loader.load();
        //Criando um novo stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Remover Procedimento");
        //Definindo que o novo stage não pode ser maximilizado
        dialogStage.setResizable(false);
        //Instancia de uma nova cena passando o anchor pane
        Scene scene = new Scene(page);
        //Passando a cena para o stage
        dialogStage.setScene(scene);
        //Carregando um Controller para o dialog
        FXMLAnchorPaneFuncionarioRemoverProcedimentoDialogController controller = loader.getController();
        //Passagem de parâmetro do stage atual para o controller poder manipulá-lo
        controller.setDialogStage(dialogStage);
        //Mostrando a tela
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }
}
