package org.project.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.model.Root;
import org.project.service.ConverterJson;
import java.io.IOException;
import static org.project.model.Constants.*;

@WebServlet("/index")
public class QuestServlet extends HttpServlet {
    Root db;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        db = (Root) config.getServletContext().getAttribute(ROOT);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Root db = getRoot(req);
        int currentQuestionIndex = getCurrentQuestionIndex(req, db);
        if (checkFinish(req, resp, db, currentQuestionIndex)) return;
        setAttributes(req, db, currentQuestionIndex);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private static void setAttributes(HttpServletRequest req, Root db, int currentQuestionIndex) {
        String currentQuestion = db.getQuestions().get(currentQuestionIndex).getQuestion();
        int answerFirstId = db.getQuestions().get(currentQuestionIndex).getAnswer().get(0);
        String answerFirst = db.getAnswers().get(answerFirstId).getAnswer();

        req.setAttribute(CURRENT_QUESTION, currentQuestion);
        req.setAttribute(ANSWER_FIRST, answerFirst);
        req.setAttribute(ANSWER_FIRST_ID, answerFirstId);

        if (db.getQuestions().get(currentQuestionIndex).getAnswer().size()>1) {
            int answerSecondId = db.getQuestions().get(currentQuestionIndex).getAnswer().get(1);
            String answerSecond = db.getAnswers().get(answerSecondId).getAnswer();
            req.setAttribute(ANSWER_SECOND, answerSecond);
            req.setAttribute(ANSWER_SECOND_ID, answerSecondId);
        }
    }

    private static boolean checkFinish(HttpServletRequest req, HttpServletResponse resp, Root db, int currentQuestionIndex) throws ServletException, IOException {
        if (db.getQuestions().get(currentQuestionIndex).answer.get(0)>=100) {
            String currentQuestion = db.getQuestions().get(currentQuestionIndex).getQuestion();
            req.setAttribute(CURRENT_QUESTION, currentQuestion);
            req.getServletContext().setAttribute(COUNT_GAMES , countGames(req));
            req.getRequestDispatcher("/finish.jsp").forward(req, resp);
            return true;
        }
        return false;
    }


    private static int getCurrentQuestionIndex(HttpServletRequest req, Root root) {
        String answer = req.getParameter(ANSWER);
        if (answer != null) {
            return root.getAnswers().get(Integer.parseInt(answer)).getQuestion().get(0);
        }
        return 0;
    }

    private static Root getRoot(HttpServletRequest req) {
        Root root;
        int countGames = 0;
        String name = req.getParameter(NAME);
        if (req.getSession().getAttribute(ROOT) == null) {
            root = ConverterJson.jsonConverter();
            req.getSession(true).setAttribute(ROOT, root);
            req.getServletContext().setAttribute(NAME, name);
            req.getServletContext().setAttribute(COUNT_GAMES, countGames);}
            else {root = (Root) req.getSession().getAttribute(ROOT);
        }
        return root;
    }
    private static int countGames(HttpServletRequest req) {
        int count = Integer.parseInt((req.getServletContext().getAttribute(COUNT_GAMES)).toString());
        return ++count;
    }
}
