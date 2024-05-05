/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ir.ac.kntu;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Answers {
    private String userName;

    private String email;

    private ArrayList<String> solutions;

    private ArrayList<LocalDateTime> dates;

    private double finalScore;

    private ArrayList<Double> socres;

    public Answers(String userName,String email){
        this.email=email;
        this.userName=userName;
        solutions=new ArrayList<String>();
        dates=new ArrayList<LocalDateTime>();
        this.finalScore=-1;
        socres =new ArrayList<Double>();
    }


    public String getEmail() {
        return email;
    }


    public String getUserName() {
        return userName;
    }


    public ArrayList<String> getSolution() {
        return  new ArrayList<String>(solutions);
    }

    public ArrayList<LocalDateTime> getDates() {
        return new ArrayList<>(dates);
    }

    public void addSolution(String solution){
        solutions.add(solution);
    }


    public void addDates(LocalDateTime date){
        dates.add(date);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFinalScore(double finalScore) {
        if (finalScore < 0) {
            finalScore = 0;
        }
        this.finalScore=finalScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void addScore(double score) {
        socres.add(score);
    }

    public ArrayList<Double> getSocres() {
        return new ArrayList<>(socres);
    }

}

