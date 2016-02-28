package com.app.manish.wpm;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private EditText wordsPerLines_et, linesPerPage_et, totalPages_et;
    ImageButton startReading, stopReading;
    private TextView wpm_tv;
    Chronometer mChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordsPerLines_et = (EditText)findViewById(R.id.words_per_line);
        linesPerPage_et = (EditText) findViewById(R.id.lines_per_page);
        totalPages_et = (EditText) findViewById(R.id.totel_pages);
        startReading = (ImageButton)findViewById(R.id.startReading);
        stopReading = (ImageButton) findViewById(R.id.stopReading);
        wpm_tv = (TextView) findViewById(R.id.result);
        mChronometer = (Chronometer)findViewById(R.id.chronometer1);

        startReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.start();
            }
        });

        stopReading.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mChronometer.stop();
                int time = ElapsedTime();
                findUserValues(time);
            }
        });
    }

    public int ElapsedTime(){
        long elapsedMillis = SystemClock.elapsedRealtime() - mChronometer.getBase();
        return (int)elapsedMillis/1000;
    }

    public void findUserValues(int time){
        Integer wordsPerLines =Integer.valueOf(wordsPerLines_et.getText().toString());
        Integer linesPerPage = Integer.valueOf(linesPerPage_et.getText().toString());
        Integer totalPages = Integer.valueOf(totalPages_et.getText().toString());
        Integer wpm = (wordsPerLines*linesPerPage*totalPages)/time;
        wpm_tv.setText("Reading speed = "+String.valueOf(wpm)+" word/minute");
    }

};