import lombok.extern.log4j.Log4j2;
import org.severle.system.ProjectOpenList;

import java.io.IOException;

@Log4j2
public class Test4 {
    public static void main(String[] args) throws IOException {

        ProjectOpenList.add("./projects/f.p7");
        ProjectOpenList.add("./projects/c.p7");
        // a b c d e -> b c d e f -> b d e f c

    }
}
