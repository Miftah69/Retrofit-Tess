package com.example.miftah.retrofitnostraok.model;

import com.example.miftah.retrofitnostraok.model.PersonResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Miftah on 2/4/2018.
 */

public interface ApiInterface {
    @Headers("Content-Type: application/json")
    @GET("api/v1/person")
    Call<PersonResponse> view();

    @Headers("Content-Type: application/json")
    @POST("api/v1/person")
    Call<PersonResponse> add(
            @Field("id") String id,
            @Field("version") String version,
            @Field("name") String name,
            @Field("address") String address,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("picture") String picture
    );

    @Headers("Content-Type: application/json")
    @GET("api/v1/person")
    Call<PersonResponse> update(
            @Field("id") String id,
            @Field("version") String version,
            @Field("name") String name,
            @Field("address") String address,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("picture") String picture
    );

    @Headers("Content-Type: application/json")
    @GET("api/v1/person")
    Call<PersonResponse> delete(
            @Field("id") String id
    );



}
