package sms;
// PROBLEMAS A DIA 16/5... NECESITO GUARDAR EN FICHERO LA RECETA, NOMBRE, ID Y CADA UNO DE LOS INGREDIENTES
// HACER FUNCION GUARDA INGREDIENTES
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/main/main.fxml"));
        primaryStage.setTitle("Store Management System");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
