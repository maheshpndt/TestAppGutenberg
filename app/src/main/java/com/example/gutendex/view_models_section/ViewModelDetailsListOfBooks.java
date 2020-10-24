package com.example.gutendex.view_models_section;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gutendex.model_section.GenreListModel;
import com.example.gutendex.model_section.ResponseBookList;
import com.example.gutendex.repository_section.BookRepository;
import java.util.List;

public class ViewModelDetailsListOfBooks extends AndroidViewModel {

    private BookRepository mBookRepository;
    private LiveData<ResponseBookList> mResponseLiveData;

    public ViewModelDetailsListOfBooks(@NonNull Application application) {
        super(application);
    }

    public void initialize(){
        mBookRepository = new BookRepository();
        mResponseLiveData = mBookRepository.getResponseLiveData();
    }

    public List<GenreListModel> getGenreList(){
        return mBookRepository.getGenreList();
    }
}
