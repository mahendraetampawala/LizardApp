package com.example.lockbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import org.w3c.dom.Text;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText edit1,edit2,edit3;

    RadioButton rbutton1,rbutton2;
    private RadioGroup radioGroup;
    AwesomeValidation awesomeValidation;
    SharedPreferences preferences;

    private static final String Shared_pref="second";
    private static final String KEY_NAME="name";
    private static final String KEY_call="call";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DatabaseHelper(this);


        preferences=getSharedPreferences("preferences",MODE_PRIVATE);
        boolean once2=preferences.getBoolean("once2",true);


        if(once2!=true){
            //Intent intent = new Intent(getApplicationContext(), Calculator.class);
            //startActivity(intent);
            Intent intent=new Intent(MainActivity.this,Calculator.class);
            startActivity(intent);

        }


        preferences=getSharedPreferences("preferences",MODE_PRIVATE);
        boolean once1=preferences.getBoolean("once1",true);
        if(once1!=true){
            // Intent intent = new Intent(getApplicationContext(), callrecorder.class);
            //startActivity((intent));
            Intent intent=new Intent(MainActivity.this, com.example.haider.callrecorder.Splashh.class);
            startActivity(intent);

        }


        //TextView textView5=findViewById(R.id.pwtxt2);
        //textView5.setText(R.string.Username);

        RadioButton CalcheckBox=findViewById(R.id.calculatorcheck);
        CalcheckBox.setText("Calculator");

        RadioButton ReccheckBox=findViewById(R.id.callrecordercheck);
        ReccheckBox.setText("CallRecorder");

        //TextView textView2=findViewById(R.id.pwtxt);
        //textView2.setText(R.string.pwlabel);

        //final TextView textView3=findViewById(R.id.reentertxt);
        //textView3.setText(R.string.reenterlabel);

        final Button setp=findViewById(R.id.setpassword);
        setp.setText(R.string.setpwd);

        rbutton1 = (RadioButton) findViewById(R.id.calculatorcheck);
        rbutton2 = (RadioButton) findViewById(R.id.callrecordercheck);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        edit1=(EditText)findViewById(R.id.Usernametxt);
        edit2=(EditText)findViewById(R.id.pwd);
        edit3=(EditText)findViewById(R.id.confirmpwd);

        TextView textView8=findViewById(R.id.information);
        textView8.setText("To unlock the source folder you should enter your password as the input of the calculator or the call recorder,and if your choice is calculator just press equal button to unlock,or else your choice is call recorder ,press call button to unlock your source folder.");





       /* awesomeValidation.addValidation(this,R.id.Usernametxt, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.pwd,RegexTemplate.NOT_EMPTY,R.string.Enter_a_six_digits_number);
        //awesomeValidation.addValidation(this,R.id.uppwttxt,".{6,}",R.string.invalid_password);
        awesomeValidation.addValidation(this,R.id.confirmpwd,R.id.pwd,R.string.passwords_are_not_matching);
        */


        SharedPreferences preferences=getSharedPreferences("PREF",MODE_PRIVATE);
        String FirstTime=preferences.getString("firsttimeinstall","");
        String SecondTime=preferences.getString("secondTimeInstall","");


        setp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Boolean isInserted = db.insert(edit1.getText().toString(), edit2.getText().toString());


                String username = edit1.getText().toString();
                String password = edit2.getText().toString();
                String confirm = edit3.getText().toString();
                if (username.equals("") || password.equals("") || confirm.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_LONG).show();
                    return;
                } else if (password.length() > 6) {

                    Toast.makeText(MainActivity.this, "too long", Toast.LENGTH_SHORT).show();
                }else if(password.length() < 6){
                    Toast.makeText(MainActivity.this, "too short", Toast.LENGTH_SHORT).show();
                }else if(password.equals(confirm)) {

                    if (radioGroup.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                    } else if (rbutton2.isChecked()) {
                        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("once2", false);
                        editor.apply();
                        boolean once2 = preferences.getBoolean("once2", true);


                        if (once2 != true) {
                            //Intent intent = new Intent(getApplicationContext(), Calculator.class);
                            //startActivity(intent);
                            Intent intent = new Intent(MainActivity.this, Calculator.class);
                            startActivity(intent);

                        }

                        Toast.makeText(MainActivity.this, "calculator", Toast.LENGTH_SHORT).show();
                    } else if (rbutton1.isChecked()) {
                        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("once1", false);
                        editor.apply();
                        boolean once1 = preferences.getBoolean("once1", true);
                        if (once1 != true) {
                            // Intent intent = new Intent(getApplicationContext(), callrecorder.class);
                            //startActivity((intent));
                            Intent intent = new Intent(MainActivity.this, Calculator.class);
                            startActivity(intent);

                        }

                        Toast.makeText(MainActivity.this, "callrecorder", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "password mismatch", Toast.LENGTH_SHORT).show();
                }

             /*   String username=edit1.getText().toString();
                String password=edit2.getText().toString();
                String confirm=edit3.getText().toString();

                //awesomeValidation.validate();

                if(username!=null||password!=null||confirm!=null){

                    if(password.length()>6){
                        Toast.makeText(MainActivity.this, "Password is too long", Toast.LENGTH_SHORT).show();
                    }else if(password.length()<6){
                        Toast.makeText(MainActivity.this, "Password is weak", Toast.LENGTH_SHORT).show();

                    }else if(password.equals(confirm)){


                        if(isInserted=true){

                            SharedPreferences.Editor editor= preferences.edit();
                            editor.putString(KEY_NAME,edit1.getText().toString());
                            editor.putString(KEY_PWD,edit2.getText().toString());
                            editor.apply();


                            Toast.makeText(MainActivity.this, "Registered Successfully ", Toast.LENGTH_SHORT).show();
                            Toast toast = Toast.makeText(getApplicationContext(), "Password setting", Toast.LENGTH_LONG);
                            toast.show();
                            //Intent intent=new Intent(MainActivity.this,Setpassword.class);
                            //startActivity(intent);


                        }else{

                            Toast.makeText(MainActivity.this, "Registered Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(MainActivity.this, "Password didn't match ", Toast.LENGTH_SHORT).show();
                    }



                }*/





            /*
                if(username.equals("")){
                    Toast.makeText(MainActivity.this, "please enter the username", Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(MainActivity.this, "please enter the password", Toast.LENGTH_SHORT).show();
                }else if(password.length()>6){
                    Toast.makeText(MainActivity.this, "Password is too long", Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<6){
                    Toast.makeText(MainActivity.this, "Password is weak", Toast.LENGTH_SHORT).show();
                }
                else if(confirm.equals("")) {
                    Toast.makeText(MainActivity.this, "Confirm your password", Toast.LENGTH_SHORT).show();



                }else if(password.equals(confirm)){



                  if(isInserted=true){

                        SharedPreferences.Editor editor= preferences.edit();
                        editor.putString(KEY_NAME,edit1.getText().toString());
                        editor.putString(KEY_PWD,edit2.getText().toString());
                        editor.apply();


                        Toast.makeText(MainActivity.this, "Registered Successfully ", Toast.LENGTH_SHORT).show();
                        Toast toast = Toast.makeText(getApplicationContext(), "Password setting", Toast.LENGTH_LONG);
                        toast.show();
                        Intent intent=new Intent(MainActivity.this,Setpassword.class);
                        startActivity(intent);


                    }else{

                      Toast.makeText(MainActivity.this, "Registered Error", Toast.LENGTH_SHORT).show();
                    }







                       /* Toast.makeText(MainActivity.this, "Registered Successfully ", Toast.LENGTH_SHORT).show();
                        Toast toast = Toast.makeText(getApplicationContext(), "Password setting", Toast.LENGTH_LONG);
                        toast.show();
                        Intent selectActivity = new Intent(getApplicationContext(), Setpassword.class);
                        startActivity(selectActivity); */

                  /*  }else{

                    }



                }else{
                    Toast.makeText(MainActivity.this, "Password didn't match ", Toast.LENGTH_SHORT).show();

                }
                */





               /* String username=edit1.getText().toString();
                String password=edit2.getText().toString();
                String confirmpassword=edit3.getText().toString();

                //if(username.equals("")|| password.equals("")||password.equals(""))){
                  //  Toast.makeText(getApplicationContext(),"Please fill the ")
                //}
                if(username.equals("")){
                    Toast.makeText(MainActivity.this, "please enter the username", Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(MainActivity.this, "please enter the password", Toast.LENGTH_SHORT).show();
                }else if(confirmpassword.equals("")){
                    Toast.makeText(MainActivity.this, "Confirm your password", Toast.LENGTH_SHORT).show();
                }else if(password.equals(confirmpassword)){

                    Toast.makeText(MainActivity.this, "Registered Successfully ", Toast.LENGTH_SHORT).show();
                    Toast toast = Toast.makeText(getApplicationContext(), "Password setting", Toast.LENGTH_LONG);
                    toast.show();

                    Intent selectActivity = new Intent(getApplicationContext(), Setpassword.class);
                    startActivity(selectActivity);
                }else{
                    Toast.makeText(MainActivity.this, "Password didnt match ", Toast.LENGTH_SHORT).show();

                }*/


            }



        });


    }
    public void signinBtn(View view){
        String username=edit1.getText().toString();
        String password=edit2.getText().toString();
        String confirm=edit3.getText().toString();
        if(username.equals("")||password.equals("")||confirm.equals("")){
            Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_LONG).show();
            return;
        }else if(radioGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
        }else if (rbutton2.isChecked()){
            Toast.makeText(MainActivity.this, "calculator", Toast.LENGTH_SHORT).show();
        }else if (rbutton1.isChecked()){
            Toast.makeText(MainActivity.this, "callrecorder", Toast.LENGTH_SHORT).show();
        }


    }
}