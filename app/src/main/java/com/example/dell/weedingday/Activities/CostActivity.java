package com.example.dell.weedingday.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.weedingday.CustomAdapters.CostListAdapter;
import com.example.dell.weedingday.CustomAdapters.TaskListAdapter;
import com.example.dell.weedingday.Entity.Cost;
import com.example.dell.weedingday.Entity.Task;
import com.example.dell.weedingday.R;

import java.util.Date;
import java.util.List;

public class CostActivity extends Activity {

    List<Cost> allCostList;
    TextView totalCostTV;
    //private static CostListAdapter myCostAdpater;
    private ListView CostListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);

        CostListView = (ListView) findViewById(R.id.CostListView);
        totalCostTV = (TextView) findViewById(R.id.TotalCostTextView);
        allCostList = Cost.listAll(Cost.class);

        TabHost mTabHost = (TabHost)findViewById(R.id.tabHost);
        mTabHost.setup();
        //Lets add the first Tab
        TabHost.TabSpec mSpec = mTabHost.newTabSpec(getString(R.string.CostsList));
        mSpec.setContent(R.id.first_Tab);
        mSpec.setIndicator(getString(R.string.CostsList));
        mTabHost.addTab(mSpec);
        //Lets add the second Tab
        mSpec = mTabHost.newTabSpec(getString(R.string.CostsSummary));
        mSpec.setContent(R.id.second_Tab);
        mSpec.setIndicator(getString(R.string.CostsSummary));
        mTabHost.addTab(mSpec);

        demo();
        setCostTextView();
    }

    @Override
    protected  void onResume()
    {
        super.onResume();
        CostListView = (ListView) findViewById(R.id.CostListView);
        totalCostTV = (TextView) findViewById(R.id.TotalCostTextView);
        allCostList = Cost.listAll(Cost.class);
        RefreshList();
        setCostTextView();

    }

    public void setCostTextView()
    {
        double cost = sumAllCosts();
        String resault = String.format("%.2f",cost);
        totalCostTV.setText(resault+ "zł");
    }

    public void RefreshList()
    {
        CostListAdapter myCostAdpater;
        List<Cost> costList = Cost.listAll(Cost.class);
        myCostAdpater =  new CostListAdapter(costList,this);
        CostListView.setAdapter(myCostAdpater);
    }

    public void demo()
    {
        ListView CostListView;
        CostListAdapter myCostAdpater;

//        for(int i=0;i<20;i++)
//        {
//            Cost t = new Cost(MainActivity.UserId,"Title "+i,"About cost "+i,i*10.99);
//            t.save();
//        }

        CostListView = (ListView) findViewById(R.id.CostListView);

        List<Cost> costList = Cost.listAll(Cost.class);

        myCostAdpater= new CostListAdapter(costList,this);      // Ustawianie wlasnego adaptera w celu wyswietlenia listy

        CostListView.setAdapter(myCostAdpater);

    }

    private double sumAllCosts()
    {
        double totalCost=0;
        for(Cost c :Cost.listAll(Cost.class))
        {
            totalCost = totalCost+c.getPrice();
        }
        return totalCost;
    }

    public void AddNewCostActivityStart(View view) {
        Intent guests = new Intent(this,AddNewCostActivity.class);
        startActivity(guests);
    }

    public void DeleteCost(View view) {
        try {
            Cost selectedCost = allCostList.get(CostListView.getPositionForView((View) view.getParent()));
            Cost.delete(selectedCost);
            Toast.makeText(this, "Usunięto poprawnie wydatek z listy", Toast.LENGTH_SHORT).show();
            RefreshList();
            setCostTextView();
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Nie można usunąć elementu z listy, spróbuj ponownie.", Toast.LENGTH_SHORT).show();
        }


    }
}
