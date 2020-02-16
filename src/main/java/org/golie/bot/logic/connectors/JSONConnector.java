package org.golie.bot.logic.connectors;

public class JSONConnector implements IConnector {


    private boolean isConnected;

    public JSONConnector(String path)
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
        return isConnected;
    }
}
