package com.example.btlon_movie.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btlon_movie.models.Category;
import com.example.btlon_movie.models.Country;
import com.example.btlon_movie.models.Movie;
import com.example.btlon_movie.adapter.MovieAdapter;
import com.example.btlon_movie.adapter.MovieItemClickListener;
import com.example.btlon_movie.R;
import com.example.btlon_movie.models.Movie_Category;
import com.example.btlon_movie.models.Slide;
import com.example.btlon_movie.adapter.SlidePagesAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {
    private ArrayList<Movie> ListSlide;
    private ViewPager sliderPage;
    private SlidePagesAdapter MySlideAdapter;
    private TabLayout RadioSlider;
    private RecyclerView MoveRV;
    private TabLayout Categorytab;
    private MovieAdapter movieAdapter;
    private List<Movie> lstMovie,lstActionbMovie,lstHorrorMovie,lstDramaMovie,lstCartoonMovie,lstAdventureMovie;
    private List<Category> lstCategory;
    private List<Country> lstCountry;
    private List<Movie_Category> lstMovieCategory;
    FirebaseDatabase database;
    DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tham chieu
        sliderPage=findViewById(R.id.ViewPage);
        RadioSlider=findViewById(R.id.radioSelector);
        MoveRV=findViewById(R.id.Rv_movie);
        Categorytab=findViewById(R.id.tabLayout);
        database=FirebaseDatabase.getInstance();
        myref= database.getReference();
        //Tao Slider
        ListSlide=new ArrayList<>();
        GetDataSlider();
        RadioSlider.setupWithViewPager(sliderPage, true);
        // Set Time
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(), 6000, 6000);

        //Khởi lạo list thể loại
        lstCategory=new ArrayList<>();
        //getListdata("","Category");
        //list country
        lstCountry=new ArrayList<>();
      //  getListdata("","Country");
        //khơi tao list movie_category
        lstMovieCategory=new ArrayList<>();
        lstMovie= new ArrayList<>();
        MoveRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        getListdata("","Movie");
        Categorytab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        lstMovie.clear();
                        getListdata("","Movie");
                        break;
                    case 1:
                        lstMovie.clear();
                        getListdata("Action","Movie");

                        break;
                    case 2:

                        lstMovie.clear();
                        getListdata("Drama","Movie");
                        break;
                    case 3:
                        lstMovie.clear();
                        getListdata("Horror","Movie");
                        break;
                    case 4:
                        lstMovie.clear();
                        getListdata("Cartoon","Movie");
                        break;
                    case 5:
                        lstMovie.clear();
                        getListdata("Adventure","Movie");
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
    //lấy dữ liệu từ firebase
    private void getListdata(String keys,String child){
        myref= database.getReference();
        List<Category> DScategory=new ArrayList<>();
        List<Country> DScountry=new ArrayList<>();
        myref.child(child).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot sanp:snapshot.getChildren()){
                        if(child=="Movie"){
                            Movie movie=sanp.getValue(Movie.class);
                            DScategory.clear();
                            DScountry.clear();
                            for(int i=1;i<=sanp.child("Category").getChildrenCount();i++){
                                Category category=sanp.child("Category/"+i).getValue(Category.class);
                                DScategory.add(category);
                            }
                            for(int i=1;i<=sanp.child("Country").getChildrenCount();i++){
                                Country country=sanp.child("Country/"+i).getValue(Country.class);
                                DScountry.add(country);
                            }
                            movie.setCategory(DScategory);
                            movie.setCountry(DScountry);

                            if(keys==""){
                                lstMovie.add(movie);

                            }
                            else{
                                for(int i=0;i<sanp.child("Category").getChildrenCount();i++){
                                    String theloai=sanp.child("Category/"+i+"/Name").getValue().toString();
                                    if(theloai.equals(keys))
                                    {
                                        lstMovie.add(movie);
                                    }
                                }
                            }
                        }
                        if(child=="Category"){
                            Category category=sanp.getValue(Category.class);
                            lstCategory.add(category);
                        }
                        if(child=="Country"){
                            Country country =sanp.getValue(Country.class);
                            lstCountry.add(country);
                        }
                }

                movieAdapter=new MovieAdapter(MainActivity.this,lstMovie,MainActivity.this);
                MoveRV.setAdapter(movieAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        //tạo vận chuyển animation giữa 2 actyvity
        Intent intent1=new Intent(MainActivity.this, MovieDetailActivity.class);
        intent1.putExtra("title",movie.getName());
        intent1.putExtra("imgUrl",movie.getThumbnail());
        intent1.putExtra("imgCover",movie.getImage());
        intent1.putExtra("rating",movie.getRating());
        intent1.putExtra("Language",movie.getLanguage());
        intent1.putExtra("Year",movie.getYear());

        intent1.putExtra("ID",movie.getID());
        intent1.putExtra("Link",movie.getLink());
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
    void GetDataSlider()
    {
        myref.child("Movie").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot sanp:snapshot.getChildren()){
                    Movie movie=sanp.getValue(Movie.class);
                    if(movie.getRating().contains("Like"))
                    {
                        ListSlide.add(movie);
                    }
                }
                MySlideAdapter = new SlidePagesAdapter(MainActivity.this, ListSlide);
                sliderPage.setAdapter(MySlideAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}