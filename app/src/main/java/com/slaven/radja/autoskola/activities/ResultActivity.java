package com.slaven.radja.autoskola.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.slaven.radja.autoskola.Constants;
import com.slaven.radja.autoskola.R;

/**
 * Created by Computer on 07/08/2014.
 */

public class ResultActivity extends BaseActivity implements View.OnClickListener{
    Button back;
    ImageView slikaPrikaz;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        rootView = findViewById(R.id.root_view);
        setBackground();
//get button back
        back = (Button) findViewById(R.id.bBack);
        back.setOnClickListener(this);

//get text view
        TextView t = (TextView) findViewById(R.id.textResult);
        slikaPrikaz = (ImageView) findViewById(R.id.ivPrikazRezultat);
//get score
        Bundle b = getIntent().getExtras();
        score = b.getInt(Constants.KEY_SCORE);
        if (score >= 26) {
            slikaPrikaz.setImageResource(R.drawable.check_img);
            t.setText("Uspješno ste položili test" + " sakupili ste ukupno: " + score + "/29" + " bodova!" + " Čestitamo!");
            t.setTextColor(Color.WHITE);
            t.setTextSize(25);

        } else {
            slikaPrikaz.setImageResource(R.drawable.wrong_img);
           t.setText("Nažalost niste prošli test" + " sakupili ste ukupno: " + score + "/29" + " bodova!");
            t.setTextColor(Color.WHITE);
            t.setTextSize(25);

        }
    }
//display score




    @Override
    public void onClick(View v) {
        finish();
    }
}