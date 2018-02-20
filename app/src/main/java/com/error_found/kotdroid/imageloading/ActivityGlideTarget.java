package com.error_found.kotdroid.imageloading;

import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;

public class ActivityGlideTarget extends AppCompatActivity {

    CustomView customView;
    String imgUrl3 = "https://picsum.photos/200/300/?random";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_target);

        customView=findViewById(R.id.custom_view);
        ViewTarget viewTarget=new ViewTarget<CustomView,BitmapDrawable>(customView) {
            @Override
            public void onResourceReady(@NonNull BitmapDrawable resource, @Nullable Transition<? super BitmapDrawable> transition) {
                this.view.setImage(resource);
            }
        };

        GlideApp
                .with(this)
                .load(imgUrl3)
                .into(viewTarget);


    }
}
