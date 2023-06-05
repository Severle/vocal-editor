package org.severle.text;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Text {
    public static String translate(String key) {
        String s = Language.getInstance().getValue(key);
        return s != null ? s : key;
    }

    public static String translate(String key, Object... paras) {
        String s = Language.getInstance().getValue(key);
        if (s == null) {
            return key;
        }
        for (Object para : paras) {
            if (para instanceof String) {
                s = s.replaceFirst("#s", para.toString());
            } else if (para instanceof Integer) {
                s = s.replaceFirst("#d", para.toString());
            } else if (para instanceof Double) {
                s = s.replaceFirst("#f", para.toString());
            } else if (para instanceof Character){
                s = s.replaceFirst("#c", para.toString());
            }
        }
        return s.replaceAll("##", "#");
    }
}
