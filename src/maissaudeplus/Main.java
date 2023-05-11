// Classe responsável por startar a aplicação
package maissaudeplus;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    
    private static Scene scene;
    public static Stage stage;
    private static Parent root;
    private static double x;
    private static double y;

    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("view/FXMLAnchorPaneSelecaoDePerfil.fxml")); 
        scene = new Scene(root, 900, 500 );
        
        stage.setTitle("Mais Saúde Plus");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        
        Image icon = new Image("/maissaudeplus/view/images/logo1.jpg");
        stage.getIcons().add(icon);
        
        Main.stage = stage;
        
        moveScreamOnMouseClick();
        
        stage.show();
    }
    
    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        scene.setRoot(fxmlLoader.load()); 
    }
    
    public static void setPrimarySize(){
        stage.setWidth(1200);
        stage.setHeight(800);
    }
    
    public static void moveScreamOnMouseClick(){ 
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });       
    }
    
    public static void main(String[] args) throws IOException {  
        launch(args);
    }  
}
