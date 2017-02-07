package com.example.dell.weedingday.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.dell.weedingday.Config;
import com.example.dell.weedingday.ListGuestsTemp;
import com.example.dell.weedingday.R;


public class MainActivity extends AppCompatActivity {

    public static int UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void Login_activity(View view) {

        Intent login = new Intent(this,LoginActivity.class);
        startActivity(login);
    }

    public void GuestsActivityStart(View view) {
        Intent guests = new Intent(this,GuestListActivity.class);
        startActivity(guests);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoffaction:
                SharedPreferences settings = getSharedPreferences(Config.PREFS_NAME, 0); // 0 - for private mode
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("hasLoggedIn", false);
                editor.commit();
                Intent login = new Intent(this, LoginActivity.class);
                startActivity(login);
                finish();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void TaskActivityStart(View view) {
        Intent guests = new Intent(this,TaskActivity.class);
        startActivity(guests);
    }

    public void ListGuestTempStart(View view) {
        Intent guests = new Intent(this,ListGuestsTemp.class);
        startActivity(guests);
    }

    public void CostActivityStart(View view) {
        Intent guests = new Intent(this,CostActivity.class);
        startActivity(guests);
    }

    public void ServiceActivityStart(View view) {
        Intent guests = new Intent(this,ServiceActivity.class);
        startActivity(guests);
    }

    public void TimerActivityStart(View view) {
        Intent guests = new Intent(this,TimerActivity.class);
        startActivity(guests);
    }
}
