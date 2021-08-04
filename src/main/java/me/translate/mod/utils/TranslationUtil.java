package me.translate.mod.utils;

import com.jaunt.component.*;
import com.jaunt.*;

public class TranslationUtil {

    private final UserAgent userAgent = new UserAgent();

    private final String URL = "https://translate.google.com/m?sl=auto&tl=fr&hl=en-US&q=";

    public TranslationUtil(){
        try{
            userAgent.visit(URL);
        }catch (ResponseException e){
            return;
        }
    }

    private void toTranslate(String txt){
        try{
            this.userAgent.apply(txt);
        }catch (Exception e){
            return;
        }
    }

    private void setLang(String l){
        Form form = this.userAgent.doc.getForm(0);

        try{
            form.set("tl",l);
        }catch (NotFound e){
            return;
        }
    }

    private void submit(){
        try{
            this.userAgent.doc.submit("Translate");
        }catch (SearchException e){
            return;
        }catch (ResponseException e){
            return;
        }
    }

    public String getTranslation(String lang, String txt){
        String translation="";

        toTranslate(txt);
        setLang(lang);
        submit();

        try{
            translation = this.userAgent.doc.findFirst("<div class=result-container>").getTextContent();
        }catch (NotFound e){
            return translation;
        }
        return translation;
    }

}
