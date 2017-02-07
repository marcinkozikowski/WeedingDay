package com.example.dell.weedingday.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.weedingday.CustomAdapters.CostListAdapter;
import com.example.dell.weedingday.CustomAdapters.ServiceListAdapter;
import com.example.dell.weedingday.CustomAdapters.TaskListAdapter;
import com.example.dell.weedingday.Entity.Cost;
import com.example.dell.weedingday.Entity.Service;
import com.example.dell.weedingday.Entity.Task;
import com.example.dell.weedingday.R;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

public class ServiceActivity extends AppCompatActivity {

    TextView totalCostTV;
    TextView multimediaTV;
    TextView clothesTV;
    TextView attractionsTV;
    TextView etcTV;
    TextView foodTV;
    TextView carTV;

    ListView ServiceListView;
    ServiceListAdapter myServiceAdpater;
    List<Service> serviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        totalCostTV = (TextView) findViewById(R.id.TotalServiceCostTextView);
        ServiceListView = (ListView) findViewById(R.id.ServiceListView);

        TabHost mTabHost = (TabHost)findViewById(R.id.tabHost);
        mTabHost.setup();
        //Lets add the first Tab
        TabHost.TabSpec mSpec = mTabHost.newTabSpec(getString(R.string.CostsList));
        mSpec.setContent(R.id.first_Tab);
        mSpec.setIndicator(getString(R.string.ProviderList));
        mTabHost.addTab(mSpec);
        //Lets add the second Tab
        mSpec = mTabHost.newTabSpec(getString(R.string.CostsSummary));
        mSpec.setContent(R.id.second_Tab);
        mSpec.setIndicator(getString(R.string.ServiceCosts));
        mTabHost.addTab(mSpec);

        initTextViews();

        RefreshList();
        setCostTextView();
        setServiceCostTextViews();

    }

    public void initTextViews()
    {
        multimediaTV =(TextView) findViewById(R.id.MultimediaServiceCostTextView);
        clothesTV =(TextView) findViewById(R.id.ClothesServiceCostTextView);
        attractionsTV =(TextView) findViewById(R.id.AttractionsServiceCostTextView);
        etcTV =(TextView) findViewById(R.id.EtcServiceCostTextView);
        foodTV =(TextView) findViewById(R.id.FoodServiceCostTextView);
        carTV =(TextView) findViewById(R.id.CarServiceCostTextView);
    }

    public void setServiceCostTextViews()
    {
        multimediaTV.setText(String.format("%.2f",sumServiceCost("Multimedia")));
        clothesTV.setText(String.format("%.2f",sumServiceCost("Ubrania")));
        attractionsTV.setText(String.format("%.2f",sumServiceCost("Atrakcje dodatkowe")));
        etcTV.setText(String.format("%.2f",sumServiceCost("Różne")));
        foodTV.setText(String.format("%.2f",sumServiceCost("Catering")));
        carTV.setText(String.format("%.2f",sumServiceCost("Samochód")));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        RefreshList();
        setServiceCostTextViews();
        setCostTextView();

    }

    public void RefreshList()
    {
        serviceList = Service.listAll(Service.class);

        myServiceAdpater= new ServiceListAdapter(serviceList,this);      // Ustawianie wlasnego adaptera w celu wyswietlenia listy

        ServiceListView.setAdapter(myServiceAdpater);
    }

    public void setCostTextView()
    {
        double cost = sumAllCosts();
        String resault = String.format("%.2f",cost);
        totalCostTV.setText(resault+ "zł");
    }

    private double sumAllCosts()
    {
        double totalCost=0;
        for(Service c :Service.listAll(Service.class))
        {
            totalCost = totalCost+c.getPrice();
        }
        return totalCost;
    }

    public void AddNewServiceActivityStart(View view) {
        Intent i = new Intent(this,AddNewServiceActivity.class);
        startActivity(i);

    }

    public double sumServiceCost(String type)
    {
        double cost=0;
        for(Service s:serviceList)
        {
            if(s.getType().equals(type))
            {
                cost = cost+s.getPrice();
            }
        }
        return cost;
    }

    public void DeleteService(View view) {

        try {
            Service selectedService = serviceList.get(ServiceListView.getPositionForView((View) view.getParent()));
            Service.delete(selectedService);

            Toast.makeText(this, "Usunięto poprawnie usługodawcę z listy", Toast.LENGTH_SHORT).show();
            RefreshList();
            setServiceCostTextViews();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Nie można usunąć usługodawcy z listy", Toast.LENGTH_SHORT).show();
        }
    }
}
