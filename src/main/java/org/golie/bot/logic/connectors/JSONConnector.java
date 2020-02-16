package org.golie.bot.logic.connectors;

import org.golie.bot.logic.objects.Image;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JSONConnector implements IConnector {

    private boolean isConnected;
    private Set<Image> images;

    public JSONConnector(String path)
    {
        images = new HashSet<>();
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader(path));

            JSONObject jsonObject = (JSONObject) object;

            // todo read replies


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


        isConnected = true;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public int sendImage(Image image) {
        images.add(image);
        return 0;
    }

    @Override
    public boolean reconnect() {
        return isConnected;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void cleanImages() {
        if (!images.isEmpty()) images.clear();
    }
}
