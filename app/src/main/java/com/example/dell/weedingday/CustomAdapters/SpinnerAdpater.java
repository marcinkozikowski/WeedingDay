package com.example.dell.weedingday.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.weedingday.Entity.Cost;
import com.example.dell.weedingday.R;

import java.util.List;

/**
 * Created by Dell on 2017-01-10.
 */

public class SpinnerAdpater extends BaseAdapter {
    List<String> typeList;
    private final LayoutInflater inflater;
    private Context context;

    public SpinnerAdpater(List<String> typeList, Context context) {
        this.typeList = typeList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return typeList.size();
    }

    @Override
    public Object getItem(int position) {
        return typeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.spinner_item, parent, false);

        ImageView iconSpinner = (ImageView) rowView.findViewById(R.id.iconSpinner);
        TextView nameSpinner = (TextView) rowView.findViewById(R.id.nameSpinner);


        if(typeList.get(position).equals("Multimedia")) {
            iconSpinner.setImageResource(R.drawable.multimedia);
            nameSpinner.setText(typeList.get(position));
        }
        else if(typeList.get(position).equals("Ubrania")){
            iconSpinner.setImageResource(R.drawable.clothes);
            nameSpinner.setText(typeList.get(position));
        }
        else if(typeList.get(position).equals("Catering")){
            iconSpinner.setImageResource(R.drawable.food);
            nameSpinner.setText(typeList.get(position));
        }
        else if(typeList.get(position).equals("Atrakcje dodatkowe")){
            iconSpinner.setImageResource(R.drawable.icon_attractions);
            nameSpinner.setText(typeList.get(position));
        }
        else if(typeList.get(position).equals("Samochód")){
            iconSpinner.setImageResource(R.drawable.icon_car);
            nameSpinner.setText(typeList.get(position));
        }
        else if(typeList.get(position).equals("Różne")){
            iconSpinner.setImageResource(R.drawable.etc);
            nameSpinner.setText(typeList.get(position));
        }


        return rowView;
    }
}
