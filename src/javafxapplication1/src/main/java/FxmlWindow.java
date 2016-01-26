package javafxapplication1.src.main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FxmlWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("FXMLサンプル");

        // FXMLファイル読込
        BorderPane loader = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));

        // FXMLを設定
        Scene scene = new Scene(loader);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String... args) {
        launch(args);
    }
}