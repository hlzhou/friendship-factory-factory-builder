package moments.app.database;

import moments.app.Type;

public class Data {
    private Type type;
    private String text;

    public Data(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    public Type getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
