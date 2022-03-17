package com.example.btlon_movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
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
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(), 4000, 6000);

        //thiet lap recylerview
        //init data
        List<Movie> lstMovie=new ArrayList<>();
        lstMovie.add(new Movie("The Conjuring",R.drawable.the_conjuring));
        lstMovie.add(new Movie("The Shining",R.drawable.the_shining));
        lstMovie.add(new Movie("Up",R.drawable.up));
        lstMovie.add(new Movie("Your Name",R.drawable.your_name));

        MovieAdapter movieAdapter=new MovieAdapter(this,lstMovie);
        MoveRV.setAdapter(movieAdapter);
        MoveRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

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