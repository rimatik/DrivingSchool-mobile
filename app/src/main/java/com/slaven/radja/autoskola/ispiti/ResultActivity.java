package com.slaven.radja.autoskola.ispiti;

import android.app.Activity;

/**
 * Created by Computer on 07/08/2014.
 */
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.RatingBar;
import android.widget.TextView;

import com.slaven.radja.autoskola.R;

public class ResultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
//get rating bar object

//get text view
        TextView t = (TextView) findViewById(R.id.textResult);
//get score
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
//display score


    }
}