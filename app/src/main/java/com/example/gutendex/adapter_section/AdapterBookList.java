package com.example.gutendex.adapter_section;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.gutendex.R;
import com.example.gutendex.model_section.Result;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class AdapterBookList extends RecyclerView.Adapter<AdapterBookList.ViewHolder> {

    private final Context mContext;
    private List<Result> mListOfBooks = new ArrayList<>();
    private ClickListener mListener;

    public AdapterBookList(Context context,ClickListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    public void updateData(List<Result> results) {

        for (Result data : results) {
            this.mListOfBooks.add(new Result(data.getAuthors(), data.getBookshelves(), data.getDownloadCount(), data.getFormats(), data.getId(), data.getLanguages(), data.getMediaType(), data.getSubjects(), data.getTitle()));
        }

        notifyDataSetChanged();
    }

    public void addData(List<Result> results) {
        this.mListOfBooks.clear();
        this.mListOfBooks = results;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_books, parent, false);
        return new ViewHolder(mView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mTextTitle.setText(mListOfBooks.get(position).getTitle());
        try {
            holder.mTextAuthor.setText(mListOfBooks.get(position).getAuthors().get(0).getName());
        } catch (IndexOutOfBoundsException e) {
        }

        loadImage(holder, String.valueOf(mListOfBooks.get(position).getFormats().getmImageJpeg()));

    }

    private void loadImage(ViewHolder holder, String mImageJpeg) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        Glide.with(holder.itemView)
                .load(mImageJpeg)
                .apply(requestOptions)
                .into(holder.mImageCategory);
    }

    @Override
    public int getItemCount() {
        return mListOfBooks.size();
    }

    /*
     * Get all items view id's to access view element
     * */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final WeakReference<ClickListener> listenerRef;
        private final TextView mTextTitle;
        private final TextView mTextAuthor;
        private final ImageView mImageCategory;
        private final ConstraintLayout mLayoutCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listenerRef = new WeakReference<>(mListener);
            mTextTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            mTextAuthor = (TextView) itemView.findViewById(R.id.textViewAuthor);
            mImageCategory = (ImageView) itemView.findViewById(R.id.imageViewCategory);
            mLayoutCard = (ConstraintLayout) itemView.findViewById(R.id.layoutCard);

            mLayoutCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerRef.get().onClickCurrItemBook(mListOfBooks.get(getAdapterPosition()).getFormats());
                }
            });
        }
    }
}
