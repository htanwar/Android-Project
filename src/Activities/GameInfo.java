package com.example.htanw.mathrace;

import android.app.Application;

/**
 * Created by htanw on 1/18/2016.
 */
public class GameInfo extends Application{

    /*
        Class used to store global variables 
        questions, correctly answered, and total time
        taken.

    */
    private int question = 10;
    private int correct = 0;
    private double totalTime = 0;

    public void setQuestion(int x){
        this.question = x;
    }
    public int getQuestion(){
        return question;
    }
    public void setCorrect(int y){
        this.correct = y;
    }
    public int getCorrect(){
        return correct;
    }
    public void setTotalTime(double z){
        this.totalTime = z;
    }

    public double getTotalTime() {
        return totalTime;
    }
}

