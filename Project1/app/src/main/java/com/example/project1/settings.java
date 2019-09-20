package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class settings extends AppCompatActivity {
private final String PREFS_NAME="player_prefs";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        EditText playerName = findViewById(R.id.Name);

        SharedPreferences file = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String name = file.getString("name", "nenurodyta");
        playerName.setText(name);
    }

    public void onSaveClick(View view){

        finish();
        EditText playerName = findViewById(R.id.Name);
        String name = playerName.getText().toString();
        SharedPreferences file = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefs_editor = file.edit();
        prefs_editor.putString("name", name);
        prefs_editor.apply();

    }
}
