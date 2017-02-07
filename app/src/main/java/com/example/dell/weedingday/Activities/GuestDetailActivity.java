package com.example.dell.weedingday.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.weedingday.CustomAdapters.GuestListAdapter;
import com.example.dell.weedingday.Entity.Guest;
import com.example.dell.weedingday.R;

import java.util.List;

import static com.example.dell.weedingday.Activities.GuestListActivity.ItemSelected;

/**
 * An activity representing a single Guest detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link GuestListActivity}.
 */
public class GuestDetailActivity extends AppCompatActivity {

    GuestListAdapter myGuestAdapter;
    ListView guestsListView;
    List<Guest> guestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        guestList = Guest.listAll(Guest.class);
        if(ItemSelected==0)
        {
            setContentView(R.layout.activity_guest_detail);
            try {
                RefreshGuestList();
            }
            catch(Exception e)
            {
                e.toString();
            }
//
        }
        else if(ItemSelected==1)
        {
            setContentView(R.layout.activity_guest_detail);
        }
        else if(ItemSelected==2)
        {
            setContentView(R.layout.activity_guest_detail);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(GuestDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(GuestDetailFragment.ARG_ITEM_ID));
            GuestDetailFragment fragment = new GuestDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.guest_detail_container, fragment)
                    .commit();
        }
    }

    public void EditGuest(View view) {
        guestsListView = (ListView) findViewById(R.id.GuestListView);
        EditGuestActivity.selectedGuestEdit=guestsListView.getPositionForView((View) view.getParent());

        Intent i = new Intent(getApplicationContext(),EditGuestActivity.class);
        startActivity(i);


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

        Log.w("Osoba: ",guestName.getText().toString());

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
            //RefreshGuestList(view);
        }
        catch (Exception e)
        {
            Toast.makeText(this, R.string.AddGuestFail, Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void RefreshGuestList()
    {
        List<Guest> guestsList = Guest.listAll(Guest.class);
        guestsListView = (ListView) findViewById(R.id.GuestListView);
        myGuestAdapter= new GuestListAdapter(guestsList,this);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, GuestListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
