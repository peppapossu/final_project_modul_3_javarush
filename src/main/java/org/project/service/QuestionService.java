package org.project.service;
import org.project.model.DataBase;


public class QuestionService {
    public static String getAnswerFirst(DataBase dataBase, int currentQuestionId) {
        return dataBase.getAnswers().get(getAnswerFirstId(dataBase, currentQuestionId)).getAnswer();
    }

    public static int getAnswerFirstId(DataBase dataBase, int currentQuestionId) {
        return dataBase.getQuestions().get(currentQuestionId).getAnswer().get(0);
    }

    public static String getAnswerSecond(DataBase dataBase, int answerSecondId) {
        return dataBase.getAnswers().get(answerSecondId).getAnswer();
    }

    public static int getAnswerSecondId(DataBase dataBase, int currentQuestionId) {
       return dataBase.getQuestions().get(currentQuestionId).getAnswer().get(1);
    }

    public static int getAnswersAmount(DataBase dataBase, int currentQuestionId) {
        return dataBase.getQuestions().get(currentQuestionId).getAnswer().size();
    }

    public static Integer getCurrentAnswerId(DataBase dataBase, int currentQuestionId) {
        return dataBase.getQuestions().get(currentQuestionId).getAnswer().get(0);
    }

    public static String getCurrentQuestion(DataBase dataBase, int currentQuestionId) {
        return dataBase.getQuestions().get(currentQuestionId).getQuestion();
    }
}
