package org.golie.bot.logic.commands;

import org.golie.bot.logic.connectors.Connector;
import org.golie.bot.logic.connectors.IConnector;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ImageCommand implements ICommand {

    @Override
    public String execute(String[] args, IConnector connector) {

        Set<String> tags = new HashSet<>();
        tags.addAll(Arrays.asList(args).subList(1, args.length));

        if (connector.sendImage(args[0], tags) != 0)
        {
            // todo log error
        }

        return null;
    }
}
