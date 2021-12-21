package org.huokan.client.controllers;

import javafx.fxml.Initializable;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void openBrowser() {
        try {
            Desktop.getDesktop().browse(new URI("https://discord.com/api/oauth2/authorize?client_id=546934940890169344&redirect_uri=http%3A%2F%2Flocalhost&response_type=code&scope=identify%20guilds"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
