package com.example.gutendex.adapter_section;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.gutendex.R;
import com.example.gutendex.model_section.GenreListModel;
import com.example.gutendex.model_section.Result;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class AdapterGenreList extends RecyclerView.Adapter<AdapterGenreList.ViewHolder> {

    private final Context mContext;
    private List<GenreListModel> mListOfBooks = new ArrayList<>();
    private ClickListener mListener;

    public AdapterGenreList(Context context,ClickListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    public void addData(List<GenreListModel> results){
        this.mListOfBooks = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_genre, parent, false);
        return new ViewHolder(mView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextTitle.setText(mListOfBooks.get(position).getTitle());
        holder.mImageCategory.setImageDrawable(mContext.getDrawable(mListOfBooks.get(position).getImage()));
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
        private final Button mTextTitle;
        private final ImageView mImageCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listenerRef = new WeakReference<>(mListener);
            mTextTitle = (Button) itemView.findViewById(R.id.textViewGenreName);
            mImageCategory = (ImageView) itemView.findViewById(R.id.imageViewCategory);

            mTextTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerRef.get().onPositionClicked(getAdapterPosition());
                }
            });
        }
    }
}
