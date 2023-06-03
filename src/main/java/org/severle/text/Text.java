package org.severle.text;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Text {
    public static String translate(String key) {
        return Language.getInstance().getValue(key);
    }
}
