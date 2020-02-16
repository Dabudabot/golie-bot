package org.golie.bot.logic.connectors;

import org.golie.bot.logic.objects.Image;

import java.util.Set;

public class RestConnector implements IConnector {

    private boolean isConnected;

    public RestConnector(String path, String token)
    {
        isConnected = false;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public int sendImage(Image image) {
        return 0;
    }

    @Override
    public boolean reconnect() {
        return isConnected;
    }
}
