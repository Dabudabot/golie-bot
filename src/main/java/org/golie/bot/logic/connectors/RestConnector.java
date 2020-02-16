package org.golie.bot.logic.connectors;

public class RestConnector implements IConnector {

    private boolean isConnected;

    public RestConnector(String path, String token)
    {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public int sendImage(String author, String url, String[] tags) {
        return 0;
    }

    @Override
    public boolean reconnect() {
        return false;
    }
}
