package project;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Sudarshan Krishnan, kriss03
 * 
 * Integrated by Sudarshan Krishnan, Amit Jadhav and Sanjana Gondariya
 * Documented by Sudarshan Krishnan
 * 
 * This is the Group submission. 
 */

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"));
        stage.setTitle("Project 3 Mini Paint");
        stage.setScene(scene);
        stage.show();
        stage.requestFocus();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
