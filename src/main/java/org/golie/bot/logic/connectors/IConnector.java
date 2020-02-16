package org.golie.bot.logic.connectors;

import org.golie.bot.logic.objects.Image;

import java.util.Set;

public interface IConnector {

    boolean isConnected();
    int sendImage(Image image);
    default int sendImage(String url, Set<String> tags)
    {
        Image image = new Image(url, tags);
        return sendImage(image);
    }
    boolean reconnect();

}
