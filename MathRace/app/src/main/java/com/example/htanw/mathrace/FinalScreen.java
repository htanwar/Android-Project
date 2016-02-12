package com.example.htanw.mathrace;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

/**
 * Created by htanw on 1/14/2016.
 */
public class FinalScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*
            This activity will show the final screen of the app
            it shows the total score, time, total correct, and
            incorrect.

        */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_screen);
        GameInfo quest = (GameInfo)getApplication();
        int questions = quest.getQuestion();
        int correct = quest.getCorrect();
        int wrong = 10-correct;

        TextView tv = (TextView) findViewById(R.id.textView8);
        tv.setText(Integer.toString(correct));

        TextView tv2 = (TextView) findViewById(R.id.textView9);
        tv2.setText(Integer.toString(wrong));

        TextView tv3 = (TextView) findViewById(R.id.textView12);
        //Round the total time to 2 decimal spaces
        DecimalFormat df = new DecimalFormat("#.##");
        tv3.setText(df.format(quest.getTotalTime()));

        //My formula to compute score
        long newScore = (long) ((1/(quest.getTotalTime()))*correct*100);

        TextView tv4 = (TextView) findViewById(R.id.textView13);
        tv4.setText("Score: "+ newScore);

        //Store high score
        //Using a shared preference
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        long oldScore = prefs.getLong("key", 0);
        if(newScore > oldScore ){
            SharedPreferences.Editor edit = prefs.edit();
            edit.putLong("key", newScore);
            edit.commit();
        }

        //Resets correct, questions, and total time to default values
        quest.setCorrect(0);
        quest.setQuestion(10);
        quest.setTotalTime(0);


    }

    public void onClick(View v){
        Button button = (Button) v;
        switch (v.getId()){
            //Home button goes to main activity
            case R.id.button15:
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                break;

            //Play again goes to Gamescreen
            case R.id.button16:
                Intent intent2 = new Intent();
                intent2.setClass(this, GameScreen.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //disable back button
    }

}
