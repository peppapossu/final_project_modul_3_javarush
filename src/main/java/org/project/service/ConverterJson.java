package org.project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.project.model.DataBase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.project.model.Constants.*;

public class ConverterJson {
    public static DataBase jsonConverter (){

        File file = new ConverterJson().pathToData();
        ObjectMapper om = new ObjectMapper();
        DataBase DataBase;
        try {
            DataBase = om.readValue(file, DataBase.class);
        } catch (IOException e) {
            System.out.println("Cannot read from the file");
            return null;
        }
        return DataBase;
    }
    @SneakyThrows
    private File pathToData(){
       return new File ((Paths.get(ConverterJson.class.getClassLoader().getResource(DATA_FILE).toURI())).toString());
    }
}
