package app.models.Product;

public enum Category {
    FRUITS,
    SNACKS,
    DRINKS;

    public static Category getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
