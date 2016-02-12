package com.example.htanw.mathrace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by htanw on 1/14/2016.
 */


public class GameScreen extends Activity{

    /*
        
        This activity is the game screen where you answer
        questions, each question is randomly generated.

    */
    private int answer = 0;
    private String userAns = "";
    int iClicks = 0;
    private CountDownTimer countDownTimer;
    boolean isFinished = false;
    long tStart = 0;
    final int totalTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
            Each time a question is answered it creates
            a new instance of this activity
        */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_menu);
        final GameInfo quest = (GameInfo)getApplication();
        int questions = quest.getQuestion();
        TextView tv = (TextView) findViewById(R.id.textView5);
        tv.setText("Questions: "+ questions);
        iClicks = 0;

        /*
            Used to count the time
        */

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView txtClicks = (TextView) findViewById(R.id.textView10);
                        // task to be done every 1000 milliseconds
                        iClicks = iClicks + 1;
                        txtClicks.setText("Time: " + iClicks);
                    }
                });

            }
        }, 0, 1000);
        tStart = System.currentTimeMillis();
        playGame();

    }

    @Override
    public void onBackPressed() {
        //disable back button
    }

    public void playGame(){
        /*
            Questions are randomly generated using
            numbers from 1 to 10 inclusive. Then a random sign
            of multiplication or addition
        */

        Random random = new Random();
        int left = random.nextInt(10)+1;
        int right = random.nextInt(10)+1;
        int sign= random.nextInt(2)+1;
        TextView view0 = (TextView) findViewById(R.id.textView2);
        TextView view1 = (TextView) findViewById(R.id.textView3);
        TextView view2 = (TextView) findViewById(R.id.textView4);
        answer = 0;
        switch (sign){
            case 1:
                answer = left + right;
                view0.setText(String.valueOf(left));
                view1.setText("+");
                view2.setText(String.valueOf(right));
                break;
            case 2:
                answer = left * right;
                view0.setText(String.valueOf(left));
                view1.setText("x");
                view2.setText(String.valueOf(right));
                break;
        }

    }

    public void onClick(View v){
        /*
            Used to get input from the user depeneding on 
            which button they pressed. Buttons include Submit, Delete,
            and numbers 0-9. Using a Switch statement.
        */
        Button button = (Button) v;
        TextView view = (TextView) findViewById(R.id.textView);
        switch(v.getId()){
            case R.id.button3:
                view.append("1");
                userAns = userAns + "1";
                break;
            case R.id.button4:
                view.append("2");
                userAns = userAns + "2";
                break;
            case R.id.button5:
                view.append("3");
                userAns = userAns + "3";
                break;
            case R.id.button6:
                view.append("4");
                userAns = userAns + "4";
                break;
            case R.id.button7:
                view.append("5");
                userAns = userAns + "5";
                break;
            case R.id.button8:
                view.append("6");
                userAns = userAns + "6";
                break;
            case R.id.button9:
                view.append("7");
                userAns = userAns + "7";
                break;
            case R.id.button10:
                view.append("8");
                userAns = userAns + "8";
                break;
            case R.id.button11:
                view.append("9");
                userAns = userAns + "9";
                break;
            case R.id.button12:
                view.append("0");
                userAns = userAns + "0";
                break;

            //Delete button
            case R.id.button13:
                if (view.getText().toString().length() > 0) {
                    String temp = view.getText().toString();
                    temp = temp.substring(0, temp.length() - 1);
                    if (userAns.length() > 0) {
                        userAns = userAns.substring(0, userAns.length() - 1);
                    }
                    view.setText(temp);
                    break;
                }
                else {
                    break;
                }

            //Submit button
            case R.id.button14:
                if (view.getText().length() <= 0){
                    break;
                }
                GameInfo quest = (GameInfo)getApplication();
                int i = Integer.parseInt(userAns);
                /*
                    If answer is correct then display a check mark
                    and increment the number of correct by 1
                */
                if(i == answer){

                    ImageView iv = (ImageView) findViewById(R.id.imageView4);
                    iv.setImageResource(R.drawable.check);
                    int correct = quest.getCorrect();
                    quest.setCorrect(correct+1);
                }
                /*
                    Else display a X mark for wrong answer
                */
                else{
                    ImageView iv = (ImageView) findViewById(R.id.imageView4);
                    iv.setImageResource(R.drawable.xmark);
                }
                /* 
                    Total of 10 questions if the number of questions is 
                    above 10 then continue the game until it reaches 0.
                    Basically restarting this same activity with updated 
                    statistics and variables
                */
                TextView tv = (TextView) findViewById(R.id.textView5);
                int questions = quest.getQuestion();
                questions--;
                tv.setText("Questions: " + Integer.toString(questions));
                quest.setQuestion(questions);

                if (questions > 0) {
                    Intent intent = new Intent(this, GameScreen.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent();
                    intent.setClass(this, FinalScreen.class);
                    startActivity(intent);
                }

                /*
                    Add the time to total time taken to complete
                    each question
                */
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                double elapsedSeconds = tDelta / 1000.0;
                quest.setTotalTime(quest.getTotalTime() + elapsedSeconds);
                break;


        }


    }

}

