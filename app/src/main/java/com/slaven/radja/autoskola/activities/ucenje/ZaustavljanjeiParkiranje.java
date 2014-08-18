package com.slaven.radja.autoskola.activities.ucenje;

import android.os.Bundle;
import android.widget.TextView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;

/**
 * Created by Computer on 05/08/2014.
 */
public class ZaustavljanjeiParkiranje extends BaseActivity {
    TextView tvParkiranje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zaustavljanje_i_parkiranje);
        rootView = findViewById(R.id.root_view);
        setBackground();

        tvParkiranje = (TextView) findViewById(R.id.tvParkiranje);


        tvParkiranje.setText("Zaustavljanje je prekid vožnje do tri minute a parkiranje duže od tri minute (osim prekida koji se čini da bi se postupilo po znaku ili pravilu kojim se upravlja prometom). Vozač ne smije zaustaviti ili parkirati vozilo na mjestu na kojem bi ono ugrožavalo sigurnost drugih sudionika u prometu ili predstavljalo smetnju za normalan tok prometa ili kretanje pješaka.\n\n" +
                "Na cesti, na mjestu, odnosno na prostoru namijenjenom za zaustavljanje i parkiranje vozila, te na posebno uređenoj prometnoj površini namijenjenoj za kretanje pješaka, ne smiju se ostavljati vozila koja se ne upotrebljavaju u prometu zbog dotrajalosti (neregistrirana i sl.) ili priključna vozila koja se u prometu koriste samo povremeno (lake i kamp prikolice i sl.), a ni drugi predmeti kojima se ometa tok prometa ili ugrožava okoliš.\n\n" +
                "Vozač ne smije zaustaviti ili parkirati vozilo:\n\n" +
                "1) Na obilježenom pješačkom prijelazu i na udaljenosti manjoj od 5 m od tog prijelaza i na prijelazu biciklističke staze preko kolnika.\n\n" +
                "2) Na prijelazu ceste preko željezničke ili tramvajske pruge u istoj razini.\n\n" +
                "3) Na željezničkim ili tramvajski prugama i u blizini tih pruga ako se time sprečavaju promet vozila koja se kreću po tračnicama.\n\n" +
                "4) Na raskrižju i na udaljenosti manjoj od 5 m od najbližeg ruba poprečnog kolnika.\n\n" +
                "5) U tunelima, na mostovima u podvožnjacima i na nadvožnjacima te na udaljenosti manjoj od 15 m od mosta, tunela, podvožnjaka i nadvožnjaka.\n\n" +
                "6) Na dijelu ceste u blizini vrha prijevoja i u zavoju gdje je preglednost ceste nedovoljna i gdje se vozila ne bi mogla obići bez opasnosti.\n\n" +
                "7) Na dijelu ceste gdje bi širina slobodnog prolaza od zaustavljenog ili parkiranog vozila do neisprekidane uzdužne crte na kolnika ili do neke zapreke na cesti, bila manja od 3 m, odnosno od suprotnog ruba kolnika – manja od 4 metra.\n\n" +
                "8) Na mjestu na kojem bi vozilo zaklanjalo postavljeni prometni znak ili uređaj za davanje znakova prometnim svjetlima.\n\n" +
                "9) Na biciklističkoj stazi, odnosno traci.\n\n" +
                "10) Na dijelu kolnika koji je kao stajalište za vozila javnog prijevoza putnika obilježen oznakama na kolniku ili prometnim znakom.\n\n" +
                "11) Na nogostupu.\n\n" +
                "12) Ispred kolnog ulaza u zgradu, sklonište, dvorište ili garažu, iznad priključka na vodovodnu mrežu i ulaza u kanalizaciju ili drugu komunalnu mrežu te na mjestima rezerviranim za vozila opskrbe ili na mjestima namijenjenim i obilježenim kao mjesta za odlaganje kućnog otpada, ispred i na vatrogasnim i drugim komunalnim i javnim prolazima i prilazima.\n\n" +
                "13) Na mjestu rezerviranom za parkiranje vozila osoba s invaliditetom.\n\n" +
                "Zaustavljanje je dopušteno:\n\n" +
                "1) Ako je za kretanje pješaka ostavljeno najmanje 1,6 m širine na površini za kretanje pješaka, s tim da ta površina ne može biti uz rub kolnika. Pod istim uvjetima može se parkirati ako je to dopušteno prometnim znakom. \n\n" +
                "Vozač ne smije parkirati vozilo:\n\n" +
                "1) Na dijelu ceste ispred prijelaza ceste preko željezničke i tramvajske pruge u istoj razini, i to na udaljenosti manjoj od 15 m od tih prijelaza.\n\n" +
                "2) Na udaljenosti manjoj od 15 m ispred i iza znaka kojim je obilježeno stajalište za vozila javnog prijevoza putnika.\n\n" +
                "3) Na mjestu na kojem bi parkirano vozilo onemogućilo pristup drugom vozilu radi parkiranja ili izlazak nekom već parkiranom vozilu.\n\n" +
                "4) Vozač koji zaustavi ili parkira vozilo tamo gdje je to prometnim pravilom ili znakom zabranjeno bit će kažnjen s 300kn, a ako to učini na mjestu koje je rezervirano za osobe s invaliditetom kazna je 700kn. \n\n" +
                "\n");

    }

}
