package com.example.gutendex;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.gutendex.adapter_section.AdapterBookList;
import com.example.gutendex.adapter_section.ClickListener;
import com.example.gutendex.base_class.BaseFragment;
import com.example.gutendex.databinding.FragmentListOfBooksBinding;
import com.example.gutendex.model_section.Formats;
import com.example.gutendex.model_section.ResponseBookList;
import com.example.gutendex.view_models_section.ViewModelListOfBooks;

import static com.example.gutendex.utils_section.ConfigGut.END_POINT_BOOKS_ALL;
import static com.example.gutendex.utils_section.ConfigGut.END_POINT_SEARCH;
import static com.example.gutendex.utils_section.ConfigGut.KEY_HTML;
import static com.example.gutendex.utils_section.ConfigGut.KEY_MIME_TYPE;
import static com.example.gutendex.utils_section.ConfigGut.KEY_PDF;
import static com.example.gutendex.utils_section.ConfigGut.KEY_TEXT;
import static com.example.gutendex.utils_section.ConfigGut.KEY_TOPIC;

public class FragmentListOfBooks extends BaseFragment implements SearchView.OnQueryTextListener, View.OnClickListener, ClickListener, ActivityListOfBooks.OnBackPressedListener {

    private static final String ARG_PARAM1 = "param1";
    private String mCategory;

    private FragmentListOfBooksBinding mBindingObject;
    private ViewModelListOfBooks mViewModel;
    private AdapterBookList mAdapter;
    private int pastVisibleItems, visibleItemCount, totalItemCount;
    private String mPageNext = null;
    private String mPagePrevious = null;
    private boolean isQuerySubmit;

    public FragmentListOfBooks() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mCategory Category of selected list of previous page.
     * @return A new instance of fragment Fragment_BusHistory.
     */
    public static FragmentListOfBooks newInstance(String mCategory) {
        FragmentListOfBooks fragment = new FragmentListOfBooks();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mCategory = getArguments().getString(ARG_PARAM1);
        }

        mViewModel = ViewModelProviders.of(this).get(ViewModelListOfBooks.class);
        mViewModel.initialize();

        mAdapter = new AdapterBookList(getContext(), this);

        //observer of live data change after loading from server
        mViewModel.getLiveDataUpdate().observe(this, new Observer<ResponseBookList>() {
            @Override
            public void onChanged(ResponseBookList responseBookList) {

                hideLoading();

                if (responseBookList.getNext() != null)
                    mPageNext = responseBookList.getNext();

                if (responseBookList.getPrevious() != null)
                    mPagePrevious = responseBookList.getPrevious();

                if (responseBookList.getResults().size() > 0) {
                    if (isQuerySubmit) {
                        mAdapter.addData(responseBookList.getResults());
                    } else {
                        mAdapter.updateData(responseBookList.getResults());
                    }
                } else {
                    showMessage(getContext(), getString(R.string.error_no_books));
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_list_of_books, container, false);
        mBindingObject = FragmentListOfBooksBinding.bind(mView);

        mBindingObject.textViewTitle.setText(mCategory);

        GridLayoutManager mLayoutManager;
        if (isPortrait()) {
            mLayoutManager = new GridLayoutManager(getContext(), 3);
        } else {
            mLayoutManager = new GridLayoutManager(getContext(), 6);
        }

        mBindingObject.recyclerViewBooks.setLayoutManager(mLayoutManager);
        mBindingObject.recyclerViewBooks.setAdapter(mAdapter);

        if (isNetworkConnected(getContext())) {
            /**
             * To detect the scrolling end to load next page
             */
            mBindingObject.recyclerViewBooks.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    if (dy > 0) {
                        visibleItemCount = mLayoutManager.getChildCount();
                        totalItemCount = mLayoutManager.getItemCount();
                        pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();

                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            if (mPageNext != null) {
                                showLoading();
                                isQuerySubmit = false;
                                mViewModel.getBookList(mPageNext);
                            }
                        }
                    }
                }
            });

            if (mPagePrevious == null) {

                isQuerySubmit = false;
                // request for view model to get list of books from repository
                mViewModel.getBookList(END_POINT_BOOKS_ALL + "?topic=" + mCategory + KEY_MIME_TYPE);
                //abstract method for progress bar
                showLoading();
            }
        } else {
            showMessage(getContext(),getString(R.string.error_internet));
        }
        init();

        return mView;
    }

    /**
     * initialize listeners
     */
    private void init() {
        mBindingObject.searchView.setOnQueryTextListener(this);
        mBindingObject.buttonBack.setOnClickListener(this);
        //
        ((ActivityListOfBooks) getActivity()).setOnBackPressedListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String searchKey) {
        showLoading();
        isQuerySubmit = true;
        mViewModel.getBookList(END_POINT_SEARCH + searchKey.replace(" ", "%20") + KEY_TOPIC + mCategory + KEY_MIME_TYPE);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void onClick(View view) {
        callFragment(new FragmentDetailsListOfBook());
    }

    @Override
    public void onStop() {
        super.onStop();
        hideLoading();
    }

    @Override
    public void onPositionClicked(int position) {

    }

    @Override
    public void onClickCurrItemBook(Formats formats) {
        switch (getFormateBook(formats)) {
            case KEY_HTML:
                if (isZip(formats.getTextHtmlCharsetUtf8())) {
                    callWebBrowser(formats.getTextHtmlCharsetUtf8());
                }

                break;
            case KEY_PDF:
                if (isZip(formats.getApplicationPdf())) {
                    callWebBrowser(formats.getApplicationPdf());
                }
                break;
            case KEY_TEXT:
                if (isZip(formats.getTextPlainCharsetUsAscii())) {
                    callWebBrowser(formats.getTextPlainCharsetUsAscii());
                }
                break;
            default:
                showMessage(getContext(), getString(R.string.error_noviewer));
        }
    }

    /**
     * This method is used to find url contains zip file or not
     * @param url
     * @return true : contain zip
     */
    public boolean isZip(String url) {
        if (url.contains(".zip")) {
            showAlert(getContext(), getString(R.string.error_noviewer));
        } else {
            return true;
        }
        return false;
    }

    /**
     * This method is use to load url into external web browser
     * @param mUrl
     */
    public void callWebBrowser(String mUrl) {
        if (mUrl != null) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(mUrl));
            startActivity(i);
        }
    }

    /**
     * This method validate the format of book
     * It return format type on priority of HTML , PDF, TEXT
     * @param formats
     * @return
     */
    private String getFormateBook(Formats formats) {

        if (formats.getTextHtmlCharsetUtf8() != null) {
            return KEY_HTML;
        }

        if (formats.getApplicationPdf() != null) {
            return KEY_PDF;
        }

        if (formats.getTextPlainCharsetUsAscii() != null) {
            return KEY_TEXT;
        }

        return "";
    }

    @Override
    public void doBack() {
        callFragment(new FragmentDetailsListOfBook());
    }
}