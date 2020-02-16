package org.golie.bot.messenger.vk;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.MessageAttachment;
import com.vk.api.sdk.objects.messages.MessageAttachmentType;
import com.vk.api.sdk.objects.photos.PhotoSizes;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import org.apache.commons.lang3.ArrayUtils;
import org.golie.bot.logic.commands.ICommand;
import org.golie.bot.logic.connectors.IConnector;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class VkCallbackHandler extends CallbackApiLongPoll {

    private IConnector connector;
    private Map<String, ICommand> commands;
    private Properties properties;
    private VkApiClient client;
    private GroupActor actor;

    public VkCallbackHandler(Properties properties,
                             VkApiClient client,
                             GroupActor actor,
                             IConnector connector,
                             Map<String, ICommand> commands) {
        super(client, actor);
        this.client = client;
        this.actor = actor;
        this.connector = connector;
        this.commands = commands;
        this.properties = properties;
    }

    public void messageNew(Integer groupId, Message message) {

        String imageTrigger = properties.getProperty("system_reserved_image_command");

        if (!message.getAttachments().isEmpty() &&
                commands.containsKey(imageTrigger))
        {
            for (MessageAttachment attachment : message.getAttachments())
            {
                if (attachment.getType() == MessageAttachmentType.PHOTO)
                {
                    List<PhotoSizes> sizes = attachment.getPhoto().getSizes();

                    String args = sizes.get(sizes.size() - 1).getUrl().toString() + " " +
                            getSenderName(message.getFromId()) + " " +
                            message.getDate().toString() + " " +
                            attachment.getPhoto().getText() + " " +
                            message.getText();
                    commands.get(imageTrigger).execute(args.split(" "), connector);
                }
            }
        }
    }

    private String getSenderName(Integer id) {
        try {
            List<UserXtrCounters> user = getClient().users().get(actor).userIds(id.toString()).execute();
            return user.get(0).getFirstName() + " " + user.get(0).getLastName();
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
        return "";
    }

}
