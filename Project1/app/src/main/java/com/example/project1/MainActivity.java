package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(this);
        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(this);
    }
    public void OnButtonClick(View view){
        Intent play = new Intent(this, com.example.project1.play.class);
        startActivity(play);
    }
    public void OnAboutButtonClick(View view){
        Intent about = new Intent(this, About.class);
        startActivity(about);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button2){
            Intent settings = new Intent(this, settings.class);
            startActivity(settings);
        }
        if(view.getId() == R.id.button2){
            Intent about = new Intent(this, About.class);
            startActivity(about);
        }

    }
}
