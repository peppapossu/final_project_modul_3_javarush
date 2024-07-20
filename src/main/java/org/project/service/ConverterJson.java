package org.project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.project.model.Root;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.project.model.Constants.*;

public class ConverterJson {
    public static Root jsonConverter (){

        File file = pathToData();
        ObjectMapper om = new ObjectMapper();
        Root root;
        try {
            root = om.readValue(file, Root.class);
            //root = om.readValue(file, new TypeReference<Root>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return root;
    }
    @SneakyThrows
    private static File pathToData(){
       return new File ((Paths.get(ConverterJson.class.getClassLoader().getResource(DATA_FILE).toURI())).toString());
    }

    @SneakyThrows
    private static void pathToFile() {
        Path path = Paths.get(ConverterJson.class.getClassLoader().getResource(DATA_FILE).toURI());
        Stream<String> streamLines = Files.lines(path);
        String lines = streamLines.collect(Collectors.joining("\n"));
        streamLines.close();
    }
}
