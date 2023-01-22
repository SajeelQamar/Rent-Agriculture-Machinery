package com.example.my;

import android.provider.ContactsContract;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface apiset {
    @FormUrlEncoded
    @POST("signup.php")
    Call<responsemodel> getregister(
        @Field("nameapi") String name,
        @Field("emailapi") String email,
        @Field("phapi") String phonenumber,
        @Field("passwordapi") String password,
        @Field("locationapi") String location
    );
   /* @Multipart
    @POST("API/addpost")
    Call<ResponseBody> addpost(
            @Part MultipartBody.Part Image,
            @Part("Mname") okhttp3.RequestBody Mname,
            @Part("Model") okhttp3.RequestBody Model,
            @Part("Specs") okhttp3.RequestBody Specs,
            @Part("EHrate") okhttp3.RequestBody EHrate,
            @Part("Location") okhttp3.RequestBody Location
    ); */
}
