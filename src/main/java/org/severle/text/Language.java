package org.severle.text;

public class Language {
    private static final String langPath = "lang/";
    private static final String langSubFix = ".json";
    private static Language instance;

    static {

    }

    public static Language getInstance() {
        return instance;
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

        public final String code;
        public final String location;
        CountryLangCode(String codeStr, String locationStr) {
            code = codeStr;
            location = locationStr;
        }
    }
}
