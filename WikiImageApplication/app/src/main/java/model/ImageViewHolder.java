package model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import activities.R;
import adapter.ImageAdapter;

/**
 * Created by thukralp on 27/02/16.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView flikrImageView;
    public TextView pageTitle;
    ImageAdapter.OnItemClickListener mListener;

    public ImageViewHolder(View itemView,ImageAdapter.OnItemClickListener listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        mListener = listener;
        flikrImageView = (ImageView)itemView.findViewById(R.id.imageView);
        pageTitle=(TextView)itemView.findViewById(R.id.pageTitle);
    }

    @Override
    public void onClick(View view) {
        mListener.onItemClick(getAdapterPosition(),view);
    }
}
