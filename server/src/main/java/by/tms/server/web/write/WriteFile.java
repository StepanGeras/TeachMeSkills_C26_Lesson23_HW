package by.tms.server.web.write;

import by.tms.server.model.Operation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WriteFile {

    private static final String path = "/Users/stepan_gerasimovich/Desktop/Курсы/TeachMeSkills_C26_Lesson23_HW/test.json";

    public static void doWriteFile (String json) {

        try {
            Files.write(Paths.get(path), json.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
