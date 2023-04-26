package maissaudeplus;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLAnchorPaneSelecaoDePerfil.fxml")); 
        scene = new Scene(root, 900, 490 );
        stage.setTitle("Mais Saúde Plus");
        stage.setScene(scene);
        stage.setResizable(false);
        Main.stage = stage;
        stage.show();
    }
    
    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        scene.setRoot(fxmlLoader.load()); 
    }
    
    public static void setResizableTrue(){
        if(stage != null){ 
            stage.setResizable(true);
        }
    }
    
    public static void setPrimarySize(){
        stage.setWidth(1100);
        stage.setHeight(700);
    }
    
    public static void main(String[] args) throws IOException {  
        launch(args);
    }
}
