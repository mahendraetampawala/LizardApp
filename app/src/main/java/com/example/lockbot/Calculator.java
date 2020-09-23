package com.example.lockbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {
    DatabaseHelper db;
    TextView edittext1,textview,msgpwd,edittext2,edittext3,edittext4;

    TextView assigned;
    double in1 = 0, i2 = 0;
    boolean Add, Sub, Multiply, Divide, Remainder, deci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


        Intent inte = getIntent();
        String messagepwd = inte.getStringExtra("passwordset");
        msgpwd = new TextView(this);
        //textView.setTextSize(45);
        msgpwd.setText(messagepwd);
        //setContentView(msgpwd);
       // String confirm=msgpwd.getText().toString();
       //Toast.makeText(getApplicationContext(),""+msgpwd,Toast.LENGTH_SHORT).show();

/*
       db=new DatabaseHelper(this);
        Cursor cursor=db.showname();

        if(cursor.getCount()==0){
            //Toast.makeText(getApplicationContext(), "No name", Toast.LENGTH_SHORT).show();
        }else{
            StringBuffer buffer=new StringBuffer();
            while (cursor.moveToNext()){
                // tv.setText(cursor.getString(0));

               // Toast.makeText(getApplicationContext(),"Welcome "+cursor.getString(1),Toast.LENGTH_SHORT).show();


                //tv.setText("Hello"+cursor.getString(0));

            }
        }*/


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
        Button button_Div=findViewById(R.id.btndivide);
        button_Div.setText(R.string.caldivide);
        Button buttonmultiply=findViewById(R.id.btnmultiply);
        buttonmultiply.setText(R.string.calmultiply);
        Button buttondelete=findViewById(R.id.btndelete);
        buttondelete.setText(R.string.caldelete);
        Button buttonequal=findViewById(R.id.btnequal);
        buttonequal.setText(R.string.calequal);





        edittext1 = (TextView) findViewById(R.id.display);



        edittext3=(TextView)findViewById(R.id.display3);



       /* String pwd=edittext1.getText().toString();
        db=new DatabaseHelper(this);
        Cursor cursor=db.showname();

        if(cursor.getCount()==0){
            //Toast.makeText(getApplicationContext(), "No name", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                // tv.setText(cursor.getString(0));
                String check=cursor.getString(1);
                if (check==pwd){
                    Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(),"Welcome "+cursor.getString(1),Toast.LENGTH_SHORT).show();

                //tv.setText("Hello"+cursor.getString(0));

            }
        }*/





        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext1.setText(edittext1.getText() + "1");


            }
        });

        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext1.setText(edittext1.getText() + "2");
            }
        });

        button03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext1.setText(edittext1.getText() + "3");
            }
        });

        button04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext1.setText(edittext1.getText() + "4");
            }
        });

        button05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext1.setText(edittext1.getText() + "5");
            }
        });

        button06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext1.setText(edittext1.getText() + "6");
            }
        });

        button07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext1.setText(edittext1.getText() + "7");
            }
        });

        button08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext1.setText(edittext1.getText() + "8");
            }
        });

        button09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext1.setText(edittext1.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext1.setText(edittext1.getText() + "0");
            }
        });
/*
        String pwd=edittext1.getText().toString();
        if (db.login(pwd)==true){
            Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_SHORT).show();
        }
*/


        buttonplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String pwd=edittext1.getText().toString();
                if(db.login(pwd)){
                    Toast.makeText(getApplicationContext(),"DONE ",Toast.LENGTH_SHORT).show();
                }*/


                if (edittext1.getText().length() != 0) {
                    in1 = Float.parseFloat(edittext1.getText() + "");
                    Add = true;
                    deci = false;
                    edittext1.setText(null);
                }
            }
        });

        buttonminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittext1.getText().length() != 0) {
                    in1 = Float.parseFloat(edittext1.getText() + "");
                    Sub = true;
                    deci = false;
                    edittext1.setText(null);
                }
            }
        });

        buttonmultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittext1.getText().length() != 0) {
                    in1 = Float.parseFloat(edittext1.getText() + "");
                    Multiply = true;
                    deci = false;
                    edittext1.setText(null);
                }
            }
        });

        button_Div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittext1.getText().length() != 0) {
                    in1 = Float.parseFloat(edittext1.getText() + "");
                    Divide = true;
                    deci = false;
                    edittext1.setText(null);
                }
            }
        });

     /*   button_Remainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittext1.getText().length() != 0) {
                    in1 = Float.parseFloat(edittext1.getText() + "");
                    Remainder = true;
                    deci = false;
                    edittext1.setText(null);
                }
            }
        }); */
        db=new DatabaseHelper(this);
        buttonequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (Add || Sub || Multiply || Divide || Remainder) {
                    i2 = Float.parseFloat(edittext1.getText() + "");
                    edittext1.setText("");
                }

                if (Add) {

                    edittext3.setText(in1 + i2 + "");
                    Add = false;
                   //Boolean isInsertedAns=db.insertans(edittext3.getText().toString());

                }

                if (Sub) {

                    edittext3.setText(in1 - i2 + "");
                    Sub = false;
                    //Boolean isInsertedAns=db.insertans(edittext3.getText().toString());
                }

                if (Multiply) {
                    edittext3.setText(in1 * i2 + "");
                    Multiply = false;
                    //Boolean isInsertedAns=db.insertans(edittext3.getText().toString());
                }

                if (Divide) {
                    edittext3.setText(in1 / i2 + "");
                    Divide = false;
                   // Boolean isInsertedAns=db.insertans(edittext3.getText().toString());
                }
                if (Remainder) {
                    edittext3.setText(in1 % i2 + "");
                    Remainder = false;
                    //Boolean isInsertedAns=db.insertans(edittext3.getText().toString());
                }
                String pwd=edittext1.getText().toString();



                if(db.login(pwd)){
                    Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), resourceManager.class);
                    startActivity(intent);
                    edittext1.setText("");
                }


            }







        });

        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext1.setText("");
                edittext3.setText("");
                in1 = 0.0;
                i2 = 0.0;
            }
        });

        buttondot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deci) {
                    //do nothing or you can show the error
                } else {
                    edittext1.setText(edittext1.getText() + ".");
                    deci = true;
                }

            }
        });
    }

    @Override
    public void onBackPressed(){

    }

}