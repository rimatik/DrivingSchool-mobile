package com.slaven.radja.autoskola.activities.ucenje.znakovi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;

import com.slaven.radja.autoskola.R;

import com.slaven.radja.autoskola.adapters.SignsAdapter;
import com.slaven.radja.autoskola.models.Znak;
import com.slaven.radja.autoskola.ucenje.znakovi.helperi.DbHelperZnakoviObavijesti;
import com.slaven.radja.autoskola.ucenje.znakovi.helperi.DbHelperZnakoviZaVodjenjePrometa;

import java.util.List;

import com.slaven.radja.autoskola.activities.BaseActivity;


/**
 * Created by Computer on 10/08/2014.
 */
public class ZnakoviZaVodjenjePrometa extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.znakovi_izricitih_naredbi);
        GridView prohibitorySigns = (GridView) findViewById(R.id.gv_prohibitory_signs);
        DbHelperZnakoviZaVodjenjePrometa dbHelper = new DbHelperZnakoviZaVodjenjePrometa(this);
        List<Znak> signs = dbHelper.getAllSigns();
        SignsAdapter adapter = new SignsAdapter(this, signs);
        prohibitorySigns.setAdapter(adapter);


        rootView = findViewById(R.id.root_view);
        setBackground();

    }

}
