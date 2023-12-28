package app.models.Notification;

public class EnglishLanguage implements Language {
    @Override
    public String translateText(String text) {
        return text;
    }
}
