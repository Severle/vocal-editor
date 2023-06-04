import com.formdev.flatlaf.FlatIntelliJLaf;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

@Log4j2
public class Test {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(new FlatIntelliJLaf());
        File file = new File("src/main/resources/LookAndFeel.csv");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Key\tValue\n");
            for (Map.Entry<Object, Object> entry : UIManager.getLookAndFeelDefaults().entrySet()) {
                log.debug("Key: {}, Value: {}", entry.getKey(), entry.getValue());
                writer.write(entry.getKey().toString() + '\t' + entry.getValue().toString());
            }
            log.debug("Write OK.");
            Color color1 = new Color(152, 195, 235);
            Color color2 = new Color(140, 140, 140);
            Color color3 = new Color(156, 156, 156);
            Color color4 = new Color(255, 255, 255);
            Color color5 = new Color(237, 162, 0);
            Color color6 = new Color(224, 85, 85);
            Color color7 = new Color(177, 177, 177);
            Color color8 = new Color(163, 197, 228);
        }
    }

    private static void json() {
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(new InputStreamReader(Objects.requireNonNull(Test.class.getClassLoader().getResourceAsStream("lang/zh_cn.json"))), JsonObject.class);
        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            log.debug("key: {}, value: {}", entry.getKey(), entry.getValue());
        }
    }
}
