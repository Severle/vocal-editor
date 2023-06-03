import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.extern.log4j.Log4j2;

import java.io.FileReader;

@Log4j2
public class Test {
    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader("D:\\Workspace\\Java\\editor\\src\\main\\resources\\lang\\zh_cn.json"));
        while (jsonReader.hasNext()) {
            log.debug(jsonReader.nextString());
        }
    }
}
