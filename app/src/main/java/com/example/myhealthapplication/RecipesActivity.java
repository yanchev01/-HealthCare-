package com.example.myhealthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class RecipesActivity extends AppCompatActivity {

    EditText notesEditText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        notesEditText = findViewById(R.id.notesEditText);

        // Получение экземпляра SharedPreferences
        sharedPreferences = getSharedPreferences("RecipesNotes", Context.MODE_PRIVATE);

        // Загрузка сохраненных заметок (если есть)
        String savedNotes = sharedPreferences.getString("notes", "");
        notesEditText.setText(savedNotes);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Сохранение заметок при выходе из активности
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("notes", notesEditText.getText().toString());
        editor.apply();
    }
}
