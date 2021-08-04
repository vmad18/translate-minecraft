package me.translate.mod;


import me.translate.mod.Commands.SendMessage;
import me.translate.mod.Commands.TranslateCommand;
import me.translate.mod.Event.ChatEvent;
import me.translate.mod.utils.Threading;
import me.translate.mod.utils.TranslationUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Mod(modid = "MCGTranslate", version = "0.1", clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
public class Translate {

    public static final String MODID = "MCGTranslate";

    public static final String VERSION = "0.1";

    private boolean enabled=false;

    private final Minecraft mc = Minecraft.getMinecraft();

    private final ExecutorService THREAD_POOL = Executors.newCachedThreadPool(new Threading());

    private final TranslationUtil tu = new TranslationUtil();

    private String lang = "en";

    private String toLang = "en";

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        MinecraftForge.EVENT_BUS.register(new ChatEvent(this,tu));
        ClientCommandHandler.instance.registerCommand(new SendMessage(this,tu));
        ClientCommandHandler.instance.registerCommand(new TranslateCommand(this));
    }

    public void submitThread(Runnable r){
        this.THREAD_POOL.submit(r);
    }

    /*
    -------------------
    Getters and Setters
    -------------------
     */

    public void setEnabled(boolean b){
        this.enabled = b;
    }

    public boolean isEnabled(){
        return this.enabled;
    }

    public void setLang(String s){
        this.lang = s;
    }

    public String getLang(){
        return this.lang;
    }

    public void setToLang(String s){
        this.toLang = s;
    }

    public String getToLang(){
        return this.toLang;
    }

    public Minecraft getMC(){
        return this.mc;
    }

}
