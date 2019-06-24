package com.manoj.bhat.oth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

public class Login extends AppCompatActivity {

    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText) findViewById(R.id.usr);
        pass = (EditText) findViewById(R.id.pass);
        Random rand = new Random();
        int n = rand.nextInt(200);
        QuestionDecide.ver = n%6;
    }

    public void log(View view) {
        String username, password;
        username = user.getText().toString();
        password = pass.getText().toString();
        if (TextUtils.isEmpty(username)) {
            user.setError("This field must not be empty");
        }
        else if (TextUtils.isEmpty(password)) {
            pass.setError("This field must not be empty");
        }
        else {
            Intent intent = new Intent(this,Story.class);
            startActivity(intent);
        }
    }
}
