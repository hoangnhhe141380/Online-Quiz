package controller;

import dao.QuizDAO;
import entity.Quiz;
import entity.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TakeQuizController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("USERMODEL");
            /* Check if user logined*/
            if (user != null) {
                Quiz quiz = (Quiz) request.getSession().getAttribute("QUIZMODEL");
                if (quiz != null) {
                    Integer id = (Integer) request.getSession().getAttribute("IDQUIZRUN");
                    if (quiz.getEndTime() < System.currentTimeMillis() || quiz.getListQuiz().size() <= id) { 
                        /* When time up or user finished all the quizs */
                        double score = (double) Math.round(getScore(quiz.getListQuiz()) * 100) / 100;
                        request.setAttribute("SCORE", score);
                        request.getSession().removeAttribute("QUIZMODEL");
                        request.getSession().removeAttribute("IDQUIZRUN");
                        request.getSession().removeAttribute("QUIZRUN");
                        request.getSession().removeAttribute("SCORE");
                    } else if (id != null && quiz.getListQuiz().size() >= id + 1) {
                        Quiz quizRunning = quiz.getListQuiz().get(id);
                        request.setAttribute("QUIZPROCESSING", "hello");
                        request.setAttribute("QUIZRUN", quizRunning);
                    }
                }
            } else {
                response.sendRedirect("home");
                return;
            }
            QuizDAO quizDAO = new QuizDAO();
            request.setAttribute("max", quizDAO.countNumberQuiz());
            request.getRequestDispatcher("./jsp/takequiz.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error",e.toString());
            request.getRequestDispatcher("./jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("USERMODEL");
            QuizDAO quizDAO = new QuizDAO();
            if (user != null) {
                List<Quiz> listQuiz = (List<Quiz>) request.getSession().getAttribute("QUIZ");
                
                /* When user choose their numbers of question */
                if (request.getParameterMap().containsKey("number") && listQuiz == null) {
                    int numbers;
                    //Select number question
                    numbers = Integer.parseInt(request.getParameter("number"));

                    // List number of quiz that user order
                    List<Quiz> listQuizOrder = quizDAO.getListQuestionsLimit(numbers);
                    if (listQuizOrder != null) {
                        Quiz quiz = new Quiz();
                        quiz.setEndTime(System.currentTimeMillis() + numbers * 60000);
                        quiz.setListQuiz(listQuizOrder);
                        request.getSession().setAttribute("QUIZMODEL", quiz);
                        request.getSession().setAttribute("IDQUIZRUN", 0);
                    }
                } else if (request.getParameterMap().containsKey("nextQuiz")) {     
                    /* When user in processing of doing quizs */
                    
                    Integer id = (Integer) request.getSession().getAttribute("IDQUIZRUN");  
                    Quiz quiz = (Quiz) request.getSession().getAttribute("QUIZMODEL");      
                    if (id != null) {
                        Quiz quizRunning = (Quiz) quiz.getListQuiz().get(id);
                        if (quizRunning != null) {
                            
                            /* If user doesn't choose any answers */
                            if (!request.getParameterMap().containsKey("answers")) {
                                response.sendRedirect("takequiz");
                                return;
                            }
                            
                            String[] answers = request.getParameterValues("answers");
                            String[] answerRight = quiz.getListQuiz().get(id).getAnswers();
                            int rightExpect = 0;

                            /* Calculate score */
                            double pointPerQuestion = (double) 10 / quiz.getListQuiz().size();  
                            /* point per question */
                            
                            for (int i = 0; i < answerRight.length; i++) {
                                for (int j = 0; j < answers.length; j++) {
                                    if (answerRight[i].trim().equalsIgnoreCase(answers[j].trim())) {
                                        rightExpect += 1;
                                    }
                                }
                            }
                            double score;
                            if (rightExpect == answers.length) {        
                                /* If user answers are the same as right answers in db*/
                                score = pointPerQuestion;
                            } else {    
                                score = 0;
                            }
                            
                            quiz.getListQuiz().get(id).setScore(score);
                        }
                        request.getSession().setAttribute("IDQUIZRUN", (id + 1));
                    }
                }
                response.sendRedirect("takequiz");
            } else {
                response.sendRedirect("home");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.toString());
            request.getRequestDispatcher("./jsp/error.jsp").forward(request, response);
        }
    }

    public double getScore(List<Quiz> list) {
        double score = 0;
        for (Quiz quiz : list) {
            score += quiz.getScore();
        }
        return score;
    }

}
