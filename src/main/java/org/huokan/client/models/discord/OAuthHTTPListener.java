package org.huokan.client.models.discord;

import io.javalin.Javalin;

import java.net.URI;
import java.net.URISyntaxException;

public class OAuthHTTPListener {
    // Multiple options for the port in case one is in use.
    private static final int[] PORTS = {10733, 33054, 52602};
    private static final String AUTHORIZATION_URL = "https://discord.com/api/oauth2/authorize?client_id=%s&redirect_uri=http%3A%2F%2Flocalhost%3A%d&response_type=code&scope=identify%20guilds";
    private String clientId = "546934940890169344";

    private Javalin server;
    private int port;

    public OAuthHTTPListener() {
        server = Javalin.create();
    }

    public void start() {
        startServer();
        server.get("/", ctx -> {
            ctx.result("<script>window.close();</script>");
            String code = ctx.queryParam("code");
            if (code != null) {
                // TODO trigger event
                server.stop();
            }
        });
    }

    private void startServer() throws RuntimeException {
        RuntimeException latestException = null;
        for (var port : PORTS) {
            try {
                server.start("localhost", port);
                this.port = port;
                return;
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                latestException = ex;
            }
        }
        throw latestException;
    }

    public URI getAuthorizationURI() throws URISyntaxException {
        return new URI(AUTHORIZATION_URL.formatted(clientId, port));
    }
}
