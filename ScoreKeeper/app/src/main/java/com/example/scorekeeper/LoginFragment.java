package com.example.scorekeeper;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private EditText Email, Password;
    Button Sign_up, LogIn;
    OnLoginListener loginListener;
    public interface  OnLoginListener{
        public void performSignup();
        public void performLogin(String email);
    }
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Sign_up = view.findViewById(R.id.signUpButton);
        Email = view.findViewById(R.id.emailLEditText);
        Password = view.findViewById(R.id.passwordLEditText);
        LogIn = view.findViewById(R.id.signInButton);

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogIn();
            }
        });

        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginListener.performSignup();
            }
        });


        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginListener = (OnLoginListener) activity;
    }

    private void performLogIn(){
        final String email = Email.getText().toString();
        String password = Password.getText().toString();

        Call<user>  call = MainActivity.api_interface.performUserLogin(email,password);
        call.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                if (response.body().getResponse().equals("ok")){
                    MainActivity.prefConfig.writeLoginStatus(true);
                    loginListener.performLogin(response.body().getEmail());

                }
                else if (response.body().getResponse().equals("error")){
                    MainActivity.prefConfig.displayMessage("Login Failed");
                }
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                MainActivity.prefConfig.displayMessage("No response");
            }
        });

        Email.setText("");
        Password.setText("");
    }
}
