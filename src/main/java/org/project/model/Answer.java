package org.project.model;

import lombok.Data;

import java.util.List;

@Data
public class Answer{
    public int id;
    public String answer;
    public List<Integer> question;
}