package com.slaven.radja.autoskola;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.slaven.radja.autoskola.ispiti.QuizActivity;
import com.slaven.radja.autoskola.ucenje.UcenjeLista;


public class Home extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
