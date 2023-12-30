package app.models.Notification;

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
    public abstract void generateText(); // generate text in default language - English
    public final void writeText(){
        this.text = language.translateText(text);
    }
    public abstract void addPlaceholders();
    public final void replacePlaceholders(){
        for (String key : placeholders.keySet()) {
            text = text.replace(key, placeholders.get(key));
        }
    }
    public String getText() {
        return text;
    }
}
