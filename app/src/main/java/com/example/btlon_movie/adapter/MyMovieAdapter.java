package com.example.btlon_movie.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.btlon_movie.models.Movie;

import java.util.ArrayList;
import com.example.btlon_movie.R;

public class MyMovieAdapter extends BaseAdapter {

    //    Ngữ cảnh của Activity sử dụng( ngữ cảnh lớp cha)
    private Activity activity;
    //    Nguồn dữ liệu
    private ArrayList<Movie> data;
    //    Bộ phân tích layout
    private LayoutInflater inflater;
    private ArrayList<Movie> databackup;
    public MyMovieAdapter(Activity activity, ArrayList<Movie> data) {
        this.activity = activity;
        this.data = data;
        this.inflater =(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getID();
    }
    private  class ViewHolder{
        TextView Name, Description;
        ImageView image;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=view;
        ViewHolder viewHolder;
        if(v==null) {
            v = inflater.inflate(R.layout.myitemmovie, null);
            viewHolder=new ViewHolder();
            viewHolder.Name=v.findViewById(R.id.NameMovie);
            viewHolder.Description=v.findViewById(R.id.DesMovie);
            viewHolder.image=v.findViewById(R.id.imgItemMovie);
            v.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) v.getTag();
        }
        viewHolder.Name.setText(data.get(i).getName());
        viewHolder.Description.setText(data.get(i).getDescription());
        String name =data.get(i).getThumbnail();
        Context context = viewHolder.image.getContext();
        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        viewHolder.image.setImageResource(id);
        
        return v;
    }
}
