package com.manoj.bhat.oth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;

public class AnsSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ans_success);
    }

    // this activity confirms the successful completion of a question

    public void success(View view) {
        Intent intent= new Intent(this,Question.class);
        startActivity(intent);
    }
}
