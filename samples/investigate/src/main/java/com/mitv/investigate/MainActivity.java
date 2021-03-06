package com.mitv.investigate;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.EventLog.Event;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends FragmentActivity {
    private AtomicInteger count = new AtomicInteger(0);

    ImageView mIvImg;
    CustomTarget<Bitmap> customTarget = new CustomTarget<Bitmap>() {
        @Override
        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
            mIvImg.setImageBitmap(resource);
            Log.i("investigate", "load finished:" + count.incrementAndGet());
        }

        @Override
        public void onLoadCleared(@Nullable Drawable placeholder) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvImg = findViewById(R.id.iv_img);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.i("investigate", "load start");
            if (count.get() % 2 == 0) {
                Glide.with(this).asBitmap().load("https://c-ssl.duitang.com/uploads/item/202002/03/20200203232102_hjcin.thumb.700_0.jpg").into(customTarget);
            } else {
                Glide.with(this).asBitmap().load("https://c-ssl.duitang.com/uploads/item/202003/18/20200318222019_SFuNx.thumb.700_0.jpeg").into(customTarget);
            }
        }
        return true;
    }
}