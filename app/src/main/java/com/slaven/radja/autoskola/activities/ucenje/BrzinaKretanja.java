package com.slaven.radja.autoskola.activities.ucenje;

import android.os.Bundle;
import android.widget.TextView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;

/**
 * Created by Computer on 05/08/2014.
 */
public class BrzinaKretanja extends BaseActivity {

    TextView tvBrzina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brzina_kretanja);
        rootView = findViewById(R.id.root_view);
        setBackground();


        tvBrzina = (TextView) findViewById(R.id.tvBrzinaKretanja);


        tvBrzina.setText("1.Vozač je dužan brzinu kretanja vozila prilagoditi osobinama i stanju ceste, vidljivosti preglednosti, atmosferskim prilikama, stanju vozila i tereta, gustoći pometa tako da vozilo može pravovremeno zaustaviti pred svakom zaprekom koju, u konkretnim uvjetima, može predvidjeti, odnosno da može pravovremeno postupiti prema prometnom pravilu ili znaku.\n \n" +
                "Brzina kretanja vozila na cesti uz normalne prometne uvjete ne smije se ograničiti ispod 40 km na sat.\n \n" +
                "Na cesti u naselju vozač se ne smije vozilom kretati brzinom većom od 50 km na sat, odnosno brzinom većom od brzine dopuštene postavljenim prometnim znakom za cijelo naselje ili njegov dio.\n \n" +
                "Iznimno, od odredbe stavka 1. ovog članka, na cesti u naselju čiji prometno - tehnički i sigurnosni elementi to omogućuju (npr. Pješački pothodnici i nadhodnici, upravljanje na raskrižjima s uređajima za davanje znakova s prometnim svjetlima i sl.) mora se prometnim znakom dopustit kretanje vozilom i brzinom većom od 50 km na sat, a najviše do 80 km na sat.\n \n" +
                "Novčanom kaznom u iznosu od 5.000,00 kn do 15.000,00 kn ili zatvorom do 60 dana i tri negativna prekršajna boda kaznit će se za prekršaj vozač koji se vozilom na cesti u naselju kreće brzinom koja je za više od 50 km na sat veća od dopuštene ili prometnim znakom ograničene brzine.\n \n" +
                "Na cesti izvan naselja vozač se ne smije vozilom kretati brzinom većom od brzine dopuštene postavljenim prometnim znakom, a najaviše:\n \n" +
                " \n \n" +
                "130 km na sat na autocestama\n \n" +
                "110 km na sat na cestama namijenjenim isključivo za promet motornih vozila i brzoj cesti\n \n" +
                "90 km na sat na ostalim cestama\n \n" +
                "Novčanom kaznom u iznosu od 3.000,00 kn do 7.000,00 kn te tri negativna prekršajna boda kaznit će se za prekršaj vozač koji se vozilom na cesti izvan naselja kreće brzinom koja je za više od 50 km na sat veća od dopuštene ili prometnim znakom ograničene brzine.\n" +
                " \n \n" +
                "Brzina kretanja na cestama ograničava se:\n \n" +
                "na 80 km na sat – za autobuse, za motorna vozila najveće dopuštene mase veće od 3,5 tone i sva motorna vozila ako vuku priključno vozilo bez kočnica\n \n" +
                "na 80 km na sat – za autobuse kojima se organizirano prevoze djeca\n \n" +
                "na 70 km na sat - za zglobne autobuse bez mjesta za stajanje\n \n" +
                "na 50 km na sat - za sva motorna vozila i motorna vozila s priključnim vozilom u kojima se u tovarnom prostoru prevoze osobe te za autobuse s mjestima za stajanje\n \n" +
                "na 40 km na sat – za vozilo koje vuče drugo neispravno vozilo i za traktore\n \n" +
                "na 20 km na sat za turistički vlak\n \n" +
                "iznimno brzina kretanja pojedinih vozila na autocestama dodatno se ograničava na\n \n" +
                "100 km na sat - za autobuse, osim za autobuse kojima se organizirano prevoze djeca\n \n" +
                "90 km na sat - za motorna vozila najveće dopuštene mase veće od 3,5 tone i za sva motorna vozila ako vuku priključno vozilo bez kočnica \n \n" +
                "\n \n");


    }



}
