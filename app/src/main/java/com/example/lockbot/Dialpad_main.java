package com.example.haider.callrecorder;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Dialpad_main extends AppCompatActivity {
    EditText etNumber;
    ImageButton btCall;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.main_has);

                etNumber =findViewById(R.id.et_number);
                btCall =findViewById(R.id.bt_call);

                btCall.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onClick(View v) {
                        String phone = etNumber.getText().toString();
                        if (phone.isEmpty()){
                            Toast.makeText(getApplicationContext(), "Please Enter Number!"
                                    ,Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String s ="tel:" +phone;
                            Intent intent =new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse(s));
                            startActivity(intent);
                        }
                    }
                });
            }
        }