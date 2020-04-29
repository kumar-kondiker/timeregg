package com.retigence.eggtimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView timerTextview;
    Button countButton;
    boolean isCounerRunning=false;
    CountDownTimer countDownTimer;
    private static final String TAG = "MainActivity";
    int time=30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=findViewById(R.id.id_seekbar);
        timerTextview=findViewById(R.id.timertext);
        countButton=findViewById(R.id.start_count);
        seekBar.setMax(600);
        seekBar.setProgress(5);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void updateTimer(int progress) {

        int minute=progress/60;
        int seconds=progress-minute*60;

        String minutes="",secondss="";
        if(minute<=9)
        {
            minutes="0"+minute;
        }
        else
        {
            minutes=""+minute;
        }

        if(seconds<=9)
        {
            secondss="0"+seconds;
        }
        else
        {
            secondss=""+seconds;
        }

        timerTextview.setText(minutes+" : "+secondss);

    }



    public void countDown(View view)
    {

        if(isCounerRunning==false)
        {
            isCounerRunning=true;
            seekBar.setVisibility(View.GONE);
            countButton.setText("stop");

            countDownTimer=   new CountDownTimer(seekBar.getProgress()*1000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                Log.i(TAG, "onTick: "+millisUntilFinished/1000);
                updateTimer((int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timerTextview.setText("00:00");
                seekBar.setVisibility(View.VISIBLE);
            }
        }.start();
    }
        else
        {
            isCounerRunning=true;
            countDownTimer.cancel();
            timerTextview.setText("00:00");
            seekBar.setProgress(30);
            seekBar.setVisibility(View.VISIBLE);
            countButton.setText("Go");
        }
    }

}
