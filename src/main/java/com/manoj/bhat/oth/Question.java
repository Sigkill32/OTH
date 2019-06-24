package com.manoj.bhat.oth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Question extends AppCompatActivity {

    EditText ans;
    TextView ques;
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ans = (EditText) findViewById(R.id.ans);
        ques = (TextView) findViewById(R.id.ques);
        n = QuestionDecide.ver;
        for (int i=0;i<8;i++) {
            if (QuestionDecide.id[n][i] != 0) {
                setQuesAns(QuestionDecide.id[n][i]);
                QuestionDecide.id[n][i] = 0;
                break;
            }
        }
    }

    public void sub(View view) {
        String answer;
        answer = ans.getText().toString();
        /*if (TextUtils.isEmpty(answer)) {
            ans.setError("This field cannot be empty");
        }*/

        if (answer.equals(QuestionDecide.a[QuestionDecide.currentQues])) {
            Intent intent = new Intent(this,Distance.class);
            double[] coordinates = new double[2];
            coordinates[0] = QuestionDecide.coordinates[QuestionDecide.currentQues][0];
            coordinates[1] = QuestionDecide.coordinates[QuestionDecide.currentQues][1];
            intent.putExtra("coord",coordinates);
            startActivity(intent);
            //Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        }
        else if (!answer.equals(QuestionDecide.a[n])){
            Toast.makeText(this,"Your answer is incorrect",Toast.LENGTH_LONG).show();
        }
    }

    public void setQuesAns(int n) {
        ques.setText(QuestionDecide.q[n]);
        QuestionDecide.currentQues = n;
    }
}
