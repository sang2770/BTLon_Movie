package com.example.btlon_movie.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlon_movie.R;
import com.example.btlon_movie.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    Context context;
    List<Movie> mdata;
    MovieItemClickListener movieItemClickListener;

    public MovieAdapter(Context context, List<Movie> data,MovieItemClickListener listener) {
        this.context = context;
        this.mdata = data;
        movieItemClickListener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);




        return new  MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.TvTitle.setText(mdata.get(position).getName());
        String name =mdata.get(position).getThumbnail();
        Context context = holder.ImgMovie.getContext();
        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        holder.ImgMovie.setImageResource(id);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView TvTitle;
        private ImageView ImgMovie;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TvTitle=itemView.findViewById(R.id.item_movie_title);
            ImgMovie=itemView.findViewById(R.id.item_movie_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieItemClickListener.onMovieClick(mdata.get(getAdapterPosition()),ImgMovie);
                }
            });
        }
    }
}
