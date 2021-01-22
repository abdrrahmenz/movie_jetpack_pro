package com.abdurrahman.movies.data.source.remote.service;

import com.abdurrahman.movies.BuildConfig;
import com.abdurrahman.movies.data.source.remote.BaseUrl;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    private static HttpLoggingInterceptor logging = null;

    public static Retrofit getClient() {
        if (retrofit == null)
            logging = new HttpLoggingInterceptor();
            logging.level(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient interceptor = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

            retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.BASE_URL)
                    .client(interceptor)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
