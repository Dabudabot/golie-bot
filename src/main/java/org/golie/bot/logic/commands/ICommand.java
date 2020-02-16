package org.golie.bot.logic.commands;

import org.golie.bot.logic.connectors.IConnector;

public interface ICommand {

    String execute(String[] args, IConnector connector);

}
