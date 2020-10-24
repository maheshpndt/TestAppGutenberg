package com.example.gutendex.view_models_section;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gutendex.model_section.ResponseBookList;
import com.example.gutendex.repository_section.BookRepository;

/*
* Store and manage user data and also useful for handle rotation change scenario
* */

public class ViewModelListOfBooks extends AndroidViewModel {

    private BookRepository mBookRepository;
    private LiveData<ResponseBookList> mResponseLiveData;
    private LiveData<String> mResponseDownloaded;

    public ViewModelListOfBooks(@NonNull Application application) {
        super(application);
    }

    public void initialize(){
        mBookRepository = new BookRepository();
        mResponseLiveData = mBookRepository.getResponseLiveData();
        mResponseDownloaded = mBookRepository.getDownloadLiveData();
    }

    public void getBookList(String url){
        mBookRepository.getBookList(url);
    }

    public void downloadPdf(String url){
        mBookRepository.downloadPdf(url);
    }

    public LiveData<String> getDownloadedFilePath(){
        return mResponseDownloaded;
    }

    public LiveData<ResponseBookList> getLiveDataUpdate(){
        return mResponseLiveData;
    }
}
