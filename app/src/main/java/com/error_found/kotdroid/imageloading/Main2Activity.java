package com.error_found.kotdroid.imageloading;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.recycler_view_glide)
    RecyclerView recyclerViewGlide;
    @BindView(R.id.recycler_view_fresco)
    RecyclerView recyclerViewFresco;

    String imgUrlRandom = "https://picsum.photos/200/300/?random";

    private GlideAdapter mGlideAdapter;
    private FrescoAdapter mFrescoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        mGlideAdapter = new GlideAdapter(this);
        mFrescoAdapter = new FrescoAdapter(this);

        GridLayoutManager ggm = new GridLayoutManager(this, 2);
        GridLayoutManager ggm1 = new GridLayoutManager(this, 2);

        for (int i = 0; i < 100; i++) {
            mFrescoAdapter.updateList(imgUrlRandom);
            mGlideAdapter.updateList(imgUrlRandom);
        }

        recyclerViewGlide.setLayoutManager(ggm);
        recyclerViewFresco.setLayoutManager(ggm1);

        recyclerViewGlide.setAdapter(mGlideAdapter);
        recyclerViewFresco.setAdapter(mFrescoAdapter);


    }
}
