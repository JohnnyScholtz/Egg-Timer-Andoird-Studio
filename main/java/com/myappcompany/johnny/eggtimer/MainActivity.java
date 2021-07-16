package com.myappcompany.johnny.eggtimer;


import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView timerTextView;

    SeekBar timerSeekBar;

    MediaPlayer mediaPlayer;

    public void startTimerButton(View view) {

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm_clock);

        CountDownTimer countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000, 1000) {

            public void onTick(long millisecondsUntilDone) {

                updateTimer((int) millisecondsUntilDone / 1000);

            }

            public void onFinish() {

                mediaPlayer.start();

            }

        }.start();

    }

    public void resetTimerButton(View view) {

        mediaPlayer.pause();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                timerSeekBar.setProgress(30);

            }
        }, 500);



    }

    public void updateTimer(int secondsLeft) {

        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String secondString;

        if (seconds < 10) {

            secondString = ("0" + Integer.toString(seconds));

        } else {

            secondString = Integer.toString(seconds);

        }



        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);

        timerTextView = findViewById(R.id.timerTextView);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
