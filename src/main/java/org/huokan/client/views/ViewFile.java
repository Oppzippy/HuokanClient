package org.huokan.client.views;

import java.io.InputStream;
import java.net.URL;

public enum ViewFile {
    MAIN("/org/huokan/client/views/Main.fxml"),
    LOGIN("/org/huokan/client/views/Login.fxml"),
    MAIN_MENU("/org/huokan/client/views/MainMenu.fxml"),
    CENTERED_IMAGE("/org/huokan/client/views/partials/CenteredImage.fxml"),
    CONTENT_SWITCHER("/org/huokan/client/views/partials/ContentSwitcher.fxml"),
    BOOST_REQUEST("/org/huokan/client/views/BoostRequest.fxml"),
    RUN_SUBMISSION("/org/huokan/client/views/RunSubmission.fxml"),
    MYTHIC_PLUS("/org/huokan/client/views/partials/boostrequest/MythicPlus.fxml");

    private String path;

    private ViewFile(String path) {
        this.path = path;
    }

    public InputStream getInputStream() {
        return getClass().getResourceAsStream(path);
    }

    public URL getURL() {
        return getClass().getResource(path);
    }
}
