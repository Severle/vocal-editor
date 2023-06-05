package org.severle.core.io.writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.severle.core.Project7;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Project7Writer {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Project7Writer() {
    }

    public void write(Project7 project, File file) {
        String json = gson.toJson(project);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(Project7 project, String fileName) {
        String json = gson.toJson(project);
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
