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

public class VkBot extends Bot {

    private GroupActor actor;
    private VkApiClient client;
    private VkCallbackHandler handler;

    public VkBot(Properties properties, IConnector connector, Map<String, ICommand> commands)
    {
        super(connector, commands);
        token = properties.getProperty("vk_token");
        id = properties.getProperty("vk_group_id");

        actor = new GroupActor(Integer.parseInt(id), token);
        HttpTransportClient httpClient = HttpTransportClient.getInstance();
        client = new VkApiClient(httpClient);

        try {
            if (!client.groups().getLongPollSettings(actor, actor.getGroupId()).execute().getIsEnabled()) {
                client.groups().setLongPollSettings(actor, actor.getGroupId()).enabled(true).wallPostNew(true).execute();
            }

            handler = new VkCallbackHandler(client, actor);
            handler.run();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
    }
}
