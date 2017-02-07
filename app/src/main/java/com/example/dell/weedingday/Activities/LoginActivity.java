package com.example.dell.weedingday.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.weedingday.Config;
import com.example.dell.weedingday.Entity.User;
import com.example.dell.weedingday.R;

public class LoginActivity extends AppCompatActivity {


    EditText Login;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitControls();
        SharedPreferences settings = getSharedPreferences(Config.PREFS_NAME, 0); // 0 - for private mode
        SharedPreferences.Editor editor = settings.edit();
        boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);

        if(hasLoggedIn)
        {
            Intent mainMenu = new Intent(this, MainActivity.class);
            startActivity(mainMenu);
            finish();
        }

    }

    private void InitControls()
    {
        Login = (EditText) findViewById(R.id.LoginLogin);
        Password = (EditText) findViewById(R.id.PasswordLogin);
    }

    public void RegisterActivityStart(View view) {
        Intent register = new Intent(this,RegisterActivity.class);
        startActivity(register);
    }

    public void LogIn(View view) {
        try {
            SharedPreferences settings = getSharedPreferences(Config.PREFS_NAME, 0); // 0 - for private mode
            SharedPreferences.Editor editor = settings.edit();
            if (User.find(User.class, "login = ? and password = ?", Login.getText().toString(), Password.getText().toString()).size()>0) {
                Intent mainMenu = new Intent(this, MainActivity.class);
                User u = User.find(User.class, "login = ? and password = ?", Login.getText().toString(), Password.getText().toString()).get(0);
                MainActivity.UserId = Integer.parseInt(u.getId().toString());
                editor.putBoolean("hasLoggedIn", true);
                editor.commit();
                Toast.makeText(this, "Poprawnie zalogowano użytkownika "+Login.getText().toString(), Toast.LENGTH_SHORT).show();
                startActivity(mainMenu);
                finish();
            } else {
                Toast.makeText(this, "Użytkownik o podanych danych nie istanie w bazie", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Nie można zalogować, spróbuj ponownie", Toast.LENGTH_LONG).show();
        }
    }

}
