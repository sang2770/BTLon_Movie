package com.example.btlon_movie.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.btlon_movie.DownloadImageTask;
import com.example.btlon_movie.models.Movie;

import java.io.InputStream;
import java.util.ArrayList;
import com.example.btlon_movie.R;
import com.example.btlon_movie.ui.MovieDetailActivity;
import com.example.btlon_movie.ui.VideoActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        ImageButton BtnPlay, BtnDelete;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ViewHolder viewHolder;
        if (v == null) {
            v = inflater.inflate(R.layout.myitemmovie, null);
            viewHolder = new ViewHolder();
            viewHolder.Name = v.findViewById(R.id.NameMovie);
            viewHolder.Description = v.findViewById(R.id.DesMovie);
            viewHolder.image = v.findViewById(R.id.imgItemMovie);
            viewHolder.BtnPlay = v.findViewById(R.id.Btn_Play);
            viewHolder.BtnDelete = v.findViewById(R.id.Btn_Delete);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.Name.setText(data.get(i).getName());
        viewHolder.Description.setText(data.get(i).getDescription());
        String name = data.get(i).getThumbnail();
        Context context = viewHolder.image.getContext();
        new DownloadImageTask(viewHolder.image)
                .execute(name);
        viewHolder.BtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPlay = new Intent(context, VideoActivity.class);
                intentPlay.putExtra("IdVideo", data.get(i).getLink());
                context.startActivity(intentPlay);
            }
        });
        viewHolder.BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog Confirm=new AlertDialog.Builder(context).create();
                Confirm.setTitle("Warning");
                Confirm.setMessage("Bạn có chắc chắn muốn xóa không");
                Confirm.setButton(AlertDialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index) {
                        dialogInterface.dismiss();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        DatabaseReference myref = database.getReference();
                        Log.d("delete", String.valueOf(data.get(i).getID()));
                        myref.child("User").child(user.getUid()).child("MyList").child(String.valueOf(data.get(i).getID()))
                                .removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        Toast.makeText(context, "Bạn đã xóa thành công", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
                Confirm.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index) {
                        dialogInterface.dismiss();
                    }
                });
                Confirm.show();

            }
        });
        return v;
    }
}
