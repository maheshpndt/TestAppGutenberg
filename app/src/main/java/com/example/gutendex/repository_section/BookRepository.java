package com.example.gutendex.repository_section;

import android.os.Environment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gutendex.R;
import com.example.gutendex.interface_section.ApiInterface;
import com.example.gutendex.model_section.GenreListModel;
import com.example.gutendex.model_section.ResponseBookList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookRepository {
    private static final String BASE_URL = "http://gutendex.com/";
    private MutableLiveData<ResponseBookList> mResponseLiveData;
    private MutableLiveData<String> mDownloadLiveData;
    private ApiInterface mApiInterface;

    /**
     * Use : create object builder for api calling
     */
    public BookRepository() {
        this.mResponseLiveData = new MutableLiveData<>();
        this.mDownloadLiveData = new MutableLiveData<String>();

        HttpLoggingInterceptor mInterceptor = new HttpLoggingInterceptor();
        mInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient mClient = new OkHttpClient.Builder().addInterceptor(mInterceptor).build();

        mApiInterface = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(mClient)
                .build()
                .create(ApiInterface.class);
    }

    /**
     * Use           : trigger api call for to get list of books
     * mApiInterface : callback for response
     * @param url : api end point for book details
     */
    public void getBookList(String url){
        try {
            mApiInterface.getBookList(url).enqueue(new Callback<ResponseBookList>() {
                @Override
                public void onResponse(Call<ResponseBookList> call, Response<ResponseBookList> response) {
                    if (response.isSuccessful()) {
                        mResponseLiveData.postValue(response.body());
                    } else {
                        mResponseLiveData.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBookList> call, Throwable t) {
                    mResponseLiveData.postValue(null);
                }
            });
        } catch (Exception e){
            mResponseLiveData.postValue(null);
        }
    }

    /**
     * Download the image using url from server
     * @param url
     */

    public void downloadPdf(String url){
        try {
            mApiInterface.downloadFile(url).enqueue(new Callback<ResponseBody>() {
                private String fileName = "file.zip";
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {

                        if (url.split("/")[url.split("/").length-1]!=null)
                            fileName = url.split("/")[url.split("/").length-1];

                        String mPath = writeResponseBodyToDisk(response.body(),fileName);
                        mDownloadLiveData.postValue(mPath);
                    } else {
                        mDownloadLiveData.postValue(null);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    mDownloadLiveData.postValue(null);
                }
            });
        } catch (Exception e){
            mDownloadLiveData.postValue(null);
        }
    }

    /**
     * function to store the downloaded file to local storage
     * @param body
     * @param fileName
     * @return
     */
    private String writeResponseBodyToDisk(ResponseBody body, String fileName) {
        try {
            File futureStudioIconFile = new File(Environment.getExternalStorageDirectory() + File.separator + fileName);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                }

                outputStream.flush();

                return futureStudioIconFile.toString();
            } catch (IOException e) {
                return null;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }
    /*
    * use : use to get updated response to the view model
    * */
    public LiveData<ResponseBookList> getResponseLiveData(){
        return mResponseLiveData;
    }

    /*
     * use : use to get updated response to the view model
     * */
    public LiveData<String> getDownloadLiveData(){
        return mDownloadLiveData;
    }

    public List<GenreListModel> getGenreList() {
        ArrayList<GenreListModel> genreListModels = new ArrayList<>();
        genreListModels.add(new GenreListModel(R.drawable.ic_fiction,"Fiction"));
        genreListModels.add(new GenreListModel(R.drawable.ic_drama,"Drama"));
        genreListModels.add(new GenreListModel(R.drawable.ic_humour,"Humour"));
        genreListModels.add(new GenreListModel(R.drawable.ic_politics,"Politics"));
        genreListModels.add(new GenreListModel(R.drawable.ic_philosophy,"Philosophy"));
        genreListModels.add(new GenreListModel(R.drawable.ic_history,"History"));
        genreListModels.add(new GenreListModel(R.drawable.ic_adventure,"Adventure"));
        return genreListModels;
    }

}
