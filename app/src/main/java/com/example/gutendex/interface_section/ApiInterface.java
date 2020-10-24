package com.example.gutendex.interface_section;

import com.example.gutendex.model_section.ResponseBookList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET
    Call<ResponseBookList> getBookList(@Url String url);

    @GET
    Call<ResponseBody> downloadFile(@Url String url);

}
