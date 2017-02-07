package com.example.dell.weedingday.Activities;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.dell.weedingday.CustomAdapters.GuestListAdapter;
import com.example.dell.weedingday.Entity.Guest;
import com.example.dell.weedingday.R;
import com.example.dell.weedingday.dummy.DummyContent;

import java.util.List;

/**
 * A fragment representing a single Guest detail screen.
 * This fragment is either contained in a {@link GuestListActivity}
 * in two-pane mode (on tablets) or a {@link GuestDetailActivity}
 * on handsets.
 */


public class GuestDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GuestDetailFragment() {
    }

    public GuestListAdapter myGuestAdapter;
    ListView guestsListView;
    List<Guest> guestsList;
//    TextView sleepingTV;
//    TextView brideGuestsTV;
//    TextView groomGuestsTV;
//    TextView allGuestsTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    // Ladowanie odpowiedniego layoutu do wskazanego activity
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.guests_summary, container, false);

//        sleepingTV = (TextView) rootView.findViewById(R.id.SleepingNumber);
//        brideGuestsTV = (TextView) rootView.findViewById(R.id.BrideGuestNumber);
//        groomGuestsTV = (TextView) rootView.findViewById(R.id.GroomGuestNumber);
//        allGuestsTV = (TextView) rootView.findViewById(R.id.AllGuestsSummary);

        // Show the dummy content as text in a TextView.
        if (mItem.id == "0") {
            rootView = inflater.inflate(R.layout.guest_list_items, container, false);
            RefreshGuestList(rootView);
        }
        else if(mItem.id == "1")
        {
            rootView = inflater.inflate(R.layout.add_guest_detail, container, false);
        }
        else if(mItem.id == "2")
        {
            rootView = inflater.inflate(R.layout.guests_summary, container, false);
            sumAllGuests(rootView);
        }
        return rootView;
    }

    public void sumAllGuests(View root)
    {
        TextView sleepingTV = (TextView) root.findViewById(R.id.SleepingNumber);
        TextView brideGuestsTV = (TextView) root.findViewById(R.id.BrideGuestNumber);
        TextView groomGuestsTV = (TextView) root.findViewById(R.id.GroomGuestNumber);
        TextView allGuestsTV = (TextView) root.findViewById(R.id.AllGuestsSummary);

        int sleeping = 0;
        int groomGuests= 0;
        int brideGuests = 0;
        int allGuests = 0;

        List<Guest> guestList = Guest.listAll(Guest.class);

        for(Guest g:guestList)
        {
            if(g.isWithFriend())
            {
                allGuests=allGuests+2;
                if(g.isSleeping())
                {
                    sleeping=sleeping+2;
                }
                if(g.isFromBride())
                {
                    brideGuests=brideGuests+2;
                }
                if(g.isFromBride()==false)
                {
                    groomGuests=groomGuests+2;
                }
            }
            if(g.isWithFriend()==false)
            {
                allGuests=allGuests+1;
                if(g.isSleeping())
                {
                    sleeping=sleeping+1;
                }
                if(g.isFromBride())
                {
                    brideGuests=brideGuests+1;
                }
                if(g.isFromBride()==false)
                {
                    groomGuests=groomGuests+1;
                }
            }
        }

        Log.w("Liczba gosci", String.valueOf(allGuests));
        Log.w("Liczba noclegow", String.valueOf(sleeping));
        Log.w("Liczba mlodego", String.valueOf(groomGuests));
        Log.w("Liczba mlodej", String.valueOf(brideGuests));
//
       sleepingTV.setText(String.valueOf(sleeping));
        groomGuestsTV.setText(String.valueOf(groomGuests));
        brideGuestsTV.setText(String.valueOf(brideGuests));
        allGuestsTV.setText(String.valueOf(allGuests));
    }


    public void RefreshGuestList(View root)
    {
        guestsList = Guest.listAll(Guest.class);
        guestsListView = (ListView) root.findViewById(R.id.GuestListView);
        myGuestAdapter= new GuestListAdapter(guestsList,getContext());
        guestsListView.setAdapter(myGuestAdapter);
    }
    }
