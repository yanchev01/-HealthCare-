package com.example.myhealthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    EditText eUsername, edPassword;
    Button btn;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eUsername = findViewById(R.id.editTextUsername);
        edPassword = findViewById(R.id.editTextPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewRegister);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = eUsername.getText().toString();
                String password = edPassword.getText().toString();
                Database db = new Database();
                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Заполните все пункты", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.login(username, password)) {
                        Toast.makeText(getApplicationContext(), "Вход выполнен", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        // save our data with key and value
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(), "Пользователя с такими данными не существует", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}