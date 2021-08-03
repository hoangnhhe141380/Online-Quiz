package controller;

import dao.QuizDAO;
import entity.Quiz;
import entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            User user = (User) request.getSession().getAttribute("USERMODEL");
            if (user == null) {
                response.sendRedirect("home");
                return;
            }
            if (user.getRoleId() != 1) {
                response.sendRedirect("home");
                return;
            }
            if (request.getParameter("quizid") == null) {
                response.sendRedirect("managequiz");
                return;
            }
            int quizID = Integer.parseInt(request.getParameter("quizid"));
            QuizDAO quizDAO = new QuizDAO();
            Quiz quizUpdate = quizDAO.getQuestionByID(quizID);

            for (String s : quizUpdate.getAnswers()) {
                request.setAttribute("ANSWER" + s, "checked");
            }

            request.setAttribute("QUIZUPDATE", quizUpdate);
            request.getRequestDispatcher("./jsp/updatequiz.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.toString());
            request.getRequestDispatcher("./jsp/error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            User user = (User) request.getSession().getAttribute("USERMODEL");
            /* Check if user has loged in */
            if (user != null) {
                int quizID = Integer.parseInt(request.getParameter("quizid"));

                String question = request.getParameter("question").trim();
                String option1 = request.getParameter("option1").trim();
                String option2 = request.getParameter("option2").trim();
                String option3 = request.getParameter("option3").trim();
                String option4 = request.getParameter("option4").trim();
                List<String> listOption = new ArrayList<>();
                listOption.add(option1);
                listOption.add(option2);
                listOption.add(option3);
                listOption.add(option4);
                String[] answers = request.getParameterValues("answers");

                Quiz quizUpdate = new Quiz();
                quizUpdate.setId(quizID);
                quizUpdate.setQuestion(question);
                quizUpdate.setOption(listOption);
                quizUpdate.setAnswers(answers);

                QuizDAO quizDAO = new QuizDAO();

                int result = quizDAO.updateQuiz(quizUpdate);
                request.setAttribute("result", result);

                Quiz quiz = quizDAO.getQuestionByID(quizID);
                for (String s : quizUpdate.getAnswers()) {
                    request.setAttribute("ANSWER" + s, "checked");
                }

                request.setAttribute("QUIZUPDATE", quizUpdate);
                request.getRequestDispatcher("./jsp/updatequiz.jsp").forward(request, response);
            } else {
                response.sendRedirect("home");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.toString());
            request.getRequestDispatcher("./jsp/error.jsp").forward(request, response);
        }
    }

}
