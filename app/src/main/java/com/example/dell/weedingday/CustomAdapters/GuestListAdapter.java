package com.example.dell.weedingday.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.weedingday.Entity.Guest;
import com.example.dell.weedingday.R;

import org.w3c.dom.Text;

import java.util.List;

import static android.view.View.INVISIBLE;

/**
 * Created by Dell on 2017-01-05.
 */

public class GuestListAdapter extends BaseAdapter {

    private List<Guest> guests;
    private final LayoutInflater inflater;
    private Context context;

    public GuestListAdapter(List<Guest> guests, Context context) {
        super();
        this.guests = guests;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }


    @Override
    public int getCount() {
        return guests.size();
    }

    @Override
    public Object getItem(int position) {
        return guests.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
//        return guests.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View rowView = inflater.inflate(R.layout.guest_item, parent, false);

        TextView guestName = (TextView) rowView.findViewById(R.id.GuestNameList);
        TextView guestSurname = (TextView) rowView.findViewById(R.id.GuestSurnameList);
        TextView guestFriendName = (TextView) rowView.findViewById(R.id.GuestFrindNameList);
        TextView guestFriendSurname = (TextView) rowView.findViewById(R.id.GuestFriendSurnameList);

        Guest g = (Guest) getItem(position);

            guestName.setText(g.getName());
            guestSurname.setText(g.getSurname());
            guestFriendName.setText(g.getFriendName());
            guestFriendSurname.setText(g.getFirendSurname());

        return rowView;
    }
}
