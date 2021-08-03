package dao;

import entity.Quiz;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizDAO extends dbcontext.DBContext {

    public String convertStringArrayToString(String[] answers) {
        String answerString = "";
        for (int i = 0; i < answers.length; i++) {
            answerString += answers[i];
        }
        return answerString;
    }

    public java.sql.Date getCurrentDate() {
        Date utilDate = new Date();
        return new java.sql.Date(utilDate.getTime());
    }

    /* Save new quiz to database */
    public int saveNewQuiz(Quiz quiz) throws Exception {
        int result = 0;
        String sql = "INSERT INTO question(question,option1,option2,option3,option4,answers,userID,createdDate) \n"
                + "VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, quiz.getQuestion());
            stm.setString(2, quiz.getOption().get(0));
            stm.setString(3, quiz.getOption().get(1));
            stm.setString(4, quiz.getOption().get(2));
            stm.setString(5, quiz.getOption().get(3));
            stm.setString(6, convertStringArrayToString(quiz.getAnswers()));
            stm.setInt(7, quiz.getUserID());
            stm.setDate(8, getCurrentDate());
            stm.executeUpdate();
            return 1;
        } catch (Exception e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    /* Save new quiz to database */
    public int updateQuiz(Quiz quiz) throws Exception {
        int result = 0;
        String sql = "UPDATE question\n"
                + "SET question = ? , option1 = ? , option2 = ? , option3 = ? , option4 = ? ,answers = ? \n"
                + "WHERE id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, quiz.getQuestion());
            stm.setString(2, quiz.getOption().get(0));
            stm.setString(3, quiz.getOption().get(1));
            stm.setString(4, quiz.getOption().get(2));
            stm.setString(5, quiz.getOption().get(3));
            stm.setString(6, convertStringArrayToString(quiz.getAnswers()));
            stm.setInt(7, quiz.getId());
            stm.executeUpdate();
            return 1;
        } catch (Exception e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    /* Get a list a quiz with number of quizs user wants */
    public List<Quiz> getListQuestionsLimit(int limit) throws SQLException, Exception {
        String sql = "SELECT TOP(?) * FROM question ORDER BY NEWID()";
        List<Quiz> listQuiz = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, limit);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("id"));
                quiz.setQuestion(rs.getString("question"));
                List<String> options = new ArrayList<>();
                options.add(rs.getString("option1"));
                options.add(rs.getString("option2"));
                options.add(rs.getString("option3"));
                options.add(rs.getString("option4"));
                quiz.setOption(options);
                String[] answers = rs.getString("answers").split("");
                quiz.setAnswers(answers);
                quiz.setUserID(rs.getInt("userID"));
                quiz.setCreatedDate(rs.getDate("createdDate"));
                listQuiz.add(quiz);
            }
            return listQuiz;
        } catch (Exception e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    /* Get quiz by ID */
    public Quiz getQuestionByID(int id) throws Exception {
        String sql = "SELECT * FROM question WHERE id=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("id"));
                quiz.setQuestion(rs.getString("question"));
                List<String> options = new ArrayList<>();
                options.add(rs.getString("option1"));
                options.add(rs.getString("option2"));
                options.add(rs.getString("option3"));
                options.add(rs.getString("option4"));
                quiz.setOption(options);
                String[] answers = rs.getString("answers").split("");
                quiz.setAnswers(answers);
                quiz.setUserID(rs.getInt("userID"));
                quiz.setCreatedDate(rs.getDate("createdDate"));
                return quiz;
            }
        } catch (Exception e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    /* Get paging list of quiz by userID */
    public List<Quiz> getListPagingQuestionsByUser(int userId, int index, int size) throws Exception {
        String sql = "  with r as (\n"
                + "SELECT ROW_NUMBER() OVER (ORDER BY id) as i, * from question where userID = ?\n"
                + ")\n"
                + "SELECT * from r where i between ? and ?";
        List<Quiz> listQuiz = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            int from = index * size - (size - 1);
            int to = from + size - 1;
            stm.setInt(1, userId);
            stm.setInt(2, from);
            stm.setInt(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("id"));
                quiz.setQuestion(rs.getString("question"));
                List<String> options = new ArrayList<>();
                options.add(rs.getString("option1"));
                options.add(rs.getString("option2"));
                options.add(rs.getString("option3"));
                options.add(rs.getString("option4"));
                quiz.setOption(options);
                String[] answers = rs.getString("answers").split("");
                quiz.setAnswers(answers);
                quiz.setUserID(rs.getInt("userID"));
                quiz.setCreatedDate(rs.getDate("createdDate"));
                listQuiz.add(quiz);
            }
            return listQuiz;
        } catch (Exception e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    /* Number or quiz that user created */
    public int countNumberQuizByUserID(int userID) throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM question WHERE userID = ?";
        int count = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (Exception e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return count;
    }

    /* Number of quiz that stored in database */
    public int countNumberQuiz() throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM question";
        int count = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (Exception e) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        QuizDAO db = new QuizDAO();
        List<Quiz> quizs = db.getListPagingQuestionsByUser(8, 1, 5);
        for (Quiz quiz : quizs) {
            System.out.println(quiz);
        }
    }
}
