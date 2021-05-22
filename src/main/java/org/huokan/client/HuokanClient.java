package org.huokan.client;

import com.google.inject.Guice;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.huokan.client.views.ViewFile;

import java.io.IOException;

public class HuokanClient extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        var injector = Guice.createInjector(new BasicModule());
        primaryStage.setTitle("Huokan Boosting Community Client");
        var fxmlCache = injector.getInstance(FXMLCache.class);
        fxmlCache.setControllerFactory(injector::getInstance);
        var root = (Parent) fxmlCache.getView(ViewFile.MAIN);
        var scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
