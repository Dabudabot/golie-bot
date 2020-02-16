package org.golie.bot.logic.connectors;

import org.golie.bot.logic.objects.Image;

import java.util.Properties;

public class Connector implements IConnector {

    private JSONConnector jsonConnector;
    private RestConnector restConnector;
    private boolean connected;

    public Connector(Properties properties)
    {
        jsonConnector = new JSONConnector(properties.getProperty("json_file"));
        restConnector = new RestConnector(
                properties.getProperty("back_url"),
                properties.getProperty("back_token")
        );

        reconnect();
        connected = true;
    }

    @Override
    public int sendImage(Image image) {

        if (restConnector.isConnected())
        {
            if (jsonConnector.isConnected()) {
                for (Image oldImage : jsonConnector.getImages()) {
                    restConnector.sendImage(oldImage);
                }
                jsonConnector.cleanImages();
            }
            return restConnector.sendImage(image);
        }

        if (jsonConnector.isConnected())
        {
            return jsonConnector.sendImage(image);
        }

        return -1;
    }

    @Override
    public boolean reconnect()
    {
        Thread thread = new Thread(() -> {
            while (true) {
                jsonConnector.reconnect();
                restConnector.reconnect();
            }
        });

        thread.start();

        return true;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }
}
