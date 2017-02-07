package com.example.dell.weedingday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.weedingday.CustomAdapters.GuestListAdapter;
import com.example.dell.weedingday.Entity.Guest;

import java.util.List;

public class ListGuestsTemp extends AppCompatActivity {

    GuestListAdapter myGuestAdapter;
    ListView guestsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_guests_temp);
//  public Guest(int userId, String name, String surname, int phoneNumber, boolean fromBride, boolean withFriend, boolean isSleeping, String friendName, String firendSurname)
        Guest n = new Guest(0,"Jan","Kowalski",509549069,true,true,true,"Oliwia","Szok");
        n.save();

 //       Guest.deleteAll(Guest.class);

    int size = Guest.listAll(Guest.class).size();
    Toast.makeText(this, "Ilosc gosci w bazie: "+ size, Toast.LENGTH_LONG).show();

//        //String userId = String.valueOf(MainActivity.UserId);
        List<Guest> guestsList = Guest.listAll(Guest.class);

        guestsListView = (ListView) findViewById(R.id.GuestListViewTemp);   // Ustawianie widoku listy w layoucie lista gosci
        myGuestAdapter= new GuestListAdapter(guestsList,this);      // Ustawianie wlasnego adaptera w celu wyswietlenia listy
        guestsListView.setAdapter(myGuestAdapter);


    }
}
