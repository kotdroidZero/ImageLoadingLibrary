package com.error_found.kotdroid.imageloading;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by user12 on 19/2/18.
 */

public class CustomView extends FrameLayout {

    @BindView(R.id.iv_image) ImageView imageView;
    @BindView(R.id.tv_textview)
    TextView textView;

    public void initialize(Context context)
    {
        inflate(context,R.layout.view_custom,this);
        ButterKnife.bind(this);
    }
    public CustomView(@NonNull Context context) {
        super(context);
        initialize(context);

    }

    public CustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    public CustomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public void setImage(Drawable drawable)
    {
        imageView.setImageDrawable(drawable);
    }
}
