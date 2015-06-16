package com.jakey.motivateme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;


public class Signin extends Activity {

    private EditText username=null;
    private  EditText pass=null;
    private Button login;
    private EditText regusername=null;
    private EditText regpass=null;
    private EditText confpass=null;
    private EditText email=null;
    private Button register;
    private static int userId;

    DBAdapter database = new DBAdapter(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jakey.motivateme.R.layout.activity_signin);
        username = (EditText) findViewById(com.jakey.motivateme.R.id.txtusername);
        pass = (EditText) findViewById(com.jakey.motivateme.R.id.txtpass);
        login = (Button) findViewById(com.jakey.motivateme.R.id.signinbtt);


    }

    public void AttemptLogin(View view) throws SQLException {

        String UserName = username.getText().toString();
        String Pass = pass.getText().toString();
        username.setText("");
        pass.setText("");

        database.open();
        int currentUserId= database.getUser(UserName, Pass);
        database.close();

        setCurrentUser(currentUserId);

        if (currentUserId > 0)
        {
            Toast.makeText(this, "Welcome Back, "+UserName+"!",
                    Toast.LENGTH_SHORT).show();
            Intent toHome = new Intent(this, Main.class);
            startActivity(toHome);

        }
        else {
            Toast.makeText(this, "Log in failed",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void setCurrentUser(int currentUserId)
    {
        userId=currentUserId;
    }

    public static int getCurrentUser()
    {
        return userId;
    }

    public void register(View view)
    {
       setContentView(com.jakey.motivateme.R.layout.activity_registration);

    }


    public void createUser(View v) throws SQLException

    {
        //get data from form
        regusername = (EditText)findViewById(com.jakey.motivateme.R.id.txtregusername);
        regpass = (EditText)findViewById(com.jakey.motivateme.R.id.txtregpass);
        confpass= (EditText)findViewById(com.jakey.motivateme.R.id.txtconfpass);
        email= (EditText)findViewById(com.jakey.motivateme.R.id.txtemail);
        register= (Button)findViewById(com.jakey.motivateme.R.id.registerbtt);

        String myUsername=regusername.getText().toString();
        String myPass=regpass.getText().toString();
       // String myConfPass=confpass.getText().toString();
        String myEmail=email.getText().toString();

            database.open();
            database.insertUser(myUsername, myPass, myEmail);
            database.close();

            regusername.setText("");
            regpass.setText("");
            pass.setText("");
            confpass.setText("");
            email.setText("");
            Toast.makeText(Signin.this, "User Created",
                    Toast.LENGTH_LONG).show();
            Intent toHome = new Intent(this, Signin.class);
            startActivity(toHome);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.jakey.motivateme.R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == com.jakey.motivateme.R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
