package com.example.dell.weedingday.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.weedingday.Entity.Cost;
import com.example.dell.weedingday.Entity.Task;
import com.example.dell.weedingday.R;

import java.util.List;
import java.util.Random;

/**
 * Created by Dell on 2017-01-09.
 */

public class CostListAdapter extends BaseAdapter {

    private List<Cost> costList;
    private final LayoutInflater inflater;
    private Context context;

    public CostListAdapter(List<Cost> costList, Context context) {
        this.costList = costList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return costList.size();
    }

    @Override
    public Object getItem(int position) {
        return costList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return costList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.cost_item, parent, false);

        ImageView taskImg = (ImageView) rowView.findViewById(R.id.CostImg);
        TextView title= (TextView) rowView.findViewById(R.id.TitleCoast);
        TextView aboutTask = (TextView) rowView.findViewById(R.id.AboutCoast);
        TextView dateTask = (TextView) rowView.findViewById(R.id.CostPriceTextView);

        Cost t = (Cost) getItem(position);

        title.setText(t.getTitle());
        taskImg.setImageResource(R.drawable.ic_dollar_symbol);
        aboutTask.setText(t.getAbout());
        dateTask.setText(String.format("%.2f",t.getPrice()));

        return rowView;
    }
}
