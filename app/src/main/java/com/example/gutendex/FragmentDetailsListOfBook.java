package com.example.gutendex;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gutendex.adapter_section.AdapterGenreList;
import com.example.gutendex.adapter_section.ClickListener;
import com.example.gutendex.base_class.BaseFragment;
import com.example.gutendex.databinding.FragmentDetailsListOfBookBinding;
import com.example.gutendex.model_section.Formats;
import com.example.gutendex.view_models_section.ViewModelDetailsListOfBooks;

public class FragmentDetailsListOfBook extends BaseFragment implements ClickListener {

    private FragmentDetailsListOfBookBinding mBindingObject;
    private ViewModelDetailsListOfBooks mViewModel;

    public FragmentDetailsListOfBook() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ViewModelDetailsListOfBooks.class);
        mViewModel.initialize();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_details_list_of_book, container, false);
        mBindingObject = FragmentDetailsListOfBookBinding.bind(mView);

        AdapterGenreList mAdapter = new AdapterGenreList(getContext(),this);

        GridLayoutManager mLayoutManager;
        if (isPortrait()) {
            mLayoutManager = new GridLayoutManager(getContext(), 1);
        } else {
            mLayoutManager = new GridLayoutManager(getContext(), 2);
        }

        mBindingObject.recyclerViewGenre.setLayoutManager(mLayoutManager);
        mBindingObject.recyclerViewGenre.setAdapter(mAdapter);

        mAdapter.addData(mViewModel.getGenreList());

        return mView;
    }

    @Override
    public void onPositionClicked(int position) {
        callFragment(new FragmentListOfBooks().newInstance(mViewModel.getGenreList().get(position).getTitle()));
    }

    @Override
    public void onClickCurrItemBook(Formats formats) {

    }

}