package com.example.btlon_movie.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.btlon_movie.models.Category;
import com.example.btlon_movie.models.Movie;
import com.example.btlon_movie.adapter.MovieAdapter;
import com.example.btlon_movie.adapter.MovieItemClickListener;
import com.example.btlon_movie.R;
import com.example.btlon_movie.models.Movie_Category;
import com.example.btlon_movie.models.Slide;
import com.example.btlon_movie.adapter.SlidePagesAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {
    private ArrayList<Slide> ListSlide;
    private ViewPager sliderPage;
    private SlidePagesAdapter MySlideAdapter;
    private TabLayout RadioSlider;
    private RecyclerView MoveRV;
    private TabLayout Categorytab;
    private MovieAdapter movieAdapter;
    private List<Movie> lstMovie,lstActionbMovie,lstHorrorMovie,lstDramaMovie,lstCartoonMovie,lstAdventureMovie;
    private List<Category> lstCategory;
    private List<Movie_Category> lstMovieCategory;
    private FireB


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tham chieu
        sliderPage=findViewById(R.id.ViewPage);
        RadioSlider=findViewById(R.id.radioSelector);
        MoveRV=findViewById(R.id.Rv_movie);
        Categorytab=findViewById(R.id.tabLayout);

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
        lstMovie=new ArrayList<>();
        lstMovie.add(new Movie(1,"The Conjuring",R.drawable.the_conjuring_bg," ",R.drawable.the_conjuring,"", "", "" ));
        lstMovie.add(new Movie(2,"The Shining",R.drawable.the_shining_bg,"",R.drawable.the_shining,"", "", ""  ));
        lstMovie.add(new Movie(3,"Up",R.drawable.up_bg," ",R.drawable.up,"", "", "" ));
        lstMovie.add(new Movie(4,"Your Name",R.drawable.your_name_bg," ",R.drawable.your_name,"", "", "" ));
        lstMovie.add(new Movie(5,"Doremon",R.drawable.doremon_bg," ",R.drawable.doremon,"", "", "" ));
        lstMovie.add(new Movie(6,"End Game",R.drawable.endgame_bg," ",0x7f0700cc,"", "", "" ));
        lstMovie.add(new Movie(7,"A Silent Void",R.drawable.a_silent_voice_bg," ",R.drawable.a_silent_voice,"", "", "" ));
        lstMovie.add(new Movie(8,"Bathman",R.drawable.batman_bg," ",R.drawable.batman,"", "", "" ));
        lstMovie.add(new Movie(9,"Get Out",R.drawable.get_out_bg," ",R.drawable.get_out,"", "", "" ));
        lstMovie.add(new Movie(10,"hereditary",R.drawable.hereditary_bg," ",R.drawable.hereditary,"", "", "" ));
        lstMovie.add(new Movie(11,"IT",R.drawable.it_bg," ",R.drawable.it,"", "", "" ));

        //Khởi lạo list thể loại
        lstCategory=new ArrayList<>();
        lstCategory.add(new Category(1,"Action"));
        lstCategory.add(new Category(2,"Drama"));
        lstCategory.add(new Category(3,"Horror"));
        lstCategory.add(new Category(4,"Cartoon"));
        lstCategory.add(new Category(5,"Adventure"));

        //khơi tao list movie_category
        lstMovieCategory=new ArrayList<>();
        lstMovieCategory.add(new Movie_Category(1,3));
        lstMovieCategory.add(new Movie_Category(2,3));
        lstMovieCategory.add(new Movie_Category(3,4));
        lstMovieCategory.add(new Movie_Category(4,4));
        lstMovieCategory.add(new Movie_Category(5,4));
        lstMovieCategory.add(new Movie_Category(6,1));
        lstMovieCategory.add(new Movie_Category(6,5));
        lstMovieCategory.add(new Movie_Category(7,4));
        lstMovieCategory.add(new Movie_Category(8,1));
        lstMovieCategory.add(new Movie_Category(9,3));
        lstMovieCategory.add(new Movie_Category(10,3));
        lstMovieCategory.add(new Movie_Category(11,3));


        lstActionbMovie=new ArrayList<>();


        lstHorrorMovie=new ArrayList<>();

        lstDramaMovie=new ArrayList<>();

        lstCartoonMovie=new ArrayList<>();

        lstAdventureMovie=new ArrayList<>();




        movieAdapter=new MovieAdapter(this,lstMovie,this);
        Categorytab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        movieAdapter=new MovieAdapter(MainActivity.this,lstMovie,MainActivity.this);
                        MoveRV.setAdapter(movieAdapter);
                        break;
                    case 1:

                        movieAdapter=new MovieAdapter(MainActivity.this,lstActionbMovie,MainActivity.this);
                        MoveRV.setAdapter(movieAdapter);
                        break;
                    case 2:

                        movieAdapter=new MovieAdapter(MainActivity.this,lstDramaMovie,MainActivity.this);
                        MoveRV.setAdapter(movieAdapter);
                        break;
                    case 3:
                        movieAdapter=new MovieAdapter(MainActivity.this,lstHorrorMovie,MainActivity.this);
                        MoveRV.setAdapter(movieAdapter);
                        break;
                    case 4:
                        movieAdapter=new MovieAdapter(MainActivity.this,lstCartoonMovie,MainActivity.this);
                        MoveRV.setAdapter(movieAdapter);
                        break;
                    case 5:
                        movieAdapter=new MovieAdapter(MainActivity.this,lstAdventureMovie,MainActivity.this);
                        MoveRV.setAdapter(movieAdapter);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        MoveRV.setAdapter(movieAdapter);
        MoveRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));


        //Menu bottom
        BottomNavigationView menu=findViewById(R.id.Navigation);
        menu.setSelectedItemId(R.id.mainActivity);
        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.myMovie:
                        startActivity(new Intent(MainActivity.this, MyMovie.class));
                        overridePendingTransition(0,0);
                        finishAffinity();
                        return  true;
                    case R.id.UserInfo:
                        startActivity(new Intent(MainActivity.this, UserActivity.class));
                        overridePendingTransition(0,0);
                        finishAffinity();
                        return  true;
                    case R.id.mainActivity:
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        //tạo vận chuyển animation giữa 2 actyvity
        Intent intent1=new Intent(MainActivity.this, MovieDetailActivity.class);
        intent1.putExtra("title",movie.getTitle());
        intent1.putExtra("imgUrl",movie.getThumbnail());
        intent1.putExtra("imgCover",movie.getCoverPhoto());
        intent1.putExtra("rating",movie.getRating());


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