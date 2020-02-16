package org.golie.bot.logic.connectors;

import java.util.Properties;

public class Connector implements IConnector {

    private JSONConnector jsonConnector;
    private RestConnector restConnector;
    private boolean connected;

    public Connector(Properties properties)
    {
        connected = false;
        jsonConnector = new JSONConnector(properties.getProperty("json_file"));
        restConnector = new RestConnector(
                properties.getProperty("back_url"),
                properties.getProperty("back_token")
        );

        reconnect();
    }

    @Override
    public int sendImage(String author, String url, String[] tags)
    {
        if (restConnector.isConnected())
        {
            return restConnector.sendImage(author, url, tags);
        }

        if (jsonConnector.isConnected())
        {
            return jsonConnector.sendImage(author, url, tags);
        }

        return -1;
    }

    @Override
    public boolean reconnect()
    {
        // todo check connectivity in the loop in separate thread

        while (true)
        {
            jsonConnector.reconnect();
            restConnector.reconnect();
        }
    }

    @Override
    public boolean isConnected() {
        return connected;
    }
}
