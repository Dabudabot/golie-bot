package org.golie.bot.messenger.vk;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.golie.bot.logic.commands.ICommand;
import org.golie.bot.logic.connectors.IConnector;
import org.golie.bot.messenger.Bot;

import java.util.Map;
import java.util.Properties;

/**
 * Group VK bot
 * to start using it
 * follow the instruction:
 * https://vk.com/dev/bots_docs
 */
public class VkBot extends Bot {

    /**
     *  representation of bot
     */
    private GroupActor actor;
    /**
     * representation of service
     */
    private VkApiClient client;
    /**
     * handles events
     */
    private VkCallbackHandler handler;

    /**
     * ctor to start the bot
     *
     * @param properties must contain vk_token and vk_group_id
     * @param connector connection to data sources
     * @param commands triggers and command for this bot
     */
    public VkBot(Properties properties, IConnector connector, Map<String, ICommand> commands)
    {
        super(connector, commands);

        // get properties
        token = properties.getProperty("vk_token");
        id = properties.getProperty("vk_group_id");

        // setup bot
        actor = new GroupActor(Integer.parseInt(id), token);

        // connect to service
        HttpTransportClient httpClient = HttpTransportClient.getInstance();
        client = new VkApiClient(httpClient);

        try {
            // check long poll events and subscribe
            if (!client.groups().getLongPollSettings(actor, actor.getGroupId()).execute().getIsEnabled()) {
                client.groups().setLongPollSettings(actor, actor.getGroupId()).enabled(true).wallPostNew(true).execute();
            }

            // start processing
            handler = new VkCallbackHandler(properties, client, actor, connector, commands);
            handler.run();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
    }
}
