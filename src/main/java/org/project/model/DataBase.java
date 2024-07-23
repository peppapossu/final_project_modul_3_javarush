package org.project.model;

import java.util.List;
import lombok.Data;

@Data
public class DataBase {
    private List<Question> questions;
    private List<Answer> answers;

}