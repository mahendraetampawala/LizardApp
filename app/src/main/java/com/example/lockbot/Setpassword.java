package com.example.lockbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Setpassword extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton rbutton1,rbutton2;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpassword);

        TextView textView01=findViewById(R.id.selectchoice);
        textView01.setText(R.string.label2);

        RadioButton radio1=findViewById(R.id.recorderrb);
        radio1.setText(R.string.radiobtn2);

        RadioButton radio2=findViewById(R.id.calrb);
        radio2.setText(R.string.radiobtn1);

        TextView auto1=findViewById(R.id.autocompletetxt);
        auto1.setText(R.string.autocomplete1);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        rbutton1=(RadioButton)findViewById(R.id.recorderrb);
        rbutton2=(RadioButton)findViewById(R.id.calrb);
        next=(Button)findViewById(R.id.proceedbtn);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast toast=Toast.makeText(getApplicationContext(),"Please select one of these",Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    if (rbutton2.isChecked()) {
                        Intent intent = new Intent(getApplicationContext(), Calculator.class);
                        startActivity(intent);
                    } else if (rbutton1.isChecked()) {
                        Intent intent = new Intent(getApplicationContext(), callrecorder.class);
                        startActivity((intent));
                    }
                }
            }
        });




    }
}