package com.example.htanw.mathrace;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    /*
        Main activity displays the main menu
        where you can either start the game or view the
        current hih score
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //Starts the game when the start button is clicked
    public void goStart(View view){
        startActivity(new Intent(this, GameScreen.class));
    }

    //Views the high score which was stored in a shared preference variable
    public void goScore(View view){
        //Displays high score as a Toast message
        Button button = (Button) view;
        final SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        final long oldScore = prefs.getLong("key", 0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getBaseContext(), "Current Highscore: " + oldScore, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        //disable back button
    }


}
