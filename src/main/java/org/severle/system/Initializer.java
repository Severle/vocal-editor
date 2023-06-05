package org.severle.system;

import lombok.extern.log4j.Log4j2;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.severle.text.Language;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Log4j2
public class Initializer {
    private static final Document settings;
    public static final String DEFAULT_NEW_FILE_NAME = "untitled";
    public static final String SUFFIX = ".p7";

    static {
        SAXReader reader = new SAXReader();
        try {
            settings = reader.read(Initializer.class.getClassLoader().getResourceAsStream("settings.xml"));
        } catch (DocumentException e) {
            log.error("Initialize settings error, {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Language.CountryLangCode getSettingLanguage() {
        String value = settings.getRootElement().element("language").attributeValue("value");
        return Language.CountryLangCode.getCodeByString(value);
    }

    public static String[] getLastOpenProjects() {
        String[] strings = new String[5];

        List<Element> list = settings.getRootElement().element("last-open").elements("li");
        for (int i = 0; i < 5; i++) {
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

    public static String getProjectRepository() {
        return settings.getRootElement().element("project-repository").attributeValue("value");
    }

    public static Element getLastOpenElement() {
        return settings.getRootElement().element("last-open");
    }

    public static void flushSettings() {
        log.debug("Flush settings.");
        try (FileWriter fileWriter = new FileWriter(new File("./src/main/resources", "settings.xml"))) {
            XMLWriter writer = new XMLWriter(fileWriter);
            writer.write(settings);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
