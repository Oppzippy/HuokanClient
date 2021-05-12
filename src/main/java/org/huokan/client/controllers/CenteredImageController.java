package org.huokan.client.controllers;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CenteredImageController implements Initializable {
    @FXML
    private Pane container;

    @FXML
    private ImageView imageView;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        container.widthProperty().addListener(this::onResize);
        container.heightProperty().addListener(this::onResize);
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public void onResize(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        if (imageView.getImage() == null) {
            return;
        }
        imageView.setFitWidth(container.getWidth());
        imageView.setFitHeight(container.getHeight());

        var containerRatio = container.getWidth() / container.getHeight();
        adjustViewport(containerRatio);
    }

    private void adjustViewport(double aspectRatio) {
        var image = imageView.getImage();
        var imageRatio = image.getWidth() / image.getHeight();

        double width, height;
        if (aspectRatio > imageRatio) {
            width = image.getWidth();
            height = width / aspectRatio;
        } else {
            height = image.getHeight();
            width = height * aspectRatio;
        }

        var xOffset = (image.getWidth() - width) / 2;
        var yOffset = (image.getHeight() - height) / 2;

        var viewport = new Rectangle2D(xOffset, yOffset, width, height);
        imageView.setViewport(viewport);
    }
}
