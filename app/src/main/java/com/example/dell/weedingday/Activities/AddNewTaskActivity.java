package com.example.dell.weedingday.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.weedingday.Entity.Task;
import com.example.dell.weedingday.R;

import java.util.Date;
import java.util.GregorianCalendar;

public class AddNewTaskActivity extends AppCompatActivity {

    EditText title;
    EditText about;
    DatePicker taskDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);

        title = (EditText)findViewById(R.id.TaskTitleAdd);
        about = (EditText)findViewById(R.id.TaskAboutAdd);
        taskDatePicker = (DatePicker) findViewById(R.id.TaskDataPicker);

    }

    public void AddNewTaskToList(View view) {

        Task t;

        String _title;
        String _about;
        int _day;
        int _month;
        int _year;

        _title = title.getText().toString();
        _about = about.getText().toString();

        Date taskDate = new Date();

        GregorianCalendar calendarBeg=new GregorianCalendar(taskDatePicker.getYear(),
                taskDatePicker.getMonth(),taskDatePicker.getDayOfMonth());

        taskDate=calendarBeg.getTime();


        t = new Task(MainActivity.UserId,_title,_about,taskDate,false);
        t.save();

        Toast.makeText(this, "Poprawnie dodano nowe zadanie", Toast.LENGTH_SHORT).show();

        finish();
    }
}
