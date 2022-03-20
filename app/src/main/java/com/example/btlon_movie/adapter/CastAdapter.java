package com.example.btlon_movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btlon_movie.R;
import com.example.btlon_movie.models.Cast;

import java.util.List;
import java.util.zip.Inflater;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    Context context;
    List<Cast> data;

    public CastAdapter(Context context, List<Cast> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_cast,parent,false);

        return new CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Glide.with(context).load(data.get(position).getImg_Link()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_cast);
        }
    }
}
