package com.example.my;

import com.squareup.okhttp.ResponseBody;

import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class apiController  {
  //  private  static final String url="http://192.168.1.5/API/";
    private  static final String url="http://192.168.43.10/API/";
    private static apiController clientobject;
    private static Retrofit retrofit;

    public  static Retrofit getClient(){
        HttpLoggingInterceptor interceptor= new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder().
                readTimeout(60, TimeUnit.SECONDS).
                connectTimeout(60,TimeUnit.SECONDS).
                writeTimeout(10,TimeUnit.MINUTES).
                addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
  /*   public interface UploadService {
        @Multipart
        @POST("API/addpost") //test is name of controller and upload is name of method in API
        Call<ResponseBody> addpost(
                @Part MultipartBody.Part photo
        );
    } */

    public apiController()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized apiController getInstance()
    {
        if (clientobject==null)
            clientobject=new apiController();
        return clientobject;
    }
    apiset getapi()
    {
        return retrofit.create(apiset.class);
    }
}
