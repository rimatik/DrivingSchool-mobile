package com.slaven.radja.autoskola.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.models.Znak;

import java.util.List;


public class SignsAdapter extends BaseAdapter {

    private Context context;
    private List<Znak> signs;

    public SignsAdapter(Context context, List<Znak> signs) {
        this.signs = signs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return signs.size();
    }

    @Override
    public Object getItem(int position) {
        return signs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Znak currentSign = (Znak) getItem(position);
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_sign, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.signDescription.setText(currentSign.getName());
        holder.signImage.setImageResource(currentSign.getId_img());
        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    static class ViewHolder {
        private ImageView signImage;
        private TextView signDescription;

        public ViewHolder(View view) {
            signImage = (ImageView) view.findViewById(R.id.sign_image);
            signDescription = (TextView) view.findViewById(R.id.sign_description);
        }
    }
}
