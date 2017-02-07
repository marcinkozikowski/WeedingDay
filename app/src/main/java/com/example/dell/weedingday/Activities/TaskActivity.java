package com.example.dell.weedingday.Activities;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.weedingday.CustomAdapters.CostListAdapter;
import com.example.dell.weedingday.CustomAdapters.GuestListAdapter;
import com.example.dell.weedingday.CustomAdapters.TaskListAdapter;
import com.example.dell.weedingday.Entity.Cost;
import com.example.dell.weedingday.Entity.Guest;
import com.example.dell.weedingday.Entity.Task;
import com.example.dell.weedingday.R;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TaskActivity extends Activity {

    private  ListView TaskListView;
    private  TaskListAdapter myTaskAdpater;
    private  List<Task> taskList;
    private Timer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        //Task.deleteAll(Task.class);

        TaskListView = (ListView) findViewById(R.id.TaskListView);
        RefreshList();

        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Date now = new Date();
                Log.w("Timer sie uruchomil",now.toString());
                checkTaskToDoTomorrow();
            }
        }, 0, 60000);//sprawdz co 10 minut
    }



    @Override
    protected  void onResume()
    {
        super.onResume();
        RefreshList();
        checkTaskToDoTomorrow();
    }

    public void RefreshList()
    {
        taskList = Task.listAll(Task.class);
        myTaskAdpater= new TaskListAdapter(taskList,this);      // Ustawianie wlasnego adaptera w celu wyswietlenia listy
        TaskListView.setAdapter(myTaskAdpater);
    }

    public void AddNewTaskActivityStart(View view) {
        Intent guests = new Intent(this,AddNewTaskActivity.class);
        startActivity(guests);
    }

    public void DeleteTask(View view) {

        try {
            Task selectedTask = taskList.get(TaskListView.getPositionForView((View) view.getParent()));
            Cost.delete(selectedTask);

            Toast.makeText(this, R.string.TaskDeletedSucces, Toast.LENGTH_SHORT).show();
            RefreshList();
        }
        catch (Exception e)
        {
            Toast.makeText(this, R.string.TaskDeletedFail, Toast.LENGTH_SHORT).show();
        }
    }

    public void TaskCheckBoxClick(View view) {
        CheckBox done = (CheckBox) findViewById(R.id.TaskCheckBox);
        Task selectedTask = taskList.get(TaskListView.getPositionForView((View) view.getParent()));

        Log.w("Pozycja taska", String.valueOf(TaskListView.getPositionForView((View) view.getParent())));

        if(selectedTask.getCompleted()==false) {
            selectedTask.setCompleted(true);
            selectedTask.save();
            RefreshList();
        }
        else if(selectedTask.getCompleted()==true)
        {
            selectedTask.setCompleted(false);
            selectedTask.save();
            RefreshList();
        }

    }

    public void checkTaskToDoTomorrow()
    {
        Date now = new Date();
        for(Task t:Task.listAll(Task.class))
        {
            if(t.getTaskExecusionDate().getTime()-now.getTime()>20400000 && t.getTaskExecusionDate().getTime()-now.getTime()<129600000 && t.getCompleted()==false   )
            {
                String message = t.getTitle();
                addNotification(message);
            }
        }
    }
    public void addNotification(String message) {
        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_church)
                        .setContentTitle("WeedingDay")
                        .setContentText("Zbliża się data wykonania zadania: "+message);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

}
