package com.example.dell.weedingday.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dell.weedingday.CustomAdapters.GuestListAdapter;
import com.example.dell.weedingday.Entity.Guest;
import com.example.dell.weedingday.R;
import com.example.dell.weedingday.dummy.DummyContent;

import java.util.List;

/**
 * An activity representing a list of Guests. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link GuestDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class GuestListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    public static int ItemSelected;
    GuestListAdapter myGuestAdapter;
    ListView guestsListView;

    @Override
    protected void onResume()
    {
        super.onResume();
        //RefreshGuestList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        View recyclerView = findViewById(R.id.guest_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.guest_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
    }

    public void AddNewGuest(View view) {

        TextView guestName = (TextView) findViewById(R.id.GuestNameAdd);
        TextView guestSurame = (TextView) findViewById(R.id.GuestSurnameAdd);
        TextView guestPhone = (TextView) findViewById(R.id.GuestPhoneAdd);
        CheckBox sleeping = (CheckBox) findViewById(R.id.IsSleeping);
        CheckBox isWithFriend = (CheckBox) findViewById(R.id.IsWithFriend);
        RadioButton groomGuest = (RadioButton) findViewById(R.id.GroomGuestRadioButton);
        RadioButton brideGuest = (RadioButton) findViewById(R.id.BrideGuestRadioButton);
        TextView firendName = (TextView) findViewById(R.id.GuestFriendNameAdd);
        TextView firendSurname = (TextView) findViewById(R.id.GuestFriendSurnameAdd);

        try {

            if (isWithFriend.isChecked() == false) {
                if (brideGuest.isChecked()) {
                    Guest g = new Guest(MainActivity.UserId, guestName.getText().toString(),
                            guestSurame.getText().toString(),
                            Integer.parseInt(guestPhone.getText().toString()), true, isWithFriend.isChecked(), sleeping.isChecked());
                    g.save();
                    Toast.makeText(this, R.string.AddGuestSucces, Toast.LENGTH_LONG).show();
                } else if (groomGuest.isChecked()) {
                    Guest g = new Guest(MainActivity.UserId, guestName.getText().toString(),
                            guestSurame.getText().toString(),
                            Integer.parseInt(guestPhone.getText().toString()), false, isWithFriend.isChecked(), sleeping.isChecked());
                    g.save();
                    Toast.makeText(this, R.string.AddGuestSucces, Toast.LENGTH_LONG).show();
                }
            } else if (isWithFriend.isChecked() == true) {
                if (brideGuest.isChecked()) {
                    Guest g = new Guest(MainActivity.UserId, guestName.getText().toString(),
                            guestSurame.getText().toString(),
                            Integer.parseInt(guestPhone.getText().toString()), true, isWithFriend.isChecked(), sleeping.isChecked(), firendName.getText().toString(), firendSurname.getText().toString());
                    g.save();
                    Toast.makeText(this, R.string.AddGuestSucces, Toast.LENGTH_LONG).show();
                } else if (groomGuest.isChecked()) {
                    Guest g = new Guest(MainActivity.UserId, guestName.getText().toString(),
                            guestSurame.getText().toString(),
                            Integer.parseInt(guestPhone.getText().toString()), false, isWithFriend.isChecked(), sleeping.isChecked(), firendName.getText().toString(), firendSurname.getText().toString());
                    g.save();
                    Toast.makeText(this, R.string.AddGuestSucces, Toast.LENGTH_LONG).show();
                }
            }
            RefreshGuestList();
        }
        catch (Exception e)
        {
            Toast.makeText(this, R.string.AddGuestFail, Toast.LENGTH_LONG).show();
        }
    }

    public void DeleteGuest(View view) {

        guestsListView = (ListView) findViewById(R.id.GuestListView);

        final int position = guestsListView.getPositionForView((View) view.getParent());

        Guest g = Guest.listAll(Guest.class).get(position);
        Guest.delete(g);
        RefreshGuestList();
        Toast.makeText(this, R.string.DeleteGuestSucces, Toast.LENGTH_LONG).show();

    }


    public void EditGuest(View view) {
        guestsListView = (ListView) findViewById(R.id.GuestListView);
        EditGuestActivity.selectedGuestEdit=guestsListView.getPositionForView((View) view.getParent());

        Intent i = new Intent(getApplicationContext(),EditGuestActivity.class);
        startActivity(i);


    }

    public void RefreshGuestList()
    {
        List<Guest> guestsList = Guest.listAll(Guest.class);
        guestsListView = (ListView) findViewById(R.id.GuestListView);   // Ustawianie widoku listy w layoucie lista gosci
        myGuestAdapter= new GuestListAdapter(guestsList,this);      // Ustawianie wlasnego adaptera w celu wyswietlenia listy
        guestsListView.setAdapter(myGuestAdapter);
    }

    public void ShowFriendDetails(View view) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.GuestFriendLL);
        TextView tv = (TextView) findViewById(R.id.GuestFriendTextView);

        CheckBox ch = (CheckBox) findViewById(R.id.IsWithFriend);

        if(ch.isChecked())
        {
            tv.setVisibility(View.VISIBLE);
            ll.setVisibility(View.VISIBLE);
        }
        if(ch.isChecked()==false)
        {
            tv.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.INVISIBLE);
        }
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.guest_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            //holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemSelected=Integer.parseInt(holder.mItem.id);
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(GuestDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        GuestDetailFragment fragment = new GuestDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.guest_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, GuestDetailActivity.class);
                        intent.putExtra(GuestDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            //public final TextView mIdView;
            public final TextView mContentView;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                //mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
