package org.huokan.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HuokanClient extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Huokan Boosting Community Client");
        var loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("views/Main.fxml"));
        var root = loader.<Parent>load(
                this.getClass().getResourceAsStream("views/Main.fxml")
        );
        var scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
