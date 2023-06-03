package org.severle.system;

import lombok.extern.log4j.Log4j2;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.severle.text.Language;

import java.util.List;

@Log4j2
public class Initializer {
    private static final Document settings;

    static {
        SAXReader reader = new SAXReader();
        try {
            settings = reader.read(Initializer.class.getClassLoader().getResourceAsStream("settings.xml"));
        } catch (DocumentException e) {
            log.error("Initialize settings error, {}", e.getCause().getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Language.CountryLangCode getSettingLanguage() {
        String value = settings.getRootElement().element("language").attributeValue("value");
        return Language.CountryLangCode.getCodeByString(value);
    }

    public static String [] getLastOpenProjects() {
        String [] strings = new String[5];

        List<Element> list = settings.getRootElement().element("last-open").elements("li");
        for (int i = 0;i < 5;i++) {
            strings[i] = list.get(i).attributeValue("value");
        }

        return strings;
    }

    public static boolean isMaxWindow() {
        String s = settings.getRootElement().element("is-max-window").attributeValue("value");
        if (s.equals("true")) {
            return true;
        } else if (s.equals("false")) {
            return false;
        } else {
            log.error("Error settings with max-window");
            throw new RuntimeException("Error settings with max-window");
        }
    }
}
