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

public class MakeQuizController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {

            User user = (User) request.getSession().getAttribute("USERMODEL");

            /* Check if user has loged in */
            if (user == null) {
                response.sendRedirect("home");
                return;
            }
            /* Check if user has role teacher */
            if (user.getRoleId() != 1) {
                response.sendRedirect("home");
                return;
            }
            request.getRequestDispatcher("./jsp/makequiz.jsp").forward(request, response);

        } catch (IOException | ServletException e) {
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

                if (request.getParameter("question").trim().isEmpty()
                        || request.getParameter("option1").trim().isEmpty()
                        || request.getParameter("option2").trim().isEmpty()
                        || request.getParameter("option3").trim().isEmpty()
                        || request.getParameter("option4").trim().isEmpty()) {
                    request.setAttribute("message", "Must have question or answer!");
                    request.getRequestDispatcher("./jsp/makequiz.jsp").forward(request, response);
                }

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

                if (answers == null) {
                    request.setAttribute("message", "Answer must not empty!");
                    request.setAttribute("q", question);
                    request.setAttribute("o1", option1);
                    request.setAttribute("o2", option2);
                    request.setAttribute("o3", option3);
                    request.setAttribute("o4", option4);
                    request.getRequestDispatcher("./jsp/makequiz.jsp").forward(request, response);
                } else if (String.join("", answers).equals("1234")) {
                    request.setAttribute("message", "Can not select all answer!");
                    request.setAttribute("q", question);
                    request.setAttribute("o1", option1);
                    request.setAttribute("o2", option2);
                    request.setAttribute("o3", option3);
                    request.setAttribute("o4", option4);
                    request.setAttribute("answerString", String.join("", answers));
                    request.getRequestDispatcher("./jsp/makequiz.jsp").forward(request, response);
                } else {

                    Quiz quizNew = new Quiz();
                    quizNew.setQuestion(question);
                    quizNew.setOption(listOption);
                    quizNew.setAnswers(answers);
                    quizNew.setUserID(user.getId());

                    QuizDAO quizDAO = new QuizDAO();

                    int result = quizDAO.saveNewQuiz(quizNew);

                    request.setAttribute("result", result);
                    request.getRequestDispatcher("./jsp/makequiz.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect("home");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.toString());
            request.getRequestDispatcher("./jsp/error.jsp").forward(request, response);
        }
    }

}
