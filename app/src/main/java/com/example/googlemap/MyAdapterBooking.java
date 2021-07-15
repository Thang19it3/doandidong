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

public class MyAdapterBooking extends RecyclerView.Adapter<MyAdapterBooking.MyViewHolder>  {

    Context context;
    ArrayList<Booking> list;

    public MyAdapterBooking(Context context, ArrayList<Booking> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_booking,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Booking booking = list.get(position);
        holder.tvticket.setText("Mã vé : " + booking.getTicket());
        holder.tvname.setText("Tên sân : "+booking.getPitchid());
        holder.tvdate.setText("Ngày đặt : "+booking.getTime_booking());
        holder.tvtime.setText("Thời gian : "+booking.getStart_time()+" - " + booking.getEnd_time());
        holder.tvprice.setText("Giá tiền : "+Float.toString(booking.getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvticket, tvname, tvadd, tvdate, tvtime, tvphone, tvprice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvticket = (TextView) itemView.findViewById(R.id.ticketpitchbook);
            tvname = (TextView) itemView.findViewById(R.id.namepitchbook);
//            tvadd = (TextView) itemView.findViewById(R.id.addpitchbook);
            tvdate = (TextView) itemView.findViewById(R.id.datepitchbook);
            tvtime = (TextView) itemView.findViewById(R.id.timepitchbook);
//            tvphone = (TextView) itemView.findViewById(R.id.phonepitchbook);
            tvprice = (TextView) itemView.findViewById(R.id.pricepitchbook);
        }

    }
}
