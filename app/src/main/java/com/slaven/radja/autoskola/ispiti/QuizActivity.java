package com.slaven.radja.autoskola.ispiti;

/**
 * Created by Computer on 06/08/2014.
 */
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.helpers.DisplayHelper;

public class QuizActivity extends Activity implements View.OnClickListener{
    List<Question> quesList;
    int score = 0;
    int qid = 0;
    Question currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc;
    Button butNext;
    ImageView questionImage;
    LinearLayout questionPicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        DbHelper db = new DbHelper(this);
        quesList = db.getAllQuestions();
        currentQ = quesList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.textView1);
        rda = (RadioButton) findViewById(R.id.radio0);
        rdb = (RadioButton) findViewById(R.id.radio1);
        rdc = (RadioButton) findViewById(R.id.radio2);
        butNext = (Button) findViewById(R.id.bNext);
        questionImage = (ImageView) findViewById(R.id.iv_question_image);
        questionPicker = (LinearLayout) findViewById(R.id.ll_question_picker);
        setQuestionView();
        setQuestionPicker();

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                Log.d("yourans", currentQ.getANSWER() + " " + answer.getText());
                if(currentQ.getANSWER().equals(answer.getText()))
                {
                    score++;
                    Log.d("score", "Your score"+score);
                }
                if(qid<5){
                    currentQ=quesList.get(qid);
                    setQuestionView();
                }else{
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();
                }
            }
        });



    }

    private void setQuestionPicker() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0 , DisplayHelper.getDpToPx(this, 30));
        params.weight = 1;
        params.rightMargin = DisplayHelper.getDpToPx(this, 5);
        for(int i = 0; i < quesList.size(); i++) {
            final TextView textView = new TextView(this);
            final int position = i;
            textView.setText(Integer.toString(i + 1));
            textView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            textView.setBackgroundColor(getResources().getColor(R.color.light_gray));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentQ = quesList.get(position);
                    qid = position;
                    setQuestionView();
                }
            });
            questionPicker.addView(textView, params);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }

    private void setQuestionView() {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        if (currentQ.hasImage()) {
            questionImage.setImageResource(currentQ.getImg_ID());
            questionImage.setVisibility(View.VISIBLE);
        } else {
            questionImage.setVisibility(View.GONE);
        }
        qid++;
  }


    @Override
    public void onClick(View v) {

    }
}