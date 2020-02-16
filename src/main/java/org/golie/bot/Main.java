package org.golie.bot;

import org.golie.bot.logic.commands.ICommand;
import org.golie.bot.logic.commands.ImageCommand;
import org.golie.bot.logic.connectors.Connector;
import org.golie.bot.logic.connectors.IConnector;
import org.golie.bot.messenger.Bot;
import org.golie.bot.messenger.vk.VkBot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Properties;

public class Main {

    private static final String PROPERTIES_FILE = "config.properties";

    /**
     * Entry point of the project
     * Initialize properties
     * Initialize connections
     * Initialize bots
     *
     * @param args
     */
    public static void main(String[] args) {

        // properties file from resources
        Properties properties = readProperties();

        // connections to possible sources of data
        // get instance of the Connector to connect to all
        // possible sources
        IConnector connector = new Connector(properties);

        // need just to call ctor and all magic will happen
        Bot vk = new VkBot(properties,
                connector,
                Map.ofEntries(
                        new AbstractMap.SimpleEntry<String, ICommand>(
                                properties.getProperty("system_reserved_image_command"),
                                new ImageCommand()
                        )
                )
        );
    }

    private static Properties readProperties() {
        InputStream inputStream = Bot.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
        try (inputStream) {
            if (inputStream == null) {
                throw new FileNotFoundException("property file '" + PROPERTIES_FILE + "' not found in the classpath");
            }
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Incorrect properties file");
        }
    }

}
