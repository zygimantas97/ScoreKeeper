package com.example.scorekeeper;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {

    private TextView TextView;
    private Button LogOut;
    private  Button MyGames;
    OnLogoutListener logoutListener;
    OnMyGamesListener myGamesListener;

    public interface OnLogoutListener{
        public void performLogout();
    }
    public interface OnMyGamesListener{
        public void performMyGames();
    }
    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        TextView = view.findViewById(R.id.welcome_message);
        TextView.setText("Welcome " + MainActivity.prefConfig.readName());
        LogOut = view.findViewById(R.id.logout_button);
        MyGames = view.findViewById(R.id.gameListButton);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutListener.performLogout();
            }
        });
        MyGames.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                myGamesListener.performMyGames();
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        logoutListener = (OnLogoutListener) activity;
        myGamesListener = (OnMyGamesListener)activity;
    }
}
