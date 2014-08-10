package com.slaven.radja.autoskola.ucenje.znakovi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.slaven.radja.autoskola.R;

/**
 * Created by Computer on 09/08/2014.
 */
public class PrometniZnakovi extends Activity implements AdapterView.OnItemClickListener{

    ListView list;
    String[] titles;



    public String lv_class_names[]= {ZnakoviOpasnosti.class.getName(), ZnakoviIzricitihNaredbi.class.getName(), ZnakoviObavijesti.class.getName(), ZnakoviZaVodjenjePrometa.class.getName(),DopunskePloceUzZnakove.class.getName()};
    int[] images ={R.drawable.znak_opasnosti_img,R.drawable.zabrana_prometa_u_jednom_smjeru_img, R.drawable.obilijezen_pjesacki_prijelaz_img, R.drawable.putokazna_ploca_img,R.drawable.dopunske_ploce_uz_znakove_img};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prometni_znakovi);

        Resources res = getResources();
        titles = res.getStringArray(R.array.titlesZnakovi);

        list =(ListView) findViewById(R.id.listView);
        PrometniZnakoviAdapter adapter = new PrometniZnakoviAdapter(this,titles,images);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

    }




    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


        Intent i = null;
        try {
            i = new Intent(this, Class.forName(lv_class_names[position]));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        startActivity(i);



    }


}
class PrometniZnakoviAdapter extends ArrayAdapter<String>
{
    Context context;
    int[] images;
    String[] titles;
    PrometniZnakoviAdapter(Context c,String [] titles,int images[])
    {
        super(c,R.layout.prometni_znakovi,R.id.textView,titles);
        this.images=images;
        this.context=c;
        this.titles = titles;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.redznakovi,parent,false);

        ImageView myImage = (ImageView) row.findViewById(R.id.imageView);
        TextView myText = (TextView) row.findViewById(R.id.textView);

        myImage.setImageResource(images[position]);
        myText.setText(titles[position]);

        return row;
    }
}