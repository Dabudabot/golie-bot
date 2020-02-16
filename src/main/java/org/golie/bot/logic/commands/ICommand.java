package org.golie.bot.logic.commands;

import org.golie.bot.logic.connectors.Connector;

public interface ICommand {

    String execute(int argv, String[] args, Connector connector);

}
