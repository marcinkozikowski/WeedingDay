package com.example.dell.weedingday.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.weedingday.Entity.Service;
import com.example.dell.weedingday.Entity.Task;
import com.example.dell.weedingday.R;

import java.util.List;
import java.util.Random;

/**
 * Created by Dell on 2017-01-09.
 */

public class ServiceListAdapter extends BaseAdapter {

    private List<Service> serviceList;
    private final LayoutInflater inflater;
    private Context context;

    public ServiceListAdapter(List<Service> serviceList, Context context) {
        this.serviceList = serviceList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return serviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return serviceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return serviceList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = inflater.inflate(R.layout.service_item, parent, false);


        ImageView serviceImg = (ImageView) rowView.findViewById(R.id.ServiceImg);
        TextView aboutService = (TextView) rowView.findViewById(R.id.AboutService);
        TextView servicePrice = (TextView) rowView.findViewById(R.id.CostService);
        TextView serviceTitle = (TextView) rowView.findViewById(R.id.TitleService);

        Service t = (Service) getItem(position);

        if(t.getType().equals("Multimedia"))
        {
            serviceImg.setImageResource(R.drawable.multimedia);
        }
        else if(t.getType().equals("Ubrania"))
        {
            serviceImg.setImageResource(R.drawable.clothes);
        }
        else if(t.getType().equals("Catering"))
        {
            serviceImg.setImageResource(R.drawable.food);
        }
        else if(t.getType().equals("Atrakcje dodatkowe"))
        {
            serviceImg.setImageResource(R.drawable.icon_attractions);
        }
        else if(t.getType().equals("Samochód"))
        {
            serviceImg.setImageResource(R.drawable.icon_car);
        }
        else if(t.getType().equals("Różne"))
        {
            serviceImg.setImageResource(R.drawable.etc);
        }

        serviceTitle.setText(t.getName().toString());
        aboutService.setText(t.getAbout());
        servicePrice.setText(String.format("%.2f",t.getPrice()));

        return rowView;
    }
}
