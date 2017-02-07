package com.example.dell.weedingday.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.weedingday.Entity.User;
import com.example.dell.weedingday.R;

public class RegisterActivity extends AppCompatActivity {

    EditText Name;
    EditText Surname;
    EditText Password;
    EditText Login;
    EditText Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        InitControls();
    }

    public void InitControls()
    {
        Name = (EditText) findViewById(R.id.NameRegister);
        Surname = (EditText) findViewById(R.id.SurnameRegister);
        Password = (EditText) findViewById(R.id.PasswordRegister);
        Login = (EditText) findViewById(R.id.LoginRegister);
        Email = (EditText) findViewById(R.id.EmailRegister);
    }

    public void RegisterNewUser(View view) {
        try {
            User checkUser = new User(Name.getText().toString(), Surname.getText().toString(), Login.getText().toString(), Password.getText().toString(), Email.getText().toString());
            if (User.find(User.class,"login = ? and password = ?",Login.getText().toString(),Password.getText().toString()).size()>0)
            {
                Toast.makeText(getApplicationContext(), "Użytkownik o podanych danych już istnieje.", Toast.LENGTH_LONG).show();
                finish();
            }
            else if(Password.getText() != null && Login.getText() != null) {
                User newUser = new User(Name.getText().toString(), Surname.getText().toString(), Login.getText().toString(), Password.getText().toString(), Email.getText().toString());
                newUser.save();
                Toast.makeText(getApplicationContext(), "Poprawnie zarejestrowano nowego użytkownika", Toast.LENGTH_LONG).show();
                finish();
            } else
            {
                Toast.makeText(this, "Nieprawidłowe dane", Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Niepowodzenie podczas zapisu do bazy danych", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
