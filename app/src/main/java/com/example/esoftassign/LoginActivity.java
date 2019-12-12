package com.example.esoftassign;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonlogin;
    private EditText editTextUserName,editTextPassword;
    private String username,password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        editTextUserName = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);


        buttonlogin = findViewById(R.id.btnlogin);

        buttonlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnlogin)
        {
            username = editTextUserName.getText().toString();
            password=editTextPassword.getText().toString();
            if(checkvalidate()) {

                if(username.equals("softwarica") && password.equals("coventry")) {
                    Intent intent = new Intent(this, DashboardActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(this, "Either username or Password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    private boolean checkvalidate(){
        if(TextUtils.isEmpty(username))
        {
            editTextUserName.setError("Please enter a username");
            editTextUserName.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(password))
        {
            editTextPassword.setError("Please enter password");
            editTextPassword.requestFocus();
            return false;
        }


        return  true;
    }
}
