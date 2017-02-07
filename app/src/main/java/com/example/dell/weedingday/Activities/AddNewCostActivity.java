package com.example.dell.weedingday.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.weedingday.Entity.Cost;
import com.example.dell.weedingday.R;

public class AddNewCostActivity extends AppCompatActivity {

    EditText title;
    EditText about;
    EditText cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_cost);

        title=(EditText) findViewById(R.id.CostTitleAdd);
        about=(EditText) findViewById(R.id.CostAboutAdd);
        cost=(EditText) findViewById(R.id.CostCostAdd);
    }

    public void AddNewCost(View view) {

        String _title;
        String _about;
        double _cost;

        _title = this.title.getText().toString();
        _about = this.about.getText().toString();
        _cost = Double.parseDouble(cost.getText().toString().replace(",","."));

        Cost c = new Cost(MainActivity.UserId,_title,_about,_cost);

        c.save();

        Toast.makeText(this, "Poprawnie dodano nowy wydatek", Toast.LENGTH_SHORT).show();

        finish();
    }

}
