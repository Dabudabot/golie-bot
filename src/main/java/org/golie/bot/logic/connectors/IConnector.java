package org.golie.bot.logic.connectors;

public interface IConnector {

    boolean isConnected();
    int sendImage(String author, String url, String[] tags);
    boolean reconnect();

}
