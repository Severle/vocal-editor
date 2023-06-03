package org.severle.text;

public class Text {
    public static String translate(String key) {
        return Language.getInstance().getValue(key);
    }
}
