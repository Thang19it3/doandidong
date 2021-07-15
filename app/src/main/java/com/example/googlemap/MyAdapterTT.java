package com.example.googlemap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterTT extends RecyclerView.Adapter<MyAdapterTT.MyViewHolder>  {

    Context context;
    ArrayList<TinTuc> list;

    public MyAdapterTT(Context context, ArrayList<TinTuc> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tin_tuc,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TinTuc tinTuc = list.get(position);
        holder.tvname.setText(tinTuc.getName());
        holder.tvcontent.setText(tinTuc.getContent());
        holder.tvtime.setText(tinTuc.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvname, tvcontent, tvtime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = (TextView) itemView.findViewById(R.id.namett);
            tvcontent = (TextView) itemView.findViewById(R.id.noidungtt);
            tvtime = (TextView) itemView.findViewById(R.id.timett);
        }

    }
}
