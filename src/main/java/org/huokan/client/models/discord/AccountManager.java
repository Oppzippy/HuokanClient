package org.huokan.client.models.discord;

import java.io.IOException;

public interface AccountManager {
    public void logIn(String token) throws IOException;
    public String getUserId();
    public String getUsername();
    public String getDiscriminator();
    public String getTag();
}
