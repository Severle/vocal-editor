import com.formdev.flatlaf.FlatIntelliJLaf;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

@Log4j2
public class Test {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(new FlatIntelliJLaf());
        JTable table = new JTable();
        log.debug(table.getSelectionForeground());
        log.debug(table.getSelectionBackground());
        log.debug(table.getForeground());
        log.debug(table.getBackground());
    }

    private static void json() {
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(new InputStreamReader(Objects.requireNonNull(Test.class.getClassLoader().getResourceAsStream("lang/zh_cn.json"))), JsonObject.class);
        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            log.debug("key: {}, value: {}", entry.getKey(), entry.getValue());
        }
    }
}
