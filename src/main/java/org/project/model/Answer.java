package org.project.model;

import lombok.Data;

import java.util.List;

@Data
public class Answer{
    private int id;
    private String answer;
    private List<Integer> question;
}