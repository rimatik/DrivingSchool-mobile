package com.slaven.radja.autoskola.activities.ucenje;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;

/**
 * Created by Computer on 05/08/2014.
 */
public class KruzniTokRotor extends BaseActivity {

    ImageView ivKruzniTok;
    TextView tvKruzniTok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kruzni_tok_rotor);
        rootView = findViewById(R.id.root_view);
        setBackground();

        ivKruzniTok = (ImageView) findViewById(R.id.ivKruzniTok);
        tvKruzniTok = (TextView) findViewById(R.id.tvKruzniTok);


        tvKruzniTok.setText("\n Službeni naziv za kružni tok - rotor je prometno raskrižje sa kružnim tokom prometa.\n\n"
        + "- U kružnom toku imaju prednost vozila koja zadržavaju smjer ravno i svoju prometnu traku \n\n"
        + "- Prilikom ulaska u kružni tok ne dajemo žmigavac. To je prometno ravno\n\n"
        + "- Prije izlaska iz kružnog toka dajemo desni žmigavac. Na taj način obavještavamo vozila da izlazimo iz kružnog toka i olakšavamo njihov ulazak\n");

    }
}
