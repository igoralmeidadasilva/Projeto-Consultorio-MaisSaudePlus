// Classe que contém os métodos que fazem a configuração para exibição do ancorpane 
// Classe que contém os métodos responsáveis para enviar um email ao paciente que teve mais de 5 consultas com procedimentos marcados como true na flag obesidade
// Raphael Pavani Manhães Bersot - 20211si017
package maissaudeplus.controller.medico;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import maissaudeplus.model.domain.Paciente;
import java.util.*;
import javax.mail.Address;
import maissaudeplus.model.dao.NotificacaoDAO;
import maissaudeplus.model.database.Database;
import maissaudeplus.model.database.DatabaseFactory;
import maissaudeplus.model.domain.Notificacao;

public class FXMLAnchorPaneMedicoNotificarPacienteController implements Initializable {

    @FXML
    private JFXButton buttonConfirmarRemoverProcedimento;
    @FXML
    private JFXButton buttonCancelarRemoverProcedimento;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Paciente paciente;
    private NotificacaoDAO notificacaoDao = new NotificacaoDAO();
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    void handleButtonCancelar() {
        dialogStage.close();
    }

    @FXML
    void handleButtonConfirmar() throws Exception {
        buttonConfirmarClicked = true;
        
        notificacaoDao.setConnection(connection);
        
        NotificarPaciente(paciente);
        Notificacao notificacao = new Notificacao();
        LocalDate data = LocalDate.now();
        notificacao.setPaciente(paciente);
        notificacao.setDataNotificacao(data);
        notificacaoDao.inserir(notificacao);
        
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    // Método que realiza o nevio de email
    public void NotificarPaciente(Paciente paciente) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        javax.mail.Authenticator auth = new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                // Login email: contato.maissaudeplus@gmail.com
                // Senha email: @ifes2023
                // Senha de app: ikktsgakmfpmxldi
                return new javax.mail.PasswordAuthentication("contato.maissaudeplus@gmail.com", "ikktsgakmfpmxldi");
            }
        };
        Session session = Session.getInstance(props, auth);

        try{
            javax.mail.Message message = new MimeMessage(session);
            Address[] destinatarios = InternetAddress.parse(paciente.getEmail());
            message.setFrom(new InternetAddress("contato.maissaudeplus@gmail.com"));
            message.setRecipients(javax.mail.Message.RecipientType.TO, destinatarios);
            message.setSubject("Alerta de saúde");
            message.setText("Prezado(a) " + paciente.getNome() +
                            "\nTemos a honra de convidá-lo (a) para participar dos progamas de saúde e bem estar " 
                          + "Oferecidos pela clínica Mais Saúde Plus!\n. "
                          + "Público-alvo:\n" +
                            "Clientes da clínica Mais Saúde Plus;\n" +
                            "Temas de desataque:\n" +
                            "Sobrepeso e obesidade, saúde pública e prevenção, exercício físico e controle do peso corporal;\n" +
                            "Agradecemos desde já sua atenção e valiosa participação, e permanecemos à disposição para quaisquer outras dúvidas.\n" +
                            "Contato, dúvidas e sugestões:\n" +
                            "contato.maissaudeplus@gmail.com");

            Transport.send(message);
        
        } catch (javax.mail.MessagingException e){
            System.err.println("O E-mail não existe!");
        }
    }
    
}