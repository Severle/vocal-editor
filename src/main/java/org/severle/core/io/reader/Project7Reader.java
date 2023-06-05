package org.severle.core.io.reader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.severle.core.Project7;

import java.io.*;

public class Project7Reader {
    private final Gson gson = new Gson();

    public Project7Reader() {
    }

    public Project7 read(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            try (JsonReader reader = new JsonReader(fileReader)) {
                return gson.fromJson(reader, Project7.class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
