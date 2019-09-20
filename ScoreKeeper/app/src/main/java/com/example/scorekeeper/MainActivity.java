package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButtonClickSignUp(View view){
        Intent intentObj = new Intent(this, SignUpActivity.class);
        startActivity(intentObj);
    }
    public void onButtonClickSignIn(View view){

    }
}
