package com.slaven.radja.autoskola.ispiti;

import android.app.Activity;

/**
 * Created by Computer on 07/08/2014.
 */
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.slaven.radja.autoskola.Home;
import com.slaven.radja.autoskola.R;

public class ResultActivity extends Activity implements View.OnClickListener{
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
//get button back
        back = (Button) findViewById(R.id.bBack);
        back.setOnClickListener(this);

//get text view
        TextView t = (TextView) findViewById(R.id.textResult);
//get score
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        if(score < 2){
        t.setText("Nažalost niste prošli test" + " sakupili ste ukupno: " + score + "/21" + " bodova!");
        t.setTextColor(Color.RED);
        }else {
            t.setText("Uspješno ste položili test" + " sakupili ste ukupno: " + score + "/21" + " bodova!" + " Čestitamo!");
            t.setTextColor(Color.GREEN);
        }
        }
//display score




    @Override
    public void onClick(View v) {
        v.getId();
        Intent ucenje = new Intent(ResultActivity.this,Home.class);
        startActivity(ucenje);
        finish();
    }
}