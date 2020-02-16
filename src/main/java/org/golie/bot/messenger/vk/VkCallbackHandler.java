package org.golie.bot.messenger.vk;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.Message;

public class VkCallbackHandler extends CallbackApiLongPoll {

    public VkCallbackHandler(VkApiClient client, GroupActor actor) {
        super(client, actor);
    }

    public void messageNew(Integer groupId, Message message) {
        // TODO handle
    }

}
