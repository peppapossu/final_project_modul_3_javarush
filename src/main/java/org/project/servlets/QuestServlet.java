package org.project.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.model.DataBase;
import org.project.service.ConverterJson;

import java.io.IOException;
import static org.project.model.Constants.*;
import static org.project.service.QuestionService.*;

@WebServlet("/index")
public class QuestServlet extends HttpServlet {
    private DataBase dataBase;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dataBase = (DataBase) config.getServletContext().getAttribute(ROOT);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBase db = getDB(req);
        int currentQuestionId = getCurrentQuestionId(req, db);
        if (checkFinish(req, resp, db, currentQuestionId)) return;
        setAttributes(req, db, currentQuestionId);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void setAttributes(HttpServletRequest req, DataBase db, int currentQuestionId) {
        
        req.setAttribute(CURRENT_QUESTION, getCurrentQuestion(db,currentQuestionId));
        req.setAttribute(ANSWER_FIRST, getAnswerFirst(db, currentQuestionId));
        req.setAttribute(ANSWER_FIRST_ID, getAnswerFirstId(db, currentQuestionId));

        if (getAnswersAmount(db, currentQuestionId) >1) {
            req.setAttribute(ANSWER_SECOND, getAnswerSecond(db, getAnswerSecondId(db, currentQuestionId)));
            req.setAttribute(ANSWER_SECOND_ID, getAnswerSecondId(db, currentQuestionId));
        }
    }
    //
    private boolean checkFinish(HttpServletRequest req, HttpServletResponse resp, DataBase dataBase, int currentQuestionId) throws ServletException, IOException {
        if (getCurrentAnswerId(dataBase, currentQuestionId) >=100) {
            req.setAttribute(CURRENT_QUESTION, getCurrentQuestion(dataBase, currentQuestionId));
            req.getServletContext().setAttribute(COUNT_GAMES , countGames(req));
            req.getRequestDispatcher("/finish.jsp").forward(req, resp);
            return true;
        }
        return false;
    }

    private int getCurrentQuestionId(HttpServletRequest req, DataBase db) {
        String answer = req.getParameter(ANSWER);
        if (answer != null) {
            return db.getAnswers().get(Integer.parseInt(answer)).getQuestion().get(0);
        }
        return 0;
    }

    private DataBase getDB(HttpServletRequest req) {
        DataBase db;
        int countGames = 0;
        String name = req.getParameter(NAME);
        if (req.getSession().getAttribute(ROOT) == null) {
            db = ConverterJson.jsonConverter();
            req.getSession(true).setAttribute(ROOT, db);
            req.getServletContext().setAttribute(NAME, name);
            req.getServletContext().setAttribute(COUNT_GAMES, countGames);}
            else {
            db = (DataBase) req.getSession().getAttribute(ROOT);
        }
        return db;
    }
    private int countGames(HttpServletRequest req) {
        int count = Integer.parseInt((req.getServletContext().getAttribute(COUNT_GAMES)).toString());
        return ++count;
    }
}
