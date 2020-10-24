package com.example.gutendex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.gutendex.databinding.ActivityListOfBooksBinding;


public class ActivityListOfBooks extends AppCompatActivity {

    private ActivityListOfBooksBinding mBindingObject;

    protected ActivityListOfBooks.OnBackPressedListener onBackPressedListener;

    public interface OnBackPressedListener {
        void doBack();
    }

    public void setOnBackPressedListener(ActivityListOfBooks.OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null) {
            onBackPressedListener.doBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindingObject = DataBindingUtil.setContentView(this,R.layout.activity_list_of_books);
        getSupportActionBar().hide();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new FragmentDetailsListOfBook()).commit();
    }
}