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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

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
    private List<Integer> answersA,answersB,answersC;
    private List<Integer> correctAnswersOne, correctAnswersTwo;
    private int qid = 0;
    private Question currentQ;
    private TextView txtQuestion, tvVrijeme;
    private CheckBox rda, rdb, rdc;
    private Button butNext,butPrev;
    private Intent intent;
    private ImageView questionImage;
    private Spinner questionPicker;
    private CountDownTimer countDownTimer;

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kviz);
        rootView = findViewById(R.id.root_view);
        setBackground();
        DbHelper db = DbHelper.getInstance(this);

        quesList = db.getAllQuestions();
        answersA = new ArrayList<Integer>();
        answersB = new ArrayList<Integer>();
        answersC = new ArrayList<Integer>();
        correctAnswersOne = new ArrayList<Integer>();
        correctAnswersTwo = new ArrayList<Integer>();
        initializeListOfAnswers();

        currentQ = quesList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.textView1);
        rda = (CheckBox) findViewById(R.id.checkBox0);
        rda.setOnCheckedChangeListener(checkedChangeListener);
        rdb = (CheckBox) findViewById(R.id.checkBox1);
        rdb.setOnCheckedChangeListener(checkedChangeListener);
        rdc = (CheckBox) findViewById(R.id.checkBox2);
        rdc.setOnCheckedChangeListener(checkedChangeListener);
        butNext = (Button) findViewById(R.id.bNext);
        butPrev = (Button) findViewById(R.id.bPrev);
        tvVrijeme = (TextView) findViewById(R.id.satPrikaz);
        intent = new Intent(this, ResultActivity.class);
        questionImage = (ImageView) findViewById(R.id.iv_question_image);
        questionPicker = (Spinner) findViewById(R.id.question_picker);

        setupQuestionPickerSpinner();
        setNextQuestionView();


        countDownTimer = new CountDownTimer(1200000, 1000) {

            public void onTick(long millisUntilFinished) {

                tvVrijeme.setText("Vrijeme: " + TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) + ":" + (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

            }

            public void onFinish() {
                for (int i = 0; i < quesList.size(); i++) {
                    if (quesList.get(i).getOneOrTwoAnswers() == 1) {

                        if (answersA.get(i).equals(correctAnswersOne.get(i))) {
                            score++;
                        }
                         if (answersB.get(i).equals(correctAnswersOne.get(i))){
                            score++;
                        }
                        if (answersC.get(i).equals(correctAnswersOne.get(i))){
                            score++;
                        }
                    }
                    if (quesList.get(i).getOneOrTwoAnswers() == 2) {

                        if (answersA.get(i).equals(correctAnswersOne.get(i)) && answersB.get(i).equals(correctAnswersTwo.get(i))) {
                            score++;
                            score++;
                        }
                        if (answersA.get(i).equals(correctAnswersOne.get(i)) && answersC.get(i).equals(correctAnswersTwo.get(i))) {
                            score++;
                            score++;
                        }
                        if (answersB.get(i).equals(correctAnswersOne.get(i)) && answersA.get(i).equals(correctAnswersTwo.get(i))) {
                            score++;
                            score++;
                        }
                        if (answersB.get(i).equals(correctAnswersOne.get(i)) && answersC.get(i).equals(correctAnswersTwo.get(i))) {
                            score++;
                            score++;
                        }
                        if (answersC.get(i).equals(correctAnswersOne.get(i)) && answersA.get(i).equals(correctAnswersTwo.get(i))) {
                            score++;
                            score++;
                        }
                        if (answersC.get(i).equals(correctAnswersOne.get(i)) && answersB.get(i).equals(correctAnswersTwo.get(i))) {
                            score++;
                            score++;
                        }
                    }
                }
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.KEY_SCORE, score);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        };

        countDownTimer.start();

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (qid == quesList.size() - 1) {
                    score = 0;
                    for (int i = 0; i < quesList.size(); i++) {
                        if (quesList.get(i).getOneOrTwoAnswers() == 1) {

                            if(answersA.get(i) != -1 && answersB.get(i) != -1 && answersC.get(i) != -1)
                            {
                            }
                            else if (answersA.get(i) != -1 && answersB.get(i) != -1){
                            }
                            else if (answersA.get(i) != -1 && answersC.get(i) != -1){
                            }
                            else if (answersB.get(i) != -1 && answersC.get(i) != -1){
                            }
                            else
                            {
                                if (answersA.get(i).equals(correctAnswersOne.get(i))) {
                                    score +=1;
                                }
                                if (answersB.get(i).equals(correctAnswersOne.get(i))){
                                    score +=1;
                                }
                                if (answersC.get(i).equals(correctAnswersOne.get(i))){
                                    score +=1;
                                }
                            }

                        }
                        if (quesList.get(i).getOneOrTwoAnswers() == 2) {
                            if(answersA.get(i) != -1 && answersB.get(i) != -1 && answersC.get(i) != -1)
                            {
                            }else {
                                if (answersA.get(i).equals(correctAnswersOne.get(i)) && answersB.get(i).equals(correctAnswersTwo.get(i))) {

                                    score +=2;
                                }
                                if (answersA.get(i).equals(correctAnswersOne.get(i)) && answersC.get(i).equals(correctAnswersTwo.get(i))) {

                                    score +=2;
                                }
                                if (answersB.get(i).equals(correctAnswersOne.get(i)) && answersA.get(i).equals(correctAnswersTwo.get(i))) {

                                    score +=2;
                                }
                                if (answersB.get(i).equals(correctAnswersOne.get(i)) && answersC.get(i).equals(correctAnswersTwo.get(i))) {

                                    score +=2;
                                }
                                if (answersC.get(i).equals(correctAnswersOne.get(i)) && answersA.get(i).equals(correctAnswersTwo.get(i))) {

                                    score +=2;
                                }
                                if (answersC.get(i).equals(correctAnswersOne.get(i)) && answersB.get(i).equals(correctAnswersTwo.get(i))) {

                                    score +=2;
                                }
                            }


                        }
                    }

                    countDownTimer.cancel();
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.KEY_SCORE, score);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                } else{
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
            answersA.add(i, -1);
            answersB.add(i, -1);
            answersC.add(i, -1);
            correctAnswersOne.add(i, quesList.get(i).getCorrectAnswerOne());
            correctAnswersTwo.add(i, quesList.get(i).getCorrectAnswerTwo());
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



                if (answersA.get(qid) == 0){
                    rda.setChecked(true);
                   }
                 else
                {

                rda.setChecked(false);
                }
                if (answersB.get(qid) == 1){
                    rdb.setChecked(true);
                   }
                 else
                rdb.setChecked(false);

                if (answersC.get(qid) == 2){
                    rdc.setChecked(true);
                    }
                 else
                rdc.setChecked(false);

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
                    answersA.set(qid, 0);

                } if (currentQ.getOPTB().equals(text)) {
                    answersB.set(qid, 1);

                } if (currentQ.getOPTC().equals(text)) {
                    answersC.set(qid, 2);

                }



            }else{
                String text = (String) compoundButton.getText();

                if (currentQ.getOPTA().equals(text)) {
                    if(rda.isChecked()){

                    }else
                        answersA.set(qid, -1);

                } if (currentQ.getOPTB().equals(text)) {
                    if(rdb.isChecked()){

                    }else
                        answersB.set(qid, -1);

                } if (currentQ.getOPTC().equals(text)) {
                    if(rdc.isChecked()){

                    }else
                        answersC.set(qid, -1);

                }
            }


            }

        };
     }