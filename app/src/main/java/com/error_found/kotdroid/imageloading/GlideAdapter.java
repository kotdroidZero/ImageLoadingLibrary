package com.error_found.kotdroid.imageloading;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user12 on 20/2/18.
 */

public class GlideAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> listUrl;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public GlideAdapter(Context mContext) {
        this.mContext = mContext;
        listUrl = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(mContext);

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GlideHolder(mLayoutInflater.inflate(R.layout.row_item_glide, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GlideHolder glideHolder = ((GlideHolder) holder);
        GlideApp
                .with(mContext)
                .load(listUrl.get(position))
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(glideHolder.ivGlide);
    }

    @Override
    public int getItemCount() {
        return listUrl.size();
    }

    public class GlideHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_glide)
        ImageView ivGlide;

        public GlideHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void updateList(String url) {
        listUrl.add(url);
        notifyDataSetChanged();

    }

}
