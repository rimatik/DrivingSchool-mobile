package com.slaven.radja.autoskola.activities.ucenje;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;

/**
 * Created by Computer on 05/08/2014.
 */
public class ZaustavljanjeiParkiranje extends BaseActivity {
    TextView tvParkiranje;
    String zaustavljanje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zaustavljanje_i_parkiranje);
        rootView = findViewById(R.id.root_view);
        setBackground();

        Resources res = getResources();
        tvParkiranje = (TextView) findViewById(R.id.tvParkiranje);
        zaustavljanje = res.getString(R.string.zaustavljanje);
        tvParkiranje.setText(zaustavljanje);


    }

}
