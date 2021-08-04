package me.translate.mod.Event;

import me.translate.mod.Translate;
import me.translate.mod.utils.TranslationUtil;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatEvent {

    private final Translate translate;

    private final TranslationUtil translationUtil;

    public ChatEvent(Translate tr, TranslationUtil tu){
        this.translate = tr;
        this.translationUtil = tu;
    }

    @SubscribeEvent
    public void onChatReceive(ClientChatReceivedEvent e){
        if(!translate.isEnabled()) return;

        String formattedText = e.message.getFormattedText();
        String[] splitFormatted = formattedText.split(" ");

        String msg = e.message.getUnformattedText();
        String[] split = msg.split(" ");

        StringBuilder sb = new StringBuilder();

        for(int i=2;i<split.length;i++){
            sb.append(split[i]+" ");
        }

        String newString = sb.toString();

        translationUtil.getTranslation(newString, translate.getLang());

        translate.getMC().thePlayer.addChatMessage(new ChatComponentText(splitFormatted[0] + " " + splitFormatted[1] + " " + newString));
        e.setCanceled(true);
    }
}
