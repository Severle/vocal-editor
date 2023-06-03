package org.severle.text;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.severle.system.Initializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Language {
    private static final String langPath = "lang/";
    private static final Language instance;
    private static final Gson GSON = new Gson();

    static {
        instance = new Language(Initializer.getSettingLanguage());
    }

    public static Language getInstance() {
        return instance;
    }

    private CountryLangCode code;
    private Map<String, String> map;
    private Language(CountryLangCode code) {
        this.code = code;
        this.map = getMapWithFile();
    }
    private Map<String, String> getMapWithFile() {
        try(InputStream inputStream = Language.class.getClassLoader().getResourceAsStream(langPath + this.code.getFileNameCode())) {
            assert inputStream != null;
            JsonObject object = GSON.fromJson(new InputStreamReader(inputStream), JsonObject.class);
            Set<Map.Entry<String, JsonElement>> entrySet = object.entrySet();
            Map<String, String> result = new HashMap<>(entrySet.size());
            for (Map.Entry<String, JsonElement> entry : entrySet) {
                result.put(entry.getKey(), entry.getValue().getAsString());
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void selectNewCode(CountryLangCode code) {
        this.code = code;
        this.map = getMapWithFile();
    }
    public String getValue(String key) {
        return map.get(key);
    }


    public static enum CountryLangCode {
        ZH_CN("zh-cn", "简体中文"),
        ZH_TW("zh-tw", "繁体中文(台湾)"),
        ZH_HK("zh-hk", "繁体中文(香港)"),
        EN_US("en-us", "英语(美国)"),
        EN_HK("en-hk", "英语(香港)"),
        EN_GB("en-gb", "英语(英国)"),
        EN_WW("en-ww", "英语(全球)"),
        EN_CA("en-ca", "英语(加拿大)"),
        EN_AU("en-au", "英语(澳大利亚)"),
        EN_IE("en-ie", "英语(爱尔兰)"),
        EN_FI("en-fi", "英语(芬兰)");
        private static final String SUFFIX = ".json";
        public final String code;
        public final String location;
        CountryLangCode(String codeStr, String locationStr) {
            code = codeStr;
            location = locationStr;
        }
        public String getFileNameCode() {
            return this.code.replace('-', '_') + SUFFIX;
        }
        public static CountryLangCode getCodeByString(String code) {
            for (CountryLangCode langCode : CountryLangCode.values()) {
                if (code.equals(langCode.code)) {
                    return langCode;
                }
            }
            return null;
        }
    }
}
