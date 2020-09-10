package com.rohit.examples.android.indiancoffeehouse;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {

    private ArrayList<Integer> mImgSlide;
    private LayoutInflater mlayoutInflater;
    private Context mcontext;

    public ImageAdapter(Context context, ArrayList<Integer> img_Slide) {
        this.mcontext = context;
        this.mImgSlide = img_Slide;
        this.mlayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mImgSlide.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View imageLayout = mlayoutInflater.inflate(R.layout.slide_image, container, false);

        assert imageLayout != null;
        final ImageView imageView = imageLayout.findViewById(R.id.image);

        imageView.setImageResource(mImgSlide.get(position));
        container.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
    }

    @Nullable
    @Override
    public Parcelable saveState() {
        return null;
    }
}
