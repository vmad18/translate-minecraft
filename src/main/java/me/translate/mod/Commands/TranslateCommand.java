package me.translate.mod.Commands;

import me.translate.mod.Translate;
import me.translate.mod.utils.TranslationUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class TranslateCommand extends CommandBase {

    private final Translate translate;


    public TranslateCommand(Translate t){
        this.translate = t;
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public String getCommandName() {
        return "gt";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/gt";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if(args.length > 3){
            showSyntaxError(sender);
            return;
        }

        try{
            switch (args[0].toLowerCase()) {
                case(" "):
                    translate.setEnabled(!translate.isEnabled());
                    showMessage(EnumChatFormatting.AQUA + "GTranslate: " + (translate.isEnabled() ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled"), sender);
                    break;
                case("help"):
                    showHelpMessage(sender);
                    break;
                case("lang"):
                    try {
                        translate.setLang(args[1]);
                        showMessage(EnumChatFormatting.AQUA + "GTranslate: " + EnumChatFormatting.GOLD + "Selected Language: " + args[1] , sender);
                    }catch (Exception e){
                        showHelpMessage(sender);
                    }
                    break;
                case("tolang"):
                    try{
                        translate.setToLang(args[1]);
                        showMessage(EnumChatFormatting.AQUA + "GTranslate: " + EnumChatFormatting.GOLD + "Selected To Language: " + args[1] , sender);
                    }catch (Exception e){
                        showHelpMessage(sender);
                    }
                    break;
            }
        }catch (Exception e){
            translate.setEnabled(!translate.isEnabled());
            showMessage(EnumChatFormatting.AQUA + "GTranslate: " + (translate.isEnabled() ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled"), sender);
        }

    }


    private void showMessage(String message, ICommandSender sender) {
        sender.addChatMessage(new ChatComponentText(message));
    }

    private void showSyntaxError(ICommandSender sender) {
        showMessage(EnumChatFormatting.RED + "Usage: " + getCommandUsage(sender), sender);
    }

    private void showHelpMessage(ICommandSender sender){
        showMessage(EnumChatFormatting.GREEN + "Do /gt lang <language> to translate as specific language and /gt tolang <lang> to set the language you want to change.", sender);
    }

}
