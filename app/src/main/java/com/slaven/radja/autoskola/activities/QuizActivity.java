package com.slaven.radja.autoskola.activities;

/**
 * Created by Computer on 06/08/2014.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.slaven.radja.autoskola.Constants;
import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.helpers.DbHelper;
import com.slaven.radja.autoskola.models.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QuizActivity extends BaseActivity {

    private List<Question> quesList;
    private List<Integer> answers;
    private List<Integer> correctAnswers;
    private int qid = 0;
    private Question currentQ;
    private TextView txtQuestion, tvVrijeme;
    private RadioButton rda, rdb, rdc;
    private Button butNext;
    private Intent intent;
    private ImageView questionImage;
    private Spinner questionPicker;
    private CountDownTimer countDownTimer;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kviz);
        rootView = findViewById(R.id.root_view);
        setBackground();
        DbHelper db = DbHelper.getInstance(this);

        quesList = db.getAllQuestions();
        answers = new ArrayList<Integer>();
        correctAnswers = new ArrayList<Integer>();
        initializeListOfAnswers();
        currentQ = quesList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.textView1);
        rda = (RadioButton) findViewById(R.id.radio0);
        rda.setOnCheckedChangeListener(checkedChangeListener);
        rdb = (RadioButton) findViewById(R.id.radio1);
        rdb.setOnCheckedChangeListener(checkedChangeListener);
        rdc = (RadioButton) findViewById(R.id.radio2);
        rdc.setOnCheckedChangeListener(checkedChangeListener);
        butNext = (Button) findViewById(R.id.bNext);
        Button butPrev = (Button) findViewById(R.id.bPrev);
        tvVrijeme = (TextView) findViewById(R.id.satPrikaz);
        intent = new Intent(this, ResultActivity.class);
        questionImage = (ImageView) findViewById(R.id.iv_question_image);
        questionPicker = (Spinner) findViewById(R.id.question_picker);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        setupQuestionPickerSpinner();
        setNextQuestionView();

        countDownTimer = new CountDownTimer(1200000, 1000) {

            public void onTick(long millisUntilFinished) {

                tvVrijeme.setText("Vrijeme: " + TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) + ":" + (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

            }

            public void onFinish() {
                startActivity(intent);
                finish();
            }
        };

        countDownTimer.start();

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ako sam na zadnjem pitanju
                if (qid == quesList.size() - 1) {
                    int score = 0;
                    for (int i = 0; i < quesList.size(); i++) {
                        if (answers.get(i).equals(correctAnswers.get(i))) {
                            score++;
                        }
                    }
                    countDownTimer.cancel();
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.KEY_SCORE, score);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                } else {
                    qid++;
                    currentQ = quesList.get(qid);
                    setNextQuestionView();
                }
            }

        });

        butPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qid == 0) {
                    return;
                }
                qid--;
                currentQ = quesList.get(qid);
                setPrevQuestionView();
            }
        });
    }

    private void initializeListOfAnswers() {
        for (int i = 0; i < quesList.size(); i++) {
            // -1 znaci da je pitanje neodgovoreno
            answers.add(i, -1);
            correctAnswers.add(i, quesList.get(i).getCorrectAnswer());
        }
    }

    private void setupQuestionPickerSpinner() {
        List<String> questions = new ArrayList<String>();
        for (int i = 0; i < quesList.size(); i++) {
            questions.add(String.valueOf(i + 1));

        }
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_row, questions);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item_row);
        questionPicker.setAdapter(spinnerArrayAdapter);
        questionPicker.setOnItemSelectedListener(questionPickerListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            countDownTimer.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setNextQuestionView() {
        setQuestionTextAndAnswers();
        if (qid == quesList.size() - 1) {
            butNext.setText(getString(R.string.finish_quiz));
        } else {
            butNext.setText(getString(R.string.next));
        }
    }

    private void setPrevQuestionView() {
        setQuestionTextAndAnswers();
        butNext.setText(getString(R.string.next));
    }

    private void setQuestionTextAndAnswers() {
        if (answers.get(qid) != -1) {
            if (answers.get(qid) == 0) {
                rda.toggle();
            } else if (answers.get(qid) == 1) {
                rdb.toggle();
            } else {
                rdc.toggle();
            }
        } else {
            radioGroup.clearCheck();
        }
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
    }

    private AdapterView.OnItemSelectedListener questionPickerListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
            ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
            qid = position;
            currentQ = quesList.get(qid);
            setNextQuestionView();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
            if (checked) {
                String text = (String) compoundButton.getText();
                if (currentQ.getOPTA().equals(text)) {
                    answers.set(qid, 0);
                } else if (currentQ.getOPTB().equals(text)) {
                    answers.set(qid, 1);
                } else {
                    answers.set(qid, 2);
                }
            }
        }
    };
}