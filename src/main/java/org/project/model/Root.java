package org.project.model;

import java.util.List;
import lombok.Data;

@Data
public class Root{
    public List<Question> questions;
    public List<Answer> answers;

}