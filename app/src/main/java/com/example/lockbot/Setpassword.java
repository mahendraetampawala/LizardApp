package com.example.lockbot;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Setpassword extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper db;
    private RadioGroup radioGroup;
    private RadioButton rbutton1,rbutton2;
    private Button next;
    SharedPreferences preferences;
    TextView textview;
    String password;
    EditText edit1,edit2;



    private static final String Shared_pref="second";
    private static final String KEY_NAME="name";
    private static final String KEY_call="call";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent inte = getIntent();
        String message = inte.getStringExtra("password");
        TextView textView = new TextView(this);
        //textView.setTextSize(45);
        textView.setText(message);
        //setContentView(textView);


        //Cursor result = obj.viewdata(t1.getText().toString());

        db = new DatabaseHelper(this);
        Cursor cursor = db.showname();


        preferences=getSharedPreferences("preferences",MODE_PRIVATE);
        boolean once2=preferences.getBoolean("once2",true);


        if(once2!=true){
            //Intent intent = new Intent(getApplicationContext(), Calculator.class);
            //startActivity(intent);
            Intent intent=new Intent(Setpassword.this,Calculator.class);
           startActivity(intent);

        }


        preferences=getSharedPreferences("preferences",MODE_PRIVATE);
        boolean once1=preferences.getBoolean("once1",true);
        if(once1!=true){
           // Intent intent = new Intent(getApplicationContext(), callrecorder.class);
            //startActivity((intent));
                Intent intent=new Intent(Setpassword.this,callrecorder.class);
               startActivity(intent);

        }




        if (cursor.getCount() == 0) {
            //Toast.makeText(getApplicationContext(), "No name", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {

                // textview.setText(cursor.getString(0));
                Toast.makeText(getApplicationContext(), "Welcome" + cursor.getString(0), Toast.LENGTH_SHORT).show();

                //tv.setText("Hello"+cursor.getString(0));

            }
        }


   /*listView=(ListView)findViewById(R.id.viewname);
        TextView viewname=findViewById(R.id.viewname);
       db=new DatabaseHelper(getApplicationContext());
       sqLiteDatabase=db.getReadableDatabase();
        Cursor cursor=db.viewdata();

   DatabaseHelper db=new DatabaseHelper(this);
        TextView viewname=findViewById(R.id.viewname);
        Cursor cursor=db.viewdata();
        StringBuilder stringBuilder=new StringBuilder();




        //StringBuilder stringBuilder=new StringBuilder();

        while (cursor.moveToNext()){
            stringBuilder.append("Hello" +cursor.getString(0));
        }


        viewname.setText(stringBuilder);

    EditText t1=findViewById(R.id.Usernametxt);
    TextView tv=findViewById(R.id.viewname);

            obj = new DatabaseHelper(this);
            Cursor result = obj.viewdata(t1.getText().toString());
            while (result.moveToNext()) {
                tv.setText(result.getString(0));
            }
*/
        ///////////////////////////////////////

        setContentView(R.layout.activity_setpassword);

        TextView textView01 = findViewById(R.id.selectchoice);
        textView01.setText(R.string.label2);

        RadioButton radio1 = findViewById(R.id.recorderrb);
        radio1.setText(R.string.radiobtn2);

        RadioButton radio2 = findViewById(R.id.calrb);
        radio2.setText(R.string.radiobtn1);

        TextView auto1 = findViewById(R.id.autocompletetxt);
        auto1.setText(R.string.autocomplete1);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rbutton1 = (RadioButton) findViewById(R.id.recorderrb);
        rbutton2 = (RadioButton) findViewById(R.id.calrb);
        next = (Button) findViewById(R.id.proceedbtn);


        /*preferences=getSharedPreferences(Shared_pref_name,MODE_PRIVATE);
        String once=preferences.getString(KEY_NAME,null);
        if(once!=null){

            Intent intent=new Intent(Setpassword.this,Calculator.class);
            //intent.putExtra("passwordset",textView.getText().toString());

            startActivity(intent);
        }


       preferences=getSharedPreferences(Shared_pref_name,MODE_PRIVATE);
        String once1=preferences.getString(KEY_call,null);
        if(once1!=null){

            Intent intent=new Intent(Setpassword.this,callrecorder.class);
            //intent.putExtra("passwordset",textView.getText().toString());
            startActivity(intent);
        }
  */    


      /*  if(once2!=true){
            //Intent intent = new Intent(getApplicationContext(), Calculator.class);
            //startActivity(intent);
            Intent intent=new Intent(Setpassword.this,Calculator.class);
           startActivity(intent);

        }


        preferences=getSharedPreferences("preferences",MODE_PRIVATE);
        boolean once1=preferences.getBoolean("once1",true);
        if(once1!=true){
           // Intent intent = new Intent(getApplicationContext(), callrecorder.class);
            //startActivity((intent));
                Intent intent=new Intent(Setpassword.this,callrecorder.class);
               startActivity(intent);

        }*/


        // edit1=(EditText)findViewById(R.id.Usernametxt);
        //edit2=(EditText)findViewById(R.id.pwd);



            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (radioGroup.getCheckedRadioButtonId() == -1) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please select one of these", Toast.LENGTH_LONG);
                        toast.show();
                    } else if (rbutton2.isChecked()) {

                        preferences=getSharedPreferences("preferences",MODE_PRIVATE);
                        SharedPreferences.Editor editor= preferences.edit();
                        editor.putBoolean("once2",false);
                        editor.apply();
                        boolean once2=preferences.getBoolean("once2",true);


                        if(once2!=true){
                            //Intent intent = new Intent(getApplicationContext(), Calculator.class);
                            //startActivity(intent);
                            Intent intent=new Intent(Setpassword.this,Calculator.class);
                            startActivity(intent);

                        }




                       // Intent intent = new Intent(getApplicationContext(), Calculator.class);
                        //startActivity(intent);
                    } else if (rbutton1.isChecked()) {

                        preferences=getSharedPreferences("preferences",MODE_PRIVATE);
                        SharedPreferences.Editor editor= preferences.edit();
                        editor.putBoolean("once1",false);
                        editor.apply();
                        boolean once1=preferences.getBoolean("once1",true);
                        if(once1!=true){
                            // Intent intent = new Intent(getApplicationContext(), callrecorder.class);
                            //startActivity((intent));
                            Intent intent=new Intent(Setpassword.this,callrecorder.class);
                            startActivity(intent);

                        }


                        //Intent intent = new Intent(getApplicationContext(), callrecorder.class);
                        //startActivity((intent));
                    }
                }

            });



    }
    @Override
    public void onBackPressed(){

    }


}