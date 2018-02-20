package com.error_found.kotdroid.imageloading;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindViews({R.id.iv_glide1, R.id.iv_glide2, R.id.iv_glide3})
    List<ImageView> ivList = new ArrayList<>();
    @BindViews({R.id.sdv_fresco1, R.id.sdv_fresco2, R.id.sdv_fresco3})
    List<SimpleDraweeView> sdvList = new ArrayList<>();
    String imgUrl = "http://wallpaperswide.com/download/nature_hd_background-wallpaper-1366x768.jpg";
    String imgUrl2 = "https://farm9.staticflickr.com/8200/8248153196_7a7664e147_b.jpg";
    String imgUrl3 = "https://picsum.photos/200/300/?random";
    String gifUrl = "https://media.giphy.com/media/Rxb9AZOq1U1Vu/giphy.gif";


    String videoUrl = "https://player.vimeo.com/external/122827831.hd.mp4?s=b11c74b0b2216faccbb5b2337a0450629830ed24";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        ViewPropertyTransition.Animator animationObject = new ViewPropertyTransition.Animator() {
            @Override
            public void animate(View view) {
                view.setAlpha(0f);

                ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                fadeAnim.setDuration(2500);
                fadeAnim.start();
            }
        };

        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.fade_in);
        GlideApp
                .with(MainActivity.this)
                .load(imgUrl3)
                .apply(RequestOptions.bitmapTransform(new com.error_found.kotdroid.imageloading.RoundedCornersTransformation(this, 20, 12, "#7D9067", 12)))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)        //triggered when imgUrl can not be loaded from the network
                .fallback(R.drawable.fallback)             //triggered when the imgUrl will be empty or null
                .priority(Priority.IMMEDIATE)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)

//                .transition(DrawableTransitionOptions.withCrossFade())
                .transition(GenericTransitionOptions.with(animationObject))
                .centerCrop()
                .into(ivList.get(2));


        //using Gif in Glide
        GlideApp
                .with(this)
                //.asGif()            //kind of check only load if the url is gif
                //.asBitmap()     //if we provide this method a image will be loaded instead of gif


                .load(gifUrl)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.placeholder)
                .priority(Priority.LOW)
                .transition(GenericTransitionOptions.with(animationObject))
                .error(R.drawable.error)
                .fallback(R.drawable.fallback)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .fitCenter()
                .into(ivList.get(0));

        GlideApp
                .with(this)
                .load(imgUrl)
                .placeholder(R.drawable.placeholder)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(ivList.get(1));


        sdvList.get(0).setImageURI(Uri.parse(imgUrl3));
        sdvList.get(1).setImageURI(Uri.parse(imgUrl));
        ImageRequest cacheRequest=ImageRequestBuilder.newBuilderWithSource(Uri.parse(imgUrl))
                .disableDiskCache()
                .build();
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imgUrl))
                .setRotationOptions(RotationOptions.autoRotate())
                .build();
        ImageRequest resizeRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imgUrl))
                .setResizeOptions(new ResizeOptions(50, 50))
                .build();
        sdvList.get(1).setController(
                Fresco.newDraweeControllerBuilder()
                        .setTapToRetryEnabled(true)
                        .setUri(imgUrl)
                        .setImageRequest(cacheRequest)
                        //.setImageRequest(resizeRequest)
                        .setImageRequest(imageRequest)
                        .build()
        );


    }


}
