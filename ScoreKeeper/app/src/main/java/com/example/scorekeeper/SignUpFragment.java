package com.example.scorekeeper;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {
    private EditText Email, Name, Surname, Password, VerifyPassword;
    private TextView EmailValidation, PasswordValidation, PasswordVerification;
    private Button SignUp;
    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);


        Email = view.findViewById(R.id.emailSEditText);
        Name = view.findViewById(R.id.firstNameEditText);
        Surname = view.findViewById(R.id.lastNameEditText);
        Password = view.findViewById(R.id.passwordSEditText);
        VerifyPassword = view.findViewById(R.id.verifyPasswordEditText);

        EmailValidation =view.findViewById(R.id.emailValidation);
        PasswordValidation=view.findViewById(R.id.passwordValidation);
        PasswordVerification = view.findViewById(R.id.passwordVerification);

        SignUp = view.findViewById(R.id.signSUpButton);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSignUp();
            }
        });

        return view;
    }

    public void performSignUp(){
        EmailValidation.setText("");
        PasswordValidation.setText("");
        PasswordVerification.setText("");
        String email = Email.getText().toString();
        String name = Name.getText().toString();
        String surname =Surname.getText().toString();
        String password = Password.getText().toString();
        String verification = VerifyPassword.getText().toString();
        if (isValide(email,name,surname,password,verification)){
            performQueue(email,name,surname,password);
        }

    }

    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches()){

            return true;
        }

        else {
            EmailValidation.setText("Please provide a valid email");
            EmailValidation.setVisibility(View.VISIBLE);
            return false;}
    }

    public boolean isPasswordValide(String password){
        String regExpn =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

        CharSequence inputStr = password;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches()){

            return true;
        }

        else {
            PasswordValidation.setText("Please provide a valid password: at least 8 characters long, one capital letter and digit");
            PasswordValidation.setVisibility(View.VISIBLE);
            return false;}
    }

    public boolean isPasswordVerified(String password, String verification){

        if (password.equals(verification)){
            return  true;
        }

        else {
            PasswordVerification.setText("Passwords do not match");
            PasswordVerification.setVisibility(View.VISIBLE);
            return false;}
    }

    private boolean isValide(String email, String name, String surname , String password, String verification){
        boolean emailv = isEmailValid(email);
        boolean passv = isPasswordValide(password);
        boolean passve = isPasswordVerified(password,verification);
        return (emailv&&passv&passve );


    }

    private void performQueue(String email, String name, String surname, String password){
        Call<user> call = MainActivity.api_interface.performRegistration(email, name, surname, password);
        call.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                if (response.body().getResponse().equals("ok")){
                    MainActivity.prefConfig.displayMessage("Registration successful");
                }
                else if(response.body().getResponse().equals("exist")){
                    MainActivity.prefConfig.displayMessage("User with this email already exists");
                }
                else if (response.body().getResponse().equals("error")){
                    MainActivity.prefConfig.displayMessage("Registration failed");
                }
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {

                MainActivity.prefConfig.displayMessage("No response");
            }
        });

        Email.setText("");
        Name.setText("");
        Surname.setText("");
        Password.setText("");
        VerifyPassword.setText("");

        EmailValidation.setText("");
        PasswordVerification.setText("");
        PasswordValidation.setText("");
        EmailValidation.setVisibility(View.INVISIBLE);
        PasswordVerification.setVisibility(View.INVISIBLE);
        PasswordValidation.setVisibility(View.INVISIBLE);

    }

}
