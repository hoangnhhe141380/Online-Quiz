package entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class Quiz {
    private int id;
    private String question;
    private List<String> option;
    private String[] answers;
    private double score = 0;
    private boolean status = false;
    private Long endTime;
    private int userID;
    private Date createdDate;
    private List<Quiz> listQuiz;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<Quiz> getListQuiz() {
        return listQuiz;
    }

    public void setListQuiz(List<Quiz> listQuiz) {
        this.listQuiz = listQuiz;
    }
    
    public Quiz(int id, String question, List<String> option, String[] answers, Long endTime, int userID, Date createdDate) {
        this.id = id;
        this.question = question;
        this.option = option;
        this.answers = answers;
        this.endTime = endTime;
        this.userID = userID;
        this.createdDate = createdDate;
    }

    public Quiz() {
    }
    
    public String convertDateToString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy",Locale.ENGLISH);
        return dateFormat.format(this.createdDate);
    }
}
