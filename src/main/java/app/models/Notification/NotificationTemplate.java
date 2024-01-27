package app.models.Notification;

import app.models.Language.Language;
import app.service.OrderService;

import java.util.HashMap;

public abstract class NotificationTemplate {
    protected String text;
    protected Language language;
    protected HashMap<String, String> placeholders = new HashMap<>();
    protected OrderService orderService;

    public NotificationTemplate(Language language, OrderService orderService){
        this.language = language;
        this.orderService = orderService;
    }
    public final void createTemplate(){
        // Template method - Skeleton
        generateText();
        writeText();
        addPlaceholders();
        replacePlaceholders();
    }
    // generate text in default language (English) - abstract method
    public abstract void generateText();

    // translate the generated text to the chosen language - final method
    public final void writeText(){
        this.text = language.translateText(text);
    }

    // add the translated placeholders and their actual values to the placeholders hashmap - abstract method
    public abstract void addPlaceholders();

    // replace the placeholders in the translated text with their actual values - final method
    public final void replacePlaceholders(){
        for (String key : placeholders.keySet()) {
            text = text.replace(key, placeholders.get(key));
        }
    }

    public String getText() {
        return text;
    }
}
