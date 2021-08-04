package me.translate.mod.Commands;

import me.translate.mod.Translate;
import me.translate.mod.utils.TranslationUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class SendMessage extends CommandBase {

    private final Translate translate;

    private final TranslationUtil tu;

    public SendMessage(Translate t, TranslationUtil tu){
        this.translate = t;
        this.tu = tu;
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public String getCommandName() {
        return "tr";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/tr";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 1 || translate.isEnabled()) {
            showSyntaxError(sender);
            return;
        }

        StringBuilder sb = new StringBuilder();

        for(String s:args){
            sb.append(s+" ");
        }

        String msg = sb.toString();

        translate.getMC().thePlayer.sendChatMessage(tu.getTranslation(translate.getToLang(), msg));

    }

    private void showMessage(String message, ICommandSender sender) {
        sender.addChatMessage(new ChatComponentText(message));
    }

    private void showSyntaxError(ICommandSender sender) {
        showMessage(EnumChatFormatting.RED + "Usage: " + getCommandUsage(sender) + " or you have not enabled the mod with /gt enable", sender);
    }

}
