package com.slaven.radja.autoskola.ucenje;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.adapters.SignsAdapter;
import com.slaven.radja.autoskola.adapters.TrafficLightsAdapter;
import com.slaven.radja.autoskola.ucenje.znakovi.Semafor;
import com.slaven.radja.autoskola.ucenje.znakovi.Znak;
import com.slaven.radja.autoskola.ucenje.znakovi.helperi.DbHelperPrometniSemafor;
import com.slaven.radja.autoskola.ucenje.znakovi.helperi.DbHelperZnakoviOpasnosti;

import java.util.List;

/**
 * Created by Computer on 05/08/2014.
 */
public class PrometniSemafor extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.prometni_semafor);
        GridView prohibitorySigns = (GridView) findViewById(R.id.gv_prohibitory_traffic);
        DbHelperPrometniSemafor dbHelper = new DbHelperPrometniSemafor(this);
        List<Semafor> semafori = dbHelper.getAllTrafficLights();
        TrafficLightsAdapter adapter = new TrafficLightsAdapter(this, semafori);
        prohibitorySigns.setAdapter(adapter);

    }
}