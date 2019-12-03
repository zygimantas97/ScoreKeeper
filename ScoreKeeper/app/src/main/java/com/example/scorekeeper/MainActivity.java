package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginListener, WelcomeFragment.OnLogoutListener, WelcomeFragment.OnMyGamesListener {
    public  static prefConfig prefConfig;
    public static API_interface api_interface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefConfig = new prefConfig(this);
        api_interface = API_client.getAPI_client().create(API_interface.class);
        if (findViewById(R.id.fragment_container)!=null){
            if (savedInstanceState!=null){
                return;
            }
            if (prefConfig.readLoginStatus()){
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new WelcomeFragment()).commit();
            }

            else{
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new LoginFragment()).commit();
            }
        }
    }




    @Override
    public void performSignup() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SignUpFragment()).addToBackStack(null).commit();
    }

    @Override
    public void performLogin(String email) {
        prefConfig.writeName(email);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WelcomeFragment()).commit();

    }

    @Override
    public void performLogout() {
        prefConfig.writeLoginStatus(false);
        prefConfig.writeName("User");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();

    }

    @Override
    public void performMyGames() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
