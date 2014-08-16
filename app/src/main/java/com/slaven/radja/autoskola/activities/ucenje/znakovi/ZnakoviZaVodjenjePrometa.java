package com.slaven.radja.autoskola.activities.ucenje.znakovi;

import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;
import com.slaven.radja.autoskola.adapters.SignsAdapter;
import com.slaven.radja.autoskola.helpers.DbHelperZnakoviZaVodjenjePrometa;
import com.slaven.radja.autoskola.models.Znak;

import java.util.List;


/**
 * Created by Computer on 10/08/2014.
 */
public class ZnakoviZaVodjenjePrometa extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.znakovi_izricitih_naredbi);
        rootView = findViewById(R.id.root_view);
        setBackground();
        GridView prohibitorySigns = (GridView) rootView;
        DbHelperZnakoviZaVodjenjePrometa dbHelper = new DbHelperZnakoviZaVodjenjePrometa(this);
        List<Znak> signs = dbHelper.getAllSigns();
        SignsAdapter adapter = new SignsAdapter(this, signs);
        prohibitorySigns.setAdapter(adapter);



    }

}
