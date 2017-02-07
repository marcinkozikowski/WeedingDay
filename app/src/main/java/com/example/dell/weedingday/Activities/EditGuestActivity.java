package com.example.dell.weedingday.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class EditGuestActivity extends AppCompatActivity {

    public static int selectedGuestEdit;
    GuestListAdapter myGuestAdapter;
    ListView guestsListView;
    List<Guest> guestList;

    TextView guestName;
    TextView guestSurame;
    TextView guestPhone;
    CheckBox sleeping;
    CheckBox isWithFriend;
    RadioButton groomGuest;
    RadioButton brideGuest;
    TextView firendName;
    TextView firendSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_guest);

        initControls();

        guestList = Guest.listAll(Guest.class);
        Guest selectedGuest = guestList.get(selectedGuestEdit);

        TextView guestName = (TextView) findViewById(R.id.GuestNameAdd);
        TextView guestSurame = (TextView) findViewById(R.id.GuestSurnameAdd);
        TextView guestPhone = (TextView) findViewById(R.id.GuestPhoneAdd);
        CheckBox sleeping = (CheckBox) findViewById(R.id.IsSleeping);
        CheckBox isWithFriend = (CheckBox) findViewById(R.id.IsWithFriend);
        RadioButton groomGuest = (RadioButton) findViewById(R.id.GroomGuestRadioButton);
        RadioButton brideGuest = (RadioButton) findViewById(R.id.BrideGuestRadioButton);
        TextView firendName = (TextView) findViewById(R.id.GuestFriendNameAdd);
        TextView firendSurname = (TextView) findViewById(R.id.GuestFriendSurnameAdd);


        guestName.setText(selectedGuest.getName());
        guestSurame.setText(selectedGuest.getSurname());
        guestPhone.setText(String.valueOf(selectedGuest.getPhoneNumber()));
        sleeping.setChecked(selectedGuest.isSleeping());
        isWithFriend.setChecked(selectedGuest.isWithFriend());
        if(selectedGuest.isFromBride())
        {
            brideGuest.setChecked(true);
        }
        else
        {
            groomGuest.setChecked(true);
        }
        firendName.setText(selectedGuest.getFriendName());
        firendSurname.setText(selectedGuest.getFirendSurname());

        ShowFriendDetails();
    }

    public void initControls()
    {
        guestName = (TextView) findViewById(R.id.GuestNameAdd);
        guestSurame = (TextView) findViewById(R.id.GuestSurnameAdd);
        guestPhone = (TextView) findViewById(R.id.GuestPhoneAdd);
        sleeping = (CheckBox) findViewById(R.id.IsSleeping);
        isWithFriend = (CheckBox) findViewById(R.id.IsWithFriend);
        groomGuest = (RadioButton) findViewById(R.id.GroomGuestRadioButton);
        brideGuest = (RadioButton) findViewById(R.id.BrideGuestRadioButton);
        firendName = (TextView) findViewById(R.id.GuestFriendNameAdd);
        firendSurname = (TextView) findViewById(R.id.GuestFriendSurnameAdd);
    }

    public void ShowFriendDetails() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.GuestFriendLL);
        TextView tv = (TextView) findViewById(R.id.GuestFriendTextView);

        CheckBox ch = (CheckBox) findViewById(R.id.IsWithFriend);

        if(guestList.get(selectedGuestEdit).isWithFriend())
        {
            tv.setVisibility(View.VISIBLE);
            ll.setVisibility(View.VISIBLE);
        }
        if(guestList.get(selectedGuestEdit).isWithFriend()==false)
        {
            tv.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.INVISIBLE);
        }
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

        Guest selectedGuest = guestList.get(selectedGuestEdit);

        try {

            if (isWithFriend.isChecked() == false) {
                if (brideGuest.isChecked()) {
                    selectedGuest.setName(guestName.getText().toString());
                    selectedGuest.setSurname(guestSurame.getText().toString());
                    selectedGuest.setPhoneNumber(Integer.parseInt(guestPhone.getText().toString()));
                    selectedGuest.setFromBride(true);
                    selectedGuest.setWithFriend(isWithFriend.isChecked());
                    selectedGuest.setSleeping(sleeping.isChecked());

                    selectedGuest.save();
                    Toast.makeText(this, R.string.AddGuestSucces, Toast.LENGTH_LONG).show();
                } else if (groomGuest.isChecked()) {
                    selectedGuest.setName(guestName.getText().toString());
                    selectedGuest.setSurname(guestSurame.getText().toString());
                    selectedGuest.setPhoneNumber(Integer.parseInt(guestPhone.getText().toString()));
                    selectedGuest.setFromBride(false);
                    selectedGuest.setWithFriend(isWithFriend.isChecked());
                    selectedGuest.setSleeping(sleeping.isChecked());
                    selectedGuest.save();
                    Toast.makeText(this, R.string.AddGuestSucces, Toast.LENGTH_LONG).show();
                }
            } else if (isWithFriend.isChecked() == true) {
                if (brideGuest.isChecked()) {
                    selectedGuest.setName(guestName.getText().toString());
                    selectedGuest.setSurname(guestSurame.getText().toString());
                    selectedGuest.setPhoneNumber(Integer.parseInt(guestPhone.getText().toString()));
                    selectedGuest.setFromBride(true);
                    selectedGuest.setWithFriend(isWithFriend.isChecked());
                    selectedGuest.setSleeping(sleeping.isChecked());
                    selectedGuest.setFirendSurname(firendSurname.getText().toString());
                    selectedGuest.setFriendName(firendName.getText().toString());
                    selectedGuest.save();
                    Toast.makeText(this, R.string.AddGuestSucces, Toast.LENGTH_LONG).show();
                } else if (groomGuest.isChecked()) {
                    selectedGuest.setName(guestName.getText().toString());
                    selectedGuest.setSurname(guestSurame.getText().toString());
                    selectedGuest.setPhoneNumber(Integer.parseInt(guestPhone.getText().toString()));
                    selectedGuest.setFromBride(false);
                    selectedGuest.setWithFriend(isWithFriend.isChecked());
                    selectedGuest.setSleeping(sleeping.isChecked());
                    selectedGuest.setFirendSurname(firendSurname.getText().toString());
                    selectedGuest.setFriendName(firendName.getText().toString());
                    selectedGuest.save();
                    Toast.makeText(this, R.string.AddGuestSucces, Toast.LENGTH_LONG).show();
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, R.string.AddGuestFail, Toast.LENGTH_LONG).show();
        }
        finish();
    }

}
