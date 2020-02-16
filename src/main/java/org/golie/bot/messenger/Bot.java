package org.golie.bot.messenger;

import org.golie.bot.logic.commands.ICommand;
import org.golie.bot.logic.connectors.IConnector;

import java.util.Map;

public class Bot {

    protected String token;
    protected String id;
    protected IConnector connector;
    protected Map<String, ICommand> commands;

    public Bot(IConnector connector, Map<String, ICommand> commands)
    {
        this.connector = connector;
        this.commands = commands;
    }

}
