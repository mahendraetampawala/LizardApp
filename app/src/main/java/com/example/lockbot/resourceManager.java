package com.example.lockbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import org.w3c.dom.Text;

import java.util.prefs.Preferences;

public class resourceManager extends AppCompatActivity {
    DatabaseHelper db;
    EditText edit2,edit3,edit1;
    SharedPreferences preferences;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_manager);
        db=new DatabaseHelper(this);


        Cursor cursor = db.showname();

        if (cursor.getCount() == 0) {
            //Toast.makeText(getApplicationContext(), "No name", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {

                // textview.setText(cursor.getString(0));
                //Toast.makeText(getApplicationContext(), "Welcome" + cursor.getString(0), Toast.LENGTH_SHORT).show();

                //tv.setText("Hello"+cursor.getString(0));



            }
        }







        final Button updatingbtn=findViewById(R.id.updatebtn);
        updatingbtn.setText(R.string.setpwd);



        edit2=(EditText)findViewById(R.id.uppwttxt);
        edit3=(EditText)findViewById(R.id.confirmupdate);
        edit1=(EditText)findViewById(R.id.UPUsernametxt);

        //db = new DatabaseHelper(this);


        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);


        awesomeValidation.addValidation(this,R.id.UPUsernametxt, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.confirmupdate,RegexTemplate.NOT_EMPTY,R.string.Enter_a_six_digits_number);
        //awesomeValidation.addValidation(this,R.id.uppwttxt,".{6,}",R.string.invalid_password);
        awesomeValidation.addValidation(this,R.id.confirmupdate,R.id.uppwttxt,R.string.passwords_are_not_matching);

        db = new DatabaseHelper(resourceManager.this);
        updatingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Boolean ischeck = db.checking(edit1.getText().toString());
               // boolean isExist = db.checkUserExist(edit1.getText().toString());
                Boolean isupdated = db.update(edit2.getText().toString(), edit1.getText().toString());

                String username = edit1.getText().toString();
                String password = edit2.getText().toString();
                String confirm = edit3.getText().toString();

            awesomeValidation.validate();


            /*
            if(isExist){
                    Intent intent = new Intent(Loginactivity.this, MainActivity.class);
                    intent.putExtra("username", edtUsername.getText().toString());
                    startActivity(intent);
                } else {
                    edtPassword.setText(null);
                    Toast.makeText(Loginactivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                }


                /////////

                if(db.checking(username)){
                    Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), resourceManager.class);
                    startActivity(intent);
                    edittext1.setText("");
                }

            */



                    // if (username != null || password != null || confirm != null) {

                  if (password.length() > 6) {
                    Toast.makeText(resourceManager.this, "Password is too long", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(resourceManager.this, "Password is weak", Toast.LENGTH_SHORT).show();

                } else if (password.equals(confirm)) {

                    if(db.checking(username)) {
                        if (isupdated = true) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Password setting", Toast.LENGTH_LONG);
                            toast.show();

                            Toast.makeText(resourceManager.this, "Updated Successfully ", Toast.LENGTH_SHORT).show();
                            edit1.setText("");
                            edit2.setText("");
                            edit3.setText("");


                        } else {

                            Toast.makeText(resourceManager.this, "updating Error", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(resourceManager.this, "Invalid username", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(resourceManager.this, "Password mismatch ", Toast.LENGTH_SHORT).show();
                }






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
   /* public void onlyonce(){
        SharedPreferences preferences=getSharedPreferences("PREFERENCE",MODE_PRIVATE);
        String FirstTime=preferences.getString("FirstTimeInserted ","");


        if(FirstTime.equals("Yes")){
            Intent intent=new Intent(MainActivity.this,Setpassword.class);
            startActivity(intent);
        }else{
            SharedPreferences.Editor editor= preferences.edit();
            editor.putString("FirstTimeInserted","Yes");
            editor.apply();
        }

    } */
}