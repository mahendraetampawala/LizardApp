package com.example.lockbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView1=findViewById(R.id.setpwlabel);
        textView1.setText(R.string.label1);
        textView1.setTextColor(Color.BLACK);

        TextView textView2=findViewById(R.id.pwtxt);
        textView2.setText(R.string.pwlabel);

        final TextView textView3=findViewById(R.id.reentertxt);
        textView3.setText(R.string.reenterlabel);

        final Button setpwdbtn=findViewById(R.id.setpassword);
        setpwdbtn.setText(R.string.setpwd);

        CheckBox check1=findViewById(R.id.checkBox1);
        check1.setText(R.string.checkbox);

        setpwdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(getApplicationContext(), "Password setting", Toast.LENGTH_LONG);
                toast.show();

                Intent selectActivity = new Intent(getApplicationContext(), Setpassword.class);
                startActivity(selectActivity);
            }

        });

    }
}