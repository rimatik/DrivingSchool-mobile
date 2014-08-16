package com.slaven.radja.autoskola.activities.ucenje;

import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;
import com.slaven.radja.autoskola.adapters.TrafficLightsAdapter;
import com.slaven.radja.autoskola.helpers.DbHelperPrometniSemafor;
import com.slaven.radja.autoskola.models.Semafor;

import java.util.List;


/**
 * Created by Computer on 05/08/2014.
 */
public class PrometniSemafor extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.prometni_semafor);
        rootView = findViewById(R.id.root_view);
        setBackground();
        GridView prohibitorySigns = (GridView) rootView;
        DbHelperPrometniSemafor dbHelper = new DbHelperPrometniSemafor(this);
        List<Semafor> semafori = dbHelper.getAllTrafficLights();
        TrafficLightsAdapter adapter = new TrafficLightsAdapter(this, semafori);
        prohibitorySigns.setAdapter(adapter);
    }
}