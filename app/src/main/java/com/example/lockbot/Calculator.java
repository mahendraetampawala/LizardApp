package com.example.lockbot;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


        Button button01=findViewById(R.id.btn1);
        button01.setText(R.string.calone);
        Button button02=findViewById(R.id.btn2);
        button02.setText(R.string.caltwo);
        Button button03=findViewById(R.id.btn3);
        button03.setText(R.string.calthree);
        Button button04=findViewById(R.id.btn4);
        button04.setText(R.string.calfour);
        Button button05=findViewById(R.id.btn5);
        button05.setText(R.string.calfive);
        Button button06=findViewById(R.id.btn6);
        button06.setText(R.string.calsix);
        Button button07=findViewById(R.id.btn7);
        button07.setText(R.string.calseven);
        Button button08=findViewById(R.id.btn8);
        button08.setText(R.string.caleight);
        Button button09=findViewById(R.id.btn9);
        button09.setText(R.string.calnine);
        Button button0=findViewById(R.id.btnzero);
        button0.setText(R.string.calzero);
        Button buttondot=findViewById(R.id.btndot);
        buttondot.setText(R.string.caldot);
        Button buttonplus=findViewById(R.id.btnplus);
        buttonplus.setText(R.string.calplus);
        Button buttonminus=findViewById(R.id.btnminus);
        buttonminus.setText(R.string.calminus);

        Button buttonmultiply=findViewById(R.id.btnmultiply);
        buttonmultiply.setText(R.string.calmultiply);
        Button buttondelete=findViewById(R.id.btndelete);
        buttondelete.setText(R.string.caldelete);
        Button buttonequal=findViewById(R.id.btnequal);
        buttonequal.setText(R.string.calequal);
    }
}