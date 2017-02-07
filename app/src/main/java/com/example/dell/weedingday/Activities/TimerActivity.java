package com.example.dell.weedingday.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.dell.weedingday.Config;
import com.example.dell.weedingday.R;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.GregorianCalendar;

public class TimerActivity extends AppCompatActivity {

    DatePicker calendar;
    TextView timeSummary;
    Button calculateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        calendar = (DatePicker) findViewById(R.id.datePicker);
        timeSummary = (TextView) findViewById(R.id.TimeSummary);
        calculateBtn = (Button) findViewById(R.id.calculateTimeBtn);

        Date now = new Date();

        SharedPreferences settings = getSharedPreferences(Config.PREFS_NAME, 0); // 0 - for private mode
        SharedPreferences.Editor editor = settings.edit();

        Boolean weedingDateConfig = settings.getBoolean("weedingDayBool",false);
        long weedingDateTime = settings.getLong("weedingTime",now.getTime());

        editor.commit();

        if(weedingDateConfig)
        {
            calculateBtn.setVisibility(View.INVISIBLE);

            long diff = weedingDateTime - now.getTime();
            float dayFloat = (float) diff / (24 * 60 * 60 * 1000);
            double days = Math.ceil(dayFloat);
            String resault = String.format("%.0f",days);
            timeSummary.setText(resault);
        }
        else{
            calculateBtn.setVisibility(View.VISIBLE);
        }




    }

    public void CalculateDaysToWeeding(View view) {
        int year,day,month;
        Date todayDate = new Date();
        Date weedingDate = new Date();

        GregorianCalendar calendarBeg=new GregorianCalendar(calendar.getYear(),
                calendar.getMonth(),calendar.getDayOfMonth());

        weedingDate=calendarBeg.getTime();

        long diff = calendarBeg.getTimeInMillis() - todayDate.getTime();

        float dayFloat = (float) diff / (24 * 60 * 60 * 1000);

        double days = Math.ceil(dayFloat);

        String resault = String.format("%.0f",days);
        timeSummary.setText(resault);

        SharedPreferences settings = getSharedPreferences(Config.PREFS_NAME, 0); // 0 - for private mode
        SharedPreferences.Editor editor = settings.edit();

        editor.putBoolean("weedingDayBool",true);
        editor.putLong("weedingTime",calendarBeg.getTimeInMillis());

        editor.commit();

        calculateBtn.setVisibility(View.INVISIBLE);
    }
}
