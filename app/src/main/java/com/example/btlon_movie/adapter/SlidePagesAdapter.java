package com.example.btlon_movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.btlon_movie.DownloadImageTask;
import com.example.btlon_movie.R;
import com.example.btlon_movie.models.Movie;
import com.example.btlon_movie.models.Slide;
import com.example.btlon_movie.ui.MovieDetailActivity;
import com.example.btlon_movie.ui.VideoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SlidePagesAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Movie> ListSlide;

    public SlidePagesAdapter(Context context, ArrayList<Movie> listSlide) {
        this.context = context;
        ListSlide = listSlide;
    }

    @Override
    public int getCount() {
        return ListSlide.size();
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout=inflater.inflate(R.layout.silde_item, null);

        ImageView SlideImg=slideLayout.findViewById(R.id.imageView);
        TextView SlideTitle=slideLayout.findViewById(R.id.Silde_title);
        new DownloadImageTask(SlideImg)
                .execute(ListSlide.get(position).getImage());

        SlideTitle.setText(ListSlide.get(position).getName());
        //Play Video
        FloatingActionButton play_fab = slideLayout.findViewById(R.id.PlayVideoSlide);
        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPlay = new Intent(context, VideoActivity.class);
                intentPlay.putExtra("IdVideo", ListSlide.get(position).getLink());
                context.startActivity(intentPlay);
            }
        });
        container.addView(slideLayout);
        return slideLayout;
    }
    
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
