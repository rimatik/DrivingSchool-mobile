package com.slaven.radja.autoskola.activities.ucenje.znakovi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.slaven.radja.autoskola.DatabaseConstants;
import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.adapters.SignsAdapter;
import com.slaven.radja.autoskola.aplication.AutoskolaApp;
import com.slaven.radja.autoskola.helpers.DbHelper;
import com.slaven.radja.autoskola.models.Znak;

import java.util.List;

/**
 * Created by slaven.radja on 10.10.2014..
 */
public class FragmentZnakoviPrikaz extends android.support.v4.app.Fragment{


    TextView opisZnaka;
    ImageView menu , right, left;
    ImageView image;
    byte[] bArray;
    Bitmap bitmap;
    Znak sign;
    DbHelper dbHelper;
    int i = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_znakovi_prikaz, null, false);

        opisZnaka = (TextView) v.findViewById(R.id.tv_FragmentPrikaz);
        menu = (ImageView) v.findViewById(R.id.iv_Menu);
        image = (ImageView) v.findViewById(R.id.iv_FragmentPrikaz);
        left = (ImageView) v.findViewById(R.id.iv_left);
        right = (ImageView) v.findViewById(R.id.iv_right);

        i = AutoskolaApp.getPosition();
        changeData(i);


        return v;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

    }



    public void changeData(int i){

        dbHelper = DbHelper.getInstance(AutoskolaApp.getAppContext());
        sign = dbHelper.getSign(DatabaseConstants.TABLE_ZNAK_OPASNOSTI);
        bArray = sign.getBitmap();
        bitmap = BitmapFactory.decodeByteArray(bArray, 0, bArray.length);
        image.setImageBitmap(bitmap);

        Resources resources = getResources();
        String[] descriptions = resources.getStringArray(R.array.desDangerSigns);
        opisZnaka.setText(descriptions[i]);

    }




}
