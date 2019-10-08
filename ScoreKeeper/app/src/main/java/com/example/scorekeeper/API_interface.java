package com.example.scorekeeper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_interface {
    @GET("registration.php")
    Call<user> performRegistration(@Query("email") String email,
                                   @Query("name")String name,
                                   @Query("surname") String surname,
                                   @Query("password")String password);

    @GET("login.php")
    Call<user> performUserLogin(@Query("email") String email,
                                @Query("password") String password);





}
