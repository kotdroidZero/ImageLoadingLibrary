package com.error_found.kotdroid.imageloading;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user12 on 20/2/18.
 */

public class FrescoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> listUrl;
    Context mContext;
    LayoutInflater mLayoutInflater;
    ImageRequest cacheRequest;

    public FrescoAdapter(Context mContext) {
        this.mContext = mContext;
        listUrl = new ArrayList<>();
        mLayoutInflater = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE));


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FrescoHolder(mLayoutInflater.inflate(R.layout.row_item_fresco, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FrescoHolder mFrescoHolder = ((FrescoHolder) holder);
        Uri uri=Uri.parse(listUrl.get(position));
        Fresco.getImagePipeline().evictFromCache(uri);
        cacheRequest = ImageRequestBuilder.newBuilderWithSource(uri)
                .disableDiskCache()
                .build();

        mFrescoHolder.sdvFresco.setController(
                Fresco.newDraweeControllerBuilder()
                        .setImageRequest(cacheRequest)
                        .build()
        );
        mFrescoHolder.sdvFresco.setImageURI(listUrl.get(position));

    }

    @Override
    public int getItemCount() {
        return listUrl.size();
    }

    public class FrescoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sdv_fresco)
        SimpleDraweeView sdvFresco;

        public FrescoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }


    }

    public void updateList(String url) {
        listUrl.add(url);
        notifyDataSetChanged();

    }
}
