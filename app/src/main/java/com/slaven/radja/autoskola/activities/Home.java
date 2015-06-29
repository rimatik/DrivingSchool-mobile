package com.slaven.radja.autoskola.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.ucenje.znakovi.ZnakoviObavijesti;


public class Home extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rootView = findViewById(R.id.root_view);
        ImageView imgUcenje = (ImageView) findViewById(R.id.ivUcenje);
        ImageView imgIspiti = (ImageView) findViewById(R.id.ivIspiti);
        ImageView imgPostavke = (ImageView) findViewById(R.id.ivSettings);
        ImageView imgInfo = (ImageView) findViewById(R.id.ivInfo);

        imgUcenje.setOnClickListener(this);
        imgIspiti.setOnClickListener(this);
        imgPostavke.setOnClickListener(this);
        imgInfo.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setBackground();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ivUcenje:
                Intent ucenje = new Intent(v.getContext(),UcenjeLista.class);
                startActivityForResult(ucenje,0);
            break;
            case R.id.ivIspiti:
                Intent ispiti = new Intent(v.getContext(),QuizActivity.class);
                startActivityForResult(ispiti,0);
                break;
            case R.id.ivSettings:
                Intent postavke = new Intent(v.getContext(),Postavke.class);
                startActivityForResult(postavke,0);
                break;
            case R.id.ivInfo:
                Intent info = new Intent(v.getContext(),Info.class);
                startActivityForResult(info,0);
                break;

        }

    }
}
