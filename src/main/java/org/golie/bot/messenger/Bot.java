package org.golie.bot.messenger;

import org.golie.bot.logic.commands.ICommand;
import org.golie.bot.logic.connectors.IConnector;

import java.util.Map;

/**
 * Abstract class that holds fields of
 * the bot that are the same to any source
 */
public class Bot {

    /**
     * token for the messenger to connect
     */
    protected String token;
    /**
     * identification inside messenger
     */
    protected String id;
    /**
     * connection to the source of data
     */
    protected IConnector connector;
    /**
     * trigger and command to execute
     */
    protected Map<String, ICommand> commands;

    public Bot(IConnector connector, Map<String, ICommand> commands)
    {
        this.connector = connector;
        this.commands = commands;
    }

}
