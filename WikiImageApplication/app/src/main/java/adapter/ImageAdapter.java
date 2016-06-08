package adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import activities.R;
import model.ImageViewHolder;
import model.Page;
import utils.ImageUtils;

/**
 * Created by thukralp on 27/02/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    List<Page> mPageList;
    Activity mContext;
    OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int flikrImagePosition,View view);
    }

    public ImageAdapter(List<Page> pageList,Activity mContext)
    {
        mPageList = pageList;
        this.mContext=mContext;
        if(mContext instanceof OnItemClickListener) {
            mListener = (OnItemClickListener) mContext;
        }
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_image_item,null);
        return new ImageViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.pageTitle.setText(mPageList.get(position).getTitle());
        if(mPageList.get(position).getThumbnail()!=null) {
            Picasso.with(mContext).load(ImageUtils.generateImageUrl(mPageList.get(position))).resize(mPageList.get(position).getThumbnail().getWidth(), mPageList.get(position).getThumbnail().getHeight()).placeholder(R.drawable.placeholder).into(holder.flikrImageView);
        }
        else
        {
            Picasso.with(mContext).load(ImageUtils.generateImageUrl(mPageList.get(position))).placeholder(R.drawable.placeholder).into(holder.flikrImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mPageList.size();
    }



}
