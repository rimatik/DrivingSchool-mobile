package com.slaven.radja.autoskola.activities.ucenje.raskrizje;

import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;

import com.slaven.radja.autoskola.adapters.PrometniPolicajacAdapter;

import com.slaven.radja.autoskola.helpers.DbHelper;
import com.slaven.radja.autoskola.models.Semafor;

import java.util.List;

/**
 * Created by Computer on 10/08/2014.
 */
public class PrometniPolicajacNaRaskrizju extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_prednost_na_raskrizju);
        rootView = findViewById(R.id.root_view);
        setBackground();
        GridView prohibitorySigns = (GridView) rootView;
        DbHelper dbHelper = DbHelper.getInstance(this);
        List<Semafor> semafori = dbHelper.getAllCops();
        PrometniPolicajacAdapter adapter = new PrometniPolicajacAdapter(this, semafori);
        prohibitorySigns.setAdapter(adapter);
    }
}
