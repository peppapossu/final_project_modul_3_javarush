package org.project.model;

import lombok.Data;

import java.util.List;

@Data
public class Question{
    public int id;
    public String question;
    public List<Integer> answer;
}