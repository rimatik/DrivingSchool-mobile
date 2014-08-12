package com.slaven.radja.autoskola.ucenje.znakovi;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;


import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.adapters.SignsAdapter;
import com.slaven.radja.autoskola.ucenje.znakovi.helperi.DbHelperZnakoviOpasnosti;

import java.util.List;

/**
 * Created by Computer on 10/08/2014.
 */
public class ZnakoviIzricitihNaredbi extends Activity {

    private GridView prohibitorySigns;
    private DbHelperZnakoviOpasnosti dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.znakovi_izricitih_naredbi);
        prohibitorySigns = (GridView) findViewById(R.id.gv_prohibitory_signs);
        dbHelper = new DbHelperZnakoviOpasnosti(this);
        List<Znak> signs = dbHelper.getAllSigns();
        SignsAdapter adapter = new SignsAdapter(this, signs);
        prohibitorySigns.setAdapter(adapter);
    }

    private Integer[] mThumbIds = {
            R.drawable.raskrizje_sa_sporednom_cestom_pod_pravim_kutom_thumb, R.drawable.obilijezen_pjesacki_prijelaz_thumb,
            R.drawable.raskrizje_iste_vaznosti_thumb, R.drawable.spajanje_sporedne_ceste_pod_pravim_kutom_s_lijeve_strane_thumb,
         };

    private Integer[] mImageIds = {
            R.drawable.raskrizje_sa_sporednom_cestom_pod_pravim_kutom_img, R.drawable.obilijezen_pjesacki_prijelaz_img, R.drawable.raskrizje_iste_vaznosti_img,
            R.drawable.spajanje_sporedne_ceste_pod_pravim_kutom_s_lijeve_strane_img};

}
