package com.example.btlon_movie.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btlon_movie.models.Movie;
import com.example.btlon_movie.adapter.MovieAdapter;
import com.example.btlon_movie.adapter.MovieItemClickListener;
import com.example.btlon_movie.R;
import com.example.btlon_movie.models.Slide;
import com.example.btlon_movie.adapter.SlidePagesAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {
    private ArrayList<Slide> ListSlide;
    private ViewPager sliderPage;
    private SlidePagesAdapter MySlideAdapter;
    private TabLayout RadioSlider;
    private RecyclerView MoveRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tham chieu
        sliderPage=findViewById(R.id.ViewPage);
        RadioSlider=findViewById(R.id.radioSelector);
        MoveRV=findViewById(R.id.Rv_movie);

        //Tao Slider
        ListSlide=new ArrayList<>();
        ListSlide.add(new Slide(R.drawable.joker,"BatMan"));
        ListSlide.add(new Slide(R.drawable.star,"Star"));
        MySlideAdapter = new SlidePagesAdapter(this, ListSlide);

        sliderPage.setAdapter(MySlideAdapter);

        RadioSlider.setupWithViewPager(sliderPage, true);
        // Set Time
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(), 6000, 6000);

        //thiet lap recylerview
        //init data
        List<Movie> lstMovie=new ArrayList<>();
        lstMovie.add(new Movie(1,"The Conjuring",R.drawable.no_way_home,R.drawable.the_conjuring));
        lstMovie.add(new Movie(2,"The Shining",R.drawable.no_way_home,R.drawable.the_shining));
        lstMovie.add(new Movie(3,"Up",R.drawable.no_way_home,R.drawable.up));
        lstMovie.add(new Movie(4,"Your Name",R.drawable.no_way_home,R.drawable.your_name));

        MovieAdapter movieAdapter=new MovieAdapter(this,lstMovie,this);
        MoveRV.setAdapter(movieAdapter);
        MoveRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        //tạo vận chuyển animation giữa 2 actyvity
        Intent intent1=new Intent(MainActivity.this, MovieDetailActivity.class);
        intent1.putExtra("title",movie.getTitle());
        intent1.putExtra("imgUrl",movie.getThumbnail());
        intent1.putExtra("imgCover",movie.getCoverPhoto());


        //tạo animation
        ActivityOptions activityOptions=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                movieImageView,"shareName");

        startActivity(intent1,activityOptions.toBundle());


    }

    class  SliderTimer extends TimerTask{

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new TimerTask() {
                @Override
                public void run() {
                    if(sliderPage.getCurrentItem()<ListSlide.size()-1)
                    {
                        sliderPage.setCurrentItem(sliderPage.getCurrentItem()+1);
                    }else{
                        sliderPage.setCurrentItem(0);
                    }

                }
            });
        }
    }
}