package app.models.Language;

public class EnglishLanguage implements Language {
    @Override
    public String translateText(String text) {
        // default language is English, return text without translation
        return text;
    }
}
