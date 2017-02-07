package com.example.dell.weedingday.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dell.weedingday.CustomAdapters.SpinnerAdpater;
import com.example.dell.weedingday.Entity.Service;
import com.example.dell.weedingday.R;

import java.util.ArrayList;

public class AddNewServiceActivity extends AppCompatActivity {

    Spinner mySpinner;
    EditText title;
    EditText about;
    EditText cost;
    ImageView serviceImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_add_new_service);

        mySpinner = (Spinner)findViewById(R.id.SpinnerService);
        title = (EditText) findViewById(R.id.TitleServiceAdd);
        about= (EditText) findViewById(R.id.AboutServiceAdd);
        cost = (EditText) findViewById(R.id.CostServiceAdd);

        ArrayList<String> myList = new ArrayList<String>();

        myList.add(0,"Multimedia");
        myList.add(1,"Ubrania");
        myList.add(2,"Catering");
        myList.add(3,"Atrakcje dodatkowe");
        myList.add(4,"Samochód");
        myList.add(5,"Różne");


        SpinnerAdpater mySpinnerAdapter = new SpinnerAdpater(myList,this);
        mySpinner.setAdapter(mySpinnerAdapter);
    }


    public void AddNewService(View view) {

        String type = mySpinner.getSelectedItem().toString();

        Log.w("Kategoria",type);

        String categoryName;
        String _title;
        String _about;
        int imgInt=0;
        double _cost;


        _title = title.getText().toString();
        _about = about.getText().toString();
        _cost = Double.parseDouble(cost.getText().toString().replace(",","."));

        Service s = new Service(MainActivity.UserId,_title,_about,_cost,0,type);
        s.save();

        finish();
    }
}
