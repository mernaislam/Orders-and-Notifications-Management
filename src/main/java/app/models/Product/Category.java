package app.models.Product;

public enum Category {
    CATEGORY1,
    CATEGORY2,
    CATEGORY3;

    public static Category getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
