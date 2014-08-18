package com.slaven.radja.autoskola.activities.ucenje.raskrizje;

import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;
import com.slaven.radja.autoskola.adapters.PrednostNaRaskrizjuAdapter;
import com.slaven.radja.autoskola.adapters.TrafficLightsAdapter;
import com.slaven.radja.autoskola.helpers.DbHelperPrednostNaRaskrizju;
import com.slaven.radja.autoskola.helpers.DbHelperPrometniSemafor;
import com.slaven.radja.autoskola.models.Semafor;

import java.util.List;

/**
 * Created by Computer on 10/08/2014.
 */
public class PrednostNaRaskrizju extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_prednost_na_raskrizju);
        rootView = findViewById(R.id.root_view);
        setBackground();
        GridView prohibitorySigns = (GridView) rootView;
        DbHelperPrednostNaRaskrizju dbHelper = new DbHelperPrednostNaRaskrizju(this);
        List<Semafor> semafori = dbHelper.getAllRaskrizja();
        PrednostNaRaskrizjuAdapter adapter = new PrednostNaRaskrizjuAdapter(this, semafori);
        prohibitorySigns.setAdapter(adapter);
    }
}
