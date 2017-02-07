package com.example.dell.weedingday.CustomAdapters;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.weedingday.Entity.Task;
import com.example.dell.weedingday.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Dell on 2017-01-06.
 */

public class TaskListAdapter extends BaseAdapter {

    private List<Task> tasksList;
    private final LayoutInflater inflater;
    private Context context;

    public TaskListAdapter(List<Task> tasksList, Context context) {
        super();
        this.tasksList = tasksList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return tasksList.size();
    }

    @Override
    public Object getItem(int position) {
        return tasksList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tasksList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.task_item, parent, false);

        TextView titleTask = (TextView) rowView.findViewById(R.id.TitleTask);
        CheckBox taskCh = (CheckBox) rowView.findViewById(R.id.TaskCheckBox);
        TextView aboutTask = (TextView) rowView.findViewById(R.id.AboutTask);
        TextView dateTask = (TextView) rowView.findViewById(R.id.DateTask);

        Task t = (Task) getItem(position);


        titleTask.setText(t.getTitle());
        taskCh.setChecked(t.getCompleted());
        aboutTask.setText(t.getAbout());

        Date a = t.getTaskExecusionDate();
        DateFormat df = new SimpleDateFormat("dd//MM/yyyy");
        String now = df.format(a);
        dateTask.setText(now);

        return rowView;
    }
}
