package com.slaven.radja.autoskola.ucenje.raskrizje;

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
 * Created by Computer on 05/08/2014.
 */
public class Raskrizje extends Activity implements AdapterView.OnItemClickListener{

    ListView list;
    String[] titles;



    public String lv_class_names[]= {PrednostNaRaskrizju.class.getName(), PrometniPolicajacNaRaskrizju.class.getName(), HijerarhijskiRedosljedNaRaskrizju.class.getName()};
    int[] images ={R.drawable.crossroad,R.drawable.policajac, R.drawable.hijerarhijski};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raskrizje);

        Resources res = getResources();
        titles = res.getStringArray(R.array.titlesRaskrsca);

        list =(ListView) findViewById(R.id.listView);
        RaskrizjeAdapter adapter = new RaskrizjeAdapter(this,titles,images);
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
class RaskrizjeAdapter extends ArrayAdapter<String>
{
    Context context;
    int[] images;
    String[] titles;
    RaskrizjeAdapter(Context c,String [] titles,int images[])
    {
        super(c,R.layout.raskrizje,R.id.textView,titles);
        this.images=images;
        this.context=c;
        this.titles = titles;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.red,parent,false);

        ImageView myImage = (ImageView) row.findViewById(R.id.imageView);
        TextView myText = (TextView) row.findViewById(R.id.textView);

        myImage.setImageResource(images[position]);
        myText.setText(titles[position]);

        return row;
    }
}